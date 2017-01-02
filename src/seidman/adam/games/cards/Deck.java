package seidman.adam.games.cards;

public class Deck {

	private Card[] _cards;

	public Deck() {
		shuffle();
	}

	private Deck(String[] args) {
		_cards = new Card[52];
	}

	public void shuffle() {
		_cards = new Card[52];
		Deck temp = new Deck(null);
		assignLoop: for (int i = 0; i < temp._cards.length; i++) {
			System.out.println(i);
			Card c = Card.getRandomCard();
			if (temp.contains(c)) {
				i--;
				continue assignLoop;
			}
			temp._cards[i] = c;
		}
		for (int i = 0; i < _cards.length; i++) {
			_cards[i] = temp._cards[i];
		}
	}

	@Override
	public String toString() {
		String str = "";
		for (Card i : _cards)
			str += i + "\n";
		return str;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Deck) {
			return ((Deck) obj).toString().equals(this.toString());
		}
		return super.equals(obj);
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

	public Card[] getDeck() {
		return this._cards;
	}
	
}
