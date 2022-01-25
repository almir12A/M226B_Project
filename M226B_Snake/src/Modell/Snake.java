package Modell;

import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import Controller.Game;



public class Snake {
	private ArrayList<Rectangle> body;
	private int w = Game.width;
	private int h = Game.height;
	private int d = Game.dimension;

	
	private String move; //NOTHING, UP, DOWN, LEFT, RIGHT
	/**
	 * Snake wird herstellt mit drei Rect’s lang und move ist auf Nothing was heisst das Snake bewegt sich noch nicht.
	 */
	public Snake() {
		body = new ArrayList<>();
		
		Rectangle rect = new Rectangle(Game.dimension, Game.dimension);
		rect.setLocation(Game.width / 2 * Game.dimension, Game.height / 2 * Game.dimension);
		body.add(rect);
		
		rect = new Rectangle(d, d);
		rect.setLocation((w / 2 - 1) * d, (h / 2) * d);
		body.add(rect);
		
		rect = new Rectangle(d, d);
		rect.setLocation((w / 2 - 2) * d, (h / 2) * d);
		body.add(rect);
		
		move = "NOTHING";// er soll stehen bleiben
	}
	
	/**
	 * in welcher richtung sich der Snake bewegt und wie schnell
	 */
	public void move() {
		if(move != "NOTHING") {
			Rectangle first = body.get(0);
			
			Rectangle rect = new Rectangle(Game.dimension, Game.dimension);
			
			if(move == "UP") {
				rect.setLocation(first.x, first.y - Game.dimension);
			}
			else if(move == "DOWN") {
				rect.setLocation(first.x, first.y + Game.dimension);
			}
			else if(move == "LEFT") {
				rect.setLocation(first.x - Game.dimension, first.y);
			}
			else{
				rect.setLocation(first.x + Game.dimension, first.y);
			}
			
			body.add(0, rect);
			body.remove(body.size()-1);
		}
	}
	/**
	 * Bei der Klasse update wird player.grow(); abgerufen wenn es den Apple isst und in der Klasse grow wird der neue Rect an der der Snake hinten angefügt.
	 */
	public void grow() {
		Rectangle first = body.get(0);
		
		Rectangle rect = new Rectangle(Game.dimension, Game.dimension);
		
		if(move == "UP") {
			rect.setLocation(first.x, first.y - Game.dimension);
		}
		else if(move == "DOWN") {
			rect.setLocation(first.x, first.y + Game.dimension);
		}
		else if(move == "LEFT") {
			rect.setLocation(first.x - Game.dimension, first.y);
		}
		else{
			rect.setLocation(first.x + Game.dimension, first.y);
		}
		
		body.add(0, rect);
	}

	public ArrayList<Rectangle> getBody() {
		return body;
	}
	

	public void setBody(ArrayList<Rectangle> body) {
		this.body = body;
	}
	
	public int getX() {
		return body.get(0).x;
	}
	
	public int getY() {
		return body.get(0).y ;
	}
	
	public String getMove() {
		return move;
	}
	
	public void up() {
		move = "UP";
	}
	public void down() {
		move = "DOWN";
	}
	public void left() {
		move = "LEFT";
	}
	public void right() {
		move = "RIGHT";
	}




}