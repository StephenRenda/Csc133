package com.mycompany.a1;

import com.codename1.ui.Form;

public class Game extends Form{
	
	private GameWorld gw;
	
	public Game() {
		
		gw = new GameWorld();
		gw.init();
		play();
	}
	
	private void play() {
		// code here to accept and
		// execute user commands that
		// operate on the game world
		//(refer to �Appendix - CN1
		//Notes� for accepting
		//keyboard commands via a text
		//field located on the form)
	}
}