package com.tutorial.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import com.tutorial.main.Game.STATE;

public class Menu extends MouseAdapter{
	
	private Game game;
	private Handler handler;
	private HUD hud;
	private Random r = new Random();
	
	public Menu(Game game, Handler handler, HUD hud) {
		this.game = game;
		this.hud = hud;
		this.handler = handler;
	}
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		if(game.gameState == STATE.Menu) {
		
			//Play Button
			if(mouseOver (mx, my, 210, 150, 200, 64)) {			
				game.gameState = STATE.Game;
				handler.addObject(new Player(Game.WIDTH/2 - 32, Game.HEIGHT/2 - 32, ID.Player, handler));	
				handler.clearEnemies();
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
				
				AudioPlayer.getSound("menu_sound").play();
			}		
			//Help Button
			if(mouseOver (mx, my, 210, 250, 200, 64)) {
				game.gameState = STATE.Help;
				
				AudioPlayer.getSound("menu_sound").play();

			}	
			//Quit Button
			if(mouseOver (mx, my, 210, 350, 200, 64)) {
				System.exit(1);
				
				
			}
		}
		//Back Button in Help Menu
		if(game.gameState == STATE.Help) {
			if(mouseOver(mx, my, 210, 350, 200, 64)) {
				game.gameState = STATE.Menu;
				AudioPlayer.getSound("menu_sound").play();
				return;
			}
		}
		//Game Over Menu
		if(game.gameState == STATE.End) {
			if(mouseOver(mx, my, 210, 350, 200, 64)) {
				game.gameState = STATE.Game;
				hud.setLevel(1);
				hud.setScore(0);
				handler.addObject(new Player(Game.WIDTH/2 - 32, Game.HEIGHT/2 - 32, ID.Player, handler));	
				handler.clearEnemies();
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
				AudioPlayer.getSound("menu_sound").play();

			}
		}
	}
	
	public void mouseReleased(MouseEvent e) {
		
		
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if(mx > x && mx < x + width) {
			if(my > y && my < y + height) {
				return true;
			}else return false;
		}else return false;
	}
	
	public void tick() {
		
		
		
	}
	
	public void render(Graphics g) {
		
		if(game.gameState == STATE.Menu) {
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			Font fnt3 = new Font("arial", 1, 30);
			Font fnt4 = new Font("arial", 1, 30);
		
		
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Wave", 50, 50);
			
					
			g.setColor(Color.white);
			g.drawRect(210, 150, 200, 64);
			g.setFont(fnt2);
			g.setColor(Color.white);
			g.drawString("Play", 270, 190);
			
			g.setColor(Color.white);
			g.drawRect(210, 250, 200, 64);
			g.setFont(fnt3);
			g.setColor(Color.white);
			g.drawString("Help", 270, 290);
			
			g.setColor(Color.white);
			g.drawRect(210, 350, 200, 64);
			g.setFont(fnt4);
			g.setColor(Color.white);
			g.drawString("Quit", 270, 390);
			
			}else if(game.gameState == STATE.Help) {
				
				Font fnt = new Font("arial", 1, 50);
				Font fnt2 = new Font("arial", 1, 30);

				
				g.setFont(fnt);
				g.setColor(Color.white);
				g.drawString("Help", 50, 50);
				
				g.setFont(fnt2);
				g.drawString("use WASD keys to move player", 100, 225);
				g.drawString("Dodge Enemies", 200, 275);

				
				g.setColor(Color.white);
				g.drawRect(240, 357, 150, 64);
				g.setFont(fnt2);
				g.setColor(Color.white);
				g.drawString("Back", 275, 400);			
				
				}else if(game.gameState == STATE.End) {
					
					Font fnt = new Font("arial", 1, 50);
					Font fnt2 = new Font("arial", 1, 30);
		
					
					g.setFont(fnt);
					g.setColor(Color.white);
					g.drawString("Game Over", 50, 50);
					
					g.setFont(fnt2);
					g.drawString("You lost with a Score of " + hud.getScore(), 100, 225);
					g.drawString("Try again", 200, 275);
		
					
					g.setColor(Color.white);
					g.drawRect(240, 357, 150, 64);
					g.setFont(fnt2);
					g.setColor(Color.white);
					g.drawString("Back", 275, 400);
		
					
				
			}
	}
	
	

}
