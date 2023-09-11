package treasurequest.domains;

/**
 * Enumère les types de résultat disponibles.
 * */
public enum EnumProfile {
	GAIN("Gains"), LOSS("Pertes"), DURATION("Bilan"), LUMBERJACK("Bucheron"), MINER("Mineur"), FARMER("Fermier"), TOURIST("Touriste"), NONE("Inconnu");

	private String title;

	EnumProfile(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}
}
