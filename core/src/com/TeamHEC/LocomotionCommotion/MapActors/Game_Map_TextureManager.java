package com.TeamHEC.LocomotionCommotion.MapActors;

import com.TeamHEC.LocomotionCommotion.Scene.CustomTexture;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
/*
 * Holds all the CustomTextures and file paths for all actors in StartMenu
 * This means if we need to change a file path you come here.
 */
public class Game_Map_TextureManager{
	private static Game_Map_TextureManager instance = null;
	
	protected Game_Map_TextureManager()
	{}
	
	public static Game_Map_TextureManager getInstance() {
		if(instance == null)
			instance = new Game_Map_TextureManager();
		return instance;
	}	
	
	//Map
	public CustomTexture map = new CustomTexture(Gdx.files.internal("gameScreen/game_map/map.png"));
	public CustomTexture mapInfo = new CustomTexture(Gdx.files.internal("gameScreen/game_map/mapinfo.png"));
	public CustomTexture station = new CustomTexture(Gdx.files.internal("gameScreen/game_map/station.png"));
	public CustomTexture stationx2 = new CustomTexture(Gdx.files.internal("gameScreen/game_map/Stop.png"));
	
	public CustomTexture stationInfo = new CustomTexture(Gdx.files.internal("gameScreen/game_map/stationInfoframe.png"));
	public CustomTexture stationSelect = new CustomTexture(Gdx.files.internal("gameScreen/game_map/stationSelectBtn.png"));
	public CustomTexture trainInfo = new CustomTexture(Gdx.files.internal("gameScreen/game_map/trainInfo.png"));
	public CustomTexture trainInfoPlanRoute = new CustomTexture(Gdx.files.internal("gameScreen/game_map/trainInfoPlanRoute.png"));
	
	public CustomTexture junction = new CustomTexture(Gdx.files.internal("gameScreen/game_map/junction.png"));
	public CustomTexture junctionx2 = new CustomTexture(Gdx.files.internal("gameScreen/game_map/junction2.png"));
	
	public CustomTexture p1Station = new CustomTexture(Gdx.files.internal("gameScreen/game_map/p1station.png"));
	public CustomTexture p1Stationx2 = new CustomTexture(Gdx.files.internal("gameScreen/game_map/p1station2.png"));
	public CustomTexture p1Train = new CustomTexture(Gdx.files.internal("gameScreen/game_map/p1train.png"));
	public CustomTexture p1Trainx2 = new CustomTexture(Gdx.files.internal("gameScreen/game_map/p1train2.png"));
	
	public CustomTexture p2Station = new CustomTexture(Gdx.files.internal("gameScreen/game_map/p2station.png"));
	public CustomTexture p2Stationx2 = new CustomTexture(Gdx.files.internal("gameScreen/game_map/p2station2.png"));
	public CustomTexture p2Train = new CustomTexture(Gdx.files.internal("gameScreen/game_map/p2train.png"));
	public CustomTexture p2Trainx2 = new CustomTexture(Gdx.files.internal("gameScreen/game_map/p2train2.png"));
	
	public CustomTexture routeBlip = new CustomTexture(Gdx.files.internal("gameScreen/game_map/routeBlip.png"));
	public CustomTexture redRouteBlip = new CustomTexture(Gdx.files.internal("gameScreen/game_map/routeBlip2.png"));
	
	public Texture mapLines = new Texture(Gdx.files.internal("gameScreen/game_map/lines.png"));
	public Texture cityNames = new Texture(Gdx.files.internal("gameScreen/game_map/cities.png"));
	
	public Texture obsAmsterdamBerlin = new Texture(Gdx.files.internal("gameScreen/game_map/noAmsterdamBerlin.png"));
	public Texture obsAmsterdamDublin = new Texture(Gdx.files.internal("gameScreen/game_map/noAmsterdamDublin.png"));
	public Texture obsAthensRome = new Texture(Gdx.files.internal("gameScreen/game_map/noAthensRome.png"));
	public Texture obsAthensVienna = new Texture(Gdx.files.internal("gameScreen/game_map/noAthensVienna.png"));
	public Texture obsBerlinJunct = new Texture(Gdx.files.internal("gameScreen/game_map/noBerlinJunct.png"));
	public Texture obsBerlinOslo = new Texture(Gdx.files.internal("gameScreen/game_map/noBerlinOslo.png"));
	public Texture obsBernJunct = new Texture(Gdx.files.internal("gameScreen/game_map/noBernJunct.png"));
	public Texture obsBernMonaco = new Texture(Gdx.files.internal("gameScreen/game_map/noBernMonaco.png"));
	public Texture obsBernPrague = new Texture(Gdx.files.internal("gameScreen/game_map/noBernPrague.png"));
	public Texture obsBernRome = new Texture(Gdx.files.internal("gameScreen/game_map/noBernRome.png"));
	public Texture obsDublinLondon = new Texture(Gdx.files.internal("gameScreen/game_map/noDublinLondon.png"));
	public Texture obsDublinReykjavic = new Texture(Gdx.files.internal("gameScreen/game_map/noDublinReykjavic.png"));
	public Texture obsHelsinkiMoscow = new Texture(Gdx.files.internal("gameScreen/game_map/noHelsinkiMoscow.png"));
	public Texture obsHelsinkiStockholm = new Texture(Gdx.files.internal("gameScreen/game_map/noHelsinkiStockholm.png"));
	public Texture obsHelsinkiVilnius = new Texture(Gdx.files.internal("gameScreen/game_map/noHelsinkiVilnius.png"));
	public Texture obsLisbonMadrid = new Texture(Gdx.files.internal("gameScreen/game_map/noLisbonMadrid.png"));
	public Texture obsLisbonRome = new Texture(Gdx.files.internal("gameScreen/game_map/noLisbonRome.png"));
	public Texture obsLondonParis = new Texture(Gdx.files.internal("gameScreen/game_map/noLondonParis.png"));
	public Texture obsMadridMonaco = new Texture(Gdx.files.internal("gameScreen/game_map/noMadridMonaco.png"));
	public Texture obsMadridParis = new Texture(Gdx.files.internal("gameScreen/game_map/noMadridParis.png"));
	public Texture obsMonacoParis = new Texture(Gdx.files.internal("gameScreen/game_map/noMonacoParis.png"));
	public Texture obsMoscowJunct = new Texture(Gdx.files.internal("gameScreen/game_map/noMoscowJunct.png"));
	public Texture obsOsloReykjavic = new Texture(Gdx.files.internal("gameScreen/game_map/noOsloReykjavic.png"));
	public Texture obsOsloStockholm = new Texture(Gdx.files.internal("gameScreen/game_map/noOsloStockholm.png"));
	public Texture obsParisJunct = new Texture(Gdx.files.internal("gameScreen/game_map/noParisJunct.png"));
	public Texture obsPragueRjunct = new Texture(Gdx.files.internal("gameScreen/game_map/noPragueRJunct.png"));
	public Texture obsPragueLjunct = new Texture(Gdx.files.internal("gameScreen/game_map/noPragueLJunct.png"));
	public Texture obsPragueVienna = new Texture(Gdx.files.internal("gameScreen/game_map/noPragueVienna.png"));
	public Texture obsPragueWarsaw = new Texture(Gdx.files.internal("gameScreen/game_map/noPragueWarsaw.png"));
	public Texture obsStockholmWarsaw = new Texture(Gdx.files.internal("gameScreen/game_map/noStockholmWarsaw.png"));
	public Texture obsVilniusJunct = new Texture(Gdx.files.internal("gameScreen/game_map/noVilniusJunct.png"));
	public Texture obsWarsawJunct = new Texture(Gdx.files.internal("gameScreen/game_map/noWarsawJunct.png"));
	public Texture obsWarsawBerlin = new Texture(Gdx.files.internal("gameScreen/game_map/noBerlinWarsaw.png"));
	
}
