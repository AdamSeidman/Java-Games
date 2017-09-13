package seidman.adam.games;

import java.awt.Dimension;

import seidman.adam.games.minesweeper.MinesweeperUI;
import seidman.adam.games.snake.SnakeUI;

public abstract class Constants {

	static final Dimension FRAME_SIZE = new Dimension(300, 200);
	static final boolean RESIZABLE = false;
	static final String TITLE = "Adam J Seidman's Game Database";
	static final Playable[] UI_LIST = new Playable[] { MinesweeperUI.getInstance(), SnakeUI.getInstance() };

}
