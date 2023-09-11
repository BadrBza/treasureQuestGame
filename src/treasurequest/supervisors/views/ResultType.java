package treasurequest.supervisors.views;

/**
 * Enumère les types de résultat disponibles.
 * */
public enum ResultType {
	GAIN("Gains"), LOSS("Pertes"), DURATION("Bilan"), LUMBERJACK("Bucheron"), MINER("Mineur"), FARMER("Fermier"), TOURIST("Touriste"), NONE("Inconnu");

	private String title;

	ResultType(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}
}
