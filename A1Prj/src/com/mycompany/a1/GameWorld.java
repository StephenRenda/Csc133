package com.mycompany.a1;

import java.util.ArrayList;
import java.util.Iterator;


import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;

public class GameWorld {
	//Assume game world size is fixed. 
	private static final int world_width = 1000;
	private static final int world_height = 1000;
	//Entities to distinguish between collision types.
	public enum Entity { PLAYER, ENEMY, BASE, ENERGYSTATION, DRONE}
	//List to hold all game object.
	private ArrayList<GameObject> list;
	private GameObjectIterator iterator;
	
	private int sequenceIndex = 1;
	private int lives;
	private int currentTime;
	
	private boolean gameOver;
	
	public void init(){
		//initial game objects/setup
		list = new ArrayList<GameObject>();
		iterator = new GameObjectIterator();	

		currentTime = 0;
		lives = 3;
		gameOver = false;
		
		createBase();
		createBase();
		createBase();
		createBase();
		createCyborg();
		createDrone();
		createDrone();
		createEnergyStation();
		createEnergyStation();

		}
	public void createBase() {
		Base base = new Base();
		base.setSequenceNumber(getSequenceIndex());
		accumulateSequenceIndex();
		base.setLocation(new Point(150 * getSequenceIndex(),150 * getSequenceIndex()));
		list.add(base);
	}
	public int getSequenceIndex() {
		return sequenceIndex;
	}
	public void accumulateSequenceIndex() {
		this.sequenceIndex += 1;
	}
	public void createCyborg() {
		Cyborg cyborg = new Cyborg();
		cyborg.setLocation(new Point(300 ,300));

		list.add(cyborg);
	}
	public void createDrone() {
		Drone drone = new Drone();
		list.add(drone);
	}
	public void createEnergyStation() {
		EnergyStation energyStation = new EnergyStation();
		list.add(energyStation);
	}
	public void ModifySpeed(boolean speedUp)
	{
		Cyborg cyborgObj = FindCyborg();
		if (cyborgObj != null)
		{
			if ((cyborgObj.getSpeed()) >= (cyborgObj.getMaximumSpeed() - (cyborgObj.getDamageLevel() * (cyborgObj.getMaximumSpeed()/4))))
			{
				if (speedUp)
				{
					System.out.println("Max Speed Reached!");
				}
				else
				{
					cyborgObj.AdjustSpeed(speedUp);
				}
			}
			else 
			{
				cyborgObj.AdjustSpeed(speedUp);
			}
		}
	}
	public void TurnCyborg(boolean right)
	{
		Cyborg cyborg = FindCyborg();
		if (cyborg != null)
		{
			if (right)
			{
				cyborg.setSteeringDirection(cyborg.getSteeringDirection() + 5);
			}
			else
			{
				cyborg.setSteeringDirection(cyborg.getSteeringDirection() - 5);
			}
		}
	}
	public void Collision(Entity entityOne, Entity entityTwo, int baseNum)
	{
		Cyborg objectOne = null;
		GameObject objectTwo = null;
		if (entityOne != null && entityTwo != null)
		{
			switch (entityOne)
			{
				case PLAYER:
					objectOne = FindCyborg();
					break;

				default:
					System.err.println("Bad value passed");
					break;
			}
			
			if (objectOne != null)
			{
				switch (entityTwo)
				{
					case BASE:
						int i = -1;
						while (iterator.hasNext())
						{
							i++;
							if (iterator.next() instanceof Base)
							{
								Base base = (Base) list.get(i);
								if (base.getSequenceNumber() == baseNum)									
								{
									if (base.getSequenceNumber() == (objectOne.getLastBaseReached() + 1))									
									{
										objectTwo = base;	
									}	
									else
									{
										objectTwo = null;
									}
								}
							}
						}
						break;
						
					case ENEMY:
						objectTwo = FindCyborg();
						break;
						
					case ENERGYSTATION:
						i = -1;
						boolean found = false;
						while (iterator.hasNext())
						{
							i++;
							if (iterator.next() instanceof EnergyStation)
							{
								EnergyStation energyStation = (EnergyStation) list.get(i);
								
								if (energyStation.getCapacity() > 0 && !found)
								{
									objectTwo= energyStation;
									found = true;
								}	
							}
						}
						break;	
					case DRONE:
						i = -1;
						while (iterator.hasNext())
						{
							i++;
							if (iterator.next() instanceof Drone)
							{
								Drone drone = (Drone) list.get(i);
								objectTwo= drone;
							}
						}
						break;
					default:
						System.err.println("Bad value passed");
						break;
				}
				if (objectTwo != null)
				{
					if (objectTwo instanceof Cyborg) {
						
						CyborgCollision(objectOne);
					}
					if (objectTwo instanceof Base) {
						
						BaseCollision(objectOne, objectTwo);
					}
					if (objectTwo instanceof EnergyStation) {
						
						EnergyStationCollision(objectOne, objectTwo);
					}
					if (objectTwo instanceof Drone) {
						
						DroneCollision(objectOne);
					}
				}
				else
				{
					if (baseNum == (objectOne.getLastBaseReached() + 1))
					{
						System.err.println("Cant find " + entityTwo + " " + baseNum + "." );
					}
					else
					{
						System.err.println("Base #" + baseNum + ". Out of sequence, correct base is #"+ (objectOne.getLastBaseReached() + 1));
					}					
				}
			}
			else
			{
				System.err.println("No instance of " + entityOne);
			}
		}
		else
		{
			System.err.println("A value of null was passed to method");
			System.err.println("Entity one = " + entityOne + "\nEntity two = " + entityTwo);
		}
	}
	public void Display()
	{
		Cyborg cyborg = FindCyborg();
		
		if (FindInstanceOfCyborg())
		{
			System.out.println("Lives: " + lives);
			System.out.println("Current time: " + currentTime);
			System.out.println("Highest base visited: " + cyborg.getLastBaseReached());
			System.out.println("Highest base visited: " + cyborg.getLastBaseReached());
			System.out.println("Energy Level: " + cyborg.getEnergyLevel());
			System.out.println("Damage Level: " + cyborg.getDamageLevel());
			System.out.println();
		}
		
	}
	public void CyborgCollision(Cyborg objectOne)
	{
		objectOne.setDamageLevel(objectOne.getDamageLevel() + 1);
		System.out.println("Player cyborg's damageLevel is set to " + objectOne.getDamageLevel());
		System.out.println("PRETEND enemy cyborg color becomes lighter red.");	
	}
	public void BaseCollision(Cyborg objectOne, GameObject objectTwo)
	{
		objectOne.setLastBaseReached(objectOne.getLastBaseReached() + 1);	
		System.out.println("Based #" + ((Base) objectTwo).getSequenceNumber() + ", at location: " + objectTwo.getLocation().getX() +" " + objectTwo.getLocation().getY() +
				" Reached! Correct sequence with lastBaseReached " + objectOne.getLastBaseReached());
	}
	public void EnergyStationCollision(Cyborg objectOne, GameObject objectTwo)
	{
		System.out.println("color before:" + ColorUtil.green((objectTwo.getColor())));
		objectTwo.setColor(0, 255, 0);
		System.out.println("color after:" + ColorUtil.green((objectTwo.getColor())));
		System.out.println("Found energyStation at location: " + ((EnergyStation)objectTwo).getLocation().getX() + "," + + objectTwo.getLocation().getY());
	
		objectOne.setEnergyLevel(objectOne.getEnergyLevel() + ((EnergyStation) objectTwo).getCapacity());
		System.out.println("New energyLevel: " + objectOne.getEnergyLevel());

		((EnergyStation) objectTwo).setCapacity(0);
		System.out.println("With capacity: " + ((EnergyStation) objectTwo).getCapacity());

		createEnergyStation();
		System.out.println("New energyStation created!");
	}
	public void DroneCollision(Cyborg objectOne)
	{
		objectOne.setDamageLevel(objectOne.getDamageLevel() + 1);
		objectOne.setColor(((ColorUtil.red(objectOne.getColor()) >= 255) ? 255 : (ColorUtil.red(objectOne.getColor()) + 25)), 128, 128);	
	}
	public void AdvanceGameClock()
	{
		int i = -1;
		while (iterator.hasNext())
		{
			i++;
			if (iterator.next() instanceof Moveable)
			{
				Moveable move = (Moveable) list.get(i);
				if (iterator.current() instanceof Cyborg)
				{
					Cyborg cyborg = (Cyborg) list.get(i);
					if (cyborg.getEnergyLevel() == 0 || 
					   (cyborg.getDamageLevel() == 4))
					{
						lives--;
						System.out.println("Life lost, "+ lives + " remaining.");
						list.remove(cyborg);
						createCyborg();
						currentTime = 0;
						if (lives == 0)
						{
							gameOver = true;
						}
					}
					else
					{
						cyborg.setEnergyLevel(cyborg.getEnergyLevel() - cyborg.getEnergyConsumptionRate());
						cyborg.Steer(cyborg.getSteeringDirection());
						move.Move();
					}
				}
				else
				{
					move.Move();
				}
			}
	
		}
		currentTime++;
	}
	public void PrintMap()
	{
		System.out.println("Printing");
		while (iterator.hasNext())
		{
			System.out.println(iterator.next());
		}
	}
	private Cyborg FindCyborg()
	{
		int i = -1;
		Cyborg temp = null;
		while (iterator.hasNext())
		{
			i++;
			if (iterator.next() instanceof Cyborg)
			{
				temp = (Cyborg) list.get(i);
				iterator.ResetIndex();
				break;
			}
		}
		
		if (temp == null)
		{
			System.err.println("No Cyborg has been created yet"); 
			return null;
		}
		else { return temp; }
	} 
	private boolean FindInstanceOfCyborg()
	{
		if (list.size() > 0 && !gameOver)
		{
			while (iterator.hasNext())
			{
				if (iterator.next() instanceof Cyborg)
				{
					iterator.ResetIndex();
					return true;
				}
			}
		}
		return false;
	}
	public boolean GameOver()
	{
		if (gameOver) { System.out.println("Cyborg has run out of lives!"); }
		return gameOver;
	}
	private class GameObjectIterator implements Iterator<Object>
	{
		private int index;
		
		public GameObjectIterator() 
		{
			index = -1;
		}
		
		@Override
		public boolean hasNext() 
		{
			if (list.size() <= 0) return false;
			if (index == list.size() - 1) 
			{
				index = -1;
				return false;
			}
			return true;
		}

		@Override
		public Object next() 
		{
			index++;
			return list.get(index);
		}

		@Override
		public void remove() 
		{
			list.remove(index);
			index--;
		}
		

		public void ResetIndex()
		{
			index = -1;
		}
		

		public Object current()
		{
			if (index >= 0)
			{
				return list.get(index);
			}
			else
			{
				return null;
			}
		}
	}
}
