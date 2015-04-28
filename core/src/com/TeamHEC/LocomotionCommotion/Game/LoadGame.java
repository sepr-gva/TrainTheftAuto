package com.TeamHEC.LocomotionCommotion.Game;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.json.*;

public class LoadGame {
	
	JSONObject jsonplayer1;
	JSONObject jsonplayer2;
	JSONObject turn0;
	
	JSONObject player1resources;
	JSONObject player2resources;
	
	public Integer gold1;
	public Integer coal1;
	public Integer oil1;
	public Integer electric1;
	public Integer nuclear1;
	
	public Integer gold2;
	public Integer coal2;
	public Integer oil2;
	public Integer electric2;
	public Integer nuclear2;
	
	JSONArray cards1;
	JSONArray trains1;
	JSONArray stations1;
	JSONArray goals1;
	
	JSONArray cards2;
	JSONArray trains2;
	JSONArray stations2;
	JSONArray goals2;

	public String playerTurn;
	public int turnCount;
	public int turnLimit;
	
	public JSONObject[] turnArray;
	
	private void initialise(JSONObject obj){
		turn0 = obj.getJSONObject("0");
		turnArray = new JSONObject[obj.getInt("turnLimit")];
		System.out.println(turnArray);
		JSONObject tempObj = turn0;
		for (int i = 0; i < obj.getInt("turnLimit"); i++){
			try {
				tempObj = obj.getJSONObject(String.valueOf(i));
			}
			catch (Exception e){
				tempObj = null;
			}
			turnArray[i] = tempObj;
		}
		
		/*
		jsonplayer1 = obj.getJSONObject("player1");
		player1resources = jsonplayer1.getJSONObject("resources");
		
		gold1 = player1resources.getInt("gold");
		coal1 = player1resources.getInt("coal");
		oil1 = player1resources.getInt("oil"); 
		electric1 = player1resources.getInt("electric");
		nuclear1 = player1resources.getInt("nuclear");
		
		cards1 = jsonplayer1.getJSONArray("cards");
		trains1 = jsonplayer1.getJSONArray("trains");
		stations1 = jsonplayer1.getJSONArray("stations");
		goals1 = jsonplayer1.getJSONArray("goals");
				
		
		jsonplayer2 = obj.getJSONObject("player2");
		player2resources = jsonplayer2.getJSONObject("resources");
		
		gold2 = player2resources.getInt("gold");
		coal2 = player2resources.getInt("coal");
		oil2 = player2resources.getInt("oil"); 
		electric2 = player2resources.getInt("electric");
		nuclear2 = player2resources.getInt("nuclear");
		
		cards2 = jsonplayer2.getJSONArray("cards");
		trains2 = jsonplayer2.getJSONArray("trains");
		stations2 = jsonplayer2.getJSONArray("stations");
		goals2 = jsonplayer2.getJSONArray("goals");

		
		playerTurn = obj.getString("playerTurn");
		turnCount = obj.getInt("turnCount");
		turnLimit = obj.getInt("turnLimit");
		*/
	}
	
	public JSONObject createJSONObject(File file){
		JSONObject newObj;
		try{
			InputStream inputStream = new FileInputStream(file);
			JSONTokener tokener = new JSONTokener(inputStream);
			newObj = new JSONObject(tokener);
			initialise(newObj);
		}
		catch (Exception e){
			newObj = null;
		}
		return newObj;
	}
}
