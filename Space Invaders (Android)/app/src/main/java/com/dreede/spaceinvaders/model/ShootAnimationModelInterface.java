package com.dreede.spaceinvaders.model;


import java.util.ArrayList;
import java.util.Iterator;

import com.dreede.spaceinvaders.model.entities.Shoot;
import com.dreede.spaceinvaders.model.entities.skills.DestroyableSkillInterface;
import com.dreede.spaceinvaders.util.Rectangle;


/**
 * Das Interface {@link ShootAnimationModelInterface} [...]
 * 
 * 
 * @author Paul Wenzel
 *
 */
public interface ShootAnimationModelInterface {
	
/////////////////////////////////////////////////Datenfelder/////////////////////////////////////////////////




//////////////////////////////////////////////////Methoden///////////////////////////////////////////////////

	/**
	 * Die Methode gibt einen Iterator aus, mit welchem man durch alle {@link Shoot} Objekte iterrieren kann.
	 * 
	 * @return Iterator mit welchem man durch alle {@link Shoot} Objekte iterrieren kann
	 */
	public Iterator<Shoot> getIteratorShootList();
	
	/**
	 * Die Methode gibt eine List mit allen zerstörbaren Objekten aus. (Aliens und Spieler inbegriffen! )
	 * 
	 * @return List mit allen zerstörbaren Objekten
	 */
	public ArrayList<DestroyableSkillInterface> getDestroyableEntities();
	
	/**
	 * Die Methode gibt eine Liste mit allen Grenzen aus, welche als Rechtecke dargestellt sind.
	 * Innerhalb dieser Rechtecke, darf sich das Objekt NICHT befinden.
	 * 
	 * @return Liste mit allen Grenzen
	 */
	public ArrayList<Rectangle> getBoundaryList();
}
