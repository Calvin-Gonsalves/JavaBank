

public class Account {
String username;
String password;
String number;
String aadhar;
String mail;
double balance;

Account(String username,String password,String number,String aadhar,String mail,double balance){
	this.username=username;
	this.password=password;
	this.number=number;
	this.aadhar=aadhar;
	this.mail=mail;
	this.balance=balance;
	}



public void deposit(double amount) {
	if(amount>0) {
	this.balance=balance+amount;
	}
}

public void withdraw(double amount) {
	if(amount<=balance) {
	this.balance=balance-amount;
	}
}

public  void transfer(Account transfer,double amount) {
	this.withdraw(amount);
	transfer.deposit(amount);
}


	
	
}
