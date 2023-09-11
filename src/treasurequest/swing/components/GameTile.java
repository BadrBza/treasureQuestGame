package treasurequest.swing.components;

import java.awt.*;

import treasurequest.swing.Theme;
import treasurequest.swing.engine.GameComponent;
import treasurequest.supervisors.views.SpriteType;
import treasurequest.supervisors.views.TileType;

/**
 * Décrit une tuile à afficher.
 * 
 * */
public class GameTile extends GameComponent {
	private final TileType background;
	private SpriteType foreground = SpriteType.NONE;
	
	/**
	 * Initialise une tuile avec un type, des coordonées et des dimensions.
	 * */
	public GameTile(TileType type, int left, int top, int width, int height) {
		super(left, top, width, height);
		
		this.background = type == null ? TileType.UNKNOWN : type;
		this.foreground = SpriteType.NONE;
	}
	

	@Override
	public void repaint(Graphics2D painter) {
		Color matchingColor = Theme.TILES.get(background);
		
		painter.setColor(matchingColor);
		painter.fillRect(getLeft(), getTop(), getWidth(), getHeight());
		
		painter.setColor(matchingColor.brighter());
		painter.drawRect(getLeft(), getTop(), getWidth(), getHeight());
		
		if(foreground.isNotNone()) {
			drawSprite(painter);
		}
	}

	private void drawSprite(Graphics2D betterPainter) {
		var img = Theme.DUG_CASE_SPRITES.get(foreground);
		int imgWidth = img.getWidth(null);
		int imgHeight = img.getHeight(null);
		
		var gapX = (getWidth() - imgWidth)/2;
		var gapY = (getHeight() - imgHeight)/2;
		
		betterPainter.drawImage(img, getLeft() + gapX , getTop() + gapY, imgWidth, imgHeight, null);				
	}

	public void setForeground(SpriteType foreground) {
		if(foreground != null) {
			this.foreground = foreground;
		}
	}

}
