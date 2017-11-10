package vierGewinnt;


public class Intermediate {
	public static int mediumColumn(int[][] fieldState){
		for(int i = 0; i<6; i++) {
			int row = Utility.row(i, fieldState);
			if(row<5) {
				fieldState[row][i] = 2;
				ConnectFourLib.printSpielfeld(System.out, fieldState);
				if(ConnectFourLib.gewonnen(2, fieldState)){
					fieldState[row][i] = 0;			
					return i;
				}else {
					fieldState[row][i] = 0;					
				}
			}
		}
		for(int i = 0; i<6; i++) {
			int row = Utility.row(i, fieldState);
			if(row<5) {
				fieldState[row][i] = 1;
				ConnectFourLib.printSpielfeld(System.out, fieldState);
				if(ConnectFourLib.gewonnen(1, fieldState)){
					fieldState[row][i] = 0;			
					return i;
				}else {
					fieldState[row][i] = 0;					
				}
			}
		}
		int column = 0;
		int row = 6;
		while(row > 5) {
			column = (int) Math.floor(Math.random()*6);
			row = Utility.row(column, fieldState);
		}
		return column;
	}
}
