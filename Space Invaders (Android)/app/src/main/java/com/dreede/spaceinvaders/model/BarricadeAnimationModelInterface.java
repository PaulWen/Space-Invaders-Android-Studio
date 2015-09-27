package com.dreede.spaceinvaders.model;


import java.util.Iterator;

import com.dreede.spaceinvaders.model.entities.Barricade;


/**
 * Das Interface {@link BarricadeAnimationModelInterface} [...]
 * 
 * 
 * @author Paul Wenzel
 *
 */
public interface BarricadeAnimationModelInterface {
	
/////////////////////////////////////////////////Datenfelder/////////////////////////////////////////////////




//////////////////////////////////////////////////Methoden///////////////////////////////////////////////////

	/**
	 * Die Methode gibt einen Iterator aus, mit welchem man durch alle {@link Barricade} Objekte iterrieren kann.
	 * 
	 * @return Iterator mit welchem man durch alle {@link Barricade} Objekte iterrieren kann
	 */
	public Iterator<Barricade> getIteratorBarricadenList();
}
