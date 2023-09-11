package treasurequest.swing.engine;

import java.util.Objects;

/**
 * Représente un vecteur à 2 dimensions dont les composants sont des floats.
 * 
 * Les objets de cette classe sont immuables.
 * */
public final class Vector2f {
	public static final float EPSILON = 0.0001f;
	
	public static final Vector2f ZERO = new Vector2f(0, 0);
	public static final Vector2f X_AXIS = new Vector2f(1, 0);
	public static final Vector2f Y_AXIS = new Vector2f(0, 1);
	
	private final float xComponent;
	private final float yComponent;

	/**
	 * Construit un nouveau vecteur {@code (x; y)}.
	 * */
	public Vector2f(float givenX, float givenY) {
		this.xComponent = givenX;
		this.yComponent = givenY;
	}
	
	/**
	 * Construit un nouveau vecteur en copiant les attributs du vecteur {@code that}.
	 * */
	public Vector2f(Vector2f that) {
		this.xComponent = that.xComponent;
		this.yComponent = that.yComponent;
	}
	
	/**
	 * Retourne la valeur du composant X de ce vecteur.
	 * */
	public float getX() {
		return this.xComponent;
	}
	

	/**
	 * Retourne la valeur du composant Y de ce vecteur.
	 * */
	public float getY() {
		return this.yComponent;
	}

	/**
	 * Retourne la longueur de ce vecteur.
	 * */
	public float getLength() {
		return (float)Math.sqrt(xComponent*xComponent + yComponent*yComponent);
	}
	
	/**
	 * Retourne la longueur de ce vecteur au carée.
	 * */
	public float getSquaredLength() {
		return xComponent*xComponent + yComponent*yComponent;
	}
	
	/**
	 * Retourne le vecteur unitaire dérivé de ce vecteur.
	 * */
	public Vector2f getNormalized() {
		float length = this.getLength();
		if(Float.compare(length, 0.0f) != 0) {
			return new Vector2f(xComponent/length, yComponent/length);
		} else {
			return this;
		}
	}
	
	/**
	 * Retourne le Vector {@code (this.x + that.x; this.y + that.y)}.
	 * 
	 * Retourne ce vecteur si {@code that==null || that == (0; 0)}. 
	 * Retourne {@code that} si {@code that != null && this == (0; 0)}. 
	 * 
	 * */
	public Vector2f add(Vector2f that) {
		if(that == null || that.isZero()) {
			return this;
		} if(this.isZero()) {
			return that;
		} else {
			return new Vector2f(this.xComponent + that.xComponent, this.yComponent + that.yComponent);
		}
	}
	
	/**
	 * Retourne le Vector {@code (this.x + scalar; this.y + scalar)}.
	 * 
	 * Retourne ce vecteur si {@code scalar == 0.0}. 
	 * */
	public Vector2f add(float scalar) {
		if(Math.abs(scalar) <= EPSILON) {
			return this;
		} else {
			return new Vector2f(this.xComponent + scalar, this.yComponent + scalar);
		}
	}
	
	/**
	 * Retourne le Vector {@code (this.x + deltaX; this.y + deltaX)}.
	 * 
	 * Retourne ce vecteur si {@code deltaX == 0.0 && deltaY == 0.0}. 
	 * */
	public Vector2f add(float deltaX, float deltaY) {
		if(Math.abs(deltaX) <= EPSILON && Math.abs(deltaY) <= EPSILON) {
			return this;
		} else {
			return new Vector2f(this.xComponent + deltaX, this.yComponent + deltaY);
		}
	}
	
	/**
	 * Retourne le Vector {@code (this.x - that.x; this.y - that.y)}.
	 * 
	 * Retourne ce vecteur si {@code that==null || that == (0; 0)}.
	 * 
	 * */
	public Vector2f subtract(Vector2f that) {
		if(that == null || that.isZero()) {
			return this;
		} else {
			return new Vector2f(this.xComponent - that.xComponent, this.yComponent - that.yComponent);
		}
	}
	
	/**
	 * Retourne le vecteur {@code (this.x*that.x; this.y*that.y)}.
	 * */
	public Vector2f mult(Vector2f that) {
		if(that == null || that.isZero() || this.isZero()) {
			return ZERO;
		} else {
			return new Vector2f(this.xComponent * that.xComponent, this.yComponent * that.yComponent);
		}
	}
	
	/**
	 * Retourne le vecteur {@code (x*scalar; y*scalar)}.
	 * */
	public Vector2f mult(float scalar) {
		return new Vector2f(xComponent*scalar, yComponent*scalar);
	}
	
	/**
	 * Retourne {@code this.x == 0.0f && this.y==0.0f} avec la marge d'erreur {@code EPSILON}.
	 * */
	public boolean isZero() {
		return Math.abs(xComponent) <= EPSILON && Math.abs(yComponent) <= EPSILON;
	}
	
	/**
	 * Retourne {@code true} si {@code this.x == that.x && this.y == that.y} avec la marge d'erreur {@code EPSILON}.
	 * */
	public boolean equalTo(Vector2f that) {
		if(this == that) {
			return true;
		}
		
		if(that == null) {
			return false;
		}
		
		return Math.abs(xComponent - that.xComponent) <= EPSILON*2 &&
				Math.abs(yComponent - that.yComponent) <= EPSILON*2;
	}

	@Override
	public int hashCode() {
		return Objects.hash(xComponent, yComponent);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Vector2f))
			return false;
		
		return this.equalTo((Vector2f) obj);
	}

	@Override
	public String toString() {
		return String.format("V2(x: %10.4f, y: %10.4f)", xComponent, yComponent);
	}
	
}
