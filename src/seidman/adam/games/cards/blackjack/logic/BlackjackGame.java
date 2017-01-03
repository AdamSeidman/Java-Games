package seidman.adam.games.cards.blackjack.logic;

import javax.swing.JPanel;

/**
 * 
 * Logic for the Blackjack Game.
 * 
 * @author Adam Seidman
 *
 */
public class BlackjackGame extends JPanel {

	private static final long serialVersionUID = 1L;
	private static BlackjackGame _instance;

	/**
	 * Create a BlackjackGame
	 */
	private BlackjackGame() {

	}

	/**
	 * Get the current running instance of the Blackjack Game.
	 * 
	 * @return The current BlackjackGame.
	 */
	public static BlackjackGame getInstance() {
		if (_instance == null) {
			_instance = new BlackjackGame();
		}
		return _instance;
	}

}
