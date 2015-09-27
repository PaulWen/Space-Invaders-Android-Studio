package com.dreede.spaceinvaders.util;


/**
 * Die Klasse {@link Rectangle} stellt ein Rechteck da, welches in einem Koordinatensystem Positioniert ist,
 * welches den Ursprug in der unteren-linken-Ecke besitzt.
 * 
 * 
 * @author Paul Wenzel
 *
 */
public class Rectangle {
	
/////////////////////////////////////////////////Datenfelder/////////////////////////////////////////////////
	
	/** die x-Koordinate der unteren-linken-Ecke des Rechtecks */
	private float x;
	/** die Y-Koordinate der unteren-linken-Ecke des Rechtecks */
	private float y;
	/** die Breite des Rechteckes */
	private float width;
	/** die Höhe des Rechteckes */
	private float height;
	
/////////////////////////////////////////////////Konstruktor/////////////////////////////////////////////////
	
	/**
	 * Der Konstruktor der Klasse {@link Rectangle}.
	 * 
	 * @param x die x-Koordinate der unteren-linken-Ecke des Rechtecks
	 * @param y die Y-Koordinate der unteren-linken-Ecke des Rechtecks
	 * @param width die Breite des Rechteckes (der Wert muss >= 0 sein!)
	 * @param height die Höhe des Rechteckes (der Wert muss >= 0 sein!)
	 */
	public Rectangle(float x, float y, float width, float height) {
		// Datenfelder initialisieren
		this.x = x;
		this.y = y;
		if (width >= 0 && height >= 0) {
			this.width = width;
			this.height = height;
		} else {
			throw new IllegalArgumentException("Höhe und Breite müssen >= 0 sein!");
		}
	}
	
//////////////////////////////////////////////Getter und Setter//////////////////////////////////////////////
	
	/**
	 * Die Methode gibt die x-Koordinate der unteren-linken-Ecke des Rechtecks aus.
	 * 
	 * @return die x-Koordinate der unteren-linken-Ecke des Rechtecks
	 */
	public float getX() {
		return x;
	}
	/**
	 * Die Methode dient dazu die x-Koordinate der unteren-linken-Ecke des Rechtecks neu zusetzen.
	 * 
	 * @param die gewünschte neue x-Koordinate der unteren-linken-Ecke des Rechtecks
	 */
	public void setX(float x) {
		this.x = x;
	}
	
	/**
	 * Die Methode gibt die y-Koordinate der unteren-linken-Ecke des Rechtecks aus.
	 * 
	 * @return die y-Koordinate der unteren-linken-Ecke des Rechtecks
	 */
	public float getY() {
		return y;
	}
	/**
	 * Die Methode dient dazu die y-Koordinate der unteren-linken-Ecke des Rechtecks neu zusetzen.
	 * 
	 * @param die gewünschte neue y-Koordinate der unteren-linken-Ecke des Rechtecks
	 */
	public void setY(float y) {
		this.y = y;
	}

	/**
	 * Die Methode gibt die Breite des Rechteckes aus.
	 * 
	 * @return die Breite des Rechteckes
	 */
	public float getWidth() {
		return width;
	}
	/**
	 * Die Methode dient dazu die Breite des Rechteckes neu zusetzen.
	 * 
	 * @param die gewünschte neue Breite des Rechteckes
	 */
	public void setWidth(float width) {
		this.width = width;
	}
	
	/**
	 * Die Methode gibt die Höhe des Rechteckes aus.
	 * 
	 * @return die Höhe des Rechteckes
	 */
	public float getHeight() {
		return height;
	}
	/**
	 * Die Methode dient dazu die Höhe des Rechteckes neu zusetzen.
	 * 
	 * @param die gewünschte neue Höhe des Rechteckes
	 */
	public void setHeight(float height) {
		this.height = height;
	}
	
///////////////////////////////////////////////geerbte Methoden//////////////////////////////////////////////
	
	
	
	
//////////////////////////////////////////////////Methoden///////////////////////////////////////////////////
	
	/**
	 * Die Methode gibt die x-Koordinate der Linkenseite des Rechteckes aus.
	 * 
	 * @return die x-Koordinate der Linkenseite des Rechteckes
	 */
	public float left() {
		return x;
	}
	/**
	 * Die Methode gibt die x-Koordinate der RechteSeite des Rechteckes aus.
	 * 
	 * @return die x-Koordinate der RechteSeite des Rechteckes
	 */
	public float right() {
		return x + width;
	}
	
	/**
	 * Die Methode gibt die y-Koordinate der Unterseite des Rechteckes aus.
	 * 
	 * @return die y-Koordinate der Linkenseite des Rechteckes
	 */
	public float bottom() {
		return y;
	}
	/**
	 * Die Methode gibt die y-Koordinate der Oberseite des Rechteckes aus.
	 * 
	 * @return die y-Koordinate der RechteSeite des Rechteckes
	 */
	public float top() {
		return y + height;
	}
	
	/**
	 * Die Methode gibt die x-Koordiante des Mittelpunktes vom Rechteck aus.
	 * 
	 * @return die x-Koordiante des Mittelpunktes vom Rechteck
	 */
	public float centerX() {
		return x + width / 2;
	}
	
	/**
	 * Die Methode gibt die y-Koordiante des Mittelpunktes vom Rechteck aus.
	 * 
	 * @return die y-Koordiante des Mittelpunktes vom Rechteck
	 */
	public float centerY() {
		return y + height / 2;
	}
	
	
	/**
	 * Die Methode prüft, ob sich das Rechteck mit einem anderen Rechteck schneidet.
	 * 
	 * @param rectangle das Rechteck, welches auf Überscheidungen geprüft werden soll
	 * @return true: die Rechtecke überschneiden sich
	 */
    public boolean intersects(Rectangle rectangle) {
        return this.left() <= rectangle.right() && rectangle.left() <= this.right() && this.top() >= rectangle.bottom() && rectangle.top() >= this.bottom();
    }
    
    /**
     * Die Methode prüft, ob das Rechteck ein anderes Rechteck entält.
     * 
     * @param rectangle das andere Rechteck, bei dem geguckt werden soll, ob es in diesem Rechteck enthalten ist
     * @return true: das Rechteck entält das andere Rechteck
     */
    public boolean contains(Rectangle rectangle) {
        // gucken, dass das Rechteck überhaupt Volumen hat
    	return this.getWidth() > 0 && this.getHeight() > 0
        // gucken ob das andere Rechteck in diesem enthalten ist
        && this.left() <= rectangle.left() && this.top() >= rectangle.top() && this.right() >= rectangle.right() && this.bottom() <= rectangle.bottom();
    }
    
    public boolean contains(float x, float y) {
    	// gucken, dass das Rechteck überhaupt Volumen hat
    	return this.getWidth() > 0 && this.getHeight() > 0 
        // gucken ob der Punkt in diesem Rechteck enthalten ist
    	&& x >= this.left() && x <= this.right() && y <= this.top() && y >= this.bottom();
    }
	
///////////////////////////////////////////////Innere Klassen////////////////////////////////////////////////	
	
	
	
	
}
