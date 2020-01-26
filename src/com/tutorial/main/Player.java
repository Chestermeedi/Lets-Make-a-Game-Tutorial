package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Player extends GameObject{

	public int sze = 32;
	
	Random r = new Random();
	Handler handler;
	
	private BufferedImage player_image;
	
	public Player(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
		SpriteSheet ss = new SpriteSheet(Game.Sprite_Sheet);
		
		player_image = ss.grabImage(1, 1, sze, sze);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x,(int) y, sze, sze);
	}
		
//		velX = r.nextInt(5) + 1;
//		velY = r.nextInt(5);
		
	
	public void tick() {
		x += velX;
		y += velY;		
		
// this is how we make the player unable to go outside the window using the "Game.Clamp" method
		x = Game.clamp(x, 0, Game.WIDTH - 37);
		y = Game.clamp(y, 0, Game.HEIGHT - 60);
		
		
		//This is the trail on the bottom
//		handler.addObject(new Trail((int)x,(int) y, ID.Trail, Color.green, 16, 16, 0.08f, handler));
		//this is the trail above to give it the seamless fade effect
//		handler.addObject(new Trail((int)x,(int) y, ID.Trail, Color.cyan, 16, 16, 0.12f, handler));


		
		collision();
		
	}
	
	private void collision() {
		for (int i = 0; i < handler.object.size(); i++){			
			
			
			GameObject tempObject = handler.object.get(i);
			
			//collision code (whatever is in this code deals with the enemy)
			if(tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.FastEnemy  || tempObject.getId() == ID.SmartEnemy || tempObject.getId() == ID.EnemyBoss) { //so now "tempObject" is "BasicEnemy"
				if(getBounds().intersects(tempObject.getBounds())) {
					HUD.HEALTH -=2;
					
				}
			}
			
		}
	}

	public void render(Graphics g) {
//		if(id == ID.Player)g.setColor(Color.cyan); 
//		else if(id == ID.Player2)g.setColor(Color.magenta);
//		g.fillRect((int)x, (int)y, 32, 32);
		g.drawImage(player_image, (int)x, (int)y, null);

	}
}
