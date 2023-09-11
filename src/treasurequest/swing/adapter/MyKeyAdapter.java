package treasurequest.swing.adapter;

import java.awt.event.KeyEvent;

/**
 * Adapte l'interface KeyListener pour gérer l'événement {@code keyPressed(KeyEvent)}.
 * 
 * L'adaptateur appelle une méthode conforme à l'interface fonctionnelle {@code KeyTypedEventHandler}.
 * */
public class MyKeyAdapter extends java.awt.event.KeyAdapter {
	private final KeyTypedEventHandler handler;

	/**
	 * Construit une instance composée du {@code handler}.
	 * */
	public MyKeyAdapter(KeyTypedEventHandler handler) {
		this.handler = handler;
	}
	
	/**
	 * Méthode appelée par Swing quand l'utilisateur libère une touche.
	 * 
	 * Délègue la gestion de l'événement à l'instance de {@code KeyTypedEventHandler} associée.
	 * 
	 * {@inheritDoc}
	 * */
	@Override
	public void keyPressed(KeyEvent evt) {
		this.handler.onKeyTyped(evt.getExtendedKeyCode());
	}

}

