package treasurequest.domains;

/**
 * CTT: La complexité temporelle de la méthode placeTreasures() est de O(KN), où
 * K est le nombre de trésors à placer et N est le nombre de cases de la carte.
 * La méthode itère sur toutes les cases de la carte et vérifie si chaque case
 * est creusable et ne contient pas déjà de trésor, ce qui prend un temps
 * proportionnel à N. Elle place ensuite un nombre aléatoire de trésors
 * (proportionnel à K) sur des cases creusables choisies au hasard, ce qui prend
 * également un temps proportionnel à N. Donc, la complexité totale de la
 * méthode est de O(KN).
 * 
 * @author badr
 */

public class TreasureQuestGameFactory implements CreateTreasureQuestGame, RetrieveTreasureQuestGame {

	private TreasureQuestGame game;

	/**
	 * Crée un nouvelle objet de TreasureQuestGame et lance une nouvelle partie.
	 *
	 * @return Un objet de TreasureQuestGame avec une nouvelle partie lancée.
	 */
	@Override
	public void createNewGame() {
		game = new TreasureQuestGame();
		game.newGame();
	}

	@Override
	public void createNewGameBigMap() {
		game = new TreasureQuestGame();
		game.newGameBigMap();

	}

	/**
	 * Renvoie l'objet TreasureQuestGame associé à cette instance de classe.
	 *
	 * @return L'objet TreasureQuestGame associé à cette instance de classe.
	 */
	@Override
	public TreasureQuestGame getGame() {
		return game;
	}

}
