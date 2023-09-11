package treasurequest.domains;

/**
 * cette interface GameData comporte les méthodes pour les statistiques en
 * rapport avec le temps,les dépenses et les gains.
 * 
 * @author badr
 *
 */
public interface GameData {
	/**
	 * Retourne le temps total de jeu en millisecondes.
	 *
	 * @return Le temps total de jeu en millisecondes.
	 */
	long getTotalTimePlayed();

	/**
	 * Retourne les gains totaux du joueur.
	 *
	 * @return Les gains totaux du joueur.
	 */
	int getTotalEarnings();

	/**
	 * Retourne les dépenses totales du joueur.
	 *
	 * @return Les dépenses totales du joueur.
	 */
	int getTotalExpenses();

	/**
	 * Incrémente les dépenses du joueur de la quantité spécifiée.
	 *
	 * @param expenses La quantité à ajouter aux dépenses du joueur.
	 */
	public void incrementExpenses(int expenses);

	/**
	 * Incrémente les gains du joueur de la quantité spécifiée.
	 *
	 * @param earnings La quantité à ajouter aux gains du joueur.
	 */
	public void incrementEarnings(int earnings);

	/**
	 * Incrémente le temps total de jeu de la quantité spécifiée.
	 *
	 * @param time Le temps à ajouter au temps total de jeu.
	 */
	public void incrementTimePlayed(long time);

	/**
	 * Enregistre la fin du jeu en arrêtant le chronomètre et met à jour le temps
	 * total de jeu.
	 */
	public void endGameTime();

	/**
	 * Démarre le chronomètre du jeu.
	 */
	public void startTime();

}
