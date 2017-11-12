package vierGewinnt;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class UI extends JFrame{
	
	//initialize variables
	static JLabelRound[][] field = new JLabelRound[6][7]; // graphical field
	static int[][] fieldState = new int [6][7]; // field state in integers
	static JButtonArrow[] selectors = new JButtonArrow[7];
	static JButton restart;
	static JLabel background = new JLabel();
	private int fieldSize = 100;
	private int buttonSize = 80;
	private int difficultyButtonWidth = 300;
	private int difficultyButtonHeight = 80;
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
		// setup selector buttons for columns
		for (int i=0; i<7; i++){
			selectors[i] = new JButtonArrow();
			selectors[i].setVisible(true);
			selectors[i].setSize(buttonSize, 3*buttonSize/4);
			selectors[i].setLocation(120+(buttonSize+30)*i,60);
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
				field[i][j].setBackground(empty);
				field[i][j].setVisible(true);
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
		restart.setVisible(true);
		connectFour.add(restart);
		// background for slots
		background.setSize(800, 690);
		background.setLocation(80, 155);
		background.setOpaque(true);
		background.setBackground(backgroundColor);
		background.setBorder(new LineBorder(Color.black, 3));
		background.setVisible(true);
		connectFour.add(background);
		
		setContentPane(connectFour);
		Utility.startOptions();
	}
}