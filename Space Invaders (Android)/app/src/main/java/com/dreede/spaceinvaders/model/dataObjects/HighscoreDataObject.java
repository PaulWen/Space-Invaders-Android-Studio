package com.dreede.spaceinvaders.model.dataObjects;
/**
 * Die Klasse {@link HighscoreDataObject} [...]
 * 
 * 
 * @author Paul Wenzel
 *
 */
public class HighscoreDataObject implements Comparable<HighscoreDataObject> {
	
/////////////////////////////////////////////////Datenfelder/////////////////////////////////////////////////
	
	private int points;
	private int wave;
	
/////////////////////////////////////////////////Konstruktor/////////////////////////////////////////////////
	
	/**
	 * Der Konstruktor der Klasse {@link HighscoreDataObject}. 
	 */
	public HighscoreDataObject(int points, int wave) {
		// Datenfelder initialisieren
		this.points = points;
		this.wave = wave;
		
	}
	
//////////////////////////////////////////////Getter und Setter//////////////////////////////////////////////
	
	public int getPoints() {
		return points;
	}
	
	public int getWave() {
		return wave;
	}
	
///////////////////////////////////////////////geerbte Methoden//////////////////////////////////////////////
	
	@Override
	public int compareTo(HighscoreDataObject another) {
		if (points > another.getPoints()) {
			return 1;
		} else if (points < another.getPoints()) {
			return -1;
		} else {
			if (wave > another.getWave()) {
				return 1;
			} else if (wave < another.getWave()) {
				return -1;
			} else {
				return 0;
			}
		}
	}
	
//////////////////////////////////////////////////Methoden///////////////////////////////////////////////////
	
	
	
	
	
///////////////////////////////////////////////Innere Klassen////////////////////////////////////////////////	
	
	
	
	
}
