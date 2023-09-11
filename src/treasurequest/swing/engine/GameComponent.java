package treasurequest.swing.engine;

import java.awt.Graphics2D;

/**
 * Représente un élément de scène à dessiner.
 * 
 * Cet élément est délimité par un rectangle auquel l'objet délègue la plupart de ses appels.
 * 
 * La méthode {@code repaint(Graphics2D)} est à définir au niveau des classes descendantes.
 * */
public abstract class GameComponent {
	private final Rectangle2f bounds;
	
	/**
	 * Construit un élément de jeu de position {@code (left + width*0.5; top + height*0.5)} et de dimension {@code (width; height)}.
	 * */
	public GameComponent(int left, int top, int width, int height) {
		bounds = new Rectangle2f(
				new Vector2f(left + width*0.5f, top + height*0.5f), 
				new Length2f(width, height));
	}
	
	/**
	 * Retourne la coordonnée en X du côté gauche.
	 * */
	public final int getLeft() {
		return (int)bounds.getLeft();
	}
	
	/**
	 * Retourne la distance entre le côté gauche et celui de droite.
	 * */
	public final int getWidth() {
		return (int)bounds.getWidth();
	}
	
	/**
	 * Retourne la coordonnée en Y du côté haut.
	 * */
	public final int getTop() {
		return (int)bounds.getTop();
	}
	
	/**
	 * Retourne la distance entre côté haut et le côté bas.
	 * */
	public final int getHeight() {
		return (int)bounds.getHeight();
	}
	
	/**
	 * Retourne la position de cet objet.
	 * 
	 * */
	public Vector2f getPosition() {
		return bounds.getCenter();
	}
	
	/**
	 * Retourne la position en X de cet objet.
	 * 
	 * La position correspond au point au centre du rectangle délimitant cet objet.
	 * */
	public int getPosX() {
		return (int)bounds.getCenterX();
	}
	
	/**
	 * Retourne la position en Y de cet objet.
	 * 
	 * La position correspond au point au centre du rectangle délimitant cet objet.
	 * */
	public int getPosY() {
		return (int)bounds.getCenterY();
	}
	
	/**
	 * Retourne {@code true} si la position de cet objet est égal à celle de {@code l'autre}.
	 * */
	public boolean isAtSamePosAs(GameComponent other) {
		if(other == null) {
			return false;
		}
		return  bounds.hasSameCenter(other.bounds);
	}

	/**
	 * Retourne {@code true} si cet objet occupe la position {@code target}.
	 * */
	public boolean isAtPos(Vector2f target) {
		return  bounds.hasSameCenter(target);
		
	}
	
	/**
	 * Déplace cet objet vers le point {@code newPos} de {@code factor*D} où D est la distance séparant newPos de la position de cet objet.
	 * 
	 * Quand {@code factor == 1.0}, le déplacement est instantané. Quand {@code factor in [0; 1.0]} le déplacement correspond à une fraction de cette distance.
	 * */
	public void moveTo(Vector2f newPos, float factor) {
		bounds.moveTo(newPos, factor);
	}
	
	
	/**
	 * Décrit au peintre comment dessiner cet objet.
	 * */
	public abstract void repaint(Graphics2D painter);
}
