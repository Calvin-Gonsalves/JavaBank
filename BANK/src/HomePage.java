import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.text.MaskFormatter;

public class HomePage {
	
	JFrame home=new JFrame("Home page");
	JPanel panel=new JPanel();
	JLabel welcome=new JLabel();
	JLabel number=new JLabel();
	JButton check=new JButton("Account details");
	JButton deposit=new JButton("Deposit");
	JButton transfer=new JButton("Transfer");
	JButton withdraw=new JButton("Withdraw");
	Data d=new Data();
	double amount;
	String file="data.txt";
	String file2="carddata.txt";
    String ifsccode="EBIN0001234";  //bank ifsc code
	ArrayList<Account> info=new ArrayList<Account>();
	ArrayList<Card> card=new ArrayList<Card>();
	
	
	
	public HomePage(Account a) {
		try {
			info=d.read(file);
			
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			card=d.readcard(file2);
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		
		welcome.setText(a.username+"  welcome  to  Ebank");
		welcome.setBounds(50, 0, 500, 100);
		welcome.setForeground(Color.white);
		welcome.setHorizontalAlignment(JLabel.CENTER);
		welcome.setFont(new Font("Ink Free",Font.BOLD,30));
		
		number.setText("Account number -" +a.number);
		number.setBounds(20,200 ,300,50);
		number.setForeground(Color.green);
		number.setHorizontalAlignment(JLabel.CENTER);
		number.setFont(new Font(null,Font.BOLD,20));
		
		
		check.setFocusable(false);
		check.setBounds(8, 120, 130, 60);
		check.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String check=JOptionPane.showInputDialog("Enter your bank pin");
				if(check.equals("1111")) {
				Object[] o= {
					"Account number - "+a.number+"\n\n"+
					"User id                   - "+a.username+"\n\n"+
					"Password            - "+a.password+"\n\n"+
					"Aadhar no            - "+a.aadhar+"\n\n"+
					"Email address    - "+a.mail +"\n\n"+
					"Balance                - "+a.balance
					
				};
				JOptionPane.showMessageDialog(null, o, "Account details", JOptionPane.INFORMATION_MESSAGE);
			}
			}	
		});
		
		deposit.setFocusable(false);
		deposit.setBounds(149, 120, 120, 60);
		deposit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			
				
				
				JFormattedTextField one=new JFormattedTextField();;
				
				JTextField two = new JTextField();
				JFormattedTextField three=new JFormattedTextField();
				JPasswordField four =  new JPasswordField();
				
				Object[] details= {
						"Bank Name",one,
						"Bank ifsc ",two,
						"Account number",three,
						
				};
				int check1=JOptionPane.showConfirmDialog(null, details, "Account details", JOptionPane.OK_CANCEL_OPTION);
				if(check1==JOptionPane.OK_OPTION) {
					if(one.getText().equalsIgnoreCase("ebank") && two.getText().equalsIgnoreCase(ifsccode) && three.getText().equals(a.number)){
						try {one = new JFormattedTextField(new MaskFormatter("#### #### #### ####"));
						} catch (ParseException e4) {}
						try {three = new JFormattedTextField(new MaskFormatter("##/##"));
						} catch (ParseException e3) {}
						two.setText("");
						
						Object[] message = {
							    "Card Number", one,
							    "Name on card:", two,
							    "Expiry Date " , three,
							    "cvv" ,four
							};
						int check2 = JOptionPane.showConfirmDialog(null, message, "Card Details", JOptionPane.OK_CANCEL_OPTION);
						if (check2 == JOptionPane.OK_OPTION) {
							
							String mypass=String.valueOf(four.getPassword());
							
							for(Card c1:card) {
							if(one.getText().equals(c1.cardnumber) && two.getText().equals(c1.cardname) && three.getText().equals(c1.expiry) && mypass.equals(c1.cvv)) {
								JOptionPane.showMessageDialog(null, "Your card balance is "+c1.cardbalance, "Card verified", JOptionPane.INFORMATION_MESSAGE);
								
								amount=	Double.parseDouble(JOptionPane.showInputDialog("Enter the amount to deposit"));
								try {
									a.deposit(amount);
									for(Account b:info) {
										if(a.username.equals(b.username)) {
											b.balance=a.balance;
										}
									}
									for(Card x:card) {
										if(one.getText().equals(x.cardnumber)) {
											x.cardbalance-=amount;
										}
									}
									d.writecard(card, file2);
									d.write(info,file);
									JOptionPane.showMessageDialog(null, amount+" successfully deposited, current balance -"+a.balance,"Deposit successful", JOptionPane.INFORMATION_MESSAGE);
								} catch (IOException e1) {}								
								}else {
									JOptionPane.showMessageDialog(null, "wrong details");
								}
							}	
							}
						   
						}
					}
				}
				
			
			
			
			
		

		});
		
		withdraw.setFocusable(false);
		withdraw.setBounds(280, 120, 120, 60);
		withdraw.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				amount=	Double.parseDouble(JOptionPane.showInputDialog("Enter the amount to withdraw"));
				
				try {
					a.withdraw(amount);
					for(Account b:info) {
						if(a.username.equals(b.username)) {
							b.balance=a.balance;
						}
					}
					d.write(info,file);
					JOptionPane.showMessageDialog(null, amount+" successfully withdrawed, current balance -"+a.balance,"withdraw successful", JOptionPane.INFORMATION_MESSAGE);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
				}
				
			
		
});
		
		transfer.setFocusable(false);
		transfer.setBounds(410, 120, 120, 60);
		transfer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JTextField one=new JTextField();
				JTextField two=new JTextField();
				JTextField three=new JTextField();
				JTextField four=new JTextField();
				Object[] m= {
						"Bank name",one,
						"ifsc code",two,
						"Account number",three,
						"Account user id",four
				};
				 JOptionPane.showConfirmDialog(null, m, "Transfer Details", JOptionPane.OK_CANCEL_OPTION);
				
				
				Account transfer = null;
				if(one.getText().equalsIgnoreCase("ebank") && two.getText().equalsIgnoreCase(ifsccode)) {
					for(Account a:info) {
						
						if(three.getText().equals(a.number) && four.getText().equals(a.username)) {
							transfer=a;
							break;
						}
					}
					if(transfer!=null) {
						amount=	Double.parseDouble(JOptionPane.showInputDialog("Enter the amount to transfer"));
						
						
						try {
							a.transfer(transfer, amount);
							for(Account b:info) {
								if(a.username.equals(b.username)) {
									b.balance=a.balance;
								}
								if(transfer.number.equals(b.number)) {
									b.balance=transfer.balance;
								}
							}
							d.write(info,file);
							JOptionPane.showMessageDialog(null, amount+" successfully transfered, current balance -"+a.balance,"Transfer successful", JOptionPane.INFORMATION_MESSAGE);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}
				
				}
				
			}
			
		});
		
		panel.add(number);
		panel.add(transfer);
		panel.add(welcome);
		panel.add(check);
		panel.add(deposit);
		panel.add(withdraw);
		panel.setPreferredSize(new Dimension(550,250));
		panel.setLayout(null);
		panel.setForeground(Color.white);
		panel.setBackground(Color.BLACK);
		
		home.add(panel);
		home.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		home.pack();
		home.setVisible(true);
		home.setResizable(false);
		home.setLocationRelativeTo(null);
		
	

	}

}

