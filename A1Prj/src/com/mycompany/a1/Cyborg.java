package com.mycompany.a1;

public class Cyborg extends Moveable implements ISteerable {
	
	private double steeringDirection;
	
	private int maximumSpeed;
	
	private int energyLevel;
	
	private int energyConsumptionRate;
	
	private int damageLevel;
	
	private int lastBaseReached;
	
	public Cyborg()
	{
		size =40;
		damageLevel = 0;	
		energyLevel = 15;
		lastBaseReached = 1;
		maximumSpeed = 100;
		energyConsumptionRate = 5;
		
		setSpeed(0);
		setColor(139,0,0);
	}

	public double getSteeringDirection() {
		return steeringDirection;
	}

	public void setSteeringDirection(double steeringDirection) {
		this.steeringDirection = steeringDirection;
	}

	public int getMaximumSpeed() {
		return maximumSpeed;
	}

	public void setMaximumSpeed(int maximumSpeed) {
		this.maximumSpeed = maximumSpeed;
	}

	public int getEnergyLevel() {
		return energyLevel;
	}

	public void setEnergyLevel(int energyLevel) {
		this.energyLevel = energyLevel;
	}

	public int getEnergyConsumptionRate() {
		return energyConsumptionRate;
	}

	public void setEnergyConsumptionRate(int energyConsumptionRate) {
		this.energyConsumptionRate = energyConsumptionRate;
	}

	public int getDamageLevel() {
		return damageLevel;
	}

	public void setDamageLevel(int damageLevel) {
		this.damageLevel = damageLevel;
	}

	public  int getLastBaseReached() {
		return lastBaseReached;
	}

	public void setLastBaseReached(int lastBaseReached) {
		this.lastBaseReached = lastBaseReached;
	}
	public void AdjustSpeed(boolean speedUp)
	{
		if (speedUp && getSpeed() <= getMaximumSpeed())
		{
			//Increase speed
			setSpeed(getSpeed() + 1);
		}
		else if (!speedUp && getSpeed() > 0)
		{
			//Decrease speed
			setSpeed(getSpeed() - 1);
		}
	}
	
	@Override
	public void Steer(double d) 
	{
		if (getHeading() == 0 && d < 0)
		{
			setHeading(359);
		}
		else
		{
			setHeading((int) (getHeading() + d));			
		}
	}
	
	public String toString()
	{
		String parentString = super.toString();
		String thisString = " maxSpeed = " + getMaximumSpeed()
						  + " steeringDirection = " + getSteeringDirection()
						  + " energyLevel = " + getEnergyLevel()
						  + " damageLevel = " + getDamageLevel()
						  + " lastBaseReached = " + getLastBaseReached();
		return "Cyborg: " + parentString + "\n " + thisString;
	}

}
