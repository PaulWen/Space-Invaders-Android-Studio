package com.dreede.spaceinvaders.controller;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.dreede.spaceinvaders.R;
import com.dreede.spaceinvaders.model.HighscoreMenuModel;
import com.dreede.spaceinvaders.model.dataObjects.HighscoreDataObject;
import com.dreede.spaceinvaders.util.SoundEffectManager;
import com.dreede.spaceinvaders.util.SoundtrackManager;

/**
 * Die Klasse {@link HighscoreMenuActivity} [...]
 * 
 * 
 * @author Paul Wenzel
 *
 */
public class HighscoreMenuActivity extends Activity implements OnClickListener {
	
/////////////////////////////////////////////////Datenfelder/////////////////////////////////////////////////

	private HighscoreMenuModel model;
	
	private Button backButton;
	
	private boolean continueMusic;
	
/////////////////////////////////////////////////Konstruktor/////////////////////////////////////////////////
	
	/**
	 * Der Konstruktor der Klasse {@link HighscoreMenuActivity}. 
	 */
	public HighscoreMenuActivity() {
		// Datenfelder initialisieren
		
	}
	
	
	
//////////////////////////////////////////////Getter und Setter//////////////////////////////////////////////
	
	
	
	
	
	
///////////////////////////////////////////////geerbte Methoden//////////////////////////////////////////////
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.highscore_menu);
		
		// Datenfelder initialisieren
		model = new HighscoreMenuModel(this);
		backButton = (Button)findViewById(R.id.buttonBackHighscoreMenu);
		backButton.setOnClickListener(this);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		SoundtrackManager.startMediaPlayer(this, R.raw.soundtrack1);
		continueMusic = false;
		
		// Daten laden
		List<HighscoreDataObject> highscoreDataObjectList = model.getAllCommentsSorted();
		
		if (highscoreDataObjectList.size() > 0) {
			((TextView)findViewById(R.id.TextViewPoints1HighscoreMenu)).setText(highscoreDataObjectList.get(0).getPoints() + "");
			((TextView)findViewById(R.id.TextViewWave1HighscoreMenu)).setText(highscoreDataObjectList.get(0).getWave() + "");
		}
		if (highscoreDataObjectList.size() > 1) {
			((TextView)findViewById(R.id.TextViewPoints2HighscoreMenu)).setText(highscoreDataObjectList.get(1).getPoints() + "");
			((TextView)findViewById(R.id.TextViewWave2HighscoreMenu)).setText(highscoreDataObjectList.get(1).getWave() + "");
		}
		if (highscoreDataObjectList.size() > 2) {
			((TextView)findViewById(R.id.TextViewPoints3HighscoreMenu)).setText(highscoreDataObjectList.get(2).getPoints() + "");
			((TextView)findViewById(R.id.TextViewWave3HighscoreMenu)).setText(highscoreDataObjectList.get(2).getWave() + "");
		}
		if (highscoreDataObjectList.size() > 3) {
			((TextView)findViewById(R.id.TextViewPoints4HighscoreMenu)).setText(highscoreDataObjectList.get(3).getPoints() + "");
			((TextView)findViewById(R.id.TextViewWave4HighscoreMenu)).setText(highscoreDataObjectList.get(3).getWave() + "");
		}
		if (highscoreDataObjectList.size() > 4) {
			((TextView)findViewById(R.id.TextViewPoints5HighscoreMenu)).setText(highscoreDataObjectList.get(4).getPoints() + "");
			((TextView)findViewById(R.id.TextViewWave5HighscoreMenu)).setText(highscoreDataObjectList.get(4).getWave() + "");
		}
		if (highscoreDataObjectList.size() > 5) {
			((TextView)findViewById(R.id.TextViewPoints6HighscoreMenu)).setText(highscoreDataObjectList.get(5).getPoints() + "");
			((TextView)findViewById(R.id.TextViewWave6HighscoreMenu)).setText(highscoreDataObjectList.get(5).getWave() + "");
		}
		if (highscoreDataObjectList.size() > 6) {
			((TextView)findViewById(R.id.TextViewPoints7HighscoreMenu)).setText(highscoreDataObjectList.get(6).getPoints() + "");
			((TextView)findViewById(R.id.TextViewWave7HighscoreMenu)).setText(highscoreDataObjectList.get(6).getWave() + "");
		}
		if (highscoreDataObjectList.size() > 7) {
			((TextView)findViewById(R.id.TextViewPoints8HighscoreMenu)).setText(highscoreDataObjectList.get(7).getPoints() + "");
			((TextView)findViewById(R.id.TextViewWave8HighscoreMenu)).setText(highscoreDataObjectList.get(7).getWave() + "");
		}
		if (highscoreDataObjectList.size() > 8) {
			((TextView)findViewById(R.id.TextViewPoints9HighscoreMenu)).setText(highscoreDataObjectList.get(8).getPoints() + "");
			((TextView)findViewById(R.id.TextViewWave9HighscoreMenu)).setText(highscoreDataObjectList.get(8).getWave() + "");
		}
		if (highscoreDataObjectList.size() > 9) {
			((TextView)findViewById(R.id.TextViewPoints10HighscoreMenu)).setText(highscoreDataObjectList.get(9).getPoints() + "");
			((TextView)findViewById(R.id.TextViewWave10HighscoreMenu)).setText(highscoreDataObjectList.get(9).getWave() + "");
		}
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
