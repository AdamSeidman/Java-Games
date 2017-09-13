package seidman.adam.games.cards;

import java.awt.Color;
import java.awt.Graphics;

public interface Suit {

	public void draw(Graphics g, int x, int y);

	abstract class PP { // Position Points
		// Club
		private static final int[] COL = new int[] { 0, 6, 16, 16 };
		private static final int[] COM = new int[] { 8, 0, 16, 16 };
		private static final int[] COR = new int[] { 16, 6, 16, 16 };
		private static final int[] CR = new int[] { 11, 16, 10, 12 };

		// Heart
		private static final int[] HOL = new int[] { 1, 0, 14, 14 };
		private static final int[] HOR = new int[] { 12, 0, 15, 14 };
		private static final int H_ADDITIONAL_OFFSET_X = 2;
		private static final int H_ADDITIONAL_OFFSET_Y = 11;
		private static final int[] HTX = new int[] { 11, 0, 23 };
		private static final int[] HTY = new int[] { 13, 0, 0 };

		// Spade
		private static final int[] STX = new int[] { 13, 3, 24 };
		private static final int[] STY = new int[] { 0, 8, 8 };
		private static final int[] SB = new int[] { 1, 2, 24, 24 };
		private static final int[] SCTX = new int[] { 13, 0, 27 };
		private static final int[] SCTY = new int[] { 12, 36, 36 };
		private static final int[] SS = new int[] { 9, 8, 9, 20 };
		private static final int[] SFX = new int[] { 13, 3, 23 };
		private static final int[] SFY = new int[] { 22, 28, 28 };

		// Diamond
		private static final int[] DX = new int[] { 12, 24, 12, 0 };
		private static final int[] DY = new int[] { 0, 14, 28, 14 };
	}

	public class Club implements Suit {
		public void draw(Graphics g, int x, int y) {
			x -= (Card.scale(Constants.CLUB_SIZE.width) / 2);
			y -= (Card.scale(Constants.CLUB_SIZE.height) / 2);
			Color rColor = g.getColor();
			g.setColor(Constants.CLUB_COLOR);
			g.fillOval(x + Card.scale(PP.COL[0]), y + Card.scale(PP.COL[1]), Card.scale(PP.COL[2]),
					Card.scale(PP.COL[3]));
			g.fillOval(x + Card.scale(PP.COM[0]), y + Card.scale(PP.COM[1]), Card.scale(PP.COM[2]),
					Card.scale(PP.COM[3]));
			g.fillOval(x + Card.scale(PP.COR[0]), y + Card.scale(PP.COR[1]), Card.scale(PP.COR[2]),
					Card.scale(PP.COR[3]));
			g.fillRect(x + Card.scale(PP.CR[0]), y + Card.scale(PP.CR[1]), Card.scale(PP.CR[2]),
					Card.scale((PP.CR[3])));
			g.setColor(rColor);
		}
	}

	public class Heart implements Suit {
		public void draw(Graphics g, int x, int y) {
			x -= (Card.scale(Constants.HEART_SIZE.width) / 2);
			y -= (Card.scale(Constants.HEART_SIZE.height) / 2);
			Color rColor = g.getColor();
			g.setColor(Constants.HEART_COLOR);
			g.fillOval(x + Card.scale(PP.HOL[0]), y + Card.scale(PP.HOL[1]), Card.scale(PP.HOL[2]),
					Card.scale(PP.HOL[3]));
			g.fillOval(x + Card.scale(PP.HOR[0]), y + Card.scale(PP.HOR[1]), Card.scale(PP.HOR[2]),
					Card.scale(PP.HOR[3]));
			x += Card.scale(PP.H_ADDITIONAL_OFFSET_X);
			y += Card.scale(PP.H_ADDITIONAL_OFFSET_Y);
			int[] xPoints = new int[] { x + Card.scale(PP.HTX[0]), x + Card.scale(PP.HTX[1]),
					x + Card.scale(PP.HTX[2]) };
			int[] yPoints = new int[] { y + Card.scale(PP.HTY[0]), y + Card.scale(PP.HTY[1]),
					y + Card.scale(PP.HTY[2]) };
			g.fillPolygon(xPoints, yPoints, xPoints.length);
			g.setColor(rColor);
		}
	}

	public class Spade implements Suit {
		public void draw(Graphics g, int x, int y) {
			x -= (Card.scale(Constants.SPADE_SIZE.width) / 2);
			y -= (Card.scale(Constants.SPADE_SIZE.height) / 2);
			Color rColor = g.getColor();
			g.setColor(Constants.SPADE_COLOR);
			int[] xPoints = new int[] { x + Card.scale(PP.STX[0]), x + Card.scale(PP.STX[1]),
					x + Card.scale(PP.STX[2]) };
			int[] yPoints = new int[] { y + Card.scale(PP.STY[0]), y + Card.scale(PP.STY[1]),
					y + Card.scale(PP.STY[2]) };
			g.fillPolygon(xPoints, yPoints, 3);
			g.fillOval(x + Card.scale(PP.SB[0]), y + Card.scale(PP.SB[1]), Card.scale(PP.SB[2]), Card.scale(PP.SB[3]));
			g.setColor(Constants.CARD_FRONT_COLOR);
			xPoints = new int[] { x + Card.scale(PP.SCTX[0]), x + Card.scale(PP.SCTX[1]), x + Card.scale(PP.SCTX[2]) };
			yPoints = new int[] { y + Card.scale(PP.SCTY[0]), y + Card.scale(PP.SCTY[1]), y + Card.scale(PP.SCTY[2]) };
			g.fillPolygon(xPoints, yPoints, 3);
			g.setColor(Constants.SPADE_COLOR);
			g.fillRect(x + Card.scale(PP.SS[0]), y + Card.scale(PP.SS[1]), Card.scale(PP.SS[2]), Card.scale(PP.SS[3]));
			xPoints = new int[] { x + Card.scale(PP.SFX[0]), x + Card.scale(PP.SFX[1]), x + Card.scale(PP.SFX[2]) };
			yPoints = new int[] { y + Card.scale(PP.SFY[0]), y + Card.scale(PP.SFY[1]), y + Card.scale(PP.SFY[2]) };
			g.fillPolygon(xPoints, yPoints, 3);
			g.setColor(rColor);
		}
	}

	public class Diamond implements Suit {
		public void draw(Graphics g, int x, int y) {
			x -= (Card.scale(Constants.DIAMOND_SIZE.width) / 2);
			y -= (Card.scale(Constants.DIAMOND_SIZE.height) / 2);
			Color rColor = g.getColor();
			g.setColor(Constants.DIAMOND_COLOR);
			int[] xPoints = new int[] { x + Card.scale(PP.DX[0]), x + Card.scale(PP.DX[1]), x + Card.scale(PP.DX[2]),
					x + Card.scale(PP.DX[3]) };
			int[] yPoints = new int[] { y + Card.scale(PP.DY[0]), y + Card.scale(PP.DY[1]), y + Card.scale(PP.DY[2]),
					y + Card.scale(PP.DY[3]) };
			g.fillPolygon(xPoints, yPoints, 4);
			g.setColor(rColor);
		}
	}

}
