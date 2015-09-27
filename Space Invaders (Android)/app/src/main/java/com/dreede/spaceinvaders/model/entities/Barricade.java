package com.dreede.spaceinvaders.model.entities;

import com.dreede.spaceinvaders.R;
import com.dreede.spaceinvaders.model.entities.skills.DestroyableSkill;
import com.dreede.spaceinvaders.model.entities.skills.DestroyableSkillInterface;


/**
 * Die Klasse {@link Barricade} ist eine Barricade.
 * 
 * 
 * @author Paul Wenzel
 *
 */
public class Barricade extends TexturedOpenGlObject implements DestroyableSkillInterface  {
	
/////////////////////////////////////////////////Datenfelder/////////////////////////////////////////////////
	
	private DestroyableSkill destroyableSkill;
	
/////////////////////////////////////////////////Konstruktor/////////////////////////////////////////////////
	
	/**
	 * Der Konstruktor der Klasse {@link Barricade}. 
	 * 
	 * @param x X-Koordinate der unteren-linken-Ecke des Objektes
	 * @param y Y-Koordinate der unteren-linken-Ecke des Objektes
	 */
	public Barricade(float x, float y) {
		super(new int[]{R.drawable.barricade, R.drawable.barricade_broken1, R.drawable.barricade_broken2}, 200, 46, x, y);
		
		// Datenfelder initialisieren
		destroyableSkill = new DestroyableSkill(20);
	}
	
//////////////////////////////////////////////Getter und Setter//////////////////////////////////////////////
	
	@Override
	public DestroyableSkill getDestroyableSkill() {
		return destroyableSkill;
	}
	
///////////////////////////////////////////////geerbte Methoden//////////////////////////////////////////////
	
	

	
	
	
//////////////////////////////////////////////////Methoden///////////////////////////////////////////////////
	
	
	
	
	
///////////////////////////////////////////////Innere Klassen////////////////////////////////////////////////	
	
	
	
	
}
