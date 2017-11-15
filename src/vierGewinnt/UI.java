package vierGewinnt;

import java.awt.Color;
import java.awt.Font;

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
	static Color player = Color.decode("#00f2ff");
	static Color computer = Color.decode("#ff9900");
	static Color selectorColor = Color.orange;
	static Color empty = Color.gray;
	static Color difficultyBackground = Color.decode("#000000");
	static Color backgroundColor = Color.decode("#000000");
	static Color JPanelbg = Color.decode("#ffffff");
	
	public UI(){
		super("connectFour");
		connectFour = new JPanel();
		connectFour.setLayout(null);
		connectFour.setBackground(JPanelbg);
		// setup selector buttons for columns
		for (int i=0; i<7; i++){
			selectors[i] = new JButtonArrow();
			selectors[i].setVisible(true);
			selectors[i].setSize(buttonSize, 3*buttonSize/4);
			selectors[i].setLocation(25+(buttonSize+30)*i,25);
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
				field[i][j].setLocation((fieldSize+10)*j+20, (fieldSize+10)*(5-i)+115);
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
		restart.setForeground(Color.white);
		restart.setFont(new Font("Arial", Font.PLAIN, 36));
		connectFour.add(restart);
		// background for slots
		background.setSize(800, 690);
		background.setLocation(-3, 95);
		background.setOpaque(true);
		background.setBackground(backgroundColor);
		background.setBorder(new LineBorder(Color.black, 3));
		background.setVisible(true);
		connectFour.add(background);
		
		setContentPane(connectFour);
		Utility.startOptions();
	}
}