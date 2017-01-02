package seidman.adam.games.utilities;

/**
 * 
 * Directions for many purposes.
 * 
 * @author Adam Seidman
 *
 */
public final class Direction {

	// List of many possible Directions that can be used.
	public static final Direction NORTH = new Direction(0);
	public static final Direction UP = new Direction(0);
	public static final Direction RIGHT = new Direction(1);
	public static final Direction EAST = new Direction(1);
	public static final Direction SOUTH = new Direction(2);
	public static final Direction DOWN = new Direction(2);
	public static final Direction WEST = new Direction(3);
	public static final Direction LEFT = new Direction(3);
	public static final Direction NONE = new Direction(4);
	public static final Direction OTHER = new Direction(5);

	private int direction;

	// Private so no developer can make their own directions.
	private Direction(int n) {
		direction = n;
	}

	/**
	 * @return Integer value of this Direction.
	 */
	public int toInt() {
		return direction;
	}

	@Override
	/**
	 * Overriding method to check actual integer values if comparing two
	 * Directions.
	 */
	public boolean equals(Object obj) {
		if (obj instanceof Direction) {
			return ((Direction) obj).toInt() == direction;
		}
		return super.equals(obj);
	}

}
