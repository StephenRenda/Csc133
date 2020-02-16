package com.mycompany.a1;

import com.codename1.charts.models.Point;

class Moveable extends GameObject {

	private int heading;
	private int speed;
	
	public Moveable() {
		speed = r.nextInt(16);
		heading = r.nextInt(360);
	}
	
	public void Move()
	{
		Point newLoc = new Point();
		Point oldLoc = getLocation();
		double deltaX = 0.0;
		double deltaY = 0.0;
		
		//If going directly north or south only affect Y
		if (heading == 0 || heading == 180)
		{
			deltaY = Math.sin( Math.toRadians(90 - heading) ) * speed;
		}
		else if (heading == 90 || heading == 270)
		{
			deltaX = Math.cos( Math.toRadians(90 - heading) ) * speed;
		}
		else
		{
			deltaX = Math.cos( Math.toRadians(90 - heading) ) * speed;
			deltaY = Math.sin( Math.toRadians(90 - heading) ) * speed;
		}
		
		newLoc.setX((float) (deltaX + oldLoc.getX()));
		newLoc.setY((float) (deltaY + oldLoc.getY()));
		
		setLocation(newLoc);
	}

	public String toString()
	{
		String parentString = super.toString();
		String thisString = " heading = " + heading + " speed = " + speed + " size = " + getSize();
		return parentString + thisString;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getHeading() {
		return heading;
	}

	public void setHeading(int heading) {
		this.heading = heading;
	}
}