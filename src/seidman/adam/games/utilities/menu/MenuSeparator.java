package seidman.adam.games.utilities.menu;

import javax.swing.JMenu;

/**
 * 
 * A menu separator for javax.swing.JMenuBar's.
 * 
 * @author Adam Seidman
 *
 */
public final class MenuSeparator extends JMenu {

	private static final long serialVersionUID = 1L;

	/**
	 * '|' character grayed out on a javax.swing.JMenuBar
	 */
	public MenuSeparator() {
		super("|");
		this.setEnabled(false);
	}
}