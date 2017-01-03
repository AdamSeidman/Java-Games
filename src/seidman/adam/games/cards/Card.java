package seidman.adam.games.cards;

import java.awt.Graphics;
import java.util.Random;

import seidman.adam.games.utilities.Index;

/**
 * 
 * A simple card object that stores a suit and the card value.
 * 
 * @author Adam Seidman
 *
 */
public class Card {

	private Suit _suit;
	private int _number;

	/**
	 * Create a card.
	 * 
	 * @param number
	 *            An int- the number value of the card.
	 * @param suit
	 *            An Suit- the suit of the card.
	 */
	public Card(int number, Suit suit) {
		this._suit = suit;
		this._number = number;
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

	/**
	 * Sets the suit of the card.
	 * 
	 * @param suit
	 *            An Suit- the suit of the card.
	 */
	public void setSuit(Suit suit) {
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
	 * @return An Suit- suit of the card.
	 */
	public Suit getSuit() {
		return this._suit;
	}

	/**
	 * @return An int- number value of the card.
	 */
	public int getNum() {
		return this._number;
	}

	/**
	 * Create a new random card.
	 * 
	 * @return A new Card.
	 */
	public static final Card getRandomCard() {
		Random random = new Random();
		Suit s = new Suit.Diamond();
		int r = random.nextInt(4);
		switch (r) {
		case Constants.CLUBS:
			s = new Suit.Club();
			break;
		case Constants.HEARTS:
			s = new Suit.Heart();
			break;
		case Constants.SPADES:
			s = new Suit.Spade();
			break;
		}
		return new Card(random.nextInt(13) + 1, s);
	}

	public void draw(Graphics g, int x, int y) {
		g.setColor(this._number <= 0 ? Constants.CARD_BACK_COLOR : Constants.CARD_FRONT_COLOR);
		g.fillRoundRect(x, y, Constants.CARD_WIDTH, Constants.CARD_HEIGHT, Constants.ROUND_RECT_CONSTANTS[0],
				Constants.ROUND_RECT_CONSTANTS[1]);
		g.setColor(Constants.CARD_OUTLINE_COLOR);
		g.drawRoundRect(x, y, Constants.CARD_WIDTH, Constants.CARD_HEIGHT, Constants.ROUND_RECT_CONSTANTS[0],
				Constants.ROUND_RECT_CONSTANTS[1]);
		if (_number > 0) {
			for (Index i : Constants.coordMap(x, y).get(_number)) {
				_suit.draw(g, i.getX(), i.getY());
			}
		} else {
			g.setColor(Constants.CARD_BACK_SYMBOL_COLOR);
			g.fillOval((x + Constants.CARD_CENTER.getX()) - (Constants.SYMBOL_WIDTH / 2),
					(y + Constants.CARD_CENTER.getY()) - (Constants.SYMBOL_HEIGHT / 2), Constants.SYMBOL_WIDTH,
					Constants.SYMBOL_HEIGHT);
		}
	}

	public boolean isFaceCard() {
		return _number == 11 || _number == 12 || _number == 13;
	}

}
