package com.dreede.spaceinvaders.model.entities;

import com.dreede.spaceinvaders.R;
import com.dreede.spaceinvaders.model.entities.skills.DestroyableSkill;
import com.dreede.spaceinvaders.model.entities.skills.DestroyableSkillInterface;
import com.dreede.spaceinvaders.model.entities.skills.DestructiveSkill;
import com.dreede.spaceinvaders.model.entities.skills.DestructiveSkillInterface;
import com.dreede.spaceinvaders.model.entities.skills.MoveableSkill;
import com.dreede.spaceinvaders.model.entities.skills.MoveableSkillInterface;


/**
 * Die Klasse {@link Shoot} ist ein Schuss. Schuss Objekte werden über die Innere Klasse {@link Shoot.Builder} erbaut.
 * 
 * 
 * @author Paul Wenzel
 *
 */
public class Shoot extends TexturedOpenGlObject implements MoveableSkillInterface, DestroyableSkillInterface, DestructiveSkillInterface {
	
/////////////////////////////////////////////////Datenfelder/////////////////////////////////////////////////
	
	private MoveableSkill movableSkill;
	private DestroyableSkill destroyableSkill;
	private DestructiveSkill destructiveSkill;
	
	/** ob der Schuss ein Schuss von einem Spieler oder Alien ist */
	private boolean shootOfPlayer;
	
/////////////////////////////////////////////////Konstruktor/////////////////////////////////////////////////
	
	/**
	 * Der Konstruktor der Klasse {@link Shoot}. 
	 * 
	 * @param spriteResourceIDList die ResourceID, welche der gewüschten Bilddatei zugeordnet ist
	 * @param width Breite des Objekts
	 * @param height Höhe des Objekts
	 * @param x X-Koordinate der unteren-linken-Ecke des Objektes
	 * @param y Y-Koordinate der unteren-linken-Ecke des Objektes
	 * @param velocityY die Geschwindigkeit des Schusses auf der y-Achse
	 * @param lives die Anzahl an Leben die das Objekt noch hat (0 = zerstört)
	 * @param destructivePower die Anzahl an Leben die das Objekt abzieht bei jedem anderen Objekt das es berührt
	 * @param shootOfPlayer true: der Schuss ist von einem Spieler
	 */
	private Shoot(int[] spriteResourceIDList, float width, float height, float x, float y, float velocityY, int lives, int destructivePower, boolean shootOfPlayer) {
		super(spriteResourceIDList, width, height, x, y);
		
		// Datenfelder initialisieren
		movableSkill = new MoveableSkill(0f, velocityY);
		movableSkill.setVelocityY(velocityY);
		
		destroyableSkill = new DestroyableSkill(lives);
		destructiveSkill = new DestructiveSkill(destructivePower);
		
		this.shootOfPlayer = shootOfPlayer;
	}
	
//////////////////////////////////////////////Getter und Setter//////////////////////////////////////////////
	
	/**
	 * Die Methode gibt aus, ob der Schuss ein Schuss von einem Spieler oder Alien ist.
	 * 
	 * @return true: der Schuss ist von einem Spieler
	 */
	public boolean isShootOfPlayer() {
		return shootOfPlayer;
	}
	
///////////////////////////////////////////////geerbte Methoden//////////////////////////////////////////////
	
	@Override
	public MoveableSkill getMovableSkill() {
		return movableSkill;
	}
	@Override
	public void addMovement(float x, float y) {
		super.addMovement(x, y);
	}
	
	@Override
	public DestroyableSkill getDestroyableSkill() {
		return destroyableSkill;
	}
	
	@Override
	public DestructiveSkill getDestructiveSkill() {
		return destructiveSkill;
	}
	
//////////////////////////////////////////////////Methoden///////////////////////////////////////////////////
	
	
	
	
///////////////////////////////////////////////Innere Klassen////////////////////////////////////////////////	
	
	/**
	 * Die innere Klasse {@link Shoot.Builder} ist der Erbauer, um {@link Shoot} Objekte zu erbauen.
	 * 
	 * 
	 * @author Paul Wenzel
	 *
	 */
	public static class Builder {
		
	///////////////////////////////////////////////Datenfelder///////////////////////////////////////////////
		
		public static final int PLAYER_SHOOT = 0;
		public static final int ALIEN_SHOOT = 1;
		
		private Shoot shoot;
		
	///////////////////////////////////////////////Konstruktor///////////////////////////////////////////////
		
		/**
		 * Der Konstruktor der inneren Klasse {@link Shoot.Builder}.
		 * 
		 * @param shootID die ID des geünschten Schusses (siehe Konstanten der Klasse {@link Shoot.Builder})
		 * @param centerX x-Koordinate des Mittelpunktes des Schusses
		 * @param centerY y-Koordinate des Mittelpunktes des Schusses
		 */
		public Builder(int shootID, float centerX, float centerY) {
			switch (shootID) {
			case PLAYER_SHOOT:
				shoot = new Shoot(new int[]{R.drawable.shoot_player}, 7, 20, centerX - 7 / 2, centerY - 20 / 2, 50f, 3, 5, true);
				break;
			case ALIEN_SHOOT:
				shoot = new Shoot(new int[]{R.drawable.shoot_alien2}, 7, 20, centerX - 7 / 2, centerY - 20 / 2, -50f, 3, 5, false);
				break;
			}
		}
		
	////////////////////////////////////////////Getter und Setter////////////////////////////////////////////
		
		/**
		 * Die Methode gibt den fertig erbauten Schuss aus.
		 * 
		 * @return der fertig erbauten Schuss
		 */
		public Shoot getShoot() {
			return shoot;
		}
		
	/////////////////////////////////////////////geerbte Methoden////////////////////////////////////////////
		
		
		
	////////////////////////////////////////////////Methoden/////////////////////////////////////////////////
				
		
		
	}
}
