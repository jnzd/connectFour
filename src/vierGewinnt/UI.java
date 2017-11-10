package vierGewinnt;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class UI extends JFrame  implements ActionListener{
	static RoundJLabel[][] field = new RoundJLabel[6][7];
	static int[][] fieldState = new int [6][7];
	static JButton[] selectors = new JButton[7];
	static JButton easy;
	static JButton medium;
	static JButton hard = new JButton();
	static JButton restart;
	private int fieldSize = 100;
	private int buttonSize = 80;
	private int difficultyButtonWidth = 500;
	private int difficultyButtonHeight = 100;
	static JPanel connectFour;
	static JPanel difficulty;
	static Color player = Color.red;
	static Color computer = Color.yellow;
	static Color selectorColor = Color.orange;
	static Color empty = Color.gray;
	private Color difficultyBackground = Color.gray;
	
	public UI(){
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
		easy.setLocation((Main.windowWidth/2)-(difficultyButtonWidth/2), (130-(difficultyButtonHeight/2)));
		easy.addActionListener(this);
		difficulty.add(easy);
		medium = new JButton();
		medium.setText("mittel");
		medium.setBackground(difficultyBackground);
		medium.setSize(difficultyButtonWidth, difficultyButtonHeight);
		medium.setLocation((Main.windowWidth/2)-(difficultyButtonWidth/2), ((Main.windowHeight-50)/3)*1-(difficultyButtonHeight/2)+130);
		medium.addActionListener(this);
		difficulty.add(medium);	
		hard = new JButton();
		hard.setText("schwer");
		hard.setBackground(difficultyBackground);
		hard.setSize(difficultyButtonWidth, difficultyButtonHeight);
		hard.setLocation((Main.windowWidth/2)-(difficultyButtonWidth/2), ((Main.windowHeight-50)/3)*2-(difficultyButtonHeight/2)+130);
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
				field[i][j] = new RoundJLabel();
				field[i][j].setSize(fieldSize,fieldSize);
				field[i][j].setLocation((fieldSize+10)*j+100, (fieldSize+10)*(5-i)+175);
				field[i][j].setOpaque(true);
				field[i][j].setBackground(empty);
				field[i][j].setBorder(new LineBorder (Color.darkGray));
				connectFour.add(field[i][j]);
			}
		}
		/**
		 * restart
		 */
		restart = new JButton();
		restart.setText("restart");
		restart.setBackground(difficultyBackground);
		restart.setSize(difficultyButtonWidth/2, difficultyButtonHeight/2);
		restart.setLocation((Main.windowWidth/2)-(difficultyButtonWidth/4), Main.windowHeight-100);
		restart.addActionListener(this);
		connectFour.add(restart);
		setContentPane(difficulty);
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
				Utility.playerTurn(i);
				Utility.computerTurn(Utility.level);
			}
		}
	}
/******************************************************************************************************/
	public void start(int difficulty){
		Utility.level = difficulty;
		this.setContentPane(connectFour);
		connectFour.revalidate();
		connectFour.repaint();
	}
/******************************************************************************************************/
	public void restart(){
		this.setContentPane(difficulty);
		difficulty.revalidate();
		difficulty.repaint();
		for (int i=0; i<7; i++){
			selectors[i].setVisible(true);
			selectors[i].setBackground(selectorColor);
		}
		for(int i=0; i<6; i++){
			for(int j=0; j<7; j++){
				fieldState[i][j] = 0;
				field[i][j].setBackground(empty);
			}
		}
	}
}
