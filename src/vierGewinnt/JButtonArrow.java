package vierGewinnt;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import javax.swing.JButton;
import javax.swing.border.Border;

public class JButtonArrow extends JButton {
	private ArrowBorder border;
	public JButtonArrow() {
		super();
		setContentAreaFilled(false);
		init();
	}
	private void init(){
		border = new ArrowBorder();
		setBorder(border);
		setBackground(Color.white);
	}
	protected void paintComponent(Graphics g) {
		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(getBackground());
		int width = getWidth();
		int height = getHeight();
		int[] xs = {width/3, 2, width/2, width-3, width*2/3, width*2/3, width/3, width/3};
        int[] ys = {height/2, height/2, height-2, height/2, height/2, 1, 1, height/2};
        super.paintComponent(g);
		g.fillPolygon(xs, ys, 8);
	}
	private static class ArrowBorder implements Border {
		private Color color;
	    ArrowBorder() {
	        this.color = Color.black;
	    }
	    public Insets getBorderInsets(Component c) {
	        return new Insets(0, 0, 0, 0);
	    }
	    public boolean isBorderOpaque() {
	        return true;
	    }
	    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
			((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    	g.setColor(color);
	    	int[] xs = {x+(width/3), x+2, x+(width/2), x+width-3, x+(width*2/3), x+(width*2/3), x+(width/3), x+(width/3)};
	        int[] ys = {y+(height/2), y+(height/2), y+height-2, y+(height/2), y+(height/2), y+1, y+1, y+(height/2)};
	        ((Graphics2D) g).setStroke(new BasicStroke(3));
	        g.drawPolyline(xs, ys, 8);
	    }
	}
}