package banking;

public class Account{
private double balance;

public Account (double init_balance){balance=init_balance;}
public Account(){balance=0.0d;}
public double getBalance(){return balance;}
public boolean deposit(double amt){balance+=amt;return true;}
public boolean withdraw(double amt){if(amt<=balance){balance-=amt;return true;}else{return false;}}
}