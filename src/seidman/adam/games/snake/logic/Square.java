package seidman.adam.games.snake.logic;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

import seidman.adam.games.snake.utilities.Variables;

public class Square extends JPanel {

	private static final long serialVersionUID = 1L;

	private int _updatesLeft;

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
		if (_updatesLeft-- == 0) {
			_updatesLeft++;
		} else if (_updatesLeft == Variables.DOT_UPDATE_CONSTANT - 1) {
			_updatesLeft++;
		}
		this.setBackground(_updatesLeft > 0 ? Variables.SNAKE_AND_DOT_COLOR : Variables.BACKGROUND_COLOR);
	}

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

	public int getUpdatesLeft() {
		return _updatesLeft;
	}

}
