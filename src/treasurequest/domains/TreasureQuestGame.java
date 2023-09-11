package treasurequest.domains;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import treasurequest.io.CharArrayFileReader;

/**
 * Classe principale du jeu Treasure Quest. Cette classe gère les éléments
 * principaux du jeu tels que la carte, les trésors et le joueur.
 * 
 * 
 * CTT du calcul d'indices : Pour répondre à la question de la complexité
 * temporelle (CTT) du calcul d'indices au début du jeu Treasure Quest,
 * 
 * La méthode generateIndice(Coordinate coordinate) utilise une boucle for pour
 * itérer sur toutes les coordonnées de la carte. La taille de la boucle dépend
 * du nombre total de coordonnées (N x M) dans la carte.
 * 
 * J'utilise une interface List avec l'implémentation ArrayList pour représenter
 * les coordonnées environnantes. Cette structure de données me permet d'accéder
 * aux éléments par index ( O(1) ) et de les parcourir séquentiellement ( O(N)
 * ). De plus dans ma méthode getTreasureArea j'utilise le add (arraylist) qui a
 * une ctt O(1)
 * 
 * En résumé, la CTT du calcul d'indices dépend de la taille de la carte (N x M)
 * et de l'algorithme de calcul de distance. J'utilise l'interface List avec
 * l'implémentation ArrayList pour les coordonnées environnantes, ce qui permet
 * un accès efficace et des opérations rapides lors de l'itération sur les
 * coordonnées.
 * 
 * @author badr
 */

public class TreasureQuestGame implements Iterable<Coordinate> {

	private final CaseMap caseMap;
	private int treasuresToFind;
	private Player player;
	private Coordinate activeCoordinate;

	/**
	 * Constructeur de la classe TreasureQuestGame. Initialise les attributs de la
	 * classe avec les informations du jeu ( map,tresor a trouver, joueur et
	 * coordonnées)
	 * 
	 */
	public TreasureQuestGame() {
		this.caseMap = new CaseMap();
		this.treasuresToFind = 0;
		this.player = null;
		this.activeCoordinate = null;

	}

	/**
	 * Lance une nouvelle partie de Treasure Quest.
	 */
	public void newGame() {
		loadMap("resources/maps/map-sample-4.txt");
		placeTreasures();
		setActiveCoordinateToCenter();
		initializePlayerCoins();
	}

	/**
	 * Lance une nouvelle partie de TreasureQuest avec une map aléatoire (16x16) issue de la bigmap
	 */
	public void newGameBigMap() {
		loadMapBigMap("resources/maps/big-map.txt");
		placeTreasures();
		setActiveCoordinateToCenter();
		initializePlayerCoins();
	}

	/**
	 * Charge la carte du jeu à partir d'un fichier texte.
	 *
	 * @param filePath Le chemin du fichier contenant la carte.
	 */
	private void loadMap(String filePath) {
		char[][] mapArray = CharArrayFileReader.parseFile(filePath);
		caseMap.createFromCharArray(mapArray);
	}

	/**
	 * Charge la carte du jeu (big-map) à partir d'un fichier texte.
	 * @param filePath filePath Le chemin du fichier contenant la carte ( big-map)
	 */
	private void loadMapBigMap(String filePath) {
		char[][] mapArray = CharArrayFileReader.parseFile(filePath);
		caseMap.generateBigMap(mapArray);
	}

	/**
	 * Place les trésors sur la carte de manière aléatoire. chaque trésor a une
	 * valeur comprise entre 10 et 20
	 */
	private void placeTreasures() {
		int diggableCases = caseMap.getDiggableCaseCount();
		treasuresToFind = (int) Math.max(1, diggableCases * 0.1);

		Random random = new Random();
		for (int i = 0; i < treasuresToFind; i++) {
			int treasureValue = random.nextInt(20 - 10 + 1) + 10;
			caseMap.placeTreasureRandomly(treasureValue);
		}
	}

	/**
	 * Positionne le curseur actif au centre de la carte.
	 */
	private void setActiveCoordinateToCenter() {
		activeCoordinate = caseMap.getMiddleCoordinate();
	}

	/**
	 * Initialise les pièces du joueur en fonction du nombre de trésors à trouver.
	 */
	private void initializePlayerCoins() {
		int PlayerCoins = treasuresToFind * 2;
		player = new Player(PlayerCoins);
	}

	/**
	 * Retourne le solde de pièces du joueur.
	 *
	 * @return Le solde de pièces du joueur.
	 */
	public int getPlayerCoins() {
		return player.getCoins();
	}

	/**
	 * Modifie le solde de pièces du joueur.
	 * 
	 * @param coins Le nouveau solde de pièces du joueur.
	 */
	public void setPlayerCoins(int coins) {
		player.setCoins(coins < 0 ? 0 : coins);
	}

	/**
	 * Retourne un itérateur sur les coordonnées de la carte du jeu.
	 * 
	 * @return Un itérateur sur les coordonnées de la carte du jeu.
	 */
	@Override
	public Iterator<Coordinate> iterator() {
		return caseMap.iterator();
	}

	/**
	 * Retourne la coordonnée centrale de la carte du jeu.
	 * 
	 * @return La coordonnée centrale de la carte du jeu.
	 */
	public Coordinate getMiddleCoordinate() {
		return caseMap.getMiddleCoordinate();
	}

	/**
	 * Retourne la case de la carte du jeu correspondant à la coordonnée donnée.
	 * 
	 * @param coordinate La coordonnée de la case à retourner.
	 * @return La case de la carte du jeu correspondant à la coordonnée donnée.
	 */
	public Case getCoordinateValue(Coordinate coordinate) {
		return caseMap.getCoordinateValue(coordinate);
	}

	/**
	 * Récupère le type de case à la coordonnée spécifiée.
	 * 
	 * @param axeXaxeY La coordonnée pour laquelle récupérer le type de case.
	 * @return Le type de case à la coordonnée spécifiée.
	 */
	public CaseType getCaseTypeAtcoordinate(Coordinate axeXaxeY) {
		return caseMap.getCaseTypeAtcoordinate(axeXaxeY);
	}

	/**
	 * Récupère le nombre de cases pouvant être creusées restantes.
	 * 
	 * @return Le nombre de cases pouvant être creusées restantes.
	 */
	public int getDiggableCaseCount() {
		return caseMap.getDiggableCaseCount();
	}

	/**
	 * Récupère la coordonnée active actuelle.
	 * 
	 * @return La coordonnée active actuelle.
	 */
	public Coordinate getCoordinateActive() {
		return activeCoordinate;
	}

	/**
	 * 
	 * Vérifie si les coordonnées spécifiées sont valides dans le contexte de la
	 * carte du jeu.
	 * 
	 * @param row La coordonnée de ligne.
	 * @param col La coordonnée de colonne.
	 * @return true si les coordonnées sont valides, false sinon.
	 */
	public boolean isValidCoordinate(int row, int col) {
		int maxRow = caseMap.getWidth();
		int maxCol = caseMap.getHeight();
		return (row >= 0 && row < maxRow && col >= 0 && col < maxCol);
	}

	/**
	 * 
	 * Récupère le type de la case aux coordonnées spécifiées.
	 * 
	 * @param axeX La coordonnée X de la case.
	 * @param axeY La coordonnée Y de la case.
	 * @return Le type de la case aux coordonnées spécifiées.
	 * 
	 */
	public CaseType getCaseTypeAtCoordinate(int axeX, int axeY) {

		if (!isValidCoordinate(axeX, axeY)) {
		}
		Case caseAtCoordinate = getCoordinateValue(new Coordinate(axeX, axeY));
		return caseAtCoordinate.getType();
	}

	/**
	 * 
	 * Modifie la coordonnée active du joueur en fonction des coordonnées x et y
	 * fournies.
	 * 
	 * @param axeX La coordonnée en x de la case active.
	 * @param axeY La coordonnée en y de la case active.
	 */
	public void setActiveCoordinate(int axeX, int axeY) {
		if (!isValidCoordinate(axeX, axeY)) {
		}
		this.activeCoordinate = new Coordinate(axeX, axeY);
	}

	/**
	 * 
	 * Met à jour la case active en la marquant comme visitée.
	 */
	public void updateActiveCase() {
		// Récupération de la case active
		Case activeCase = getCoordinateValue(activeCoordinate);

		// Mise à jour de la case active
		activeCase.setVisited(true);
	}

	/**
	 * 
	 * Décrémente le nombre de tresor encore à trouver.
	 */
	public void decrementDiggableCaseCount() {
		this.treasuresToFind--;
		System.out.println("treasuresToFind:" + treasuresToFind);
	}

	/**
	 * Récupere le joueur
	 * 
	 * @return player
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * Récupère la carte des cases.
	 *
	 * @return la carte des cases.
	 */
	public CaseMap getCaseMap() {
		return caseMap;
	}

	/**
	 * Retourne le nombre de trésors restants à trouver sur la carte.
	 *
	 * @return Le nombre de trésors restants à trouver sur la carte.
	 */
	public int getTreasureCount() {
		return treasuresToFind;
	}

	/**
	 * Déduit un certain nombre de pièces de la bourse du joueur. Si le nombre de
	 * pièces déduit est supérieur au nombre de pièces actuel du joueur, la bourse
	 * est mise à zéro.
	 *
	 * @param coins le nombre de pièces à déduire
	 */
	public void deductCoins(int coins) {
		int remainingCoins = Math.max(0, player.getCoins() - coins);
		player.setCoins(remainingCoins);
	}

	/**
	 * Ajoute un certain nombre de pièces à la bourse du joueur.
	 *
	 * @param coins le nombre de pièces à ajouter
	 */
	public void addCoins(int coins) {
		int totalCoins = player.getCoins() + coins;
		player.setCoins(totalCoins);
	}

	/**
	 * Vérifie si le joueur peut creuser la case donnée.
	 *
	 * @param activeCase la case à vérifier
	 * @return true si le joueur peut creuser la case, false sinon
	 */
	public boolean canDig(Case activeCase) {
		CaseType activeCaseType = activeCase.getType();
		Player player = getPlayer();
		return activeCaseType != CaseType.WATER && !activeCase.isDug() && player.canDig()
				&& player.getCoins() >= activeCaseType.getPriceCoins() && getTreasureCount() > 0;
	}

	/**
	 * Récupère la coordonnée du trésor le plus proche par rapport à la coordonnée
	 * donnée.
	 *
	 * @param coordinate la coordonnée de référence.
	 * @return la coordonnée du trésor le plus proche, ou null s'il n'y en a pas.
	 */
	public Coordinate getNearestTreasure(Coordinate coordinate) {
		return caseMap.getNearestTreasure(activeCoordinate);
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
		return caseMap.getTreasureArea(activeCoordinate);
	}

	/**
	 * Génère l'indice de la direction vers la case contenant le trésor le plus
	 * proche à partir de la coordonnée donnée.
	 *
	 * @param coordinate la coordonnée de référence.
	 * @return l'indice de la direction vers la case contenant le trésor le plus
	 *         proche.
	 */
	public OrientationSprite generateIndice(Coordinate coordinate) {
		return caseMap.generateIndice(coordinate);
	}

	/**
	 * verifie si tous les trésor sont trouvés ou si y'a plus de cases creusables
	 * 
	 * @return true si tous les trésor sont trouvés ou si y'a plus de cases
	 *         creusables
	 */
	public boolean isGameOver() {
		return isAllTreasuresFound() || !hasDiggableCases();
	}

	/**
	 * Vérifie s'il reste des cases creusables sur la carte.
	 *
	 * @return true s'il reste des cases creusables, sinon false.
	 */
	public boolean hasDiggableCases() {
		return caseMap.getDiggableCaseCount() > 0;
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
		return caseMap.isAllTreasuresFound();
	}

	/**
	 * 
	 * Récupère les coordonnées des cases creusées sur la carte du jeu.
	 * 
	 * @return Une Map contenant les coordonnées des cases creusées en tant que clés
	 *         et les Cases correspondantes en tant que valeurs.
	 */
	public Map<Coordinate, Case> getDugCoordinates() {
		Map<Coordinate, Case> dugCoordinates = new HashMap<>();

		for (Map.Entry<Coordinate, Case> entry : caseMap.getCaseMap().entrySet()) {
			if (entry.getValue().isDug()) {
				dugCoordinates.put(entry.getKey(), entry.getValue());
			}
		}

		return dugCoordinates;
	}

}
