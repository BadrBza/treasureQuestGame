package treasurequest.domains;


import java.util.Objects;



/**
 * Représente une coordonnée (x, y) dans un système de coordonnées à deux
 * dimensions. Cette classe est utilisée pour déterminer la position des cases
 * dans la carte du jeu Treasure Quest.
 * 
 * @author badr
 */
public class Coordinate {

	private int axeX;
	private int axeY;

	/**
	 * Constructeur de la classe Coordinate.
	 *
	 * @param axeX La valeur de l'axe des abscisses (x).
	 * @param axeY La valeur de l'axe des ordonnées (y).
	 */
	public Coordinate(int axeX, int axeY) {
		this.axeX = axeX;
		this.axeY = axeY;
	}

	/**
	 * Retourne la valeur de l'axe des abscisses (x).
	 *
	 * @return La valeur de l'axe des abscisses (x).
	 */
	public int getX() {
		return axeX;
	}

	/**
	 * Définit la valeur de l'axe des abscisses (x).
	 *
	 * @param axeX La nouvelle valeur de l'axe des abscisses (x).
	 */
	public void setX(int axeX) {
		this.axeX = axeX;
	}

	/**
	 * Retourne la valeur de l'axe des ordonnées (y).
	 *
	 * @return La valeur de l'axe des ordonnées (y).
	 */
	public int getY() {
		return axeY;
	}

	/**
	 * Définit la valeur de l'axe des ordonnées (y).
	 *
	 * @param axeY La nouvelle valeur de l'axe des ordonnées (y).
	 */
	public void setY(int axeY) {
		this.axeY = axeY;
	}

	@Override
	public int hashCode() {
		return Objects.hash(axeX, axeY);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Coordinate)) {
			return false;
		}
		Coordinate other = (Coordinate) obj;
		return axeX == other.axeX && axeY == other.axeY;
	}

	
	
	/**
	 * Calcule la distance entre deux coordonnées en utilisant la formule de distance Euclidienne.
	 *
	 * @param coord1 la première coordonnée.
	 * @param coord2 la deuxième coordonnée.
	 * @return la distance entre les deux coordonnées.
	 */
	public static double distanceCalculateXandY(Coordinate coord1, Coordinate coord2) {
	    int xDiff = coord1.getX() - coord2.getX();
	    int yDiff = coord1.getY() - coord2.getY();

	    return Math.sqrt(xDiff * xDiff + yDiff * yDiff);
	}


	
	/**
	 * Calcule la distance entre la coordonnée actuelle et une autre coordonnée en utilisant la formule de distance Euclidienne.
	 *
	 * @param other la coordonnée avec laquelle calculer la distance.
	 * @return la distance entre la coordonnée actuelle et la coordonnée spécifiée.
	 */
	public double distanceTo(Coordinate other) {
	    int xDiff = other.getX() - this.getX();
	    int yDiff = other.getY() - this.getY();

	    return Math.sqrt(xDiff * xDiff + yDiff * yDiff);
	}


	
	/**
	 * Calcule la direction entre la coordonnée actuelle et une autre coordonnée.
	 *
	 * @param other la coordonnée avec laquelle calculer la direction.
	 * @return la direction entre la coordonnée actuelle et la coordonnée spécifiée.
	 */
	public OrientationSprite calculateDirection(Coordinate other) {
	    int deltaX = other.getX() - this.getX();
	    int deltaY = other.getY() - this.getY();

	    return deltaY < 0 ?
	            (deltaX < 0 ? OrientationSprite.NORTH_WEST : (deltaX == 0 ? OrientationSprite.NORTH : OrientationSprite.NORTH_EAST))
	            : (deltaY == 0 ? (deltaX < 0 ? OrientationSprite.WEST : OrientationSprite.EAST)
	            : (deltaX < 0 ? OrientationSprite.SOUTH_WEST : (deltaX == 0 ? OrientationSprite.SOUTH : OrientationSprite.SOUTH_EAST)));
	}


}
