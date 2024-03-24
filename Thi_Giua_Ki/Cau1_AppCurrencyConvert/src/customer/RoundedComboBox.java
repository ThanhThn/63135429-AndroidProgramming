package customer;


import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

public class RoundedComboBox<E> extends JComboBox<E> {
    private Color fill;
    private int strokeWidth;
    private String labeText = "VND";
    private Color lineColor = new Color(3, 155, 216);
    private boolean mouseOver;
    public String getLabeText() {
        return labeText;
    }

    public void setLabeText(String labeText) {
        this.labeText = labeText;
    }

    public Color getLineColor() {
        return lineColor;
    }

    public void setLineColor(Color lineColor) {
        this.lineColor = lineColor;
    }

    public RoundedComboBox() {
        strokeWidth = 4;
        fill = new Color(255, 255, 255);
        setOpaque(false);
        setBorder(null);
        setBackground(fill);
        setForeground(new Color(75, 30, 213));
        setUI(new ComboBoxUI(this));
        setRenderer(new ComboBoxRenderer());
    }

    private class ComboBoxUI extends BasicComboBoxUI {
    	
    	
        private boolean animateHinText = true;
        private float location;
        private boolean show;
        private RoundedComboBox combo;

        public ComboBoxUI(RoundedComboBox combo) {
            this.combo = combo;
        }
    	
        @Override
        protected JButton createArrowButton() {
            return new ArrowButton();
        }

        @Override
        public void paint(Graphics g, JComponent c) {
            super.paint(g, c);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
            int s = strokeWidth;
            int w = getWidth() - (2 * s);
            int h = getHeight() - (2 * s);
            RoundRectangle2D background = new RoundRectangle2D.Float(s, s, w, h, 40, 40);
            g2.setPaint(fill);
            g2.fill(background);
            g2.setStroke(new BasicStroke(s));
            g2.setColor(new Color(75, 30, 213));
            g2.draw(background);
            createHintText(g2);
            g2.dispose();
        }
        
        private void createHintText(Graphics2D g2) {
            Insets in = getInsets();
            g2.setColor(new Color(150, 150, 150));
            FontMetrics ft = g2.getFontMetrics();
            Rectangle2D r2 = ft.getStringBounds(combo.getLabeText(), g2);
            double height = getHeight() - in.top - in.bottom;
            double textY = (height - r2.getHeight()) / 2;
            double size;
            if (animateHinText) {
                if (show) {
                    size = 18 * (1 - location);
                } else {
                    size = 18 * location;
                }
            } else {
                size = 18;
            }
            g2.drawString(combo.getLabeText(), 26, (int) (in.top + textY + ft.getAscent() - size));
        }
        

    }

    private class ComboBoxRenderer extends DefaultListCellRenderer {
        public ComboBoxRenderer() {
            setOpaque(false);
            setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
            setBackground(Color.BLUE);
            setForeground(Color.YELLOW);
        }

        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value,
                                                      int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (isSelected) {
                setBackground(list.getSelectionBackground());
                setForeground(list.getSelectionForeground());
            } else {
                setBackground(list.getBackground());
                setForeground(list.getForeground());
            }
            return this;
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);

            // Adjust the position of the text
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.translate(5, 5);
        }
    }
}