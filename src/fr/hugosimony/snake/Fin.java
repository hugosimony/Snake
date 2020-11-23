package fr.hugosimony.snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Fin extends JFrame {

	private static final long serialVersionUID = 1L;
	private static Color fond = new Color(255,150,150);
	private static Color rouge = new Color(255,0,0);
	private static Color vert = new Color(0,255,0);
	Font font = new Font("Arial", Font.BOLD, 20);
	Fin fin = this;

	public Fin(int score){
		
		this.setTitle("Snake [Fin]");
		this.setSize(600, 250);
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
		
		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridLayout(3,1));
		
		JLabel message_score = new JLabel();
		if(score < 50) {
			message_score.setText("Vous avez perdu ! Votre score est de " + score + ".");
		}
		if(score >= 50 && score < 100) {
			message_score.setText("Vous avez perdu ! Bravo, votre score est de " + score + "!");
		}
		if(score > 100) {
			message_score.setText("Vous avez perdu ! Félicitations ! Votre score est de " + score + "!");
		}
		
		message_score.setFont(font);
		message_score.setHorizontalAlignment(JLabel.CENTER);
		
		panel1.add(message_score);
		
		JLabel question = new JLabel();
		question.setText("Voulez vous rejouer ?");
		question.setFont(font);
		question.setHorizontalAlignment(JLabel.CENTER);
		
		panel1.add(question);
		
		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridLayout(1,2));
		
		JButton oui = new JButton("Oui");
		JButton non = new JButton("Non");
		oui.setFont(font);
		non.setFont(font);
		oui.setBackground(vert);
		non.setBackground(rouge);
		oui.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fin.dispose();
				Menu menu = new Menu();
				menu.setVisible(true);
			}
		});
		non.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fin.dispose();
			}
		});
		panel2.add(oui);
		panel2.add(non);
		panel2.setBackground(fond);
		panel1.add(panel2);
		
		panel1.setBackground(fond);
		this.add(panel1);
		
	}
	
}
