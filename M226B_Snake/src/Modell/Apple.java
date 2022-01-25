package Modell;

import java.awt.Rectangle;

import Controller.Game;

public class Apple {
	private int x;
	private int y;
	
	public Apple(Snake player) {
		this.random_spawn(player);
	}
	/**
	 * 
	 * @param player
	 */
	public void random_spawn(Snake player) {
		boolean onSnake = true;
		while(onSnake) {
			onSnake = false;
			//x und y bekommen eine random zahl 
			x = (int)(Math.random() * Game.width - 1);
			y = (int)(Math.random() * Game.height - 1);
			
			//wenn Rectangle r die gleichen Koordinaten hat wie x und y dann wird onSnake auf true gesetzt und x und y bekommen wieder random Koordinaten
			for(Rectangle r : player.getBody()){
				if(r.x == x && r.y == y) {
					onSnake = true;
				}
			}
		}
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}