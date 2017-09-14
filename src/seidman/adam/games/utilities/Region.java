package seidman.adam.games.utilities;

public class Region {

	private int _height;
	private int _width;
	private int _x;
	private int _y;

	public Region(int x, int y, int width, int height) {
		this._x = x;
		this._y = y;
		this._width = width;
		this._height = height;
	}

	public boolean inBounds(int x, int y) {
		return x >= this._x && x <= this._x + this._width && y >= this._y && y <= this._y + this._height;
	}

}
