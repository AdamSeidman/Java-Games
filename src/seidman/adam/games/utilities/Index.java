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
	public int x;
	public int y;

	/**
	 * Enter the coordinates of the index.
	 * 
	 * @param x
	 *            X-Coordinate
	 * @param y
	 *            Y-Coordinate
	 */
	public Index(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Check the coordinates in .equals() to see if they match.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Index) {
			Index i = (Index) obj;
			return i.x == this.x && i.y == this.y;
		}
		return super.equals(obj);
	}

}
