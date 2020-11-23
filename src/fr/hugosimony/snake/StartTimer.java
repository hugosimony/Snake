package fr.hugosimony.snake;

import java.util.Timer;

public class StartTimer {
	
	int difficulty;
	int compteur_partie;
	int score_total;
	Game game;
	
	public StartTimer(int difficulty, Game game, int compteur_partie, int score_total) {
		this.difficulty = difficulty;
		this.game = game;
		this.compteur_partie = compteur_partie;
		this.score_total = score_total;
	}

	public void start() {
		Timer timer;
		timer = new Timer();
		timer.schedule(new Deplacement(game, difficulty, compteur_partie, score_total), 700, 150/difficulty);
	}
	
}
