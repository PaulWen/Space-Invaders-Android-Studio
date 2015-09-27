package com.dreede.spaceinvaders.controller.animation;

import java.util.Iterator;

import com.dreede.spaceinvaders.model.AlienAnimationModelInterface;
import com.dreede.spaceinvaders.model.entities.Alien;
import com.dreede.spaceinvaders.util.Rectangle;


/**
 * Die Klasse {@link AlienAnimation} dient dazu jedes Alien zu animieren.
 * 
 * 
 * @author Paul Wenzel
 *
 */
public class AlienAnimation extends Animation {

	
/////////////////////////////////////////////////Datenfelder/////////////////////////////////////////////////
	
	/** das Model damit die Animationsklasse alle nötigen Informationen abrufen kann */
	private AlienAnimationModelInterface model;
	
/////////////////////////////////////////////////Konstruktor/////////////////////////////////////////////////
	
	/**
	 * Der Konstruktor der Klasse {@link AlienAnimation}. 
	 * 
	 * @param model das Model damit die Animationsklasse alle nötigen Informationen abrufen kann
	 */
	public AlienAnimation(AlienAnimationModelInterface model) {
		// Datenfelder initialisieren
		this.model = model;
	}
	
//////////////////////////////////////////////Getter und Setter//////////////////////////////////////////////
	
	
	
	
	
	
///////////////////////////////////////////////geerbte Methoden//////////////////////////////////////////////
	
	@Override
	public void animate(long loopDuration) {
		Iterator<Alien> alienList = model.getIteratorAlienList();
		while (alienList.hasNext()) {
			Alien alien = alienList.next();
		
			/////////////gucken ob zerstört/////////////
			if (alien.getDestroyableSkill().getLives() <= 0) {
				model.newExplosion(alien.getX(), alien.getY());
				model.addPlayerPoints(alien.getPoints());
				alienList.remove();
				continue;
			}

			/////////////Schießen/////////////
			alien.getShootableSkill().countdownTimeUntilRelaoded(loopDuration);
			
			Rectangle playerPolygon = model.getPolygonOfPlayer();
			Rectangle alienPolygon = alien.getPolygon();
			
			// ob  das Alien Schussbereit ist && ob sich das Alien unter dem Spieler befindet
			if (alien.getShootableSkill().loaded() && alienPolygon.bottom() > playerPolygon.bottom() && alienPolygon.centerX() >= playerPolygon.left() && alienPolygon.centerX() <= playerPolygon.right()) {
				model.newShoot(alien.getShootableSkill().getShoot(alienPolygon.centerX(), alienPolygon.centerY()));
			}
			
			/////////////Objekt bewegen/////////////
			if (updateMovement(loopDuration, alien, alien.getPolygon(), model.getBoundaryList())) {
				// wenn das Alien gegen eine Grenze gestoßen ist soll es runter gehen und die Richtung wechseln
				alien.addMovement(0, Alien.NUMBER_OF_PIXEL_TO_MOVE_ALIEN_DEEPER * -1);
				alien.getMovableSkill().setVelocityX(alien.getMovableSkill().getVelocityX() * -1);
				
				// TODO ACHTUNG: Aliens können nach unten aus dem Spielfeld "abhauen" und "fallen" dann unendlich tief runter!!
			}
			
			/////////////Frame auswählen/////////////
			updateTextureLive(alien, alien.getDestroyableSkill());
		}
	}
	
//////////////////////////////////////////////////Methoden///////////////////////////////////////////////////
	
	
	
	
	
///////////////////////////////////////////////Innere Klassen////////////////////////////////////////////////	
	
	
	
	
}
