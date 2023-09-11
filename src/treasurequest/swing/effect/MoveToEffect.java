package treasurequest.swing.effect;

import treasurequest.swing.engine.*;

/**
 * Déplace un objet vers une position cible pendant une durée exprimée en millisecondes.
 * 
 * La mise à jour se fait de façon non-linéaire. Elle est fonction du temps écoulé entre les appels.
 * */
public class MoveToEffect implements CompletableTask {
	private final GameComponent object;
	private final Vector2f target;
	private final long start;
	private long last;
	private final double duration;
	
	/**
	 * Construit un déplacement de l'objet vers la cible pendant une durée exprimée en millisecondes.
	 * 
	 * */
	public MoveToEffect(GameComponent object, Vector2f target, long durationInMillis) {
		this.object = object;
		this.target = target;
		this.duration = (double)durationInMillis;
		this.start = System.currentTimeMillis();
		this.last = start;
	}

	@Override
	public void update() {
		long now = System.currentTimeMillis();
		long elapsed = now - last;
		
		double timeRatio = Math.min(elapsed/duration, 1);
		object.moveTo(target, (float)timeRatio);
		
		last = now;
	}

	@Override
	public boolean isDone() {
		if(last - start > duration) {
			object.moveTo(target, 1);
		}
		
		return last - start > duration || object.isAtPos(target);
	}

}
