package banking.domain;

import banking.domain.Account;

public class CheckingAccount extends Account{
	private double overdraftProtection;
	private double overdraftLimit;///useless by now

	public CheckingAccount() {super(0);overdraftProtection=0;overdraftLimit=0;}
	public CheckingAccount(double balance,double protect)
	{super(balance);overdraftProtection=protect;overdraftLimit=protect;
	System.out.println("Creating a Checking Account with a "+ balance+ " balance and " 
	+protect + " overdraft protection.");
	}
	public CheckingAccount(double balance)
	{super(balance);overdraftProtection=0;overdraftLimit=0;
	System.out.println("Creating a Checking Account with a "+ balance
			+" balance and no overdraft protection.");
	}
	public double getOverdraftProtection(){return overdraftProtection;}
	@Override
	public boolean withdraw(double amt){
		if(amt<=super.getBalance()){
			super.setBalance(super.getBalance()-amt);
			System.out.println("Withdraw "+amt+" :True");
			return true;}
		else if((amt-super.getBalance())<=overdraftProtection){
			overdraftProtection-=(amt-super.getBalance());
		    super.setBalance(0);
		    System.out.println("Withdraw "+amt+" :True");
		    return true;}
		else {
			System.out.println("Withdraw "+amt+" :False");
			return false;}}
}