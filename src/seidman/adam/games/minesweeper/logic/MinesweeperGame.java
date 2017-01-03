package seidman.adam.games.minesweeper.logic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import seidman.adam.games.minesweeper.MinesweeperUI;
import seidman.adam.games.minesweeper.utilities.Variables;
import seidman.adam.games.utilities.Index;

/**
 * 
 * Game logic for minesweeper.
 * 
 * @author Adam Seidman
 *
 */
public final class MinesweeperGame {

	private static MinesweeperGame _instance;
	private static Random _random;
	private static Index _initialIndex;
	private boolean _ranOnce;

	public static MouseEvent _mouseEvent;
	public JLabel _numOfMinesLeftLabel;

	private Tile[][] _tiles;

	private MinesweeperGame() {
		_numOfMinesLeftLabel = new JLabel(Variables.minesLeftLabelText(0), SwingConstants.CENTER);
		_ranOnce = false;
		_random = new Random();
		restart();
	}

	/**
	 * @return The current Game running.
	 */
	public static MinesweeperGame getInstance() {
		if (_instance == null) {
			_instance = new MinesweeperGame();
		}
		return _instance;
	}

	/**
	 * Send the index of the first click on the board.
	 * 
	 * @param i
	 *            The index of the mouse click.
	 */
	public static void sendInitialIndex(Index i) {
		_initialIndex = i;
		MinesweeperGame.getInstance().restart();
	}

	/**
	 * @return True true if an initial index has been sent.
	 */
	public static boolean hasInitialIndex() {
		return !(_initialIndex == null);
	}

	/**
	 * Reassign all tiles randomly for a new game.
	 */
	public void restart() {
		MinesweeperUI ui;
		_tiles = new Tile[Variables.gridSize()[1]][Variables.gridSize()[0]];
		for (int i = 0; i < _tiles[0].length; i++) {
			for (int j = 0; j < _tiles.length; j++) {
				_tiles[j][i] = new Tile(false, 0, new Index(j, i));
			}
		}
		assignLoop: for (int i = 0; i < Variables.numberOfMines(); i++) {
			// assignLoop - Loop to assign mines to the board.
			int x = _random.nextInt(Variables.gridSize()[1]); // Pick random
																// positions.
			int y = _random.nextInt(Variables.gridSize()[0]);
			if (_tiles[x][y].isMine() || (hasInitialIndex() && _tiles[x][y].getIndex().equals(_initialIndex))) {
				i--; // Don't assign a mine to the first tile clicked.
				continue assignLoop;
			}
			_tiles[x][y] = new Tile(true, 0, new Index(x, y));
			for (int iX = x - 1; iX <= x + 1; iX++) { // Raise surrounding tiles
														// number of surrounding
														// mines by 1.
				for (int iY = y - 1; iY <= y + 1; iY++) {
					try {
						if (!_tiles[iX][iY].isMine()) {
							int numOfMines = _tiles[iX][iY].minesSurrounding();
							_tiles[iX][iY] = new Tile(false, ++numOfMines, new Index(iX, iY));
						}
					} catch (ArrayIndexOutOfBoundsException e) {
						// If tile is on edge/corner, don't try to change number
						// of tiles that would exist outside the panel.
					}
				}
			}
		}
		for (Tile[] i : _tiles) {
			for (Tile j : i) {
				// To start off, make all tiles look untouched.
				j.setBackground(Variables.UNTOUCHED_COLOR);
			}
		}
		if (_ranOnce) {
			// This should not run on the first go, as the UI has not been
			// generated.
			ui = MinesweeperUI.getInstance();
			ui.setSize(Variables.frameSize());
			ui.setVisible(true);
			ui.setLocationRelativeTo(null); // Re-center
		}
		if (hasInitialIndex()) {
			// If you are resetting the board based on the first click, re-click
			// the initial index.
			_tiles[_initialIndex.getX()][_initialIndex.getY()].getMouseListeners()[0].mouseReleased(_mouseEvent);
			MinesweeperUI.getInstance().reset();
		}
		_ranOnce = true;
	}

	/**
	 * Essentially a recursively called method. If you click a blank tile, it
	 * should click all the surrounding tiles.
	 * 
	 * @param i
	 *            Index of the blank tile clicked.
	 */
	public void blankClicks(Index i) {
		for (int iX = i.getX() - 1; iX <= i.getX() + 1; iX++) {
			for (int iY = i.getY() - 1; iY <= i.getY() + 1; iY++) {
				if (iX == i.getX() && iY == i.getY()) {
					// Don't keep calling the same blank tile.
					continue;
				}
				try {
					_tiles[iX][iY].getMouseListeners()[0].mouseReleased(_mouseEvent);
				} catch (ArrayIndexOutOfBoundsException e1) {
					// When blank tile is on edge/corner, will ignore tiles that
					// would be outside panel.
				}
			}
		}

	}

	/**
	 * Create the panel to use in the Minesweeper UI.
	 * 
	 * @return The contents- A JPanel.
	 */
	public JPanel getPanel() {
		JPanel ret = new JPanel(); // Overall panel- to return.
		JPanel sub = new JPanel(); // Main panel with tiles.
		sub.setLayout(new GridLayout(Variables.gridSize()[1], Variables.gridSize()[0], Variables.GRID_BUFFER,
				Variables.GRID_BUFFER));
		for (int i = 0; i < _tiles.length; i++) {
			for (int j = 0; j < _tiles[i].length; j++) {
				sub.add(_tiles[i][j]);
			}
		}
		ret.setLayout(new BorderLayout());
		ret.add(sub, BorderLayout.CENTER);
		// Add a label to the bottom to show how many mines the user has
		// protected.
		_numOfMinesLeftLabel.setText(Variables.minesLeftLabelText(getNumberOfMinesProtected()));
		ret.add(_numOfMinesLeftLabel, Variables.MINES_LEFT_LABEL_POSITION);
		return ret;
	}

	/**
	 * Run any time a game has completed.
	 * 
	 * @param messageText
	 *            A string of text to show the user after they have completed
	 *            the game. IE: win/lose String.
	 * @param mineColor
	 *            Color that mines should become after the game is over | Note-
	 *            if you pick java.awt.Color.RED and the mine is currently
	 *            java.awt.Color.BLUE, then the mine will become a mix of the
	 *            two colors (new Color(255, 0, 255)).
	 */
	public void gameOver(String messageText, Color mineColor) {
		_initialIndex = null;
		Variables.update();
		for (int i = 0; i < _tiles.length; i++) { // Set most mines to
													// mineColor.
			for (Tile j : _tiles[i]) {
				if (j.isMine()) {
					j.setBackground((j.getBackground().equals(Color.BLUE) && mineColor.equals(Color.RED))
							? Variables.LOSE_MINE_COLOR_CORRECT : mineColor);
				}
			}
		}
		JOptionPane.showMessageDialog(null, new JLabel(messageText, SwingConstants.CENTER), Variables.TITLE,
				JOptionPane.PLAIN_MESSAGE, null);
		int answer = -1;
		while (answer < 0) { // See if the user wants to play again.
			answer = JOptionPane.showOptionDialog(null, new JLabel(Variables.PLAY_AGAIN_MESSAGE, SwingConstants.CENTER),
					Variables.TITLE, JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, -1);
		}
		MinesweeperUI ui = MinesweeperUI.getInstance();
		if (answer == 1) { // Thank user for playing.
			ui.setVisible(false);
			JOptionPane.showMessageDialog(null, new JLabel(Variables.LEAVE_MESSAGE, SwingConstants.CENTER),
					Variables.TITLE, JOptionPane.PLAIN_MESSAGE, null);
			System.exit(0);
		}
		// Otherwise, restart the game.
		restart();
		ui.reset();
	}

	/**
	 * Run when someone clicks the restart option on the JMenuBar.
	 */
	public void reDoGame() {
		_initialIndex = null;
		Variables.update();
		MinesweeperUI ui = MinesweeperUI.getInstance();
		restart();
		ui.reset();
	}

	/**
	 * After each click, this method is ran to see whether the user has won.
	 */
	public void checkForWinning() {
		for (int i = 0; i < _tiles.length; i++) {
			for (int j = 0; j < _tiles[i].length; j++) {
				if (!_tiles[i][j].isMine() && !_tiles[i][j].isClicked()) {
					return;
				}
			}
		}
		gameOver(Variables.WIN_MESSAGE, Variables.WIN_MINE_COLOR);
	}

	/**
	 * Used for the 'Mines Left' label at the bottom of the UI.
	 * 
	 * @return An int- the number of mines that have been right/shift-clicked.
	 */
	public int getNumberOfMinesProtected() {
		int ret = 0;
		for (int i = 0; i < _tiles.length; i++) {
			for (Tile j : _tiles[i]) {
				if (j.isProtected()) {
					ret++;
				}
			}
		}
		return ret;
	}

}
