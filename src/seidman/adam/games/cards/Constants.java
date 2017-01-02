package seidman.adam.games.cards;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;

import seidman.adam.games.utilities.Index;

public class Constants {

	protected static final int CARD_WIDTH = 140;
	protected static final int CARD_HEIGHT = 190;
	protected static final int CARD_PANEL_BUFFER = 20;
	protected static final int CLUBS = 0;
	protected static final int HEARTS = 1;
	protected static final int SPADES = 2;
	protected static final int DIAMONDS = 3;
	protected static final Index CARD_CENTER = new Index(CARD_WIDTH / 2, CARD_HEIGHT / 2);
	protected static final int ACE = 1;
	protected static final int JACK = 11;
	protected static final int QUEEN = 12;
	protected static final int KING = 13;
	protected static final Color CARD_FRONT_COLOR = Color.WHITE;
	protected static final Color CARD_BACK_COLOR = new Color(80, 80, 225);
	protected static final Color CARD_BACK_SYMBOL_COLOR = new Color(245, 245, 245);
	protected static final int[] ROUND_RECT_CONSTANTS = new int[] { 20, 22 };
	protected static final boolean DRAW_OUTLINE = false;
	protected static final Color CARD_OUTLINE_COLOR = Color.BLACK;
	protected static final int SYMBOL_WIDTH = 60;
	protected static final int SYMBOL_HEIGHT = 170;
	protected static final Dimension CLUB_SIZE = new Dimension(32, 28);
	protected static final Dimension HEART_SIZE = new Dimension(26, 25);
	protected static final Dimension SPADE_SIZE = new Dimension(32, 26);
	protected static final Dimension DIAMOND_SIZE = new Dimension(24, 28);

	// Constants- Offsets for drawing cards.
	private static final int TWO_AND_THREE_OFFSET = 60;
	private static final int EIGHT_AND_TEN_OFFSET = 30;
	private static final int OUTSIDE_OFFSET_X = 39;
	private static final int FOUR_THROUGH_TEN_OFFSET_Y = 61;
	private static final int OUTSIDE_OFFSET_Y = 20;
	private static final int SEVEN_OFFSET = 35;
	private static final int FACE_OFFSET = 32;

	protected static final HashMap<Integer, ArrayList<Index>> coordMap(int x, int y) {
		Index cardCenter = new Index(x + CARD_CENTER.x, y + CARD_CENTER.y);
		HashMap<Integer, ArrayList<Index>> ret = new HashMap<Integer, ArrayList<Index>>();
		ArrayList<Index> indexList = new ArrayList<Index>();
		indexList.add(cardCenter);
		ret.put(ACE, indexList);
		indexList = new ArrayList<Index>();
		indexList.add(new Index(cardCenter.x, cardCenter.y - TWO_AND_THREE_OFFSET));
		indexList.add(new Index(cardCenter.x, cardCenter.y + TWO_AND_THREE_OFFSET));
		ret.put(2, indexList);
		indexList = new ArrayList<Index>();
		indexList.add(new Index(cardCenter.x, cardCenter.y - TWO_AND_THREE_OFFSET));
		indexList.add(cardCenter);
		indexList.add(new Index(cardCenter.x, cardCenter.y + TWO_AND_THREE_OFFSET));
		ret.put(3, indexList);
		indexList = new ArrayList<Index>();
		indexList.add(new Index(cardCenter.x - OUTSIDE_OFFSET_X, cardCenter.y - FOUR_THROUGH_TEN_OFFSET_Y));
		indexList.add(new Index(cardCenter.x + OUTSIDE_OFFSET_X, cardCenter.y - FOUR_THROUGH_TEN_OFFSET_Y));
		indexList.add(new Index(cardCenter.x - OUTSIDE_OFFSET_X, cardCenter.y + FOUR_THROUGH_TEN_OFFSET_Y));
		indexList.add(new Index(cardCenter.x + OUTSIDE_OFFSET_X, cardCenter.y + FOUR_THROUGH_TEN_OFFSET_Y));
		ret.put(4, indexList);
		indexList = new ArrayList<Index>();
		indexList.add(new Index(cardCenter.x - OUTSIDE_OFFSET_X, cardCenter.y - FOUR_THROUGH_TEN_OFFSET_Y));
		indexList.add(new Index(cardCenter.x + OUTSIDE_OFFSET_X, cardCenter.y - FOUR_THROUGH_TEN_OFFSET_Y));
		indexList.add(new Index(cardCenter.x - OUTSIDE_OFFSET_X, cardCenter.y + FOUR_THROUGH_TEN_OFFSET_Y));
		indexList.add(new Index(cardCenter.x + OUTSIDE_OFFSET_X, cardCenter.y + FOUR_THROUGH_TEN_OFFSET_Y));
		indexList.add(cardCenter);
		ret.put(5, indexList);
		indexList = new ArrayList<Index>();
		indexList.add(new Index(cardCenter.x - OUTSIDE_OFFSET_X, cardCenter.y - FOUR_THROUGH_TEN_OFFSET_Y));
		indexList.add(new Index(cardCenter.x + OUTSIDE_OFFSET_X, cardCenter.y - FOUR_THROUGH_TEN_OFFSET_Y));
		indexList.add(new Index(cardCenter.x - OUTSIDE_OFFSET_X, cardCenter.y + FOUR_THROUGH_TEN_OFFSET_Y));
		indexList.add(new Index(cardCenter.x + OUTSIDE_OFFSET_X, cardCenter.y + FOUR_THROUGH_TEN_OFFSET_Y));
		indexList.add(new Index(cardCenter.x - OUTSIDE_OFFSET_X, cardCenter.y));
		indexList.add(new Index(cardCenter.x + OUTSIDE_OFFSET_X, cardCenter.y));
		ret.put(6, indexList);
		indexList = new ArrayList<Index>();
		indexList.add(new Index(cardCenter.x - OUTSIDE_OFFSET_X, cardCenter.y - FOUR_THROUGH_TEN_OFFSET_Y));
		indexList.add(new Index(cardCenter.x + OUTSIDE_OFFSET_X, cardCenter.y - FOUR_THROUGH_TEN_OFFSET_Y));
		indexList.add(new Index(cardCenter.x - OUTSIDE_OFFSET_X, cardCenter.y + FOUR_THROUGH_TEN_OFFSET_Y));
		indexList.add(new Index(cardCenter.x + OUTSIDE_OFFSET_X, cardCenter.y + FOUR_THROUGH_TEN_OFFSET_Y));
		indexList.add(new Index(cardCenter.x - OUTSIDE_OFFSET_X, cardCenter.y));
		indexList.add(new Index(cardCenter.x + OUTSIDE_OFFSET_X, cardCenter.y));
		indexList.add(new Index(cardCenter.x, cardCenter.y - SEVEN_OFFSET));
		ret.put(7, indexList);
		indexList = new ArrayList<Index>();
		indexList.add(new Index(cardCenter.x - OUTSIDE_OFFSET_X, cardCenter.y - FOUR_THROUGH_TEN_OFFSET_Y));
		indexList.add(new Index(cardCenter.x + OUTSIDE_OFFSET_X, cardCenter.y - FOUR_THROUGH_TEN_OFFSET_Y));
		indexList.add(new Index(cardCenter.x - OUTSIDE_OFFSET_X, cardCenter.y + FOUR_THROUGH_TEN_OFFSET_Y));
		indexList.add(new Index(cardCenter.x + OUTSIDE_OFFSET_X, cardCenter.y + FOUR_THROUGH_TEN_OFFSET_Y));
		indexList.add(new Index(cardCenter.x - OUTSIDE_OFFSET_X, cardCenter.y));
		indexList.add(new Index(cardCenter.x + OUTSIDE_OFFSET_X, cardCenter.y));
		indexList.add(new Index(cardCenter.x, cardCenter.y - EIGHT_AND_TEN_OFFSET));
		indexList.add(new Index(cardCenter.x, cardCenter.y + EIGHT_AND_TEN_OFFSET));
		ret.put(8, indexList);
		indexList = new ArrayList<Index>();
		indexList.add(new Index(cardCenter.x - OUTSIDE_OFFSET_X, cardCenter.y - FOUR_THROUGH_TEN_OFFSET_Y));
		indexList.add(new Index(cardCenter.x + OUTSIDE_OFFSET_X, cardCenter.y - FOUR_THROUGH_TEN_OFFSET_Y));
		indexList.add(new Index(cardCenter.x - OUTSIDE_OFFSET_X, cardCenter.y + FOUR_THROUGH_TEN_OFFSET_Y));
		indexList.add(new Index(cardCenter.x + OUTSIDE_OFFSET_X, cardCenter.y + FOUR_THROUGH_TEN_OFFSET_Y));
		indexList.add(new Index(cardCenter.x - OUTSIDE_OFFSET_X, cardCenter.y - OUTSIDE_OFFSET_Y));
		indexList.add(new Index(cardCenter.x + OUTSIDE_OFFSET_X, cardCenter.y - OUTSIDE_OFFSET_Y));
		indexList.add(new Index(cardCenter.x - OUTSIDE_OFFSET_X, cardCenter.y + OUTSIDE_OFFSET_Y));
		indexList.add(new Index(cardCenter.x + OUTSIDE_OFFSET_X, cardCenter.y + OUTSIDE_OFFSET_Y));
		indexList.add(cardCenter);
		ret.put(9, indexList);
		indexList = new ArrayList<Index>();
		indexList.add(new Index(cardCenter.x - OUTSIDE_OFFSET_X, cardCenter.y - FOUR_THROUGH_TEN_OFFSET_Y));
		indexList.add(new Index(cardCenter.x + OUTSIDE_OFFSET_X, cardCenter.y - FOUR_THROUGH_TEN_OFFSET_Y));
		indexList.add(new Index(cardCenter.x - OUTSIDE_OFFSET_X, cardCenter.y + FOUR_THROUGH_TEN_OFFSET_Y));
		indexList.add(new Index(cardCenter.x + OUTSIDE_OFFSET_X, cardCenter.y + FOUR_THROUGH_TEN_OFFSET_Y));
		indexList.add(new Index(cardCenter.x - OUTSIDE_OFFSET_X, cardCenter.y - OUTSIDE_OFFSET_Y));
		indexList.add(new Index(cardCenter.x + OUTSIDE_OFFSET_X, cardCenter.y - OUTSIDE_OFFSET_Y));
		indexList.add(new Index(cardCenter.x - OUTSIDE_OFFSET_X, cardCenter.y + OUTSIDE_OFFSET_Y));
		indexList.add(new Index(cardCenter.x + OUTSIDE_OFFSET_X, cardCenter.y + OUTSIDE_OFFSET_Y));
		indexList.add(new Index(cardCenter.x, cardCenter.y - EIGHT_AND_TEN_OFFSET));
		indexList.add(new Index(cardCenter.x, cardCenter.y + EIGHT_AND_TEN_OFFSET));
		ret.put(10, indexList);
		indexList = new ArrayList<Index>();
		indexList.add(new Index(cardCenter.x, cardCenter.y + FACE_OFFSET));
		ret.put(JACK, indexList);
		ret.put(QUEEN, indexList);
		ret.put(KING, indexList);
		return ret;
	}

}