package com.TeamHEC.LocomotionCommotion.Player;

import com.TeamHEC.LocomotionCommotion.LocomotionCommotion;
import com.TeamHEC.LocomotionCommotion.Card.CardFactory;
import com.TeamHEC.LocomotionCommotion.Game.GameScreen;
import com.TeamHEC.LocomotionCommotion.MapActors.Game_Map_Manager;
import com.TeamHEC.LocomotionCommotion.UI_Elements.Game_Shop;
import com.TeamHEC.LocomotionCommotion.UI_Elements.WarningMessage;

/**
 * 
 * @author Callum Hewitt <ch1194@york.ac.uk>
 * The shop object used by a player to buy and sell fuel and cards.
 *
 */

public class Shop {

	private Player customer;
	private CardFactory cardFactory;
	
	//Sell price is 70% of the buy price
	public final static int coalPrice = 1;
	public final static float coalSellPrice = 0.7f;
	public final static int oilPrice = 2;
	public final static float oilSellPrice = 1.4f;
	public final static int electricPrice = 3;
	public final static float electricSellPrice = 2.1f;
	public final static int nuclearPrice = 4;
	public final static float nuclearSellPrice = 2.8f;
	public final static int cardPrice = 1000;
	public final static float cardSellPrice = 700f;
	public final static int trainPrice = 1500;
	public final static float trainSellPrice = 1000f;
	
	/**
	 * The initialiser for shop. Creates cardFactory and assigns customer.
	 * @param customer The player who will be buying and selling things from this shop object.
	 */
	public Shop(Player customer)
	{
		this.customer = customer;	
		cardFactory = new CardFactory(customer);
	}
	
	/**
	 * Purchases fuel from the shop using the customer's money. If the player doesn't have enough gold it will display a warning window (unless testing)
	 * @param fuelType The type of fuel as string the player will obtain: "Coal", "Oil", "Electric", "Nuclear"
	 * @param quantity The amount of fuel the player will purchase
	 * @param testCase Determines if the run is a testCase or not.
	 */
	public void buyFuel(String fuelType, int quantity, boolean testCase)
	{		
		if(fuelType == "Coal" && customer.getGold() >= (quantity*coalPrice)) {
			customer.addFuel(fuelType, quantity);
			customer.subGold(quantity * coalPrice);
		}
		else if(fuelType == "Oil" && customer.getGold() >= (quantity*oilPrice)) {
			customer.addFuel(fuelType, quantity);
			customer.subGold(quantity * oilPrice);
		}
		else if(fuelType == "Electric" && customer.getGold() >= (quantity*electricPrice)) {
			customer.addFuel(fuelType, quantity);
			customer.subGold(quantity * electricPrice);
		}
		else if(fuelType == "Nuclear" && customer.getGold() >= (quantity*nuclearPrice)) {
			customer.addFuel(fuelType, quantity);
			customer.subGold(quantity * nuclearPrice);
		}	
		else
		{
			if(!testCase)
				WarningMessage.fireWarningWindow("SORRY", "You don't have enough gold!");
		}
	}
		
	public void sellFuel(String fuelType, int quantity, boolean testCase)
	{
		
		if(fuelType == "Coal" && customer.getFuel(fuelType) >= quantity) {
			customer.subFuel(fuelType, quantity);
			customer.addGold((int)(Math.ceil(quantity * coalSellPrice)));
		}
		
		
		else if(fuelType == "Oil" && customer.getFuel(fuelType) >= quantity) {
			customer.subFuel(fuelType, quantity);
			customer.addGold((int)(Math.ceil(quantity * oilSellPrice)));
		}
		
		
		else if(fuelType == "Electric" && customer.getFuel(fuelType) >= quantity) {
			customer.subFuel(fuelType, quantity);
			customer.addGold((int)(Math.ceil(quantity * electricSellPrice))); //DO NOT REMOVE MATH.CEIL IT ROUNDS WIERDLY
		}
		
		
		else if(fuelType == "Nuclear" && customer.getFuel(fuelType) >= quantity) {
			customer.subFuel(fuelType, quantity);
			customer.addGold((int)(Math.ceil(quantity * nuclearSellPrice)));
		}
		else
		{
			if(!testCase)
				WarningMessage.fireWarningWindow("SORRY", "You don't have enough "+fuelType+"!");
		}
	}

	/**
	 * Purchases a card for the player
	 * @param testCase A boolean deciding if this is a testCase run or not.
	 */
	public void buyCard(boolean testCase)
	{
		if ((customer.getCards().size() < 7) && (customer.getGold() >= 1000))
		{			
			// Sets the owner to the card and subtract gold from player
			customer.addCard(cardFactory.createAnyCard());
			customer.subGold(1000);		
		}
		else if ((!testCase) && (customer.getCards().size() >= 7))
		{
			WarningMessage.fireWarningWindow("SORRY", "You have too many cards already!");
		}
		else if ((!testCase) && (customer.getGold() < 1000))
		{
			WarningMessage.fireWarningWindow("SORRY", "You don't have enough gold!");
		}
		Game_Map_Manager.cardToggle();
		Game_Map_Manager.cardToggle();
	}
	
	public void buyTrain()
	{
		if (customer.getTrains().size() < 5 && customer.getGold() >= 1500)
		{
			//Adds a new train to the "customer's" list of trains.Need to consider choosing a city.
			Game_Shop.actorManager.buy=false;
			Game_Shop.actorManager.sell=false;
			if (Game_Shop.actorManager.open== false)
			{
				Game_Shop.actorManager.open= true;
				for(int i=Game_Shop.actorManager.getStageStart(); i<=Game_Shop.actorManager.getStageEnd();i++){
					if (i > GameScreen.getStage().getActors().size-1){}
					else {
						GameScreen.getStage().getActors().get(i).setVisible(true);
					}
				}			
			}
			
			else
			{	
				Game_Shop.actorManager.open= false;
				for(int i=Game_Shop.actorManager.getStageStart(); i<=Game_Shop.actorManager.getStageEnd();i++){
					if (i > GameScreen.getStage().getActors().size-1){}
					else {
						GameScreen.getStage().getActors().get(i).setVisible(false);
					}
				}
			}
			
			WarningMessage.fireWarningWindow("NEW TRAIN", "Choose a station for your new train.");
			Game_Map_Manager.buyTrain = true;
			Game_Map_Manager.trainsUntouchable();
		}
		else if (customer.getGold() < 1500)
		{
			WarningMessage.fireWarningWindow("SORRY", "You don't have enough gold!");
		}
		else if (customer.getTrains().size() >= 5)
		{
			WarningMessage.fireWarningWindow("SORRY", "You have too many trains already!");
		}
	}
	
	public void sellTrain(){
		if (customer.getTrains().size() > 1){
			//Adds a new train to the "customer's" list of trains.Need to consider choosing a city.
			Game_Shop.actorManager.buy=false;
			Game_Shop.actorManager.sell=false;
			if (Game_Shop.actorManager.open== false)
			{
				Game_Shop.actorManager.open= true;
				for(int i=Game_Shop.actorManager.getStageStart(); i<=Game_Shop.actorManager.getStageEnd();i++){
					if (i > GameScreen.getStage().getActors().size-1){}
					else {
						GameScreen.getStage().getActors().get(i).setVisible(true);
					}
				}			
			}
			
			else
			{	
				Game_Shop.actorManager.open= false;
				for(int i=Game_Shop.actorManager.getStageStart(); i<=Game_Shop.actorManager.getStageEnd();i++){
					if (i > GameScreen.getStage().getActors().size-1){}
					else {
						GameScreen.getStage().getActors().get(i).setVisible(false);
					}
				}
			}
			WarningMessage.fireWarningWindow("SELL TRAIN", "Choose a train to sell and then click confirm.");
			Game_Map_Manager.sellTrain = true;
			Game_Map_Manager.confirmRouteBtn.setVisible(true);
			Game_Map_Manager.cancelRouteBtn.setVisible(true);
		}
		else{
			WarningMessage.fireWarningWindow("SORRY", "You cannot sell your only train!");
		}
	}
}