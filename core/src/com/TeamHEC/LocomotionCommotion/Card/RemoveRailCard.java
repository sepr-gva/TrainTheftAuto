package com.TeamHEC.LocomotionCommotion.Card;

import com.TeamHEC.LocomotionCommotion.MapActors.Game_Map_Manager;
import com.TeamHEC.LocomotionCommotion.MapActors.Game_Map_TextureManager;
import com.TeamHEC.LocomotionCommotion.Player.Player;

public class RemoveRailCard extends Card{
	
	public RemoveRailCard(Player player) {
		super(player, Game_Map_TextureManager.getInstance().removeRail, "Remove Rail");
	}
	
	@Override
	public void implementCard()
	{
		Game_Map_Manager.implementRemoveConnection();
	}

}
