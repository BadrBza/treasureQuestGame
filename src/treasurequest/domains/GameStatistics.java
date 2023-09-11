package treasurequest.domains;

import java.time.Duration;
import java.time.Instant;

/**
 * La classe GameStatistics implémente l'interface GameData et maintient les
 * statistiques de jeu, telles que le temps total de jeu, les gains totaux et
 * les dépenses totales et le profil du joueurs.
 */
public class GameStatistics implements GameData {
	private long totalTimePlayed;
	private int totalEarnings;
	private int totalExpenses;
	private Instant startTime;
	private Instant endTime;

	/**
	 * Constructeur de la classe GameStatistics. Initialise les statistiques à zéro
	 * et enregistre le temps de début.
	 */
	public GameStatistics() {
		totalTimePlayed = 0;
		totalEarnings = 0;
		totalExpenses = 0;
		startTime = Instant.now();
		endTime = null;

	}

	@Override
	public void incrementTimePlayed(long time) {
		totalTimePlayed += time;
	}

	@Override
	public void incrementEarnings(int earnings) {
		totalEarnings += earnings;
	}

	@Override
	public void incrementExpenses(int expenses) {
		totalExpenses += expenses;
	}

	@Override
	public long getTotalTimePlayed() {
		return totalTimePlayed;
	}

	@Override
	public int getTotalEarnings() {
		return totalEarnings;
	}

	@Override
	public int getTotalExpenses() {
		return totalExpenses;
	}

	@Override
	public void startTime() {
		this.startTime = Instant.now();
		this.endTime = null;

	}

	public Instant getStartTime() {
		return startTime;
	}

	public Instant getEndTime() {
		return endTime;
	}

	@Override
	public void endGameTime() {
		if (this.startTime != null && this.endTime == null) {
			this.endTime = Instant.now();
			Duration duration = Duration.between(startTime, endTime);
			long timePlayed = duration.toMillis();
			totalTimePlayed += timePlayed;
		}
	}

}
