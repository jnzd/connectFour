package vierGewinnt;

import javax.swing.JFrame;

public class Main extends JFrame{
	static int windowWidth = 1000;
	static int windowHeight = 1000;
/******************************************************************************************************/
	public static void main(String[] args){
		UI window = new UI();
		window.setSize(windowWidth, windowHeight);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		window.setVisible(true);
		window.setLocationRelativeTo(null); //centers window on screen
		window.setResizable(false);
	}
}
