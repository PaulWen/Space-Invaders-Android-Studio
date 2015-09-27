package com.dreede.spaceinvaders.controller.animation;

import java.util.ArrayList;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;

import com.dreede.spaceinvaders.model.ButtonAnimationModelInterface;
import com.dreede.spaceinvaders.model.entities.buttons.ImageViewButton;


/**
 * Die Klasse {@link ButtonAnimation} dient dazu jeden Button zu animieren.
 * 
 * 
 * @author Paul Wenzel
 *
 */
public class ButtonAnimation implements OnTouchListener {

	
/////////////////////////////////////////////////Datenfelder/////////////////////////////////////////////////
	
	/** das Model damit die Animationsklasse alle nötigen Informationen abrufen kann */
	private ButtonAnimationModelInterface model;
	
/////////////////////////////////////////////////Konstruktor/////////////////////////////////////////////////
	
	/**
	 * Der Konstruktor der Klasse {@link ButtonAnimation}. 
	 * 
	 * @param model das Model damit die Animationsklasse alle nötigen Informationen abrufen kann
	 * @param die ViewGroup, welche alle Button enthält die überwacht werden sollen
	 */
	public ButtonAnimation(ButtonAnimationModelInterface model, ViewGroup buttonContainer) {
		// Datenfelder initialisieren
		this.model = model;
		
		buttonContainer.setOnTouchListener(this);
	}
	
//////////////////////////////////////////////Getter und Setter//////////////////////////////////////////////
	
	
	
	
	
	
///////////////////////////////////////////////geerbte Methoden//////////////////////////////////////////////
	
	@Override
	public boolean onTouch(View view, MotionEvent motionEvent) {
		// die Button als nicht gedrückt setzen
		resetButtons(model.getButtonList());
		
		// in der Variable wird im Falle das  ACTION_POINTER_UP die ID des Pointers gespeichert, um sicherzustellen,
		// dass dieser Pointer nicht mehr verwendet wird um eine Aktion auszuführen
		int pointerUp = -1;
		
		// wenn sich mindestens ein Finger erhoben hat
		if (motionEvent.getActionMasked() == MotionEvent.ACTION_UP || motionEvent.getActionMasked() == MotionEvent.ACTION_POINTER_UP) { 
			pointerUp = motionEvent.getActionIndex();
		}	

		// prüfen ob überhaubt noch ein Finger auf dem Screen ist, wenn "motionEvent.getActionMasked() != MotionEvent.ACTION_UP" ist kein Finger mehr auf dem Screen
		if (motionEvent.getActionMasked() != MotionEvent.ACTION_UP) {
			// gucken wo sich Finger auf dem Screen befinden
			for (int i = 0; i < motionEvent.getPointerCount(); i++) { //für jeden Finger/Pointer der auf dem Touchscreen ist
				// nur wenn es sich nicht um den Pointer handelt, welcher eigentlich grade entfernt wurde und somit kein Button gelöst haben kann
				if (pointerUp != motionEvent.getPointerId(i)) { 
					ImageViewButton buttonPressed = buttonIsPressed(motionEvent.getX(i), motionEvent.getY(i), model.getButtonList());
					// überprüfen, ob überhaupt eine Button berührt wurde
					if (buttonPressed != null) { 
						buttonPressed.setTouched(true);
					}
				} 
			}
		}
		return true;
	}
	
//////////////////////////////////////////////////Methoden///////////////////////////////////////////////////
	
	/**
	 * Methode, welche guckt ob ein Button bei einer Touchscreen berührung gedrückt wurde
	 * 
	 * @param x => x Wert wo der Touchscreen berührt wurde
	 * @param y => y Wert wo der Touchscreen berührt wurde
	 * @param buttonListIterator =>	die Liste mit allen Buttons die Überprüft werden sollen ob sie gedrückt wurden
	 * @return => der Button, welcher berührt wurde; null, wenn kein Button berührt wurde
	 */
	private static ImageViewButton buttonIsPressed(float x, float y, ArrayList<ImageViewButton> buttonList) {	
		for (ImageViewButton button : buttonList) {
			/////////////gucken ob berührt/////////////
			if (button.getPolygon().contains(x, y)) {
				return button;
			}
		}
		
		return null;
	}
	
	private void resetButtons(ArrayList<ImageViewButton> buttonList) {
		for (ImageViewButton button : buttonList) {
			button.setTouched(false);
		}
	}
	
///////////////////////////////////////////////Innere Klassen////////////////////////////////////////////////	
	
	
	
	
}
