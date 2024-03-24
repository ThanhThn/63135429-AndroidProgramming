package customer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

public class IconButton extends JButton{

    private boolean over;
    private Color fill;
    ImageIcon icon;

    public IconButton(URL location) {
        fill = new Color(255, 255,255, 0);
        icon = new ImageIcon(location);
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
            icon.paintIcon(this, g2d, this.getWidth() / 2 - icon.getIconWidth() / 2 , this.getHeight() / 2 - icon.getIconHeight() / 2);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(fill);
        }
        super.paintComponent(g);
    }

}
