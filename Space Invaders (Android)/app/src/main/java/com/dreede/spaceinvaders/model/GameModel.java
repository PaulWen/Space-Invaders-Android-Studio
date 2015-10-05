package com.dreede.spaceinvaders.model;

import com.dreede.spaceinvaders.Config;
import com.dreede.spaceinvaders.R;
import com.dreede.spaceinvaders.model.dataObjects.GameModelDataToSaveDataObject;
import com.dreede.spaceinvaders.model.entities.Alien;
import com.dreede.spaceinvaders.model.entities.Barricade;
import com.dreede.spaceinvaders.model.entities.Explosion;
import com.dreede.spaceinvaders.model.entities.Player;
import com.dreede.spaceinvaders.model.entities.Shoot;
import com.dreede.spaceinvaders.model.entities.TexturedOpenGlObject;
import com.dreede.spaceinvaders.model.entities.buttons.ImageViewButton;
import com.dreede.spaceinvaders.model.entities.skills.DestroyableSkillInterface;
import com.dreede.spaceinvaders.util.Rectangle;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Die Klasse {@link GameModel} ist das Model vom Spiel. Es speichert und verwaltet alle Objekte auf dem Spielfeld.
 * 
 * 
 * @author Paul Wenzel
 *
 */
public class GameModel implements ViewModelInterface, PlayerAnimationModelInterface, AlienAnimationModelInterface,
								  BarricadeAnimationModelInterface, ShootAnimationModelInterface, ExplosionAnimationModelInterface,
								  ButtonAnimationModelInterface {
	
/////////////////////////////////////////////////Datenfelder/////////////////////////////////////////////////
	
	private int currentWave;
	
	private TexturedOpenGlObject background;
	/** der Spieler */
	private Player player;
	/** Liste mit allen Aliens */
	private ArrayList<Alien> alienList;
	/** Liste mit allen Barrikaden */
	private ArrayList<Barricade> barricadenList;
	/** Liste mit allen Schüssen */
	private ArrayList<Shoot> shootList;
	/** Liste mit allen Explosionen */
	private ArrayList<Explosion> explosionList;
	/** Liste mit allen Grenzen, welche als Rechtecke dargestellt sind - innerhalb dieser Rechtecke, darf sich das Objekt NICHT befinden */
	private ArrayList<Rectangle> boundaryList;
	/** Liste mit allen Buttons */
	private ImageViewButton moveLeftButton;
	private ImageViewButton moveRightButton;
	private ImageViewButton shootButton;
	
/////////////////////////////////////////////////Konstruktor/////////////////////////////////////////////////
	
	/**
	 * Der Konstruktor der Klasse {@link GameModel}. 
	 */
	public GameModel(ImageViewButton moveLeftButton, ImageViewButton moveRightButton, ImageViewButton shootButton) {
		// Datenfelder initialisieren
		currentWave = 0;
		background = new TexturedOpenGlObject(new int[]{R.drawable.background}, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT, 0, 0);
		player = null;
		alienList = new ArrayList<Alien>();
		barricadenList = new ArrayList<Barricade>();
		shootList = new ArrayList<Shoot>();
		explosionList = new ArrayList<Explosion>();
		boundaryList = new ArrayList<Rectangle>();

		this.moveLeftButton = moveLeftButton;
		this.moveRightButton = moveRightButton;
		this.shootButton = shootButton;
		
		///////////vorübergehendes laden von Objekten///////////
		player = new Player(200, 100);
		alienList.add(new Alien.Builder(Alien.Builder.ALIEN_1, 500, 500).getAlien());
		alienList.add(new Alien.Builder(Alien.Builder.ALIEN_1, 580, 500).getAlien());
		barricadenList.add(new Barricade(500, 200));
		barricadenList.add(new Barricade(750, 200));
		barricadenList.add(new Barricade(1000, 200));
		
		boundaryList.add(new Rectangle(0, 0, 0, Config.SCREEN_HEIGHT));
		boundaryList.add(new Rectangle(0, 0, Config.SCREEN_WIDTH, 0));
		boundaryList.add(new Rectangle(Config.SCREEN_WIDTH, 0, 0, Config.SCREEN_HEIGHT));
		boundaryList.add(new Rectangle(0, Config.SCREEN_HEIGHT, Config.SCREEN_WIDTH, 0));
	}
	
	/**
	 * Der Konstruktor der Klasse {@link GameModel}. 
	 */
	public GameModel(GameModelDataToSaveDataObject gameModelDataToSaveDataObject, ImageViewButton moveLeftButton, ImageViewButton moveRightButton, ImageViewButton shootButton) {
		// Datenfelder initialisieren
		background = gameModelDataToSaveDataObject.getBackground();
		player = gameModelDataToSaveDataObject.getPlayer();
		alienList = gameModelDataToSaveDataObject.getAlienList();
		barricadenList = gameModelDataToSaveDataObject.getBarricadenList();
		shootList = gameModelDataToSaveDataObject.getShootList();
		explosionList = gameModelDataToSaveDataObject.getExplosionList();
		boundaryList = gameModelDataToSaveDataObject.getBoundaryList();
		
		this.moveLeftButton = moveLeftButton;
		this.moveRightButton = moveRightButton;
		this.shootButton = shootButton;
	}
	
//////////////////////////////////////////////Getter und Setter//////////////////////////////////////////////
	
	
	
	
	
	
///////////////////////////////////////////////geerbte Methoden//////////////////////////////////////////////
	
	@Override
	public ArrayList<TexturedOpenGlObject> texturedEntitiesToDraw() {
		ArrayList<TexturedOpenGlObject> texturedEntitiesToDraw = new ArrayList<TexturedOpenGlObject>();
		
		texturedEntitiesToDraw.add(background);
		texturedEntitiesToDraw.addAll(barricadenList);
		texturedEntitiesToDraw.addAll(alienList);
		texturedEntitiesToDraw.addAll(shootList);
		texturedEntitiesToDraw.add(player);
		texturedEntitiesToDraw.addAll(explosionList);

		return texturedEntitiesToDraw;
	}
	
	@Override
	public Player getPlayer() {
		return player;
	}
	
	@Override
	public Iterator<Explosion> getIteratorExplosionList() {
		return explosionList.iterator();
	}
	
	@Override
	public Iterator<Alien> getIteratorAlienList() {
		return alienList.iterator();
	}
	
	@Override
	public Iterator<Barricade> getIteratorBarricadenList() {
		return barricadenList.iterator();
	}
	
	@Override
	public Iterator<Shoot> getIteratorShootList() {
		return shootList.iterator();
	}
	
	@Override
	public ArrayList<DestroyableSkillInterface> getDestroyableEntities () {
		ArrayList<DestroyableSkillInterface> destroyableEntities = new ArrayList<DestroyableSkillInterface>();
		
		destroyableEntities.addAll(barricadenList);
		destroyableEntities.addAll(alienList);
		destroyableEntities.addAll(shootList);
		destroyableEntities.add(player);
		
		return destroyableEntities;
	}
	
	@Override
	public ArrayList<Rectangle> getBoundaryList() {
		return boundaryList;
	}
	
	@Override
	public Rectangle getPolygonOfPlayer() {
		return player.getPolygon();
	}
	
	@Override
	public void newShoot(Shoot shoot) {
		shootList.add(shoot);
	}
	
	@Override
	public void newExplosion(float x, float y) {
		explosionList.add(new Explosion(x, y));
	}
	
	@Override
	public ArrayList<ImageViewButton> getButtonList() {
		ArrayList<ImageViewButton> buttonList = new ArrayList<ImageViewButton>();
		buttonList.add(moveLeftButton);
		buttonList.add(moveRightButton);
		buttonList.add(shootButton);
		return buttonList;
	}
	
	@Override
	public boolean isMoveLeftButtonTouched() {
		return moveLeftButton.isTouched();
	}
	
	@Override
	public boolean isMoveRightButtonTouched() {
		return moveRightButton.isTouched();
	}
	
	@Override
	public boolean isShootButtonTouched() {
		return shootButton.isTouched();
	}
	
	@Override
	public void addPlayerPoints(int points) {
		player.addPoints(points);
	}

//////////////////////////////////////////////////Methoden///////////////////////////////////////////////////
	
	public boolean isGameOver() {
		return player.getDestroyableSkill().getLives() <= 0;
	}
	
	public boolean isWaveOver() {
		return alienList.size() == 0;
	}
	
	public void nextWave(ArrayList<Alien> alienList) {
		this.alienList = alienList;
		currentWave++;
	}
	
	public int getCurrentWave() {
		return currentWave;
	}
	
///////////////////////////////////////////////Innere Klassen////////////////////////////////////////////////	
	
	
	
	
}
