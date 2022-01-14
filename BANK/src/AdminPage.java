import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;


public class AdminPage {
	JFrame frame=new JFrame("Admin Page");
	JPanel panel= new JPanel();
	JComboBox numberbox=new JComboBox();
	JLabel numberlabel=new JLabel("Select account");
	JLabel useridlabel=new JLabel("User id");
	JLabel passwordlabel=new JLabel("Password");
	JLabel aadharlabel=new JLabel("Aadhar no");
	JLabel maillabel=new JLabel("Email id");
	JLabel balancelabel=new JLabel("Balance");
	JTextField useridfield=new JTextField();
	JTextField passwordfield=new JTextField();
	JTextField aadharfield=new JTextField();
	JTextField mailfield=new JTextField();
	JTextField balancefield=new JTextField();
	String file="data.txt";
	ArrayList<Account> info=new ArrayList<Account>();
	Data d=new Data();
	
	AdminPage() throws IOException{
		info=d.read(file);
		for(Account a:info) {
			numberbox.addItem(a.number);
			if(numberbox.getSelectedItem().equals(a.number)) {
				useridfield.setText(a.username);
				passwordfield.setText(a.password);
				aadharfield.setText(a.aadhar);
				mailfield.setText(a.mail);
				balancefield.setText(String.valueOf(a.balance));
			}
		}
		numberbox.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				for(Account a:info) {
					if(numberbox.getSelectedItem().equals(a.number)) {
						useridfield.setText(a.username);
						passwordfield.setText(a.password);
						aadharfield.setText(a.aadhar);
						mailfield.setText(a.mail);
						balancefield.setText(String.valueOf(a.balance));
					}
				}
				
			}
			
		});
		
		
		useridfield.setEditable(false);
		passwordfield.setEditable(false);
		aadharfield.setEditable(false);
		mailfield.setEditable(false);
		balancefield.setEditable(false);
		
		numberlabel.setForeground(Color.white);
		numberlabel.setBorder(BorderFactory.createLoweredBevelBorder());
		
		useridlabel.setForeground(Color.white);
		useridlabel.setBorder(BorderFactory.createLoweredBevelBorder());
		
		passwordlabel.setForeground(Color.white);
		passwordlabel.setBorder(BorderFactory.createLoweredBevelBorder());
		
		aadharlabel.setForeground(Color.white);
		aadharlabel.setBorder(BorderFactory.createLoweredBevelBorder());
		
		maillabel.setForeground(Color.white);
		maillabel.setBorder(BorderFactory.createLoweredBevelBorder());
		
		balancelabel.setForeground(Color.white);
		balancelabel.setBorder(BorderFactory.createLoweredBevelBorder());
					
		panel.add(numberlabel);
		panel.add(numberbox);
		panel.add(useridlabel);
		panel.add(useridfield);
		panel.add(passwordlabel);
		panel.add(passwordfield);
		panel.add(aadharlabel);
		panel.add(aadharfield);
		panel.add(maillabel);
		panel.add(mailfield);
		panel.add(balancelabel);
		panel.add(balancefield);
		panel.setPreferredSize(new Dimension(400,300));
		panel.setLayout(new GridLayout(6,2));
		panel.setBackground(Color.BLACK);
		
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
	}
	
}
