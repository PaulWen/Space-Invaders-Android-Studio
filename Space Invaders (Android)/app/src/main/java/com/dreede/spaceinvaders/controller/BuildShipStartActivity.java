package com.dreede.spaceinvaders.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.dreede.spaceinvaders.R;
import com.dreede.spaceinvaders.util.SoundEffectManager;
import com.dreede.spaceinvaders.util.SoundtrackManager;

/**
 * Die Klasse {@link BuildShipStartActivity} ist ein Menü in welcher man seine Schiffe verwalten kann und neue
 * Schiffe bauen kann, bzw. das Menü stellt Buttons zur verfügung über welche man zu den Schiffverwalzungs-Menüs gelangt.
 * 
 * 
 * 
 * @author Paul Wenzel
 *
 */
public class BuildShipStartActivity extends Activity implements OnClickListener {
	
/////////////////////////////////////////////////Datenfelder/////////////////////////////////////////////////
	
	private Button backButton;
	private Button highscoreButton;
	private boolean continueMusic;
	
/////////////////////////////////////////////////Konstruktor/////////////////////////////////////////////////
	
	/**
	 * Der Konstruktor der Klasse {@link BuildShipStartActivity}. 
	 */
	public BuildShipStartActivity() {
		// Datenfelder initialisieren
		
	}
	
	
	
//////////////////////////////////////////////Getter und Setter//////////////////////////////////////////////
	
	
	
	
	
	
///////////////////////////////////////////////geerbte Methoden//////////////////////////////////////////////
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.build_ship_start_menu);
		
		// Datenfelder initialisieren
		backButton = (Button)findViewById(R.id.buttonBackBuildShipStartMenu);
		backButton.setOnClickListener(this);
		highscoreButton = (Button)findViewById(R.id.buttonHighscorePlayMenu);
		highscoreButton.setOnClickListener(this);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		SoundtrackManager.startMediaPlayer(this, R.raw.soundtrack1);
		continueMusic = false;
	}

	@Override
	protected void onPause() {
		super.onPause();
		
		if (!continueMusic) SoundtrackManager.pauseMediaPlayer();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	public void onClick(View view) {
		if (view == backButton) {
			SoundEffectManager.playSoundEffect("button.mp3");
			continueMusic = true;
			finish();
		}
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_MENU) {
			continueMusic = true;
			Intent intent = new Intent(this, OptionsMenuActivity.class);
			startActivity(intent);
		} else if (keyCode == KeyEvent.KEYCODE_BACK) {
			continueMusic = true;
			finish();
		}
		
		return true;
	}
	
//////////////////////////////////////////////////Methoden///////////////////////////////////////////////////
	
	
	
	
	
///////////////////////////////////////////////Innere Klassen////////////////////////////////////////////////	
	
	
	
	
}
