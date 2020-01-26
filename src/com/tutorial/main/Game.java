package com.tutorial.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Game extends Canvas implements Runnable{
	
	private static final long serialVersionUID = 1550691097823471818L;	
	
	// This is going to set the width and height which will be called upon in the window class
	public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
	
	// The entire game is going to run within this thread
	private Thread thread;
	// it's not recommended to use a single thread but since the game is so simple we will be able to using a single thread
	
	private boolean running = false;	
	public static boolean paused = false;
	
	private Random r;
	private Handler handler;
	private HUD hud;
	private Spawn spawner;
	private Menu menu;
	private Shop shop;
	
	public enum STATE {
		Menu,
		Help,
		Shop,
		Game,
		End
	}
	
	public static STATE gameState = STATE.Menu;	
	public static BufferedImage Sprite_Sheet;
	
	public Game() {
		
		BufferedImageLoader loader = new BufferedImageLoader();
		
		try {
			Sprite_Sheet = loader.loadImage("/Sprite_Sheet.png");
			System.out.println("loaded");
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		handler = new Handler();
		hud = new HUD();
		shop = new Shop(handler, hud);
		menu = new Menu(this, handler, hud);
		this.addKeyListener(new KeyInput(handler, this));
		this.addMouseListener(menu);
		this.addMouseListener(shop);
		
		AudioPlayer.load();
		AudioPlayer.getMusic("music").loop();
		
		new Window(WIDTH, HEIGHT, "Let's build a game", this);
		
		spawner = new Spawn(handler, hud);
		r = new Random();		
		
		if(gameState == STATE.Game) {
		handler.addObject(new Player(WIDTH/2 - 32, HEIGHT/2 - 32, ID.Player, handler));		
		handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
		
		}else {
			for(int i = 0; i < 20; i++) {
				handler.addObject(new MenuParticle(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.MenuParticle, handler));
			}
		}

		
		
		
//		for(int i = 0; i < 5; i++) 
//		handler.addObject(new BasicEnemy(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.BasicEnemy, handler));

		
		
		
		
		
//		handler.addObject(new Player(WIDTH/2 + 64, HEIGHT/2 - 32, ID.Player2));

	}


	// This method is going to start up our thread
	public synchronized void start() {
	
	// This sets "thread" to a new Thread referring to "this" as everything in this class
		thread = new Thread(this);
		
	// This is going to start our thread in motion (like putting the car in drive)
		thread.start();
	// This is asking if the thread is running.  In this case it is.
		running = true;		
	}
	
	public synchronized void stop() {
	/* This is a "Try and Catch statement stating that if it's possible to do what is asked then do it
	 * if not then do what is in the "Catch" part.  Which in this case is going to print the command "e.printStackTrace();" 
	 * into the console which is going to explain why it wasn't able to execute the code in the "Try" statement
	 */
		try {
	// thread.join is stopping the thread.
			thread.join();
			running = false;
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		//"this.requestFocus();" will make it so you don't have to click the screen to use the game
		//so it will automatically let you use the controls to play the game instead of having to click the screen
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				delta--;
			}
			if(running)
				render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}			
		}
		stop();
	}
	
	private void tick() {
		handler.tick(); 

		if(gameState == STATE.Game) {
			
			if(!paused) {
		

			hud.tick();
			spawner.tick();
			
				if(HUD.HEALTH <= 0) {
					HUD.HEALTH = 100;
					gameState = STATE.End;				
					handler.clearEnemies();
				}
			
			}			else if(gameState == STATE.Menu || gameState == STATE.End) {
				handler.tick();			
				menu.tick();
						}
		}
	}
	
	private void render() {		
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black );
				g.fillRect(0,  0, WIDTH,  HEIGHT);
				
		if(paused) {
			g.setColor(Color.yellow);
			g.drawString("PAUSED", 100, 100);
		}
		
		if(gameState == STATE.Game) {
			hud.render(g);
			handler.render(g);
		}if(gameState == STATE.Shop) {
			shop.render(g);
		}else if(gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End) {
			menu.render(g);
			handler.render(g);
		}
				
		g.dispose();
		bs.show();
	}
	
//this method is needed to be able to make the borders barriers so nothing can cross out of the window
	public static float clamp(float var, float min, float max) {
		if(var >= max)
			return var = max;
		else if(var <= min)
			return var = min;
		else return var;
	}
	
	public static void main(String [] args) {
		new Game();
	}

}
