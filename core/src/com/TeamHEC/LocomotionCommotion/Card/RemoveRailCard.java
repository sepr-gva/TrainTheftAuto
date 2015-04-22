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
		if (!Game_Map_Manager.implementRemoveConnection()){
			Card card = new RemoveRailCard(this.getOwner());
			this.getOwner().getCards().add(card);
			Game_CardHand.actorManager.addCard(card);
			Game_CardHand.actorManager.organiseHand();
		}
	}

}
