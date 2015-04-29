package com.TeamHEC.LocomotionCommotion.UI_Elements;

import java.util.ArrayList;

import org.json.*;

import com.TeamHEC.LocomotionCommotion.LocomotionCommotion;
import com.TeamHEC.LocomotionCommotion.Game.GameScreen;
import com.TeamHEC.LocomotionCommotion.Map.Connection;
import com.TeamHEC.LocomotionCommotion.Map.MapObj;
import com.TeamHEC.LocomotionCommotion.Map.Station;
import com.TeamHEC.LocomotionCommotion.MapActors.Game_Map_Manager;
import com.TeamHEC.LocomotionCommotion.MapActors.Game_Map_Train;
import com.TeamHEC.LocomotionCommotion.Player.Player;
import com.TeamHEC.LocomotionCommotion.Train.CoalTrain;
import com.TeamHEC.LocomotionCommotion.Train.ElectricTrain;
import com.TeamHEC.LocomotionCommotion.Train.NuclearTrain;
import com.TeamHEC.LocomotionCommotion.Train.OilTrain;
import com.TeamHEC.LocomotionCommotion.Train.Route;
import com.TeamHEC.LocomotionCommotion.Train.Train;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;

/**
 * 
 * @author Michael Dipper <md859@york.ac.uk>
 *
 */
public class ReplayModeUI {
	//Class for replay mode only UI elements.
	//Includes corner frame, previous, play/pause and next buttons.
	private final static Array<Actor> actors = new Array<Actor>();
	
	public static SpriteButton previousButton, nextButton, playPauseButton;
	public static Sprite cornerFrame;
	
	public void create(Stage stage){
		actors.clear();
		
		cornerFrame = new Sprite((LocomotionCommotion.screenX-Game_TextureManager.getInstance().game_menuobject_replaycornerframe.getWidth())+3,2
				,Game_TextureManager.getInstance().game_menuobject_replaycornerframe);
		actors.add(cornerFrame);
		
		previousButton = new SpriteButton(1412, 117, Game_TextureManager.getInstance().game_menuobject_replaypreviousbutton){
			@Override
			protected void onClicked(){
				if (LocomotionCommotion.replayTurnSelected != 0){
					LocomotionCommotion.replayTurnSelected -= 1;
					updateUI();
				}
			}
		};
		actors.add(previousButton);
		
		nextButton = new SpriteButton(1576, 117, Game_TextureManager.getInstance().game_menuobject_replaynextbutton){
			@Override
			protected void onClicked(){
				if (LocomotionCommotion.replayTurnSelected < LocomotionCommotion.loadedReplay.turnArray.length()-1){			
					LocomotionCommotion.replayTurnSelected += 1;
					updateUI();
				}
			}
		};
		actors.add(nextButton);
			
		playPauseButton = new SpriteButton(1494, 117, Game_TextureManager.getInstance().game_menuobject_replayplaybutton){
			@Override
			protected void onClicked(){
				if (this.getTexture() == Game_TextureManager.getInstance().game_menuobject_replayplaybutton){
					this.setTexture(Game_TextureManager.getInstance().game_menuobject_replaypausebutton);
				}
				else {
					this.setTexture(Game_TextureManager.getInstance().game_menuobject_replayplaybutton);
				}
				
			}
		};
		//actors.add(playPauseButton);
		
		for (Actor a : actors){
			a.setTouchable(Touchable.enabled);
			stage.addActor(a);
		}
	}
	
	private void updateUI(){
		updateTrains(1);
		updateTrains(2);
		
		if (LocomotionCommotion.replayTurnSelected % 2 == 0){
			updateResources(1);
		}
		else {
			updateResources(2);
		}
		
		GameScreenUI.playerScore.setText(GameScreen.game.getPlayer1().getName() + "    " 
				+ LocomotionCommotion.getReplayTurn().getJSONObject("player1").getJSONObject("resources").getInt("points") + "     SCORE     "
				+ LocomotionCommotion.getReplayTurn().getJSONObject("player2").getJSONObject("resources").getInt("points")
				+ "     " + GameScreen.game.getPlayer2().getName()+ "     " + LocomotionCommotion.getReplayTurn().getString("playerTurn") 
				+ " played this turn "+ "     Turn " + LocomotionCommotion.replayTurnSelected + "/" + GameScreen.game.getTurnLimit());
	}
	
	private void updateResources(int playerNum){
		JSONObject playerResources = LocomotionCommotion.getReplayTurn().getJSONObject("player1").getJSONObject("resources");
		
		if (playerNum == 2){
			playerResources = LocomotionCommotion.getReplayTurn().getJSONObject("player2").getJSONObject("resources");
		}
		
		GameScreenUI.goldQuant.setText(Integer.toString(playerResources.getInt("gold")));
		GameScreenUI.coalQuant.setText(Integer.toString(playerResources.getInt("coal")));
		GameScreenUI.oilQuant.setText(Integer.toString(playerResources.getInt("oil")));
		GameScreenUI.electricityQuant.setText(Integer.toString(playerResources.getInt("electric")));
		GameScreenUI.nuclearQuant.setText(Integer.toString(playerResources.getInt("nuclear")));
	}
	
	private void updateTrains(int playerNum){
		Player playerGetter = GameScreen.game.getPlayer1();
		JSONArray playerTrains = LocomotionCommotion.getReplayTurn().getJSONObject("player1").getJSONArray("trains");
		
		if (playerNum == 2){
			playerGetter = GameScreen.game.getPlayer2();
			playerTrains = LocomotionCommotion.getReplayTurn().getJSONObject("player2").getJSONArray("trains");
		}
		
		for (Train train: playerGetter.getTrains()){
			GameScreen.getStage().getActors().removeValue(train.getActor(), false);
		}
		
		playerGetter.trains = new ArrayList<Train>(); 
		
		for (int i=0; i < playerTrains.length(); i++){			
			int speedMod = playerTrains.getJSONObject(i).getInt("speedMod");
			Boolean inStation = playerTrains.getJSONObject(i).getBoolean("inStation");
			
			Station replayStation = GameScreen.game.getGameMap().stationsList.get(0);
			for (Station station : GameScreen.game.getGameMap().stationsList){
				if (station.getName().equals(playerTrains.getJSONObject(i).getString("station"))){
					replayStation = station;
				}
			}
			
			Route replayRoute = new Route(GameScreen.game.getGameMap().stationsList.get(0));
			
			if (playerTrains.getJSONObject(i).getJSONObject("route").getJSONArray("connections").length() != 0){
				ArrayList<Connection> connections = new ArrayList<Connection>();
				for (int j=0; j < playerTrains.getJSONObject(i).getJSONObject("route").getJSONArray("connections").length(); j++){
					MapObj start = new MapObj((float) playerTrains.getJSONObject(i).getJSONObject("route").getJSONArray("connections").getJSONObject(j).getJSONObject("startMapObj").getDouble("x"), (float) playerTrains.getJSONObject(i).getJSONObject("route").getJSONArray("connections").getJSONObject(j).getJSONObject("startMapObj").getDouble("y"), null);
					MapObj end = new MapObj((float) playerTrains.getJSONObject(i).getJSONObject("route").getJSONArray("connections").getJSONObject(j).getJSONObject("endMapObj").getDouble("x"), (float) playerTrains.getJSONObject(i).getJSONObject("route").getJSONArray("connections").getJSONObject(j).getJSONObject("endMapObj").getDouble("y"), null);
					Connection newCon = new Connection(start, end);
					connections.add(newCon);
				}
				
				int routeIndex = playerTrains.getJSONObject(i).getJSONObject("route").getInt("routeIndex");
				float connectionTravelled = (float) playerTrains.getJSONObject(i).getJSONObject("route").getDouble("connectionTravelled");
				
				replayRoute = new Route(connections, routeIndex, connectionTravelled);
			}
			else {
				replayRoute = new Route(replayStation);
			}
			
			Train replayTrain;
			
			String fuelType = playerTrains.getJSONObject(i).getString("type");
			if (fuelType.equals("Coal"))
				replayTrain = new CoalTrain(speedMod, inStation, replayRoute, playerGetter);
			else if (fuelType.equals("Nuclear"))
				replayTrain = new NuclearTrain(speedMod, inStation, replayRoute, playerGetter);
			else if (fuelType.equals("Electric"))
				replayTrain = new ElectricTrain(speedMod, inStation, replayRoute, playerGetter);
			else if (fuelType.equals("Oil"))
				replayTrain = new OilTrain(speedMod, inStation, replayRoute, playerGetter);
			else
				replayTrain = new OilTrain(speedMod, inStation, replayRoute, playerGetter); 
			
			playerGetter.addTrain(replayTrain);
		}
	}
}
