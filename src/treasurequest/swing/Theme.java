package treasurequest.swing;

import java.awt.*;
import java.util.Map;

import javax.swing.*;

import treasurequest.supervisors.views.ResultType;
import treasurequest.supervisors.views.SpriteType;
import treasurequest.supervisors.views.TileType;

/**
 * Définit l'aspect général des vues.
 * */
public final class Theme {
	static {
		 try {
			UIManager.setLookAndFeel(
			            UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Dimensions
	public static final int WINDOW_WIDTH = 1280;
	public static final int WINDOW_HEIGHT = 720;
	public static final int PANEL_WIDTH = 1280;
	public static final int PANEL_HEIGHT = 720;
	public static final int TILE_SIZE = 64;
	
	//Police de caractères
	public static final Font ITEM_FONT = new Font("Ravie", Font.PLAIN, 24);
	public static final Font SUBTITLE_FONT = new Font("Segoe", Font.PLAIN, 20);
	public static final Font NORMAL_FONT = new Font("Segoe", Font.PLAIN, 12);
	
	//Couleurs
	public static final Color BACKGROUND_COLOR = Color.decode("#cff2fc");
	public static final Color PRIMARY_COLOR = Color.decode("#D90B43");
	public static final Color SECONDARY_COLOR = Color.decode("#0BD9A1");
	
	public static final Color PRIMARY_COLORA =  new Color(234, 179, 16, 200);
	public static final Color SECONDARY_COLORA =  new Color(5, 111, 150, 200);

	/**
	 * Définit la couleur des cases
	 * */
	public static final Map<TileType, Color> TILES = Map.of(
			TileType.WATER, Color.decode("#cff2fc"),
			TileType.GRASSLAND, Color.decode("#f3fccf"),
			TileType.FOREST, Color.decode("#dcfccf"),
			TileType.SAND, Color.decode("#fcefcf"),
			TileType.ROCK, Color.decode("#e5e5e5"),
			TileType.UNKNOWN, Color.decode("#000000")
	);
	
	/**
	 * Définit les sprites correspondant aux états d'une case creusée.
	 * */
	public static final Map<SpriteType, Image> DUG_CASE_SPRITES = Map.of(
			SpriteType.WEST, new ImageIcon("resources/images/sprites/west.png").getImage(),
			SpriteType.NORTH_WEST, new ImageIcon("resources/images/sprites/north_west.png").getImage(),
			SpriteType.NORTH, new ImageIcon("resources/images/sprites/north.png").getImage(),
			 SpriteType.NORTH_EAST, new ImageIcon("resources/images/sprites/north_east.png").getImage(),
			 SpriteType.EAST, new ImageIcon("resources/images/sprites/east.png").getImage(),
			 SpriteType.SOUTH_EAST, new ImageIcon("resources/images/sprites/south_east.png").getImage(),
			 SpriteType.SOUTH, new ImageIcon("resources/images/sprites/south.png").getImage(),
			 SpriteType.SOUTH_WEST, new ImageIcon("resources/images/sprites/south_west.png").getImage(),
			 SpriteType.DUG, new ImageIcon("resources/images/sprites/dug.png").getImage(),
			 SpriteType.TREASURE, new ImageIcon("resources/images/sprites/treasure.png").getImage()
	);
	
	/**
	 * Définit les images associées aux différents résultats.
	 * */
	public static final Map<ResultType, Image> RESULTS_SPRITES = Map.of(
			ResultType.NONE, new ImageIcon("resources/images/results/none.png").getImage(),
			ResultType.LOSS, new ImageIcon("resources/images/results/loss.png").getImage(),
			ResultType.GAIN, new ImageIcon("resources/images/results/gain.png").getImage(),
			ResultType.DURATION, new ImageIcon("resources/images/results/duration.png").getImage(),
			ResultType.TOURIST, new ImageIcon("resources/images/results/tourist.png").getImage(),
			ResultType.FARMER, new ImageIcon("resources/images/results/farmer.png").getImage(),
			ResultType.LUMBERJACK, new ImageIcon("resources/images/results/lumberjack.png").getImage(),
			ResultType.MINER, new ImageIcon("resources/images/results/miner.png").getImage()
			);

	
	/**
	 * Détermine la couleur du texte à afficher pour la couleur {@code background}.
	 * Retourne la couleur noir si {@code background == null}.
	 * */
	public static Color frontColorFor(Color background) {
		if(PRIMARY_COLOR == background || SECONDARY_COLOR == background) {
			return Color.WHITE;
		} else {
			return Color.BLACK;
		}
	}
	


}
