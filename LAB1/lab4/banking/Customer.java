package banking;

public class Customer{
private String firstName;
private String lastName;
private Account account;

public Customer(String f,String l){firstName=f;lastName=l;}
public Customer(){firstName="NULL";lastName="NULL";}
public String getFirstName(){return firstName;}
public String getLastName(){return lastName;}
public void setFirstName(String f){firstName=f;}
public void setLastName(String l){lastName=l;}
public Account getAccount(){return account;}
public void setAccount(Account acct){account=acct;}
}