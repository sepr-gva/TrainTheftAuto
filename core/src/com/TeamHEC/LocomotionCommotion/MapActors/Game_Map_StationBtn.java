package com.TeamHEC.LocomotionCommotion.MapActors;

import com.TeamHEC.LocomotionCommotion.LocomotionCommotion;
import com.TeamHEC.LocomotionCommotion.Game.GameScreen;
import com.TeamHEC.LocomotionCommotion.Goal.Goal;
import com.TeamHEC.LocomotionCommotion.Map.Connection;
import com.TeamHEC.LocomotionCommotion.Map.MapObj;
import com.TeamHEC.LocomotionCommotion.Map.Station;
import com.TeamHEC.LocomotionCommotion.Train.CoalTrain;
import com.TeamHEC.LocomotionCommotion.Train.ElectricTrain;
import com.TeamHEC.LocomotionCommotion.Train.NuclearTrain;
import com.TeamHEC.LocomotionCommotion.Train.OilTrain;
import com.TeamHEC.LocomotionCommotion.Train.Route;
import com.TeamHEC.LocomotionCommotion.Train.Train;
import com.TeamHEC.LocomotionCommotion.UI_Elements.GameScreenUI;
import com.TeamHEC.LocomotionCommotion.UI_Elements.Game_StartingSequence;
import com.TeamHEC.LocomotionCommotion.UI_Elements.Game_TextureManager;
import com.TeamHEC.LocomotionCommotion.UI_Elements.SpriteButton;
import com.TeamHEC.LocomotionCommotion.UI_Elements.WarningMessage;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class Game_Map_StationBtn extends SpriteButton {

	public boolean exit = false;

	// Used to hold player1s selection:
	public static Game_Map_Station selectedStation, selectedP1;
	public static Station tempP1Station;

	public Game_Map_StationBtn(float x, float y, Texture texture)
	{
		super(x, y, texture);	
	}
	
	@Override
	protected void onClicked()
	{
		started = true;
	}
		
	@Override
	public void act(float delta){
		if(started){
			if(Game_StartingSequence.inProgress)
			{
				if(Game_StartingSequence.player1)
				{
					// Sets texture (could be done via listener?)
					
					selectedStation.texture = Game_Map_TextureManager.getInstance().p1Station;
					selectedStation.setOwned(true);
					Game_Map_Manager.hideInfoBox();
					
					tempP1Station = selectedStation.getStation();
					
					selectedStation.setTouchable(Touchable.disabled);
					selectedP1 = selectedStation;
					selectedStation = null;
					
					Game_StartingSequence.selectLabel.setVisible(true);
					Game_StartingSequence.getStartedWindow.setVisible(true);
					Game_StartingSequence.selectLabel.setText(LocomotionCommotion.player2name + ", please select your start station!");
					Game_StartingSequence.player1 = false;
				}
				else	
				{
					selectedStation.texture=Game_Map_TextureManager.getInstance().p2Station;
					selectedStation.setOwned(true);
					Game_Map_Manager.hideInfoBox();
					
					selectedP1.setTouchable(Touchable.enabled);
					
					Game_StartingSequence.selectLabel.setVisible(false);
					
					GameScreen.createCoreGame(tempP1Station, selectedStation.getStation());
					Game_StartingSequence.startGame();
					GameScreenUI.refreshResources();
					Game_StartingSequence.inProgress = false;
					
					Game_StartingSequence.selectLabel.setVisible(true);
					Game_StartingSequence.getStartedWindow.setVisible(true);
					Game_StartingSequence.getStartedWindow.setX(130);
					Game_StartingSequence.getStartedWindow.setTexture(Game_TextureManager.getInstance().game_start_getstartedwindow2);
					
					Game_StartingSequence.selectLabel.setText(GameScreen.game.getPlayerTurn().getName()+", select a new goal from the Goal Screen!");
					Game_StartingSequence.selectLabel.setX(950);
				}
			}
			//Boolean that says that a break rail card is being implemented, and the first city is
			//being chosen and so when a city is clicked it becomes the first city in the break
			else if (Game_Map_Manager.firstRemoveCity){
				//First city chosen
				Game_Map_Manager.firstRemoveCity = false;
				//Cards first city is this city
				Game_Map_Manager.currentCity = selectedStation.getStation();
				Game_Map_Manager.hideInfoBox();
				//Second city is now being chosen, so when a city is clicked it becomes the second city
				WarningMessage.fireWarningWindow("CHOOSE SECOND STATION", "Choose an ajoining station, inbetween which \nthe connection will be removed.");
				Game_Map_Manager.secondRemoveCity = true;
			}
			//Checking for if the second city is being chosen
			else if (Game_Map_Manager.secondRemoveCity){
				//Second city has been chosen, go back to normal city interaction
				Game_Map_Manager.secondRemoveCity = false;
				//The actual connection and whether it has been found (if it is valid or not)
				Connection con = null;
				boolean found = false;
				//Checking whether a connection between the two chosen cities exists
				for (Connection connection : Game_Map_Manager.currentCity.connections){
					if (connection.getDestination() == selectedStation.getStation()){
						found = true;
						con = connection;
					}
				}
				
				//If a connection is found, go on to check if it is already broken or not,
				//If it is, start the implementation of the card over again
				if (found){
					//If the connection is not broken, break it and give a message to say what
					//has happened
					if (con.getTraversable()){
						Game_Map_Manager.removeConnection(Game_Map_Manager.currentCity, selectedStation.getStation());
						WarningMessage.fireWarningWindow("CONNECTION REMOVED", "Connection between " + 
								Game_Map_Manager.currentCity.getName() + " and " + selectedStation.getStation().getName() + 
								"\nhas been removed.");
						Game_Map_Manager.trainsTouchable();
						Game_Map_Manager.planBackground.setVisible(false);
						Game_Map_Manager.cancelRouteBtn.setVisible(false);
					}
					else{
						WarningMessage.fireWarningWindow("NOT THERE", "The connection you want to remove doesn't exist yet, \nchoose a new starting city.");
						Game_Map_Manager.firstRemoveCity = true;
					}
				}
				else{
					WarningMessage.fireWarningWindow("NOT VALID CONNECTION", "That is not a valid connection, choose a new starting city.");
					Game_Map_Manager.firstRemoveCity = true;
				}
				Game_Map_Manager.hideInfoBox();
			}
			//Boolean that says that a fix rail card is being implemented, and the first city is
			//being chosen and so when a city is clicked it becomes the first city in the fix
			else if (Game_Map_Manager.firstAddCity){
				//First city chosen
				Game_Map_Manager.firstAddCity = false;
				//Cards first city is this city
				Game_Map_Manager.currentCity = selectedStation.getStation();
				Game_Map_Manager.hideInfoBox();
				//Second city is now being chosen, so when a city is clicked it becomes the second city
				WarningMessage.fireWarningWindow("CHOOSE SECOND STATION", "Choose an ajoining station, inbetween which \nthe connection will be added.");
				Game_Map_Manager.secondAddCity = true;
			}
			//Checking for if the second city is being chosen
			else if (Game_Map_Manager.secondAddCity){
				//Second city has been chosen, go back to normal city interaction
				Game_Map_Manager.secondAddCity = false;
				//The actual connection and whether it has been found (if it is valid or not)
				Connection con = null;
				boolean found = false;
				//Checking whether a connection between the two chosen cities exists
				for (Connection connection : Game_Map_Manager.currentCity.connections){
					if (connection.getDestination() == selectedStation.getStation()){
						found = true;
						con = connection;
					}
				}
					
				//If a connection is found, go on to check if it is already broken or not,
				//If it is, start the implementation of the card over again
				if (found){
					//If the connection is not broken, break it and give a message to say what
					//has happened
					if (!con.getTraversable()){
						for (ConnectionSprite sprite : Game_Map_Manager.connectionSprites){
							sprite.toggleGrey();
						}
						Game_Map_Manager.addConnection(Game_Map_Manager.currentCity, selectedStation.getStation());
						WarningMessage.fireWarningWindow("CONNECTION ADDED", "Connection between " + 
								Game_Map_Manager.currentCity.getName() + " and " + selectedStation.getStation().getName() + 
								"\nhas been added.");
						Game_Map_Manager.trainsTouchable();
						Game_Map_Manager.planBackground.setVisible(false);
						Game_Map_Manager.cancelRouteBtn.setVisible(false);
					}
					else{
						WarningMessage.fireWarningWindow("ALREADY THERE", "The connection you want to add already exists, \nchoose a new starting city.");
						Game_Map_Manager.firstAddCity = true;						}
				}
				else{
					WarningMessage.fireWarningWindow("NOT VALID CONNECTION", "That is not a valid connection, choose a new starting city.");
					Game_Map_Manager.firstAddCity = true;
				}
				Game_Map_Manager.hideInfoBox();
			}
			else if (Game_Map_Manager.teleportCity){
				Train train = Game_Map_Manager.currentTeleportCard.train;
				train.route.getRoute().clear();
				train.route.setRouteIndex(0);
				train.route.setConnectionTravelled(0);
				
				train.route.setCurrentMapObj(selectedStation.getStation());
				if (train.goal != null){
					if (train.goal.getFStationObject() == selectedStation.getStation()){
						train.goal.goalComplete();
					}
				}
				Game_Map_Manager.cancelRouteBtn.setVisible(false);
				Game_Map_Manager.hideInfoBox();
				Game_Map_Manager.teleportCity = false;
				Game_Map_Manager.trainsTouchable();
			}
			else
			{
				//Placing new train on map
				if (Game_Map_Manager.buyTrain == true){
					if (selectedStation.getStation().getOwner() != null
							&& selectedStation.getStation().getOwner() != GameScreen.game.getPlayerTurn()){
						if (GameScreen.game.getPlayerTurn() == GameScreen.game.getPlayer1()){
							WarningMessage.fireWarningWindow("Station Owned by the Enemy!", "That station is owned by "
									+ GameScreen.game.getPlayer2().getName() + " \nand so you cannot place a train here, choose another station.");
						}
						else{
							WarningMessage.fireWarningWindow("Station Owned by the Enemy!", "That station is owned by "
									+ GameScreen.game.getPlayer1().getName() + " \nand so you cannot place a train here, choose another station.");
						}
					}
					else{
						if (selectedStation.getStation().getOwner() == null){
							selectedStation.getStation().setOwner(GameScreen.game.getPlayerTurn());
							GameScreen.game.getPlayerTurn().getStations().add(selectedStation.getStation());
						}
						Train testTrain;
						
						String fuelType = selectedStation.getStation().getResourceString();
						if (fuelType.equals("Coal"))
							testTrain = new CoalTrain(0, true, new Route(selectedStation.getStation()), GameScreen.game.getPlayerTurn());
						else if (fuelType.equals("Nuclear"))
							testTrain = new NuclearTrain(0, true, new Route(selectedStation.getStation()), GameScreen.game.getPlayerTurn());
						else if (fuelType.equals("Electric"))
							testTrain = new ElectricTrain(0, true, new Route(selectedStation.getStation()), GameScreen.game.getPlayerTurn());
						else if (fuelType.equals("Oil"))
							testTrain = new OilTrain(0, true, new Route(selectedStation.getStation()), GameScreen.game.getPlayerTurn());
						else
							testTrain = new OilTrain(0, true, new Route(selectedStation.getStation()), GameScreen.game.getPlayerTurn());
						
						GameScreen.game.getPlayerTurn().addTrain(testTrain);
						GameScreen.game.getPlayerTurn().subGold(1500);
						Game_Map_Manager.buyTrain = false;
						Game_Map_Manager.trainsTouchable();
					}
				}
				//Buy Stations in game
				GameScreen.game.getPlayerTurn().purchaseStation(selectedStation.getStation());
				Game_Map_Manager.hideInfoBox();
			}
			started = false;
		}
	}
}