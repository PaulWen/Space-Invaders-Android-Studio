package com.dreede.spaceinvaders.model.entities;

import com.dreede.spaceinvaders.Config;
import com.dreede.spaceinvaders.R;
import com.dreede.spaceinvaders.model.entities.skills.DestroyableSkill;
import com.dreede.spaceinvaders.model.entities.skills.DestroyableSkillInterface;
import com.dreede.spaceinvaders.model.entities.skills.MoveableSkill;
import com.dreede.spaceinvaders.model.entities.skills.MoveableSkillInterface;
import com.dreede.spaceinvaders.model.entities.skills.ShootableSkill;
import com.dreede.spaceinvaders.model.entities.skills.ShootableSkillInterface;


/**
 * Die Klasse {@link Shoot} ist ein Spieler.
 * 
 * 
 * @author Paul Wenzel
 *
 */
public class Player extends TexturedOpenGlObject implements MoveableSkillInterface, DestroyableSkillInterface, ShootableSkillInterface {
	
/////////////////////////////////////////////////Datenfelder/////////////////////////////////////////////////
	
	private MoveableSkill movableSkill;
	private DestroyableSkill destroyableSkill;
	private ShootableSkill shootableSkill;
	
	private int points;
	private int pointMultiplikator;
	private long timeOfLastPoints;
	private static final int MULTIPLIKATOR_TIME = 2000;
	
/////////////////////////////////////////////////Konstruktor/////////////////////////////////////////////////
	
	/**
	 * Der Konstruktor der Klasse {@link Shoot}. 
	 * 
	 * @param x X-Koordinate der unteren-linken-Ecke des Objektes
	 * @param y Y-Koordinate der unteren-linken-Ecke des Objektes
	 */
	public Player(float x, float y) {
		super(new int[]{R.drawable.player}, Config.PLAYER_WIDTH, Config.PLAYER_HEIGHT, x, y);
		
		// Datenfelder initialisieren
		movableSkill = new MoveableSkill(100f, 0f);
		destroyableSkill = new DestroyableSkill(5);
		shootableSkill = new ShootableSkill(Shoot.Builder.PLAYER_SHOOT, 1000);
	}
	
//////////////////////////////////////////////Getter und Setter//////////////////////////////////////////////
	
	
	
	
	
	
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
	public ShootableSkill getShootableSkill() {
		return shootableSkill;
	}
	
	public int getPoints() {
		return points;
	}
	
//////////////////////////////////////////////////Methoden///////////////////////////////////////////////////
	
	public void addPoints(int points) {
		if ((System.currentTimeMillis() - timeOfLastPoints) <= MULTIPLIKATOR_TIME) {
			this.points += pointMultiplikator * points;
			pointMultiplikator++;
		} else {
			this.points += points;
			pointMultiplikator = 1;
		}
		
		timeOfLastPoints = System.currentTimeMillis();
	}
	
	
///////////////////////////////////////////////Innere Klassen////////////////////////////////////////////////	
	
	
	
	
}
