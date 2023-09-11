package treasurequest.domains;

/**
 * Représente un joueur participant au jeu Treasure Quest. Cette classe contient
 * le solde des pièces d'un joueur et les méthodes pour gérer ce solde.
 * 
 * @author badr
 */

public class Player  {
	private int coins;


	/**
	 * Constructeur de la classe Player.
	 *
	 * @param initialCoins Le solde initial de pièces pour le joueur.
	 */
	public Player(int initialCoins) {
		this.coins = initialCoins;
	}

	/**
	 * Retourne le solde de pièces du joueur.
	 *
	 * @return Le solde de pièces du joueur.
	 */
	public int getCoins() {
		return coins;
	}

	/**
	 * Définit le solde de pièces du joueur.
	 *
	 * @param coins Le nouveau solde de pièces du joueur.
	 */
	public void setCoins(int coins) {
		this.coins = coins;
	}

	/**
	 * Vérifie si le joueur peut creuser une case.
	 *
	 * @return true si le joueur a suffisamment de pièces pour creuser, false sinon.
	 */
	public boolean canDig() {
		return coins > 0;
	}

	/**
	 * Ajoute un montant de pièces au solde du joueur.
	 *
	 * @param coinsToAdd Le montant de pièces à ajouter au solde du joueur.
	 */
	public void addCoins(int coinsToAdd) {
		this.coins += coinsToAdd;
	}

	/**
	 * Soustrait un montant de pièces du solde du joueur.
	 *
	 * @param coinsToSubtract Le montant de pièces à soustraire du solde du joueur.
	 */
	public void subtractCoins(int coinsToSubtract) {
		this.coins -= coinsToSubtract;
	}

	
}
