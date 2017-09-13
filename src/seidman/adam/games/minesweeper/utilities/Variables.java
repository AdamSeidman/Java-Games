package seidman.adam.games.minesweeper.utilities;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * 
 * Difference variables needed for minesweeper development.
 * 
 * @author Adam Seidman
 *
 */
public abstract class Variables {

	// Variables that developers can see.
	public static final int GRID_BUFFER = 5;
	public static final boolean RESIZABLE = true;
	public static final String TITLE = "Minesweeper";
	public static final String LOSE_MESSAGE = "You have lost.";
	public static final String WIN_MESSAGE = "You win!";
	public static final String LEAVE_MESSAGE = "Thanks for playing!";
	public static final String PLAY_AGAIN_MESSAGE = "Would you like to play again?";
	public static final String QUIT_MESSAGE = "Are you sure you want to quit?";
	public static final String RESTART_MESSAGE = "Are you sure you would like to end the current game?";
	public static final Color LOSE_MINE_COLOR = Color.RED;
	public static final Color PROTECTED_TILE_COLOR = Color.BLUE;
	public static final Color WIN_MINE_COLOR = Color.GREEN;
	public static final Color LOSE_MINE_COLOR_CORRECT = new Color(255, 0, 255);
	public static final Color BORDER_COLOR = Color.BLACK;
	public static final String ABOUT_MESSAGE = "<html>Created by Adam J Seidman</html>";
	public static final String MINES_LEFT_LABEL_POSITION = BorderLayout.SOUTH;
	public static final Color TOUCHED_COLOR = new Color(238, 238, 238);
	public static final Color UNTOUCHED_COLOR = new Color(222, 222, 222);
	public static final String HELP_MESSAGE = "<html><nobr><b>Purpose: "
			+ "</b>Click on every tile that is <i>not</i> a mine.<br>" + "<br><b>Tile Explanations-</b><br>"
			+ "&#9<b><i>A Number: </i></b>This number of surrounding tiles are mines.<br>"
			+ "&#9<b><i>Tile is Blank: </i></b>There are no mines surrounding this tile.<br>"
			+ "&#9<i><b>Tile is Blue: </b></i>This tile is protected from left-clicking.<br>"
			+ "&#9<i><b>Tile is Red: </b></i>This tile is a mine.<br>"
			+ "&#9<i><b>Tile is Purple: </b></i>This tile is a mine, but you protected it.<br>"
			+ "&#9<b><i>Tile is Green: </i></b>This tile is a mine, but you have won.<br>"
			+ "<br><b>How to Play</b><br>" + "&#9-Left-click on a tile to select it.<br>"
			+ "&#9-Shift/Right-click on a tile to select it.</nobr></html>";

	// Unchanging variables for within this class's use.
	private static final int GRID_MAX_WIDTH = 30;
	private static final int GRID_MAX_HEIGHT = 25;
	private static final int GRID_MIN_HEIGHT = 10;
	private static final int GRID_MIN_WIDTH = 10;
	private static final int NUM_OF_MINES_MIN = 2;
	private static final int NUM_OF_MINES_MAX = 99;
	private static final int MENU_HEIGHT = 15;
	private static final int SOUTHERN_LABEL_HEIGHT = 16;
	private static final int TILE_SIZE = 30;
	private static final String MINES_LEFT_LABEL_TEXT_ALPHA = "<html><div style='text-align:center;'>Mines Left: ";
	private static final String MINES_LEFT_LABEL_TEXT_BETA = "</div></html>";

	// Changing variables for within this class's use.
	private static int _gridWidth = 10;
	private static int _gridHeight = 10;
	private static int _numOfMines = 15;
	private static int _frameWidth = _gridWidth * TILE_SIZE;
	private static int _frameHeight = SOUTHERN_LABEL_HEIGHT + MENU_HEIGHT + (_gridHeight * TILE_SIZE);
	private static int _q_numOfMines = _numOfMines; // Queued variables.
	private static int _q_gridWidth = _gridWidth;
	private static int _q_gridHeight = _gridHeight;
	private static boolean _firstTime = true;

	/**
	 * Get new variables (to put in queue) from the user.
	 */
	public static final void adjustVariables() {
		JTextField gridWidthField = new JTextField(Integer.toString(_gridWidth));
		JTextField gridHeightField = new JTextField(Integer.toString(_gridHeight));
		JTextField numMinesField = new JTextField(Integer.toString(_numOfMines));
		JPanel sizeEditPanel = new JPanel(); // Panel to adjust the amount of
												// tiles.
		sizeEditPanel.setLayout(new GridLayout(1, 4, GRID_BUFFER, GRID_BUFFER));
		sizeEditPanel.add(new JLabel("Field Width:"));
		sizeEditPanel.add(gridWidthField);
		sizeEditPanel.add(new JLabel("Field Height:"));
		sizeEditPanel.add(gridHeightField);
		JPanel minesEditPanel = new JPanel(); // Panel to adjust the amount of
												// mines.
		minesEditPanel.setLayout(new GridLayout(1, 2, GRID_BUFFER, GRID_BUFFER));
		minesEditPanel.add(new JLabel("Number of Mines:"));
		minesEditPanel.add(numMinesField);
		JPanel paneContent = new JPanel(); // All content of the JOptionPane.
		paneContent.add(sizeEditPanel);
		paneContent.add(new JLabel( // Label with board limit.
				"<html><div style='text-align: center;'><nobr><br>The board cannot be larger than "
						+ Integer.toString(GRID_MAX_WIDTH) + " tiles wide and " + Integer.toString(GRID_MAX_HEIGHT)
						+ " tiles high<br> and no smaller than " + Integer.toString(GRID_MIN_WIDTH) + " tiles wide and "
						+ Integer.toString(GRID_MIN_HEIGHT) + " tiles high.<br><br><br><br><nobr></div></html>",
				SwingConstants.CENTER));
		paneContent.add(minesEditPanel);
		paneContent.add(new JLabel( // label with limits on number of mines.
				"<html><br>The grid must contain " + Integer.toString(NUM_OF_MINES_MIN) + " - "
						+ Integer.toString(NUM_OF_MINES_MAX) + " mines.<br></html>",
				SwingConstants.CENTER));
		paneContent.setPreferredSize(new Dimension(350, 190)); // Size of help
																// message.
		if (JOptionPane.showOptionDialog(null, paneContent, "Edit", JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
				null, new String[] { "OK" }, -1) >= 0) {
			try { // Attempt to get variables that the user chose.
				int width = Integer.parseInt(gridWidthField.getText());
				int height = Integer.parseInt(gridHeightField.getText());
				int mines = Integer.parseInt(numMinesField.getText());
				if (width < GRID_MIN_WIDTH || width > GRID_MAX_WIDTH || height < GRID_MIN_HEIGHT
						|| height > GRID_MAX_HEIGHT || mines < NUM_OF_MINES_MIN || mines > NUM_OF_MINES_MAX) {
					// If out of limits, throw new Exception().
					throw new Exception();
				}
				_q_gridWidth = width; // Queue set variables.
				_q_gridHeight = height;
				_q_numOfMines = mines;
				JOptionPane.showMessageDialog(null, // Success message.
						new JLabel("<html><div style='text-align: center;'>The game has been updated."
								+ "<br>Restart to use the new options</div></html>", SwingConstants.CENTER),
						"Success", JOptionPane.PLAIN_MESSAGE, null);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, // Error message
						new JLabel("<html><div style='text-align: center;'>There was an error with your input."
								+ "<br>Please try again.</div></html>", SwingConstants.CENTER),
						"Error", JOptionPane.ERROR_MESSAGE, null);
			}
		}
	}

	/**
	 * @return Current java.awt.Dimension that the minesweeper screen should be.
	 */
	public static final Dimension frameSize() {
		return new Dimension(_frameWidth, _frameHeight);
	}

	/**
	 * @return new int[]{grid width, grid height}
	 */
	public static final int[] gridSize() {
		return new int[] { _gridWidth, _gridHeight };
	}

	/**
	 * @return A String- the title of the help message.
	 */
	public static final String helpMessageTitle() {
		String ret = (_firstTime ? "Welcome to Minesweeper" : "Help");
		_firstTime = false;
		return ret;
	}

	/**
	 * Get the text of the 'Mines Left' label.
	 * 
	 * @param n
	 *            number of tiles protected on-screen.
	 * @return A String- the text of the label.
	 */
	public static final String minesLeftLabelText(int n) {
		return MINES_LEFT_LABEL_TEXT_ALPHA + Integer.toString(_numOfMines - n) + MINES_LEFT_LABEL_TEXT_BETA;
	}

	/**
	 * @return Current number of mines on the screen. (Hidden and Not Hidden)
	 */
	public static final int numberOfMines() {
		return _numOfMines;
	}

	/**
	 * Make the queued variables become the actual variables.
	 */
	public static final void update() {
		_gridHeight = _q_gridHeight;
		_gridWidth = _q_gridWidth;
		_numOfMines = _q_numOfMines;
		_frameWidth = _q_gridWidth * TILE_SIZE;
		_frameHeight = SOUTHERN_LABEL_HEIGHT + MENU_HEIGHT + (_q_gridHeight * TILE_SIZE);
	}

}
