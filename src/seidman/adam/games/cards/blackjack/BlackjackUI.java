package seidman.adam.games.cards.blackjack;

import javax.swing.JFrame;

import seidman.adam.games.Playable;
import seidman.adam.games.cards.blackjack.logic.BlackjackGame;

/**
 * 
 * User interface for the Blackjack Game.
 * 
 * @author Adam Seidman
 *
 */
public class BlackjackUI extends JFrame implements Playable {

	private static final long serialVersionUID = 1L;
	private static BlackjackUI _instance;
	
	private BlackjackGame _game;

	/**
	 * Create a BlackjackUI
	 */
	private BlackjackUI() {
		_game = BlackjackGame.getInstance();
	}

	/**
	 * Get the current running instance of the user interface for the blackjack
	 * game.
	 * 
	 * @return The current BlackjackUI.
	 */
	public static BlackjackUI getInstance() {
		if (_instance == null) {
			_instance = new BlackjackUI();
		}
		return _instance;
	}

	/**
	 * Implemented from seidman.adam.games.Playable so it can be run with other
	 * user interfaces.
	 */
	public void runUI() {
		BlackjackUI.getInstance().setVisible(true);
	}

	public static void main(String[] args) {
		BlackjackUI.getInstance().runUI();
	}

}
