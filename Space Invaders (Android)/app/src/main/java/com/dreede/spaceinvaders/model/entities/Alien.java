package com.dreede.spaceinvaders.model.entities;

import com.dreede.spaceinvaders.R;
import com.dreede.spaceinvaders.model.entities.skills.DestroyableSkill;
import com.dreede.spaceinvaders.model.entities.skills.DestroyableSkillInterface;
import com.dreede.spaceinvaders.model.entities.skills.MoveableSkill;
import com.dreede.spaceinvaders.model.entities.skills.MoveableSkillInterface;
import com.dreede.spaceinvaders.model.entities.skills.ShootableSkill;
import com.dreede.spaceinvaders.model.entities.skills.ShootableSkillInterface;


/**
 * Die Klasse {@link Alien} ist ein Alien.
 * 
 * 
 * @author Paul Wenzel
 *
 */
public class Alien extends TexturedOpenGlObject implements MoveableSkillInterface, DestroyableSkillInterface, ShootableSkillInterface {
	
/////////////////////////////////////////////////Datenfelder/////////////////////////////////////////////////
	
	/**
	 * Anzahl der Pixel, um die ein Alien sich runterbewegen soll, nachdem der den linken oder rechten Rand des 
	 * Spielfeldes erreicht hat.
	 */
	public static final int NUMBER_OF_PIXEL_TO_MOVE_ALIEN_DEEPER = 80;

	private int alienID;
	
	private int points;
	
	private MoveableSkill movableSkill;
	private DestroyableSkill destroyableSkill;
	private ShootableSkill shootableSkill;
	
/////////////////////////////////////////////////Konstruktor/////////////////////////////////////////////////
	
	/**
	 * Der Konstruktor der Klasse {@link Alien}. 
	 * 
	 * @param x X-Koordinate der unteren-linken-Ecke des Objektes
	 * @param y Y-Koordinate der unteren-linken-Ecke des Objektes
	 */
	public Alien(int alienID, int[] spriteResourceIDList, float width, float height, float x, float y, float velocityX, float velocityY, int lives, int points) {
		super(spriteResourceIDList, width, height, x, y);
		
		
		// Datenfelder initialisieren
		this.alienID = alienID;
		this.points = points;
		movableSkill = new MoveableSkill(velocityX, velocityY);
		destroyableSkill = new DestroyableSkill(lives);
		shootableSkill = new ShootableSkill(Shoot.Builder.ALIEN_SHOOT, 1000);
		
		movableSkill.setVelocityX(movableSkill.getMaxVelocityX() * -1);
	}
	
//////////////////////////////////////////////Getter und Setter//////////////////////////////////////////////
	
	/**
	 * Die Methode gibt die AlienID des Aliens aus.
	 * 
	 * @return die AlienID des Aliens (siehe Konstanten der Klasse {@link Alien.Builder})
	 */
	public int getAlienID() {
		return alienID;
	}
	
	public int getPoints() {
		return points;
	}
	
///////////////////////////////////////////////geerbte Methoden//////////////////////////////////////////////
	
	@Override
	public MoveableSkill getMovableSkill() {
		return movableSkill;
	}

	@Override
	public DestroyableSkill getDestroyableSkill() {
		return destroyableSkill;
	}
	
	@Override
	public ShootableSkill getShootableSkill() {
		return shootableSkill;
	}
	
	@Override
	public void addMovement(float x, float y) {
		super.addMovement(x, y);
	}
	
//////////////////////////////////////////////////Methoden///////////////////////////////////////////////////
	
	
	
	
	
///////////////////////////////////////////////Innere Klassen////////////////////////////////////////////////	
	
	/**
	 * Die innere Klasse {@link Alien.Builder} ist der Erbauer, um {@link Alien} Objekte zu erbauen.
	 * 
	 * 
	 * @author Paul Wenzel
	 *
	 */
	public static class Builder {
		
	///////////////////////////////////////////////Datenfelder///////////////////////////////////////////////
		
		public static final int ALIEN_1 = 1;
		public static final int ALIEN_2 = 2;
		public static final int ALIEN_3 = 3;
		
		private Alien alien;
		
	///////////////////////////////////////////////Konstruktor///////////////////////////////////////////////
		
		/**
		 * Der Konstruktor der inneren Klasse {@link Alien.Builder}.
		 * 
		 * @param alienID die ID des geünschten Schusses (siehe Konstanten der Klasse {@link Alien.Builder})
		 * @param centerX x-Koordinate des Mittelpunktes des Schusses
		 * @param centerY y-Koordinate des Mittelpunktes des Schusses
		 */
		public Builder(int alienID, float x, float y) {
			switch (alienID) {
			case ALIEN_1:
				alien = new Alien(ALIEN_1, new int[]{R.drawable.alien1}, 70, 63, x, y, 50f, 20f, 5, 10);
				break;
			case ALIEN_2:
				alien = new Alien(ALIEN_2, new int[]{R.drawable.alien2}, 70, 63, x, y, 50f, 20f, 5, 20);
				break;
			case ALIEN_3:
				alien = new Alien(ALIEN_3, new int[]{R.drawable.alien3}, 70, 63, x, y, 50f, 20f, 5, 30);
				break;
			}
		}
		
	////////////////////////////////////////////Getter und Setter////////////////////////////////////////////
		
		/**
		 * Die Methode gibt das fertig erbaute Alien aus.
		 * 
		 * @return das fertig erbauten Alien
		 */
		public Alien getAlien() {
			return alien;
		}
		
	/////////////////////////////////////////////geerbte Methoden////////////////////////////////////////////
		
		
		
	////////////////////////////////////////////////Methoden/////////////////////////////////////////////////
				
		
		
	}	
	
}
