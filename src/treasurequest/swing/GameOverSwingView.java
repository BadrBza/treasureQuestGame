package treasurequest.swing;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.Timer;

import treasurequest.supervisors.GameOverSupervisor;
import treasurequest.supervisors.views.*;
import treasurequest.swing.components.*;

/**
 * Implémentation Swing de la vue de fin de partie.
 * 
 * Cette vue affiche différents résultat. À chaque résultat correspond une image, un titre et une description. La description peut peut changer.
 */
public class GameOverSwingView extends SwingView implements GameOverView {

	private static final int PANEL_BY_ROW = 4;
	private final ImageIcon background = new ImageIcon("resources/images/game_over_background.jpg");
	private static final long serialVersionUID = -2457251566214956414L;
	
	private final GameOverSupervisor supervisor;
	private final Timer animator;
	
	private final  GameDataPanel commandsPanel;
	private final Set<GameResultPanel> panels = new LinkedHashSet<>();
	
	/**
	 * Construit une instance de la vue game over à l'aide du superviseur.
	 * */
	public GameOverSwingView(GameOverSupervisor supervisor) {
		super(ViewNames.GAME_OVER);
		
		this.animator = new Timer(16, (evt) -> onNewFrameRequested());
		
		this.commandsPanel = new GameDataPanel("COMMANDES", getWidth()/100*3, getHeight()/100*3, getWidth()/10*3);
		this.commandsPanel.addAll(
				"\u23ce : revenir au menu principal");
		
		this.supervisor = supervisor;
		this.supervisor.setView(this);
	}
	
	private void onNewFrameRequested() {
		this.updateUI();
	}

	@Override
	protected void paintComponent(Graphics painter) {
		super.paintComponent(painter);
		
		Graphics2D betterPainter = (Graphics2D)painter;		
		betterPainter.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		int deltaX = (this.getWidth() - background.getIconWidth())/2;
		painter.drawImage(background.getImage(), deltaX, 0, null);
		for(var resultPanel : panels) {
			resultPanel.repaint(betterPainter);
		}
		betterPainter.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
		this.commandsPanel.repaint(betterPainter);
	}
	
	@Override
	public void clearPanels() {
		panels.clear();
	}

	@Override
	public void addPanel(ResultType type, String message) {
		int row = panels.size()/PANEL_BY_ROW;
		int col = panels.size() - (row*PANEL_BY_ROW);
		
		panels.add(new GameResultPanel(type, message, getWidth()/100*3 + col*(256+5), getHeight()/3 + row*(200+5)));
	}

	
	@Override
	public void onEnter(String viewName) {
		animator.start();
		supervisor.onEnter(viewName);
	}

	
	@Override
	public void onLeave(String viewName) {
		animator.stop();
	}
	
	@Override
	public void onKeyTyped(int keyCode) {
		if(KeyEvent.VK_ENTER == keyCode) {
			supervisor.onGoToMain();
		}
	}
}
