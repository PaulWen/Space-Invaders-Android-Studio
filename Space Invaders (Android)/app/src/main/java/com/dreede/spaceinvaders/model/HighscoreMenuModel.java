package com.dreede.spaceinvaders.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.dreede.spaceinvaders.model.dataObjects.HighscoreDataObject;
import com.dreede.spaceinvaders.model.databases.HighscoreDatabaseManager;

/**
 * Die Klasse {@link HighscoreMenuModel} [...]
 * 
 * 
 * @author Paul Wenzel
 *
 */
public class HighscoreMenuModel {
	
/////////////////////////////////////////////////Datenfelder/////////////////////////////////////////////////
	
	private HighscoreDatabaseManager highscoreDatabaseManager;
	private SQLiteDatabase highscoreDatabase;
	
/////////////////////////////////////////////////Konstruktor/////////////////////////////////////////////////
	
	/**
	 * Der Konstruktor der Klasse {@link HighscoreMenuModel}. 
	 */
	public HighscoreMenuModel(Context context) {
		// Datenfelder initialisieren
		highscoreDatabaseManager = new HighscoreDatabaseManager(context);
	}
	
	
	
//////////////////////////////////////////////Getter und Setter//////////////////////////////////////////////
	
	
	
	
	
	
///////////////////////////////////////////////geerbte Methoden//////////////////////////////////////////////
	
	

	
	
	
//////////////////////////////////////////////////Methoden///////////////////////////////////////////////////
	
	private void open() {
		highscoreDatabase = highscoreDatabaseManager.getReadableDatabase();
	}

	private void close() {
		highscoreDatabaseManager.close();
	}
	
	public List<HighscoreDataObject> getAllCommentsSorted() {
		open();
		
		List<HighscoreDataObject> highscoreDataObjectList = new ArrayList<HighscoreDataObject>();

	    Cursor cursor = highscoreDatabase.query(HighscoreDatabaseManager.HIGHSCORE_TABLE_NAME,
	    										null, null, null, null, null, null);

	    cursor.moveToFirst();
	    while (!cursor.isAfterLast()) {
	      highscoreDataObjectList.add(new HighscoreDataObject(cursor.getInt(cursor.getColumnIndex(HighscoreDatabaseManager.POINTS_ROW_NAME)),
	    		  											  cursor.getInt(cursor.getColumnIndex(HighscoreDatabaseManager.WAVE_ROW_NAME))));
	      cursor.moveToNext();
	    }
	    // make sure to close the cursor
	    cursor.close();
	    
	    close();
	    
	    Collections.sort(highscoreDataObjectList);
	    Collections.reverse(highscoreDataObjectList);
	    return highscoreDataObjectList;
	  }
	
///////////////////////////////////////////////Innere Klassen////////////////////////////////////////////////	
	
	
	
	
}
