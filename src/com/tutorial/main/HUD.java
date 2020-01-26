package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;

public class HUD {
	
	public int bounds = 0;
	public static float HEALTH = 100;
	private float greenValue = 255f;
	
	private int score = 0;
	private int level = 1;
	
	public void tick() {
		HEALTH = Game.clamp(HEALTH, 0, 100+(bounds/2));
		greenValue = HEALTH*2;
		greenValue = Game.clamp(greenValue,  0,  255);	
		
		score++;
				
	}

	public void render(Graphics g) {
		//this is the grey bar under the health
		g.setColor(Color.gray);
		g.fillRect(15, 15, 200 + bounds, 10);
		
		//this helps the health bar transition from green to red
		g.setColor(new Color(75, (int)greenValue, 0));		
		
		g.fillRect(15, 15, (int)HEALTH * 2, 10);
		
		//this is the healthbars outline
		g.setColor(Color.white);
		g.drawRect(15, 15, 200 + bounds, 10);
		
		//this is setting the positions of the "Score" and "Level" text
		g.drawString("Score " + score, 15, 48);
		g.drawString("Level " + level, 15, 61);
		g.drawString("Space for Shop", 15, 80);


	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	
	
	
}
