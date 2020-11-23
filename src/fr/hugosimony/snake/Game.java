package fr.hugosimony.snake;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.hugosimony.snake.Bouton;

public class Game extends JFrame{

	private static final long serialVersionUID = 1L;
	private static Color fond = new Color(0,255,0);
	private static Color fond_principal = new Color(150,255,150);
	private static Color rouge = new Color(255,0,0);
	private static int GAUCHE = 1;
	public int x_memoire = 11;
	public int y_memoire = 11;
	public int x_pomme;
	public int y_pomme;
	public int deplacement = GAUCHE;
	public int deplacement_memoire = GAUCHE;
	public int longeur_snake = 3;
	public int difficulty;
	public JLabel label_score = new JLabel();
	public String score_string = "0";
	public int meilleur_score = 0;
	public int score = 0;
	public int compteur_partie;
	public int score_moyen;
	public int score_total;
	public boolean pomme_trouve = false;
	Game game = this;
	public boolean pause = false;
	public boolean stop = false;
	public boolean IA = false;
	Bouton[][] tableau_bouton;
	Font font1 = new Font("Arial", Font.BOLD, 40);
	Font font2 = new Font("Arial", Font.BOLD, 15);
	Font font3 = new Font("Arial", Font.BOLD, 25);
	public int tableau_snake[][] = {
			{1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,},
			{1000,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1000,},
			{1000,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1000,},
			{1000,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1000,},
			{1000,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1000,},
			{1000,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1000,},
			{1000,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1000,},
			{1000,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1000,},
			{1000,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1000,},
			{1000,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1000,},
			{1000,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1000,},
			{1000,0,0,0,0,0,0,0,0,0,0,1,2,3,0,0,0,0,0,0,0,0,0,0,1000,},
			{1000,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1000,},
			{1000,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1000,},
			{1000,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1000,},
			{1000,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1000,},
			{1000,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1000,},
			{1000,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1000,},
			{1000,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1000,},
			{1000,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1000,},
			{1000,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1000,},
			{1000,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1000,},
			{1000,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1000,},
			{1000,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1000,},
			{1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,},
		 };
	
	public Game(int difficulty, boolean IA, int meilleur_score, int compteur_partie, int score_total){
		
		this.compteur_partie = compteur_partie;
		this.difficulty = difficulty;
		this.IA = IA;
		this.meilleur_score = meilleur_score;
		this.score_total = score_total;
		
		this.setTitle("Snake [Difficulté " + difficulty + "]     -----    Made by Hugo Simony-Jungo");
		this.setSize(500, 600);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
	    	public void windowClosed(WindowEvent e) {
	    		if(isVisible()){
	    			System.exit(0);
	    		}
	    	}
		});
		
		if(compteur_partie == 1) {
			score_moyen = 0;
		}else 
			score_moyen = score_total/(compteur_partie-1);
		
		JPanel panel_principal = new JPanel();
		panel_principal.setLayout(new BorderLayout());
		
		if(IA) {
			label_score.setText("Score : " + score + " --- Meilleur score : " + meilleur_score + " --- Score Moyen : " + score_moyen);
			label_score.setFont(font2);
		}else {
			label_score.setText("Score : " + score);
			label_score.setFont(font1);
		}
		label_score.setHorizontalAlignment(JLabel.CENTER);
		label_score.setVerticalAlignment(JLabel.CENTER);
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(26,25));
		Font font_bouton = new Font("Arial", Font.BOLD, 40);
		
		tableau_bouton = new Bouton[25][25];
		
		panel_principal.add("North", label_score);
		
		for(int i=0;i<25;i++) { 	
			for(int j=0;j<25;j++) {
			
				Bouton bouton = new Bouton(i,j,this);
				bouton.setBorder(null);
				bouton.setFont(font_bouton);
				panel.add(bouton);
				tableau_bouton[i][j] = bouton;
				
			}
		}
		
		randomPomme();

		panel.setBackground(fond_principal);
		panel_principal.add("Center", panel);
		
		JLabel label_copyright = new JLabel();
		if(IA) {
			label_copyright.setText("L'intelligence Artificielle joue sa partie n°" + compteur_partie);
			label_copyright.setFont(font2);
		}else {	
			label_copyright.setText("Utilisez les flêches pour vous déplacer. Faîtes pause avec espace");
			label_copyright.setFont(font2);
		}
		label_copyright.setHorizontalAlignment(JLabel.CENTER);
		label_copyright.setVerticalAlignment(JLabel.CENTER);
		
		panel_principal.add("South", label_copyright);
		
		panel_principal.setBackground(fond_principal);
		this.add(panel_principal);
		
		StartTimer start_timer = new StartTimer(difficulty, this, compteur_partie, score_total);
		start_timer.start();
		
	}
	
	public void randomPomme(){
		int x_alea = 1 + (int)(Math.random() * 23);
		int y_alea = 1 + (int)(Math.random() * 23);
		if(tableau_snake[x_alea][y_alea] != 0) {
			randomPomme();
		}else {
			tableau_bouton[x_alea][y_alea].setBackground(rouge);
			tableau_bouton[x_alea][y_alea].setForeground(rouge);
			tableau_bouton[x_alea][y_alea].setText("5");
			pomme_trouve = true;
		}
	}
	
	public void trouverPomme(){
		for(int i=0;i<25;i++) {	
			for(int j=0;j<25;j++) {
				if(tableau_bouton[i][j].getBackground() == rouge) {
					x_pomme = i;
					y_pomme = j;
					pomme_trouve = true;
				}
			}
		}
		if(!pomme_trouve)
			randomPomme();
	}
	
	
	public void allongerSnake(Bouton ce_bouton) {
		longeur_snake++;
		pomme_trouve = false;
		score = score + difficulty;
		if(score>meilleur_score)
			meilleur_score = score;
		if(IA) {
			label_score.setText("Score : " + score + " --- Meilleur score : " + meilleur_score + " --- Score Moyen : " + score_moyen);
		}else
			label_score.setText("Score : " + score);
		ce_bouton.setBackground(fond);
		ce_bouton.setForeground(fond);
		ce_bouton.setText("");
		randomPomme();
	}
}
