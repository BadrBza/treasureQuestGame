package treasurequest.swing.engine;

/**
 * Réprésente un rectangle dans un espace à 2 dimensions.
 * */
public class Rectangle2f {
	private final Length2f dimension;	
	private Vector2f center;

	/**
	 * Construit un rectangle de centre {@code center} et longueurs {@code dimension}.
	 * */
	public Rectangle2f(Vector2f center, Length2f dimension) {
		this.center = center;
		this.dimension = dimension;
	}
	
	/**
	 * Retourne le côté supérieur gauche de ce rectangle.
	 * */
	public Vector2f getTopLeft() {
		return center.add(getWidth()*-0.5f, getHeight()*-0.5f);
	}
	
	/**
	 * Retourne le côté inférieur droit de ce rectangle.
	 * */
	public Vector2f getBottomRight() {
		return center.add(getWidth()*0.5f, getHeight()*0.5f);
	}
	
	/**
	 * Retourne la base de ce rectangle.
	 * */
	public float getWidth() {
		return dimension.getWidth();
	}
	
	/**
	 * Retourne la longueur de ce rectangle.
	 * */
	public float getHeight() {
		return dimension.getHeight();
	}

	/**
	 * Déplace le centre de ce rectangle de {@code delta}.
	 * */
	public void moveTo(Vector2f target, float factor) {
		var distance = target.subtract(center);
		var normalized = distance.getNormalized();
		this.center = center.add(normalized.mult(factor*distance.getLength()));
	}

	/**
	 * Retourne la coordonnée en X du côté gauche.
	 * */
	public float getLeft() {
		return center.getX() - dimension.getWidth()*0.5f;
	}
	
	/**
	 * Retourne la coordonnée en Y du côté du dessus.
	 * */
	public float getTop() {
		return center.getY() - dimension.getHeight()*0.5f;
	}

	/**
	 * Retourne {@code true} si ce rectangle est de même centre que l'autre rectangle.
	 * 
	 * */
	public boolean hasSameCenter(Rectangle2f other) {
		if(other == null) {
			return false;
		}
		return this.hasSameCenter(other.center);
	}

	/**
	 * Retourne vait si ce rectangle à son centre à la position {@code position)
	 * */
	public boolean hasSameCenter(Vector2f position) {
		if(position == null) {
			return false;
		}
		return this.center.equalTo(position);
	}

	/**
	 * Retourne la coordonnée X du centre de ce rectangle.
	 * */
	public float getCenterX() {
		return center.getX();
		
	}
	
	/**
	 * Retourne la coordonnée Y du centre de ce rectangle.
	 * */
	public float getCenterY() {
		return center.getY();
	}

	public Vector2f getCenter() {
		return center;
	}

}
