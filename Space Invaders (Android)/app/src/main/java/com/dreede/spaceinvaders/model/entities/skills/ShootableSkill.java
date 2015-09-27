package com.dreede.spaceinvaders.model.entities.skills;

import com.dreede.spaceinvaders.model.entities.Shoot;


/**
 * Die Klasse {@link ShootableSkill} fasst alle nötigen Informationen zusammen, die ein schießfähiges Objekt 
 * speichern muss. Außerdem stellt es die nötigen Methoden zur Manipulation dieser Werte zur Verfügung.
 * 
 * 
 * @author Paul Wenzel
 *
 */
public class ShootableSkill {
	
/////////////////////////////////////////////////Datenfelder/////////////////////////////////////////////////
	
	/** die ID des Schusses, welchen dieses Objekt abfeuern kann (siehe Konstanten der Klasse {@link Shoot.Builder}) */
	private int shootID;
	/** wie lange es dauert bis das Objekt nachgeladen hat für den nächsten Schuss (in Millisekunden) */
	private long reloadTime;
	/** wieviel Zeit noch vergehen muss, bis das Objekt wieder Schießen kann (in Millisekunden) */
	private long timeUntilRelaoded;

/////////////////////////////////////////////////Konstruktor/////////////////////////////////////////////////
	
	/**
	 * Der Konstruktor der Klasse {@link ShootableSkill}.
	 * 
	 * @param die ID des Schusses, welchen dieses Objekt abfeuern kann (siehe Konstanten der Klasse {@link Shoot.Builder})
	 * @param reloadTime wie lange es dauert bis das Objekt nachgeladen hat für den nächsten Schuss (in Millisekunden)
	 */
	public ShootableSkill(int shootID, long reloadTime) {
		this.shootID = shootID;
		this.reloadTime = reloadTime;
		this.timeUntilRelaoded = 0;
	}
	
//////////////////////////////////////////////Getter und Setter//////////////////////////////////////////////
	
	/**
	 * Die Methode gibt ein Objekt, welches einen Schuss ist aus. Es wird davon ausgegangen das geschossen wurde!
	 * Deshalb wird der Zeitpunkt des letzten Schusses auf die aktuelle Zeit gesetzt. 
	 * 
	 * @param centerX x-Koordinate des Mittelpunktes des Schusses
	 * @param centerY y-Koordinate des Mittelpunktes des Schusses
	 * @return der Schuss an der gewünschten Position
	 * 		   falls NULL, dann wurde noch nicht nachgeladen und es muss noch gewartet werden
	 */
	public Shoot getShoot(float centerX, float centerY) {
		if (loaded()) {
			timeUntilRelaoded = reloadTime;
			return new Shoot.Builder(shootID, centerX, centerY).getShoot();
		} else {
			return null;
		}
	}
	
	/**
	 * Die Methode zählt die noch nötige ReloadTime bis das Objekt Reloaded ist runter. 
	 * 
	 * @param milliseconds um wieviele Millisekunden die ReloadTime heruntergezählt werden soll
	 */
	public void countdownTimeUntilRelaoded(long milliseconds) {
		timeUntilRelaoded -= milliseconds;
	}
	
	/**
	 * Die Methode gibt aus, ob das Objekt geladen ist und somit schießen kann.
	 * 
	 * @return true: das Objekt ist schuss bereit; false: das Objekt muss noch nachladen
	 */
	public boolean loaded() {
		if (timeUntilRelaoded <= 0) {
			return true;
		} else {
			return false;
		}
	}
	
///////////////////////////////////////////////geerbte Methoden//////////////////////////////////////////////
	
	

	
	
	
//////////////////////////////////////////////////Methoden///////////////////////////////////////////////////
	
	
	
	
	
	
///////////////////////////////////////////////Innere Klassen////////////////////////////////////////////////	
	
	
	
	
}
