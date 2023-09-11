package treasurequest.supervisors.views;

/**
 * Représente les sprites affichables quand une case est creusée.
 * 
 * Une case creusée peut contenir :
 * <ul>
 * <li>Un creux ({@code DUG})
 * <li>Un trésor ({@code TREASURE})
 * <li>Une orientation parmi les huit points cardinaux ({@code WEST, NORTH_WEST, ..., SOUTH_WEST}). 
 * Cette partie sera détaillée pendant l'itération 2.</li>
 * </ul>
 * 
 * La valeur {@code NONE} est utilisée pour représenter une case non-creusée.
 * */
public enum SpriteType {
	WEST, NORTH_WEST, NORTH, NORTH_EAST, EAST, SOUTH_EAST, SOUTH, SOUTH_WEST, TREASURE, DUG, NONE;
	
	/**
	 * Retourne {@code true} si ce SpriteType n'est pas {@code NONE}.
	 * */
	public boolean isNotNone() {
		return NONE != this;
	}

}
