package com.dreede.spaceinvaders.model;


import java.util.ArrayList;
import java.util.Iterator;

import com.dreede.spaceinvaders.model.entities.Explosion;
import com.dreede.spaceinvaders.model.entities.skills.DestroyableSkillInterface;


/**
 * Das Interface {@link ExplosionAnimationModelInterface} [...]
 * 
 * 
 * @author Paul Wenzel
 *
 */
public interface ExplosionAnimationModelInterface {
	
/////////////////////////////////////////////////Datenfelder/////////////////////////////////////////////////




//////////////////////////////////////////////////Methoden///////////////////////////////////////////////////

	/**
	 * Die Methode gibt einen Iterator aus, mit welchem man durch alle {@link Explosion} Objekte iterrieren kann.
	 * 
	 * @return Iterator mit welchem man durch alle {@link Explosion} Objekte iterrieren kann
	 */
	public Iterator<Explosion> getIteratorExplosionList();
	
	/**
	 * Die Methode gibt eine List mit allen zerstörbaren Objekten aus.
	 * 
	 * @return List mit allen zerstörbaren Objekten
	 */
	public ArrayList<DestroyableSkillInterface> getDestroyableEntities();
}
