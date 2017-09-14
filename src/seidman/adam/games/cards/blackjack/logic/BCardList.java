package seidman.adam.games.cards.blackjack.logic;

import java.util.ArrayList;

import seidman.adam.games.cards.Card;
import seidman.adam.games.cards.Utilities;

/**
 * 
 * List of seidman.adam.games.cards.Card's customized for Blackjack use
 * 
 * @author Adam Seidman
 *
 */
public class BCardList extends ArrayList<Card> {

	private static final long serialVersionUID = 1L;
	private static final int ACE = seidman.adam.games.cards.Constants.ACE; // Import Super-Constants
	private static final int JACK = seidman.adam.games.cards.Constants.JACK;
	private static final int QUEEN = seidman.adam.games.cards.Constants.QUEEN;
	private static final int KING = seidman.adam.games.cards.Constants.KING;

	/**
	 * Check for a specific type of number card in the card list.
	 * 
	 * @param n
	 *            An integer that you want to search for.
	 * @return True, if the number exists in the card list.
	 */
	public boolean has(int n) {
		for (Card card : this) {
			if (card.getNum() == n) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @return True, if list of card contains an Ace.
	 */
	public boolean hasAce() {
		return this.has(ACE);
	}

	/**
	 * Know if the card list has a Jack, Queen, or King.
	 * 
	 * @return True, if a face card is in the card list.
	 */
	public boolean hasFaceCard() {
		return this.has(JACK) || this.has(QUEEN) || this.has(KING);
	}

	public boolean isBusted() {
		return this.sum() > Constants.NUMBER_LIMIT;
	}

	public int sum() {
		return Utilities.sumOf((Card[]) this.toArray());
	}

}
