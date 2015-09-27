package com.dreede.spaceinvaders.model.entities;

import java.util.ArrayList;

import com.dreede.spaceinvaders.R;
import com.dreede.spaceinvaders.model.entities.skills.DestroyableSkillInterface;
import com.dreede.spaceinvaders.model.entities.skills.DestructiveSkill;
import com.dreede.spaceinvaders.model.entities.skills.DestructiveSkillInterface;

/**
 * Die Klasse {@link Explosion} ist eine Explosion.
 * 
 * 
 * @author Paul Wenzel
 *
 */
public class Explosion extends TexturedOpenGlObject implements DestructiveSkillInterface {
	
/////////////////////////////////////////////////Datenfelder/////////////////////////////////////////////////
	
	/** wie viele Milliekunden ein Frame der Exploion zu sehen sein soll */
	private final long MILLISECONDS_PER_FRAME = 100;
	
	private DestructiveSkill destructiveSkill;
	
	/** wie viele Millisekunden es noch dauert bis der nächste Frame angezeigt werden soll */
	private long timeUntilNextFrame;
	
	/** eine Liste mit allen getroffenen Objekten, damit kein Objekt zweimal getroffen wird */
	private ArrayList<DestroyableSkillInterface> hitEntities;
	
/////////////////////////////////////////////////Konstruktor/////////////////////////////////////////////////
	
	/**
	 * Der Konstruktor der Klasse {@link Explosion}. 
	 * 
	 * @param x X-Koordinate der unteren-linken-Ecke des Objektes
	 * @param y Y-Koordinate der unteren-linken-Ecke des Objektes
	 */
	public Explosion(float x, float y) {
		super(new int[]{R.drawable.explosion1, R.drawable.explosion2, R.drawable.explosion3, R.drawable.explosion4}, 70, 63, x, y);
		
		// Datenfelder initialisieren
		destructiveSkill = new DestructiveSkill(2);
		timeUntilNextFrame = MILLISECONDS_PER_FRAME;
		hitEntities = new ArrayList<DestroyableSkillInterface>();
	}
	
//////////////////////////////////////////////Getter und Setter//////////////////////////////////////////////
	
	/**
	 * Die Methode gibt aus, ob der nächste Frame angezeigt werden soll.
	 * 
	 * @return true: nächster Frame soll angezeigt werden
	 */
	public boolean isNextFrame() {
		return timeUntilNextFrame <= 0;
	}
	
	/**
	 * Die Methode setzt die timeUntilNextFrame-Variabel wider zurück.
	 */
	public void resetTimeUntilNextFrame() {
		timeUntilNextFrame = MILLISECONDS_PER_FRAME;
	}
	
	/**
	 * Die Methode zählt die Wartezeit bis der nächste Frame gezeigt werden soll runter. 
	 * 
	 * @param milliseconds um wieviele Millisekunden die TimeUntilNextFrame heruntergezählt werden soll
	 */
	public void countdownTimeUntilNextFrame(long milliseconds) {
		timeUntilNextFrame -= milliseconds;
	}
	
///////////////////////////////////////////////geerbte Methoden//////////////////////////////////////////////
	
	@Override
	public DestructiveSkill getDestructiveSkill() {
		return destructiveSkill;
	}

//////////////////////////////////////////////////Methoden///////////////////////////////////////////////////
	
	/**
	 * Die Methode guckt ob ein Objekt breits von dieser Explosion getroffen wurde und deshalb nicht nochmal leben
	 * dafür abgezuogen bekommt.
	 * 
	 * @param entity welches Objekt überprüft werden soll
	 * @return true: das Objekt wurde noch nicht von von der Explosion getroffen und muss deshalb noch getroffen werden
	 */
	public boolean hitEntity(DestroyableSkillInterface entity) {
		if (hitEntities.contains(entity)) {
			return false;
		} else {
			hitEntities.add(entity);
			return true;
		}
	}
	
///////////////////////////////////////////////Innere Klassen////////////////////////////////////////////////	
	
	
	
	
}
