package com.TeamHEC.LocomotionCommotion;

import com.TeamHEC.LocomotionCommotion.Game.CoreGame;
import com.TeamHEC.LocomotionCommotion.Game.GameScreen;
import com.TeamHEC.LocomotionCommotion.Game.LoadGame;
import com.TeamHEC.LocomotionCommotion.Scene.SceneManager;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

import java.util.ArrayList;

import org.json.JSONObject;
/**
 * 
 * @author Robert Precious <rp825@york.ac.uk>
 * 
 * This is the main class.
 * We create the the startMenu as a Scene and GameScreen as a Screen. There is no significant difference between the two.
 * 
 */
public class LocomotionCommotion extends Game {

	public GameScreen gameScreen;
	public CoreGame newGame;

	public static int screenX = 1680;
	public static int screenY = 1050;
	
	//StartMenuOptions
	public static String gameName, player1name, player2name;
	public static int turnChoice;

	private static LocomotionCommotion INSTANCE = new LocomotionCommotion();
	public static boolean gameFinished = false;
	
	public static LoadGame loadedReplay = new LoadGame();
	public static int replayTurnSelected = 0;
	public static Boolean replayPlaying = false;

	public static LocomotionCommotion getInstance()
	{
		return INSTANCE;
	}

	private LocomotionCommotion(){}
	public static final String TITLE = "Train Theft Auto", VERSION = "0.0.0.1";

	public static JSONObject getReplayTurn(){
		return loadedReplay.turnArray.getJSONObject(replayTurnSelected).getJSONObject(String.valueOf(replayTurnSelected));
	}
	
	/**
	 * Create sets the screen to show the start scene
	 */
	@Override
	public void create()
	{
		setScreen(SceneManager.getInstance().startScene);
	}

	/**
	 * This Method sets the screen to the gameScreen, We dispose the startScene, create a new gameScreen reset the screen which resets the booleans to the start 
	 * setting for all the relevant gameScreen objects.
	 * Finally we set the screen to gameScreen.
	 */
	public void setGameScreen(Boolean replayMode)
	{
		SceneManager.getInstance().startScene.dispose();
		gameScreen = new GameScreen(replayMode);
		GameScreen.create();
		gameScreen.resetScreen();
		Gdx.graphics.getGL20().glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
		setScreen(gameScreen);
	}
	/**
	 * This Method changes the screen to startScene. We dispose gameScreen.
	 */
	public void setMenuScreen()
	{
		gameScreen.dispose();
		Gdx.graphics.getGL20().glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );

		SceneManager.getInstance().startScene.addToStage();
		setScreen(SceneManager.getInstance().startScene);
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void render() {
		Gdx.graphics.getGL20().glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
		super.render();

	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}

	@Override
	public void dispose() {
		super.dispose();
	}
}