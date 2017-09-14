package seidman.adam.games.snake.utilities;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import seidman.adam.games.utilities.Index;

/**
 * 
 * @author Adam Seidman
 *
 */
public abstract class Variables {

	private static final int SQUARE_SIZE_PX = 25;

	// Messages
	public static final String LEAVE_MESSAGE = "Thanks for playing!";
	public static final String PLAY_AGAIN_MESSAGE = "Would you like to play again?";
	public static final String POINTS_PHRASE = "  |  Points: ";
	public static final String[] PAUSE_MESSAGE = new String[] { "Press the Space Bar to Continue",
			"Press 'P' key to Pause", "Use the Arrow Keys to Turn the Snake" };
	public static final String QUIT_MESSAGE = "Are you sure you would like to quit?";
	public static final String TITLE = "Snake";

	// Colors
	public static final Color BACKGROUND_COLOR = Color.WHITE;
	public static final Color PAUSE_TEXT_COLOR = new Color(105, 190, 105, 195);
	public static final Color SNAKE_AND_DOT_COLOR = Color.BLACK;

	// Sizing and Positioning
	public static final int GRID_BUFFER = 5;
	public static final int NUM_TILES_HIGH = 15;
	public static final int NUM_TILES_WIDE = 30;
	public static final int DOT_UPDATE_CONSTANT = (NUM_TILES_WIDE * NUM_TILES_HIGH) + 5;
	public static final int[] PAUSE_MESSAGE_BUFFER_X = new int[] { 57, 174, 0 };
	public static final int PAUSE_MESSAGE_NEWLINE_BUFFER = 110;
	public static final Index PAUSE_SCREEN_INDEX = new Index(36, 100);
	public static final boolean RESIZABLE = false;
	public static final Dimension SCREEN_SIZE = new Dimension(
			((SQUARE_SIZE_PX + GRID_BUFFER) * NUM_TILES_WIDE) + GRID_BUFFER,
			((SQUARE_SIZE_PX + GRID_BUFFER) * NUM_TILES_HIGH) + GRID_BUFFER);
	public static final Index STARTING_INDEX = new Index(7, 7);
	public static final int STARTING_SIZE = 5;

	// Other Various Constants
	public static final Font PAUSE_FONT = new Font("Arial", Font.BOLD, 46);
	public static final double UPDATE_DELAY = 0.175;
	public static final boolean USE_WINDOW_LISTENER = false;

}
