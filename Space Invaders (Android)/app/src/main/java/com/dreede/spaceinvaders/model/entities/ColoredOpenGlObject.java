package com.dreede.spaceinvaders.model.entities;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import android.opengl.GLES20;

import com.dreede.spaceinvaders.util.Rectangle;
import com.dreede.spaceinvaders.util.shaderprograms.TextureShaderProgram;

/**
 * Die abstrakte Klasse {@link ColoredOpenGlObject} enthält alle Informationen, um das Objekt mit einer Farbe in OpenGL zu zeichnen.
 * Falls die Entities weitere Fähigkeiten haben möchten (z.B. zerstörbar, beweglich...) so müssen die die Passenden
 * SkillInterfaces implementieren und werden dadurch gezwungen die SkilObjekte zu haben.
 * 
 * 
 * @author Paul Wenzel
 *
 */
public class ColoredOpenGlObject implements Serializable {
	
/////////////////////////////////////////////////Datenfelder/////////////////////////////////////////////////
	
	////////////////////////////////ZEICHNEN////////////////////////////////
	/** Anzahl der Bytes(8-bit) pro Float(32-bit) */
	private static final int BYTES_PER_FLOAT = 4;
	/** Anzahl der Koordinaten, die einen Koordinatenpunkt des Objektes repräsentieren */
	private static final int POSITION_COMPONENT_COUNT = 2;
	/** Anzahl der Koordinaten, die einen Texturpunkt repräsentieren */
	private static final int TEXTURE_COORDINATES_COMPONENT_COUNT = 2;
	
	
	/** Buffer mit den Weltkoordinaten von diesem Objekt */
	private FloatBuffer vertexData;
	/** Buffer mit den Texturkoordinaten von diesem Objekt */
	private FloatBuffer textureVertexData;

	/** Breite des Objekts */
	private float width;
	/** Höhe des Objekts */
	private float height;
	
	/** x-Koordinate der unteren-linken-Ecke des Objektes */
	private float x;
	/** y-Koordinate der unteren-linken-Ecke des Objektes */
	private float y;
	
	/** eine Liste mit allen spriteResourceIDs der gewünschten Texturen */
	private int[] spriteResourceIDList;
	/** der Index der gewünschten Textur aus der spriteResourceIDList */
	private int currentSpriteIndexNumber;
	
/////////////////////////////////////////////////Konstruktor/////////////////////////////////////////////////
	
	/**
	 * Der Konstruktor der abstrakten Klasse {@link ColoredOpenGlObject}. 
	 * HINWEIS: Nur die Paramter angeben die zu den gewünschten Fähigkeit gehören! Die anderen auf NULL setzen!
	 * 
	 * @param spriteResourceIDList die ResourceID, welche der gewüschten Bilddatei zugeordnet ist
	 * @param width Breite des Objekts
	 * @param height Höhe des Objekts
	 * @param x x-Koordinate der unteren-linken-Ecke des Objektes
	 * @param y y-Koordinate der unteren-linken-Ecke des Objektes
	 */
	public ColoredOpenGlObject(int[] spriteResourceIDList, float width, float height, float x, float y) {
		// Datenfelder initialisieren

		////////////////////////////////ZEICHNEN////////////////////////////////
		this.width = width;
		this.height = height;
		
		this.x = 0;
		this.y = 0;
		addMovement(x, y);
		
		this.spriteResourceIDList = spriteResourceIDList;
		currentSpriteIndexNumber = 0;
		
		float[] textureVertices = new float[] {
				// Ordnung der koordinaten: S, T
				0, 0,
				0, -1,
				1, -1,
				
				1, -1,
				1, 0,
				0, 0
		};
		
		// The vertex buffer.
		textureVertexData = ByteBuffer
				.allocateDirect(textureVertices.length * BYTES_PER_FLOAT) // den benötigten Speicherplatz resavieren
				.order(ByteOrder.nativeOrder()) // auslesen wie herum das verwendete System Bytes liest (Endianness - Byte-Reihenfolge) und diese Leserichtung für den ByteBuffer ebenfalls einstellen
				.asFloatBuffer(); // aus dem ByteBuffer ein FloatBuffer machen
		textureVertexData.put(textureVertices); // den erstellten FloatBuffer mit den Koordinaten füllen
		textureVertexData.position(0); // den Zeiger des FloatBuffers auf das erste Elemnet setzen
	}
	
//////////////////////////////////////////////Getter und Setter//////////////////////////////////////////////
	
	////////////////////////////////ZEICHNEN////////////////////////////////
	/**
	 * Die Methode gibt die x-Koordinate der unteren-linken-Ecke des Objektes aus.
	 * 
	 * @return x-Koordinate der unteren-linken-Ecke des Objektes
	 */
	public float getX() {
		return x;
	}
	/**
	 * Die Methode gibt die y-Koordinate der unteren-linken-Ecke des Objektes aus.
	 * 
	 * @return y-Koordinate der unteren-linken-Ecke des Objektes
	 */
	public float getY() {
		return y;
	}
	
	
	/**
	 * Die Methode ermöglicht es die x-Koordinate der unteren-linken-Ecke des Objektes neu zusetzen.
	 * 
	 * @return die gewünschte neue x-Koordinate der unteren-linken-Ecke des Objektes
	 */
	public void setX(float x) {
		this.x = x;
	}
	/**
	 * Die Methode ermöglicht es die y-Koordinate der unteren-linken-Ecke des Objektes neu zusetzen.
	 * 
	 * @return die gewünschte neue y-Koordinate der unteren-linken-Ecke des Objektes
	 */
	public void setY(float y) {
		this.y = y;
	}
	
	/**
	 * Die Methode ist dafür da, um das Objekt zu bewegen.
	 * 
	 * @param xMovement um wie viele Pixel sich das Objekt auf der x-Achse bewegen soll
	 * @param yMovement um wie viele Pixel sich das Objekt auf der y-Achse bewegen soll
	 */
	protected void addMovement(float xMovement, float yMovement) {
		// Objekt bewegen
		x += xMovement;
		y += yMovement;
		
		// Die Punkte neu setzen, dabei die Höhe und Breite des Objektes beachten, damit es die gewünschten Dimensionen annimmt.
		float[] vertices = new float[] {
				// Ordnung der koordinaten: X, Y
				x, y,
				x, y + height,
				x + width, y + height,
				
				x + width, y + height,
				x + width, y,
				x, y
		};
		
		// The vertex buffer.
		vertexData = ByteBuffer
				.allocateDirect(vertices.length * BYTES_PER_FLOAT) // den benötigten Speicherplatz resavieren
				.order(ByteOrder.nativeOrder()) // auslesen wie herum das verwendete System Bytes liest (Endianness - Byte-Reihenfolge) und diese Leserichtung für den ByteBuffer ebenfalls einstellen
				.asFloatBuffer(); // aus dem ByteBuffer ein FloatBuffer machen
		vertexData.put(vertices); // den erstellten FloatBuffer mit den Koordinaten füllen
		vertexData.position(0); // den Zeiger des FloatBuffers auf das erste Elemnet setzen
	}
	
	/**
	 * Die Methode gibt die gerundete Höhe des Objekts aus.
	 * 
	 * @return Höhe des Objekts
	 */
	public int getHeight() {
		return Math.round(height);
	}
	/**
	 * Die Methode gibt die gerundete Breite des Objekts aus.
	 * 
	 * @return Breite des Objekts
	 */
	public int getWidth() {
		return Math.round(width);
	}	

	
	/**
	 * Die Methode gibt eine Liste mit allen ResourceIDs der gewünschten Texturen aus.
	 * 
	 * @return
	 */
	public int[] getSpriteResourceIDList() {
		return spriteResourceIDList;
	}
	/**
	 * Die Methode gibt die ResourceID der aktuell gewünschten Texture aus.
	 * 
	 * @return die ResourceID der gewünschten Texture
	 */
	public int getCurrentSpriteResourceID() {
		return spriteResourceIDList[currentSpriteIndexNumber];
	}
	/**
	 * Die Methode sorg dafür, dass die nächste vorhhandene Textur als die anzuzeigende gesetzt wird.
	 * Falls breits die letzte vorhandene Textur ausgeählt ist wird weiterhin diese angezeigt.
	 */
	public void nextSprite() {
		if (currentSpriteIndexNumber + 1 != spriteResourceIDList.length) {
			currentSpriteIndexNumber++;
		}
	}
	/**
	 * Die Methode sorg dafür, dass die erste Textur als die anzuzeigende gesetzt wird.
	 */
	public void firstSprite() {
		currentSpriteIndexNumber = 0;
	}
	/**
	 * Die Methode gibt aus der wie vielte Texture gerade angezeigt wird.
	 * 
	 * @return die wie vielte Texture gerade angezeigt wird (Bsp.: 0 = 1. Texture)
	 */
	public int getCurrentSpriteIndexNumber() {
		return currentSpriteIndexNumber;
	}
	
	/**
	 * Die Methode gibt die Anzahl an Texturen die es für das Objekt gibt aus.
	 * 
	 * @return Anzahl an Texturen für das Objekt
	 */
	public int getNumberOfSprites() {
		return spriteResourceIDList.length;
	}
	
	/**
	 * Die Methode gibt ein {@link Rectangle} aus, welches das Objekt repräsentiert.
	 * Das {@link Rectangle} kann zum Beispiel zu Kollisionsüberprüfung verwendet werden.
	 * 
	 * @return ein {@link Rectangle}, welches das Objekt repräsentiert
	 */
	public Rectangle getPolygon() {
		return new Rectangle(getX(), getY(), getWidth(), getHeight());
	}
	
///////////////////////////////////////////////geerbte Methoden//////////////////////////////////////////////
	
	

	
	
	
//////////////////////////////////////////////////Methoden///////////////////////////////////////////////////
	
	/**
	 * Die Methode zeichnet das Objekt auf den Bildschirm.
	 * 
	 * @param projectionAndViewMatrix die TransformationMatrix um das Objekt richtig zu Positionieren
	 * @param shaderProgram das TextureShaderProgram um das Objekt mit Texture zu zeichnen
	 * @param textureID die OpenGL-ID der gewünschten Texture
	 */
	public void draw(float[] projectionAndViewMatrix, TextureShaderProgram shaderProgram, int textureID) {
		// die Uniforms setzen
		shaderProgram.setUniforms(projectionAndViewMatrix, textureID);
		
		// der Vertex-Variable des VertexShaders die Daten geben
		GLES20.glVertexAttribPointer(shaderProgram.getPositionAttributeLocation(), POSITION_COMPONENT_COUNT, GLES20.GL_FLOAT, false, 0, vertexData); // den FloatBuffer mit den Vertex-Daten der Vertex-Variable des VertexShaders zuweisen
		GLES20.glVertexAttribPointer(shaderProgram.getTextureCoordinatesAttributeLocation(), TEXTURE_COORDINATES_COMPONENT_COUNT, GLES20.GL_FLOAT, false, 0, textureVertexData); // den FloatBuffer mit den Vertex-Daten der Vertex-Variable des VertexShaders zuweisen
		
		// die Verknüpften Daten freigeben
		GLES20.glEnableVertexAttribArray(shaderProgram.getPositionAttributeLocation());
		GLES20.glEnableVertexAttribArray(shaderProgram.getTextureCoordinatesAttributeLocation());
		
		// die ersten 6 Punkte als zwei Dreicke zeichnen
		GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, 6);
		
		// die Verknüpften Daten wieder lösen
		GLES20.glDisableVertexAttribArray(shaderProgram.getPositionAttributeLocation());
		GLES20.glDisableVertexAttribArray(shaderProgram.getTextureCoordinatesAttributeLocation());
	}
	
///////////////////////////////////////////////Innere Klassen////////////////////////////////////////////////	
	
	
	
	
}
