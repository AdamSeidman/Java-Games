package seidman.adam.games.cards;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import seidman.adam.games.utilities.Index;

public class Constants {

	protected static final int CARD_WIDTH = 140;
	protected static final int CARD_HEIGHT = 190;
	protected static final Color DEFAULT_BACKGROUND_COLOR = new Color(40, 140, 73);
	
	// CardPanel Constants
	protected static final int MIN_BUFFER_Y = 20;
	protected static final int MIN_BUFFER_X = 20;
	protected static final double MIN_CARD_SCALE = 0.35;

	// Card Face Coloring
	protected static final Color CARD_FRONT_COLOR = Color.WHITE;
	protected static final Color CARD_BACK_COLOR = new Color(80, 80, 225);
	protected static final Color CARD_BACK_SYMBOL_COLOR = new Color(245, 245, 245);
	protected static final int[] ROUND_RECT_CONSTANTS = new int[] { 20, 22 };
	protected static final boolean DRAW_CARD_OUTLINE = true;
	protected static final Color CARD_OUTLINE_COLOR = Color.BLACK;
	protected static final int SYMBOL_WIDTH = 60;
	protected static final int SYMBOL_HEIGHT = 170;

	// Suit Constants
	protected static final Color CLUB_COLOR = Color.BLACK;
	protected static final Dimension CLUB_SIZE = new Dimension(32, 28);
	protected static final Color HEART_COLOR = Color.RED;
	protected static final Dimension HEART_SIZE = new Dimension(26, 25);
	protected static final Color SPADE_COLOR = Color.BLACK;
	protected static final Dimension SPADE_SIZE = new Dimension(24, 26);
	protected static final Color DIAMOND_COLOR = Color.RED;
	protected static final Dimension DIAMOND_SIZE = new Dimension(24, 28);

	// Randomizer Numbers
	protected static final int CLUBS = 0;
	protected static final int HEARTS = 1;
	protected static final int SPADES = 2;
	protected static final int DIAMONDS = 3;

	// Card Numbers
	public static final int ACE = 1;
	public static final int JACK = 11;
	public static final int QUEEN = 12;
	public static final int KING = 13;

	// Constants- Offsets for drawing cards.
	private static final int TWO_AND_THREE_OFFSET = 60;
	private static final int FOUR_THROUGH_TEN_OFFSET_Y = 61;
	private static final int SEVEN_OFFSET = 35;
	private static final int EIGHT_AND_TEN_OFFSET = 30;
	private static final int FACE_OFFSET = 32;
	private static final int OUTSIDE_OFFSET_X = 39;
	private static final int OUTSIDE_OFFSET_Y = 20;

	protected static final ArrayList<Index> coordList(int x, int y, Card card) {
		Index cardCenter = new Index(x + (card.scale(CARD_WIDTH) / 2), y + (card.scale(CARD_HEIGHT) / 2));
		int outOffX = card.scale(OUTSIDE_OFFSET_X);
		int outOffY = card.scale(OUTSIDE_OFFSET_Y);
		int fourThroughTenY = card.scale(FOUR_THROUGH_TEN_OFFSET_Y);
		int twoAndThree = card.scale(TWO_AND_THREE_OFFSET);
		int eightAndTen = card.scale(EIGHT_AND_TEN_OFFSET);
		ArrayList<Index> indexList = new ArrayList<Index>();

		switch (card.getNum()) {
		case ACE:
			indexList.add(cardCenter);
			break;
		case 2:
			indexList.add(new Index(cardCenter.getX(), cardCenter.getY() - twoAndThree));
			indexList.add(new Index(cardCenter.getX(), cardCenter.getY() + twoAndThree));
			break;
		case 3:
			indexList.add(new Index(cardCenter.getX(), cardCenter.getY() - twoAndThree));
			indexList.add(cardCenter);
			indexList.add(new Index(cardCenter.getX(), cardCenter.getY() + twoAndThree));
			break;
		case 4:
			indexList.add(new Index(cardCenter.getX() - outOffX, cardCenter.getY() - fourThroughTenY));
			indexList.add(new Index(cardCenter.getX() + outOffX, cardCenter.getY() - fourThroughTenY));
			indexList.add(new Index(cardCenter.getX() - outOffX, cardCenter.getY() + fourThroughTenY));
			indexList.add(new Index(cardCenter.getX() + outOffX, cardCenter.getY() + fourThroughTenY));
			break;
		case 5:
			indexList.add(new Index(cardCenter.getX() - outOffX, cardCenter.getY() - fourThroughTenY));
			indexList.add(new Index(cardCenter.getX() + outOffX, cardCenter.getY() - fourThroughTenY));
			indexList.add(new Index(cardCenter.getX() - outOffX, cardCenter.getY() + fourThroughTenY));
			indexList.add(new Index(cardCenter.getX() + outOffX, cardCenter.getY() + fourThroughTenY));
			indexList.add(cardCenter);
			break;
		case 6:
			indexList.add(new Index(cardCenter.getX() - outOffX, cardCenter.getY() - fourThroughTenY));
			indexList.add(new Index(cardCenter.getX() + outOffX, cardCenter.getY() - fourThroughTenY));
			indexList.add(new Index(cardCenter.getX() - outOffX, cardCenter.getY() + fourThroughTenY));
			indexList.add(new Index(cardCenter.getX() + outOffX, cardCenter.getY() + fourThroughTenY));
			indexList.add(new Index(cardCenter.getX() - outOffX, cardCenter.getY()));
			indexList.add(new Index(cardCenter.getX() + outOffX, cardCenter.getY()));
			break;
		case 7:
			indexList.add(new Index(cardCenter.getX() - outOffX, cardCenter.getY() - fourThroughTenY));
			indexList.add(new Index(cardCenter.getX() + outOffX, cardCenter.getY() - fourThroughTenY));
			indexList.add(new Index(cardCenter.getX() - outOffX, cardCenter.getY() + fourThroughTenY));
			indexList.add(new Index(cardCenter.getX() + outOffX, cardCenter.getY() + fourThroughTenY));
			indexList.add(new Index(cardCenter.getX() - outOffX, cardCenter.getY()));
			indexList.add(new Index(cardCenter.getX() + outOffX, cardCenter.getY()));
			indexList.add(new Index(cardCenter.getX(), cardCenter.getY() - card.scale(SEVEN_OFFSET)));
			break;
		case 8:
			indexList.add(new Index(cardCenter.getX() - outOffX, cardCenter.getY() - fourThroughTenY));
			indexList.add(new Index(cardCenter.getX() + outOffX, cardCenter.getY() - fourThroughTenY));
			indexList.add(new Index(cardCenter.getX() - outOffX, cardCenter.getY() + fourThroughTenY));
			indexList.add(new Index(cardCenter.getX() + outOffX, cardCenter.getY() + fourThroughTenY));
			indexList.add(new Index(cardCenter.getX() - outOffX, cardCenter.getY()));
			indexList.add(new Index(cardCenter.getX() + outOffX, cardCenter.getY()));
			indexList.add(new Index(cardCenter.getX(), cardCenter.getY() - eightAndTen));
			indexList.add(new Index(cardCenter.getX(), cardCenter.getY() + eightAndTen));
			break;
		case 9:
			indexList.add(new Index(cardCenter.getX() - outOffX, cardCenter.getY() - fourThroughTenY));
			indexList.add(new Index(cardCenter.getX() + outOffX, cardCenter.getY() - fourThroughTenY));
			indexList.add(new Index(cardCenter.getX() - outOffX, cardCenter.getY() + fourThroughTenY));
			indexList.add(new Index(cardCenter.getX() + outOffX, cardCenter.getY() + fourThroughTenY));
			indexList.add(new Index(cardCenter.getX() - outOffX, cardCenter.getY() - outOffY));
			indexList.add(new Index(cardCenter.getX() + outOffX, cardCenter.getY() - outOffY));
			indexList.add(new Index(cardCenter.getX() - outOffX, cardCenter.getY() + outOffY));
			indexList.add(new Index(cardCenter.getX() + outOffX, cardCenter.getY() + outOffY));
			indexList.add(cardCenter);
			break;
		case 10:
			indexList.add(new Index(cardCenter.getX() - outOffX, cardCenter.getY() - fourThroughTenY));
			indexList.add(new Index(cardCenter.getX() + outOffX, cardCenter.getY() - fourThroughTenY));
			indexList.add(new Index(cardCenter.getX() - outOffX, cardCenter.getY() + fourThroughTenY));
			indexList.add(new Index(cardCenter.getX() + outOffX, cardCenter.getY() + fourThroughTenY));
			indexList.add(new Index(cardCenter.getX() - outOffX, cardCenter.getY() - outOffY));
			indexList.add(new Index(cardCenter.getX() + outOffX, cardCenter.getY() - outOffY));
			indexList.add(new Index(cardCenter.getX() - outOffX, cardCenter.getY() + outOffY));
			indexList.add(new Index(cardCenter.getX() + outOffX, cardCenter.getY() + outOffY));
			indexList.add(new Index(cardCenter.getX(), cardCenter.getY() - eightAndTen));
			indexList.add(new Index(cardCenter.getX(), cardCenter.getY() + eightAndTen));
			break;
		default: // Face Cards
			indexList.add(new Index(cardCenter.getX(), cardCenter.getY() + card.scale(FACE_OFFSET)));
		}
		return indexList;
	}

}
