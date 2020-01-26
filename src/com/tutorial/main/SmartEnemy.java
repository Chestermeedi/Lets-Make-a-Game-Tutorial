package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class SmartEnemy extends GameObject{
	
	private Handler handler;
	private GameObject player;
	
	private BufferedImage smart_enemy_image;

	public SmartEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);		
		this.handler = handler;
		
		SpriteSheet ss = new SpriteSheet(Game.Sprite_Sheet);
		
		smart_enemy_image = ss.grabImage(1, 4, 16, 16);
		
		
		
		for(int i = 0; i<handler.object.size(); i++) {
			if(handler.object.get(i).getId() == ID.Player) player = handler.object.get(i);
		}
		
		
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int) y, 16, 16);
	}
	
	public void tick() {
		x += velX;
		y += velY;
		
		float diffX = x - player.getX() - 8;
		float diffY = y - player.getY() - 8;
		float distance = (float)Math.sqrt((x - player.getX()) * (x - player.getX()) + (y - player.getY()) * (y - player.getY()));

		velX = (float) ((-1.0/distance) * diffX) ;
		velY = (float) ((-1.0/distance) * diffY) ;

		
		if(y <= 0 || y >= Game.HEIGHT - 32)velY *= -1; 
		if(x <= 0 || x >= Game.WIDTH - 16)velX *= -1; 
		
		//The float in this line of code will adjust the size of the trail
		handler.addObject(new Trail((int)x,(int) y, ID.Trail, Color.white, 16, 16, 0.02f, handler));

	}
	
	public void render(Graphics g) {
		
//		Graphics2D g2d = (Graphics2D) g;
//		g.setColor(Color.green);
//		g2d.draw(getBounds());
		
		
//		g.setColor(Color.white);
//		g.fillRect((int)x,(int)  y,  16,  16);
		g.drawImage(smart_enemy_image, (int)x, (int)y, null);
	}

}
