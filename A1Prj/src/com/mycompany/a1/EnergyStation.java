package com.mycompany.a1;

public class EnergyStation extends Fixed {
	
	private int capacity;
	
	public EnergyStation()
	{
		setColor(0,128,0);
		size =r.nextInt(30);
		
		capacity = getSize();
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public String toString()
	{
		String parentString = super.toString();
		String thisString = " size = " + getSize() + " capacity =" + getCapacity();;
		return "EnergyStation: " + parentString + thisString;
	}
}
