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
 * Die Klasse {@link PauseMenuActivity} [...]
 * 
 * 
 * @author Paul Wenzel
 *
 */
public class PauseMenuActivity extends Activity implements OnClickListener {
	
/////////////////////////////////////////////////Datenfelder/////////////////////////////////////////////////
	
	private Button restartButton;
	private Button startMenuButton;
	private Button optionsButton;
	private Button backButton;
	private boolean continueMusic;
	
/////////////////////////////////////////////////Konstruktor/////////////////////////////////////////////////
	
	/**
	 * Der Konstruktor der Klasse {@link PauseMenuActivity}. 
	 */
	public PauseMenuActivity() {
		// Datenfelder initialisieren
		
	}
	
	
	
//////////////////////////////////////////////Getter und Setter//////////////////////////////////////////////
	
	
	
	
	
	
///////////////////////////////////////////////geerbte Methoden//////////////////////////////////////////////
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pause_menu);
		
		// Datenfelder initialisieren
		restartButton = (Button)findViewById(R.id.buttonRestartPauseMenu);
		restartButton.setOnClickListener(this);
		startMenuButton = (Button)findViewById(R.id.buttonStartMenuPauseMenu);
		startMenuButton.setOnClickListener(this);
		optionsButton = (Button)findViewById(R.id.buttonOptionsPauseMenu);
		optionsButton.setOnClickListener(this);
		backButton = (Button)findViewById(R.id.buttonBackPauseMenu);
		backButton.setOnClickListener(this);
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
		if (view == restartButton) {
			SoundEffectManager.playSoundEffect("button.mp3");
			continueMusic = true;
			Intent intent = new Intent(this, GameActivity.class);
			startActivity(intent);
		} else if (view == optionsButton) {
			SoundEffectManager.playSoundEffect("button.mp3");
			continueMusic = true;
			Intent intent = new Intent(this, OptionsMenuActivity.class);
			startActivity(intent);
		} else if (view == startMenuButton) {
			SoundEffectManager.playSoundEffect("button.mp3");
			continueMusic = true;
			Intent intent = new Intent(this, StartMenuActivity.class);
			startActivity(intent);
		} else if (view == backButton) {
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
