package com.dreede.spaceinvaders.model;

import java.util.ArrayList;

import com.dreede.spaceinvaders.model.entities.Alien;

/**
 * Die Klasse {@link WaveBuilder} [...]
 * 
 * 
 * @author Paul Wenzel
 *
 */
public class WaveBuilder {
	
/////////////////////////////////////////////////Datenfelder/////////////////////////////////////////////////
	
	public static final int WAVE_1 = 1;
	public static final int WAVE_2 = 2;
	public static final int WAVE_3 = 3;
	public static final int WAVE_4 = 4;
	public static final int WAVE_5 = 5;
	
	private ArrayList<Alien> alienList;
	
/////////////////////////////////////////////////Konstruktor/////////////////////////////////////////////////
	
	/**
	 * Der Konstruktor der Klasse {@link WaveBuilder}. 
	 * 
	 * @param waveID die ID der geünschten Welle (siehe Konstanten der Klasse {@link WaveBuilder})
	 */
	public WaveBuilder(int waveID) {
		alienList = new ArrayList<Alien>();

		switch (waveID) {
			case WAVE_1:
				alienList.add(new Alien.Builder(Alien.Builder.ALIEN_1, 500, 500).getAlien());
				alienList.add(new Alien.Builder(Alien.Builder.ALIEN_1, 580, 500).getAlien());
				break;
			case WAVE_2:
				alienList.add(new Alien.Builder(Alien.Builder.ALIEN_2, 500, 500).getAlien());
				alienList.add(new Alien.Builder(Alien.Builder.ALIEN_2, 580, 500).getAlien());
				break;
			case WAVE_3:
				alienList.add(new Alien.Builder(Alien.Builder.ALIEN_3, 500, 500).getAlien());
				alienList.add(new Alien.Builder(Alien.Builder.ALIEN_3, 580, 500).getAlien());
				break;
				
			default:
				alienList.add(new Alien.Builder(Alien.Builder.ALIEN_3, 500, 580).getAlien());
				alienList.add(new Alien.Builder(Alien.Builder.ALIEN_3, 580, 580).getAlien());
				alienList.add(new Alien.Builder(Alien.Builder.ALIEN_2, 500, 420).getAlien());
				alienList.add(new Alien.Builder(Alien.Builder.ALIEN_2, 580, 420).getAlien());
				alienList.add(new Alien.Builder(Alien.Builder.ALIEN_1, 500, 500).getAlien());
				alienList.add(new Alien.Builder(Alien.Builder.ALIEN_1, 580, 500).getAlien());
				break;
		}
	}
	
//////////////////////////////////////////////Getter und Setter//////////////////////////////////////////////
	
	public ArrayList<Alien> getAlienList() {
		return alienList;
	}
	
///////////////////////////////////////////////geerbte Methoden//////////////////////////////////////////////
	
	

	
	
	
//////////////////////////////////////////////////Methoden///////////////////////////////////////////////////
	
	
	
	
	
///////////////////////////////////////////////Innere Klassen////////////////////////////////////////////////	
	
	
	
	
}
