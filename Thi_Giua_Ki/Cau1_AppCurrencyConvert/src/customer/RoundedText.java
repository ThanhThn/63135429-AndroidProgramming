package customer;
import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

import java.awt.*;
public class RoundedText extends JTextField{
    private boolean over;
    private Color fill;
    private Color line;
    private int strokeWidth;

    public RoundedText() {
        strokeWidth = 2;
        fill = new Color(255, 255, 255);
        line = new Color(75, 30, 213);
        setOpaque(false);
        setBorder(null);
        setBackground(fill);
        setForeground(new Color(75, 30, 213));
    }


    public int getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth(int strokeWidth) {
        this.strokeWidth = strokeWidth;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        if (!isOpaque()) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int s = strokeWidth;
            int w = getWidth() - (2 * s);
            int h = getHeight() - (2 * s);
            g2d.setColor(fill);
            g2d.fillRoundRect(s, s, w, h, h, h);
            g2d.setStroke(new BasicStroke(s));
            g2d.setColor(line);
            g2d.drawRoundRect(s, s, w, h, h, h);
        }
        super.paintComponent(g);
    }

}
