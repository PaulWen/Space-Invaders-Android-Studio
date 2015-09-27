package com.dreede.spaceinvaders.controller.animation;

import com.dreede.spaceinvaders.model.PlayerAnimationModelInterface;
import com.dreede.spaceinvaders.model.entities.Player;
import com.dreede.spaceinvaders.util.SoundEffectManager;


/**
 * Die Klasse {@link PlayerAnimation} dient dazu Spieler zu animieren.
 * 
 * 
 * @author Paul Wenzel
 *
 */
public class PlayerAnimation extends Animation {

	
/////////////////////////////////////////////////Datenfelder/////////////////////////////////////////////////
	
	/** das Model damit die Animationsklasse alle nötigen Informationen abrufen kann */
	private PlayerAnimationModelInterface model;
	
/////////////////////////////////////////////////Konstruktor/////////////////////////////////////////////////
	
	/**
	 * Der Konstruktor der Klasse {@link PlayerAnimation}.
	 * 
	 *  @param model das Model damit die Animationsklasse alle nötigen Informationen abrufen kann
	 */
	public PlayerAnimation(PlayerAnimationModelInterface model) {
		// Datenfelder initialisieren
		this.model = model;
	}
	
//////////////////////////////////////////////Getter und Setter//////////////////////////////////////////////
	
	
	
	
	
	
///////////////////////////////////////////////geerbte Methoden//////////////////////////////////////////////
	
	@Override
	public void animate(long loopDuration) {
		Player player = model.getPlayer();
	
		/////////////gucken ob zerstört/////////////
		if (player.getDestroyableSkill().getLives() <= 0) {
			// Spieler tot
		} else {
			/////////////Schießen/////////////
			player.getShootableSkill().countdownTimeUntilRelaoded(loopDuration);
			
			// ob  das Alien Schussbereit ist && ob sich das Alien unter dem Spieler befindet
			if (player.getShootableSkill().loaded() && model.isShootButtonTouched()) {
				model.newShoot(player.getShootableSkill().getShoot(player.getPolygon().centerX(), player.getPolygon().centerY()));
				SoundEffectManager.playSoundEffect("shoot_player.mp3");
			}
			
			/////////////Objekt bewegen/////////////
			// Geschwindigkeiten setzen
			player.getMovableSkill().setVelocityX(0);
			if (model.isMoveLeftButtonTouched()) {
				player.getMovableSkill().setVelocityX(player.getMovableSkill().getMaxVelocityX() * -1);
			}
			if (model.isMoveRightButtonTouched()) {
				player.getMovableSkill().setVelocityX(player.getMovableSkill().getMaxVelocityX());
			}
			// Position updaten
			updateMovement(loopDuration, player, player.getPolygon(), model.getBoundaryList());
			
			/////////////Frame auswählen/////////////
			updateTextureLive(player, player.getDestroyableSkill());
		}
	}
	
//////////////////////////////////////////////////Methoden///////////////////////////////////////////////////
	
	
	
	
	
///////////////////////////////////////////////Innere Klassen////////////////////////////////////////////////	
	
	
	
	
}
