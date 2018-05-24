package banking.domain;

import banking.domain.Account;

public class CheckingAccount extends Account{
	private double overdraftProtection;
	private double overdraftLimit;//useless by now
	private boolean isProtected=false;

	public CheckingAccount() {super(0);overdraftProtection=0;overdraftLimit=0;}
	public CheckingAccount(double balance,double protect)
	{super(balance);overdraftProtection=protect;overdraftLimit=protect;
	System.out.println("Creating a Checking Account with a "+ balance+ " balance and " 
	+protect + " overdraft protection.");isProtected=true;
	}
	public CheckingAccount(double balance)
	{super(balance);overdraftProtection=0;overdraftLimit=0;
	System.out.println("Creating a Checking Account with a "+ balance
			+" balance and no overdraft protection.");isProtected=false;
	}
	public double getOverdraftProtection(){return overdraftProtection;}
	
	@Override
	public void withdraw(double amt) throws OverdraftException {
		if(overdraftProtection>0) {isProtected=true;}else {isProtected=false;}
		if(amt<=super.getBalance()){
			super.setBalance(super.getBalance()-amt);
			System.out.println("withdraw "+amt);
			Bank.getBank().transactions.add(new Transaction("CheckingAccount",accountID,"withdraw",amt,true,""));}
		else if((amt-super.getBalance())<=overdraftProtection){
			overdraftProtection-=(amt-super.getBalance());
		    super.setBalance(0);
		    System.out.println("withdraw "+amt);
		    Bank.getBank().transactions.add(new Transaction("CheckingAccount",accountID,"withdraw",amt,true,""));}
		else {
			System.out.print("withdraw "+amt);
			if(isProtected) {
				Bank.getBank().transactions.add(new Transaction("CheckingAccount",accountID,"withdraw",amt,false,""));
				throw new OverdraftException("Exception: Insufficient funds for overdraft protection   Deficit: ",amt);}
			else{
				Bank.getBank().transactions.add(new Transaction("CheckingAccount",accountID,"withdraw",amt,false,""));
				throw new OverdraftException("Exception: No overdraft protection   Deficit: ",amt);}}}
}