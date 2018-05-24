package banking.domain;

public class Account{
	protected double balance;

	public Account (double init_balance){balance=init_balance;}
	public Account(){balance=0.0d;}
	public double getBalance(){return balance;}
	public void setBalance(double blc) {balance=blc;}
	public boolean deposit(double amt){
		balance+=amt;
		System.out.println("deposit "+amt);
		return true;}
	public void withdraw(double amt) throws OverdraftException {
		if(amt<=balance){balance-=amt;
		System.out.println("withdraw "+amt);}
		else{
			System.out.println("withdraw "+amt);
			throw new OverdraftException("Exception: No overdraft protection   Deficit: ",amt);}}
}
