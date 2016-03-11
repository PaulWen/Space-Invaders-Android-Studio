package com.dreede.spaceinvaders.view;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.Matrix;
import android.util.Log;

import com.dreede.spaceinvaders.controller.animation.AnimationInterface;
import com.dreede.spaceinvaders.model.ViewModelInterface;
import com.dreede.spaceinvaders.model.entities.TexturedOpenGlObject;
import com.dreede.spaceinvaders.util.TextureHelper;
import com.dreede.spaceinvaders.util.shaderprograms.TextureShaderProgram;

import java.util.HashMap;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Die Klasse {@link GameRenderer} ist dafür verantowrtlich die Inhalte auf die GameSurfaceView zu zeichnen.
 * 
 * 
 * @author Paul Wenzel
 *
 */
public class GameRenderer implements Renderer {
	
/////////////////////////////////////////////////Datenfelder/////////////////////////////////////////////////

	/** welcher Activity der Renderer angehört, um Dateien laden zu können */
	private Context context;
	
	/** das Model über welches die View alle Objekte hohlen kann die sie zeichnen soll */
	private ViewModelInterface model;
	
	/** das TextureShaderProgram welches zum zeichnen des Objektes verwendet werden soll */
	private TextureShaderProgram textureShaderProgram;
	
	/** 
	 * die OpenGL-IDs der Texturen
	 * (Key: Andoid ResourceID der Bilddatei, Value: OpenGL-TextureID der Texture)
	 */
	private HashMap<Integer, Integer> textureIDMap;
	
	private float[] projectionMatrix;
	private float[] viewMatrix;
	private float[] projectionAndViewMatrix;
	
	/** das Interface, welches eine Methode enthält, die bei jedem Fram aufgerufen werden soll, um die Objekte zu animieren */
	private AnimationInterface toAnimate;
	
	/** der Zeitpunkt zudem der Letzte Frame gerendert wurde (Zeit in Millisekunden seit Januar 1, 1970 00:00:00.0 UTC) */
	private long lastFrame;
	
/////////////////////////////////////////////////Konstruktor/////////////////////////////////////////////////
	
	/**
	 * Der Konstruktor der Klasse {@link GameRenderer}.
	 * 
	 * @param context welcher Activity der Renderer angehört, um Dateien laden zu können
	 * @param model das Model über welches die View alle Objekte hohlen kann die sie zeichnen soll 
	 * @param toAnimate das Interface, welches eine Methode enthält, die bei jedem Fram aufgerufen werden soll, um
	 * 					die Objekte zu animieren
	 */
	public GameRenderer(Context context, ViewModelInterface model, AnimationInterface toAnimate) {
		// Datenfelder initialisieren
		this.context = context;
		this.model = model;
		
		textureShaderProgram = null;
		
		textureIDMap = new HashMap<Integer, Integer>();
		
		projectionMatrix = new float[16];
		viewMatrix = new float[16];
		projectionAndViewMatrix = new float[16];
		
		this.toAnimate = toAnimate;
		
		lastFrame = 0;
	}
	
//////////////////////////////////////////////Getter und Setter//////////////////////////////////////////////
	
	
	
	
	
	
///////////////////////////////////////////////geerbte Methoden//////////////////////////////////////////////
	
	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		////////////////////////////////OpenGL Konfigurieren////////////////////////////////
		
		// die Farbe setzen mit der der Bildschirm immer überzeichnet werden soll
		GLES20.glClearColor(0.0f, 1.0f, 0.0f, 0.0f);

		// PNG-Transperenz erhalten lassen
		GLES20.glEnable(GLES20.GL_BLEND);
		GLES20.glBlendFunc(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
		GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_LINEAR);

		////////////////////////////////Shader laden////////////////////////////////
		
		//TextureShaderProgramm laden
		textureShaderProgram = new TextureShaderProgram(context);

		////////////////////////////////Texturen laden////////////////////////////////

//		for (TexturedOpenGlObject entity : model.texturedEntitiesToDraw()) {
//			for (int spriteResourceID : entity.getSpriteResourceIDList()) {
//				// gucken, ob die gewünschte Bildressource schon geladen ist
//				// (ACHTUNG: Jede Datei ist dadurch immer nur in einer Größe vorhanden!!)
//				if (!textureIDMap.keySet().contains(spriteResourceID)) {
//					// falls die gewünschte Bildresource noch nicht geladen wurde diese laden
//					textureIDMap.put(spriteResourceID,
//									TextureHelper.loadTexture(context, spriteResourceID,
//									entity.getWidth(), entity.getHeight()));
//				}
//			}
//		}
	}
	
	@Override
	public void onSurfaceChanged(GL10 gl, int screenWidth, int screenHeight) {
		Log.d(getClass().getName(), "width:" + screenWidth);
		Log.d(getClass().getName(), "height:" + screenHeight);

		// Viewport neu setzen, damit es immernoch Fullscreen ist
		GLES20.glViewport(0, 0, screenWidth, screenHeight);

	    // die orthographische Matrix hohlen um direkt auf den Screen zeichnen zu können
		Matrix.orthoM(projectionMatrix, 0, 0f, screenWidth, 0.0f, screenHeight, 0f, 50f);

		// die Kamera so Positionieren, dass WorldCoordinates = ScreenCoordiantes
		Matrix.setLookAtM(viewMatrix, 0, 0f, 0f, 1f, 0f, 0f, 0f, 0f, 1.0f, 0.0f);
		
        // die Projektions- und Viewtransformation berrechnen mit welcher jeder Pukt multipliziert werden muss
		Matrix.multiplyMM(projectionAndViewMatrix, 0, projectionMatrix, 0, viewMatrix, 0);
	}
	
	@Override
	public void onDrawFrame(GL10 gl) {
		// alle Objekte animieren
		long renderDuration = System.currentTimeMillis() - lastFrame;
		toAnimate.animate(renderDuration);
		lastFrame += renderDuration;
		
		// jeden Pixel mit der zuvor in onSurfaceCreated() festgelegten Farbe überzeichnen
		GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
		
		////////////Zeichnen////////////
			// Objekte mit Textur zeichnen
		textureShaderProgram.useProgram();
		for (TexturedOpenGlObject entity : model.texturedEntitiesToDraw()) {
			// gucken, ob die gewünschte Bildressource schon geladen ist
			// (ACHTUNG: Jede Datei ist dadurch immer nur in einer Größe vorhanden!!)
			if (!textureIDMap.keySet().contains(entity.getCurrentSpriteResourceID())) {
				// falls die gewünschte Bildresource noch nicht geladen wurde, diese laden
				textureIDMap.put(entity.getCurrentSpriteResourceID(), 
								TextureHelper.loadTexture(context, entity.getCurrentSpriteResourceID(),
								entity.getWidth(), entity.getHeight()));
			}

			// das Objekt Zeichnen
			entity.draw(projectionAndViewMatrix, textureShaderProgram, textureIDMap.get(entity.getCurrentSpriteResourceID()));
		}
	}

//////////////////////////////////////////////////Methoden///////////////////////////////////////////////////
	
	/**
	 * Die Methode wird von der GameActivity aufgerufen, wenn die Activity fortgesetzt wird.
	 */
	public void onResume() {
		// lastFrame setzen
		lastFrame = System.currentTimeMillis();
    }
	
///////////////////////////////////////////////Innere Klassen////////////////////////////////////////////////	
	
	
}
