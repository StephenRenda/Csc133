package com.mycompany.a1;

public abstract class GameObject {
    public GameObject() { System.out.println("Base Constructor Called"); } 
    
    private int size;
    //All game objects have an integer attribute size
    
    public int getSize () { return size; }
    
	abstract void location();
}

 class Fixed extends GameObject {
	void location() { System.out.println("Location Fixed"); }
}

 class Movable extends GameObject {
	void location() { System.out.println("Location Movable"); }
}