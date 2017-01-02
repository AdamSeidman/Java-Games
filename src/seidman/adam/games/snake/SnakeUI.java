package seidman.adam.games.snake;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import seidman.adam.games.UI;
import seidman.adam.games.snake.logic.Game;
import seidman.adam.games.snake.utilities.Variables;
import seidman.adam.games.utilities.WindowLeavingAdapter;

public class SnakeUI extends JFrame implements UI {

	private static final long serialVersionUID = 1L;
	private static SnakeUI _instance;

	private Game _game;

	private SnakeUI() {
		super(Variables.TITLE);
		this.addWindowListener(new WindowLeavingAdapter(Variables.TITLE, Variables.QUIT_MESSAGE) {
			public void windowClosing() {
				SnakeUI.getInstance().setVisible(false);
				JOptionPane.showMessageDialog(null, new JLabel(Variables.LEAVE_MESSAGE, SwingConstants.CENTER),
						Variables.TITLE, JOptionPane.PLAIN_MESSAGE, null);
			}
		});
		_game = Game.getInstance();
		this.setContentPane(_game);
		this.addKeyListener(_game.getSnakeKeyListener());
		this.setSize(Variables.SCREEN_SIZE);
		this.setResizable(Variables.RESIZABLE);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setLocationRelativeTo(null);
		_game.getTimedTask().start();
	}

	public static SnakeUI getInstance() {
		if (_instance == null) {
			_instance = new SnakeUI();
		}
		return _instance;
	}

	public void runUI() {
		SnakeUI.getInstance().setVisible(true);
	}

	public static void main(String[] args) {
		SnakeUI.getInstance().runUI();
	}

}
