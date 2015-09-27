package com.dreede.spaceinvaders.model;


import java.util.ArrayList;

import com.dreede.spaceinvaders.model.entities.Player;
import com.dreede.spaceinvaders.model.entities.Shoot;
import com.dreede.spaceinvaders.util.Rectangle;


/**
 * Das Interface {@link PlayerAnimationModelInterface} [...]
 * 
 * 
 * @author Paul Wenzel
 *
 */
public interface PlayerAnimationModelInterface {
	
/////////////////////////////////////////////////Datenfelder/////////////////////////////////////////////////




//////////////////////////////////////////////////Methoden///////////////////////////////////////////////////

	/**
	 * Die Methode gibt das {@link Player} Objekt aus.
	 * 
	 * @return das {@link Player} Objekt
	 */
	public Player getPlayer();
	
	/**
	 * Die Methode gibt aus, ob sich das Schiff nach links bewegen soll.
	 * 
	 * @return true, wenn sich das Schiff nach links bewegen soll
	 */
	public boolean isMoveLeftButtonTouched();
	
	/**
	 * Die Methode gibt aus, ob sich das Schiff nach rechts bewegen soll.
	 * 
	 * @return true, wenn sich das Schiff nach rechts bewegen soll
	 */
	public boolean isMoveRightButtonTouched();
	
	/**
	 * Die Methode gibt aus, ob das Schiff schießen soll.
	 * 
	 * @return true, wenn das Schiff schießen soll
	 */
	public boolean isShootButtonTouched();
	
	/**
	 * Die Methode fügt einen gewünschten Schuss zum Spielgeschehen hinzu.
	 * 
	 * @param shoot der gewünschte Schuss
	 */
	public void newShoot(Shoot shoot);
	
	/**
	 * Die Methode gibt eine Liste mit allen Grenzen aus, welche als Rechtecke dargestellt sind.
	 * Innerhalb dieser Rechtecke, darf sich das Objekt NICHT befinden.
	 * 
	 * @return Liste mit allen Grenzen
	 */
	public ArrayList<Rectangle> getBoundaryList();
}
