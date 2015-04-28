package com.TeamHEC.LocomotionCommotion.Game;

import org.json.*;

public class LoadGame {
	
	JSONObject jsonplayer1;
	JSONObject jsonplayer2;
	
	JSONObject player1resources;
	JSONObject player2resources;
	
	public String gold1;
	public String coal1;
	public String oil1;
	public String electric1;
	public String nuclear1;
	
	public String gold2;
	public String coal2;
	public String oil2;
	public String electric2;
	public String nuclear2;
	
	JSONArray cards1;
	JSONArray trains1;
	JSONArray stations1;
	JSONArray goals1;
	
	JSONArray cards2;
	JSONArray trains2;
	JSONArray stations2;
	JSONArray goals2;

	/* -------------------------- */
	
	public String playerTurn;
	public String turnCount;
	public String turnLimit;
	
	public LoadGame(String file){
		JSONObject obj = new JSONObject(file);
		
		jsonplayer1 = obj.getJSONObject("Player1");
		player1resources = jsonplayer1.getJSONObject("resources");
		
		gold1 = player1resources.getString("gold");
		coal1 = player1resources.getString("coal");
		oil1 = player1resources.getString("oil"); 
		electric1 = player1resources.getString("electric");
		nuclear1 = player1resources.getString("nuclear");
		
		cards1 = player1resources.getJSONArray("cards");
		trains1 = player1resources.getJSONArray("trains");
		stations1 = player1resources.getJSONArray("stations");
		goals1 = player1resources.getJSONArray("goals");
				
		
		/* ------------------------------------ */
		
		jsonplayer2 = obj.getJSONObject("Player2");
		player2resources = jsonplayer2.getJSONObject("resources");
		
		gold2 = player2resources.getString("gold");
		coal2 = player2resources.getString("coal");
		oil2 = player2resources.getString("oil"); 
		electric2 = player2resources.getString("electric");
		nuclear2 = player2resources.getString("nuclear");
		
		cards2 = player2resources.getJSONArray("cards");
		trains2 = player2resources.getJSONArray("trains");
		stations2 = player2resources.getJSONArray("stations");
		goals2 = player2resources.getJSONArray("goals");
		
		/* ---------------------------------- */
		
		playerTurn = obj.getString("playerTurn");
		turnCount = obj.getString("turnCount");
		turnLimit = obj.getString("turnLimit");
	}
}
