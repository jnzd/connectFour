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
	private JButton restart, setDifficultyButton;
	private int fieldSize = 100;
	private int buttonSize = 80;
	private int difficultyButtonWidth = 500;
	private int difficultyButtonHeight = 100;
	private static int windowWidth = 1000;
	private static int windowHeight = 1000;
	private JPanel connectFour, difficulty;
	
	private Color selectorColor = Color.orange;
	private Color empty = Color.gray;
	private Color player = Color.red;
	private Color computer = Color.yellow;
	private Color difficultyBackground = Color.gray;
	public Main(){
		super("connectFour"); //Fenstername
		JPanel connectFour = new JPanel();
		connectFour.setLayout(null);
		JPanel difficulty = new JPanel();
		difficulty.setLayout(null);
		
		/*** playing field ***/
		for (int i=0; i<7; i++){
			selectors[i] = new JButton();
			selectors[i].setSize(buttonSize, buttonSize);
			selectors[i].setLocation(100+(buttonSize+30)*i,50);
			selectors[i].addActionListener(this);
			selectors[i].setBackground(selectorColor);
			connectFour.add(selectors[i]);
		}
		for(int i=0; i<6; i++){
			for(int j=0; j<7; j++){
				field[i][j] = new JLabel();
				field[i][j].setSize(fieldSize,fieldSize);
				field[i][j].setLocation((fieldSize+10)*j+100, (fieldSize+10)*(5-i)+175);
				field[i][j].setBackground(empty);
				field[i][j].setBorder(new LineBorder (Color.darkGray));
				field[i][j].setText(i+"+"+j);
				connectFour.add(field[i][j]);
			}
		}
		/*** difficulty menu ***/
		for(int i=0; i<3;i++){
			difficultyButtons[i] = new JButton();
			difficultyButtons[i].setBackground(difficultyBackground);
			difficultyButtons[i].setSize(difficultyButtonWidth, difficultyButtonHeight);
			difficultyButtons[i].setLocation((windowWidth/2)-(difficultyButtonWidth/2), ((windowHeight-50)/3)*(i)-(difficultyButtonHeight/2)+130);
			difficultyButtons[i].addActionListener(this);
			difficulty.add(difficultyButtons[i]);			
		}
		difficultyButtons[0].setText("leicht");;
		difficultyButtons[1].setText("mittel");
		difficultyButtons[2].setText("schwer");
		setContentPane(difficulty);
		//setContentPane(connectFour);
	}
	public void actionPerformed(ActionEvent click){
		Object source = click.getSource();
		for(int i=0; i<3; i++){
			if(source == difficultyButtons[i]){
				//setDifficulty(i);
				this.setContentPane(connectFour);
				connectFour.revalidate();
				connectFour.repaint();
			}
		}
	}
	public void setDifficulty(int difficulty){
		
	}
	public static void main(String[] args){
		int[][] feld = new int[6][7];
		int spielzug = ConnectFourLib.computerProfiSpielzug(feld);
		feld[5][spielzug] = 2;
		ConnectFourLib.printSpielfeld(System.out, feld);
		boolean gewonnen = ConnectFourLib.gewonnen(2, feld);
		
		Main window = new Main();
		window.setSize(windowWidth,windowHeight);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		window.setVisible(true);
		window.setLocationRelativeTo(null); //ceneters window on screen
		window.setResizable(false);
	}
}
