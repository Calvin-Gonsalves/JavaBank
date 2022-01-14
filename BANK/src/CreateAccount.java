import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

public class CreateAccount implements ActionListener{

	JFrame d=new JFrame();
	JLabel username=new JLabel("      User Name");
	JLabel idnum=new JLabel("       Aadhar Number");
	JLabel deposit=new JLabel("      Initial Deposit");
	JLabel setpass=new JLabel("      Set Password");
	JLabel mail=new JLabel("      Enter email");
	JTextField namefield=new JTextField();
	JTextField numberfield=new JTextField();
	JTextField depositfield=new JTextField();
	JTextField setpassfield=new JTextField();
	JTextField mailfield=new JTextField();
	JButton proceed=new JButton("Proceed");
	JButton reset=new JButton("Reset");
	Data data=new Data();
	boolean check=false;
	String file="data.txt";
	
	 CreateAccount(){
		 proceed.setFocusable(false);
		 proceed.addActionListener(this);
		 
		 reset.setFocusPainted(false);
		 reset.addActionListener(this);
		 
		 
		 
		 d.add(username);
		 d.add(namefield);
		 d.add(idnum);
		 d.add(numberfield);
		 d.add(mail);
		 d.add(mailfield);
		 d.add(setpass);
		 d.add(setpassfield);
		 d.add(deposit);
		 d.add(depositfield);
		 d.add(proceed);
		 d.add(reset);
		d.setSize(300, 300);
		d.setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
		d.setTitle("Create New Account");
		d.setLayout(new GridLayout(6,2));
		d.setLocationRelativeTo(null);
		d.setVisible(true);
	 }
	 public void actionPerformed(ActionEvent e) {
		 if(e.getSource()==reset) {
				namefield.setText("");
				numberfield.setText("");
				depositfield.setText("");
				setpassfield.setText("");
			}
		 if(e.getSource()==proceed) {
			
		 A:if(true) {
		 if(namefield.getText().isEmpty()) {
			 JOptionPane.showMessageDialog(d, "username not found", "Error", JOptionPane.ERROR_MESSAGE );
			 break A;	 
		 }
		 
		 if(numberfield.getText().isEmpty()) {
			 JOptionPane.showMessageDialog(d, "Aadhar number not found", "Error", JOptionPane.ERROR_MESSAGE );
			 break A;
		 }
		 try {
		 Integer.parseInt(numberfield.getText());
		 }catch(NumberFormatException k){
			 JOptionPane.showMessageDialog(d, "enter digits only for Aadhar number", "Error", JOptionPane.ERROR_MESSAGE );
			 break A;
		 }
		 if(mail.getText().isEmpty()) {
			 JOptionPane.showMessageDialog(d, "email not found", "Error", JOptionPane.ERROR_MESSAGE );
			 break A;	 
		 }
		 if(setpassfield.getText().isEmpty()) {
			 JOptionPane.showMessageDialog(d, "password not found", "Error", JOptionPane.ERROR_MESSAGE );
			 break A;
		 }
		 if(depositfield.getText().isEmpty()) {
			 JOptionPane.showMessageDialog(d, "enter your initial deposit", "Error", JOptionPane.ERROR_MESSAGE );
			 break A;
		 }
		 try {
			 Integer.parseInt(depositfield.getText());
			 }catch(NumberFormatException k){
				 JOptionPane.showMessageDialog(d, "enter digits only for your deposit", "Error", JOptionPane.ERROR_MESSAGE );
				 break A;
			 }
		 if(Integer.parseInt(depositfield.getText())<1000) {
			 JOptionPane.showMessageDialog(d, "minimum deposit of 1000 should be done", "Error", JOptionPane.ERROR_MESSAGE );
			 break A;
		 }
		check=true;
		 }
		if(check) {
			String username=namefield.getText();
			String password=setpassfield.getText();
			String aadhar=numberfield.getText();
			String mail=mailfield.getText();
			double balance=Double.parseDouble(depositfield.getText());
			try {
				data.add(file, username, password,aadhar,mail, balance);
				JOptionPane.showMessageDialog(d, username+" your account is created", "Account created", JOptionPane.INFORMATION_MESSAGE );
				new LoginPage();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	
				
		 }
	 }
	
}
