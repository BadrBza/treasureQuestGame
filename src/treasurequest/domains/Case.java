package treasurequest.domains;

/**
 * La classe Case représente une case individuelle sur la carte du jeu Treasure
 * Quest. Chaque case a un type (par exemple, terre ou eau), un état (creusée ou
 * non creusée) et une valeur de trésor (0 si aucune trésor, une valeur positive
 * sinon).
 * 
 * @author badr
 */
public class Case {
	private final CaseType type;
	private boolean dug;
	private int treasureValue;
	private boolean visited;
	private OrientationSprite indice;
	private Coordinate coordinate;

	/**
	 * Construit un nouvel objet Case avec le type de case spécifié. Initialise
	 * l'état de la case comme non creusée et la valeur du trésor à 0.
	 *
	 * @param type Le type de case (CaseType) pour cette case.
	 */
	public Case(CaseType type) {
		this.type = type;
		this.dug = false;
		this.treasureValue = 0;
	}

	/**
	 * Récupère le type de case (CaseType) de cette case.
	 *
	 * @return Le type de case (CaseType) de cette case.
	 */
	public CaseType getType() {
		return type;
	}

	/**
	 * Indique si la case a été creusée ou non.
	 *
	 * @return Vrai si la case a été creusée, faux sinon.
	 */
	public boolean isDug() {
		return dug;
	}

	/**
	 * Creuse la case si elle n'est pas de type "eau". Si la case est de type "eau",
	 * la case ne sera pas creusée.
	 */
	public void dig() {
		if (type != CaseType.WATER) {
			this.dug = true;
		}
	}

	/**
	 * Indique si cette case contient un trésor.
	 *
	 * @return Vrai si la case contient un trésor, faux sinon.
	 */
	public boolean hasTreasure() {
		return treasureValue > 0;
	}

	/**
	 * Récupère la valeur du trésor contenu dans la case.
	 *
	 * @return La valeur du trésor dans la case.
	 */
	public int getTreasureValue() {
		return treasureValue;
	}

	/**
	 * Définit la valeur du trésor pour cette case, si le type de case n'est pas de
	 * l'eau.
	 *
	 * @param treasureValue La valeur du trésor à définir pour cette case.
	 */
	public void setTreasureValue(int treasureValue) {
		if (type != CaseType.WATER) {
			this.treasureValue = treasureValue;
		}
	}

	/**
	 * renvoie true si la case est visité
	 * 
	 * @return visite = true
	 */
	public boolean isVisited() {
		return visited;
	}

	/**
	 * 
	 * Modifie l'état de la case visitée.
	 * 
	 * @param visited La nouvelle valeur de l'état visité de la case.
	 */
	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	
	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	/**
	 * Définit l'indice de sprite pour la case.
	 *
	 * @param indice l'indice de sprite à définir.
	 */
	public void setIndice(OrientationSprite indice) {
	    this.indice = indice;
	}
	


	/**
	 * Renvoie la coordonnée de la case.
	 * 
	 * @return La coordonnée de la case.
	 */
	public Coordinate getCoordinate() {
		return this.coordinate;
	}
	
	
	


}
