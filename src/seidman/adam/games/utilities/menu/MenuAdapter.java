package seidman.adam.games.utilities.menu;

import javax.swing.JMenu;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

/**
 * 
 * An adapter for javax.swing.event.MenuListener for menu convenience.
 * 
 * @author Adam Seidman
 *
 */
public abstract class MenuAdapter implements MenuListener {

	private final JMenu MENU;

	/**
	 * Create a MenuAdapter
	 * 
	 * @param menu
	 *            The menu for which this MenuAdapter is for.
	 */
	public MenuAdapter(JMenu menu) {
		MENU = menu;
	}

	/**
	 * Removes menuCanceled() method from things to implement.
	 */
	public void menuCanceled(MenuEvent e) {
	}

	/**
	 * Removes menuDeselected() method from things to implement.
	 */
	public void menuDeselected(MenuEvent e) {
	}

	/**
	 * Will use click method and then unclick the menu passed through the
	 * constructor.
	 */
	public void menuSelected(MenuEvent e) {
		menuClicked();
		MENU.setSelected(false);
	}

	/**
	 * User will implement this method. This is what happens when the menu is
	 * clicked.
	 */
	public abstract void menuClicked();
	
}
