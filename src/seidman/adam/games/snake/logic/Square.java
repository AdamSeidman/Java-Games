package seidman.adam.games.snake.logic;

import javax.swing.JPanel;

import seidman.adam.games.snake.utilities.Variables;

public class Square extends JPanel {

	private static final long serialVersionUID = 1L;

	public int _updatesLeft; // TODO: make private

	public Square(int updatesLeft) {
		_updatesLeft = ++updatesLeft;
		update();
	}

	public void extend() {
		if (_updatesLeft != 0) {
			_updatesLeft++;
		}
	}

	public void update() {
		if(_updatesLeft-- == 0) {
			_updatesLeft++;
		}
		this.setBackground(_updatesLeft > 0 ? Variables.SNAKE_COLOR : Variables.NOT_SNAKE_COLOR);
	}

	public void set(int snakeSize) {
		if (_updatesLeft > 0) {
			Game.getInstance().notify(snakeSize == Variables.NUM_TILES_HIGH * Variables.NUM_TILES_WIDE);
		}
		_updatesLeft = ++snakeSize;
		update();
	}

}
