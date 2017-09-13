package seidman.adam.games.snake;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import seidman.adam.games.Playable;
import seidman.adam.games.snake.logic.SnakeGame;
import seidman.adam.games.snake.utilities.Variables;
import seidman.adam.games.utilities.WindowLeavingAdapter;
import seidman.adam.games.utilities.timing.TimedTask;

/**
 * 
 * The user interface for a Snake game.
 * 
 * @author Adam Seidman
 *
 */
public class SnakeUI extends JFrame implements Playable {

	private static final long serialVersionUID = 1L;
	private static SnakeUI _instance;

	private SnakeGame _game;
	private boolean _paused;

	/**
	 * Get the current running instance of SnakeUI.
	 * @return SnakeUI- current user interface.
	 */
	public static SnakeUI getInstance() {
		if (_instance == null) {
			_instance = new SnakeUI();
		}
		return _instance;
	}
	
	/**
	 * Create a new Snake User Interface and set is visible to the user.
	 */
	private SnakeUI() {
		super(Variables.TITLE + Variables.POINTS_PHRASE + Integer.toString(0));
		_paused = false;
		if (Variables.USE_WINDOW_LISTENER) {
			// If this mode is on, the JFrame will ask you before leaving.
			this.addWindowListener(new WindowLeavingAdapter(Variables.TITLE, Variables.QUIT_MESSAGE) {
				public void windowClosing() {
					SnakeUI.getInstance().setVisible(false);
					JOptionPane.showMessageDialog(null, new JLabel(Variables.LEAVE_MESSAGE, SwingConstants.CENTER),
							Variables.TITLE, JOptionPane.PLAIN_MESSAGE, null);
				}
			});
		}
		_game = SnakeGame.getInstance();
		this.setContentPane(_game);
		this.addKeyListener(_game.getSnakeKeyListener());
		this.setSize(Variables.SCREEN_SIZE);
		this.setResizable(Variables.RESIZABLE);
		this.setDefaultCloseOperation(
				Variables.USE_WINDOW_LISTENER ? JFrame.DO_NOTHING_ON_CLOSE : JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		_game.getTimedTask().start();
		new TimedTask(0.1) {
			@Override
			public void task() {
				SnakeUI.getInstance().getKeyListeners()[0].keyPressed(
						// Wait, so graphics show up
						new KeyEvent(SnakeUI.getInstance().getContentPane(), 0, 0, 0, KeyEvent.VK_P, 'P', 0));
			}
		}.schedule();
	}

	/**
	 * Show/hide the pause menu on the screen.
	 * @param show Whether or not you would like to show the pause message.
	 */
	public void displayPauseMenu(boolean show) {
		boolean prevPaused = _paused;
		_paused = show;
		if (_paused && !prevPaused) {
			Graphics g = this.getContentPane().getGraphics();
			g.setColor(Variables.PAUSE_TEXT_COLOR);
			g.setFont(Variables.PAUSE_FONT);
			for (int i = 0; i < Variables.PAUSE_MESSAGE.length; i++) {
				g.drawString(Variables.PAUSE_MESSAGE[i],
						Variables.PAUSE_SCREEN_INDEX.getX() + Variables.PAUSE_MESSAGE_BUFFER_X[i],
						Variables.PAUSE_SCREEN_INDEX.getY() + (Variables.PAUSE_MESSAGE_NEWLINE_BUFFER * i));
			}
		}
	}

	/**
	 * Implemented for Playable interface.
	 */
	public void runUI() {
		SnakeUI.getInstance().setVisible(true);
	}

	public static void main(String[] args) {
		
		SnakeUI.getInstance().runUI();
	}
	
}
