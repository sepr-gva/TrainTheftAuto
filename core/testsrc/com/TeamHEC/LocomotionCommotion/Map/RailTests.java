package com.TeamHEC.LocomotionCommotion.Map;

import static org.junit.Assert.*;

import com.TeamHEC.LocomotionCommotion.MapActors.Game_Map_Manager;
import com.TeamHEC.LocomotionCommotion.Mocking.GdxTestRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(GdxTestRunner.class)
public class RailTests {
	
	/**
	 * Break all the connections
	 */
	@Before
	public void setUp(){
		removeAllConnections();
	};
	
	@Test
	public void testBroken(){
		for (Station station : WorldMap.getInstance().stationsList){
			for (Connection connection : station.connections){
				assertTrue("Connection between " + connection.getStartMapObj() + 
						" " + connection.getDestination() + " was not removed properly.",
						connection.getTraversable() == false);
			}
		}
	}
	
	@Test
	public void testFixed(){
		addAllConnections();
		for (Station station : WorldMap.getInstance().stationsList){
			for (Connection connection : station.connections){
				assertTrue("Connection between " + connection.getStartMapObj() + 
						" " + connection.getDestination() + " was not added properly.",
						connection.getTraversable());
			}
		}
	}
	
	public void addAllConnections(){
		for (Station station : WorldMap.getInstance().stationsList){
			for (Connection connection : station.connections){
				Game_Map_Manager.addConnection(connection.getStartMapObj(), connection.getDestination());
			}
		}
	}
	
	public void removeAllConnections(){
		for (Station station : WorldMap.getInstance().stationsList){
			for (Connection connection : station.connections){
				Game_Map_Manager.removeConnection(connection.getStartMapObj(), connection.getDestination(), false);
			}
		}
	}

}
