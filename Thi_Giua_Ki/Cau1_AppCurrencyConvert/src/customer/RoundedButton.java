package customer;

import javax.swing.*;
import java.awt.*;

public class RoundedButton extends JButton{
	private boolean over;
	private Color fill;
	private Color fillOriginal;
	private Color fillOver;
	private Color fillClick;
	private int strokeWidth;
	
	public RoundedButton (){
		fillOriginal = new Color (255, 125, 59);
		fillOver = new Color(255, 255, 255);
		fillClick = new Color(255, 154, 103);	
		fill = fillOriginal;
		strokeWidth = 2;
		setOpaque(false);
		setBorder(null);
		setBorderPainted(false);
		setBackground(fillOriginal);
		setForeground(Color.white);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		if(!isOpaque()) {
			Graphics2D g2d = (Graphics2D) g;
			
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			int s = strokeWidth;
			int w = getWidth() - (2 * s);
			int h = getHeight() - (2 * s);
			g2d.setBackground(fill);
			g2d.fillRoundRect(s, s, w, h, h, h);
		}
		super.paintComponent(g);
	}
}
