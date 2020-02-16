package com.mycompany.a1;

public class Drone extends Moveable {
	
	public Drone()
	{
		setColor(255,255,0);
	}
	public String toString()
	{
		String parentString = super.toString();
		return "Drone: " + parentString;
	}

}
