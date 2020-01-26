package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class EnemyBossBullet extends GameObject{
	
	private Handler handler;
	Random r = new Random();

	public EnemyBossBullet(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		velX = (r.nextInt(5 - -5) + -5);
		velY = 5;
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int) y, 4, 4);
	}
	
	public void tick() {
		x += velX;
		y += velY;
		
//		if(y <= 0 || y >= Game.HEIGHT - 32)velY *= -1; 
//		if(x <= 0 || x >= Game.WIDTH - 16)velX *= -1; 
		
		if(y >= Game.HEIGHT) handler.removeObject(this);
		
		//The float in this line of code will adjust the size of the trail
		handler.addObject(new Trail((int)x,(int) y, ID.Trail, Color.green, 4, 4, 0.04f, handler));

	}
	
	public void render(Graphics g) {
		
//		Graphics2D g2d = (Graphics2D) g;
//		g.setColor(Color.green);
//		g2d.draw(getBounds());
		
		
		g.setColor(Color.green);
		g.fillRect((int)x,(int)  y,  4,  4);
	}

}