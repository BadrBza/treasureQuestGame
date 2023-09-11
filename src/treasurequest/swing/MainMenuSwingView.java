package treasurequest.swing;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.*;
import java.util.*;
import java.util.stream.*;

import javax.swing.*;
import javax.swing.Timer;

import treasurequest.supervisors.MainMenuSupervisor;
import treasurequest.supervisors.views.*;
import treasurequest.swing.components.*;
import treasurequest.swing.effect.MoveToEffect;

/**
 * Implémentation Swing du menu principal.
 * 
 * */
public final class MainMenuSwingView extends SwingView implements MainMenuView {
	private static final long serialVersionUID = -2211970715714357966L;
	
	private final ImageIcon background = new ImageIcon("resources/images/main_menu_background.jpg");
	
	private final MainMenuSupervisor supervisor;
	private final Timer animator;
	private MoveToEffect moveEffect;
	
	private List<MenuItem> items;	
	private ItemSelector selector;
	private int selected = 0;

	private final GameDataPanel menuCommandsPanel;
	
	/**
	 * Construit un menu principal swing composé de son superviseur.
	 * */
	public MainMenuSwingView(MainMenuSupervisor supervisor) {
		super(ViewNames.MAIN_MENU);
		
		this.animator = new Timer(16, (evt) -> onNewFrameRequested());
		
		this.menuCommandsPanel = new GameDataPanel("COMMANDES", getWidth()/100*3, getHeight()/100*3, getWidth()/10*3);
		this.menuCommandsPanel.addAll(
				"\u2191 : déplacer vers le haut",
				"\u2193 : déplacer vers le bas",
				"\u23ce : choisir");
		
		this.supervisor = supervisor;
		this.supervisor.setView(this);
	}
	
	@Override
	public void paintComponent(Graphics painter) {		
		super.paintComponent(painter);
		
		Graphics2D betterPainter = (Graphics2D)painter;
		betterPainter.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		betterPainter.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		
		int deltaX = (this.getWidth() - background.getIconWidth())/2;
		painter.drawImage(background.getImage(), deltaX, 0, null);
		
		menuCommandsPanel.repaint(betterPainter);
	
		selector.repaint(betterPainter);
		
		for(var item : items) {	
			item.repaint(betterPainter);
		}
	}

	@Override
	public void onKeyTyped(int keyCode) {
		if(items.isEmpty()) {
			return;
		}
		
		int oldSelected = selected;
		if(keyCode == KeyEvent.VK_DOWN) {
			this.selected = (selected + 1) % items.size();
		} else if(keyCode == KeyEvent.VK_UP) {
			this.selected = (selected == 0) ? items.size() - 1 : selected - 1;
		} else if(KeyEvent.VK_ENTER == keyCode) {
			this.supervisor.onItemSelected(this.selected);
		}
		
		if(oldSelected != selected) {
			moveEffect = new MoveToEffect(selector, items.get(selected).getPosition(), 300);
		}
	}

	@Override
	public void setItems(List<String> items) {
		if(items == null) {
			return;
		}
		
		var left = getWidth()/3;
		var top = getHeight()/3;
		var width = getWidth()/3;
		var height = getHeight()/12;
		var gap = getHeight()/15;
		
		this.items = IntStream.range(0, items.size())
				.mapToObj((index) -> new MenuItem(index, items.get(index), left, top+index*(height+gap), width, height))
				.collect(Collectors.toList());
		this.selected = 0;
		
		selector = new ItemSelector(left-10, top-10, width+20, height+20);
	}
	
	@Override
	public void onEnter(String fromView) {
		animator.start();
	}

	
	@Override
	public void onLeave(String toView) {
		animator.stop();
	}

	private void onNewFrameRequested() {
		if(moveEffect != null) {
			moveEffect.update();
			if(moveEffect.isDone()) {
				moveEffect = null;
			}
			this.updateUI();
		}
		
	}

}
