package seidman.adam.games.utilities;

/**
 * 
 * Directions for many purposes.
 * 
 * @author Adam Seidman
 *
 */
public enum Direction {
	NORTH(0), UP(0), RIGHT(1), EAST(1), DOWN(2), SOUTH(2), WEST(3), LEFT(3), OTHER(4), NONE(5);
	Direction(int direction) {
		this._direction = direction;
	}

	private final int _direction;

	public boolean equals(Direction direction) {
		return this._direction == direction._direction;
	}
}
