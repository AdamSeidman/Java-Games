package seidman.adam.games.cards;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import seidman.adam.games.utilities.ClickableRegion;
import seidman.adam.games.utilities.Index;

/**
 * 
 * A simple card object that stores a suit and the card value.
 * 
 * @author Adam Seidman
 *
 */
public class Card {

	public enum RegionCollectorType {
		USE_LATEST, USE_ALL, NONE
	};

	private ArrayList<ClickableRegion> _clickRegions = new ArrayList<ClickableRegion>();
	private boolean _flipped = false;
	private int _number;
	private double _scaleFactor = 1.0;
	private Suit _suit;

	RegionCollectorType _regCollType;

	/**
	 * Create a new random card.
	 * 
	 * @return A new Card.
	 */
	public static final Card getRandomCard() {
		Random random = new Random();
		Suit suit = new Suit.Diamond();
		int r = random.nextInt(4);
		switch (r) {
		case Constants.CLUBS:
			suit = new Suit.Club();
			break;
		case Constants.HEARTS:
			suit = new Suit.Heart();
			break;
		case Constants.SPADES:
			suit = new Suit.Spade();
			break;
		}
		return new Card(random.nextInt(Constants.KING) + 1, suit);
	}

	/**
	 * Create a card.
	 * 
	 * @param number
	 *            An int- the number value of the card.
	 * @param suit
	 *            An Suit- the suit of the card.
	 */
	public Card(int number, Suit suit) {
		this(number, suit, RegionCollectorType.USE_LATEST);
	}

	public Card(int number, Suit suit, RegionCollectorType regCollType) {
		this._regCollType = regCollType;
		this._suit = suit;
		this._number = number;
		this._flipped = number < 0;
	}

	public Card clone() {
		return new Card(this._number, this._suit);
	}

	public void draw(Graphics g, int x, int y) {
		int width = this.scale(Constants.CARD_WIDTH);
		int height = this.scale(Constants.CARD_HEIGHT);

		// Region Collecting
		if (this._regCollType == RegionCollectorType.USE_LATEST) {
			this._clickRegions = new ArrayList<ClickableRegion>();
		}
		if (this._regCollType != RegionCollectorType.NONE) {
			this._clickRegions.add(new ClickableRegion(x, y, width, height));
		}

		// Normal Drawing
		g.setColor(this._number <= 0 ? Constants.CARD_BACK_COLOR : Constants.CARD_FRONT_COLOR);
		g.fillRoundRect(x, y, width, height, Constants.ROUND_RECT_CONSTANTS[0], Constants.ROUND_RECT_CONSTANTS[1]);
		if (Constants.DRAW_CARD_OUTLINE) {
			g.setColor(Constants.CARD_OUTLINE_COLOR);
			g.drawRoundRect(x, y, width, height, Constants.ROUND_RECT_CONSTANTS[0], Constants.ROUND_RECT_CONSTANTS[1]);
		}
		if (this._number > 0) {
			for (Index i : Constants.coordList(x, y, this)) {
				this._suit.draw(g, i.getX(), i.getY(), this.getScaleFactor());
			}
		} else {
			g.setColor(Constants.CARD_BACK_SYMBOL_COLOR);
			g.fillOval((x + (width / 2)) - (this.scale(Constants.SYMBOL_WIDTH) / 2),
					(y + (height / 2)) - (this.scale(Constants.SYMBOL_HEIGHT) / 2), this.scale(Constants.SYMBOL_WIDTH),
					this.scale(Constants.SYMBOL_HEIGHT));
		}
	}

	@Override
	/**
	 * Overridden to check to see if the toString() methods match.
	 */
	public boolean equals(Object obj) {
		if (obj instanceof Card) {
			return this.toString().equals(obj.toString());
		}
		return super.equals(obj);
	}

	/**
	 * Flips the card. If it is facing forward, hides the cards face. If it is
	 * facing backward, it shows you the cards face.
	 * 
	 * @return this.isFlipped()
	 */
	public boolean flipCard() {
		this._number *= -1;
		this._flipped = !this._flipped;
		return this.isFlipped();
	}

	public int getHeight() {
		return (int) (((double) Constants.CARD_HEIGHT) * this.getScaleFactor());
	}

	/**
	 * @return An int- number value of the card.
	 */
	public int getNum() {
		return this._number;
	}

	public double getScaleFactor() {
		return this._scaleFactor;
	}

	/**
	 * @return An Suit- suit of the card.
	 */
	public Suit getSuit() {
		return this._suit;
	}

	public int getWidth() {
		return (int) (((double) Constants.CARD_WIDTH) * this.getScaleFactor());
	}

	public boolean isBlackjackWith(Card c) {
		if (this._number == Constants.ACE) {
			return c.isFaceCard();
		} else if (this._number > 10 && c.getNum() < 10) {
			return c.isBlackjackWith(this);
		}
		return false;
	}

	public boolean isFaceCard() {
		return this._number == Constants.JACK || this._number == Constants.QUEEN || this._number == Constants.KING;
	}

	/**
	 * @return True, if number <= 0.
	 */
	public boolean isFlipped() {
		return this._number <= 0;
	}

	/**
	 * Return an integer scaled by the Card scale factor as an integer. It is
	 * accessible across the package.
	 * 
	 * @param n
	 *            The number you want to scale
	 * @return The scaled integer.
	 */
	int scale(int n) {
		return (int) (((double) n) * this.getScaleFactor());
	}

	/**
	 * Set all of the card information at once.
	 * 
	 * @param number
	 *            An int- the number value of the card.
	 * @param suit
	 *            An Suit- the suit of the card.
	 */
	public void set(int number, Suit suit) {
		this._number = number;
		this._suit = suit;
	}

	/**
	 * Sets the number value of the card.
	 * 
	 * @param number
	 *            An int- the number value of the card.
	 */
	public void setNumber(int number) {
		this._number = number;
	}

	public double setScaleFactor(double sf) {
		this._scaleFactor = sf;
		return this._scaleFactor;
	}

	/**
	 * Sets the suit of the card.
	 * 
	 * @param suit
	 *            An Suit- the suit of the card.
	 */
	public void setSuit(Suit suit) {
		this._suit = suit;
	}

	@Override
	/**
	 * @return A String- the common-speak way to refer to a card.
	 */
	public String toString() {
		String suit = "";
		if (_suit instanceof Suit.Club) {
			suit = " of Clubs";
		} else if (_suit instanceof Suit.Heart) {
			suit = " of Hearts";
		} else if (_suit instanceof Suit.Spade) {
			suit = " of Spades";
		} else {
			suit = " of Diamonds";
		}
		switch (this._number) {
		case Constants.ACE:
			return "Ace" + suit;
		case Constants.JACK:
			return "Jack" + suit;
		case Constants.QUEEN:
			return "Queen" + suit;
		case Constants.KING:
			return "King" + suit;
		default:
			return Integer.toString(this._number) + suit;
		}
	}

}
