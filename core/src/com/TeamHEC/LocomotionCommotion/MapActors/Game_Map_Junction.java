package com.TeamHEC.LocomotionCommotion.MapActors;

import com.TeamHEC.LocomotionCommotion.Game.GameScreen;
import com.TeamHEC.LocomotionCommotion.Map.Connection;
import com.TeamHEC.LocomotionCommotion.Map.Junction;
import com.TeamHEC.LocomotionCommotion.UI_Elements.WarningMessage;

public class Game_Map_Junction extends Game_Map_MapObj{
	
	public float offset = 0;
	public Junction junction;
	
	public Game_Map_Junction(Junction junction, float actorX, float actorY)
	{
		super(actorX, actorY, Game_Map_TextureManager.getInstance().junction, Game_Map_TextureManager.getInstance().junctionx2);
		this.junction = junction;
	}
	@Override
	protected void onClicked()
	{
		super.onClicked();
		//Boolean that says that a break rail card is being implemented, and the first city is
		//being chosen and so when a city is clicked it becomes the first city in the break
		if (Game_Map_Manager.firstRemoveCity){
			//First city chosen
			Game_Map_Manager.firstRemoveCity = false;
			//Cards first city is this city
			Game_Map_Manager.currentCity = this.junction;
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
				if (connection.getDestination() == this.junction){
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
					Game_Map_Manager.removeConnection(Game_Map_Manager.currentCity, this.junction);
					WarningMessage.fireWarningWindow("CONNECTION REMOVED", "Connection between " + 
							Game_Map_Manager.currentCity.getName() + " and " + this.junction.getName() + 
							"\nhas been removed.");
					Game_Map_Manager.trainsTouchable();
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
			Game_Map_Manager.currentCity = this.junction;
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
				if (connection.getDestination() == this.junction){
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
					Game_Map_Manager.addConnection(Game_Map_Manager.currentCity, this.junction);
					WarningMessage.fireWarningWindow("CONNECTION ADDED", "Connection between " + 
							Game_Map_Manager.currentCity.getName() + " and " + this.junction + 
							"\nhas been added.");
					Game_Map_Manager.trainsTouchable();
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
	}
}
