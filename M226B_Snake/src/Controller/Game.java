package Controller;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Modell.Apple;
import Modell.Snake;
import View.Graphics;

/**
 * 
 * @author almir
 *
 */
public class Game implements KeyListener{
	private Snake player;
	private Apple apple;
	private Graphics graphics;
	private JFrame window;
	
	public static final int width = 30;
	public static final int height = 30;
	public static final int dimension = 20;
	private String highscore = "";
	private Snake s;

	/**
	 * Konstruktor aufrufe
	 */
	public Game() {
		window = new JFrame();
		
		player = new Snake();
		
		apple = new Apple(player);
		
		graphics = new Graphics(this);
		
		window.add(graphics);
		
		window.setTitle("Snake");
		window.setSize(width * dimension + 2, height * dimension + dimension + 4);
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		s = getPlayer();
	}


	/**
	 * state wird auf Runnung gesetzt
	 */
	public void start() {
		graphics.state = "RUNNING";
		
	}
	/**
	 * überprüft ob state == Running ist sollen player und apple angezeigt werden
	 */
	public void update() {
		if(graphics.state == "RUNNING") {
			if(check_food_collision()) {
				player.grow();
				apple.random_spawn(player);
			}
			/**
			 * prüft ob es sich selber oder die wand berührt hat wenn ja dann wird state auf End gesetzt
			 */
			else if(check_wall_collision() || check_self_collision()) {
				graphics.state = "END";
				if (highscore.equals("")) {
					highscore = this.GetHighScore();
				}
				checkScore();
			}
			else {
				player.move();
			}
		}
	}
	/**
	 * Wenn der neue Score Gösser ist als der Highscore muss man seinem Namen angeben und der neue Highscore wird auf dem File highscore.txt gespeichert.
		
	 */
	public void checkScore() {
		
		if(highscore.equals("")) {
			return;
		}
		if ((s.getBody().size() - 3) > Integer.parseInt((highscore.split(":")[1]))) {
			String name = JOptionPane.showInputDialog("New highscore. Dein Namen");
			highscore = name + ":" + (s.getBody().size() - 3);
			
			//erstellt ein file und überprüft ob es einen schon gibt
			File scoreFile = new File("highscore.txt");
			if (!scoreFile.exists()) {
				try {
					scoreFile.createNewFile();
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
			/**
			 * Der Alte Highscore wurde vom neuem überschrieben.
			 */
			FileWriter writeFile = null;
			BufferedWriter writer = null;
			try {
				writeFile = new FileWriter(scoreFile);
				writer = new BufferedWriter(writeFile);
				writer.write(this.highscore);
			}catch (Exception e) {
				System.out.println(e);
			}
			finally {
				try {
					if(writer !=null) {
						writer.close();
					}
				}catch (Exception e) {}
				
			}
		}
	}
		
	/**
	 * nimmt den HighScore was in der highscore.txt ist
	 * @return
	 */
	public String GetHighScore() {
		FileReader readFile = null;
		BufferedReader reader = null;
		try {
			readFile = new FileReader("highscore.txt");
			reader = new BufferedReader(readFile);
			return reader.readLine();
		} catch (Exception e) {
			return "Nobody:2";
			//e.printStackTrace();
		}
		finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * Überprüft, ob der Player die Koordinaten des Fensterende Übertritten hat.
	 * @return
	 */
	private boolean check_wall_collision() {
		if(player.getX() < 0 || player.getX() >= width * dimension 
				|| player.getY() < 0|| player.getY() >= height * dimension) {
			return true;
		}
		return false;
	}
	/**
	 * Überprüft, ob der Player im Radius vom Apple ist.
	 * @return
	 */
	private boolean check_food_collision() {
		if(player.getX() == apple.getX() * dimension && player.getY() == apple.getY() * dimension) {
			return true;
		}
		return false;
	}
	/**
	 * Überprüft, ob der Player sich selbst berührt. 
	 * @return
	 */
	private boolean check_self_collision() {
		for(int i = 1; i < player.getBody().size(); i++) {
			if(player.getX() == player.getBody().get(i).x &&
					player.getY() == player.getBody().get(i).y) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void keyTyped(KeyEvent e) {	}
	/**
	 * der Player(Snake) wird mit den Pfeiltasten gesteuert
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		
		int keyCode = e.getKeyCode();
		if(graphics.state == "RUNNING") {
			if(keyCode == KeyEvent.VK_UP && player.getMove() != "DOWN") {
				player.up();
			}
		
			if(keyCode == KeyEvent.VK_DOWN && player.getMove() != "UP") {
				player.down();
			}
		
			if(keyCode == KeyEvent.VK_LEFT && player.getMove() != "RIGHT") {
				player.left();
			}
		
			if(keyCode == KeyEvent.VK_RIGHT && player.getMove() != "LEFT") {
				player.right();
			}
		}
		else {
			this.start();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {	}

	public Snake getPlayer() {
		return player;
	}

	public void setPlayer(Snake player) {
		this.player = player;
	}

	public Apple getApple() {
		return apple;
	}

	public void setApple(Apple apple) {
		this.apple = apple;
	}

	public JFrame getWindow() {
		return window;
	}

	public void setWindow(JFrame window) {
		this.window = window;
	}
	
}
