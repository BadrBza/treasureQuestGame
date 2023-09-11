package treasurequest.swing.components;

import java.awt.*;

import treasurequest.supervisors.views.ResultType;
import treasurequest.swing.Theme;
import treasurequest.swing.engine.GameComponent;

/**
 * Représente un panneau de résultat.
 * 
 * Un panneau de résultat est composé d'une couleur de fond, d'une image
 * correspondant au type de résultat, d'une description et d'un titre.
 */
public class GameResultPanel extends GameComponent {
	private static final int CHARS_BY_LINE = 32;
	private static final int GAP = 5;
	private static final int IMG_SIZE = 128;

	private Color background;
	private final ResultType result;
	private final String description;

	/**
	 * Construit un GameResultPanel à l'aide d'un ResultType et d'une courte description.
	 * */
	public GameResultPanel(ResultType resultType, String description, int left, int top) {
		super(left, top, 256, 160);

		this.result = resultType == null ? ResultType.NONE : resultType;
		this.description = description == null || description.isBlank() ? "" : description;
		this.background = Theme.SECONDARY_COLORA;
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
		int titleHeight = getRenderedTileHeight(painter);
		int imgHeight = getRenderedImageHeight();
		int descriptionHeight = getRenderedDescriptionLineHeigh(painter) * (1+countLinesToDraw());

		drawPanel(painter, titleHeight + imgHeight + descriptionHeight+GAP);

		painter.drawImage(Theme.RESULTS_SPRITES.get(result), getLeft() + (getWidth() - IMG_SIZE) / 2, getTop() + GAP, IMG_SIZE,
				IMG_SIZE, null);

		drawTitle(painter);
		drawDescription(painter);
	}

	private int getRenderedImageHeight() {
		return IMG_SIZE + GAP;
	}
	
	private int getRenderedTileHeight(Graphics painter) {
		var titleFontMetrics = painter.getFontMetrics(Theme.SUBTITLE_FONT);
		return titleFontMetrics.getHeight() + GAP;
	}
	
	private int getRenderedDescriptionLineHeigh(Graphics painter) {
		var normalFontMetrics = painter.getFontMetrics(Theme.NORMAL_FONT);
		return (normalFontMetrics.getHeight() + GAP);
	}
	
	private int countLinesToDraw() {
		return description.length() / CHARS_BY_LINE;
	}	

	private void drawPanel(Graphics painter, int totalHeight) {
		painter.setColor(getBackground());
		painter.fillRect(getLeft(), getTop(), getWidth(), totalHeight);
	}

	private void drawTitle(Graphics painter) {
		painter.setFont(Theme.SUBTITLE_FONT);
		painter.setColor(Theme.frontColorFor(Theme.SECONDARY_COLOR));
		var fontMetrics = painter.getFontMetrics();
		
		int titleBaseLine = getTop() + getRenderedImageHeight() + getRenderedTileHeight(painter);
		painter.drawString(result.getTitle(), getLeft() + fontMetrics.getMaxAscent(), titleBaseLine);
		
		painter.drawLine(getLeft() + fontMetrics.getAscent(), titleBaseLine+2,
				getLeft() + getWidth() - 2 * fontMetrics.getAscent(), titleBaseLine+2);
	}

	private void drawDescription(Graphics2D painter) {
		var fontMetrics = painter.getFontMetrics(Theme.SUBTITLE_FONT);		
		int baseLine = getTop() + getRenderedImageHeight() + getRenderedTileHeight(painter) + getRenderedDescriptionLineHeigh(painter);

		painter.setFont(Theme.NORMAL_FONT);
		painter.setColor(Theme.frontColorFor(Theme.SECONDARY_COLOR));
		painter.drawString(description, getLeft() + fontMetrics.getMaxAscent(), baseLine);
	}
}
