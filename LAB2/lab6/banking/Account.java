package banking;

public class Account{
	protected double balance;

	public Account (double init_balance){balance=init_balance;}
	public Account(){balance=0.0d;}
	public double getBalance(){return balance;}
	public void setBalance(double blc) {balance=blc;}
	public boolean deposit(double amt){
		balance+=amt;
		System.out.println("Deposit "+amt+" :True");
		return true;}
	public boolean withdraw(double amt){
		if(amt<=balance){balance-=amt;
		System.out.println("Withdraw "+amt+" :True");
		return true;}
		else{
			System.out.println("Withdraw "+amt+" :False");
			return false;}}
}
