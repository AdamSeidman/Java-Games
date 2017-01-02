package seidman.adam.games.snake.utilities;

import java.awt.Color;
import java.awt.Dimension;

import seidman.adam.games.utilities.Index;

public abstract class Variables {

	private static final int SQUARE_SIZE_PX = 25;

	public static final String TITLE = "Snake";
	public static final String QUIT_MESSAGE = "Are you sure you would like to quit?";
	public static final String LEAVE_MESSAGE = "Thanks for playing!";
	public static final String PLAY_AGAIN_MESSAGE = "Would you like to play again?";
	public static final boolean RESIZABLE = false;
	public static final int GRID_BUFFER = 5;
	public static final Index STARTING_INDEX = new Index(7, 7);
	public static final int NUM_TILES_WIDE = 30;
	public static final int NUM_TILES_HIGH = 15;
	public static final Dimension SCREEN_SIZE = new Dimension(
			((SQUARE_SIZE_PX + GRID_BUFFER) * NUM_TILES_WIDE) + GRID_BUFFER,
			((SQUARE_SIZE_PX + GRID_BUFFER) * NUM_TILES_HIGH) + GRID_BUFFER);
	public static final int STARTING_SIZE = 5;
	public static final Color SNAKE_COLOR = Color.BLACK;
	public static final Color NOT_SNAKE_COLOR = Color.WHITE;
	public static final Color DOT_COLOR = new Color(1, 1, 1);
	public static final double UPDATE_DELAY = 0.25;

}
