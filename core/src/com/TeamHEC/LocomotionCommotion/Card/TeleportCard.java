package com.TeamHEC.LocomotionCommotion.Card;

import com.TeamHEC.LocomotionCommotion.Map.MapObj;
import com.TeamHEC.LocomotionCommotion.Map.WorldMap;
import com.TeamHEC.LocomotionCommotion.MapActors.Game_Map_Manager;
import com.TeamHEC.LocomotionCommotion.Player.Player;
import com.TeamHEC.LocomotionCommotion.Train.Train;
import com.TeamHEC.LocomotionCommotion.UI_Elements.Game_TextureManager;
import com.TeamHEC.LocomotionCommotion.UI_Elements.WarningMessage;

/**
 * 
 * @author Matthew Taylor <mjkt500@york.ac.uk>
 * Teleports a train (currently to London should be changed and worked in with UI so it teleports to a specified location).
 */

public class TeleportCard extends Card{
	
	public Train train;
	
	/**
	 * Initialises the card
	 * @param player The owner of the card.
	 */
	public TeleportCard(Player player)
	{
		super(player, Game_TextureManager.getInstance().game_card_teleportcard, "Teleport");
	}
	
	@Override
	/**
	 * Takes the owner's first train in their trains list and moves it to London.
	 * Should be changed in Assessment 3 to teleport either random trains to random locations or a specified train to a specified location.
	 */
	public void implementCard()
	{
		WarningMessage.fireWarningWindow("Choose a Train!", "Choose a train to teleport.");
		
		Game_Map_Manager.teleportTrain = true;
		Game_Map_Manager.currentTeleportCard = this;
		Game_Map_Manager.opponentUntouchable();
		Game_Map_Manager.confirmRouteBtn.setVisible(true);
		Game_Map_Manager.cancelRouteBtn.setVisible(true);
	}
}
