package treasurequest.domains;
/**
 * cette interface permet de creer le jeu
 * @author badr
 */
public interface CreateTreasureQuestGame {

	/**
	 * permet de creer le jeu
	 */
	public void createNewGame();
	
	/**
	 * permet de creer le jeu avec une map de 16x16 sur base de la BigMap
	 */
	public void createNewGameBigMap();
}
