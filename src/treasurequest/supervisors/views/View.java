package treasurequest.supervisors.views;

/**
 * Liste les méthodes à implémenter pour définir une vue vers laquelle on peut naviguer.
 * 
 * */
public interface View {
	/**
	 * Retourne le nom de cet écran.
	 * */
	String getName();
	
	/**
	 * Méthode appelée par la fenêtre principale avant que cet écran ne devienne l'écran courant.
	 * */
	void onEnter(String fromScreen);
	
	/**
	 * Méthode appelée par la fenêtre principale avant que cet écran ne soit plus l'écran courant.
	 * */
	void onLeave(String toScreen);
	
	/**
	 * Méthode appelée par un superviseur pour confirmer une demande de quitter
	 * */
	void confirmExit();

	/**
	 * Méthode appelée par un superviseur pour demander de naviguer de cet écran vers l'ecran {@code screenName}.
	 * */
	void goTo(String screenName);
}
