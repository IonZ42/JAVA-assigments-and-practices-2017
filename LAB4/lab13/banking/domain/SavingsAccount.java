package banking.domain;

import banking.domain.Account;

public class SavingsAccount extends Account{
	private double interestRate;//百分数，显示时*100,useless by now

	public SavingsAccount(){super(0);interestRate=0;}
	public SavingsAccount(double balance,double i_rate)
	{super(balance);interestRate=i_rate;
	System.out.println("Creating a Savings Account with a "
	+ balance+ " balance and " +i_rate*100 + "% interest.");
	}
	public double getInterestRate() {return interestRate;}
//	@Override
//	public boolean deposit(double amt) {super.deposit(amt);return true;}
}