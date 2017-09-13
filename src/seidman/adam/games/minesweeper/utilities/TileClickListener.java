package seidman.adam.games.minesweeper.utilities;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import seidman.adam.games.minesweeper.MinesweeperUI;
import seidman.adam.games.minesweeper.logic.MinesweeperGame;
import seidman.adam.games.minesweeper.logic.Tile;

/**
 * 
 * Special Mouse Listener for listening to when
 * seidman.adam.games.minesweeper.logic.Tile's are clicked.
 * 
 * @author Adam Seidman
 *
 */
public final class TileClickListener implements MouseListener {

	private final Tile TILE;

	/**
	 * Create a TileClickListener
	 * 
	 * @param t
	 *            Tile for which the listener is for.
	 */
	public TileClickListener(Tile t) {
		TILE = t;
	}

	/**
	 * Check to see if a Mouse Click is a right/shift-click.
	 * 
	 * @param e
	 *            MouseEvent of the mouse-click you are checking.
	 * @return True, if right/shift-click.
	 */
	public boolean isProtectClick(MouseEvent e) {
		return SwingUtilities.isRightMouseButton(e) || e.isShiftDown();
	}
	
	/**
	 * Needed to implement. Do nothing on mouse click.
	 */
	public void mouseClicked(MouseEvent e) {
	}

	/**
	 * Needed to implement. Do nothing on mouse press.
	 */
	public void mousePressed(MouseEvent e) {
	}

	/**
	 * Needed to implement. Do nothing on mouse entering.
	 */
	public void mouseEntered(MouseEvent e) {
	}

	/**
	 * Needed to implement. Do nothing on mouse exiting.
	 */
	public void mouseExited(MouseEvent e) {
	}

	/**
	 * On mouse release over a tile, perform the correct action for the tile.
	 */
	public void mouseReleased(MouseEvent e) {
		MinesweeperGame g = MinesweeperGame.getInstance();
		MinesweeperGame._mouseEvent = e;
		if (!MinesweeperGame.hasInitialIndex()) {
			// If this is first click on the board, reset the board.
			MinesweeperGame.sendInitialIndex(TILE.getIndex());
		} else if (TILE.isClicked() || (TILE.isProtected() && !isProtectClick(e))) {
			// Ignore left-click of tile that is protected.
			return;
		} else if (isProtectClick(e) && !TILE.isClicked()) {
			// If tile is not already clicked, and is right/shift-clicked,
			// un/protect a tile.
			TILE.setProtectedness(!TILE.isProtected());
			TILE.setBackground(TILE.isProtected() ? Variables.PROTECTED_TILE_COLOR : Variables.UNTOUCHED_COLOR);
		} else if (TILE.isMine()) {
			// If left-clicked on a mine, end the game.
			g.runGameOverSequence(Variables.LOSE_MESSAGE, Variables.LOSE_MINE_COLOR);
		} else if (TILE.minesSurrounding() == 0) {
			// If tile is blank, run recursive
			// seidman.adam.games.minesweeper.logic.Game.blankClicks() method.
			TILE.click();
			g.callBlankClicks(TILE.getIndex());
			TILE.setBorder(BorderFactory.createEmptyBorder());
			TILE.setBackground(Variables.TOUCHED_COLOR);
		} else {
			// Otherwise, add number and left-click like normal.
			TILE.add(new JLabel(Integer.toString(TILE.minesSurrounding()), SwingConstants.CENTER));
			TILE.setBackground(Variables.TOUCHED_COLOR);
			MinesweeperUI ui = MinesweeperUI.getInstance();
			ui.revalidate();
			ui.repaint();
			TILE.click();
		}
		// Now recheck the text of the 'Mines Left' label and check to see if
		// the user has won.
		g._numOfMinesLeftLabel.setText(Variables.minesLeftLabelText(g.getNumberOfMinesProtected()));
		g.checkForWinning();
	}

}
