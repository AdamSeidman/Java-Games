package seidman.adam.games.utilities;

/**
 * 
 * Simple utility for storing coordinates on a grid.
 * 
 * @author Adam Seidman
 *
 */
public final class Index {

	// The coordinates.
	private int _x;
	private int _y;

	/**
	 * Enter the coordinates of the index.
	 * 
	 * @param x
	 *            X-Coordinate
	 * @param y
	 *            Y-Coordinate
	 */
	public Index(int x, int y) {
		_x = x;
		_y = y;
	}

	public Index(Index i) {
		_x = i.getX();
		_y = i.getY();
	}

	/**
	 * Check the coordinates in .equals() to see if they match.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Index) {
			Index i = (Index) obj;
			return i._x == _x && i._y == _y;
		}
		return super.equals(obj);
	}

	public int getX() {
		return _x;
	}

	public int getY() {
		return _y;
	}

	public void set(int x, int y) {
		_x = x;
		_y = y;
	}

	public void setX(int x) {
		_x = x;
	}

	public void setY(int y) {
		_y = y;
	}

}
