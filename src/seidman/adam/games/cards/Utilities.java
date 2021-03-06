package seidman.adam.games.cards;

/**
 * 
 * Nice utilities to use for Card's
 * 
 * @author Adam Seidman
 *
 */
public abstract class Utilities {

	public static int scale(int n, double sf) {
		return (int) (((double) n) * sf);
	}

	public static int sumOf(Card[] list) {
		int ret = 0;
		for (Card card : list) {
			ret += card.getNum();
		}
		return ret;
	}

}
