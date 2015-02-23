package com.TeamHEC.LocomotionCommotion.MapActors;

import java.util.ArrayList;

import com.TeamHEC.LocomotionCommotion.Map.MapObj;
import com.TeamHEC.LocomotionCommotion.UI_Elements.Sprite;
import com.badlogic.gdx.graphics.Texture;

public class ConnectionSprite extends Sprite {
	
	private MapObj city1, city2;
	
	/**
	 * Creates a sprite that can be decisively drawn based on the start and end city 
	 * of the broken connection it represents
	 * @param x
	 * @param y
	 * @param texture
	 * @param city1 - start city of broken connection
	 * @param city2 - end city of broken connection
	 */
	public ConnectionSprite(float x, float y, Texture texture, MapObj city1, MapObj city2){
		super(x, y, texture);
		this.city1 = city1;
		this.city2 = city2;
	}
	
	public MapObj getCity1(){
		return city1;
	}
	
	public MapObj getCity2(){
		return city2;
	}
	
	public ArrayList<MapObj> getCities(){
		ArrayList<MapObj> cities = new ArrayList<MapObj>();
		cities.add(city1);
		cities.add(city2);
		return cities;
	}
	
	public void setCities(ArrayList<MapObj> cities){
		city1 = cities.get(0);
		city2 = cities.get(1);
	}

}