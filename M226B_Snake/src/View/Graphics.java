package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import Controller.Game;
import Modell.Apple;
import Modell.Snake;

public class Graphics extends JPanel implements ActionListener{
	private Timer t = new Timer(100, this);
	public String state;
	
	private Snake s;
	private Apple a;
	private Game game;
	private String highscore = "";


	/**
	 * 
	 * @param g
	 */
	public Graphics(Game g) {
		t.start();
		state = "START";//state wurde auf Start gesetzt
		
		game = g;
		s = g.getPlayer();
		a = g.getApple();
		highscore = g.GetHighScore();
		
		//add a keyListner 
		this.addKeyListener(g);
		this.setFocusable(true);
		this.setFocusTraversalKeysEnabled(false);
	}
	
	
	/**
	 * der Hintergrund wird gemacht
	 */
	public void paintComponent(java.awt.Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setColor(Color.black);
		g2.fillRect(0, 0, Game.width * Game.dimension + 5, Game.height * Game.dimension + 5);
		/**
		 * Wenn State Start ist, sollte ein weisser Text «Drücke irgendeine Taste» abgebildet sein
		 */
		if(state == "START") {
			g2.setColor(Color.white);
			g2.drawString("Drücke irgendeine Taste", Game.width/2 * Game.dimension - 100, Game.height / 10 * Game.dimension - 20);

		}
		/**
		 * Wenn State auf Running ist, sollte der Apple rot aufgefüllt werden und der eigene Score und der Highscore sollten angezeigt werden
		 */
		else if(state == "RUNNING") {
			g2.setColor(Color.red);
			g2.fillRect(a.getX() * Game.dimension, a.getY() * Game.dimension, Game.dimension, Game.dimension);
			g2.setFont( new Font("Arial",Font.BOLD, 20));
			g2.drawString("Your Score: " + (s.getBody().size() - 3), Game.width/2 * Game.dimension - 200, Game.height  * Game.dimension - 20);
			g2.drawString("Highscore: "+ highscore,Game.width/2 * Game.dimension + 10, Game.height  * Game.dimension - 20);
			
			g2.setColor(Color.green);
			for(Rectangle r : s.getBody()) {
				g2.fill(r);
			}
			
		}
		/**
		 * , wenn nicht dann sollte nur das erreichte Score angezeigt werden.
		 */
		else {
			g2.setColor(Color.white);
			g2.drawString("Your Score: " + (s.getBody().size() - 3), Game.width/2 * Game.dimension - 40, Game.height / 2 * Game.dimension - 20);

		}

			
		}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
		game.update();
	}
	
}
