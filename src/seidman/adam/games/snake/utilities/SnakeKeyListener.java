package seidman.adam.games.snake.utilities;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import seidman.adam.games.snake.SnakeUI;
import seidman.adam.games.snake.logic.SnakeGame;
import seidman.adam.games.utilities.Direction;
import seidman.adam.games.utilities.timing.TimedTask;

public class SnakeKeyListener implements KeyListener {

	private boolean _horizontal;
	private boolean _vertical;
	private Direction _currentDirection;
	private TimedTask _updateTask;

	public SnakeKeyListener(TimedTask updaterTask) {
		_updateTask = updaterTask;
		_horizontal = true;
		_vertical = false;
		_currentDirection = Direction.RIGHT;
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		if ((!(isVertical(e) || isHorizontal(e))) || (isVertical(e) && _vertical) || (isHorizontal(e) && _horizontal)) {
			if (!(e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_P)) {
				return;
			}
		}
		_updateTask.stop();
		switch (e.getKeyCode()) {
		case KeyEvent.VK_P:
			SnakeUI.getInstance().displayPauseMenu(true);
			return;
		case KeyEvent.VK_SPACE:
			SnakeUI.getInstance().displayPauseMenu(false);
			break;
		case KeyEvent.VK_RIGHT:
			_currentDirection = Direction.RIGHT;
			setHorizontality(true);
			break;
		case KeyEvent.VK_LEFT:
			_currentDirection = Direction.LEFT;
			setHorizontality(true);
			break;
		case KeyEvent.VK_UP:
			_currentDirection = Direction.UP;
			setHorizontality(false);
			break;
		default:
			_currentDirection = Direction.DOWN;
			setHorizontality(false);
			break;
		}
		SnakeGame.getInstance().update();
		_updateTask.start();
	}

	private void setHorizontality(boolean horizontal) {
		_horizontal = horizontal;
		_vertical = !horizontal;
	}

	public void keyReleased(KeyEvent e) {
	}

	private boolean isHorizontal(KeyEvent e) {
		return e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT;
	}

	private boolean isVertical(KeyEvent e) {
		return e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN;
	}

	public Direction getDirection() {
		return _currentDirection;
	}

	public boolean isDirection(Direction d) {
		return _currentDirection.equals(d);
	}

	public void reset() {
		_currentDirection = Direction.EAST;
		setHorizontality(true);
	}

}
