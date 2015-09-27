package com.dreede.spaceinvaders.controller.animation;

import java.util.Iterator;

import com.dreede.spaceinvaders.model.ShootAnimationModelInterface;
import com.dreede.spaceinvaders.model.entities.Alien;
import com.dreede.spaceinvaders.model.entities.Player;
import com.dreede.spaceinvaders.model.entities.Shoot;
import com.dreede.spaceinvaders.model.entities.skills.DestroyableSkillInterface;


/**
 * Die Klasse {@link ShootAnimation} dient dazu jeden Schuss zu animieren.
 * 
 * 
 * @author Paul Wenzel
 *
 */
public class ShootAnimation extends Animation {

	
/////////////////////////////////////////////////Datenfelder/////////////////////////////////////////////////
	
	/** das Model damit die Animationsklasse alle nötigen Informationen abrufen kann */
	private ShootAnimationModelInterface model;
	
/////////////////////////////////////////////////Konstruktor/////////////////////////////////////////////////
	
	/**
	 * Der Konstruktor der Klasse {@link ShootAnimation}. 
	 * 
	 * @param model das Model damit die Animationsklasse alle nötigen Informationen abrufen kann
	 */
	public ShootAnimation(ShootAnimationModelInterface model) {
		// Datenfelder initialisieren
		this.model = model;
	}
	
//////////////////////////////////////////////Getter und Setter//////////////////////////////////////////////
	
	
	
	
	
	
///////////////////////////////////////////////geerbte Methoden//////////////////////////////////////////////
	
	@Override
	public void animate(long loopDuration) {
		Iterator<Shoot> shootList = model.getIteratorShootList();
		
		while (shootList.hasNext()) {
			Shoot shoot = shootList.next();
			/////////////gucken ob zerstört/////////////
			if (shoot.getDestroyableSkill().getLives() <= 0) {
				shootList.remove();
				continue;
			} 
			
			/////////////gucken ob ein Objekt getroffen wird/////////////
			for (DestroyableSkillInterface entity: model.getDestroyableEntities()) {
				if (shoot.getPolygon().intersects(entity.getPolygon()) && shoot != entity) {
					// gucken das kein Spieler-Schuss einen Spieler abschießt...
					if ((shoot.isShootOfPlayer() && !(entity instanceof Player) && !(entity instanceof Shoot)) ||
						// ...und kein Alien-Schuss ein Alien...
						(!shoot.isShootOfPlayer() && !(entity instanceof Alien) && !(entity instanceof Shoot)) ||
						// ...und kein Spielerschuss einen Spielerschuss...
						(shoot.isShootOfPlayer() && entity instanceof Shoot && !((Shoot)entity).isShootOfPlayer()) ||
						// ...und kein Alienschuss einen Alienschuss.
						(!shoot.isShootOfPlayer() && entity instanceof Shoot && ((Shoot)entity).isShootOfPlayer()))
					{
						
						// Leben vom getrofenen Objekt abziehen
						entity.getDestroyableSkill().countdownLives(shoot.getDestructiveSkill().getDestructivePower());
						
						// Schuss zerstören
						shootList.remove();
						continue;
					}
				}
			}
			
			/////////////Objekt bewegen und gucken ob es gegen eine Grenze gestoßen ist/////////////
			 if (updateMovement(loopDuration, shoot, shoot.getPolygon(), model.getBoundaryList())) {
				 // Schuss zerstören
				 shootList.remove();
				 continue;
			 }
			
			/////////////Frame auswählen/////////////
			updateTextureLive(shoot, shoot.getDestroyableSkill());
		}
	}
	
//////////////////////////////////////////////////Methoden///////////////////////////////////////////////////
	
	
	
	
	
///////////////////////////////////////////////Innere Klassen////////////////////////////////////////////////	
	
	
	
	
}
