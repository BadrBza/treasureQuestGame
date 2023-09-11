package treasurequest.supervisors.views;

import java.util.List;

/**
 * Décrit le comment définir les données à afficher du menu principal.
 * 
 * */
public interface MainMenuView extends View {

	/**
	 * Définit les items à afficher.
	 * 
	 * Quand cette méthode est appelée, l'implémentation met le focus sur le premier item.
	 * Ne fait rien si {@code items} est {@code null} ou {@code vide}.
	 * */
	void setItems(List<String> items);
}
