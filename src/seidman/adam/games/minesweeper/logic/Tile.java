package seidman.adam.games.minesweeper.logic;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import seidman.adam.games.minesweeper.utilities.TileClickListener;
import seidman.adam.games.minesweeper.utilities.Variables;
import seidman.adam.games.utilities.Index;

/**
 * 
 * A single tile in Minesweeper. Can be a mine, a number, or blank.
 * 
 * @author Adam Seidman
 *
 */
public final class Tile extends JPanel {

	private static final long serialVersionUID = 1L;

	private final Index INDEX;
	private final boolean IS_MINE;
	private final int MINES_SURROUNDING;

	private boolean _clicked;
	private boolean _protected;

	/**
	 * Create a new Tile.
	 * 
	 * @param isMine
	 *            True, if tile is a mine.
	 * @param minesSurrounding
	 *            Int- number of mines surrounding this tile.
	 * @param i
	 *            seidman.adam.games.minesweeper.utilities.Index of the Tile in the
	 *            Game's JPanel
	 */
	public Tile(boolean isMine, int minesSurrounding, Index i) {
		IS_MINE = isMine;
		MINES_SURROUNDING = minesSurrounding;
		INDEX = i;
		this.addMouseListener(new TileClickListener(this));
		this.setBorder(BorderFactory.createLineBorder(Variables.BORDER_COLOR));
		_protected = false;
		_clicked = false;
	}

	/**
	 * Set the status of this tile to clicked. (Can only be done once.)
	 */
	public void click() {
		_clicked = true;
	}

	/**
	 * Get coordinates of tile.
	 * 
	 * @return seidman.adam.games.minesweeper.utilities.Index
	 */
	public Index getIndex() {
		return INDEX;
	}

	/**
	 * @return True, if has been clicked.
	 */
	public boolean isClicked() {
		return _clicked;
	}

	/**
	 * @return True, if mine.
	 */
	public boolean isMine() {
		return IS_MINE;
	}

	/**
	 * @return True, if right/shift-clicked.
	 */
	public boolean isProtected() {
		return _protected;
	}

	/**
	 * @return An int- number of mines surrounding.
	 */
	public int minesSurrounding() {
		return MINES_SURROUNDING;
	}

	/**
	 * Tell the board whether you would like to protect this tile or not.
	 * 
	 * @param isProtected
	 *            A boolean- the new status of the tile.
	 */
	public void setProtectedness(boolean isProtected) {
		_protected = isProtected;
	}

}
