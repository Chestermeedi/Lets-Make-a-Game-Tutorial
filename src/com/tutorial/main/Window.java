package com.tutorial.main;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends Canvas{

	private static final long serialVersionUID = -240840600533728354L;
	
	public Window(int width, int height, String title, Game game) {
		// This sets JFrame name to "frame"
		JFrame frame = new JFrame(title);
		
		//JFrame is the window that the program is going to be displayed in
		
		//These are going to set the size of the "frame" 
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		
		
		// This is going to make the X in the top right corner Function correctly
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// This sets whether or not the window can be resized by the user
		frame.setResizable(false);
		// For this lesson we're going to set it to false to avoid some unwanted problems
		
		// This is going to make the window appear in the middle of the screen when the program is executed
		frame.setLocationRelativeTo(null);
		// Usually the window will start in the top left of the computer screen
		
		// This is adding the game class to the "frame"
		frame.add(game);
		
		// This is going to allow the user to see the border around the window 
		frame.setVisible(true);
		
		// this is the start method in the game class
		game.start();
		

	}
	
	

}
