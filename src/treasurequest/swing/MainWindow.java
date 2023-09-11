/**
 * Implémente les interfaces utilisateur de l'application avec Swing.
 * */
package treasurequest.swing;

import java.util.*;

import javax.swing.JFrame;

import treasurequest.swing.adapter.*;

/**
 * Affiche les écrans qui composent l'application.
 * @author Nicolas Hendrikx
 */

public final class MainWindow extends JFrame {
	private static final long serialVersionUID = 6621287528953860235L;
	
	private final Map<String, SwingView> nameToScreen = new LinkedHashMap<>();
	private SwingView current;
	
	/**
	 * Initialise cette fenêtre principale avec un titre et des écrans.
	 * @param title le titre affiché par cette fenêtre principale.
	 */
	public MainWindow(String title, SwingView...screens) {
		super(title);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(Theme.WINDOW_WIDTH, Theme.WINDOW_HEIGHT);
		setBackground(Theme.BACKGROUND_COLOR);
		setFont(Theme.NORMAL_FONT);
		setResizable(false);
		
		for(var screen : screens) {
			this.nameToScreen.put(screen.getName(), screen);
			screen.setRouter(this::goTo);
		}
		
		this.addKeyListener(new MyKeyAdapter(this::onKeyTyped));
	}

	/**
	 * Méthode appelée par une implémentation Swing d'un écran pour changer d'écran affiché.
	 * */
	public void goTo(String screenTitle) {
		var found = getScreen(screenTitle);
		
		if(current != null) {
			current.onLeave(screenTitle);
		}
		found.onEnter(current == null ? "" : current.getName());
		
		this.current = found;		
		this.setContentPane(current);
	}

	private SwingView getScreen(String screenName) {
		if(!nameToScreen.containsKey(screenName)) {
			throw new IllegalArgumentException("screenName["+screenName+"] does not match any registered screen name.");
		}
		return nameToScreen.get(screenName);
	}

	/**
	 * Affiche le premier écran.
	 * */
	public void start(String screenName) {
		if(screenName != null) {
			this.goTo(screenName);
		}
		this.setVisible(true);
	}
	
	private void onKeyTyped(int keyCode) {
		if(current == null) {
			return;
		}
		this.current.onKeyTyped(keyCode);		
	}
	
}
