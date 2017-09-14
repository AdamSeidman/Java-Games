package seidman.adam.games.cards;

public class Deck {

	private Card[] _cards;

	public Deck() {
		this.shuffle();
	}

	private Deck(Object ph) {
		this._cards = new Card[52];
	}

	public boolean contains(Card c) {
		try {
			for (Card i : _cards) {
				if (c.equals(i)) {
					return true;
				}
			}
		} catch (NullPointerException e) {
		}
		return false;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Deck) {
			return ((Deck) obj).toString().equals(this.toString());
		}
		return super.equals(obj);
	}

	public Card get(int n) {
		return this._cards[n].clone();
	}

	public Card[] getDeck() {
		return this._cards;
	}

	public void shuffle() {
		_cards = new Card[52];
		Deck temp = new Deck(null);
		assignLoop: for (int i = 0; i < temp.getDeck().length; i++) {
			Card c = Card.getRandomCard();
			if (temp.contains(c)) {
				i--;
				continue assignLoop;
			}
			temp._cards[i] = c;
		}
		for (int i = 0; i < this._cards.length; i++) {
			this._cards[i] = temp.getDeck()[i];
		}
	}

	@Override
	public String toString() {
		String str = "";
		for (Card i : _cards)
			str += i + "\n";
		return str;
	}

}
