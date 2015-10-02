package com.dreede.spaceinvaders.model.entities.buttons;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.dreede.spaceinvaders.R;
import com.dreede.spaceinvaders.util.Rectangle;


/**
 * Die Klasse {@link ImageViewButton} [...] Objekte werden über die Innere Klasse {@link ImageViewButton.Builder} erbaut.
 * 
 * 
 * @author Paul Wenzel
 *
 */
public class ImageViewButton {
	
/////////////////////////////////////////////////Datenfelder/////////////////////////////////////////////////

	private ImageView button;
	
	/** die ButtonID des Buttons (siehe Konstanten der Klasse {@link ImageViewButton.Builder}) */
	private int buttonID;
	/** ob der Button gedrückt ist */
	private boolean touched;
	
	private int imageResourceID;
	private int imageTouchedResourceID;
	
/////////////////////////////////////////////////Konstruktor/////////////////////////////////////////////////
	
	/**
	 * Der Konstruktor der Klasse {@link ImageViewButton}. 
	 * 
	 * @param spriteResourceIDList die ResourceID, welche der gewüschten Bilddatei zugeordnet ist
	 * @param width Breite des Objekts
	 * @param height Höhe des Objekts
	 * @param x X-Koordinate der unteren-linken-Ecke des Objektes
	 * @param y Y-Koordinate der unteren-linken-Ecke des Objektes
	 * @param buttonID die ID des geünschten Buttons (siehe Konstanten der Klasse {@link ImageViewButton.Builder})
	 */
	ImageViewButton(int buttonID, ImageView button, int imageResourceID, int imageTouchedResourceID) {
		// Datenfelder initialisieren
		this.buttonID = buttonID;
		this.button = button;
		this.imageResourceID = imageResourceID;
		this.imageTouchedResourceID = imageTouchedResourceID;
		touched = false;
		button.setImageResource(imageResourceID);
	}
	
//////////////////////////////////////////////Getter und Setter//////////////////////////////////////////////
	
	/**
	 * Die Methode gibt aus, ob der Buton zur Zeit gedrückt wird.
	 * 
	 * @return true: wenn der Button gedrückt wird
	 */
	public boolean isTouched() {
		return touched;
	}
	
	/**
	 * Die Methode ermöglicht es anzugeben, ob der Button gerade gedrückt wird oder nicht.
	 * 
	 * @param touched true: wenn der Button gedrückt wird
	 */
	public void setTouched(boolean touched) {
		this.touched = touched;
		if (touched) {
			button.setImageResource(imageTouchedResourceID);
		} else {
			button.setImageResource(imageResourceID);
		}
	}
	
	/**
	 * Die Methode gibt die ButtonID des Buttons aus.
	 * 
	 * @return die ButtonID des Buttons (siehe Konstanten der Klasse {@link ImageViewButton.Builder})
	 */
	public int getButtonID() {
		return buttonID;
	}
	
	public View getView() {
		return button;
	}
	
///////////////////////////////////////////////geerbte Methoden//////////////////////////////////////////////
	

	
//////////////////////////////////////////////////Methoden///////////////////////////////////////////////////
	
	/**
	 * Die Methode gibt ein {@link Rectangle} aus, welches das Objekt repräsentiert.
	 * Das {@link Rectangle} kann zum Beispiel zu Kollisionsüberprüfung verwendet werden.
	 * 
	 * @return ein {@link Rectangle}, welches das Objekt repräsentiert
	 */
	public Rectangle getPolygon() {
		int[] location = new int[2];
		button.getLocationOnScreen(location);
		return new Rectangle(location[0], location[1], button.getWidth(), button.getHeight());
	}
	
///////////////////////////////////////////////Innere Klassen////////////////////////////////////////////////	
	
	/**
	 * Die innere Klasse {@link ImageViewButton.Builder} ist der Erbauer, um {@link ImageViewButton} Objekte zu erbauen.
	 * 
	 * 
	 * @author Paul Wenzel
	 *
	 */
	public static class Builder {
		
	///////////////////////////////////////////////Datenfelder///////////////////////////////////////////////
		
		public static final int LEFT_BUTTON = 0;
		public static final int RIGHT_BUTTON = 1;
		public static final int SHOOT_BUTTON = 2;
		
		private ImageViewButton button;
		
	///////////////////////////////////////////////Konstruktor///////////////////////////////////////////////
		
		/**
		 * Der Konstruktor der inneren Klasse {@link ImageViewButton.Builder}.
		 * 
		 * @param buttonID die ID des geünschten Buttons (siehe Konstanten der Klasse {@link ImageViewButton.Builder})
		 * @param centerX x-Koordinate des Mittelpunktes des Schusses
		 * @param centerY y-Koordinate des Mittelpunktes des Schusses
		 */
		public Builder(int buttonID, ImageView button, Context context) {
			switch (buttonID) {
				case LEFT_BUTTON:
					this.button = new ImageViewButton(LEFT_BUTTON, button, R.drawable.button_move_left, R.drawable.button_move_left_touched);
					break;
				case RIGHT_BUTTON:
					this.button = new ImageViewButton(RIGHT_BUTTON, button, R.drawable.button_move_right, R.drawable.button_move_right_touched);
					break;
				case SHOOT_BUTTON:
					this.button = new ImageViewButton(SHOOT_BUTTON, button, R.drawable.button_shoot, R.drawable.button_shoot_touched);
					break;
			}
		}
		
	////////////////////////////////////////////Getter und Setter////////////////////////////////////////////
		
		/**
		 * Die Methode gibt den fertig erbauten Button aus.
		 * 
		 * @return der fertig erbaute Button
		 */
		public ImageViewButton getButton() {
			return button;
		}
		
	/////////////////////////////////////////////geerbte Methoden////////////////////////////////////////////
		
		
		
	////////////////////////////////////////////////Methoden/////////////////////////////////////////////////
				
		
		
	}
}
