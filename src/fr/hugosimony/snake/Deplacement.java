package fr.hugosimony.snake;

import java.awt.Color;
import java.util.TimerTask;

public class Deplacement extends TimerTask{

	private static Color gris = new Color(100,100,100);
	private static Color gris_clair = new Color(125,125,125);
	private static Color fond = new Color(0,255,0);
	private static int GAUCHE = 1;
	private static int DROITE = 3;
	private static int BAS = 2;
	private static int HAUT = 4;
	public int nouv_x;
	public int nouv_y;
	public int deplacement = 1;
	public int deplacement_memoire = 1;
	public int deplacement_memoire_memoire = 1;
	public int deplacement_memoire_memoire_memoire = 1;
	public int difficulty;
	public int compteur_partie;
	public int score_total;
	public int meilleur_score;
	public int compteur;
	public Bouton ce_bouton;
	public Bouton ancien_bouton;
	Game game;
	
	public Deplacement(Game game, int difficulty, int compteur_partie, int score_total) {
		this.game = game;
		this.difficulty = difficulty;
		this.compteur_partie = compteur_partie;
		this.score_total = score_total;
	}
	
	@Override
	public void run() {
		compteur++;
		if(compteur == 5000) {
			meilleur_score = game.meilleur_score;
			compteur_partie++;
			Game game = new Game(difficulty, true, meilleur_score, compteur_partie, score_total);
			game.setVisible(true);
			System.out.println("Sûrement un bug");
		}
		if(game.stop)
			this.cancel();
		if(game.pause) {
			//Do nothing
		}else {
			nouv_x = game.x_memoire;
			nouv_y = game.y_memoire;
			if(game.deplacement == GAUCHE) 
				nouv_y--;
			if(game.deplacement == DROITE)
				nouv_y++;
			if(game.deplacement == BAS) 
				nouv_x++;
			if(game.deplacement == HAUT) 
				nouv_x--;
			if(game.deplacement == 0) 
				System.out.println("NULL");
			ancien_bouton = game.tableau_bouton[game.x_memoire][game.y_memoire];
			ce_bouton = game.tableau_bouton[nouv_x][nouv_y];
			if(ce_bouton.getText() == "1" || ce_bouton.getText() == "0") {
				this.cancel();
				game.dispose();
				if(game.IA) {
					meilleur_score = game.meilleur_score;
					score_total = score_total + game.score;
					compteur_partie++;
					Game game = new Game(difficulty, true, meilleur_score, compteur_partie, score_total);
					game.setVisible(true);
				}else {
					Fin fin = new Fin(game.score);
					fin.setVisible(true);
				}
			}else {	
				if(ce_bouton.getText() == "5")
					game.allongerSnake(ce_bouton);
				ce_bouton.setBackground(gris);
				ce_bouton.setForeground(gris);
				ce_bouton.setText("h");
				placerSnake(nouv_x,nouv_y);
			}
			
			for(int i2=0;i2<25;i2++) {	
				for(int j2=0;j2<25;j2++) {
					if(game.tableau_bouton[i2][j2].getBackground() == gris) {
						game.x_memoire = i2;
						game.y_memoire = j2;
					}
				}
			}
			
			if(game.IA) {
				game.pomme_trouve = false;
				game.trouverPomme();
				if(game.x_memoire == game.x_pomme) { 
					if(game.y_memoire - game.y_pomme > 0) {
						if(deplacement_memoire == DROITE) {
							if(verifHaut())
								deplacement = HAUT;
							if(verifBas()) 
								deplacement = BAS;
						}else {
							if(verifGauche()) {
								deplacement = GAUCHE;
							}else {
								if(game.x_memoire - game.x_pomme < 0) {
									if(verifHaut() && deplacement_memoire != BAS) {
										deplacement = HAUT;
									}else {
										if(verifDroite())
											deplacement = DROITE;
										if(verifBas() && deplacement_memoire != HAUT) 
											deplacement = BAS;
									}
								}
								if(game.x_memoire - game.x_pomme > 0) {
									if(verifBas() && deplacement_memoire != HAUT) {
										deplacement = BAS;
									}else {
										if(verifDroite())
											deplacement = DROITE;
										if(verifHaut() && deplacement_memoire != BAS) 
											deplacement = HAUT;
									}
								}
							}
						}
					}
					if(game.y_memoire - game.y_pomme < 0) {
						if(deplacement_memoire == GAUCHE) {
							if(verifHaut())
								deplacement = HAUT;
							if(verifBas()) 
								deplacement = BAS;
						}else {
							if(verifDroite()) {
								deplacement = DROITE;
							}else {
								if(game.x_memoire - game.x_pomme < 0) {
									if(verifHaut() && deplacement_memoire != BAS) {
										deplacement = HAUT;
									}else {
										if(verifGauche())
											deplacement = GAUCHE;
										if(verifBas() && deplacement_memoire != HAUT) 
											deplacement = BAS;
									}
								}
								if(game.x_memoire - game.x_pomme > 0) {
									if(verifBas() && deplacement_memoire != HAUT) {
										deplacement = BAS;
									}else {
										if(verifGauche())
											deplacement = GAUCHE;
										if(verifHaut() && deplacement_memoire != BAS) 
											deplacement = HAUT;
									}
								}
							}
						}
					}
				}
				if(game.x_memoire != game.x_pomme) {
					if(game.x_memoire - game.x_pomme > 0) {
						if(deplacement_memoire == BAS) {
							if(verifGauche())
								deplacement = GAUCHE;
							if(verifDroite()) 
								deplacement = DROITE;
						}else {
							if(verifHaut()) {
								deplacement = HAUT;
							}else {
								if(game.y_memoire - game.y_pomme < 0) {
									if(verifGauche() && deplacement_memoire != DROITE) {
										deplacement = GAUCHE;
									}else {
										if(verifBas()) 
											deplacement = BAS;
										if(verifDroite() && deplacement_memoire != GAUCHE)
											deplacement = DROITE;
									}	
								}
								if(game.y_memoire - game.y_pomme > 0) {
									if(verifDroite() && deplacement_memoire != GAUCHE) { 
										deplacement = DROITE;
									}else {
										if(verifBas()) 
											deplacement = BAS;
										if(verifGauche() && deplacement_memoire != DROITE)
											deplacement = GAUCHE;
									}	
								}
							}
						}
					}
					if(game.x_memoire - game.x_pomme < 0) {
						if(deplacement_memoire == HAUT) {
							if(verifGauche())
								deplacement = GAUCHE;
							if(verifDroite()) 
								deplacement = DROITE;
						}else {
							if(verifBas()) {
								deplacement = BAS;
							}else {
								if(game.y_memoire - game.y_pomme < 0) {
									if(verifGauche() && deplacement_memoire != DROITE) {
										deplacement = GAUCHE;
									}else {
										if(verifHaut()) 
											deplacement = HAUT;
										if(verifDroite() && deplacement_memoire != GAUCHE)
											deplacement = DROITE;
									}	
								}
								if(game.y_memoire - game.y_pomme > 0) {
									if(verifDroite() && deplacement_memoire != GAUCHE) { 
										deplacement = DROITE;
									}else {
										if(verifHaut()) 
											deplacement = HAUT;
										if(verifGauche() && deplacement_memoire != DROITE)
											deplacement = GAUCHE;
									}	
								}
							}
						}
					}
				}
				deplacement = verifGeneral();
				
				if(deplacement == deplacement_memoire || deplacement + 2 == deplacement_memoire || deplacement - 2 == deplacement_memoire) {
					game.deplacement_memoire = deplacement_memoire;
					//Do nothing
				}else {
					deplacement_memoire_memoire_memoire = deplacement_memoire_memoire;
					deplacement_memoire_memoire = deplacement_memoire;
					deplacement_memoire = deplacement;
					game.deplacement = deplacement;
				}
			}
		}
	}
	
	public void placerSnake(int x_tete, int y_tete) {
		for(int i=0;i<25;i++) {
			for(int j=0;j<25;j++) {
				if(game.tableau_snake[i][j] == game.longeur_snake) {
					game.tableau_snake[i][j] = 0;
					game.tableau_bouton[i][j].setBackground(fond);
					game.tableau_bouton[i][j].setForeground(fond);
					game.tableau_bouton[i][j].setText("");
				}
				if(game.tableau_snake[i][j] < game.longeur_snake && game.tableau_snake[i][j] != 0) {
					game.tableau_snake[i][j]++;
					game.tableau_bouton[i][j].setBackground(gris_clair);
					game.tableau_bouton[i][j].setForeground(gris_clair);
					game.tableau_bouton[i][j].setText("1");
				}
			}
		}
		game.tableau_snake[x_tete][y_tete] = 1;
	}

	public boolean verifGauche() {
		if(game.tableau_bouton[nouv_x][nouv_y - 1].getText() != "0" || game.tableau_bouton[nouv_x][nouv_y - 1].getText() != "1") {
			return true;
		}else {
			return false;
		}
	}
	public boolean verifDroite() {
		if(game.tableau_bouton[nouv_x][nouv_y + 1].getText() != "0" || game.tableau_bouton[nouv_x][nouv_y + 1].getText() != "1") {
			return true;
		}else {
			return false;
		}
	}
	public boolean verifBas() {
		if(game.tableau_bouton[nouv_x + 1][nouv_y].getText() != "0" || game.tableau_bouton[nouv_x + 1][nouv_y].getText() != "1") {
			return true;
		}else {
			return false;
		}
	}
	public boolean verifHaut() {
		if(game.tableau_bouton[nouv_x - 1][nouv_y].getText() != "0" || game.tableau_bouton[nouv_x - 1][nouv_y].getText() != "1") {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean verifTabGauche() {
		if(game.tableau_snake[game.x_memoire][game.y_memoire - 1] == 0) {
			return true;
		}else
			return false;
	}
	public boolean verifTabDroite() {
		if(game.tableau_snake[game.x_memoire][game.y_memoire + 1] == 0) {
			return true;
		}else
			return false;
	}
	public boolean verifTabBas() {
		if(game.tableau_snake[game.x_memoire + 1][game.y_memoire] == 0) {
			return true;
		}else
			return false;
	}
	public boolean verifTabHaut() {
		if(game.tableau_snake[game.x_memoire - 1][game.y_memoire] == 0) {
			return true;
		}else
			return false;
	}
	
	public int verifGeneral() {
		if(deplacement == GAUCHE) {
			if(verifTabGauche()) {
				return GAUCHE;
			}else {
				if(verifTabHaut()) {
					if(deplacement_memoire_memoire != BAS) {
						return HAUT;
					}else {
						if(verifTabBas()) {
							return BAS;
						}else {
							if(verifTabDroite())
								return DROITE;
						}
					}
				}else {
					if(verifTabBas()) {
						if(deplacement_memoire_memoire != HAUT) {
							return BAS;
						}else {
							if(verifTabHaut()){
								return HAUT;
							}else {
								if(verifTabDroite())
									return DROITE;
							}
						}
					}else {
						if(verifTabDroite())
							return DROITE;
					}
				}
			}
		}else if(deplacement == DROITE){
			if(verifTabDroite()) {
				return DROITE;
			}else {
				if(verifTabHaut()) {
					if(deplacement_memoire_memoire != BAS) {
						return HAUT;
					}else {
						if(verifTabBas()) {
							return BAS;
						}else {
							if(verifTabGauche())
								return GAUCHE;
						}
					}
				}else {
					if(verifTabBas()) {
						if(deplacement_memoire_memoire != HAUT) {
							return BAS;
						}else {
							if(verifTabHaut()) {
								return HAUT;
							}else {
								if(verifTabGauche())
									return GAUCHE;
							}
						}
					}else {
						if(verifTabGauche())
							return GAUCHE;
					}
				}
			}
		}else if(deplacement == HAUT){
			if(verifTabHaut()) {
				return HAUT;
			}else {
				if(verifTabGauche()) {
					if(deplacement_memoire_memoire != DROITE) {
						return GAUCHE;
					}else {
						if(verifTabDroite()) {
							return DROITE;
						}else {
							if(verifTabBas())
								return BAS;
						}
					}
				}else {
					if(verifTabDroite()) {
						if(deplacement_memoire_memoire != GAUCHE) {
							return DROITE;
						}else {
							if(verifTabGauche()) {
								return GAUCHE;
							}else {
								if(verifTabBas())
									return BAS;
							}
						}
					}else {
						if(verifTabBas()) 
							return BAS;
					}
				}
			}
		}else if(deplacement == BAS){
			if(verifTabBas()) {
				return BAS;
			}else {
				if(verifTabGauche()) {
					if(deplacement_memoire_memoire != DROITE) {
						return GAUCHE;
					}else {
						if(verifTabDroite()) {
							return DROITE;
						}else {
							if(verifTabHaut())
								return HAUT;
						}
					}
				}else {
					if(verifTabDroite()) {
						if(deplacement_memoire_memoire != GAUCHE) {
							return DROITE;
						}else {
							if(verifTabGauche()) {
								return GAUCHE;
							}else {
								if(verifTabHaut())
									return HAUT;
							}
						}
					}else {
						if(verifTabHaut()) 
							return HAUT;
					}
				}
			}
		}
		return GAUCHE;
	}
}
