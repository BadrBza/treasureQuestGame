package treasurequest.swing.engine;

import java.util.Objects;

/**
 * Représente une paire de longueurs nommées longueur et hauteur, respectivement.
 * 
 * Les objets de cette classe sont immuables.
 * */
public final class Length2f {
	public static final float EPSILON = 0.0001f;
	
	public static final Length2f ZERO = new Length2f(0, 0);
	
	private final float width;
	private final float height;

	/**
	 * Construit une paire {@code (width; height)}.
	 * 
	 * Si un des paramètres est négatif, sa valeur absolue est prise.
	 * */
	public Length2f(float width, float height) {
		this.width = Math.abs(width);
		this.height = Math.abs(height);
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}
	
	/**
	 * Retourne la dimension {@code (width*scalar, height*scalar)}.
	 * 
	 * Retourne la dimension {@code (0; 0)} si le {@code |scalar| <= EPSILON}.
	 * Retourne cette dimension si {@code |scalar - 1.0| <= EPSILON}.
	 * */
	public Length2f mult(float scalar) {
		if(Math.abs(scalar) <= EPSILON) {
			return ZERO;
		} else if(Math.abs(scalar - 1.0f) <= EPSILON){
			return this;
		} else {
			return new Length2f(width*scalar, height*scalar);
		}
	}

	/**
	 * Retourne {@code true} si {@code this.width == that.width && this.height == that.height} 
	 * avec la marge d'erreur {@code EPSILON}.
	 * */
	public boolean equalTo(Length2f that) {
		return Math.abs(this.width - that.width) <= EPSILON*2 &&
				Math.abs(this.height - that.height) <= EPSILON*2;
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(height, width);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Length2f))
			return false;
		Length2f that = (Length2f) obj;
		return this.equalTo(that);
	}

	@Override
	public String toString() {
		return String.format("Dimension2f(width:%10.4f, height:%10.4f)", width, height);
	}
	
	
}
