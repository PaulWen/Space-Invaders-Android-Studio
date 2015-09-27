package com.dreede.spaceinvaders.util.shaderprograms;

import android.content.Context;
import android.opengl.GLES20;

import com.dreede.spaceinvaders.util.ShaderHelper;
import com.dreede.spaceinvaders.util.TextResourceReader;


/**
 * Die abstrakte Klasse {@link ShaderProgram} definiert die grundlegensten Datenfelder und Methoden eines Shaderprogramms.
 * Von dieser Klasse soll jede Klasse erben die ein Shaderprogramm verwaltet.
 * 
 * 
 * @author Paul Wenzel
 *
 */
public abstract class ShaderProgram {
	
/////////////////////////////////////////////////Datenfelder/////////////////////////////////////////////////
	
	// Uniform Konstanten
	protected static final String U_MATRIX = "u_Matrix";
	protected static final String U_COLOR = "u_Color";
	protected static final String U_TEXTURE_UNIT = "u_TextureUnit";
	
	// Attribut Konstanten
	protected static final String A_POSITION = "a_Position";    
	protected static final String A_COLOR = "a_Color";    
	protected static final String A_TEXTURE_COORDINATES = "a_TextureCoordinates";
	
	// Shaderprogramm
	protected final int program;
	
/////////////////////////////////////////////////Konstruktor/////////////////////////////////////////////////
	
	/**
	 * Der Konstruktor der abstrakten Klasse {@link ShaderProgram}. 
	 * 
	 * @param context welcher Activity der Renderer angehört, um den benötigten Shadercode zu laden
	 * @param vertexShaderResourceId die ID der Datei, welche den Vertexshadercode enthält
	 * @param fragmentShaderResourceId die ID der Datei, welche den Fragmentshadercode enthält
	 */
	protected ShaderProgram(Context context, int vertexShaderResourceId,
		// Datenfelder initialisieren
		int fragmentShaderResourceId) {
		// erzeuge ein Shaderprogramm aus dem gewünschten Vertex- und Fragmentshader
		program = ShaderHelper.buildProgram(TextResourceReader.readTextFileFromResource(context, vertexShaderResourceId),
											TextResourceReader.readTextFileFromResource(context, fragmentShaderResourceId));
	}        
	
//////////////////////////////////////////////Getter und Setter//////////////////////////////////////////////
	
	
	
	
	
///////////////////////////////////////////////geerbte Methoden//////////////////////////////////////////////
	
	/**
	 * Die Methode setzt dieses Shaderprogramm als das aktuell zu verwendenen Shaderprogramm.
	 */
	public void useProgram() {
		GLES20.glUseProgram(program);
	}
	
//////////////////////////////////////////////////Methoden///////////////////////////////////////////////////
	
	
	
	
	
///////////////////////////////////////////////Innere Klassen////////////////////////////////////////////////	
	
	
	
	
}
