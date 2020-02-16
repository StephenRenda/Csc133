package com.mycompany.a1;

import java.util.Random;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil; 

public abstract class GameObject {

	public Random r = new Random();
    protected int size;
    private final int max = 1000;
    
    private int color;
    
	private Point location = new Point();
	
	public GameObject() {	

		size = r.nextInt(50);
		location.setX(r.nextInt(max));
		location.setY(r.nextInt(max));
	}
	
    public int getSize() { 
    	return size; 
    }
    
	public Point getLocation() { 
		return location; 
	}

	public void setLocation(Point newLocation) {
		this.location = newLocation;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int r, int g, int b) {
		color = ColorUtil.rgb(r,g,b);
	}
	public String toString()
	{
		return "loc = " + location.getX() + ", " + location.getY() +  
				" color = [" + ColorUtil.red(color) + ", " +  
							   ColorUtil.green(color) + ", " +
							   ColorUtil.blue(color) + "] ";
	}
}
