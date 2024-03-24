package customer;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;

public class RoundedComboBox<E> extends JComboBox<E>{
	  	private boolean over;
	    private Color fill;
	    private Color line;
	    private int strokeWidth;

	    public RoundedComboBox() {
	        strokeWidth = 4;
	        fill = new Color(255, 255, 255, 0);
	        line = new Color(75, 30, 213);
	        setOpaque(false);
	        setBorder(null);
	        setBackground(fill);
	        setForeground(new Color(75, 30, 213));
	        setUI(new ComboBoxUI());
	    }
	    
	    private class ComboBoxUI extends BasicComboBoxUI{
	    	 private ComboBoxUI() {
					/*
					 * addMouseListener(new MouseAdapter() {
					 * 
					 * @Override public void mouseEntered(MouseEvent me) { mouseOver = true;
					 * repaint(); }
					 * 
					 * @Override public void mouseExited(MouseEvent me) { mouseOver = false;
					 * repaint(); } }); addFocusListener(new FocusAdapter() {
					 * 
					 * @Override public void focusGained(FocusEvent fe) { showing(false); }
					 * 
					 * @Override public void focusLost(FocusEvent fe) { showing(true); } });
					 * addItemListener(new ItemListener() {
					 * 
					 * @Override public void itemStateChanged(ItemEvent ie) { if (!isFocusOwner()) {
					 * if (getSelectedIndex() == -1) { showing(true); } else { showing(false); } } }
					 * }); addPopupMenuListener(new PopupMenuListener() {
					 * 
					 * @Override public void popupMenuWillBecomeVisible(PopupMenuEvent pme) { if
					 * (arrowButton != null) { arrowButton.setBackground(new Color(200, 200, 200));
					 * } }
					 * 
					 * @Override public void popupMenuWillBecomeInvisible(PopupMenuEvent pme) { if
					 * (arrowButton != null) { arrowButton.setBackground(new Color(150, 150, 150));
					 * } }
					 * 
					 * @Override public void popupMenuCanceled(PopupMenuEvent pme) { if (arrowButton
					 * != null) { arrowButton.setBackground(new Color(150, 150, 150)); } } });
					 */
	    	 }
	        @Override
	        protected JButton createArrowButton() {
	            return new ArrowButton();
	        }
	        @Override
	        public void paint(Graphics grphcs, JComponent jc) {
	            super.paint(grphcs, jc);
	            Graphics2D g2 = (Graphics2D) grphcs;
	            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
	            int s = strokeWidth;
	            int w = getWidth() - (2 * s);
	            int h = getHeight() - (2 * s);
	            g2.setColor(fill);
	            g2.fillRoundRect(s, s, w, h, 40, 40);
	            g2.setStroke(new BasicStroke(s));
	            g2.setColor(new Color(75, 30, 213));
	            g2.drawRoundRect(s, s, w, h, 46, 46);
	            g2.dispose();
	        }
	        
	    }
}
