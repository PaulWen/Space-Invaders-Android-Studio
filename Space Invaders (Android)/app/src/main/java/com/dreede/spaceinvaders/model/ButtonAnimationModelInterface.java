package com.dreede.spaceinvaders.model;


import java.util.ArrayList;

import com.dreede.spaceinvaders.model.entities.buttons.ImageViewButton;


/**
 * Das Interface {@link ButtonAnimationModelInterface} [...]
 * 
 * 
 * @author Paul Wenzel
 *
 */
public interface ButtonAnimationModelInterface {
	
/////////////////////////////////////////////////Datenfelder/////////////////////////////////////////////////




//////////////////////////////////////////////////Methoden///////////////////////////////////////////////////

	/**
	 * Die Methode gibt eine Liste mit {@link ImageViewButton} Objekten aus.
	 * 
	 * @return Liste mit {@link ImageViewButton} Objekten
	 */
	public ArrayList<ImageViewButton> getButtonList();
	
}
