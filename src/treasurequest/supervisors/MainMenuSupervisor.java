package treasurequest.supervisors;

import java.util.List;

import treasurequest.domains.CreateTreasureQuestGame;
import treasurequest.domains.GameData;
import treasurequest.supervisors.views.*;

/**
 * Fournit les données qu'une vue représenter le menu principal doit afficher.
 * 
 * Réagit aux événements de haut niveau signalé par sa vue.
 */
public class MainMenuSupervisor {

	private static final int EXIT_ITEM = 2;

	private MainMenuView view;

	private final CreateTreasureQuestGame factory;
	private final GameData gameData;

	/**
	 * Crée une instance de PlayGameSupervisor avec une factory de
	 * TreasureQuestGame.
	 *
	 * @param gameFactory La factory de TreasureQuestGame utilisée pour créer les
	 *                    instances de jeu.
	 */
	public MainMenuSupervisor(CreateTreasureQuestGame factory, GameData gameData) {
		this.factory = factory;
		this.gameData = gameData;
	}

	public void setView(MainMenuView view) {
		if (view == null) {
			return;
		}

		this.view = view;
		this.view.setItems(List.of("Nouvelle partie", "Partie Aléatoire", "Quitter la partie"));
		// TODO : définir à la vue les items.
	}

	/**
	 * Méthode appelée par la vue quand l'utilisateur sélectionne un item.
	 * 
	 * @param itemPos la position de l'item sélectionné.
	 *                {@code item in [0; items.length[}
	 */
	public void onItemSelected(int itemPos) {
		if (EXIT_ITEM == itemPos) {
			view.confirmExit();
		}
		if (itemPos == 0) {
			factory.createNewGame();

		} else if (itemPos == 1) {
			factory.createNewGameBigMap();
		}
		view.goTo(ViewNames.PLAY_GAME);

	}

}
