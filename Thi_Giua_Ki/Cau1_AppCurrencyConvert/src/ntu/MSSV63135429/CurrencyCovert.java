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
import customer.RoundedText;

import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.SpringLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class CurrencyCovert extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Font font;
	private JTextField textField;
	
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
		lblNewLabel.setBounds(213, 34, 273, 47);
		lblNewLabel.setFont(font.deriveFont(24f));
		contentPane.add(lblNewLabel);
		
		JButton btnCovert = new RoundedButton();
		btnCovert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCovert.setBounds(176, 250, 348, 61);
		btnCovert.setText("Convert");
		btnCovert.setFont(font.deriveFont(32f));
		contentPane.add(btnCovert);
		
		JTextField txtFrom = new RoundedText();
		txtFrom.setBounds(48, 195, 187, 33);
		txtFrom.setFont(font.deriveFont(16f));
		
		txtFrom.setBorder(new EmptyBorder(4, 24, 0, 16));
		contentPane.add(txtFrom);
		
		RoundedText txtTo = new RoundedText();
		txtTo.setEditable(false);
		txtTo.setBounds(464, 194, 187, 33);
		txtTo.setFont(font.deriveFont(16f));
		txtTo.setBorder(new EmptyBorder(4, 24, 0, 16));
		contentPane.add(txtTo);
		

		
	}
}
