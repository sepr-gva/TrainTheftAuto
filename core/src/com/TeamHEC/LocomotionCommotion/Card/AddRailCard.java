package com.TeamHEC.LocomotionCommotion.Card;

import com.TeamHEC.LocomotionCommotion.MapActors.Game_Map_Manager;
import com.TeamHEC.LocomotionCommotion.MapActors.Game_Map_TextureManager;
import com.TeamHEC.LocomotionCommotion.Player.Player;

public class AddRailCard extends Card {

	public AddRailCard(Player player) {
		super(player, Game_Map_TextureManager.getInstance().addRail, "Add Rail");
	}
	
	@Override
	public void implementCard()
	{
		Game_Map_Manager.implementAddConnection();
	}

}
