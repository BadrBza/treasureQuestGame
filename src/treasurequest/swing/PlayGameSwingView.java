package treasurequest.swing;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.Timer;

import treasurequest.supervisors.PlayGameSupervisor;
import treasurequest.supervisors.views.*;
import treasurequest.swing.components.GameDataPanel;
import treasurequest.swing.components.GameTile;
import treasurequest.swing.components.ItemSelector;
import treasurequest.swing.effect.CompletableTask;
import treasurequest.swing.effect.MoveToEffect;
import treasurequest.swing.engine.*;

/**
 * Implémentation Swing de la vue du jeu.
 * 
 * */
public class PlayGameSwingView extends SwingView implements PlayGameView {
	private static final long serialVersionUID = 8263982213248089132L;

	private static final int TILE_SIZE = 30;
	
	private final PlayGameSupervisor supervisor;
	private final Timer animator;
	private final java.util.List<CompletableTask> effects = new ArrayList<>();
	
	private final GameDataPanel gameCommandsPanel;
	private final GameDataPanel gameStatusPanel;
	private final Map<Integer, GameTile> tiles = new HashMap<>();
	private final ItemSelector selector;
	
	/**
	 * Construit une instance de cette vue initialisé avec son superviseur.
	 * 
	 * Une fois les composant initialisé, le constructeur appelle la méthode {@code setView(this)} de son superviseur.
	 * */
	public PlayGameSwingView(PlayGameSupervisor supervisor) {
		super(ViewNames.PLAY_GAME);
	
		animator = new Timer(16, (evt) -> onNewFrameRequested());
			
		this.gameCommandsPanel = new GameDataPanel("COMMANDES", getWidth()/3*2, getHeight()/10, getWidth()/10*3);
		this.gameCommandsPanel.addAll(
				"\u2190 : déplacer vers la gauche",
				"\u2191 : déplacer vers le haut",
				"\u2192 : déplacer vers la droite",
				"\u2193 : déplacer vers le bas",
				"ESPACE : choisir",
				"ESC : retour au menu principal");
		
		this.gameStatusPanel = new GameDataPanel("STATUT", getWidth()/3*2, 4*getHeight()/10, getWidth()/10*3);
		this.gameStatusPanel.addAll(
			"Trésors restants : XXX",
			"Bourse : XXXX P",
			"Position de la case active : (L: XXX;C: XXX)",
			"Type de la case active : XXXX",
			"Cout de la case active XXX P"
		);
		
		this.selector = new ItemSelector(0, 0, TILE_SIZE, TILE_SIZE);
		
		this.supervisor = supervisor;
		this.supervisor.setView(this);
	}

	
	private void onNewFrameRequested() {
		if(!effects.isEmpty()) {
			var effect = effects.get(0);
			effect.update();
			
			if(effect.isDone()) {
				effects.remove(0);
			}
		}
		updateUI();
	}
	
	@Override
	protected void paintComponent(Graphics painter) {
		super.paintComponent(painter);
		Graphics2D betterPainter = (Graphics2D) painter;
		var oldTransform = betterPainter.getTransform();
		
		betterPainter.translate(-selector.getPosX() + getWidth()/2, -selector.getPosY() - TILE_SIZE + getHeight()/2);
		betterPainter.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		for(var tile : tiles.values()) {
			tile.repaint(betterPainter);
		}
		selector.repaint(betterPainter);
		betterPainter.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
		
		betterPainter.setTransform(oldTransform);
		gameCommandsPanel.repaint(betterPainter);
		gameStatusPanel.repaint(betterPainter);
		
	}
	
	@Override
	public void onEnter(String screenName) {
		animator.start();
		supervisor.onEnter(screenName);
	}

	
	@Override
	public void onLeave(String screenName) {
		animator.stop();
	}

	@Override
	public void clearTiles() {
		this.tiles.clear();
	}
	
	@Override
	public void setTileAt(TileType type, int row, int col) {
		this.tiles.put(row*1024+col, new GameTile(type, col*TILE_SIZE, row*(TILE_SIZE), TILE_SIZE, TILE_SIZE));
	}


	@Override
	public void setSpriteAt(SpriteType type, int row, int col) {
		var key = Integer.valueOf(row*1024+col);	
		if(tiles.containsKey(key)) {
			tiles.get(key).setForeground(type);
		}
		System.out.println("setSpriteAt:" + type.name() + "  row:" +row + " col:" +col);
	}


	@Override
	public void setActiveCase(int row, int col) {
		float newX = col*TILE_SIZE + TILE_SIZE*0.5f;
		float newY = row*TILE_SIZE + TILE_SIZE*0.5f;
		

		this.gameStatusPanel.setAt(2, String.format("Position de la case active : (Ligne: %d; Colonne: %d)", row, col));
		this.effects.add(new MoveToEffect(selector, new Vector2f(newX, newY), 320));
	}


	@Override
	public void setPlayerCoins(String message) {
		if(message == null || message.isBlank()) {
			return;
		}
		
		this.gameStatusPanel.setAt(0, message);
	}


	@Override
	public void setTreasuresCount(String message) {
		if(message == null || message.isBlank()) {
			return;
		}
		
		this.gameStatusPanel.setAt(1, message);
	}

	@Override
	public void setActiveCaseType(String message) {
		if(message == null || message.isBlank()) {
			return;
		}
		
		this.gameStatusPanel.setAt(3, message);		
	}

	@Override
	public void setActiveCaseCost(String message) {
		if(message == null || message.isBlank()) {
			return;
		}
		
		this.gameStatusPanel.setAt(4, message);		
	}
	
	/**
	 * Réagit aux touches du clavier, en particulier les touches fléchées.
	 * 
	 * */
	@Override
	public void onKeyTyped(int keyCode) {
		if (KeyEvent.VK_LEFT<= keyCode && keyCode <= KeyEvent.VK_DOWN) {
			int dirRow = keyCode== KeyEvent.VK_UP ? - 1 :
				keyCode == KeyEvent.VK_DOWN ? 1 : 0;
			int dirCol = keyCode== KeyEvent.VK_LEFT ? - 1 :
				keyCode == KeyEvent.VK_RIGHT ? 1 : 0;
			supervisor.onMove(dirRow, dirCol);
		} else if(keyCode == KeyEvent.VK_SPACE) {
			supervisor.onDig();
		} else if(keyCode == KeyEvent.VK_ESCAPE) {
			supervisor.onStop();
		}
	}
}
