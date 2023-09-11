package treasurequest.domains;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * La classe CaseMap représente la carte du jeu Treasure Quest. Elle stocke les
 * informations sur les cases et leur position, organisées par coordonnées. La
 * classe gère également la taille de la carte en termes de nombre de lignes et
 * de colonnes.
 * 
 * J'ai choisi d'utiliser une Map pour représenter la carte du jeu Treasure
 * Quest dans la classe CaseMap. La clé de la Map est un objet Coordinate
 * représentant les coordonnées d'une case, et la valeur associée est un objet
 * Case représentant la case elle-même. J'ai choisi cette structure de données
 * car elle permet d'accéder facilement et rapidement à une case spécifique en
 * utilisant ses coordonnées comme clé. De plus, elle permet également de
 * facilement parcourir toutes les cases de la carte en itérant sur les entrées
 * de la Map. Pour l'implémentation de la Map, j'ai choisi d'utiliser une
 * HashMap. Cette implémentation permet des temps d'accès et de recherche en
 * O(1) en moyenne, et est donc très efficace pour accéder à des éléments précis
 * dans la carte. De plus, elle ne maintient pas l'ordre d'insertion, ce qui
 * n'est pas nécessaire pour la représentation de la carte.
 * 
 * @author badr
 *
 */

public class CaseMap implements Iterable<Coordinate> {
	private final Map<Coordinate, Case> caseMap;
	private int row;
	private int col;

	/**
	 * Constructeur par défaut pour la classe CaseMap. Il initialise une nouvelle
	 * instance de la classe avec une carte vide et une taille de 0 pour les lignes
	 * et les colonnes.
	 */
	public CaseMap() {
		this.caseMap = new HashMap<>();
		row = 0;
		col = 0;
	}

	/**
	 * Crée et remplit la carte à partir d'un tableau 2D de caractères représentant
	 * les types de cases.
	 *
	 * @param mapArray Le tableau 2D de caractères représentant la carte.
	 */
	public void createFromCharArray(char[][] mapArray) {
		row = mapArray.length;
		col = mapArray[0].length;

		for (int y = 0; y < row; y++) {
			for (int x = 0; x < col; x++) {
				CaseType caseType = CaseType.fromChar(mapArray[y][x]);
				caseMap.put(new Coordinate(y, x), new Case(caseType));
			}
		}
	}

	/**
	 * Retourne la largeur de la carte (nombre de colonnes).
	 *
	 * @return La largeur de la carte en nombre de colonnes.
	 */
	public int getWidth() {
		return row;
	}

	/**
	 * Retourne la hauteur de la carte (nombre de lignes).
	 *
	 * @return La hauteur de la carte en nombre de lignes.
	 */
	public int getHeight() {
		return col;
	}

	/**
	 * Calcule et retourne le nombre de cases creusables (non-eau) présentes sur la
	 * carte.
	 *
	 * @return Le nombre de cases creusables sur la carte.
	 */
	public int getDiggableCaseCount() {
		int count = 0;
		for (Case c : caseMap.values()) {
			if (c.getType() != CaseType.WATER) {
				count++;
			}
		}
		return count;
	}

	/**
	 * Récupère la valeur de la case située aux coordonnées spécifiées.
	 *
	 * @param coordinate Les coordonnées de la case à récupérer.
	 * @return La case correspondant aux coordonnées spécifiées, ou null si aucune
	 *         case ne correspond.
	 */
	public Case getCoordinateValue(Coordinate coordinate) {
		return caseMap.get(coordinate);
	}

	/**
	 * Place un trésor d'une valeur spécifiée aléatoirement sur une case creusable
	 * de la carte.
	 *
	 * @param treasureValue La valeur du trésor à placer sur une case creusable.
	 */

	public void placeTreasureRandomly(int treasureValue) {
		List<Coordinate> digCoordinates = new ArrayList<>();
		for (Map.Entry<Coordinate, Case> entry : caseMap.entrySet()) {
			if (entry.getValue().getType() != CaseType.WATER && !entry.getValue().hasTreasure()) {
				digCoordinates.add(entry.getKey());
			}
		}

		Random random = new Random();
		Coordinate tresorCoordinate = digCoordinates.get(random.nextInt(digCoordinates.size()));
		Case treasureCase = caseMap.get(tresorCoordinate);
		treasureCase.setTreasureValue(treasureValue);
	}

	/**
	 * Retourne les coordonnées du point central de la carte.
	 *
	 * @return Les coordonnées du point central de la carte.
	 */
	public Coordinate getMiddleCoordinate() {
		int middleX = row / 2;
		int middleY = col / 2;

		return new Coordinate(middleX, middleY);

	}

	/**
	 * 
	 * Retourne un itérateur pour parcourir toutes les coordonnées de la carte du
	 * jeu.
	 * 
	 * @return Un itérateur pour parcourir toutes les coordonnées de la carte du
	 *         jeu.
	 */
	@Override
	public Iterator<Coordinate> iterator() {
		return caseMap.keySet().iterator();
	}

	/**
	 * Récupère le type de case à la coordonnée spécifiée.
	 *
	 * @param coordinate la coordonnée de la case.
	 * @return le type de case à la coordonnée spécifiée.
	 */
	public CaseType getCaseTypeAtcoordinate(Coordinate coordinate) {
		Case casecoord = getCoordinateValue(coordinate);
		return casecoord.getType();
	}

	/**
	 * Génère l'indice d'orientation le plus proche pour la coordonnée spécifiée.
	 *
	 * @param coordinate la coordonnée pour laquelle générer l'indice.
	 * @return l'indice d'orientation le plus proche pour la coordonnée spécifiée.
	 */
	public OrientationSprite generateIndice(Coordinate coordinate) {
		OrientationSprite closestDirection = null;
		double closestDistance = Double.MAX_VALUE;

		for (Coordinate other : this) {
			Case otherCase = getCoordinateValue(other);

			if (otherCase.hasTreasure()) {
				double distance = coordinate.distanceTo(other);

				if (distance < closestDistance) {
					closestDistance = distance;
					closestDirection = coordinate.calculateDirection(other);
				}
			}
		}

		return closestDirection;
	}

	/**
	 * Récupère les coordonnées des cases contenant des trésors dans la zone de
	 * recherche autour de la coordonnée donnée.
	 *
	 * @param coordinate la coordonnée de référence.
	 * @return la liste des coordonnées des cases contenant des trésors dans la zone
	 *         de recherche.
	 */
	public List<Coordinate> getTreasureArea(Coordinate coordinate) {
		List<Coordinate> treasureCoord = new ArrayList<>();

		int rowMin = Math.max(0, coordinate.getX() - 2);
		int rowMax = Math.min(getWidth() - 1, coordinate.getX() + 2);
		int colMin = Math.max(0, coordinate.getY() - 2);
		int colMax = Math.min(getHeight() - 1, coordinate.getY() + 2);

		for (int row = rowMin; row <= rowMax; row++) {
			for (int col = colMin; col <= colMax; col++) {
				Coordinate coord = new Coordinate(row, col);
				Case caseAtCoord = getCoordinateValue(coord);
				if (caseAtCoord.hasTreasure()) {
					treasureCoord.add(coord);
				}
			}
		}

		return treasureCoord;
	}

	/**
	 * Récupère la coordonnée du trésor le plus proche par rapport à la coordonnée
	 * donnée.
	 *
	 * @param coordinate la coordonnée de référence.
	 * @return la coordonnée du trésor le plus proche, ou null s'il n'y en a pas.
	 */
	public Coordinate getNearestTreasure(Coordinate coordinate) {

		List<Coordinate> treasureCoord = getTreasureArea(coordinate);

		if (treasureCoord.isEmpty()) {
			return null;
		}

		Coordinate nearestTreasure = null;
		double minDistance = Double.MAX_VALUE;
		for (Coordinate currentCoordinate : treasureCoord) {
			double distance = Coordinate.distanceCalculateXandY(coordinate, currentCoordinate);
			if (distance < minDistance) {
				minDistance = distance;
				nearestTreasure = currentCoordinate;
			}
		}
		return nearestTreasure;
	}

	/**
	 * Vérifie si tous les trésors ont été découverts.
	 *
	 * Parcourt les coordonnées de la carte et vérifie si chaque case contenant un
	 * trésor a été creusée.
	 *
	 * @return true si tous les trésors ont été découverts, sinon false.
	 */
	public boolean isAllTreasuresFound() {
		for (Coordinate coordinate : this) {
			Case caseAtCoord = getCoordinateValue(coordinate);
			if (caseAtCoord.hasTreasure() && !caseAtCoord.isDug()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Génère une map de 16x16 à partir d'un tableau de caractères.
	 *
	 * @param mapArray Le tableau de caractères représentant la carte de jeu.
	 */
	public void generateBigMap(char[][] mapArray) {
	    int mapHeight = mapArray.length;
	    int mapWidth = mapArray[0].length;
	    row = 16;
	    col = 16;

	    // Vérifie si la taille de la carte est suffisamment grande
	    if (mapHeight < 16 || mapWidth < 16) {
	        // La taille de la carte est inférieure à la taille minimale requise
	        // Gérer cette condition d'erreur ici
	        return;
	    }

	    // Génère des coordonnées de départ aléatoires dans les limites de la carte
	    int startX = generateRandomCoordinate(mapHeight - 16);
	    int startY = generateRandomCoordinate(mapWidth - 16);

	    // Parcourt les coordonnées de la sous-carte souhaitée et crée les cases correspondantes
	    for (int y = 0; y < row; y++) {
	        for (int x = 0; x < col; x++) {
	            CaseType caseType = CaseType.fromChar(mapArray[y + startX][x + startY]);
	            caseMap.put(new Coordinate(y, x), new Case(caseType));
	        }
	    }
	}

	/**
	 * Génère une coordonnée aléatoire dans la plage spécifiée.
	 *
	 * @param max La valeur maximale pour la coordonnée aléatoire.
	 * @return La coordonnée aléatoire générée.
	 */
	private int generateRandomCoordinate(int max) {
	    return (int) (Math.random() * max);
	}

	
	/**
	 * 
	 * Renvoie la carte des cases associées aux coordonnées.
	 * 
	 * @return La carte des cases avec leurs coordonnées.
	 */
	public Map<Coordinate, Case> getCaseMap() {
		return caseMap;
	}

}
