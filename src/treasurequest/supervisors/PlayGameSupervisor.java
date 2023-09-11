package treasurequest.supervisors;

import treasurequest.domains.Case;
import treasurequest.domains.CaseType;
import treasurequest.domains.Coordinate;
import treasurequest.domains.GameData;
import treasurequest.domains.RetrieveTreasureQuestGame;
import treasurequest.domains.TreasureQuestGame;
import treasurequest.supervisors.views.PlayGameView;
import treasurequest.supervisors.views.SpriteType;
import treasurequest.supervisors.views.TileType;
import treasurequest.supervisors.views.ViewNames;

/**
 * Réagit aux événements utilisateurs de sa vue en mettant à jour une partie en
 * cours et fournit à sa vue les données à afficher.
 */
public class PlayGameSupervisor {

	private PlayGameView view;
	private TreasureQuestGame game;
	private final RetrieveTreasureQuestGame gameFactory;
	private final GameData gameData;

	/**
	 * Crée une instance de PlayGameSupervisor avec une factory de
	 * TreasureQuestGame.
	 *
	 * @param gameFactory La factory de TreasureQuestGame utilisée pour créer les
	 *                    instances de jeu.
	 */
	public PlayGameSupervisor(RetrieveTreasureQuestGame gameFactory, GameData gameData) {
		this.gameFactory = gameFactory;
		this.gameData = gameData;
	}

	/**
	 * Définit la vue de ce superviseur à {@code view}.
	 */
	public void setView(PlayGameView view) {
		if (view == null) {
			return;
		}

		this.view = view;
	}

	/**
	 * Méthode appelée juste avant que la vue de ce superviseur soit affichée à
	 * l'écran.
	 * 
	 * Le superviseur affiche les données de départ du jeu (cout de la case active,
	 * nombre de trésors, bourse du joueur, etc.). Il fait également les cases et
	 * indique quelle case est active.
	 */
	// Postconditions :
	// Player : le joueur doit avoir un nombre de pièces de monnaie initial
	// supérieur ou égal à 0.
	// CaseMap : la carte doit avoir été initialisée avec un tableau 2D de
	// caractères, et chaque case doit avoir été initialisée avec un type valide
	// (terre, eau ou roche,ect..).
	// Case : représente un type qui décrit les caractéristiques d'une case sur une
	// carte, telles que son type (eau, sable, etc.),
	// sa valeur de trésor et son état (creusé ou non)

	public void onEnter(String fromView) {
		if (ViewNames.MAIN_MENU.equals(fromView)) {
			this.game = gameFactory.getGame();
           view.clearTiles();
			gameData.startTime();

			Coordinate axeXaxeY = game.getMiddleCoordinate();
			CaseType valueCoordinate = game.getCaseTypeAtcoordinate(axeXaxeY);

			// TODO : faire le rendu initial de l'écran de jeu

			viewSet(game, game, axeXaxeY, valueCoordinate);

		}
	}

	private void viewSet(TreasureQuestGame game, TreasureQuestGame casemap, Coordinate axeXaxeY,
			CaseType valueCoordinate) {
		for (Coordinate coord : game) {
			CaseType casetype = game.getCaseTypeAtcoordinate(coord);
			TileType tiletype = toTileType(casetype);
			view.setTileAt(tiletype, coord.getX(), coord.getY());
		}

		view.setActiveCase(axeXaxeY.getX(), axeXaxeY.getY());
		view.setActiveCaseCost("Cout de la case active :" + valueCoordinate.getPriceCoins());
		view.setActiveCaseType("Type de la classe d'action :" + valueCoordinate);
		view.setPlayerCoins("Bourse du joueur :" + game.getPlayerCoins());
		view.setTreasuresCount("Trésor restant :" + Math.round(casemap.getDiggableCaseCount() / 10));
	}

	/**
	 * Convertit un objet CaseType en un objet TileType correspondant.
	 *
	 * @return L'objet TileType correspondant à cet objet CaseType.
	 */
	public TileType toTileType(CaseType casetype) {
		return TileType.valueOf(casetype.name());

	}

	/**
	 * Tente de déplacer la case active de {@code deltaRow} lignes et de
	 * {@code deltaRow} colonnes.
	 * 
	 * Cette méthode doit vérifier que les coordonnées calculées correspondent bien
	 * à une case du terrain.
	 */
	public void onMove(int deltaRow, int deltaCol) {
		// Récupération de la coordonnée active
		Coordinate activeCoordinate = game.getCoordinateActive();
		int activeRow = activeCoordinate.getX();
		int activeCol = activeCoordinate.getY();

		// Calcul de la nouvelle coordonnée active
		int newRow = activeRow + deltaRow;
		int newCol = activeCol + deltaCol;

		// Vérification que la nouvelle coordonnée est valide
		updateActiveCaseAndView(newRow, newCol);
	}

	/**
	 * Met à jour la case active et la vue en fonction des nouvelles coordonnées
	 * spécifiées.
	 *
	 * @param newRow La nouvelle valeur de ligne pour la case active.
	 * @param newCol La nouvelle valeur de colonne pour la case active.
	 */
	private void updateActiveCaseAndView(int newRow, int newCol) {
		if (game.isValidCoordinate(newRow, newCol)) {
			game.setActiveCoordinate(newRow, newCol);

			game.updateActiveCase();

			CaseType caseType = game.getCaseTypeAtCoordinate(newRow, newCol);
			int caseCost = caseType.getPriceCoins();

			// Mise à jour de la vue avec la nouvelle case active et ses informations
			view.setActiveCase(newRow, newCol);
			view.setActiveCaseCost("Coût de la case active : " + caseCost);
			view.setActiveCaseType("Type de la case active : " + caseType);
		}
	}

	/**
	 * Tente de creuser la case active et met à jour l'affichage en conséquence.
	 * 
	 * Ne fais rien si la case active a déjà été creusee ou si elle est de type Eau.
	 * 
	 * Gain minimum d'un creusage : Le gain minimum correspond à la valeur minimale
	 * possible d'un trésor, soit 10, moins le coût de creusage de la case. Ainsi,
	 * la bourse du joueur doit augmenter d'au moins ce montant minimum lorsqu'il
	 * creuse une case contenant un trésor.
	 * 
	 * Gain maximum d'un creusage : Le gain maximum correspond à la valeur maximale
	 * possible d'un trésor, soit 20. moins le coût de creusage de la case. Ainsi,
	 * la bourse du joueur doit augmenter d'au moins ce montant maximum lorsqu'il
	 * creuse une case contenant un trésor.
	 * 
	 * chaque case a un cout specifique : SAND = 1, GRASSLAND = 2, FOREST = 3, ROCK= 5
	 * 
	 */

	public void onDig() {
	    Coordinate activeCoordinate = game.getCoordinateActive();
	    Case activeCase = game.getCoordinateValue(activeCoordinate);

	    boolean canDig = game.canDig(activeCase);
	    if (!canDig) {
	        return;
	    }
       
	    // Creuser la case active
	    activeCase.dig();
	   

	    gameData.incrementExpenses(activeCase.getType().getPriceCoins());
	    

	    updateViewOnDig(activeCoordinate, activeCase);
	    
	    

	    toGameOver();

	    view.setTreasuresCount("Trésors restants : " + game.getTreasureCount());
	    view.setPlayerCoins("Bourse du joueur : " + game.getPlayerCoins());
	}


	private void toGameOver() {
		if (game.isGameOver() || game.getPlayerCoins() == 0) {
			gameData.endGameTime();
			view.goTo(ViewNames.GAME_OVER);
			return;
		}
	}

	/**
	 * Met à jour la vue après une action de creusage sur la case active.
	 *
	 * @param activeCoordinate Les coordonnées de la case active.
	 * @param activeCase       La case active.
	 */
	private void updateViewOnDig(Coordinate activeCoordinate, Case activeCase) {
		int activeCaseCost = activeCase.getType().getPriceCoins();
		game.deductCoins(activeCaseCost);
		game.addCoins(activeCase.hasTreasure() ? activeCase.getTreasureValue() : 0);

		if (activeCase.hasTreasure()) {
			game.decrementDiggableCaseCount();
			gameData.incrementEarnings(activeCase.getTreasureValue());
			// int totalEarning = game.getTotalEarnings();
			view.setSpriteAt(SpriteType.TREASURE, activeCoordinate.getX(), activeCoordinate.getY());
		} else {
			view.setSpriteAt(SpriteType.DUG, activeCoordinate.getX(), activeCoordinate.getY());
			activeCase.setIndice(game.generateIndice(game.getCoordinateActive()));
			Coordinate nearestTreasure = game.getNearestTreasure(activeCoordinate);
			nearestTreasureNull(activeCoordinate, nearestTreasure);
		}
	}

	/**
	 * Met à jour la vue si la coordonnée du trésor le plus proche n'est pas nulle.
	 *
	 * @param activeCoordinate Les coordonnées de la case active.
	 * @param nearestTreasure  Les coordonnées du trésor le plus proche.
	 */
	private void nearestTreasureNull(Coordinate activeCoordinate, Coordinate nearestTreasure) {
		if (nearestTreasure != null) {
			SpriteType direction = spriteDirection(nearestTreasure, activeCoordinate);
			view.setSpriteAt(direction, activeCoordinate.getX(), activeCoordinate.getY());
		}
	}

	/**
	 * Détermine le type de sprite indiquant la direction entre deux coordonnées.
	 *
	 * @param from  La première coordonnée.
	 * @param until La deuxième coordonnée.
	 * @return Le type de sprite indiquant la direction entre les deux coordonnées.
	 */
	public SpriteType spriteDirection(Coordinate from, Coordinate until) {
		int deltaX = from.getX() - until.getX();
		int deltaY = from.getY() - until.getY();

		SpriteType horizontal = deltaX < 0 ? SpriteType.NORTH : deltaX > 0 ? SpriteType.SOUTH : SpriteType.NONE;
		SpriteType vertical = deltaY < 0 ? SpriteType.WEST : deltaY > 0 ? SpriteType.EAST : SpriteType.NONE;

		if (horizontal == SpriteType.NONE)
			return vertical;
		if (vertical == SpriteType.NONE)
			return horizontal;

		return SpriteType.valueOf(horizontal.name() + "_" + vertical.name());
	}

	/**
	 * Méthode appelée par la vue quand l'utilisateur souhaite interrompre la
	 * partie.
	 * 
	 * Ce superviseur demande à sa vue de naviguer vers le menu principal.
	 */
	public void onStop() {
		gameData.endGameTime();
		view.goTo(ViewNames.MAIN_MENU);

	}

}
