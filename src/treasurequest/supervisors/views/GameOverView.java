package treasurequest.supervisors.views;

/**
 * Décrit comment mettre à jour l'écran représentant la fin de partie.
 * */
public interface GameOverView extends View {
	/**
	 * Supprime tous les panneaux affichés par cette vue.
	 * */
	void clearPanels();
	
	/**
	 * Demande à la vue d'afficher un nouveau panneau de type {@code type} contenant {@code message}.
	 * 
	 * La vue peut afficher plusieurs panneaux du même type.
	 * */
	void addPanel(ResultType type, String message);
}
