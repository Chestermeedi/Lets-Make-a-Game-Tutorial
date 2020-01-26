package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class BasicEnemy extends GameObject{
	
	private Handler handler;
	
	private BufferedImage basic_enemy_image;

	public BasicEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		SpriteSheet ss = new SpriteSheet(Game.Sprite_Sheet);
		
		basic_enemy_image = ss.grabImage(1, 2, 16, 16);
		
		velX = 5;
		velY = 5;
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
		handler.addObject(new Trail((int)x,(int) y, ID.Trail, Color.red, 16, 16, 0.04f, handler));

	}
	
	public void render(Graphics g) {
		
//		Graphics2D g2d = (Graphics2D) g;
//		g.setColor(Color.green);
//		g2d.draw(getBounds());
		
		
//		g.setColor(Color.red);
//		g.fillRect((int)x,(int)  y,  16,  16);
		
		g.drawImage(basic_enemy_image, (int) x, (int) y, null);
	}

}
