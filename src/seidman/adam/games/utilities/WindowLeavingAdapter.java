package seidman.adam.games.utilities;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

/**
 * 
 * An adapter for javax.swing.event.WindowListener for window closing
 * convenience.
 * 
 * @author Adam Seidman
 *
 */
public abstract class WindowLeavingAdapter implements WindowListener {

	private final String TITLE;
	private final String QUIT_MESSAGE;

	/**
	 * Create a WindowLeavingAdapter
	 * 
	 * @param title
	 *            A String- title for confirmation JOptionPane.
	 * @param quitMessage
	 *            A String- messafe for confirmation JOptionPane.
	 */
	public WindowLeavingAdapter(String title, String quitMessage) {
		this.TITLE = title;
		this.QUIT_MESSAGE = quitMessage;
	}

	
	/**
	 * Removes windowActivated() method from things to implement.
	 */
	public void windowActivated(WindowEvent e) {
	}

	
	/**
	 * Removes windowClosed() method from things to implement.
	 */
	public void windowClosed(WindowEvent e) {
	}

	
	/**
	 * Will confirm if the user actually wants to leave. If they do, will run
	 * windowClosing().	
	 */
	public final void windowClosing(WindowEvent e) {
		int answer = -1;
		while (answer < 0) {
			// Confirm if leaving.
			answer = JOptionPane.showOptionDialog(null, new JLabel(QUIT_MESSAGE, SwingConstants.CENTER), TITLE,
					JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, -1);
		}
		if (answer == 0) {
			// Perform user's set action before leaving.
			windowClosing();
			System.exit(0);
		}
	}

	
	/**
	 * Removes windowDeactivated() method from things to implement.
	 */
	public void windowDeactivated(WindowEvent e) {
	}

	
	/**
	 * Removes windowDeiconified() method from things to implement.
	 */
	public void windowDeiconified(WindowEvent e) {
	}

	
	/**
	 * Removes windowIconified() method from things to implement.
	 */
	public void windowIconified(WindowEvent e) {
	}

	
	/**
	 * Removes windowOpened() method from things to implement.
	 */
	public void windowOpened(WindowEvent e) {
	}

	/**
	 * Method for user to implement. Only runs after user confirms that the game
	 * is over.
	 */
	public abstract void windowClosing();

}
