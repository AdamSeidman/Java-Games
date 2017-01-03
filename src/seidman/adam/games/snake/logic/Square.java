package seidman.adam.games.snake.logic;

import javax.swing.JPanel;

import seidman.adam.games.snake.utilities.Variables;

/**
 * 
 * One square for a grid of a Snake game.
 * 
 * @author Adam Seidman
 *
 */
public class Square extends JPanel {

	private static final long serialVersionUID = 1L;

	private int _updatesLeft;

	/**
	 * Create a new square for a Snake screen.
	 * 
	 * @param updatesLeft
	 *            An int- How many update() calls should this square remain a
	 *            snake square for?
	 */
	public Square(int updatesLeft) {
		_updatesLeft = ++updatesLeft;
		update();
	}

	/**
	 * Upon dot consumption, extend the life of a snake square by one update()
	 * method call.
	 */
	public void extend() {
		if (_updatesLeft != 0) {
			_updatesLeft++;
		}
	}

	/**
	 * Update the background color and _updatesLeft int of this Square.
	 */
	public void update() {
		if (_updatesLeft-- == 0) {
			_updatesLeft++;
		} else if (_updatesLeft == Variables.DOT_UPDATE_CONSTANT - 1) {
			_updatesLeft++;
		}
		this.setBackground(_updatesLeft > 0 ? Variables.SNAKE_AND_DOT_COLOR : Variables.BACKGROUND_COLOR);
	}

	/**
	 * When the snake travels into this Square, set the updatesLeft and switch
	 * the Color.
	 * 
	 * @param snakeSize
	 *            An integer- the current Snake size.
	 */
	public void set(int snakeSize) {
		if (_updatesLeft > 0 && _updatesLeft != Variables.DOT_UPDATE_CONSTANT) {
			SnakeGame.getInstance().notify(snakeSize == Variables.NUM_TILES_HIGH * Variables.NUM_TILES_WIDE);
		} else if (_updatesLeft == Variables.DOT_UPDATE_CONSTANT) {
			SnakeGame.getInstance().extendAll();
			snakeSize++;
		}
		_updatesLeft = ++snakeSize;
		update();
	}

	/**
	 * Get the current number of update() method calls this Square will remain
	 * Color.BLACK
	 * 
	 * @return An integer- updatesLeft
	 */
	public int getUpdatesLeft() {
		return _updatesLeft;
	}

}
