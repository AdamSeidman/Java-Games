package seidman.adam.games.utilities;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

/**
 * 
 * @author Adam Seidman
 *
 */
public final class Utilities {

	/**
	 * Gets 'Yes' or 'No' input from the user through JOptionPane.
	 * 
	 * @param title
	 *            A String- title of the JOptionPane message.
	 * @param message
	 *            A String- message of the JOptionPane pop-up.
	 * @return An int: 0- Yes, 1- No.
	 */
	public static int getYesOrNo(String title, String message) {
		int a = -1;
		while (a < 0) {
			a = JOptionPane.showOptionDialog(null, new JLabel(message, SwingConstants.CENTER), title,
					JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, -1);

		}
		return a;
	}

	/**
	 * Show a message to the user as a JOptionPane pop-up.
	 * 
	 * @param title
	 *            A String- title of the JOptionPane message.
	 * @param message
	 *            An Object- message of the JOptionPane pop-up.
	 */
	public static void showMessage(String title, Object message) {
		if (message instanceof String) {
			message = new JLabel(((String) message), SwingConstants.CENTER);
		}
		JOptionPane.showMessageDialog(null, message, title, JOptionPane.PLAIN_MESSAGE, null);
	}

}
