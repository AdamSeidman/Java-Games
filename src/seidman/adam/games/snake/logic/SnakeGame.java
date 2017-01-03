package seidman.adam.games.snake.logic;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.Random;

import javax.swing.JPanel;

import seidman.adam.games.snake.SnakeUI;
import seidman.adam.games.snake.utilities.SnakeKeyListener;
import seidman.adam.games.snake.utilities.Variables;
import seidman.adam.games.utilities.Direction;
import seidman.adam.games.utilities.Index;
import seidman.adam.games.utilities.Utilities;
import seidman.adam.games.utilities.timing.TimedTask;

/**
 * 
 * The logic for a Snake game.
 * 
 * @author Adam Seidman
 *
 */
public class SnakeGame extends JPanel {

	private static final long serialVersionUID = 1L;

	private static SnakeGame _instance;

	private Index _currentIndex;
	private Square[][] _squares;
	private SnakeKeyListener _keyListener;
	private int _snakeSize;
	private SnakeUI _ui;
	private boolean _firstTime;
	private TimedTask _task;
	private Random _random;
	private int _points;

	/**
	 * Create a new Snake Game.
	 */
	private SnakeGame() {
		_firstTime = true; // Used for not trying to make visible before it
							// appears on-screen.
		_random = new Random();
		_task = new TimedTask(Variables.UPDATE_DELAY) {
			public void task() { // Update at a specific delay.
				SnakeGame.getInstance().update();
			}
		};
		this.setLayout(new GridLayout(Variables.NUM_TILES_HIGH, Variables.NUM_TILES_WIDE, Variables.GRID_BUFFER,
				Variables.GRID_BUFFER));
		_keyListener = new SnakeKeyListener(_task);
		restart();
	}

	/**
	 * Get the current running instance of SnakeGame.
	 * 
	 * @return SnakeGame- the current one.
	 */
	public static SnakeGame getInstance() {
		if (_instance == null) {
			_instance = new SnakeGame();
		}
		return _instance;
	}

	/**
	 * Let SnakeGame know if the game should end.
	 * 
	 * @param won
	 *            A boolean- True: win. False: lost.
	 */
	public void notify(boolean won) {
		_task.stop();
		if (Utilities.getYesOrNo(Variables.TITLE, Variables.PLAY_AGAIN_MESSAGE) == 0) {
			restart();
			_task.start();
		} else {
			_ui.setVisible(false);
			Utilities.showMessage(Variables.TITLE, Variables.LEAVE_MESSAGE);
			System.exit(0);
		}
	}

	/**
	 * Update all the game logic for each pass of SnakeGame.
	 */
	public void update() {
		try {
			if (_keyListener.isDirection(Direction.UP)) {
				// This if-else section if determine the direction the snake
				// should turn.
				_currentIndex.setY(_currentIndex.getY() - 1);
			} else if (_keyListener.isDirection(Direction.DOWN)) {
				_currentIndex.setY(_currentIndex.getY() + 1);
			} else if (_keyListener.isDirection(Direction.LEFT)) {
				_currentIndex.setX(_currentIndex.getX() - 1);
			} else {
				_currentIndex.setX(_currentIndex.getX() + 1);
			}
			_squares[_currentIndex.getX()][_currentIndex.getY()].set(_snakeSize + 1);
			for (Square[] i : _squares) {
				for (Square j : i) {
					// Update every single square on the screen.
					j.update();
				}
			}

			// Update all visual things.
			if (_ui == null) {
				_ui = SnakeUI.getInstance();
			}
			_ui.revalidate();
			_ui.repaint();
		} catch (ArrayIndexOutOfBoundsException e) {
			notify(false);
		}
	}

	/**
	 * Every time you start a game, this method rewrites all the logic for
	 * SnakeGame.
	 */
	public void restart() {
		_points = 0;
		if (!_firstTime) {
			_ui = SnakeUI.getInstance();
			_ui.setVisible(false);
		}
		this.removeAll(); // Resets visual things.
		_snakeSize = Variables.STARTING_SIZE;
		_currentIndex = new Index(Variables.STARTING_INDEX);
		_squares = new Square[Variables.NUM_TILES_WIDE][Variables.NUM_TILES_HIGH];
		for (int i = 0; i < _squares.length; i++) {
			for (int j = 0; j < _squares[i].length; j++) {
				_squares[i][j] = new Square(0);
			}
		}
		for (int i = 0; i < Variables.STARTING_SIZE; i++) { // Set the initial
															// snake.
			_squares[Variables.STARTING_INDEX.getX() - i][Variables.STARTING_INDEX.getY()] = new Square(
					Variables.STARTING_SIZE - i);
		}
		for (int i = 0; i < _squares[0].length; i++) {
			for (int j = 0; j < _squares.length; j++) {
				this.add(_squares[j][i]);
			}
		}
		if (!_firstTime) {
			// Only run the visual setup while the JFrame is actually on the
			// screen.
			_ui.setVisible(true);
			_ui.revalidate();
			_ui.repaint();
		} else {
			_firstTime = false;
		}
		_keyListener.reset();
		addRandomDot(); // Add first dot to the screen.
	}

	/**
	 * Get the current Keyboard listener for the JFrame.
	 * 
	 * @return The currently implemented
	 *         seidman.adam.games.snake.utilities.SnakeKeyListener.
	 */
	public SnakeKeyListener getSnakeKeyListener() {
		return _keyListener;
	}

	/**
	 * Get the current update seidman.adam.games.utilities.timing.TimedTask
	 * 
	 * @return A TimedTask, used for update logic in the JFrame.
	 */
	public TimedTask getTimedTask() {
		return _task;
	}

	/**
	 * Overrides the normal paintComponent() method. Paints the background, and
	 * in-between rectangles of the snake Squares.
	 */
	public void paintComponent(Graphics g) {
		g.setColor(Variables.BACKGROUND_COLOR);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(Variables.SNAKE_AND_DOT_COLOR);
		for (int x = 0; x < _squares.length; x++) {
			for (int y = 0; y < _squares[x].length; y++) {
				for (int i = -1; i <= 1; i++) {
					for (int j = -1; j <= 1; j++) {
						try {
							if (_squares[x][y].getUpdatesLeft() == 0 || _squares[x + i][y + j].getUpdatesLeft() == 0
									|| i == j || !(Math.abs(_squares[x][y].getUpdatesLeft()
											- _squares[x + i][y + j].getUpdatesLeft()) == 1)) {
								// Determine whether or not to actually draw
								// connecting rectangles.
								continue;
							}

							// Draw a connecting rectangle.
							g.fillRect(Math.min(_squares[x][y].getX(), _squares[x + i][y + j].getX()),
									Math.min(_squares[x][y].getY(), _squares[x + i][y + j].getY()),
									((int) _squares[x][y].getSize().getWidth()) + (j == 0 ? Variables.GRID_BUFFER : 0),
									((int) _squares[x][y].getSize().getHeight())
											+ (i == 0 ? Variables.GRID_BUFFER : 0));
						} catch (ArrayIndexOutOfBoundsException e) {
							// Do nothing- This is just if it picks a tile that
							// isn't on the screen.
						}
					}
				}

			}
		}
	}

	/**
	 * When a random dot is consumed, extend the snake and make each current
	 * Square last for one more update() call.
	 */
	public void extendAll() {
		_snakeSize++;
		for (Square[] i : _squares) {
			for (Square j : i) {
				j.extend();
			}
		}
		addRandomDot(); // Add another random dot.
		_points++;
		// Now update the title.
		SnakeUI.getInstance().setTitle(Variables.TITLE + Variables.POINTS_PHRASE + Integer.toString(_points));
	}

	/**
	 * Picks a random unoccupied spot for a dot on the screen and places one
	 * there.
	 */
	public void addRandomDot() {
		Square sq = new Square(0);
		do { // Assign random position for dot.
			sq = _squares[_random.nextInt(Variables.NUM_TILES_WIDE)][_random.nextInt(Variables.NUM_TILES_HIGH)];
		} while (sq.getUpdatesLeft() > 0);
		sq.set(Variables.DOT_UPDATE_CONSTANT);
		sq.update();
	}

}
