package com.dreede.spaceinvaders.model.dataObjects;

import java.io.Serializable;
import java.util.ArrayList;

import com.dreede.spaceinvaders.model.entities.Alien;
import com.dreede.spaceinvaders.model.entities.Barricade;
import com.dreede.spaceinvaders.model.entities.Explosion;
import com.dreede.spaceinvaders.model.entities.Player;
import com.dreede.spaceinvaders.model.entities.Shoot;
import com.dreede.spaceinvaders.model.entities.TexturedOpenGlObject;
import com.dreede.spaceinvaders.util.Rectangle;

/**
 * Die Klasse {@link GameModelDataToSaveDataObject} [...]
 * 
 * 
 * @author Paul Wenzel
 *
 */
public class GameModelDataToSaveDataObject implements Serializable {
	
/////////////////////////////////////////////////Datenfelder/////////////////////////////////////////////////
	
	private TexturedOpenGlObject background;
	/** der Spieler */
	private Player player;
	/** Liste mit allen Aliens */
	private ArrayList<Alien> alienList;
	/** Liste mit allen Barrikaden */
	private ArrayList<Barricade> barricadenList;
	/** Liste mit allen Schüssen */
	private ArrayList<Shoot> shootList;
	/** Liste mit allen Explosionen */
	private ArrayList<Explosion> explosionList;
	/** Liste mit allen Grenzen, welche als Rechtecke dargestellt sind - innerhalb dieser Rechtecke, darf sich das Objekt NICHT befinden */
	private ArrayList<Rectangle> boundaryList;
	
/////////////////////////////////////////////////Konstruktor/////////////////////////////////////////////////
	
	/**
	 * Der Konstruktor der Klasse {@link GameModelDataToSaveDataObject}. 
	 */
	public GameModelDataToSaveDataObject(TexturedOpenGlObject background, Player player, ArrayList<Alien> alienList,
										 ArrayList<Barricade> barricadenList, ArrayList<Shoot> shootList,
										 ArrayList<Explosion> explosionList, ArrayList<Rectangle> boundaryList) {
		// Datenfelder initialisieren
		this.background = background;
		this.player = player;
		this.alienList = alienList;
		this.barricadenList = barricadenList;
		this.shootList = shootList;
		this.explosionList = explosionList;
		this.boundaryList = boundaryList;
	}
	
//////////////////////////////////////////////Getter und Setter//////////////////////////////////////////////
	
	public TexturedOpenGlObject getBackground() {
		return background;
	}

	public Player getPlayer() {
		return player;
	}

	public ArrayList<Alien> getAlienList() {
		return alienList;
	}

	public ArrayList<Barricade> getBarricadenList() {
		return barricadenList;
	}

	public ArrayList<Shoot> getShootList() {
		return shootList;
	}

	public ArrayList<Explosion> getExplosionList() {
		return explosionList;
	}

	public ArrayList<Rectangle> getBoundaryList() {
		return boundaryList;
	}
	
///////////////////////////////////////////////geerbte Methoden//////////////////////////////////////////////
	
	

	
	
	
//////////////////////////////////////////////////Methoden///////////////////////////////////////////////////
	
	
	
	
	
///////////////////////////////////////////////Innere Klassen////////////////////////////////////////////////	
	
	
	
	
}
