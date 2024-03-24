package ntu.MSSV63135429;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.json.JSONObject;

import customer.IconButton;
import customer.RoundedButton;
import customer.RoundedComboBox;
import customer.RoundedPanel;
import customer.RoundedText;

import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.SpringLayout;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import java.awt.Panel;


public class CurrencyCovert extends JFrame {
	Map<String, Double> ratesMap;
	DecimalFormat decimalFormat = new DecimalFormat("#.###");
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Font font;
	private JTextField textField;
	String[] currencyNames = {
            "VND", "AED", "AFN", "ALL", "AMD", "ANG", "AOA", "ARS", "AUD", "AWG", 
            "AZN", "BAM", "BBD", "BDT", "BGN", "BHD", "BIF", "BMD", "BND", "BOB", 
            "BRL", "BSD", "BTN", "BWP", "BYN", "BZD", "CAD", "CDF", "CHF", "CLP", 
            "CNY", "COP", "CRC", "CUP", "CVE", "CZK", "DJF", "DKK", "DOP", "DZD", 
            "EGP", "ERN", "ETB", "EUR", "FJD", "FKP", "FOK", "GBP", "GEL", "GGP", 
            "GHS", "GIP", "GMD", "GNF", "GTQ", "GYD", "HKD", "HNL", "HRK", "HTG", 
            "HUF", "IDR", "ILS", "IMP", "INR", "IQD", "IRR", "ISK", "JEP", "JMD", 
            "JOD", "JPY", "KES", "KGS", "KHR", "KID", "KMF", "KRW", "KWD", "KYD", 
            "KZT", "LAK", "LBP", "LKR", "LRD", "LSL", "LYD", "MAD", "MDL", "MGA", 
            "MKD", "MMK", "MNT", "MOP", "MRU", "MUR", "MVR", "MWK", "MXN", "MYR", 
            "MZN", "NAD", "NGN", "NIO", "NOK", "NPR", "NZD", "OMR", "PAB", "PEN", 
            "PGK", "PHP", "PKR", "PLN", "PYG", "QAR", "RON", "RSD", "RUB", "RWF", 
            "SAR", "SBD", "SCR", "SDG", "SEK", "SGD", "SHP", "SLE", "SLL", "SOS", 
            "SRD", "SSP", "STN", "SYP", "SZL", "THB", "TJS", "TMT", "TND", "TOP", 
            "TRY", "TTD", "TVD", "TWD", "TZS", "UAH", "UGX", "USD", "UYU", "UZS", 
            "VES", "VUV", "WST", "XAF", "XCD", "XDR", "XOF", "XPF", "YER", "ZAR", 
            "ZMW", "ZWL"
        };
	private String urlBase = "https://v6.exchangerate-api.com/v6/acc9075a8e69b6d784e0c26a/latest/";
	
	void usingCustomFonts() {
	    try {
	    	File fontStyle = new File(getClass().getResource("Coiny-Regular.ttf").getFile());
	    	font = Font.createFont(Font.TRUETYPE_FONT, fontStyle);
	    } catch (FontFormatException | IOException exception) {
	        JOptionPane.showMessageDialog(null, exception.getMessage());
	    }
	}
	
	private void readApi(String currency) {
		ratesMap = new HashMap<>();
	    try {
	        URL url = new URL(urlBase + currency);

	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	        connection.setRequestMethod("GET");

	        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	        StringBuilder response = new StringBuilder();
	        String line;
	        while ((line = reader.readLine()) != null) {
	            response.append(line);
	        }
	        reader.close();

	        connection.disconnect();
	        
	        JSONObject jsonObject = new JSONObject(response.toString());

	        JSONObject conversionRates = jsonObject.getJSONObject("conversion_rates");
	        for (String key : conversionRates.keySet()) {
	            double value = conversionRates.getDouble(key);
	            ratesMap.put(key, value);
	        }

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
		
	}
	
	public CurrencyCovert() {
		usingCustomFonts();
		readApi("VND"); 
		
		setTitle("Currency Convert");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 374);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("CURRENCY CONVERT");
		lblNewLabel.setBounds(213, 34, 273, 47);
		lblNewLabel.setFont(font.deriveFont(24f));
		contentPane.add(lblNewLabel);
		
		JTextField txtFrom = new RoundedText();
		txtFrom.setBounds(25, 188, 187, 33);
		txtFrom.setFont(font.deriveFont(16f));
		
		txtFrom.setBorder(new EmptyBorder(4, 24, 0, 16));
		contentPane.add(txtFrom);
		
		RoundedText txtTo = new RoundedText();
		txtTo.setEditable(false);
		txtTo.setBounds(477, 188, 187, 33);
		txtTo.setFont(font.deriveFont(16f));
		txtTo.setBorder(new EmptyBorder(4, 24, 0, 16));
		contentPane.add(txtTo);
		
		JPanel panelConvert = new RoundedPanel();
		panelConvert.setBounds(253, 181, 194, 40);
		contentPane.add(panelConvert);
		panelConvert.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("1");
		lblNewLabel_1.setBounds(33, 4, 6, 36);
		lblNewLabel_1.setFont(font.deriveFont(14f));
		lblNewLabel_1.setForeground(new Color(194, 181, 234));
		panelConvert.add(lblNewLabel_1);
		
		JLabel currencyFrom = new JLabel("VND");
		currencyFrom.setBounds(44, 4, 37, 36);
		currencyFrom.setFont(font.deriveFont(14f));
		currencyFrom.setForeground(new Color(194, 181, 234));
		panelConvert.add(currencyFrom);
		
		JLabel lblNewLabel_2 = new JLabel("=");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setForeground(new Color(194, 181, 234));
		lblNewLabel_2.setBounds(80, 4, 14, 36);
		lblNewLabel_2.setFont(font.deriveFont(14f));
		panelConvert.add(lblNewLabel_2);
		
		JLabel exchangeRate = new JLabel("1 VND");
		exchangeRate.setForeground(new Color(194, 181, 234));
		exchangeRate.setBounds(99, 4, 85, 36);
		exchangeRate.setFont(font.deriveFont(14f));
		panelConvert.add(exchangeRate);
		
		
		
		RoundedComboBox<String> cBTo = new RoundedComboBox();
		cBTo.setModel(new DefaultComboBoxModel<String>(currencyNames));
		cBTo.setBounds(477, 102, 187, 58);
		cBTo.setBorder(new EmptyBorder(6, 24, 0, 4));
		cBTo.setFont(font.deriveFont(20f));
		contentPane.add(cBTo);
		cBTo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String selectedCurrency = (String) e.getItem();
                    cBTo.setLabeText(selectedCurrency);
                    exchangeRate.setText(decimalFormat.format(ratesMap.get(selectedCurrency)) + " " + selectedCurrency);
                }
            }
        });
		
		RoundedComboBox<String> cBFrom = new RoundedComboBox();
		cBFrom.setModel(new DefaultComboBoxModel<String>(currencyNames));
		cBFrom.setFont(font.deriveFont(20f));
		cBFrom.setBounds(25, 102, 187, 58);
		cBFrom.setBorder(new EmptyBorder(6, 24, 0, 4));
		contentPane.add(cBFrom);
		cBFrom.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                	readApi((String) e.getItem());
                    String selectedCurrency = (String) e.getItem();
                    cBFrom.setLabeText(selectedCurrency);
                    currencyFrom.setText(selectedCurrency);
                    String currencyTo = (String)cBTo.getSelectedItem();
                    exchangeRate.setText(decimalFormat.format(ratesMap.get(currencyTo)) + " " + currencyTo);
            
                }
            }
        });
		
		JButton btnCovert = new RoundedButton();
		btnCovert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String amountFrom =  (String)txtFrom.getText();
				if(amountFrom.equals("")) {
					JOptionPane.showMessageDialog(null, "Cannot be converted without value!");
					return;
				}
				try {
		            double number = Double.parseDouble(amountFrom);
		            double value = number * ratesMap.get(cBTo.getSelectedItem());
		            txtTo.setText(String.valueOf(decimalFormat.format(value)));
		        } catch (NumberFormatException ex) {
		        	JOptionPane.showMessageDialog(null, "Invalid value!");
		        }
				
			}
		});
		btnCovert.setBounds(176, 250, 348, 61);
		btnCovert.setText("Convert");
		btnCovert.setFont(font.deriveFont(32f));
		btnCovert.setBorder(new EmptyBorder(6, 0, 0, 0));
		contentPane.add(btnCovert);
		
		JButton exchange = new IconButton(getClass().getResource("switch-horizontal-02.png"));
		exchange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currencyTo = (String)cBTo.getSelectedItem();
				String currencyFrom = (String)cBFrom.getSelectedItem();
				cBFrom.setSelectedItem(currencyTo);
				cBTo.setSelectedItem(currencyFrom);
				
			}
		});
		exchange.setBounds(305, 102, 82, 61);
		contentPane.add(exchange);
		
		Panel panel = new Panel();
		panel.setBackground(new Color(194, 181, 234));
		panel.setBounds(220, 132, 90, 2);
		contentPane.add(panel);
		
		Panel panel_1 = new Panel();
		panel_1.setBackground(new Color(194, 181, 234));
		panel_1.setBounds(381, 132, 90, 2);
		contentPane.add(panel_1);
	}
}
