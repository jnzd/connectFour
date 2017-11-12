package vierGewinnt;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class UI extends JFrame{
	
	//initialize variables
	static JLabelRound[][] field = new JLabelRound[6][7]; // graphical field
	static int[][] fieldState = new int [6][7]; // field state in integers
	static JButton[] selectors = new JButton[7];
	static JButton[] difficultyButtons = new JButton[3];
	static JButton restart;
	static JLabel background = new JLabel();
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
	static Color difficultyBackground = Color.gray;
	static Color backgroundColor = Color.decode("#1466FF");
	
	public UI(){
		super("connectFour");
		connectFour = new JPanel();
		connectFour.setLayout(null);
		// setup difficulty menu
		for(int i=0; i<3; i++) {
			difficultyButtons[i]= new JButton();
			difficultyButtons[i].setBackground(difficultyBackground);
			difficultyButtons[i].setSize(difficultyButtonWidth, difficultyButtonHeight);
			difficultyButtons[i].setLocation((Main.windowWidth/2)-(difficultyButtonWidth/2), ((Main.windowHeight-50)/3)*i-(difficultyButtonHeight/2)+130);
			difficultyButtons[i].addActionListener(new TheActionListener());
			connectFour.add(difficultyButtons[i]);
		}
		difficultyButtons[0].setText("leicht");
		difficultyButtons[1].setText("mittel");
		difficultyButtons[2].setText("schwer");
		// setup selector buttons for columns
		for (int i=0; i<7; i++){
			selectors[i] = new JButton();
			selectors[i].setVisible(false);
			selectors[i].setSize(buttonSize, buttonSize);
			selectors[i].setLocation(110+(buttonSize+30)*i,60);
			selectors[i].addActionListener(new TheActionListener());
			selectors[i].setBackground(selectorColor);
			connectFour.add(selectors[i]);
		}
		// setup slots for playing field
		for(int i=0; i<6; i++){
			for(int j=0; j<7; j++){
				fieldState[i][j] = 0;
				field[i][j] = new JLabelRound();
				field[i][j].setSize(fieldSize,fieldSize);
				field[i][j].setLocation((fieldSize+10)*j+100, (fieldSize+10)*(5-i)+175);
				//field[i][j].setOpaque(true);
				field[i][j].setBackground(empty);
				//field[i][j].setBorder(new LineBorder (Color.darkGray));
				field[i][j].setVisible(false);
				connectFour.add(field[i][j]);
			}
		}
		// setup restart button
		restart = new JButton();
		restart.setText("restart");
		restart.setBackground(difficultyBackground);
		restart.setSize(difficultyButtonWidth/2, difficultyButtonHeight/2);
		restart.setLocation((Main.windowWidth/2)-(difficultyButtonWidth/4), Main.windowHeight-100);
		restart.addActionListener(new TheActionListener());
		restart.setVisible(false);
		connectFour.add(restart);
		// background for slots
		background.setSize(800, 690);
		background.setLocation(80, 155);
		background.setOpaque(true);
		background.setBackground(backgroundColor);
		background.setBorder(new LineBorder(Color.black, 3));
		background.setVisible(false);
		connectFour.add(background);
		
		setContentPane(connectFour);
	}
}