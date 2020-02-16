package com.mycompany.a1;

import com.codename1.ui.Form;
import com.codename1.ui.events.ActionListener;
import com.mycompany.a1.GameWorld.Entity;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import java.lang.String;

public class Game extends Form{
	
	private GameWorld gw;
	private boolean exit = false;
	
	public Game() {
		
		gw = new GameWorld();
		gw.init();
		play();
	}
	
	private void play() {
		Label myLabel=new Label("Enter a Command:");
		this.addComponent(myLabel);
		final TextField myTextField=new TextField();
		this.addComponent(myTextField);
		this.show();
		myTextField.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt) {
			String sCommand=myTextField.getText().toString();
			myTextField.clear();
			if(sCommand.length() != 0 && !gw.GameOver())
				switch (sCommand.charAt(0)) {
					case 'a' : 
						System.out.println("Accelerating");
						gw.ModifySpeed(true);
						break;
					case 'b' : 
						System.out.println("Accelerating");
						gw.ModifySpeed(false);
						break;
					case 'l' : 
						System.out.println("Steer left");
						gw.TurnCyborg(false);
						break;
					case 'r' : 
						System.out.println("Steer right");
						gw.TurnCyborg(true);
						break;
					case 'c' : 
						gw.Collision(Entity.PLAYER, Entity.ENEMY, 0);
						break;
					case '1' : 
						gw.Collision(Entity.PLAYER, Entity.BASE, 1);
						break;
					case '2' :
						gw.Collision(Entity.PLAYER, Entity.BASE, 2);
						break;
					case '3' : 
						gw.Collision(Entity.PLAYER, Entity.BASE, 3);
						break;
					case '4' : 
						gw.Collision(Entity.PLAYER, Entity.BASE, 4);
						break;
					case '5' : 
						gw.Collision(Entity.PLAYER, Entity.BASE, 5);
						break;
					case '6' : 
						gw.Collision(Entity.PLAYER, Entity.BASE, 6);
						break;
					case '7' : 
						gw.Collision(Entity.PLAYER, Entity.BASE, 7);
						break;
					case '8' : 
						gw.Collision(Entity.PLAYER, Entity.BASE, 8);
						break;
					case '9' : 
						gw.Collision(Entity.PLAYER, Entity.BASE, 9);
						break;						
					case 'e' :
						gw.Collision(Entity.PLAYER, Entity.ENERGYSTATION, 0);
						break;
					case 'g' :
						gw.Collision(Entity.PLAYER, Entity.DRONE, 0);
						break;
					case 't' :
						gw.AdvanceGameClock();
						break;
					case 'd' :
						gw.Display();
						break;
					case 'x':
						System.out.println(" Confirm exit (y/n): ");
						exit = true;
						break;
					case 'y':
						if(exit)
						{
							System.out.println("Exiting....");
							System.exit(0);							
						}
						break;
					case 'n':
						if(exit)
							System.out.println("Cancel Exit");
						break;
					case 'm':
						gw.PrintMap();
						break;
				} //switch
			} //actionPerformed
		} //new ActionListener()
		); //addActionListener
	} //play
}