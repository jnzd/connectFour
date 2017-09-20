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
	private int[][] fieldState = new int [6][7];
	private JButton[] selectors = new JButton[7];
	private JButton easy, medium, hard = new JButton();
	private JButton[] difficultyButtons = new JButton[3];
	private JButton restart;
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
	private Color inactive = Color.lightGray;
	public Main(){
		super("connectFour");
		difficulty = new JPanel();
		connectFour = new JPanel();
		difficulty.setLayout(null);
		connectFour.setLayout(null);
		/**
		 *  difficulty menu
		 */
		easy = new JButton();
		easy.setText("leicht");
		easy.setBackground(difficultyBackground);
		easy.setSize(difficultyButtonWidth, difficultyButtonHeight);
		easy.setLocation((windowWidth/2)-(difficultyButtonWidth/2), (130-(difficultyButtonHeight/2)));
		easy.addActionListener(this);
		difficulty.add(easy);
		medium = new JButton();
		medium.setText("mittel");
		medium.setBackground(difficultyBackground);
		medium.setSize(difficultyButtonWidth, difficultyButtonHeight);
		medium.setLocation((windowWidth/2)-(difficultyButtonWidth/2), ((windowHeight-50)/3)*1-(difficultyButtonHeight/2)+130);
		medium.addActionListener(this);
		difficulty.add(medium);	
		hard = new JButton();
		hard.setText("schwer");
		hard.setBackground(difficultyBackground);
		hard.setSize(difficultyButtonWidth, difficultyButtonHeight);
		hard.setLocation((windowWidth/2)-(difficultyButtonWidth/2), ((windowHeight-50)/3)*2-(difficultyButtonHeight/2)+130);
		hard.addActionListener(this);
		difficulty.add(hard);
		/**
		 * playing field
		 * selector buttons
		 */
		for (int i=0; i<7; i++){
			selectors[i] = new JButton();
			selectors[i].setSize(buttonSize, buttonSize);
			selectors[i].setLocation(110+(buttonSize+30)*i,60);
			selectors[i].addActionListener(this);
			selectors[i].setBackground(selectorColor);
			connectFour.add(selectors[i]);
		}
		/**
		 * slots
		 */
		for(int i=0; i<6; i++){
			for(int j=0; j<7; j++){
				fieldState[i][j] = 0;
				field[i][j] = new JLabel();
				field[i][j].setSize(fieldSize,fieldSize);
				field[i][j].setLocation((fieldSize+10)*j+100, (fieldSize+10)*(5-i)+175);
				field[i][j].setBackground(empty);
				field[i][j].setBorder(new LineBorder (Color.darkGray));
				field[i][j].setText(i+"+"+j);
				connectFour.add(field[i][j]);
			}
		}
		/*
		 * restart
		 */
		restart = new JButton();
		restart.setText("restart");
		restart.setBackground(difficultyBackground);
		restart.setSize(difficultyButtonWidth/2, difficultyButtonHeight/2);
		restart.setLocation((windowWidth/2)-(difficultyButtonWidth/4), windowHeight-100);
		restart.addActionListener(this);
		connectFour.add(restart);
		
		setContentPane(difficulty);
		//setContentPane(connectFour);
	}
	public void actionPerformed(ActionEvent Click){
		Object Source = Click.getSource();
		if(Source == easy){
			start("easy");
		}
		if(Source == medium){
			start("medium");	
		}
		if(Source == hard){
			start("hard");
		}
		if(Source == restart){
			restart();
		}
		for(int i=0; i<7; i++){
			if(Source == selectors[i]){
				for(int j=5; j>=0; j--){
					if(j>0 && (fieldState[j][i]-1==1||fieldState[j][i]-1==2) && fieldState[j][i]==0){
						fieldState[j][i] = 1;
						field[j][i].setBackground(player);
						if(j==5){
							selectors[i].setBackground(inactive);
							selectors[i].setEnabled(false);
						}
					}else if(j==0 && fieldState[j][i]==0){
						fieldState[j][i] = 1;
						field[j][i].setBackground(player);
					}
				}
			}
		}
	}
	public void start(String difficulty){
		this.setContentPane(connectFour);
		connectFour.revalidate();
		connectFour.repaint();
	}
	public void restart(){
		this.setContentPane(difficulty);
		difficulty.revalidate();
		difficulty.repaint();		
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
		window.setLocationRelativeTo(null); //centers window on screen
		window.setResizable(false);
	}
}
