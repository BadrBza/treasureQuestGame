package treasurequest.swing;

import java.util.function.Consumer;

import javax.swing.JPanel;

import treasurequest.supervisors.views.View;


/**
 * Classe de base des vues implémentées en Swing.
 * 
 * Toute vue est identifiée par un nom. Ce nom est utilisé pour naviguer entre les vues.
 * */
public abstract class SwingView extends JPanel implements View {
	private static final long serialVersionUID = 225420598267162189L;
	protected Consumer<String> router;

	/**
	 * Construit une nouvelle vue avec un nom.
	 * 
	 * Les dimensions de l'écran sont tirées du thème.
	 * 
	 * @param name le titre de l'écran.
	 * */
	public SwingView(String name) {
		super(null);
		
		setBackground(Theme.BACKGROUND_COLOR);
		setSize(Theme.PANEL_WIDTH, Theme.PANEL_HEIGHT);
		
		this.setName(name);
	}

	/**
	 * Méthode appelée par la fenêtre principale avant que cette vue ne devienne la vue courante.
	 * */
	@Override
	public void onEnter(String fromView) {}
	
	/**
	 * Méthode appelée par la fenêtre principale avant  que cette vue ne soit plus la vue courante.
	 * */
	@Override
	public void onLeave(String toView) {}

	@Override
	public void confirmExit() {
		System.exit(0);
	}
	
	/**
	 * Méthode appelée quand l'utilisateur appuie sur une touche.
	 * 
	 * @param keyCode le code virtuel correspondant à la touche.
	 * */
	public void onKeyTyped(int keyCode) {
	}

	/**
	 * Définit la méthode à appeler pour traiter les demandes de navigation.
	 * */
	protected void setRouter(Consumer<String> router) {
		this.router = router;
	}

	/**
	 * Délègue une demande de navigation vers la vue {@code screenName}.
	 * */
	@Override
	public void goTo(String viewName) {
		this.router.accept(viewName);		
	}
}
