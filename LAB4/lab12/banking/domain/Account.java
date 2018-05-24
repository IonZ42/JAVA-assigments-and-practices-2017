package banking.domain;

public class Account{
	protected double balance;
	protected int accountID=nextAccountID;
	static int nextAccountID=0;//整个银行共用的accountsID列表

	public Account (double init_balance){balance=init_balance;nextAccountID++;}
	public Account(){balance=0.0d;nextAccountID++;}
	public void setBalance(double blc) {balance=blc;}
	public double getBalance(){return balance;}
	public void setAccountID(int id) {accountID=id;}
	public int getAccountID() {return accountID;}
	public String getAccountType() {return this.getClass().getName().replaceFirst("banking.domain.", "");}
	
	public boolean deposit(double amt){
		balance+=amt;
		System.out.println("deposit "+amt);
		Bank.getBank().transactions.add(new Transaction(this.getClass().getName(),accountID,"deposit",amt,true,""));
		return true;}
	public void withdraw(double amt) throws OverdraftException {
		if(amt<=balance){balance-=amt;
		System.out.println("withdraw "+amt);
		Bank.getBank().transactions.add(new Transaction(this.getClass().getName(),accountID,"withdraw",amt,true,""));}
		else{
			System.out.println("withdraw "+amt);
			Bank.getBank().transactions.add(new Transaction(this.getClass().getName(),accountID,"withdraw",amt,false,""));
			throw new OverdraftException("Exception: No overdraft protection   Deficit: ",amt);
			}}
}
