package treasurequest.domains;

/**
 * Représente les différents types de cases possibles dans le jeu Treasure
 * Quest. Chaque type de case a un coût en pièces associé.
 * 
 * @author badr
 */
public enum CaseType {

	WATER(-1), SAND(1), GRASSLAND(2), FOREST(3), ROCK(5), UNKNOWN(0);

	/**
	 * représente le prix de la case pour la creuser
	 */
	private int priceCoins;

	/**
	 * Constructeur de l'énumération CaseType.
	 *
	 * @param priceCoins Le coût en pièces pour creuser cette case.
	 */
	CaseType(int priceCoins) {
		this.priceCoins = priceCoins;
	}

	/**
	 * Retourne le coût en pièces pour creuser cette case.
	 *
	 * @return Le coût en pièces pour creuser cette case.
	 */
	public int getPriceCoins() {
		return priceCoins;
	}

	/**
	 * Convertit un caractère en un objet CaseType correspondant.
	 *
	 * @param lettre Le caractère à convertir.
	 * @return L'objet CaseType correspondant au caractère donné.
	 */
	static CaseType fromChar(char lettre) {
		switch (lettre) {
		case 'X':
			return WATER;
		case 'S':
			return SAND;
		case 'P':
			return GRASSLAND;
		case 'F':
			return FOREST;
		case 'R':
			return ROCK;
		default:
			return UNKNOWN;
		}
	}

}
