package seidman.adam.games.utilities.menu;

import java.awt.Container;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public abstract class MenuBarAdapter implements FocusListener {

	public MenuBarAdapter() {
	}


	public abstract Container getContent();
	
	public final void focusGained(FocusEvent e) {
		getContent().requestFocus();
	}

	public void focusLost(FocusEvent e) {
	}
	
}
