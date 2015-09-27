package com.dreede.spaceinvaders.model;


import java.util.ArrayList;

import com.dreede.spaceinvaders.model.entities.TexturedOpenGlObject;


/**
 * Das Interface {@link ViewModelInterface} [...]
 * 
 * 
 * @author Paul Wenzel
 *
 */
public interface ViewModelInterface {
	
/////////////////////////////////////////////////Datenfelder/////////////////////////////////////////////////




//////////////////////////////////////////////////Methoden///////////////////////////////////////////////////

	/**
	 * Die Methode gibt eine Liste mit allen Objekten aus, welche mit Texture gezeichnet werden sollen.
	 * 
	 * @return Liste mit allen Objekten aus, welche mit Texture gezeichnet werden sollen
	 */
	public ArrayList<TexturedOpenGlObject> texturedEntitiesToDraw();

}
