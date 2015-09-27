package com.dreede.spaceinvaders.controller.animation;

import java.util.Iterator;

import com.dreede.spaceinvaders.model.BarricadeAnimationModelInterface;
import com.dreede.spaceinvaders.model.entities.Barricade;


/**
 * Die Klasse {@link BarricadeAnimation} dient dazu jede Barraikade zu animieren.
 * 
 * 
 * @author Paul Wenzel
 *
 */
public class BarricadeAnimation extends Animation {

	
/////////////////////////////////////////////////Datenfelder/////////////////////////////////////////////////
	
	/** das Model damit die Animationsklasse alle nötigen Informationen abrufen kann */
	private BarricadeAnimationModelInterface model;
	
/////////////////////////////////////////////////Konstruktor/////////////////////////////////////////////////
	
	/**
	 * Der Konstruktor der Klasse {@link BarricadeAnimation}. 
	 * 
	 * @param model das Model damit die Animationsklasse alle nötigen Informationen abrufen kann
	 */
	public BarricadeAnimation(BarricadeAnimationModelInterface model) {
		// Datenfelder initialisieren
		this.model = model;
	}
	
//////////////////////////////////////////////Getter und Setter//////////////////////////////////////////////
	
	
	
	
	
	
///////////////////////////////////////////////geerbte Methoden//////////////////////////////////////////////
	
	@Override
	public void animate(long loopDuration) {
		Iterator<Barricade> barricadeList = model.getIteratorBarricadenList();
		while (barricadeList.hasNext()) {
			Barricade barricade = barricadeList.next();
		
			/////////////gucken ob zerstört/////////////
			if (barricade.getDestroyableSkill().getLives() <= 0) {
				barricadeList.remove();
				continue;
			}

			/////////////Frame auswählen/////////////
			updateTextureLive(barricade, barricade.getDestroyableSkill());
		}
	}
	
//////////////////////////////////////////////////Methoden///////////////////////////////////////////////////
	
	
	
	
	
///////////////////////////////////////////////Innere Klassen////////////////////////////////////////////////	
	
	
	
	
}
