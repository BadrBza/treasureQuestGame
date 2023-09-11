package treasurequest.domains;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * La classe ProfilePlayer représente le profil d'un joueur dans le jeu Treasure
 * Quest. Elle garde une trace des cases visitées, compte les occurrences de
 * chaque type de case et détermine le type de case dominant.
 * 
 * 
 * 
 * Dans la classe ProfilePlayer, j'ai choisi d'utiliser une HashMap pour
 * représenter les cases déjà visitées. Voici la justification de ce choix :
 * 
 * Choix de l'interface de collection : J'ai choisi l'interface Map pour
 * représenter les cases visitées car elle fournit une structure de données
 * adaptée pour stocker des éléments avec des clés associées. Dans notre cas,
 * les coordonnées (Coordinate) servent de clés pour accéder aux cases
 * correspondantes.
 * 
 * Pour implémenter l'algorithme de détermination de la plus grande zone, nous
 * avons besoin des opérations suivantes :
 * 
 * Accéder à un élément précis : Nous devons pouvoir accéder rapidement à une
 * case spécifique en utilisant sa coordonnée. L'interface Map nous permet
 * d'accéder à une case en fournissant sa clé (la coordonnée) et offre une
 * complexité d'accès en moyenne de O(1).
 * 
 * Ajouter des éléments : Nous devons pouvoir ajouter des cases visitées à
 * mesure que le joueur explore la carte. L'interface Map offre la possibilité
 * d'ajouter des paires clé-valeur, ce qui nous permet d'associer une coordonnée
 * à une case. L'opération d'ajout a une complexité moyenne de O(1) pour une
 * HashMap.
 * 
 * Supprimer des éléments : Lorsque nous explorons une case et que nous l'avons
 * prise en compte, nous devons la supprimer de la collection des cases
 * visitées. L'interface Map fournit des opérations efficaces pour supprimer un
 * élément par sa clé. La complexité de la suppression est en moyenne de O(1)
 * pour une HashMap.
 * 
 * En utilisant l'interface Map, nous avons accès à des opérations efficaces
 * pour les cas d'accès, d'ajout et de suppression, qui sont toutes nécessaires
 * pour implémenter l'algorithme de détermination de la plus grande zone.
 * 
 * Choix de l'implémentation de collection : J'ai choisi d'utiliser
 * l'implémentation HashMap pour les cases visitées. Voici la justification :
 * 
 * Complexité des principales opérations : La complexité des opérations de base
 * (accès, ajout, suppression) dans une HashMap est généralement considérée
 * comme O(1) en moyenne, ce qui les rend très efficaces pour les grandes
 * quantités de données. Cela est dû à l'utilisation de la fonction de hachage
 * interne qui permet d'accéder rapidement aux éléments.
 * 
 * Gestion des clés : Dans notre cas, les coordonnées (Coordinate) servent de
 * clés pour accéder aux cases visitées. L'implémentation HashMap permet une
 * recherche rapide des éléments en utilisant les clés, ce qui est essentiel
 * pour déterminer le type de case dominant et calculer la taille des zones
 * adjacentes.
 * 
 * Pas de nécessité d'un ordre spécifique : Dans le contexte de l'algorithme de
 * détermination de la plus grande zone, il n'est pas nécessaire de maintenir un
 * ordre spécifique pour les cases visitées. Par conséquent, une implémentation
 * non triée comme HashMap convient parfaitement.
 * 
 * En conclusion, j'ai choisi d'utiliser une HashMap pour représenter les cases
 * visitées dans la classe ProfilePlayer. Cela nous permet d'accéder rapidement
 * aux cases en utilisant leurs coordonnées et d'effectuer des opérations
 * d'ajout et de suppression
 * 
 * @author badr
 */
public class ProfilePlayer {
	private final Map<Coordinate, Case> visitedCases = new HashMap<>();
	private final Map<CaseType, Integer> caseTypeCount = new HashMap<>();
	private CaseType dominantCaseType;
	private int dominantZoneSize = 0;

	/**
	 * 
	 * Construit un objet ProfilePlayer avec les cases visitées spécifiées.
	 * 
	 * @param visitedCases Les cases visitées.
	 */
	public ProfilePlayer(Map<Coordinate, Case> visitedCases) {
		this.visitedCases.putAll(visitedCases);
	}

	/**
	 * 
	 * Détermine le type de case dominant en se basant sur les cases visitées.
	 * 
	 * @return Le type de case dominant.
	 */
	public CaseType determineDominantCaseType() {
		Map<Coordinate, Case> visitedCase = new HashMap<>(visitedCases);

		for (Map.Entry<Coordinate, Case> entry : visitedCase.entrySet()) {
			int currentZoneSize = exploreAndCountZone(entry.getKey(), entry.getValue().getType());
			caseTypeCount.put(entry.getValue().getType(),
					caseTypeCount.getOrDefault(entry.getValue().getType(), 0) + currentZoneSize);

			if (caseTypeCount.get(entry.getValue().getType()) > dominantZoneSize) {
				dominantCaseType = entry.getValue().getType();
				dominantZoneSize = caseTypeCount.get(entry.getValue().getType());
			}
		}
		return dominantCaseType;
	}

	/**
	 * 
	 * 
	 * 
	 * -Accès à la HashMap visitedCases (complexité : O(1)) : La méthode commence
	 * par accéder à la HashMap visitedCases pour récupérer la case correspondant à
	 * la coordonnée spécifiée. La complexité de cet accès est généralement
	 * considérée comme O(1) en moyenne pour les opérations de lecture dans une
	 * HashMap.
	 * 
	 * -Boucle principale (complexité : O(K)) : La boucle principale itère sur les
	 * coordonnées adjacentes, en utilisant la méthode findAdjacentCoordinates pour
	 * obtenir ces coordonnées. Le nombre d'itérations dépend du nombre moyen de
	 * coordonnées adjacentes, représenté par K. Dans chaque itération, la méthode
	 * récursive exploreAndCountZone est appelée pour explorer les coordonnées
	 * adjacentes. La complexité de cette boucle principale est donc de O(K).
	 * 
	 * -Appels récursifs (complexité : dépend de la structure de l'ensemble de
	 * coordonnées visitées) : À chaque itération de la boucle principale, la
	 * méthode exploreAndCountZone est appelée récursivement pour explorer les
	 * coordonnées adjacentes. Le nombre d'appels récursifs dépend du nombre de
	 * coordonnées adjacentes trouvées à chaque étape et de la profondeur maximale
	 * de l'exploration. La complexité globale de cette partie dépend donc de la
	 * structure de l'ensemble de coordonnées visitées et peut varier. Si toutes les
	 * coordonnées sont explorées, la complexité peut être de l'ordre de O(N), où N
	 * est le nombre total de coordonnées visitées.
	 * 
	 * -Boucle for dans findAdjacentCoordinates (complexité : O(N)) : Cette boucle
	 * itère sur les entrées de la HashMap visitedCases pour trouver les coordonnées
	 * adjacentes. Le nombre d'itérations dépend du nombre total de coordonnées
	 * visitées, représenté par N. Ainsi, la complexité de cette boucle est O(N).
	 * 
	 * -En combinant ces éléments, la complexité temporelle finale de la méthode
	 * exploreAndCountZone est approximativement de O(K * M * N), où K est le nombre
	 * moyen de coordonnées adjacentes, M est la profondeur maximale de
	 * l'exploration (dépendant de la structure de l'ensemble de coordonnées
	 * visitées) et N est le nombre total de coordonnées visitées.
	 * 
	 *
	 * Explore récursivement les cases adjacentes à la coordonnée spécifiée et
	 * compte le nombre de cases formant une zone du même type que celui spécifié.
	 * 
	 * @param coordinate La coordonnée à partir de laquelle commencer l'exploration.
	 * @param type       Le type de case à rechercher dans la zone.
	 * @return Le nombre de cases formant la zone du même type que celui spécifié.
	 */
	private int exploreAndCountZone(Coordinate coordinate, CaseType type) {
		Case currentCase = visitedCases.get(coordinate);
		if (currentCase != null && currentCase.getType().equals(type)) {
			visitedCases.remove(coordinate);
			int zoneCount = 1;
			for (Coordinate adjacentCoord : findAdjacentCoordinates(coordinate)) {
				zoneCount += exploreAndCountZone(adjacentCoord, type);
			}
			return zoneCount;
		}
		return 0;
	}

	/**
	 * Trouve et retourne les coordonnées adjacentes à la coordonnée spécifiée.
	 * 
	 * @param coord La coordonnée à partir de laquelle trouver les coordonnées
	 *              adjacentes.
	 * @return Une liste des coordonnées adjacentes.
	 */
	private List<Coordinate> findAdjacentCoordinates(Coordinate coord) {
		List<Coordinate> adjacentCoord = new ArrayList<>();
		for (Map.Entry<Coordinate, Case> entry : visitedCases.entrySet()) {
			Coordinate otherCoord = entry.getKey();
			if (isAdjacent(coord, otherCoord)) {
				adjacentCoord.add(otherCoord);
			}
		}
		return adjacentCoord;
	}

	/**
	 * Vérifie si deux coordonnées sont adjacentes.
	 * 
	 * @param coord1 La première coordonnée.
	 * @param coord2 La deuxième coordonnée.
	 * @return true si les coordonnées sont adjacentes, false sinon.
	 */
	private boolean isAdjacent(Coordinate coord1, Coordinate coord2) {
		int deltaRow = coord1.getX() - coord2.getX();
		int deltaCol = coord1.getY() - coord2.getY();
		double distance = Math.sqrt(deltaRow * deltaRow + deltaCol * deltaCol);
		return distance < 2.0;
	}
}