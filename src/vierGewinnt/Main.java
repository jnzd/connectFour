package vierGewinnt;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class Main extends JFrame implements ActionListener{
	private JLabel[][] field = new JLabel[6][7];
	private JButton[] selectors = new JButton[7];
	private JButton[] difficultyButtons = new JButton[3];
	private JButton restart;
	private int fieldSize = 20;
	private int buttonSize = 20;
	private int distance = 5;
	private int difficultyButtonWidth = 50;
	private int difficultyButtonHeight = 10;
	private static int windowWidth = 1000;
	private static int windowHeight = 1000;
	
	private Color empty = Color.white;
	private Color player = Color.red;
	private Color computer = Color.yellow;
	public Main(){
		super("connectFour"); //Fenstername
		JPanel connectFour = new JPanel();
		connectFour.setLayout(null);
		for (int i=0; i<7; i++){
			selectors[i] = new JButton;
			selectors[i].setSize(buttonSize, buttonSize);
			selectors[i].setLocation(10,distance+(buttonSize)*i);
			selectors[i].addActionListener(this);
		}
		for(int i=0; i<6; i++){
			for(int j=7; j>0; j--){
				field[i][j].setSize(fieldSize,fieldSize);
				field[i][j].setLocation(distance+(fieldSize)*i-10-buttonSize, distance+(fieldSize)*j);
				field[i][j].setBackground(empty);
				field[i][j].setBorder(new LineBorder (Color.darkGray));
				connectFour.add(field[i][j]);
			}
		}
		JPanel difficulty = new JPanel();
		difficulty.setLayout(null);
		difficultyButtons[0].setText("leicht");;
		difficultyButtons[1].setText("mittel");
		difficultyButtons[2].setText("schwer");
		for(int i=0; i<3;i++){
			difficultyButtons[i].setSize(difficultyButtonWidth, difficultyButtonHeight);
			difficultyButtons[i].setLocation(10, difficultyButtonHeight*i);
			difficultyButtons[i].addActionListener(this);
			difficulty.add(difficultyButtons[i]);			
		}
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public static void main(String[] args){
		int[][] feld = new int[6][7];
		int spielzug = ConnectFourLib.computerProfiSpielzug(feld);
		feld[5][spielzug] = 2;
		ConnectFourLib.printSpielfeld(System.out, feld);
		boolean gewonnen = ConnectFourLib.gewonnen(2, feld);
		

		//Fenster generieren
		Main window = new Main(); //f�hrt den Konstruktor aus
		window.setSize(windowWidth,windowHeight); //setzt die Gr�sse des Fensters
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		window.setVisible(true); // macht das Fenster sichtbar
		window.setLocationRelativeTo(null); //zentriert das Fenster auf dem Bildschirm
		window.setResizable(false);
	}
}
