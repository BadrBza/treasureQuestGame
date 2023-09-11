package treasurequest.swing.components;

import java.awt.*;
import java.util.ArrayList;

import treasurequest.swing.Theme;
import treasurequest.swing.engine.GameComponent;

/**
 * Décris un panneau affichant du texte.
 * 
 * Ce panneau peut avoir un titre et un nombre arbitraire de ligne.
 * */
public class GameDataPanel extends GameComponent {
	private Color background;
	private final java.util.List<String> lines = new ArrayList<>();
	private final String title;

	/**
	 * Construit un panneau dôté d'un titre, d'une position et d'une largeur connues.
	 * */
	public GameDataPanel(String title, int left, int top, int width) {
		super(left, top, width, 0);
		this.title = (title == null || title.isBlank()) ? "" : title;
		background = Theme.SECONDARY_COLORA;
	}
	
	/**
	 * @return the background
	 */
	public Color getBackground() {
		return background;
	}

	/**
	 * @param background the background to set
	 */
	public void setBackground(Color background) {
		if (background != null) {
			this.background = background;
		}
	}

	@Override
	public void repaint(Graphics2D painter) {
		int titleHeight =  getRenderedTileHeight(painter);
		
		painter.setFont(Theme.NORMAL_FONT);
		var lineFontMetrics = painter.getFontMetrics();
		
		int linesHeight = computeTotalHeight(lineFontMetrics, 5);
		
		drawPanel(painter, titleHeight + linesHeight);

		painter.setColor(Theme.frontColorFor(Theme.SECONDARY_COLOR));

		drawTitle(painter, titleHeight, lineFontMetrics);		
		drawLines(painter, titleHeight, lineFontMetrics);
	}

	private void drawPanel(Graphics painter, int totalHeight) {
		painter.setColor(getBackground());

		painter.fillRect(getLeft(), getTop(), getWidth(),totalHeight);
	}
	
	private void drawTitle(Graphics painter, int titleHeight, FontMetrics lineFontMetrics) {
		if(title != "") {
			painter.setFont(Theme.SUBTITLE_FONT);
			painter.drawString(title, getLeft() + lineFontMetrics.getMaxAscent(), getTop() + titleHeight);
			painter.drawLine(getLeft() + lineFontMetrics.getAscent(), getTop() + titleHeight+5, getLeft() + getWidth() - 2*lineFontMetrics.getAscent(), getTop() + titleHeight+5);
		}
	}
	
	private void drawLines(Graphics painter, int titleHeight, FontMetrics lineFontMetrics) {
		painter.setFont(Theme.NORMAL_FONT);
		for(int i=0; i < lines.size(); ++i) {
			painter.drawString(lines.get(i), getLeft() + lineFontMetrics.getMaxAscent(), getTop() + titleHeight + (i+1)*lineFontMetrics.getHeight()+5);
		}
	}


	private int getRenderedTileHeight(Graphics painter) {
		int titleHeight = 0;
		
		if(title != "") {
			painter.setFont(Theme.SUBTITLE_FONT);
			var titleFontMetrics = painter.getFontMetrics();
			titleHeight = titleFontMetrics.getHeight() + 5;
		}
		
		return titleHeight;
	}

	private int computeTotalHeight(FontMetrics fontMetrics, int gap) {
		int lineGap = fontMetrics.getHeight() + gap;
		return gap + lines.size()*lineGap;
	}

	/**
	 * Ajoute toutes les lignes non-blanches à ce panneau.
	 * */
	public void addAll(String... lines) {
		if(lines == null) {
			return;
		}
		
		for(var line : lines) {
			if(line != null) {
				this.lines.add(line);
			}
		}
	}

	/**
	 * Définis un nouveau texte pour la ligne {@code pos}.
	 * 
	 * Ne fais rien si pos est hors-limite ou que valeur est blanc.
	 * 
	 * @param pos le position du texte à remplacer exprimée en nombre de lignes.
	 * @param newText le nouveau texte.
	 * */
	public void setAt(int pos, String newText) {
		if(pos < 0 || pos >= lines.size()) {
			return;
		}
		if(newText == null || newText.isBlank()) {
			return;
		}
		lines.set(pos, newText);
	}

}
