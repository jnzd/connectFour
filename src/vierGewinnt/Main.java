package vierGewinnt;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class Main extends JFrame implements ActionListener{
	private JLabel[][] field = new JLabel[6][7];
	private static int[][] fieldState = new int [6][7];
	private static int[] rows = new int[7];
	private int level;
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
			selectors[i].setVisible(true);
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
				connectFour.add(field[i][j]);
			}
		}
		for(int i=0; i<7; i++){
			rows[i] = 0;
		}
		/**
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
			start(0);
		}
		if(Source == medium){
			start(1);	
		}
		if(Source == hard){
			start(2);
		}
		if(Source == restart){
			restart();
		}
		for(int i=0; i<7; i++){
			if(Source == selectors[i]){
				playerTurn(i);
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				computerTurn(level);
			}
		}
	}
	public void start(int difficulty){
		level = difficulty;
		this.setContentPane(connectFour);
		connectFour.revalidate();
		connectFour.repaint();
	}
	public void restart(){
		this.setContentPane(difficulty);
		difficulty.revalidate();
		difficulty.repaint();		
	}
	public void playerTurn(int i){
		int row = row(i);
		move(row,i,1);
	}
	public void computerTurn(int difficulty){
		int computer = 2;
		if(difficulty == 0){
			int column = (int) Math.floor(Math.random()*6);
			int row = row(column);
			move(row,column,computer);
		}else if(difficulty == 1){
			int column = mediumColumn(fieldState);
			int row = row(column);
			move(row,column,computer);			
		}else{
			int column = ConnectFourLib.computerProfiSpielzug(fieldState);
			int row = row(column);
			move(row,column,computer);
		}
	}
	public int row(int column){
		int row = rows[column];
		rows[column] += 1;
		return row;		
	}
	public void move(int row, int column, int who){
		fieldState[row][column] = who;
		field[row][column].setBackground(player);
		ConnectFourLib.printSpielfeld(System.out, fieldState);
		if(row==5){
			selectors[column].setVisible(false);
			selectors[column].setEnabled(false);
		}
		boolean gewonnen = ConnectFourLib.gewonnen(who, fieldState);
		if(gewonnen){
			System.out.println(who + " hat gewonnen");
		}	
	}
	public int mediumColumn(int[][] fieldState){
		int column = 0;
		return column;
	}
	public static void main(String[] args){
		int spielzug = ConnectFourLib.computerProfiSpielzug(fieldState);
		fieldState[5][spielzug] = 2;
		ConnectFourLib.printSpielfeld(System.out, fieldState);
		boolean gewonnen = ConnectFourLib.gewonnen(2, fieldState);
		Main window = new Main();
		window.setSize(windowWidth,windowHeight);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		window.setVisible(true);
		window.setLocationRelativeTo(null); //centers window on screen
		window.setResizable(false);
	}
}
