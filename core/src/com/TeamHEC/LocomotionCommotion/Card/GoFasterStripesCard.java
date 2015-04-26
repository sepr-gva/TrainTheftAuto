package com.TeamHEC.LocomotionCommotion.Card;

import com.TeamHEC.LocomotionCommotion.MapActors.Game_Map_Manager;
import com.TeamHEC.LocomotionCommotion.Player.Player;
import com.TeamHEC.LocomotionCommotion.Train.SpeedUpgrade;
import com.TeamHEC.LocomotionCommotion.Train.Train;
import com.TeamHEC.LocomotionCommotion.UI_Elements.Game_TextureManager;
import com.TeamHEC.LocomotionCommotion.UI_Elements.WarningMessage;

public class GoFasterStripesCard extends Card {

	public GoFasterStripesCard(Player player)
	{
		super(player, Game_TextureManager.getInstance().game_card_gofasterstripescard, "GoFasterStripes");
	}
	
	@Override
	public void implementCard()
	{
		boolean available = false;
		for (Train train : this.getOwner().getTrains()){
			if (!train.upgraded){
				available = true;
				break;
			}
		}
		if (available){
			WarningMessage.fireWarningWindow("Choose a Train!", "Choose the train you want to add Go Faster Stripes to.");
			Game_Map_Manager.goFasterTrain = true;
			Game_Map_Manager.opponentUntouchable();
			Game_Map_Manager.confirmRouteBtn.setVisible(true);
			Game_Map_Manager.cancelRouteBtn.setVisible(true);
		}
		else{
			WarningMessage.fireWarningWindow("No Upgradable Trains!", "All of the trains you own have already been upgraded.");
			Card card = new GoFasterStripesCard(this.getOwner());
			this.getOwner().getCards().add(card);
			Game_CardHand.actorManager.addCard(card);
			Game_CardHand.actorManager.organiseHand();
		}
	}
}