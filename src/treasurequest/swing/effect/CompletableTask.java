package treasurequest.swing.effect;

/**
 * Représente une tâche que l'on peut annuler.
 * 
 * L'exécution de la tâche se fait pas-à-pas en invoquant update() tant que la tache n'est pas terminée ({@code isDone() == false}).
 * */
public interface CompletableTask {
	/**
	 * Demande à la tâche d'avancer dans son traitement.
	 * */
	void update();
	
	/**
	 * Retourne {@code true} si la tâche est terminée.
	 * 
	 * */
	boolean isDone();
	
}
