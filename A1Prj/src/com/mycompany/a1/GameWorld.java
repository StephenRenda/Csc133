package com.mycompany.a1;

public class GameWorld {

	private static final int world_width = 1000;
	private static final int world_height = 1000;
	
	public void init(){
		
		GameObject Base = new Fixed();
		GameObject EnergyStation = new Fixed();
		
		GameObject Cyborg = new Movable();
		GameObject Drone = new Movable();
		
		Base.location();
		EnergyStation.location();
		
		
		//code here to create the
		//initial game objects/setup
		}
	public void grid( int x, int y){
		
	}
		// additional methods here to
		// manipulate world objects and
		// related game state data
}
