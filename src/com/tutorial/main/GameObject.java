package com.tutorial.main;

import java.awt.Graphics;
import java.awt.Rectangle;

// This class is going to have all the objects of the games and handle every single object that the game has

public abstract class GameObject {

	protected float x, y;
// This is in conjunction with the ID Enum class
	protected ID id;
// These variables are going to control the speed in the X direction and the speed in the Y direction
	protected float velX, velY;
	
// This is a Constructor.  Whenever an instance of "GameObject" is created 
// we will need to set the parameters for each of these variables
	public GameObject(float x, float y, ID id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}

// Since these 2 methods are abstract we will need to use them in our player class
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
	
// These are getters and setters and make it easier to change the value of each Parameter to a different setting each time it is used

	public float getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}

	public float getVelX() {
		return velX;
	}

	public void setVelX(int velX) {
		this.velX = velX;
	}

	public float getVelY() {
		return velY;
	}

	public void setVelY(int velY) {
		this.velY = velY;
	}
	
	
}
