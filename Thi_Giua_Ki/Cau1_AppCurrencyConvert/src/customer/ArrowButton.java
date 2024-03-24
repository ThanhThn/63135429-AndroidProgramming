package customer;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ArrowButton extends JButton{

    private boolean over;
    private Color fill;
    ImageIcon icon = new ImageIcon(getClass().getResource("chevron-down.png"));

    public ArrowButton() {
        fill = new Color(255, 255,255, 0);
        setOpaque(false);
        setBorder(null);
        setFocusPainted(false);
        setContentAreaFilled(false);
        setBackground(fill);
        setForeground(Color.white);
    }
    
    
    @Override
    protected void paintComponent(Graphics g) {
        if (!isOpaque()) {
            Graphics2D g2d = (Graphics2D) g;
            icon.paintIcon(this, g2d, this.getWidth() / 2 - icon.getIconWidth() / 2 , this.getHeight() / 2 - icon.getIconHeight() / 2 );
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(fill);
        }
        super.paintComponent(g);
    }

}
