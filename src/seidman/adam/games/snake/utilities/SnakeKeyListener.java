package seidman.adam.games.snake.utilities;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import seidman.adam.games.snake.SnakeUI;
import seidman.adam.games.snake.logic.SnakeGame;
import seidman.adam.games.utilities.Direction;
import seidman.adam.games.utilities.timing.TimedTask;

/**
 * 
 * A specialized java.awt.event.KeyListener for the Snake game.
 * 
 * @author Adam Seidman
 *
 */
public class SnakeKeyListener implements KeyListener {

	private boolean _horizontal;
	private boolean _vertical;
	private Direction _currentDirection;
	private TimedTask _updateTask;

	/**
	 * Create a new SnakeKeyListener
	 * 
	 * @param updaterTask
	 *            The TimedTask that currently updates the SnakeUI. This gives
	 *            the KeyListener access so it can pause it after certain keys
	 *            are pressed.
	 */
	public SnakeKeyListener(TimedTask updaterTask) {
		_updateTask = updaterTask;
		_horizontal = true;
		_vertical = false;
		_currentDirection = Direction.RIGHT;
	}

	/**
	 * Only here because of KeyListener implementation. Unused.
	 */
	public void keyTyped(KeyEvent e) {
	}

	/**
	 * Determine what to do for each key pressed in the Snake game.
	 */
	public void keyPressed(KeyEvent e) {
		if ((!(isVertical(e) || isHorizontal(e))) || (isVertical(e) && _vertical) || (isHorizontal(e) && _horizontal)) {
			if (!(e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_P)) {
				return; // Don't run unless a correct key is pressed.
			}
		}
		_updateTask.stop(); // Temporarily stop the updateTask for logical
							// purposed.
		switch (e.getKeyCode()) {
		case KeyEvent.VK_P:
			SnakeUI.getInstance().displayPauseMenu(true); // Pause
			return;
		case KeyEvent.VK_SPACE:
			SnakeUI.getInstance().displayPauseMenu(false); // Unpause
			break;
		case KeyEvent.VK_RIGHT: // All of these lower cases are turning logic.
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
		default: // Down
			_currentDirection = Direction.DOWN;
			setHorizontality(false);
			break;
		}
		SnakeGame.getInstance().update();
		_updateTask.start(); // Update the SnakeGame and continue with the
								// updateTask.
	}

	/**
	 * Set some local directional booleans in the correct manner.
	 * @param horizontal A boolean to determine the local ones.
	 */
	private void setHorizontality(boolean horizontal) {
		_horizontal = horizontal;
		_vertical = !horizontal;
	}

	/**
	 * Only here because of KeyListener implementation. Unused.
	 */
	public void keyReleased(KeyEvent e) {
	}

	/**
	 * Lets the developer know which kind of direction the snake should go according to a key-being=pressed event.
	 * @param e The KeyEvent from which to check.
	 * @return True, if is going horizontally.
	 */
	private boolean isHorizontal(KeyEvent e) {
		return e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT;
	}

	/**
	 * Lets the developer know which kind of firection the snake should go according to a key-being-pressed event.
	 * @param e The KeyEvent from which to check.
	 * @return True, if going vertically.
	 */
	private boolean isVertical(KeyEvent e) {
		return e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN;
	}

	/**
	 * @return The current seidman.adan.games.utilities.Direction that the snake is going.
	 */
	public Direction getDirection() {
		return _currentDirection;
	}

	/**
	 * Check a specific direction rather than asking for a direction.
	 * @param d A seidman.adam.games.utilities.Direction to compare to the current Direction.
	 * @return True, if the two Directions match.
	 */
	public boolean isDirection(Direction d) {
		return _currentDirection.equals(d);
	}

	/**
	 * Reset local variables back to their original state.
	 */
	public void reset() {
		_currentDirection = Direction.EAST;
		setHorizontality(true);
	}

}
