import java.util.*;

public class Account{
	private int id;
	private double balance;
	private double annualInterestRate;
	private Date dateCreadted;

	public Account(){id=0;balance=0.0d;annualInterestRate=4.5;dateCreadted=new Date();}
	public Account (int init_id,double init_balance){id=init_id;balance=init_balance;annualInterestRate=4.5;dateCreadted=new Date();}
	public void setBalance(double blc) {balance=blc;}
	public double getBalance(){return balance;}
	public void setID(int id0) {id=id0;}
	public int getID(){return id;}
	public void setAnnualInterestRate(double rate) {annualInterestRate=rate;}
	public double getAnnualInterestRate(){return annualInterestRate;}
	public Date getDateCreadted() {return dateCreadted;}
	public double getMothlyInterestRate(){return annualInterestRate/12.0/100;}
	public double getMothlyInterest(){return balance*getMothlyInterestRate();}
	public boolean deposit(double amt){System.out.println("deposit "+amt+" dollars");balance+=amt;return true;}
	public boolean withdraw(double amt){
		if(amt<=balance){balance-=amt;System.out.println("withdraw "+amt+" dollars");return true;}
		else{System.out.println("withdraw failed,balance isn't enough");return false;}
	}
}