package seidman.adam.games.cards;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class CardPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private ArrayList<Card> _cardList;
	private String _title;

	public CardPanel() {
		this("");
	}

	public CardPanel(String title) {
		this._title = title;
		this._cardList = new ArrayList<Card>();
	}

	public void addCard(Card c) {
		this._cardList.add(c);
	}

	public boolean contains(Card c) {
		for (Card i : this._cardList) {
			if (i.equals(c)) {
				return true;
			}
		}
		return false;
	}

	public boolean remove(Card c) {
		if (!this.contains(c)) {
			return false;
		}
		for (Card i : this._cardList) {
			if (i.equals(c)) {
				this._cardList.remove(i);
				return true;
			}
		}
		return false;
	}

	public Card[] getCardList() {
		return (Card[]) this._cardList.clone();
	}

	public void reset() {
		this._cardList = new ArrayList<Card>();
	}

	public void paintComponent(Graphics g) {
		int panelWidth = this.getWidth();
	}

}
