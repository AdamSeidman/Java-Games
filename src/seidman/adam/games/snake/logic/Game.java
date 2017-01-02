package seidman.adam.games.snake.logic;

import java.awt.GridLayout;

import javax.swing.JPanel;

import seidman.adam.games.snake.SnakeUI;
import seidman.adam.games.snake.utilities.SnakeKeyListener;
import seidman.adam.games.snake.utilities.Variables;
import seidman.adam.games.utilities.Direction;
import seidman.adam.games.utilities.Index;
import seidman.adam.games.utilities.Utilities;
import seidman.adam.games.utilities.timing.TimedTask;

public class Game extends JPanel {

	private static final long serialVersionUID = 1L;

	private static Game _instance;

	private Index _currentIndex;
	private Square[][] _squares;
	private SnakeKeyListener _keyListener;
	private int _snakeSize;
	private SnakeUI _ui;
	private boolean _firstTime;
	private TimedTask _task;

	private Game() {
		_firstTime = true;
		_task = new TimedTask(Variables.UPDATE_DELAY) {
			public void task() {
				Game.getInstance().update();
			}
		};
		_squares = new Square[Variables.NUM_TILES_WIDE][Variables.NUM_TILES_HIGH];
		this.setLayout(new GridLayout(Variables.NUM_TILES_HIGH, Variables.NUM_TILES_WIDE, Variables.GRID_BUFFER,
				Variables.GRID_BUFFER));
		_snakeSize = Variables.STARTING_SIZE;
		_currentIndex = Variables.STARTING_INDEX;
		_keyListener = new SnakeKeyListener(_task);
		restart();
	}

	public static Game getInstance() {
		if (_instance == null) {
			_instance = new Game();
		}
		return _instance;
	}

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

	public void update() {
		try {
			if (_keyListener.isDirection(Direction.UP)) {
				_currentIndex.y--;
			} else if (_keyListener.isDirection(Direction.DOWN)) {
				_currentIndex.y++;
			} else if (_keyListener.isDirection(Direction.LEFT)) {
				_currentIndex.x--;
			} else {
				_currentIndex.x++;
			}
			_squares[_currentIndex.x][_currentIndex.y].set(_snakeSize + 1);
			for (Square[] i : _squares) {
				for (Square j : i) {
					j.update();
				}
			}
			if (_ui == null) {
				_ui = SnakeUI.getInstance();
			}
			_ui.revalidate();
			_ui.repaint();
		} catch (ArrayIndexOutOfBoundsException e) {
			notify(false);
		}
	}

	public void restart() {
		if (!_firstTime) {
			_ui = SnakeUI.getInstance();
			_ui.setVisible(false);
		}
		this.removeAll();
		_currentIndex = Variables.STARTING_INDEX;
		for (int i = 0; i < _squares.length; i++) {
			for (int j = 0; j < _squares[i].length; j++) {
				_squares[i][j] = new Square(0);
			}
		}
		for (int i = 0; i < Variables.STARTING_SIZE; i++) {
			_squares[Variables.STARTING_INDEX.x - i][Variables.STARTING_INDEX.y] = new Square(
					Variables.STARTING_SIZE - i);
		}
		for (int i = 0; i < _squares[0].length; i++) {
			for (int j = 0; j < _squares.length; j++) {
				this.add(_squares[j][i]);
			}
		}
		if (!_firstTime) {
			_ui.setVisible(true);
			_ui.revalidate();
			_ui.repaint();
		} else {
			_firstTime = false;
		}
	}

	public SnakeKeyListener getSnakeKeyListener() {
		return _keyListener;
	}

	public TimedTask getTimedTask() {
		return _task;
	}

}
