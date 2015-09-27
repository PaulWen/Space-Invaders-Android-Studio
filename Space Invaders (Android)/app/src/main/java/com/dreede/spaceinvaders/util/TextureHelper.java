package com.dreede.spaceinvaders.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.util.Log;

/**
 * Die Klasse {@link TextureHelper} stellte eine Methode zu verfügung, um eine Bilddatei in ein OpenGL
 *  Texturobjekt zu laden. Das OpenGL Texturobjekt kann dann verwendet werden, um Objekte zu gestalten.
 * 
 * 
 * @author Paul Wenzel
 *
 */
public class TextureHelper {
	
/////////////////////////////////////////////////Datenfelder/////////////////////////////////////////////////
	

/////////////////////////////////////////////////Konstruktor/////////////////////////////////////////////////
	
	/**
	 * Der Konstruktor der Klasse {@link TextureHelper}. 
	 */
	public TextureHelper() {
		// Datenfelder initialisieren
		
	}
	
//////////////////////////////////////////////Getter und Setter//////////////////////////////////////////////
	
	
	
	
	
	
///////////////////////////////////////////////geerbte Methoden//////////////////////////////////////////////
	
	

	
	
	
//////////////////////////////////////////////////Methoden///////////////////////////////////////////////////
	
	/**
	 * Die Methode lädt eine Bilddatei und erstellte eine OpenGL-Texture, von welcher sie die ID zurückgibt.
	 * 
	 * @param context welcher Activity der Renderer angehört, um auf die gewünschte Bilddatei zugreifen zu können
	 * @param spriteResourceID die ResourceID, welche der gewüschten Bilddatei zugeordnet ist
	 * @param width die gewünschte Breite, die die Textur haben soll
	 * @param height die gewünschte Höhe, die die Textur haben soll
	 * 
	 * @returndie OpenGL-ID der gewünschten Texture oder 0 falls ein Fehler aufgetreten ist
	 */
	public static int loadTexture(Context context, int spriteResourceID, int width, int height) {
		final int[] textureObjectIds = new int[1];
		GLES20.glGenTextures(1, textureObjectIds, 0);
		
		if (textureObjectIds[0] == 0) {
			if (LoggerConfig.ERROR) Log.e(TextureHelper.class.getClass().getName(), "Es konnte kein neues Texturobjekt erzeugt werden.");
			return 0;
		}
		
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inScaled = false;
		
		// die Textur in der gewünschten Größe in ein Bitmap-Objekt laden für die weitere verarbeitung
		final Bitmap bitmap = Bitmap.createScaledBitmap( BitmapFactory.decodeResource(context.getResources(), spriteResourceID), width, height, false);
		
		if (bitmap == null) {
			if (LoggerConfig.ERROR) Log.e(TextureHelper.class.getClass().getName(), "Die Textur konnte nicht geladen werden.");
			
			GLES20.glDeleteTextures(1, textureObjectIds, 0);
			
			return 0;
		} 
		
		// die Textur in OpenGL einbinden
		GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureObjectIds[0]);
		
		// die Filter fürs Hoch- und Runterskallieren bestimmen
	    GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_NEAREST);
	    GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_NEAREST);
	 
		// die Bitmap in das gebundene Texturobjekt laden
		GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bitmap, 0);
		
		// die Bitmap löschen, da sie nun nicht mehr gebraucht wird, nachdem sie in OpenGL geladen wurde
		bitmap.recycle();
		
		// die Textur von OpenGl lösen, indem dem der Wert "0" übergeben wird
		GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, 0);
		
		return textureObjectIds[0];        
	}
	
///////////////////////////////////////////////Innere Klassen////////////////////////////////////////////////	
	
	
	
	
}
