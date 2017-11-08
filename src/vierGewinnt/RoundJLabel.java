package vierGewinnt;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import javax.swing.JLabel;
import javax.swing.border.Border;

public class RoundJLabel extends JLabel {
	private RoundBorder border;
	public RoundJLabel() {
		super();
		init();
	}
	private void init(){
		border = new RoundBorder();
		setBorder(border);
		setBackground(Color.white);
	}
	@Override
	protected void paintComponent(Graphics g) {
		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(getBackground());
		int width = getWidth();
		int height = getHeight();
        super.paintComponent(g);
		g.fillOval(1, 1, width-3, height-3);
	}
	private static class RoundBorder implements Border {
		private Color color;
	    RoundBorder() {
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
	        ((Graphics2D) g).setStroke(new BasicStroke(3));
	        g.drawOval(x+1, y+1, width-3, height-3);
	    }
	}
}