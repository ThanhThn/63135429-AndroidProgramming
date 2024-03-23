package ntu._63135429;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class ManHinhChinh extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManHinhChinh frame = new ManHinhChinh();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ManHinhChinh() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1128, 414);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox listCurrency1 = new JComboBox();
		listCurrency1.setBounds(21, 31, 156, 21);
		contentPane.add(listCurrency1);
		
		JComboBox listCurrency2 = new JComboBox();
		listCurrency2.setBounds(417, 31, 156, 21);
		contentPane.add(listCurrency2);
		
		textField = new JTextField();
		textField.setBounds(21, 95, 156, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(417, 95, 156, 19);
		textField_1.setColumns(10);
		contentPane.add(textField_1);
		
		JButton btnChuyenDoi = new JButton("Covert");
		btnChuyenDoi.setBackground(new Color(255, 125, 59));
		btnChuyenDoi.setBounds(231, 31, 411, 199);
		
		
		btnChuyenDoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		contentPane.add(btnChuyenDoi);
	}
}
