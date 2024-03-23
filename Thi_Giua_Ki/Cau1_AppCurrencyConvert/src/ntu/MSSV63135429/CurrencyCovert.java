package ntu.MSSV63135429;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import customer.RoundedButton;

import javax.swing.JLabel;
import java.awt.Color;

public class CurrencyCovert extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Font font;
	
	void usingCustomFonts() {
	    try {
	    	File fontStyle = new File(getClass().getResource("Coiny-Regular.ttf").getFile());
	    	font = Font.createFont(Font.TRUETYPE_FONT, fontStyle);
	    } catch (FontFormatException | IOException exception) {
	        JOptionPane.showMessageDialog(null, exception.getMessage());
	    }
	}
	
	public CurrencyCovert() {
		usingCustomFonts();
		setTitle("Currency Convert");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 374);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("CURRENCY CONVERT");
		lblNewLabel.setFont(font.deriveFont(24f));
		lblNewLabel.setBounds(225, 40, 284, 47);
		contentPane.add(lblNewLabel);
		
//		JButton btnCovert = new RoundedButton();
//		btnCovert.setSize(348, 61);
//		contentPane.add(btnCovert);
	}
}
