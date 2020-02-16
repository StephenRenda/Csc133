package com.mycompany.a1;

public class Base extends Fixed {
	
	private int sequenceNumber;
	
	public Base()
	{
		setColor(0,191,255);
		size =10;

	}
	public  int getSequenceNumber() {
		return sequenceNumber;
	}
	public void setSequenceNumber(int sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	public String toString()
	{
		String parentString = super.toString();
		String thisString = " size = " + getSize() + " sequenceNumber " + getSequenceNumber();
		return "Base: " + parentString + thisString;
	}
}
