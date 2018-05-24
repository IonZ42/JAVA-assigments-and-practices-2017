import java.util.*;

public class Account{
	private int id;
	private double balance;
	private double annualInterestRate=1.5;
	private Date dateCreadted;
	private String name;
	private ArrayList<Transaction> transactions =new ArrayList<Transaction>();

	public Account(){id=0;balance=0.0d;dateCreadted=new Date();}
	public Account (int init_id,double init_balance){id=init_id;balance=init_balance;dateCreadted=new Date();}
	public Account (String n,int init_id,double init_balance)
	{name=n;id=init_id;balance=init_balance;dateCreadted=new Date();}
	public void setBalance(double blc) {balance=blc;}
	public double getBalance(){return balance;}
	public void setID(int id0) {id=id0;}
	public int getID(){return id;}
	public void setAnnualInterestRate(double rate) {annualInterestRate=rate;}
	public double getAnnualInterestRate(){return annualInterestRate;}
	public Date getDateCreadted() {return dateCreadted;}
	public void setName(String n) {name=n;}
	public String getName() {return name;}
	public double getMothlyInterestRate(){return annualInterestRate/12.0/100;}
	public double getMothlyInterest(){return balance*getMothlyInterestRate();}
	public boolean deposit(double amt){
		transactions.add(new Transaction('D',amt,amt+balance,"deposit "+amt+" dollars at "+new Date()));
		System.out.println("deposit "+amt+" dollars");balance+=amt;return true;}
	public boolean withdraw(double amt){
		if(amt<=balance){balance-=amt;System.out.println("withdraw "+amt+" dollars");		
		transactions.add(new Transaction('W',amt,balance-amt,"withdraw "+amt+" dollars at "+new Date()));return true;}
		else{System.out.println("withdraw failed,balance isn't enough");return false;}}
	public void printTransactions() {
		System.out.println("history transactions:");
		for(Transaction t:transactions) {System.out.println(t.getDescription());}
	}
}