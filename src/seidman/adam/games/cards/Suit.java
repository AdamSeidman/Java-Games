package seidman.adam.games.cards;

import java.awt.Color;
import java.awt.Graphics;

public interface Suit {

	public void draw(Graphics g, int x, int y);

	public class Club implements Suit {
		public void draw(Graphics g, int x, int y) {
			x -= (Constants.CLUB_SIZE.width / 2);
			y -= (Constants.CLUB_SIZE.height / 2);
			Color rColor = g.getColor();
			g.setColor(Color.BLACK);
			g.fillOval(x, 6 + y, 16, 16);
			g.fillOval(8 + x, y, 16, 16);
			g.fillOval(16 + x, 6 + y, 16, 16);
			g.fillRect(11 + x, 16 + y, 10, 12);
			g.setColor(rColor);
		}
	}

	public class Heart implements Suit {
		public void draw(Graphics g, int x, int y) {
			x -= (Constants.HEART_SIZE.width / 2);
			y -= (Constants.HEART_SIZE.height / 2);
			Color rColor = g.getColor();
			g.setColor(Color.RED);
			g.fillOval(x, y, 14, 14);
			g.fillOval(x + 12, y, 14, 14);
			x += 2;
			y += 11;
			int[] xPoints = new int[] { (11 + x), x, (23 + x) };
			int[] yPoints = new int[] { (13 + y), y, y };
			g.fillPolygon(xPoints, yPoints, 3);
			g.setColor(rColor);
		}
	}

	public class Spade implements Suit {
		public void draw(Graphics g, int x, int y) {
			x -= (Constants.SPADE_SIZE.width / 2);
			y -= (Constants.SPADE_SIZE.height / 2);
			Color rColor = g.getColor();
			g.setColor(Color.BLACK);
			int[] xPoints = new int[] { (10 + x + 3), (x + 3), (21 + x + 3) };
			int[] yPoints = new int[] { y, (y + 8), (y + 8) };
			g.fillPolygon(xPoints, yPoints, 3);
			g.fillOval(x + 1, y + 2, 24, 24);
			g.setColor(Constants.CARD_FRONT_COLOR);
			g.fillRect(x + 1, y + 21, 25, 20);
			g.setColor(Color.BLACK);
			g.fillOval(3 + x, 17 + y, 6, 6);
			g.fillOval(17 + x, 17 + y, 6, 6);
			g.setColor(Constants.CARD_FRONT_COLOR);
			xPoints = new int[] { (x + 3), x - 1, (x + 7) };
			yPoints = new int[] { y + 20, (y + 24), (y + 24) };
			g.fillPolygon(xPoints, yPoints, 3);
			xPoints = new int[] { (x + 24), x + 20, (x + 28) };
			yPoints = new int[] { y + 21, (y + 25), (y + 25) };
			g.fillPolygon(xPoints, yPoints, 3);
			g.setColor(Color.BLACK);
			g.fillOval(x + 5, y + 21, 17, 3);
			g.setColor(Constants.CARD_FRONT_COLOR);
			xPoints = new int[] { (13 + x), x + 5, (22 + x) };
			yPoints = new int[] { y + 12, (y + 24), (y + 24) };
			g.fillPolygon(xPoints, yPoints, 3);
			g.setColor(Color.BLACK);
			g.fillRect(x + 9, y + 8, 9, 20);
			xPoints = new int[] { (13 + x), x + 3, (23 + x) };
			yPoints = new int[] { y + 22, (y + 28), (y + 28) };
			g.fillPolygon(xPoints, yPoints, 3);
			g.setColor(rColor);
		}
	}

	public class Diamond implements Suit {
		public void draw(Graphics g, int x, int y) {
			x -= (Constants.DIAMOND_SIZE.width / 2);
			y -= (Constants.DIAMOND_SIZE.height / 2);
			Color rColor = g.getColor();
			g.setColor(Color.RED);
			int[] xPoints = new int[] { 12 + x, x + 24, x + 12, x };
			int[] yPoints = new int[] { y, y + 14, y + 28, y + 14 };
			g.fillPolygon(xPoints, yPoints, 4);
			g.setColor(rColor);
		}
	}

}
