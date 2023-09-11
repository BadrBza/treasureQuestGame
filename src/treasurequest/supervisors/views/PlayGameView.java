package treasurequest.supervisors.views;

/**
 * Décrit comment mettre à jour l'écran représentant une partie en cours.
 * */
public interface PlayGameView extends View {	
	
	/**
	 * Supprime les tuiles existantes.
	 * 
	 * Appelez cette méthode avant de charger une carte pour éviter une superposition de tuiles à l'écran.
	 * */
	void clearTiles();
	
	/**
	 * Place une tuile aux coordonnées {@code (row, col)}.
	 * 
	 * Le coin suppérieur gauche de la tuile dans le rendu correspond à {@code (x: col*tileSize; row*tileSize)}.
	 * Des valeurs négatives sont possibles.
	 * */
	void setTileAt(TileType type, int row, int col);
	
	/**
	 * Place un sprite aux coordonnées {@code (row, col)}.
	 * 
	 * Il est conseillée d'appeler cette méthode après avoir appelé la méthode
	 * {@code setTileAt(TileType, int, int)}, sans quoi le sprite sera masqué par la tuile.
	 * */
	void setSpriteAt(SpriteType type, int row, int col);
	
	/**
	 * Définit le message associé à la bourse du joueur.
	 * 
	 * @param message un texte de la forme {@code "Bourse du joueur : [Nombre de pièce du joueur] P"}.
	 * */
	void setPlayerCoins(String message);
	
	
	/**
	 * Définit le message associé au nombre de trésors restants à trouver.
	 * 
	 * @param message un texte de la forme {@code "Trésors restants : [Nombre de trésors à trouver]"}.
	 * */
	void setTreasuresCount(String message);
	
	/**
	 * Définit le message associé à la position de la case active.
	 * 
	 * Si l'implémentation gère une scène en 2 dimension, déplace la caméra sur cette position.
	 * 
	 * @param message un texte de la forme {@code "Position de la case active : (Ligne: [ligne de la case]; Colonne: [colonne])"}.
	 * */
	void setActiveCase(int row, int col);
	
	
	/**
	 * Définit le message associé au type de la case active.
	 * 
	 * @param message un texte de la forme {@code "Type de la case action : [Type de la case]"}.
	 * */
	void setActiveCaseType(String message);
	
	/**
	 * Définit le message associé au coût de la case active.
	 * 
	 * @param message un texte de la forme {@code "Coût de la case active : [Cout de la case] P"}.
	 * */
	void setActiveCaseCost(String message);
}
