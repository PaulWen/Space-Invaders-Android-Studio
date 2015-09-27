package com.dreede.spaceinvaders.model.entities.buttons;

import android.widget.ImageView;

import com.dreede.spaceinvaders.R;


/**
 * Die Klasse {@link ShootImageViewButton} stelle eine indirekte Erweiterung von {@link ImageViewButton} dar.
 * Die Klasse erbt zwar nicht von der Klasse {@link ImageViewButton} hat jedeoch ein Objekt als Datenfeld vom Typ {@link ImageViewButton}.
 * Diese Klasse {@link ShootImageViewButton} sorgt dafür das alle ShootImageViewButtons noch eine Nachlade Animation haben können.
 * [...] Objekte werden über die Innere Klasse {@link ShootImageViewButton.Builder} erbaut.
 * 
 * 
 * @author Paul Wenzel
 *
 */
public class ShootImageViewButton {
	
/////////////////////////////////////////////////Datenfelder/////////////////////////////////////////////////
	
	/** die shootButtonID des Buttons (siehe Konstanten der Klasse {@link ShootImageViewButton.Builder}) */
	private int shootButtonID;

	/** ist die Grundlage von diesem Button  */
	private ImageViewButton imageViewButton;
	
/////////////////////////////////////////////////Konstruktor/////////////////////////////////////////////////
	
	/**
	 * Der Konstruktor der Klasse {@link ShootImageViewButton}. 
	 * 
	 * @param shootIuttonID die ID des geünschten ShootButtons (siehe Konstanten der Klasse {@link ShootImageViewButton.Builder})
	 * @param imageViewButton ist die Grundlage von diesem Button
	 */
	private ShootImageViewButton(int shootButtonID, ImageViewButton imageViewButton) {
		// Datenfelder initialisieren
		this.shootButtonID = shootButtonID;
		this.imageViewButton = imageViewButton;
	}
	
//////////////////////////////////////////////Getter und Setter//////////////////////////////////////////////
	
	/**
	 * Die Methode gibt die ShootButtonID des ShootButtons aus.
	 * ACHTUNG: diese ID ist nicht mit der ButtonID (siehe Konstanten der Klasse {@link ImageViewButton.Builder})
	 * zu verwechsel! 
	 * 
	 * @return die ShootButtonID des Buttons (siehe Konstanten der Klasse {@link ShootImageViewButton.Builder})
	 */
	public int getShootButtonID() {
		return shootButtonID;
	}
	
	/**
	 * Die Methode gibt den {@link ImageViewButton} aus, welcher die Grundlage von diesem Button darstellt.
	 * 
	 * @return den {@link ImageViewButton}
	 */
	public ImageViewButton getImageViewButton() {
		return imageViewButton;
	}
	
///////////////////////////////////////////////geerbte Methoden//////////////////////////////////////////////
	

	
//////////////////////////////////////////////////Methoden///////////////////////////////////////////////////
	

	
///////////////////////////////////////////////Innere Klassen////////////////////////////////////////////////	
	
	/**
	 * Die innere Klasse {@link ShootImageViewButton.Builder} ist der Erbauer, um {@link ShootImageViewButton} Objekte zu erbauen.
	 * 
	 * 
	 * @author Paul Wenzel
	 *
	 */
	public static class Builder {
		
	///////////////////////////////////////////////Datenfelder///////////////////////////////////////////////
		
		public static final int LASER_SHOOT_BUTTON = 0;
		public static final int ROCKET_SHOOT_BUTTON = 1;
		public static final int PLASMA_SHOOT_BUTTON = 2;
		
		private ShootImageViewButton button;
		
	///////////////////////////////////////////////Konstruktor///////////////////////////////////////////////
		
		/**
		 * Der Konstruktor der inneren Klasse {@link ShootImageViewButton.Builder}.
		 * 
		 * @param buttonID die ID des geünschten Buttons (siehe Konstanten der Klasse {@link ShootImageViewButton.Builder})
		 * @param centerX x-Koordinate des Mittelpunktes des Schusses
		 * @param centerY y-Koordinate des Mittelpunktes des Schusses
		 */
		public Builder(int buttonID, ImageView button) {
			switch (buttonID) {
			case LASER_SHOOT_BUTTON:
				this.button = new ShootImageViewButton(ShootImageViewButton.Builder.LASER_SHOOT_BUTTON, new ImageViewButton(ImageViewButton.Builder.SHOOT_BUTTON, button, R.drawable.button_shoot, R.drawable.button_shoot_touched));
				break;
			case ROCKET_SHOOT_BUTTON:
				this.button = new ShootImageViewButton(ShootImageViewButton.Builder.ROCKET_SHOOT_BUTTON, new ImageViewButton(ImageViewButton.Builder.SHOOT_BUTTON, button, R.drawable.button_shoot, R.drawable.button_shoot_touched));
				break;
			case PLASMA_SHOOT_BUTTON:
				this.button = new ShootImageViewButton(ShootImageViewButton.Builder.PLASMA_SHOOT_BUTTON, new ImageViewButton(ImageViewButton.Builder.SHOOT_BUTTON, button, R.drawable.button_shoot, R.drawable.button_shoot_touched));
				break;
			}
		}
		
	////////////////////////////////////////////Getter und Setter////////////////////////////////////////////
		
		/**
		 * Die Methode gibt den fertig erbauten Button aus.
		 * 
		 * @return der fertig erbaute Button
		 */
		public ShootImageViewButton getButton() {
			return button;
		}
		
	/////////////////////////////////////////////geerbte Methoden////////////////////////////////////////////
		
		
		
	////////////////////////////////////////////////Methoden/////////////////////////////////////////////////
				
		
		
	}
}
