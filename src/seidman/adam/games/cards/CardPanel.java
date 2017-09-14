package seidman.adam.games.cards;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class CardPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private Color _bgColor;
	private ArrayList<Card> _cardList;
	private String _title;

	public CardPanel() {
		this("", Constants.DEFAULT_BACKGROUND_COLOR);
	}

	public CardPanel(Color bgColor) {
		this("", bgColor);
	}

	public CardPanel(String title) {
		this(title, Constants.DEFAULT_BACKGROUND_COLOR);
	}

	public CardPanel(String title, Color bgColor) {
		this._title = title;
		this._cardList = new ArrayList<Card>();
		this._bgColor = bgColor;
	}

	public void addCard(Card c) {
		this._cardList.add(c);
	}

	/**
	 * @param card - The card you are checking to see if the card panel contains.
	 * @return True, if the card panel contains the specified card.
	 */
	public boolean contains(Card card) {
		for (Card i : this._cardList) {
			if (i.equals(card)) {
				return true;
			}
		}
		return false;
	}

	public Card[] getCardList() {
		Card[] ret = new Card[this._cardList.size()];
		for (int i = 0; i < this._cardList.size(); i++) {
			ret[i] = (Card) this._cardList.get(i).clone();
		}
		return ret;
	}

	public String getTitle() {
		return this._title;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setBackground(this._bgColor);
		if (this._cardList.size() < 1) {
			return;
		}
		int panelWidth = this.getWidth();
		int panelHeight = this.getHeight();
		double scale = ((double) (panelHeight - (2 * Constants.MIN_BUFFER_Y))) / ((double) Constants.CARD_HEIGHT);
		scale = Math.min(scale, (((double) (panelWidth - (Constants.MIN_BUFFER_X * (this._cardList.size() + 1))))
				/ ((double) this._cardList.size())) / ((double) Constants.CARD_WIDTH));
		for (Card card : this._cardList) {
			card.setScaleFactor(scale);
		}
		int yBuffer = (panelHeight - this._cardList.get(0).getHeight()) / 2;
		int xBuffer = (panelWidth - (this._cardList.size() * this._cardList.get(0).getWidth()))
				/ (this._cardList.size() + 1);
		for (Card card : this._cardList) {
			card.draw(g, xBuffer, yBuffer);
			xBuffer += ((panelWidth - (this._cardList.size() * this._cardList.get(0).getWidth()))
					/ (this._cardList.size() + 1)) + card.getWidth();
		}
	}

	/**
	 * Remove a card from the card panel.
	 * 
	 * @param card
	 *            - The Card you want to remove
	 * @return True, if the removal was successful
	 */
	public boolean remove(Card card) {
		if (!this.contains(card)) {
			return false;
		}
		for (Card i : this._cardList) {
			if (i.equals(card)) {
				this._cardList.remove(i);
				return true;
			}
		}
		return false;
	}

	/**
	 * Remove all Cards from the card panel.
	 */
	public void reset() {
		this._cardList = new ArrayList<Card>();
	}

}
