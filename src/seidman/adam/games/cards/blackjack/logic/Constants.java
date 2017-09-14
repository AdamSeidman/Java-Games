package seidman.adam.games.cards.blackjack.logic;

import java.awt.Color;

/**
 * 
 * @author Adam Seidman
 *
 */
public abstract class Constants {

	static final int ACE_ADDITIONAL_VALUE = 10;
	static final Color BG_COLOR = Color.GREEN;
	static final int BLACKJACK = 21;
	static final int[] CARDS_TO_FLIP = new int[] { 0 };
	static final int DEALER_HIT_NUMBER = 16; // TODO check
	static final int[] DEALER_STARTING_CARDS = new int[] { 0, 1 };
	static final int DECK_STARTING_INDEX = 4;
	static final int NUMBER_LIMIT = 21;
	static final int[] PLAYER_STARTING_CARDS = new int[] { 2, 3 };
	static final boolean WIN_ON_TIE = false; // TODO check

}
