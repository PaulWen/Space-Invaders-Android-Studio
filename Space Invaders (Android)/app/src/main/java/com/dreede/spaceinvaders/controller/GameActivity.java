package com.dreede.spaceinvaders.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dreede.spaceinvaders.R;
import com.dreede.spaceinvaders.controller.animation.AlienAnimation;
import com.dreede.spaceinvaders.controller.animation.AnimationInterface;
import com.dreede.spaceinvaders.controller.animation.BarricadeAnimation;
import com.dreede.spaceinvaders.controller.animation.ButtonAnimation;
import com.dreede.spaceinvaders.controller.animation.ExplosionAnimation;
import com.dreede.spaceinvaders.controller.animation.PlayerAnimation;
import com.dreede.spaceinvaders.controller.animation.ShootAnimation;
import com.dreede.spaceinvaders.model.GameModel;
import com.dreede.spaceinvaders.model.WaveBuilder;
import com.dreede.spaceinvaders.model.entities.buttons.ImageViewButton;
import com.dreede.spaceinvaders.util.SoundEffectManager;
import com.dreede.spaceinvaders.util.SoundtrackManager;
import com.dreede.spaceinvaders.view.GameRenderer;

import java.util.ArrayList;

/**
 * Die Klasse {@link GameActivity} ist die Activity des eigentlichen Spiels. Sie koordiniert den gesamten Ablauf.
 *
 * @author Paul Wenzel
 */
public class GameActivity extends Activity implements AnimationInterface, OnClickListener {

/////////////////////////////////////////////////Datenfelder/////////////////////////////////////////////////

    /**
     * das Model
     */
    private GameModel model;

    /**
     * die OpenGL SurfaceView auf die gezeichnet wird
     */
    private GLSurfaceView gameSurfaceView;
    /**
     * der Renderer, welcher auf die SurfaceView zeichnet und somit die View ist
     */
    private GameRenderer gameRenderer;

    /**
     * Klasse, welche die Spieler animiert
     */
    private PlayerAnimation playerAnimation;
    /**
     * Klasse, welche die Aliens animiert
     */
    private AlienAnimation alienAnimation;
    /**
     * Klasse, welche die Barrikaden animiert
     */
    private BarricadeAnimation barricadeAnimation;
    /**
     * Klasse, welche die Schüsse animiert
     */
    private ShootAnimation shootAnimation;
    /**
     * Klasse, welche die Explosionen animiert
     */
    private ExplosionAnimation explosionAnimation;
    /**
     * Klasse, welche die Buttons animiert
     * (Die Klasse animiert automatisch die Button die Berührt werden, da sie einen Touchlistener hat, welcher alle Buuttons überwacht!)
     */
    private ButtonAnimation buttonAnimation;

    private boolean continueMusic;

    private Button pauseButton;
    private ImageViewButton moveLeftButton;
    private ImageViewButton moveRightButton;
    private ImageViewButton shootButton;
    private TextView pointsTextView;

//////////////////////////////////////////////Getter und Setter//////////////////////////////////////////////


///////////////////////////////////////////////geerbte Methoden//////////////////////////////////////////////

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Datenfelder initialisieren
        // View aufbauen
        setContentView(R.layout.game);

        final RelativeLayout gameLayout = (RelativeLayout) findViewById(R.id.gamelayout);
        gameSurfaceView = new GLSurfaceView(this);
        // SurfaceView dem Layout hinzufügen
        RelativeLayout.LayoutParams glParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        gameLayout.addView(gameSurfaceView, glParams);

        //Buttons hohlen
        moveLeftButton = new ImageViewButton.Builder(ImageViewButton.Builder.LEFT_BUTTON, (ImageView) findViewById(R.id.buttonMoveLeftGame), this).getButton();
        moveRightButton = new ImageViewButton.Builder(ImageViewButton.Builder.RIGHT_BUTTON, (ImageView) findViewById(R.id.buttonMoveRightGame), this).getButton();
        pauseButton = (Button) findViewById(R.id.buttonPauseGame);
        pauseButton.setOnClickListener(this);

        //ShootButtons erstellen und dem richtigen Layout hinzufügen
        final LinearLayout gameLayoutWeaponsLinearLayout = (LinearLayout) findViewById(R.id.gameLayoutWeaponsGame);
        LayoutInflater layoutInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        ImageView shootButtonImageView = (ImageView) layoutInflater.inflate(R.layout.game_shoot_controll_button, gameLayoutWeaponsLinearLayout, false);
        shootButton = new ImageViewButton.Builder(ImageViewButton.Builder.SHOOT_BUTTON, shootButtonImageView, this).getButton();
        gameLayoutWeaponsLinearLayout.addView(shootButton.getView());


        // Punktestandanzeige hohlen
        pointsTextView = (TextView) findViewById(R.id.textViewPointsGame);

        // Alle Vie Elemente in den Vordergrund vor das Spielfeld hohlen hohlen
        for (View view : getAllChildren(gameLayout)) {
            if (view != gameSurfaceView) {
                view.bringToFront();
            }
        }

        model = new GameModel(moveLeftButton, moveRightButton, shootButton);

        gameRenderer = new GameRenderer(this, model, this);

        playerAnimation = new PlayerAnimation(model);
        alienAnimation = new AlienAnimation(model);
        barricadeAnimation = new BarricadeAnimation(model);
        shootAnimation = new ShootAnimation(model);
        explosionAnimation = new ExplosionAnimation(model);
        buttonAnimation = new ButtonAnimation(model, gameLayout);


        ////////////GameSurfaceView konfigurieren////////////
        // angeben, dass die Applikation für OpenGL 2.0 erstellt wird
        gameSurfaceView.setEGLContextClientVersion(2);
        // EGLConfigChooser setzen
        gameSurfaceView.setEGLConfigChooser(8, 8, 8, 8, 16, 0);
        // Renderer erstellen und dieser View zuweisen
        gameSurfaceView.setRenderer(gameRenderer);
        // der Renderer soll dauerhaft diese View neuzeichnen
        gameSurfaceView.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
    }

    @Override
    protected void onPause() {
        super.onPause();
        gameSurfaceView.setVisibility(View.GONE);

        if (!continueMusic) SoundtrackManager.pauseMediaPlayer();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && gameSurfaceView.getVisibility() == View.GONE) {
            gameSurfaceView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameRenderer.onResume();

        continueMusic = false;
    }

    @Override
    public void animate(long loopDuration) {
        if (model.isGameOver()) {
            continueMusic = true;
            Intent intent = new Intent(this, GameOverMenuActivity.class);
            intent.putExtra(GameOverMenuActivity.INTENT_EXTRA_POINTS, model.getPlayer().getPoints());
            intent.putExtra(GameOverMenuActivity.INTENT_EXTRA_WAVE, model.getCurrentWave());
            startActivity(intent);
        } else if (model.isWaveOver()) {
            model.nextWave(new WaveBuilder(model.getCurrentWave() + 1).getAlienList());
        } else {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    pointsTextView.setText("" + model.getPlayer().getPoints());

                }
            });

            playerAnimation.animate(loopDuration);
            alienAnimation.animate(loopDuration);
            barricadeAnimation.animate(loopDuration);
            shootAnimation.animate(loopDuration);
            explosionAnimation.animate(loopDuration);
        }
    }

    @Override
    public void onClick(View view) {
        if (view == pauseButton) {
            SoundEffectManager.playSoundEffect("button.mp3");
            continueMusic = true;
            Intent intent = new Intent(this, PauseMenuActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU) {
            continueMusic = true;
            Intent intent = new Intent(this, PauseMenuActivity.class);
            startActivity(intent);
        } else if (keyCode == KeyEvent.KEYCODE_BACK) {
            continueMusic = true;
            Intent intent = new Intent(this, PauseMenuActivity.class);
            startActivity(intent);
        }

        return true;
    }

//////////////////////////////////////////////////Methoden///////////////////////////////////////////////////

    private ArrayList<View> getAllChildren(View view) {

        if (!(view instanceof ViewGroup)) {
            ArrayList<View> viewArrayList = new ArrayList<View>();
            viewArrayList.add(view);
            return viewArrayList;
        }

        ArrayList<View> result = new ArrayList<View>();

        ViewGroup viewGroup = (ViewGroup) view;
        for (int i = 0; i < viewGroup.getChildCount(); i++) {

            View child = viewGroup.getChildAt(i);

            ArrayList<View> viewArrayList = new ArrayList<View>();
            viewArrayList.add(view);
            viewArrayList.addAll(getAllChildren(child));

            result.addAll(viewArrayList);
        }
        return result;
    }

///////////////////////////////////////////////Innere Klassen////////////////////////////////////////////////	


}
