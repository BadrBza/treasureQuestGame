package treasurequest.domains;

/**
 *
 * Représente un indice pour la case la plus proche contenant un trésor.
 */
class Indice {

	private double distance;
	private int value;

	/**
	 *
	 * Constructeur par défaut.
	 */
	public Indice() {
	}

	/**
	 *
	 * Constructeur prenant en paramètre la distance et la valeur du trésor le plus
	 * proche.
	 *
	 * @param distance la distance entre la case et le trésor le plus proche.
	 * @param value    la valeur du trésor le plus proche.
	 */
	public Indice(double distance, int value) {
		this.distance = distance;
		this.value = value;
	}

	/**
	 *
	 * Getter pour la distance entre la case et le trésor le plus proche.
	 *
	 * @return la distance entre la case et le trésor le plus proche.
	 */
	public double getDistance() {
		return distance;
	}

	/**
	 *
	 * Setter pour la distance entre la case et le trésor le plus proche.
	 *
	 * @param distance la distance entre la case et le trésor le plus proche.
	 */
	public void setDistance(double distance) {
		this.distance = distance;
	}

	/**
	 *
	 * Getter pour la valeur du trésor le plus proche.
	 *
	 * @return la valeur du trésor le plus proche.
	 */
	public int getValue() {
		return value;
	}

	/**
	 *
	 * Setter pour la valeur du trésor le plus proche.
	 *
	 * @param value la valeur du trésor le plus proche.
	 */
	public void setValue(int value) {
		this.value = value;
	}
	
	
	/**
	 *
	 * permet de verifier si la valeur n'est pas a 0
	 *
	 * @param la valeur
	 * @param retourne 0 si la valeur est a 0 sinon la valeur en entrée
	 */
	public int verifValue(int value) {
	    if (value == 0) {
	        return 0;
	    } else {
	        return this.value;
	    }
	}
	
	/**
	 * Calcule le rapport entre la valeur du trésor le plus proche et sa distance.
	 *
	 * @return Le rapport entre la valeur du trésor et la distance.
	 */
	public double calculateTreasureRatio() {
	    if (distance != 0) {
	        return value / distance;
	    } else {
	        return Double.POSITIVE_INFINITY;
	    }
	}


	/**
	 * Fusionne cet objet Indice avec un autre objet Indice.
	 *
	 * @param other L'autre objet Indice à fusionner.
	 * @return Un nouvel objet Indice résultant de la fusion.
	 */
	public Indice mergeWith(Indice other) {
	    double mergedDistance = this.distance + other.distance;
	    int mergedValue = this.value + other.value;
	    return new Indice(mergedDistance, mergedValue);
	}



}