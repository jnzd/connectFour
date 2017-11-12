package vierGewinnt;

import java.awt.Color;

import javax.swing.JOptionPane;

public class Utility {
	static int level;
/******************************************************************************************************/
	public static void playerTurn(int i){
		int row = row(i, UI.fieldState);
		move(row,i,1);
	}
/******************************************************************************************************/
	public static void computerTurn(int difficulty){
		if(difficulty == 0){
			int column = 0;
			int row = 6;
			while(row > 5) {
				column = (int) Math.floor(Math.random()*6);
				row = row(column, UI.fieldState);
			}
			move(row,column,2);
		}else if(difficulty == 1){
			int column = Intermediate.mediumColumn(UI.fieldState);
			int row = row(column, UI.fieldState);
			move(row,column,2);			
		}else{
			int column = ConnectFourLib.computerProfiSpielzug(UI.fieldState);
			int row = row(column, UI.fieldState);
			move(row,column,2);
		}
	}
/******************************************************************************************************/
	public static int row(int column, int[][] field){
		for (int i = 0; i <=5; i++) {
			if (field[i][column] == 0) {
				return i;
			}
		}
		return 6;
	}
/******************************************************************************************************/
	public static void move(int row, int column, int who){
		Color whoColor = UI.player;
		if(who == 2){
			whoColor = UI.computer;			
		}
		UI.fieldState[row][column] = who;
		UI.field[row][column].setBackground(whoColor);
		ConnectFourLib.printSpielfeld(System.out, UI.fieldState);
		if(row>4){
			UI.selectors[column].setVisible(false);
		}
		boolean win = ConnectFourLib.gewonnen(who, UI.fieldState);
		if(win){
			if(who==1) {
				JOptionPane.showMessageDialog(UI.connectFour, "Du hast gewonnen!");
			}else{
				JOptionPane.showMessageDialog(UI.connectFour, "Der Computer hat gewonnen!");				
			}
			restart();
		}	
	}
/******************************************************************************************************/
	public static void restart(){
		for (int n=0; n<7; n++){
			UI.selectors[n].setBackground(UI.selectorColor);
		}
		for(int n=0; n<6; n++){
			for(int j=0; j<7; j++){
				UI.fieldState[n][j] = 0;
				UI.field[n][j].setBackground(UI.empty);
			}
		}
		startOptions();
	}
/******************************************************************************************************/
	public static void startOptions() {
		
		Object[] options = {"Leicht",
			                "Mittel",
			                "Schwer"};
		int n = JOptionPane.showOptionDialog(UI.connectFour,
				"Wähle die Schwierigkeitsstufe!",
				"Schwierigkeit",
				JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,
				options,
				options[0]);
		if(n==0||n==1||n==2) {
			Utility.level = n;
		}
	}
}
