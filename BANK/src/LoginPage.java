import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;

public class LoginPage implements ActionListener  {
	
ArrayList<Account> info=new ArrayList<Account>();
ArrayList<Card> card=new ArrayList<Card>();
Data data=new Data();
int check;
String file="data.txt";
JFrame frame=new JFrame("Banking System");
JTextField idfield=new JTextField();
JLabel idlabel=new JLabel("User id");
JPasswordField passfield=new JPasswordField();
JLabel passlabel=new JLabel("Password");
JButton loginbutton=new JButton("Login");
JButton resetbutton=new JButton("Reset");
JButton create=new JButton("Create Account");
JLabel feedback=new JLabel("");
JLabel title =new JLabel("Ebank");
JMenuBar menu=new JMenuBar();
JMenu admin=new JMenu("Admin");
JMenu help=new  JMenu("Help");
JMenuItem forgotuser= new JMenuItem("Forgot username");
JMenuItem forgotpass= new JMenuItem("Forgot Password");



	 LoginPage()  {
		 try {
			info=data.read(file);
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		 
		 
		 menu.add(admin);
		 menu.add(help);
		 menu.setBackground(Color.black);
		 admin.setForeground(Color.WHITE);
		 admin.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
		 admin.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				String adminpass="adminlogin";
				String pass=JOptionPane.showInputDialog("Enter Admin password");
				if(pass.equals(adminpass)) {
					try {
						frame.dispose();
						new AdminPage();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}	
			}
				
			}

			@Override
			public void mousePressed(MouseEvent e) {}
			
			@Override
			public void mouseReleased(MouseEvent e) {}

			@Override
			public void mouseEntered(MouseEvent e) {}
			
			@Override
			public void mouseExited(MouseEvent e) {}
			
		 });
		 help.setForeground(Color.white);
		 help.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
		 help.add(forgotuser);
		 forgotuser.addActionListener(this);
		 help.add(forgotpass);
		 forgotpass.addActionListener(this);
		 
		 idfield.setBounds(150, 200, 300, 40);
		 idfield.setBackground(new Color(0,0,0));
		 idfield.setCaretColor(Color.white);
		 idfield.setBorder(BorderFactory.createLineBorder(Color.blue, 1, true));
		 idfield.setFont(new Font(null,Font.BOLD,15));
		 idfield.setForeground(Color.white);
		
		 
		 passfield.setBounds(150, 270, 300, 40);
		 passfield.setBackground(new Color(0,0,0));
		 passfield.setCaretColor(Color.white);
		 passfield.setBorder(BorderFactory.createLineBorder(Color.blue, 1, true));
		 passfield.setFont(new Font(null,Font.BOLD,15));
		 passfield.setForeground(Color.white);
		 
		 title.setForeground(Color.LIGHT_GRAY);
		 title.setFont(new Font("MV Boli",Font.ROMAN_BASELINE,70));
		 title.setBounds(170, 50, 300, 80);
		 
		 idlabel.setForeground(Color.white);
		 idlabel.setFont(new Font(null,Font.BOLD,15));
		 idlabel.setBounds(80, 200, 100, 40);
		 
		 passlabel.setForeground(Color.white);
		 passlabel.setFont(new Font(null,Font.BOLD,15));
		 passlabel.setBounds(70,270,100,40);
		 
		 feedback.setBounds(160,150,400,50);
		 feedback.setFont(new Font("MV Boli",Font.BOLD,20));
		 
		 loginbutton.setBounds(180, 350, 100, 30);
		 loginbutton.setFocusable(false);
		 loginbutton.setBackground(new Color(0,0,60));
		 loginbutton.setBorder(BorderFactory.createLineBorder(Color.cyan, 1, true));
		 loginbutton.setForeground(Color.white);
		 loginbutton.addActionListener(this);
		 
		 resetbutton.setBounds(310, 350, 100, 30);
		 resetbutton.setFocusable(false);
		 resetbutton.setBackground(new Color(0,0,60));
		 resetbutton.setBorder(BorderFactory.createLineBorder(Color.cyan, 1, true));
		 resetbutton.setForeground(Color.white);
		 resetbutton.addActionListener(this);
		 
		 create.setBounds(225, 400, 150, 30);
		 create.setFocusable(false);
		 create.setBackground(new Color(0,0,60));
		 create.setBorder(BorderFactory.createLineBorder(Color.cyan, 1, true));
		 create.setForeground(Color.white);
		 create.addActionListener(this);
		 
		 feedback.setForeground(Color.BLUE);
		 
		 JLabel piclabel = new JLabel(new ImageIcon("space.png"));
			piclabel.setBounds(0, 0, 550, 500);
		
		 frame.setJMenuBar(menu);
		 frame.add(title);
		 frame.add(create);
		 frame.add(feedback);
		 frame.add(resetbutton);
		 frame.add(loginbutton);
		 frame.add(idlabel);
		 frame.add(passlabel);
		 frame.add(idfield);
		 frame.add(passfield);
		 frame.add(piclabel);	
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 frame.getContentPane().setBackground(new Color(10,150,230));
		 frame.setSize(550, 500);
		 frame.setLocationRelativeTo(null);
		 frame.setLayout(null);
		 frame.setResizable(false);
		 frame.setVisible(true);
		 
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==resetbutton) {
			idfield.setText("");
			passfield.setText("");
		}
		
		if(e.getSource()==loginbutton) {
			check=0;
			String id = idfield.getText();
			String password = String.valueOf(passfield.getPassword());
			try {
				info=data.read(file);
				A:		for(Account a: info) {
					if(id.equals(a.username)) {
						check=1;
						if(password.equals(a.password)) {
							feedback.setText("Login Successful");
							JOptionPane.showMessageDialog(frame, "proceed to home page", "logged in", JOptionPane.INFORMATION_MESSAGE );
							frame.dispose();
							new HomePage(a);
							
							break A;
						}else {
							feedback.setText("Wrong Password");
						}
					}
					Timer t=new Timer(2500,k-> feedback.setText(""));
					t.setRepeats(false);
					t.start();
							
				}if(check==0) {
					feedback.setText("Username Not Found");
					
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	if(e.getSource()==create) {
		    frame.dispose();
			new CreateAccount();
		}
	
	if(e.getSource()==forgotuser) {
		Account check=null;
		String m=JOptionPane.showInputDialog("Enter your Mail id");
		for(Account a:info) {
			if(m.equals(a.mail)) {
				check=a;
			}
		}
		if(check==null) {
			JOptionPane.showMessageDialog(frame, "Mail doesn't exist", "Error", JOptionPane.ERROR_MESSAGE);
		}
		if (check!=null) {
		String num=JOptionPane.showInputDialog("Enter your Account number");
		if(num.equals(check.number)) {
			String pass=JOptionPane.showInputDialog("Enter your Account Password");
			if(pass.equals(check.password)) {
			JOptionPane.showMessageDialog(frame,"Your account username is "+check.username,"Username",JOptionPane.INFORMATION_MESSAGE );
			}else {
				JOptionPane.showMessageDialog(frame, "Wrong Account Password", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}else {
			JOptionPane.showMessageDialog(frame, "Wrong Account number", "Error", JOptionPane.ERROR_MESSAGE);
		}
				
		}
		
	}
	
	if(e.getSource()==forgotpass) {
		Account check=null;
		String m=JOptionPane.showInputDialog("Enter your Mail id");
		for(Account a:info) {
			if(m.equals(a.mail)) {
				check=a;
			}
		}
		if(check==null) {
			JOptionPane.showMessageDialog(frame, "Mail doesn't exist", "Error", JOptionPane.ERROR_MESSAGE);
		}
		if (check!=null) {
		String num=JOptionPane.showInputDialog("Enter your Account number");
		if(num.equals(check.number)) {
			String user=JOptionPane.showInputDialog("Enter your Account Username");
			if(user.equals(check.username)) {
			JOptionPane.showMessageDialog(frame,"Your account password is "+check.password,"Password",JOptionPane.INFORMATION_MESSAGE );
			}else {
				JOptionPane.showMessageDialog(frame, "Wrong Account Username", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}else {
			JOptionPane.showMessageDialog(frame, "Wrong Account number", "Error", JOptionPane.ERROR_MESSAGE);
		}
				
		}
		
	}
	
	
	
		}
	
		
	

	
}
