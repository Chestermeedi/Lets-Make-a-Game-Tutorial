package com.tutorial.main;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Trail extends GameObject {
	
	private float life;
	private float alpha = 1;
	private Handler handler;
	private Color color;
	private int width, height;
	
	//this is what we can say about life
	// life = 0.001 - 0.1;

	public Trail(int x, int y, ID id, Color color, int width, int height, float life, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		this.color = color;
		this.width = width;
		this.height = height;
		this.life = life;
		
	}

	public void tick() {
		if(alpha > life) {
			alpha -= (life - 0.0001f);
		}else handler.removeObject(this);
	}
	
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setComposite(makeTransparent(alpha));  //From Here
		
		g.setColor(color);						   //all the
		g.fillRect((int)x,(int)y, width, height);		   //way to
		
		g2d.setComposite(makeTransparent(1));	  //Here is the Trail Sandwitch
												//it's the code that makes the trail where it needs to be
												// and ends it properly

		
	}

	private AlphaComposite makeTransparent(float alpha) {
		int type = AlphaComposite.SRC_OVER;
				return (AlphaComposite.getInstance(type, alpha));
	}
	
	public Rectangle getBounds() {
		return null;
	}

}
