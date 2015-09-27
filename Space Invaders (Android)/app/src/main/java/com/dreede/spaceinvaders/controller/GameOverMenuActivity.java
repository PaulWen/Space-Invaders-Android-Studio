package com.dreede.spaceinvaders.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.dreede.spaceinvaders.R;
import com.dreede.spaceinvaders.model.GameOverMenuModel;
import com.dreede.spaceinvaders.model.dataObjects.HighscoreDataObject;
import com.dreede.spaceinvaders.util.SoundEffectManager;
import com.dreede.spaceinvaders.util.SoundtrackManager;

/**
 * Die Klasse {@link GameOverMenuActivity} ist ein Menü 
 * 
 * 
 * @author Paul Wenzel
 *
 */
public class GameOverMenuActivity extends Activity implements OnClickListener {
	
/////////////////////////////////////////////////Datenfelder/////////////////////////////////////////////////
	
	public static final String INTENT_EXTRA_POINTS = "points";
	public static final String INTENT_EXTRA_WAVE = "wave";
	
	private GameOverMenuModel model;
	
	private Button restartButton;
	private Button startMenuButton;
	private boolean continueMusic;
	
/////////////////////////////////////////////////Konstruktor/////////////////////////////////////////////////
	
	/**
	 * Der Konstruktor der Klasse {@link GameOverMenuActivity}. 
	 */
	public GameOverMenuActivity() {
		// Datenfelder initialisieren
		
	}
	
	
	
//////////////////////////////////////////////Getter und Setter//////////////////////////////////////////////
	
	
	
	
	
	
///////////////////////////////////////////////geerbte Methoden//////////////////////////////////////////////
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game_over);
		
		// Datenfelder initialisieren
		model = new GameOverMenuModel(this);
		
		restartButton = (Button)findViewById(R.id.buttonRestartGameOverMenu);
		restartButton.setOnClickListener(this);
		startMenuButton = (Button)findViewById(R.id.buttonStartMenuGameOverMenu);
		startMenuButton.setOnClickListener(this);
		
		// Daten Laden
		model.openHighscoreDatabase();
			HighscoreDataObject highscoreDataObject = new HighscoreDataObject(getIntent().getExtras().getInt(GameOverMenuActivity.INTENT_EXTRA_POINTS), getIntent().getExtras().getInt(GameOverMenuActivity.INTENT_EXTRA_WAVE));
			
			((TextView)findViewById(R.id.textViewPointsGameOverMenu)).setText(highscoreDataObject.getPoints() + "");
			((TextView)findViewById(R.id.textViewWaveGameOverMenu)).setText(highscoreDataObject.getWave() + "");
			
			if (model.isNewHighestPointScore(highscoreDataObject.getPoints())) {
				((TextView)findViewById(R.id.textViewPointsNewRecordGameOverMenu)).setVisibility(View.VISIBLE);
			} else {
				((TextView)findViewById(R.id.textViewPointsNewRecordGameOverMenu)).setVisibility(View.GONE);
			}
			if (model.isNewHighestWaveScore(highscoreDataObject.getWave())) {
				((TextView)findViewById(R.id.textViewWaveNewRecordGameOverMenu)).setVisibility(View.VISIBLE);
			} else {
				((TextView)findViewById(R.id.textViewWaveNewRecordGameOverMenu)).setVisibility(View.GONE);
			}
			
			model.addHighscoreDataObject(highscoreDataObject);
		model.closeHighscoreDatabase();
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
		} else if (view == startMenuButton) {
			SoundEffectManager.playSoundEffect("button.mp3");
			continueMusic = true;
			Intent intent = new Intent(this, StartMenuActivity.class);
			startActivity(intent);
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
			Intent intent = new Intent(this, StartMenuActivity.class);
			startActivity(intent);
		}
		
		return true;
	}

//////////////////////////////////////////////////Methoden///////////////////////////////////////////////////
	
	
	
	
	
///////////////////////////////////////////////Innere Klassen////////////////////////////////////////////////	
	
	
	
	
}
