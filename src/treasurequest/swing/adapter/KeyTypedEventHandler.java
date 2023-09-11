package treasurequest.swing.adapter;

/**
 * Définit la signature de la méthode qui sera notifiée des appuis sur les touches.
 * */
@FunctionalInterface
public interface KeyTypedEventHandler {

	/**
	 * Méthode appelée quand une touche est enfoncée.
	 * 
	 * @param keyCode le code de la touche, dépendante du layout du clavier.
	 * */
	void onKeyTyped(int keyCode);
}
