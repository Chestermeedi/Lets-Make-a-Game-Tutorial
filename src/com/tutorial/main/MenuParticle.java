package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class MenuParticle extends GameObject{
	private Handler handler;
	Random r = new Random();
	
	private Color col;
	

	public MenuParticle(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		velX = r.nextInt(10);
		velY = r.nextInt(10);
		
		col = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));		
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int) y, 16, 16);
	}
	
	public void tick() {
		x += velX;
		y += velY;
		
		if(y <= 0 || y >= Game.HEIGHT - 32)velY *= -1; 
		if(x <= 0 || x >= Game.WIDTH - 16)velX *= -1; 
		
		//The float in this line of code will adjust the size of the trail
		handler.addObject(new Trail((int)x,(int) y, ID.Trail, col, 16, 16, 0.06f, handler));
	}
	
	public void render(Graphics g) {
		
//		Graphics2D g2d = (Graphics2D) g;
//		g.setColor(Color.green);
//		g2d.draw(getBounds());
		
		g.setColor(col);
		g.fillRect((int)x,(int)  y,  16,  16);
	}

}


