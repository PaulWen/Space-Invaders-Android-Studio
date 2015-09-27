package com.dreede.spaceinvaders.model;


import java.util.ArrayList;
import java.util.Iterator;

import com.dreede.spaceinvaders.model.entities.Alien;
import com.dreede.spaceinvaders.model.entities.Shoot;
import com.dreede.spaceinvaders.util.Rectangle;


/**
 * Das Interface {@link AlienAnimationModelInterface} [...]
 * 
 * 
 * @author Paul Wenzel
 *
 */
public interface AlienAnimationModelInterface {
	
/////////////////////////////////////////////////Datenfelder/////////////////////////////////////////////////




//////////////////////////////////////////////////Methoden///////////////////////////////////////////////////

	/**
	 * Die Methode gibt einen Iterator aus, mit welchem man durch alle {@link Alien} Objekte iterrieren kann.
	 * 
	 * @return Iterator mit welchem man durch alle {@link Alien} Objekte iterrieren kann
	 */
	public Iterator<Alien> getIteratorAlienList();
	
	/**
	 * Die Methode gibt ein Rechteck, welches dem Spieler-Shiff entspricht aus.
	 * 
	 * @return  Rechteck, welches dem Spieler-Shiff entspricht
	 */
	public Rectangle getPolygonOfPlayer();
	
	/**
	 * Die Methode fügt einen gewünschten Schuss zum Spielgeschehen hinzu.
	 * 
	 * @param shoot der gewünschte Schuss
	 */
	public void newShoot(Shoot shoot);
	
	/**
	 * Die Methode fügt eine gewünschte Explosion zum Spielgeschehen hinzu.
	 * 
	 * @param explosion die gewünschte Explosion
	 */
	public void newExplosion(float x, float y);
	
	/**
	 * Die Methode gibt eine Liste mit allen Grenzen aus, welche als Rechtecke dargestellt sind.
	 * Innerhalb dieser Rechtecke, darf sich das Objekt NICHT befinden.
	 * 
	 * @return Liste mit allen Grenzen
	 */
	public ArrayList<Rectangle> getBoundaryList();
	
	public void addPlayerPoints(int points);
}
