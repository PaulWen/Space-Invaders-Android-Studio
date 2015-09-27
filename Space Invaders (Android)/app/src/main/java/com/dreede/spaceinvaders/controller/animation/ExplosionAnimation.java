package com.dreede.spaceinvaders.controller.animation;

import java.util.Iterator;

import com.dreede.spaceinvaders.model.ExplosionAnimationModelInterface;
import com.dreede.spaceinvaders.model.entities.Explosion;
import com.dreede.spaceinvaders.model.entities.skills.DestroyableSkillInterface;


/**
 * Die Klasse {@link ExplosionAnimation} dient dazu jede Explosion zu animieren.
 * 
 * 
 * @author Paul Wenzel
 *
 */
public class ExplosionAnimation extends Animation {

	
/////////////////////////////////////////////////Datenfelder/////////////////////////////////////////////////
	
	/** das Model damit die Animationsklasse alle nötigen Informationen abrufen kann */
	private ExplosionAnimationModelInterface model;

/////////////////////////////////////////////////Konstruktor/////////////////////////////////////////////////
	
	/**
	 * Der Konstruktor der Klasse {@link ExplosionAnimation}. 
	 * 
	 * @param model das Model damit die Animationsklasse alle nötigen Informationen abrufen kann
	 */
	public ExplosionAnimation(ExplosionAnimationModelInterface model) {
		// Datenfelder initialisieren
		this.model = model;
	}
	
//////////////////////////////////////////////Getter und Setter//////////////////////////////////////////////
	
	
	
	
	
	
///////////////////////////////////////////////geerbte Methoden//////////////////////////////////////////////
	
	@Override
	public void animate(long loopDuration) {
		Iterator<Explosion> explosionList = model.getIteratorExplosionList();
		while (explosionList.hasNext()) {
			Explosion explosion = explosionList.next();
		
			/////////////Frame auswählen/////////////
			// die verstrichene Zeit abziehen
			explosion.countdownTimeUntilNextFrame(loopDuration);
			
			// gucken ob der nächste Frame gezeichnet werden soll
			if (explosion.isNextFrame()) {
				// gucken ob bereits der letzte Frame angezeigt wird
				if (explosion.getCurrentSpriteIndexNumber() + 1 < explosion.getNumberOfSprites()) {
					explosion.nextSprite();
					explosion.resetTimeUntilNextFrame();
				} else {
					explosionList.remove();
					continue;
				}
			}
			
			/////////////gucken ob ein Objekt getroffen wird/////////////
			for (DestroyableSkillInterface entity: model.getDestroyableEntities()) {
				if (explosion.getPolygon().intersects(entity.getPolygon()) && explosion.hitEntity(entity)) {
					entity.getDestroyableSkill().countdownLives(explosion.getDestructiveSkill().getDestructivePower());
				}
			}
		}
	}
	
//////////////////////////////////////////////////Methoden///////////////////////////////////////////////////
	
	
	
	
	
///////////////////////////////////////////////Innere Klassen////////////////////////////////////////////////	
	
	
	
	
}
