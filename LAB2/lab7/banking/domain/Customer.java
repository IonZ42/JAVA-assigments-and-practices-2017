package banking.domain;

public class Customer{
	private String firstName;
	private String lastName;
	private SavingsAccount sa;
	private CheckingAccount ca;
	
	public boolean singleAccountAndIsSA=true;//false means chaecking account
	public boolean shareFromOther=false;
	public String shareFromFN="",shareFromLN="";
	public char shareType=' ';

	public Customer(String f,String l){firstName=f;lastName=l;ca=new CheckingAccount();sa=new SavingsAccount();}
	public Customer(){firstName="";lastName="";ca=new CheckingAccount();sa=new SavingsAccount();}
	public String getFirstName(){return firstName;}
	public String getLastName(){return lastName;}
	public void setFirstName(String f){firstName=f;}
	public void setLastName(String l){lastName=l;}
	public SavingsAccount getSavingsAccount(){return sa;}
	public void setSavingsAccount(SavingsAccount sat){sa=sat;}
	public CheckingAccount getCheckingAccount(){return ca;}
	public void setCheckingAccount(CheckingAccount cat){ca=cat;}
}