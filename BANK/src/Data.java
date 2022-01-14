import java.io.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Data {
	 int number;
	 Random r=new Random();
	 ArrayList<Account> info=new ArrayList<Account>();
	 //
	 ArrayList<Card> card=new ArrayList<Card>();

	
	public  ArrayList<Account> read(String file) throws IOException {
		FileReader fr=new FileReader(file);
		BufferedReader br=new BufferedReader(fr);
		String line;
		while((line=br.readLine())!=null) {
			String[] account=line.split(" ");
			String username=account[0];
			String password=account[1];
			String number=account[2];
			String aadhar=account[3];
			String mail=account[4];
			double balance=Double.parseDouble(account[5]);
			Account a=new Account(username,password,number,aadhar,mail,balance);
			info.add(a);
		}
		br.close();
		return info;
	}
	
	
	public  ArrayList<Card> readcard(String file) throws IOException {
		FileReader fr=new FileReader(file);
		BufferedReader br=new BufferedReader(fr);
		String line;
		while((line=br.readLine())!=null) {
			String[] cardstr=line.split("#");
			String cardnumber=cardstr[0];
			String cardname=cardstr[1];
			String expiry=cardstr[2];
			String cvv=cardstr[3];
			String phonenumber=cardstr[4];
			double cardbalance=Double.parseDouble(cardstr[5]);
			Card a=new Card(cardnumber,cardname,expiry,cvv,phonenumber,cardbalance);
			card.add(a);
		}
		br.close();
		return card;
	}
	
	
	public void add(String file,String username,String password,String aadhar,String mail,double balance) throws IOException {
		FileWriter fw=new FileWriter(file,true);
		PrintWriter pw=new PrintWriter(fw);
		number=r.nextInt(5000)+1000;
		pw.print(username+" "+password+" "+number+" "+aadhar+" "+mail+" "+balance+"\n");
		pw.close();
	}
	
	public void write(ArrayList<Account> account,String file) throws IOException {
		FileWriter fw=new FileWriter(file);
		PrintWriter pw=new PrintWriter(fw);
		for(Account a: account) {
			pw.print(a.username+" "+a.password+" "+a.number+" "+a.aadhar+" "+a.mail+" "+a.balance+"\n");
		}
		pw.close();
	}
	
	public void writecard(ArrayList<Card> account,String file) throws IOException {
		FileWriter fw=new FileWriter(file);
		PrintWriter pw=new PrintWriter(fw);
		for(Card a: card) {
			pw.print(a.cardnumber+"#"+a.cardname+"#"+a.expiry+"#"+a.cvv+"#"+a.phonenumber+"#"+a.cardbalance+"\n");
		}
		pw.close();
	}

}
