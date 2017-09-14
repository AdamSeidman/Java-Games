package seidman.adam.games.cards.blackjack.logic;

import javax.swing.JPanel;

import seidman.adam.games.cards.Card;
import seidman.adam.games.cards.Deck;
import seidman.adam.games.cards.Utilities;

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

	public boolean isDealerWinning() {
		return this._dealerCards.sum() >= this._playerCards.sum() && !this._dealerCards.isBusted();
	}

	public boolean isPlayerWinning() {
		if (this._dealerCards.sum() == this._playerCards.sum()) {
			return Constants.WIN_ON_TIE;
		}
		return !this.isDealerWinning();
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
	 * Run the sequence of hitting/staying for the dealer's cards.
	 * @return True, if the dealer busts.
	 */
	public boolean runDealerSequence() {
		// TODO
		// TODO Also a pausable method.
		return this._dealerCards.isBusted();
	}

}
