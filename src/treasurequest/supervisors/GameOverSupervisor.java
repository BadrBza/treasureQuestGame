package treasurequest.supervisors;

import treasurequest.domains.CaseType;
import treasurequest.domains.GameData;
import treasurequest.domains.ProfilePlayer;
import treasurequest.domains.RetrieveTreasureQuestGame;
import treasurequest.domains.TreasureQuestGame;
import treasurequest.supervisors.views.GameOverView;
import treasurequest.supervisors.views.ResultType;
import treasurequest.supervisors.views.ViewNames;

/**
 * Réagit aux événements de haut-niveau de sa vue et lui fournit des données à
 * afficher.
 * 
 * 
 * La partie se termine dans les situations suivantes :
 * 
 * -lorsque le joueur a découvert tous les trésors, lorsqu'il fait banqueroute
 * ou lorsqu'il décide d'abandonner.
 * 
 * -Le joueur fait banqueroute lorsqu'il n'a plus suffisamment de pièces pour
 * creuser les cases restantes.
 * 
 * -La partie se termine lorsque toutes les cases pouvant être creusées par le
 * joueur ont été explorées.
 * 
 * -Lorsque la partie est terminée, le joueur est redirigé vers l'écran de fin
 * de partie. Cependant, en cas d'abandon, le joueur est redirigé vers le menu
 * principal.
 * 
 * Evaluation de la Ctt de la méthode exploreAndCountZone est présente dans la
 * classe ProfilePlayer.
 * 
 * 
 */
public class GameOverSupervisor {
	private GameOverView view;

	private final RetrieveTreasureQuestGame factory;
	private TreasureQuestGame game;
	private final GameData gameData;

	/**
	 * Crée une instance de GameOverSupervisor avec une factory de
	 * TreasureQuestGame.
	 *
	 * @param gameFactory La factory de TreasureQuestGame utilisée pour créer les
	 *                    instances de jeu.
	 */
	public GameOverSupervisor(RetrieveTreasureQuestGame factory, GameData gameData) {
		this.factory = factory;
		this.gameData = gameData;
	}

	public void setView(GameOverView view) {
		if (view == null) {
			return;
		}

		this.view = view;
	}

	/**
	 * Méthode appelée par la vue quand une navigation vers elle est en cours.
	 * 
	 * @param fromView la vue d'origine. Correspond a priori à une constante définie
	 *                 dans ViewNames.
	 */
	public void onEnter(String fromView) {
		// TODO : générer les résultats et les afficher.
		this.game = factory.getGame();
		generateEndGameStats();

	}

	/**
	 * 
	 * Génère les statistiques de fin de partie.
	 */
	private void generateEndGameStats() {
		// Récupérer les statistiques du joueur depuis le jeu
		int expenses = gameData.getTotalExpenses();
		int earnings = gameData.getTotalEarnings();
		long totalTimePlayed = gameData.getTotalTimePlayed();

		ProfilePlayer profile = new ProfilePlayer(game.getDugCoordinates());
		CaseType profileCaseType = profile.determineDominantCaseType();

		// Récupérer le profil du joueur

		view.clearPanels();
		// Ajouter les résultats des statistiques à la vue
		view.addPanel(ResultType.LOSS, "Dépenses : " + expenses);
		view.addPanel(ResultType.GAIN, "Gains : " + earnings);
		view.addPanel(ResultType.DURATION, "Temps de jeu : " + formatTotalTimePlayed(totalTimePlayed));

		ResultType resultProfile = handlePlayerProfile(profileCaseType); // Convertir le profil du joueur en ResultType
		view.addPanel(resultProfile, "Profil du joueur : " + resultProfile.name()); // Ajouter le profil du joueur à la
																					// vue
	}

	/**
	 * 
	 * Formate le temps de jeu total au format "MM:SS".
	 * 
	 * @param totalTimePlayed Le temps de jeu total en millisecondes.
	 * @return Le temps de jeu total formaté au format "MM:SS".
	 */
	private String formatTotalTimePlayed(long totalTimePlayed) {
		long minutes = totalTimePlayed / (60 * 1000);
		long seconds = (totalTimePlayed % (60 * 1000)) / 1000;
		return String.format("%02d:%02d", minutes, seconds);
	}

	/**
	 * 
	 * Gère le profil du joueur en fonction du type de case et renvoie le résultat
	 * correspondant.
	 * 
	 * @param caseType Le type de case correspondant au profil du joueur.
	 * @return Le résultat correspondant au profil du joueur.
	 */
	private ResultType handlePlayerProfile(CaseType caseType) {
		switch (caseType) {
		case SAND:
			return ResultType.TOURIST;
		case GRASSLAND:
			return ResultType.FARMER;
		case FOREST:
			return ResultType.LUMBERJACK;
		case ROCK:
			return ResultType.MINER;
		default:
			return ResultType.NONE;
		}
	}

	/**
	 * Méthode appelée par la vue quand l'utilisateur souhaite retourner au menu
	 * principal.
	 */
	public void onGoToMain() {
		// TODO : retourner au menu principal
		view.goTo(ViewNames.MAIN_MENU);
	}
}
