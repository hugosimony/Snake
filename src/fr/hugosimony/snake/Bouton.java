package fr.hugosimony.snake;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Bouton extends JButton {

	private static final long serialVersionUID = 1L;
	private static Color noir = new Color(0,0,0);
	private static Color gris = new Color(100,100,100);
	private static Color gris_clair = new Color(125,125,125);
	private static Color fond = new Color(0,255,0);
	private static int GAUCHE = 1;
	private static int DROITE = 3;
	private static int BAS = 2;
	private static int HAUT = 4;
	public int x_memoire;
	public int y_memoire;
	int deplacement = 1;
	int deplacement_memoire = 1;
	Game game;
	
	
	public Bouton(int i, int j, Game game) {
		this.game = game;
		
		int tab[][] = {
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,},
				{0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,},
				{0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,},
				{0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,},
				{0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,},
				{0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,},
				{0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,},
				{0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,},
				{0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,},
				{0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,},
				{0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,},
				{0,1,1,1,1,1,1,1,1,1,1,3,2,2,1,1,1,1,1,1,1,1,1,1,0,},
				{0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,},
				{0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,},
				{0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,},
				{0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,},
				{0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,},
				{0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,},
				{0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,},
				{0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,},
				{0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,},
				{0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,},
				{0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,},
				{0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,},
			 };
		
		placernew(i,j,tab);
		
		if(game.IA) {
			this.addKeyListener(new KeyListener() {
				public void keyPressed(KeyEvent event) {
					int keyCode = event.getKeyCode();
					if (keyCode == KeyEvent.VK_A) {
						game.dispose();
						game.stop = true;
		    			System.exit(0);
					}
				}
				@Override
				public void keyReleased(KeyEvent arg0) {
					// Do nothing
				}
				@Override
				public void keyTyped(KeyEvent arg0) {
					// Do nothing
				}
			});
		}else {
			this.addKeyListener(new KeyListener() {
	
				public void keyPressed(KeyEvent event) {
					int keyCode = event.getKeyCode();
					if (keyCode == KeyEvent.VK_LEFT)
						deplacement = GAUCHE;
					if (keyCode == KeyEvent.VK_RIGHT)
						deplacement = DROITE;
					if (keyCode == KeyEvent.VK_DOWN)
						deplacement = BAS;
					if (keyCode == KeyEvent.VK_UP)
						deplacement = HAUT;
					if (keyCode == KeyEvent.VK_SPACE) {
						if(game.pause) {
							game.pause = false;
						}else {
							game.pause = true;
						}
					}
					if(deplacement == deplacement_memoire || deplacement + 2 == deplacement_memoire || deplacement - 2 == deplacement_memoire) {
						game.deplacement_memoire = deplacement_memoire;
						//Do nothing
					}else {
						deplacement_memoire = deplacement;
						game.deplacement = deplacement;
					}
				}
				@Override
				public void keyReleased(KeyEvent arg0) {
					// Do nothing
				}
				@Override
				public void keyTyped(KeyEvent arg0) {
					// Do nothing
				}
			});
		}
	}
	
	public void placernew(int i, int j, int tab[][]) {
		
		if(tab[i][j] == 0) {
			this.setText("0");
			this.setForeground(noir);
			this.setBackground(noir);
		}	
	
		if(tab[i][j] == 1) {
			this.setText("");
			this.setBackground(fond);
		}
		
		if(tab[i][j] == 2) {
			this.setText("1");
			this.setForeground(gris_clair);
			this.setBackground(gris_clair);
		}	
		
		if(tab[i][j] == 3) {
			this.setText("h");
			this.setForeground(gris);
			this.setBackground(gris);
		}	
	}
}
