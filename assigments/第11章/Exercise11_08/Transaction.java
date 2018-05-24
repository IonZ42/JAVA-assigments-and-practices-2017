import java.util.*;
public class Transaction {
	private Date date;
	private char type;
	private double amount;
	private double balance;
	private String description;
	
	public Transaction(char tp,double amt,double blc,String d)
	{date=new Date();amount=amt;type=tp;balance=blc;description=d;}
	public Date getDateCreadted() {return date;}
	public void setType(char t) {type=t;}
	public char getType() {return type;}
	public void setAmount(double amt) {amount=amt;}
	public double getAmount(){return amount;}
	public void setBalance(double blc) {balance=blc;}
	public double getBalance(){return balance;}
	public void setDescription(String d) {description=d;}
	public String getDescription() {return description;}
}
