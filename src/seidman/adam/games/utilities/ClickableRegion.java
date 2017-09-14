package seidman.adam.games.utilities;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class ClickableRegion extends Region {

	public ClickableRegion(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	private ArrayList<MouseListener> _mouseListeners;

	public void addMouseListener(MouseListener ml) {
		this._mouseListeners.add(ml);
	}

	public MouseListener getMouseListener() {
		if (this._mouseListeners.size() > 0) {
			return this._mouseListeners.get(0);
		}
		return null;
	}

	public MouseListener getMouseListener(int index) {
		if (index < this._mouseListeners.size() - 1) {
			return this._mouseListeners.get(index);
		}
		return null;
	}

	public ArrayList<MouseListener> getMouseListeners() {
		return this._mouseListeners;
	}

	/**
	 * Remove a MouseListener.
	 * 
	 * @param index
	 *            of MouseListener to remove.
	 * @return True, if list contains index.
	 */
	public boolean removeMouseListener(int index) {
		if (index < this._mouseListeners.size() - 1) {
			this._mouseListeners.remove(index);
			return true;
		}
		return false;
	}

	/**
	 * Remove a MouseListener.
	 * 
	 * @param ml
	 *            MouseListener to remove
	 * @return True, if list contains ml.
	 */
	public boolean removeMouseListener(MouseListener ml) {
		if (this._mouseListeners.contains(ml)) {
			this._mouseListeners.remove(ml);
			return true;
		}
		return false;
	}

	/**
	 * Send a mouse press to the MouseListeners.
	 * 
	 * @param e
	 *            MouseEvent to use and send a mouse press to the MouseListeners
	 */
	public void sendMousePress(MouseEvent e) {
		for (MouseListener ml : this._mouseListeners) {
			ml.mousePressed(e);
		}
	}

	/**
	 * Send a mouse press to all of the MouseListeners using (x, y)
	 * 
	 * @param x
	 *            coordinate of mouse point
	 * @param y
	 *            coordinate of mouse point
	 * @param e
	 *            MouseEvent to use and send a mouse press to the MouseListeners
	 * @return True if the point is in bounds.
	 */
	public boolean sendMousePress(int x, int y, MouseEvent e) {
		boolean inBounds = this.inBounds(x, y);
		if (inBounds) {
			this.sendMousePress(e);
		}
		return inBounds;
	}

	/**
	 * Send a mouse release to the MouseListeners.
	 * 
	 * @param e
	 *            MouseEvent to use and send a mouse release to the MouseListeners
	 */
	public void sendMouseRelease(MouseEvent e) {
		for (MouseListener ml : this._mouseListeners) {
			ml.mousePressed(e);
		}
	}

	/**
	 * Send a mouse release to all of the MouseListeners using (x, y)
	 * 
	 * @param x
	 *            coordinate of mouse point
	 * @param y
	 *            coordinate of mouse point
	 * @param e
	 *            MouseEvent to use and send a mouse release to the MouseListeners
	 * @return True if the point is in bounds.
	 */
	public boolean sendMouseRelease(int x, int y, MouseEvent e) {
		boolean inBounds = this.inBounds(x, y);
		if (inBounds) {
			this.sendMouseRelease(e);
		}
		return inBounds;
	}

}
