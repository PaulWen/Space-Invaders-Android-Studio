package com.dreede.spaceinvaders.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.dreede.spaceinvaders.model.dataObjects.HighscoreDataObject;
import com.dreede.spaceinvaders.model.databases.HighscoreDatabaseManager;

/**
 * Die Klasse {@link GameOverMenuModel} [...]
 * 
 * 
 * @author Paul Wenzel
 *
 */
public class GameOverMenuModel {
	
/////////////////////////////////////////////////Datenfelder/////////////////////////////////////////////////
	
	private HighscoreDatabaseManager highscoreDatabaseManager;
	private SQLiteDatabase highscoreDatabase;
	
/////////////////////////////////////////////////Konstruktor/////////////////////////////////////////////////
	
	/**
	 * Der Konstruktor der Klasse {@link GameOverMenuModel}. 
	 */
	public GameOverMenuModel(Context context) {
		// Datenfelder initialisieren
		highscoreDatabaseManager = new HighscoreDatabaseManager(context);
	}
	
	
	
//////////////////////////////////////////////Getter und Setter//////////////////////////////////////////////
	
	
	
	
	
	
///////////////////////////////////////////////geerbte Methoden//////////////////////////////////////////////
	
	

	
	
	
//////////////////////////////////////////////////Methoden///////////////////////////////////////////////////
	
	/**
	 * Die Methode öffnet die Datenbank, in welcher die Highscore-Daten drin gespeichert sind.
	 */
	public void openHighscoreDatabase() {
		highscoreDatabase = highscoreDatabaseManager.getWritableDatabase();
	}

	/**
	 * Die Methode schließt die Datenbank, in welcher die Highscore-Daten drin gespeichert sind.
	 */
	public void closeHighscoreDatabase() {
		highscoreDatabaseManager.close();
	}
	
	/**
	 * Die Methdoe gibt alle Highscore-Daten in einer Liste als {@link HighscoreDataObject} aus.
	 * 
	 * @return Liste mit allen Highscore-Daten als {@link HighscoreDataObject}
	 */
	private List<HighscoreDataObject> getAllHighscoresSorted() {
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
	    
	    Collections.sort(highscoreDataObjectList);
	    Collections.reverse(highscoreDataObjectList);
	    return highscoreDataObjectList;
	  }
	
	/**
	 * Die Methode dient dazu ein {@link HighscoreDataObject} (Highscore-Daten) der Datenbank hinzuzufügen.
	 * Die Methode sorgt dabei selber dafür, dass nur die besten 10 Ergebnisse im Highscore gespeichert werden!
	 * Somit wird das {@link HighscoreDataObject} nicht aufgenommen, wenn das neu hinzugefügte {@link HighscoreDataObject}
	 * schlechter ist als 10 bereits im {@link HighscoreDataObject} stehenden {@link HighscoreDataObject}s.
	 * 
	 * @param highscoreDataObject das {@link HighscoreDataObject} was hinzugefügt werden soll, falls es wirklich ein Highscore unter den Top 10 ist
	 */
	public void addHighscoreDataObject(HighscoreDataObject highscoreDataObject) {
		List<HighscoreDataObject> highscoreDataObjectList = getAllHighscoresSorted();
		
		// alle Daten Löschen, um anschließend nur die Top10 einzufügen.
		highscoreDatabase.delete(HighscoreDatabaseManager.HIGHSCORE_TABLE_NAME, null, null);
		
		highscoreDataObjectList.add(highscoreDataObject);
		Collections.sort(highscoreDataObjectList);
		Collections.reverse(highscoreDataObjectList);
		
		for (int i = 0; i < highscoreDataObjectList.size() && i < 10; i++) {
			ContentValues values = new ContentValues();
			values.put(HighscoreDatabaseManager.POINTS_ROW_NAME, highscoreDataObjectList.get(i).getPoints());
			values.put(HighscoreDatabaseManager.WAVE_ROW_NAME, highscoreDataObjectList.get(i).getWave());
			highscoreDatabase.insert(HighscoreDatabaseManager.HIGHSCORE_TABLE_NAME, null, values);
		}
	}
	
	/**
	 * Die Methode prüft, ob der übergebene Punktestand eine neue Bestleistung ist (also im Highscore kein Score existiert,
	 * welcher eine höheren Punktestand aufweist als den übergebene Punktestand). 
	 * 
	 * @param points der Punktestand, welche überprüft werden soll, ob sein eine neue Bestleitung darstellt
	 * 
	 * @return true: der übergebene Punktestand isteine neune Bestleitung
	 * 		   false: der übergebene Punktestand ist KEINE neue Bestleistung
	 */
	public boolean isNewHighestPointScore(int points) {
		List<HighscoreDataObject> highscoreDataObjectList = getAllHighscoresSorted();
		
		if (!highscoreDataObjectList.isEmpty()) {
			return points > highscoreDataObjectList.get(0).getPoints();
		} else {
			return true;
		}
	}
	
	/**
	 * Die Methode prüft, ob die übergebene Wellennummer eine neue Bestleistung ist (also im Highscore kein Score existiert,
	 * welcher eine höhere Wellennummer aufweist als die übergebene Wellennummer). 
	 * 
	 * @param wave die Wellennummer, welche überprüft werden soll, ob sein eine neue Bestleitung darstellt
	 * 
	 * @return true: die übergebene Wellennummer isteine neune Bestleitung
	 * 		   false: die übergebene Wellennummer ist KEINE neue Bestleistung
	 */
	public boolean isNewHighestWaveScore(int wave) {
		int highestWaveScoreInList = 0;
		
		for (HighscoreDataObject highscoreDataObject : getAllHighscoresSorted()) {
			if (highscoreDataObject.getWave() > highestWaveScoreInList) {
				highestWaveScoreInList = highscoreDataObject.getWave();
			}
		}
		
		return wave > highestWaveScoreInList;
	}
	
///////////////////////////////////////////////Innere Klassen////////////////////////////////////////////////	
	
	
	
	
}
