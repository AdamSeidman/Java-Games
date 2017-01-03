package seidman.adam.games.minesweeper;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import seidman.adam.games.Playable;
import seidman.adam.games.minesweeper.logic.MinesweeperGame;
import seidman.adam.games.minesweeper.utilities.Variables;
import seidman.adam.games.utilities.WindowLeavingAdapter;
import seidman.adam.games.utilities.menu.MenuAdapter;
import seidman.adam.games.utilities.menu.MenuBarAdapter;
import seidman.adam.games.utilities.menu.MenuSeparator;

/**
 * 
 * Main user interface for the minesweeper game.
 * 
 * @author Adam Seidman
 * 
 */
public final class MinesweeperUI extends JFrame implements Playable {

	private static final long serialVersionUID = 1L;
	private static MinesweeperUI _instance;

	private JMenuBar _menu;
	private JMenu _edit;
	private JMenu _restart;
	private JMenu _help;
	private JMenu _about;

	private MinesweeperUI() {
		super(Variables.TITLE);
		this.setSize(Variables.frameSize());
		this.setLocationRelativeTo(null); // Set to center.
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowLeavingAdapter(Variables.TITLE, Variables.QUIT_MESSAGE) {
			public void windowClosing() {
				// Thank user for playing and remove visibility.
				MinesweeperUI.getInstance().setVisible(false);
				JOptionPane.showMessageDialog(null, new JLabel(Variables.LEAVE_MESSAGE, SwingConstants.CENTER),
						Variables.TITLE, JOptionPane.PLAIN_MESSAGE, null);
			}
		});
		this.setContentPane(MinesweeperGame.getInstance().getPanel());
		// Create four menu options and add their
		// seidman.adam.games.minesweeper.utilities.MenuAdapter's.
		_menu = new JMenuBar();
		_edit = new JMenu("Edit");
		_edit.addMenuListener(new MenuAdapter(_edit) {
			public void menuClicked() {
				Variables.adjustVariables();
			}
		});
		_menu.add(_edit);
		_menu.add(new MenuSeparator());
		_restart = new JMenu("Restart");
		_restart.addMenuListener(new MenuAdapter(_restart) {
			public void menuClicked() {
				int a = -1;
				while (a < 0) {
					a = JOptionPane.showOptionDialog(null, new JLabel(Variables.RESTART_MESSAGE, SwingConstants.CENTER),
							"Restart", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, -1);
				}
				if (a == 0) {
					MinesweeperGame.getInstance().reDoGame();
				}
			}
		});
		_menu.add(_restart);
		_menu.add(new MenuSeparator());
		_help = new JMenu("Help");
		_help.addMenuListener(new MenuAdapter(_help) {
			public void menuClicked() {
				showHelpMessage();
			}
		});
		_menu.add(_help);
		_menu.add(new MenuSeparator());
		_about = new JMenu("About");
		_about.addMenuListener(new MenuAdapter(_about) {
			public void menuClicked() {
				JOptionPane.showMessageDialog(null, new JLabel(Variables.ABOUT_MESSAGE, SwingConstants.CENTER), "About",
						JOptionPane.PLAIN_MESSAGE, null);
			}
		});
		_menu.add(_about);
		_menu.addFocusListener(new MenuBarAdapter() {
			public Container getContent() {
				return MinesweeperUI.getInstance().getContentPane();
			}
		});
		this.setJMenuBar(_menu);
		this.setResizable(Variables.RESIZABLE);
	}

	/**
	 * Get current instance of MinesweeperUI running.
	 * 
	 * @return MinesweeperUI _instance
	 */
	public static MinesweeperUI getInstance() {
		if (_instance == null) {
			_instance = new MinesweeperUI();
		}
		return _instance;
	}

	/**
	 * Redraw all important graphics on the UI.
	 */
	public void reset() {
		this.setContentPane(MinesweeperGame.getInstance().getPanel());
		this.revalidate();
		this.repaint();
	}

	/**
	 * Show help message to user.
	 */
	private static void showHelpMessage() {
		JLabel label = new JLabel(Variables.HELP_MESSAGE);
		Font currentFont = label.getFont(); // Get current font.
		label.setFont(new Font(currentFont.getFontName(), Font.PLAIN, currentFont.getSize()));
		// ^ Make font plain ^
		label.setPreferredSize(new Dimension(375, 215)); // Set Size.
		JOptionPane.showMessageDialog(null, label, Variables.helpMessageTitle(), JOptionPane.PLAIN_MESSAGE, null);
	}

	/**
	 * Method needed for UI implementation.
	 */
	public void runUI() {
		MinesweeperUI.getInstance().setVisible(true); // Create/show new game.
		showHelpMessage(); // Display help message.
	}

	public static void main(String[] args) {
		MinesweeperUI.getInstance().runUI(); // Run the UI
	}

}
