package seidman.adam.games.cards.blackjack.logic;

import javax.swing.JPanel;

import seidman.adam.games.cards.Card;
import seidman.adam.games.cards.Deck;

/**
 * 
 * Logic for the Blackjack Game.
 * 
 * @author Adam Seidman
 *
 */
public class BlackjackGame extends JPanel {

	private static final long serialVersionUID = 1L;
	private static BlackjackGame _instance;

	private BCardList _dealerCards;
	private Deck _deck = new Deck();
	private int _deckIndex;
	private BCardList _playerCards;

	/**
	 * Get the current running instance of the Blackjack Game.
	 * 
	 * @return The current BlackjackGame.
	 */
	public static BlackjackGame getInstance() {
		if (_instance == null) {
			_instance = new BlackjackGame();
		}
		return _instance;
	}

	/**
	 * Create a BlackjackGame
	 */
	private BlackjackGame() {
		this.resetRound();
	}

	/**
	 * Calls the sequence that occurs when the player chooses to hit.
	 * 
	 * @return True, if player busts.
	 */
	public boolean callPlayerHit() {
		this._playerCards.add(this._deck.getDeck()[this._deckIndex++]);
		return this._playerCards.isBusted();
	}

	public boolean dealerHasBlackjack() {
		return this.hasBlackjack(this._dealerCards, Constants.DEALER_STARTING_CARDS.length);
	}

	public boolean dealerHasNumLimit() {
		return this.hasNumberLimit(this._dealerCards);
	}

	public Card[] getDealerCards() {
		Card[] ret = new Card[this._dealerCards.size()];
		for (int i = 0; i < ret.length; i++) {
			ret[i] = this._dealerCards.get(i).clone();
		}
		return ret;
	}

	public Card[] getPlayerCards() {
		Card[] ret = new Card[this._playerCards.size()];
		for (int i = 0; i < ret.length; i++) {
			ret[i] = this._playerCards.get(i).clone();
		}
		return ret;
	}

	boolean hasBlackjack(BCardList list, int handSizeLimit) {
		return list.size() == handSizeLimit && list.get(0).isBlackjackWith(list.get(1));
	}

	boolean hasNumberLimit(BCardList list) {
		return list.sum() == Constants.NUMBER_LIMIT;
	}

	public boolean isDealerWinning() {
		if (this.playerHasBlackjack() && this.dealerHasBlackjack()
				|| this.playerHasNumLimit() && this.dealerHasNumLimit()) {
			return false;
		}
		return this._dealerCards.sum() >= this._playerCards.sum() && !this._dealerCards.isBusted();
	}

	public boolean isPlayerWinning() {
		if (this.playerHasBlackjack()) {
			return true;
		}
		if (this.playerHasNumLimit()) {
			return !this.dealerHasBlackjack();
		}
		if (this._dealerCards.sum() == this._playerCards.sum()) {
			return Constants.WIN_ON_TIE;
		}
		return !this.isDealerWinning();
	}

	public boolean playerHasBlackjack() {
		return this.hasBlackjack(this._playerCards, Constants.PLAYER_STARTING_CARDS.length);
	}

	public boolean playerHasNumLimit() {
		return this.hasNumberLimit(this._playerCards);
	}

	public void resetRound() {
		this._dealerCards = new BCardList();
		this._playerCards = new BCardList();
		this._deck.shuffle();
		Card[] deckCards = this._deck.getDeck();
		this._deckIndex = Constants.DECK_STARTING_INDEX;
		for (int i : Constants.CARDS_TO_FLIP) {
			deckCards[i].flipCard();
		}
		for (int i : Constants.DEALER_STARTING_CARDS) {
			this._dealerCards.add(deckCards[i]);
		}
		for (int i : Constants.PLAYER_STARTING_CARDS) {
			this._playerCards.add(deckCards[i]);
		}
	}

	/**
	 * Run the dealer sequence as specified in runDealerSequence(int millis), but
	 * does not pause between times adding card to the dealer's list. This also has
	 * no chance of throwing an InterruptedException
	 * 
	 * @return True, if the dealer busts.
	 */
	public boolean runDealerSequence() {
		try {
			return this.runDealerSequence(0);
		} catch (InterruptedException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Run the sequence of hitting/staying for the dealer's cards. Includes a pause
	 * between the reveal of each card.
	 * 
	 * @return True, if the dealer busts.
	 */
	public boolean runDealerSequence(long millis) throws InterruptedException {
		while (this._dealerCards.sum() <= Constants.DEALER_HIT_NUMBER) {
			this._dealerCards.add(this._deck.getDeck()[this._deckIndex++]);
			if (millis > 0) {
				Thread.sleep(millis);
			}
		}
		return this._dealerCards.isBusted();
	}

}
