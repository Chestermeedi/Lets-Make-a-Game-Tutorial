package com.tutorial.main;

import java.awt.Graphics;
import java.util.LinkedList;

// This class is going to loop through and individually maintain, update and render all of the objects in the game

public class Handler {

	LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	public int spd = 5;
	
	public void tick() {
// "i" represents the object, "object.size" represents how many of the objects being called there are
	// if there are 2 objects in the room and "i" is less than the "object.size" then the code beneath the for loop will be executed
		//if "i" is 2 and there are only 2 objects in the room then that makes them equal so the code wont run
			// the code will only run if "i" is less then "object.size" which in this example is 2
		for(int i = 0; i < object.size(); i++) {
// this is going to allow us to temporarily set "i" to a specific object and run through the linked list searching for it.
			GameObject tempObject = object.get(i);
			
			tempObject.tick();
		}
	}
	
	public void render(Graphics g) {
		for(int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			
// this will allow us to pull that object "ID" when we need it and put it in the game.
	//then set "i" backed to null until we need another object.
			tempObject.render(g);
		}
	}
	
	public void clearEnemies() {
		for(int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
		
			if(tempObject.getId() == ID.Player) {
				object.clear();
				if(Game.gameState != Game.STATE.End);
				addObject(new Player((int)tempObject.getX(), (int) tempObject.getY(), ID.Player, this));
				
			}
		}
			
		}
// this will add an object to the linked list "GameObject"
		public void addObject(GameObject object) {
			this.object.add(object);
		}	
// this will remove an object from the linked list "GameObject"
		public void removeObject(GameObject object) {
			this.object.remove(object);
		}
}