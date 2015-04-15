package com.TeamHEC.LocomotionCommotion.MapActors;

import java.util.ArrayList;

import com.TeamHEC.LocomotionCommotion.Card.TeleportCard;
import com.TeamHEC.LocomotionCommotion.Game.GameScreen;
import com.TeamHEC.LocomotionCommotion.Map.Connection;
import com.TeamHEC.LocomotionCommotion.Map.Junction;
import com.TeamHEC.LocomotionCommotion.Map.MapObj;
import com.TeamHEC.LocomotionCommotion.Map.Station;
import com.TeamHEC.LocomotionCommotion.Map.WorldMap;
import com.TeamHEC.LocomotionCommotion.Train.Train;
import com.TeamHEC.LocomotionCommotion.Train.TrainInfoUI;
import com.TeamHEC.LocomotionCommotion.UI_Elements.GameScreenUI;
import com.TeamHEC.LocomotionCommotion.UI_Elements.Game_TextureManager;
import com.TeamHEC.LocomotionCommotion.UI_Elements.Sprite;
import com.TeamHEC.LocomotionCommotion.UI_Elements.SpriteButton;
import com.TeamHEC.LocomotionCommotion.UI_Elements.WarningMessage;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.utils.Array;
/**
 * 
 * @author Robert Precious/ Matthew Taylor <rp825@york.ac.uk>
 * Map Manager is used to 'manage' the map. It creates the map actors for the map. Handles routing UI and map/station information.
 */
public class Game_Map_Manager {

	private final static Array<Actor> actors = new Array<Actor>();
	private final static Array<Actor> infoactors = new Array<Actor>();
	private final static Array<Actor> trainInfoActors = new Array<Actor>();

	public static Sprite mapInfo, mapLines, cityNames;
	
	public static Array<ConnectionSprite> connectionSprites = new Array<ConnectionSprite>();
	
	public static Sprite stationInfo;
	public static Game_Map_StationBtn stationSelect;
	
	public static boolean firstAddCity = false, secondAddCity = false, firstRemoveCity = false,
			secondRemoveCity = false, teleportCity = false, teleportTrain = false, goFasterTrain = false;
	
	public static TeleportCard currentTeleportCard = null;
	
	public static MapObj currentCity = null;
	
	public static boolean buyTrain = false, sellTrain = false;
	

    // Checks if a train is moving or not.
    public static boolean isMoving = false;
	
	public static TrainInfoUI trainInfo;

	public static boolean infoVisible= false;
	public static int  stagestart, mapActors, stationTracker, numberOfStations, junctionTracker, numberOfJunctions = 2;
	public static Label stationLabelFuel,stationLabelName, stationLabelCost;
	public LabelStyle style;

	public static Sprite planBackground, routingModeWindow;
	public static Label routeLength, routeRemaining, routeFuelCost;
	public static SpriteButton confirmRouteBtn, undoLastRouteButton, abortRouteBtn, cancelRouteBtn;
	public static Array<Game_Map_Train> trainBlips = new Array<Game_Map_Train>();

	public Game_Map_Manager(){	}

	public void create(Stage stage){
	
		actors.clear();
		infoactors.clear();
		resetMap();
		stagestart =0;
		mapActors=0;
		stationTracker=0;
		numberOfStations=0;
		
		// Create the broken connection sprites
		
		connectionSprites.add(new ConnectionSprite(100, 60, Game_Map_TextureManager.getInstance().obsAmsterdamBerlin, Game_Map_TextureManager.getInstance().obsAmsterdamBerlinG, WorldMap.getInstance().stationsList.get(4), WorldMap.getInstance().stationsList.get(18)));
		connectionSprites.add(new ConnectionSprite(100, 60, Game_Map_TextureManager.getInstance().obsAmsterdamDublin, Game_Map_TextureManager.getInstance().obsAmsterdamDublinG, WorldMap.getInstance().stationsList.get(4), WorldMap.getInstance().stationsList.get(3)));
		connectionSprites.add(new ConnectionSprite(100, 60, Game_Map_TextureManager.getInstance().obsAthensRome, Game_Map_TextureManager.getInstance().obsAthensRomeG, WorldMap.getInstance().stationsList.get(17), WorldMap.getInstance().stationsList.get(13)));
		connectionSprites.add(new ConnectionSprite(100, 60, Game_Map_TextureManager.getInstance().obsAthensVienna, Game_Map_TextureManager.getInstance().obsAthensViennaG, WorldMap.getInstance().stationsList.get(17), WorldMap.getInstance().stationsList.get(12)));
		connectionSprites.add(new ConnectionSprite(100, 60, Game_Map_TextureManager.getInstance().obsBerlinJunct, Game_Map_TextureManager.getInstance().obsBerlinJunctG, WorldMap.getInstance().stationsList.get(18), WorldMap.getInstance().junction[0]));
		connectionSprites.add(new ConnectionSprite(100, 60, Game_Map_TextureManager.getInstance().obsBerlinOslo, Game_Map_TextureManager.getInstance().obsBerlinOsloG, WorldMap.getInstance().stationsList.get(18), WorldMap.getInstance().stationsList.get(5)));
		connectionSprites.add(new ConnectionSprite(100, 60, Game_Map_TextureManager.getInstance().obsWarsawBerlin, Game_Map_TextureManager.getInstance().obsWarsawBerlinG, WorldMap.getInstance().stationsList.get(10), WorldMap.getInstance().stationsList.get(18)));
		connectionSprites.add(new ConnectionSprite(100, 60, Game_Map_TextureManager.getInstance().obsBernJunct, Game_Map_TextureManager.getInstance().obsBernJunctG, WorldMap.getInstance().stationsList.get(19), WorldMap.getInstance().junction[0]));
		connectionSprites.add(new ConnectionSprite(100, 60, Game_Map_TextureManager.getInstance().obsBernMonaco, Game_Map_TextureManager.getInstance().obsBernMonacoG, WorldMap.getInstance().stationsList.get(19), WorldMap.getInstance().stationsList.get(16)));
		connectionSprites.add(new ConnectionSprite(100, 60, Game_Map_TextureManager.getInstance().obsBernPrague, Game_Map_TextureManager.getInstance().obsBernPragueG, WorldMap.getInstance().stationsList.get(19), WorldMap.getInstance().stationsList.get(11)));
		connectionSprites.add(new ConnectionSprite(100, 60, Game_Map_TextureManager.getInstance().obsBernRome, Game_Map_TextureManager.getInstance().obsBernRomeG, WorldMap.getInstance().stationsList.get(19), WorldMap.getInstance().stationsList.get(13)));
		connectionSprites.add(new ConnectionSprite(100, 60, Game_Map_TextureManager.getInstance().obsDublinLondon, Game_Map_TextureManager.getInstance().obsDublinLondonG, WorldMap.getInstance().stationsList.get(3), WorldMap.getInstance().stationsList.get(0)));
		connectionSprites.add(new ConnectionSprite(100, 60, Game_Map_TextureManager.getInstance().obsDublinReykjavic, Game_Map_TextureManager.getInstance().obsDublinReykjavicG, WorldMap.getInstance().stationsList.get(3), WorldMap.getInstance().stationsList.get(2)));
		connectionSprites.add(new ConnectionSprite(100, 60, Game_Map_TextureManager.getInstance().obsHelsinkiMoscow, Game_Map_TextureManager.getInstance().obsHelsinkiMoscowG, WorldMap.getInstance().stationsList.get(7), WorldMap.getInstance().stationsList.get(9)));
		connectionSprites.add(new ConnectionSprite(100, 60, Game_Map_TextureManager.getInstance().obsHelsinkiStockholm, Game_Map_TextureManager.getInstance().obsHelsinkiStockholmG, WorldMap.getInstance().stationsList.get(7), WorldMap.getInstance().stationsList.get(6)));
		connectionSprites.add(new ConnectionSprite(100, 60, Game_Map_TextureManager.getInstance().obsHelsinkiVilnius, Game_Map_TextureManager.getInstance().obsHelsinkiVilniusG, WorldMap.getInstance().stationsList.get(7), WorldMap.getInstance().stationsList.get(8)));
		connectionSprites.add(new ConnectionSprite(100, 60, Game_Map_TextureManager.getInstance().obsLisbonMadrid, Game_Map_TextureManager.getInstance().obsLisbonMadridG, WorldMap.getInstance().stationsList.get(15), WorldMap.getInstance().stationsList.get(14)));
		connectionSprites.add(new ConnectionSprite(100, 60, Game_Map_TextureManager.getInstance().obsLisbonRome, Game_Map_TextureManager.getInstance().obsLisbonRomeG, WorldMap.getInstance().stationsList.get(15), WorldMap.getInstance().stationsList.get(13)));
		connectionSprites.add(new ConnectionSprite(100, 60, Game_Map_TextureManager.getInstance().obsLondonParis, Game_Map_TextureManager.getInstance().obsLondonParisG, WorldMap.getInstance().stationsList.get(0), WorldMap.getInstance().stationsList.get(1)));
		connectionSprites.add(new ConnectionSprite(100, 60, Game_Map_TextureManager.getInstance().obsMadridMonaco, Game_Map_TextureManager.getInstance().obsMadridMonacoG, WorldMap.getInstance().stationsList.get(14), WorldMap.getInstance().stationsList.get(16)));
		connectionSprites.add(new ConnectionSprite(100, 60, Game_Map_TextureManager.getInstance().obsMadridParis, Game_Map_TextureManager.getInstance().obsMadridParisG, WorldMap.getInstance().stationsList.get(14), WorldMap.getInstance().stationsList.get(1)));
		connectionSprites.add(new ConnectionSprite(100, 60, Game_Map_TextureManager.getInstance().obsMonacoParis, Game_Map_TextureManager.getInstance().obsMonacoParisG, WorldMap.getInstance().stationsList.get(16), WorldMap.getInstance().stationsList.get(1)));
		connectionSprites.add(new ConnectionSprite(100, 60, Game_Map_TextureManager.getInstance().obsMoscowJunct, Game_Map_TextureManager.getInstance().obsMoscowJunctG, WorldMap.getInstance().stationsList.get(9), WorldMap.getInstance().junction[1]));
		connectionSprites.add(new ConnectionSprite(100, 60, Game_Map_TextureManager.getInstance().obsOsloReykjavic, Game_Map_TextureManager.getInstance().obsOsloReykjavicG, WorldMap.getInstance().stationsList.get(5), WorldMap.getInstance().stationsList.get(2)));
		connectionSprites.add(new ConnectionSprite(100, 60, Game_Map_TextureManager.getInstance().obsOsloStockholm, Game_Map_TextureManager.getInstance().obsOsloStockholmG, WorldMap.getInstance().stationsList.get(5), WorldMap.getInstance().stationsList.get(6)));
		connectionSprites.add(new ConnectionSprite(100, 60, Game_Map_TextureManager.getInstance().obsParisJunct, Game_Map_TextureManager.getInstance().obsParisJunctG, WorldMap.getInstance().stationsList.get(1), WorldMap.getInstance().junction[0]));
		connectionSprites.add(new ConnectionSprite(100, 60, Game_Map_TextureManager.getInstance().obsPragueLjunct, Game_Map_TextureManager.getInstance().obsPragueLjunctG, WorldMap.getInstance().stationsList.get(11), WorldMap.getInstance().junction[0]));
		connectionSprites.add(new ConnectionSprite(100, 60, Game_Map_TextureManager.getInstance().obsPragueRjunct, Game_Map_TextureManager.getInstance().obsPragueRjunctG, WorldMap.getInstance().stationsList.get(11), WorldMap.getInstance().junction[1]));
		connectionSprites.add(new ConnectionSprite(100, 60, Game_Map_TextureManager.getInstance().obsPragueVienna, Game_Map_TextureManager.getInstance().obsPragueViennaG, WorldMap.getInstance().stationsList.get(11), WorldMap.getInstance().stationsList.get(12)));
		connectionSprites.add(new ConnectionSprite(100, 60, Game_Map_TextureManager.getInstance().obsPragueWarsaw, Game_Map_TextureManager.getInstance().obsPragueWarsawG, WorldMap.getInstance().stationsList.get(11), WorldMap.getInstance().stationsList.get(10)));
		connectionSprites.add(new ConnectionSprite(100, 60, Game_Map_TextureManager.getInstance().obsStockholmWarsaw, Game_Map_TextureManager.getInstance().obsStockholmWarsawG, WorldMap.getInstance().stationsList.get(6), WorldMap.getInstance().stationsList.get(10)));
		connectionSprites.add(new ConnectionSprite(100, 60, Game_Map_TextureManager.getInstance().obsVilniusJunct, Game_Map_TextureManager.getInstance().obsVilniusJunctG, WorldMap.getInstance().stationsList.get(8), WorldMap.getInstance().junction[1]));
		connectionSprites.add(new ConnectionSprite(100, 60, Game_Map_TextureManager.getInstance().obsWarsawJunct, Game_Map_TextureManager.getInstance().obsWarsawJunctG, WorldMap.getInstance().stationsList.get(10), WorldMap.getInstance().junction[1]));

		planBackground = new Sprite(-1,50,Game_TextureManager.getInstance().game_pause_blackoutscreen);
		planBackground.setVisible(false);
		actors.add(planBackground);
		
		routingModeWindow = new Sprite(-20,65,Game_TextureManager.getInstance().routingModeWindow);
		routingModeWindow.setVisible(false);
		actors.add(routingModeWindow);
		
		confirmRouteBtn = new SpriteButton(20, 125, Game_TextureManager.getInstance().confirmroutingModebtn){
			@Override
			protected void onClicked(){
				if (sellTrain){
					GameScreen.game.getPlayerTurn().getTrains().remove(Game_Map_Manager.trainInfo.train);
					Game_Map_Manager.trainInfo.train.getActor().setTouchable(Touchable.disabled);
					Game_Map_Manager.trainInfo.train.getActor().setVisible(false);
					Game_Map_Manager.trainInfo.makeVisible(false);
					WarningMessage.fireWarningWindow("TRAIN SOLD", Game_Map_Manager.trainInfo.train.getName() + " has been sold for 1000 gold.");
					GameScreen.game.getPlayerTurn().addGold(1000);
					sellTrain = false;
					confirmRouteBtn.setVisible(false);
					cancelRouteBtn.setVisible(false);
					System.out.println(GameScreen.game.getPlayerTurn().getTrains().size());
				}
				exitRoutingMode();
			}
		};
		confirmRouteBtn.setVisible(false);
		actors.add(confirmRouteBtn);
		
		undoLastRouteButton = new SpriteButton(130, 125, Game_TextureManager.getInstance().undoRouteBtn){
			@Override
			protected void onClicked()
			{
				if(Game_Map_Manager.trainInfo.train != null)
					Game_Map_Manager.trainInfo.train.route.removeConnection();
			}
		};
		undoLastRouteButton.setVisible(false);
		actors.add(undoLastRouteButton);
		
		abortRouteBtn = new SpriteButton(130, 80, Game_TextureManager.getInstance().abortRouteBtn){
			@Override
			protected void onClicked()
			{
				if(Game_Map_Manager.trainInfo.train != null)
					Game_Map_Manager.trainInfo.train.route.abortRoute();
			}
		};
		abortRouteBtn.setVisible(false);
		actors.add(abortRouteBtn);
		
		cancelRouteBtn = new SpriteButton(20, 80, Game_TextureManager.getInstance().cancelRouteBtn){
			@Override
			protected void onClicked()
			{
				if(Game_Map_Manager.trainInfo.train != null){
					Game_Map_Manager.trainInfo.train.route.cancelRoute();
				}
				else if (Game_Map_Manager.sellTrain){
					WarningMessage.fireWarningWindow("SALE ABORTED", "You did not sell a train.");
					sellTrain = false;
					confirmRouteBtn.setVisible(false);
					cancelRouteBtn.setVisible(false);
					
				}
			}
		};
		cancelRouteBtn.setVisible(false);
		actors.add(cancelRouteBtn);

		//mapLines = new Sprite(100, 60, Game_Map_TextureManager.getInstance().mapLines);		
		//actors.add(mapLines);
		
		for (ConnectionSprite sprite : connectionSprites){
			sprite.setVisible(false);
			actors.add(sprite);
			sprite.getGreySprite().setVisible(true);
			actors.add(sprite.getGreySprite());
		}
		
		cityNames = new Sprite(100, 60, Game_Map_TextureManager.getInstance().cityNames);
		actors.add(cityNames);
	
		stationTracker=stage.getActors().size;
		for(int i = 0; i < WorldMap.getInstance().stationsList.size(); i++)
		{
			actors.add(WorldMap.getInstance().stationsList.get(i).getActor());
			numberOfStations++;
		}

		junctionTracker =stage.getActors().size;
		for(int i = 0; i < WorldMap.getInstance().junction.length; i++)
		{
			actors.add(WorldMap.getInstance().junction[i].getActor());
		}
		
		// Creates UI Train blips for 6 trains:
		for(int i = 0; i < 6; i++)
		{
			trainBlips.add(new Game_Map_Train());
		}
		actors.addAll(trainBlips);
		
		// Add train stuff

		stationInfo = new Sprite(0, 0, Game_Map_TextureManager.getInstance().stationInfo);
		infoactors.add(stationInfo);
		
		trainInfo = new TrainInfoUI();		
		trainInfoActors.add(trainInfo);
		trainInfoActors.addAll(trainInfo.getActors());

		stationSelect = new Game_Map_StationBtn(0, 0, Game_Map_TextureManager.getInstance().stationSelect);
		infoactors.add(stationSelect);

		//Stuff for Labels
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/gillsans.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = 23;

		BitmapFont font = generator.generateFont(parameter); 
		font.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		generator.dispose();
		style = new LabelStyle();
		style.font = font;
		//end

		stationLabelName = new Label(null, style);
		stationLabelFuel = new Label(null, style);
		stationLabelCost = new Label(null, style);
			
		stationLabelName.setText("LONDON");
		stationLabelName.setAlignment(Align.center);		
		stationLabelName.setColor(1,1,1,1);
		stationLabelName.setX(stationInfo.getX()+100);
		stationLabelName.setY(stationInfo.getY()+142);

		stationLabelFuel.setText("Type x 100");
		stationLabelFuel.setAlignment(Align.center);		
		stationLabelFuel.setColor(0,0,0,1);
		stationLabelFuel.setX(stationInfo.getX()+100);
		stationLabelFuel.setY(stationInfo.getY()+100);

		stationLabelCost.setText("");
		stationLabelCost.setAlignment(Align.center);		
		stationLabelCost.setColor(0,0,0,1);
		stationLabelCost.setX(stationInfo.getX()+100);
		stationLabelCost.setY(stationInfo.getY()+60);
		
		// Route Labels
		routeLength = new Label(null, style);
		routeRemaining = new Label(null, style);
		routeFuelCost =  new Label(null, style);
		
		routeLength.setText("Route length: 0");
		routeRemaining.setText("Route remaining: 0");
		routeFuelCost.setText("Fuel cost (): 0");
		
		routeLength.setPosition(10, 245, Align.center);
		routeRemaining.setPosition(10, 215, Align.center);
		routeFuelCost.setPosition(10, 185, Align.center);
		
		routeLength.setVisible(false);
		routeRemaining.setVisible(false);
		routeFuelCost.setVisible(false);
		routeLength.setColor(Color.BLACK);
		routeRemaining.setColor(Color.BLACK);
		routeFuelCost.setColor(Color.BLACK);
		actors.add(routeLength);
		actors.add(routeRemaining);
		actors.add(routeFuelCost);

		infoactors.add(stationLabelName);
		infoactors.add(stationLabelFuel);
		infoactors.add(stationLabelCost);
		
		for(Actor a : actors)
		{
			a.setTouchable(Touchable.enabled);
			stage.addActor(a);
		}
		
		stagestart = stage.getActors().size;
		
		for (Actor a : infoactors){
			a.setTouchable(Touchable.enabled);
			a.setVisible(false);
			stage.addActor(a);
			mapActors ++;
		}

		for(Actor a : trainInfoActors)
		{
			a.setTouchable(Touchable.enabled);
			a.setVisible(false);
			stage.addActor(a);
		}
		
		mapInfo = new Sprite(500, 100, Game_TextureManager.getInstance().mapInfo);

		mapInfo.setVisible(infoVisible);
		stage.addActor(mapInfo);
	}
	
	public static void enterRoutingMode()
	{		
		trainInfo.train.getRoute().showRouteBlips();
				
		trainsUntouchable();
		
		GameScreenUI.game_menuobject_endturnbutton.setVisible(false);
		
		planBackground.setVisible(true);
		routingModeWindow.setVisible(true);
		confirmRouteBtn.setVisible(true);
		undoLastRouteButton.setVisible(true);
		abortRouteBtn.setVisible(true);
		cancelRouteBtn.setVisible(true);
		
		routeLength.setVisible(true);
		routeRemaining.setVisible(true);
		routeFuelCost.setVisible(true);
		undoLastRouteButton.setVisible(true);
	}
	
	public static void exitRoutingMode()
	{
		trainInfo.unhighlightAdjacent();
		trainInfo.train.getRoute().hideRouteBlips();
		
		trainsTouchable();
		
		GameScreenUI.game_menuobject_endturnbutton.setVisible(true);
		
		planBackground.setVisible(false);
		routingModeWindow.setVisible(false);
		confirmRouteBtn.setVisible(false);
		undoLastRouteButton.setVisible(false);
		abortRouteBtn.setVisible(false);
		cancelRouteBtn.setVisible(false);
		
		routeLength.setVisible(false);
		routeRemaining.setVisible(false);
		routeFuelCost.setVisible(false);
		undoLastRouteButton.setVisible(false);
	}

	public static void moveInfoBox(float x,float y){
		showInfoBox();
		stationInfo.setX(x);
		stationInfo.setY(y);
		stationInfo.refreshBounds();
		Game_Map_Manager.stationSelect.setX(x+20);
		Game_Map_Manager.stationSelect.setY(y+10);
		Game_Map_Manager.stationSelect.refreshBounds();

		stationLabelName.setX(x+100);
		stationLabelName.setY(y+142);

		stationLabelFuel.setX(x+100);
		stationLabelFuel.setY(y+100);

		stationLabelCost.setX(x+100);
		stationLabelCost.setY(y+60);
	}

	public static void hideInfoBox(){
		stationInfo.setVisible(false);
		Game_Map_Manager.stationSelect.setVisible(false);

		stationLabelName.setVisible(false);
		stationLabelFuel.setVisible(false);
		stationLabelCost.setVisible(false);
	}

	public static void showInfoBox(){
		stationInfo.setVisible(true);
		Game_Map_Manager.stationSelect.setVisible(true);

		stationLabelName.setVisible(true);
		stationLabelFuel.setVisible(true);
		stationLabelCost.setVisible(true);
	}

	public static void resetMap(){
		for(int i=Game_Map_Manager.stationTracker; i<=Game_Map_Manager.stationTracker +Game_Map_Manager.numberOfStations-1;i++)	//All the stations on the stage
		{ 	
			if (i > GameScreen.getStage().getActors().size-1)
			{//This is just to avoid range errors
			}
			else{
				if (GameScreen.getStage().getActors().get(i).getClass() == Game_Map_Station.class)
				{
					((Game_Map_Station) GameScreen.getStage().getActors().get(i)).setOwned(false);
				}
			}
		}
	}
	
	public static void removeConnection(MapObj start, MapObj end){
		//boolean for checking whether a valid connection is ever found between the map objects
		boolean validConnection = false;
		Connection connection1 = null, connection2 = null;
		//iterate through all the stations in the world to find the one we are given
		for (Station station : WorldMap.getInstance().stationsList){
			if (start == station){
				//iterate over all the stations connections to find the one correlating to the other map object
				for (Connection connection : station.connections){
					if (connection.getDestination() == end){
						//if it is already broken, dont break it, print a warning
						if (!connection.getTraversable()){
							System.out.println("Connection between " + start.getName() +
							" and " + end.getName() + " is already broken.");
						}
						else{
							//set first connection to this connection if it isnt broken
							connection1 = connection;
						}
					}
				}
			}
			else if (end == station){
				for (Connection connection : station.connections){
					if (connection.getDestination() == start){
						//if the opposite connection is found set this to the second connection to be broken
						connection2 = connection;
					}
				}
			}
		}
		//do the exact same with junctions
		for (Junction junction : WorldMap.getInstance().junction){
			if (start == junction){
				for (Connection connection : junction.connections){
					if (connection.getDestination() == end){
						if (!connection.getTraversable()){
							System.out.println("Connection between " + start.getName() +
							" and " + end.getName() + " is already broken.");
						}
						connection1 = connection;
					}
				}
			}
			else if (end == junction){
				for (Connection connection : junction.connections){
					if (connection.getDestination() == start){
						connection2 = connection;
					}
				}
			}
		}
		
		//boolean to show whether there is a train on the rail
		boolean trainOnRail = false;
		//iterate over all player 1's trains if they have any
		if (GameScreen.game.getPlayer1().getTrains().size() > 0){
			for (Train train : GameScreen.game.getPlayer1().getTrains()){
				//if the train is on a route, find the connection it is currently on, and if it connection1
				//or connection2 then set trainonroute to true and break from the loop
				if (train.route.getRoute().size() > 0){
					if (train.route.getRoute().get(train.route.getRouteIndex()) == connection1 ||
							train.route.getRoute().get(train.route.getRouteIndex()) == connection2){
						trainOnRail = true;
						break;
					}
				}
			}
		}
		//if player 1 has no trains on the connections do the same check for player 2s trains
		if (!trainOnRail){
			if (GameScreen.game.getPlayer1().getTrains().size() > 0){
				for (Train train : GameScreen.game.getPlayer2().getTrains()){
					if (train.route.getRoute().size() > 0){
						if (train.route.getRoute().get(train.route.getRouteIndex()) == connection1 ||
								train.route.getRoute().get(train.route.getRouteIndex()) == connection2){
							trainOnRail = true;
							break;
						}
					}
				}
			}
		}
		//if there is a train on the rail then dont break it
		if (trainOnRail){
			WarningMessage.fireWarningWindow("DANGER", "Breaking a rail with a train on it is against the \nGeneva Convention.");
		}
		else{
			//otherwise set both connections to untraversable and find the correct connectionSprite to draw
			connection1.setTraversable(false);
			connection2.setTraversable(false);
			validConnection = true;
			for (ConnectionSprite sprite : connectionSprites){
				if ((connection1.getStartMapObj() == sprite.getCity1() &&
						connection1.getDestination() == sprite.getCity2()) ||
						connection1.getDestination() == sprite.getCity1() &&
						connection1.getStartMapObj() == sprite.getCity2()){
					sprite.getGreySprite().setVisible(false);
				}
			}
		}
		
		//if no valid connection is found print a warning to the console
		if (!validConnection){
			System.out.println("There is no connection between " + start.getName() + 
					" and " + end.getName() + ".");
		}
	}
	
	/**
	 * Purely for testing purposes, removes the check for whether a train is on the
	 * piece of rail as when the test is run, the game is not fully set up and causes 
	 * a crash
	 * @param start - start Map Object for the connection to be broken
	 * @param end - end Map Object for the connection to be broken
	 * @param testForOnRail - if any boolean is put in here it tells us that the test for
	 * on rail will not be done
	 */
	public static void removeConnection(MapObj start, MapObj end, boolean dontTestForOnRail){
		boolean validConnection = false;
		Connection connection1 = null, connection2 = null;
		for (Station station : WorldMap.getInstance().stationsList){
			if (start == station){
				for (Connection connection : station.connections){
					if (connection.getDestination() == end){
						if (!connection.getTraversable()){
							System.out.println("Connection between " + start.getName() +
							" and " + end.getName() + " is already broken.");
						}
						else{
							connection1 = connection;
						}
					}
				}
			}
			else if (end == station){
				for (Connection connection : station.connections){
					if (connection.getDestination() == start){
						connection2 = connection;
					}
				}
			}
		}
		for (Junction junction : WorldMap.getInstance().junction){
			if (start == junction){
				for (Connection connection : junction.connections){
					if (connection.getDestination() == end){
						if (!connection.getTraversable()){
							System.out.println("Connection between " + start.getName() +
							" and " + end.getName() + " is already broken.");
						}
						connection1 = connection;
					}
				}
			}
			else if (end == junction){
				for (Connection connection : junction.connections){
					if (connection.getDestination() == start){
						connection2 = connection;
					}
				}
			}
		}
		
		if (connection1 != null && connection2 != null){
			connection1.setTraversable(false);
			connection2.setTraversable(false);
			validConnection = true;
			for (ConnectionSprite sprite : connectionSprites){
				if ((connection1.getStartMapObj() == sprite.getCity1() &&
					connection1.getDestination() == sprite.getCity2()) ||
					connection1.getDestination() == sprite.getCity1() &&
					connection1.getStartMapObj() == sprite.getCity2()){
					sprite.getGreySprite().setVisible(false);
				}
			}
		}
		
		if (!validConnection){
			System.out.println("There is no connection between " + start.getName() + 
					" and " + end.getName() + ".");
		}
	}
	
	//done the exact same way as break rail, however as soon as a valid connection is found it is set to traversable
	//because we dont have to worry about whether there is a train on the rail, connectionSprite also found
	public static void addConnection(MapObj start, MapObj end){
		boolean validConnection = false;
		for (Station station : WorldMap.getInstance().stationsList){
			if (start == station){
				for (Connection connection : station.connections){
					if (connection.getDestination() == end){
						if (connection.getTraversable()){
							System.out.println("Connection between " + start.getName() +
							" and " + end.getName() + " is not broken.");
						}
						connection.setTraversable(true);
						validConnection = true;
						for (ConnectionSprite sprite : connectionSprites){
							if ((connection.getStartMapObj() == sprite.getCity1() &&
									connection.getDestination() == sprite.getCity2()) ||
									(connection.getDestination() == sprite.getCity1() &&
									connection.getStartMapObj() == sprite.getCity2())){
								sprite.getGreySprite().setVisible(true);
							}
						}
					}
				}
			}
			else if (end == station){
				for (Connection connection : station.connections){
					if (connection.getDestination() == start){
						connection.setTraversable(true);
					}
				}
			}
		}
		for (Junction junction : WorldMap.getInstance().junction){
			if (start == junction){
				for (Connection connection : junction.connections){
					if (connection.getDestination() == end){
						if (connection.getTraversable()){
							System.out.println("Connection between " + start.getName() +
							" and " + end.getName() + " is not broken.");
						}
						connection.setTraversable(true);
						validConnection = true;
						for (ConnectionSprite sprite : connectionSprites){
							if ((connection.getStartMapObj() == sprite.getCity1() &&
									connection.getDestination() == sprite.getCity2()) ||
									(connection.getDestination() == sprite.getCity1() &&
									connection.getStartMapObj() == sprite.getCity2())){
								sprite.getGreySprite().setVisible(true);
							}
						}
					}
				}
			}
			else if (end == junction){
				for (Connection connection : junction.connections){
					if (connection.getDestination() == start){
						connection.setTraversable(true);
					}
				}
			}
		}
		if (!validConnection){
			System.out.println("There is no connection between " + start.getName() + 
					" and " + end.getName() + ".");
		}
	}
	
	public static void trainsUntouchable(){
		// Allows you to click on stations that are covered by trains:
				for(Train t : GameScreen.game.getPlayer1().getTrains())
				{
					t.getActor().setTouchable(Touchable.disabled);
				}
				for(Train t : GameScreen.game.getPlayer2().getTrains())
				{
					t.getActor().setTouchable(Touchable.disabled);
				}
	}
	
	public static void trainsTouchable(){
		//Makes trains clickable again
				for(Train t : GameScreen.game.getPlayer1().getTrains())
				{
					t.getActor().setTouchable(Touchable.enabled);
				}
				for(Train t : GameScreen.game.getPlayer2().getTrains())
				{
					t.getActor().setTouchable(Touchable.enabled);
				}
	}
	
	public static void oponentUntouchable(){
		if (GameScreen.game.getPlayerTurn() == GameScreen.game.getPlayer1()){
			for(Train t : GameScreen.game.getPlayer2().getTrains())
			{
				t.getActor().setTouchable(Touchable.disabled);
			}
		}
		else{
			for(Train t : GameScreen.game.getPlayer1().getTrains())
			{
				t.getActor().setTouchable(Touchable.disabled);
			}
		}
	}
	
	public static void oponentTouchable(){
		if (GameScreen.game.getPlayerTurn() == GameScreen.game.getPlayer1()){
			for(Train t : GameScreen.game.getPlayer2().getTrains())
			{
				t.getActor().setTouchable(Touchable.enabled);
			}
		}
		else{
			for(Train t : GameScreen.game.getPlayer1().getTrains())
			{
				t.getActor().setTouchable(Touchable.enabled);
			}
		}
	}
	
	public static void implementAddConnection(){
		boolean addAvailable = false;
		for (ConnectionSprite sprite : connectionSprites){
			if (!sprite.getGreySprite().isVisible()){
				addAvailable = true;
				break;
			}
		}
		if (addAvailable){
			firstAddCity = true;
			trainsUntouchable();
			planBackground.setVisible(true);
			for (ConnectionSprite sprite : connectionSprites){
				sprite.toggleGrey();
			}
			WarningMessage.fireWarningWindow("CHOOSE FIRST STATION", "Choose the start city of the connection you want to add.");
		}
		else{
			WarningMessage.fireWarningWindow("MAP IS FULL", "There are no more connections available to add.");
		}
	}
	
	public static void implementRemoveConnection(){
		boolean removeAvailable = false;
		for (ConnectionSprite sprite : connectionSprites){
			if (!sprite.isVisible()){
				removeAvailable = true;
				break;
			}
		}
		if (removeAvailable){
			firstRemoveCity = true;
			trainsUntouchable();
			planBackground.setVisible(true);
			WarningMessage.fireWarningWindow("CHOOSE FIRST STATION", "Choose the start city of the connection you want to remove.");
		}
		else{
			WarningMessage.fireWarningWindow("MAP IS EMPTY", "There are no connections in the map to remove.");
		}
	}
}