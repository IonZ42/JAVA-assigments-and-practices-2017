package banking.domain;

import java.util.ArrayList;

public class Customer implements Comparable<Customer>{
	private String firstName;
	private String lastName;
	private ArrayList<Account> accounts;
	
	public String shareFromFN="",shareFromLN="";
	public char shareType=' ';//if it's not blank,then shared from others

	public Customer(String f,String l){firstName=f;lastName=l;accounts=new ArrayList<Account>();}
	public Customer(){firstName="";lastName="";accounts=new ArrayList<Account>();}
	public String getFirstName(){return firstName;}
	public String getLastName(){return lastName;}
	public void setFirstName(String f){firstName=f;}
	public void setLastName(String l){lastName=l;}
	public SavingsAccount getSavingsAccount(){
		for(Account a:accounts) {
			if (a instanceof SavingsAccount) {return (SavingsAccount) a;}}//return first 存账户
		accounts.add(new SavingsAccount());
		return (SavingsAccount) accounts.get(accounts.size()-1);}//else return new and last 存账户
	public void setSavingsAccount(SavingsAccount sat){accounts.add(sat);}
	public CheckingAccount getCheckingAccount(){
		for(Account a:accounts) {
			if (a instanceof CheckingAccount) {return (CheckingAccount) a;}}//return first 取账户
		accounts.add(new CheckingAccount());
		return (CheckingAccount) accounts.get(accounts.size()-1);}//else return new and last 取账户
	public void setCheckingAccount(CheckingAccount cat){accounts.add(cat);}
	public Account getFirstAccount() {return accounts.get(0);}
	
	@Override
	public int compareTo(Customer a) {
		String f0=a.getFirstName(),l0=a.getLastName();
		int lastVS=lastName.compareTo(l0);
		int firstVS=firstName.compareTo(f0);
		if(lastVS>0) {return 1;}
		else if(lastVS<0) {return -1;}
		else {
			if(firstVS>0) {return 1;}
			else if(firstVS<0) {return -1;}
			else {return 0;}
		}
	}
}