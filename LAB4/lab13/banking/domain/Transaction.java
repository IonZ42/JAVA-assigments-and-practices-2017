package banking.domain;

import java.util.*;

public class Transaction {
	private long time=System.currentTimeMillis();
	private Date date= new Date(time);
	private String className;
	private int id;
	private String operation;//add/update/delete for customer;add/deposit/withdraw/delete for account
	private double amount;//deposit+,withdraw-
	private boolean isOK;//operation result
	private String description;
	
	public Transaction(String cn,int id0,String op,double amt,boolean isok,String des) {
		className=cn;id=id0;operation=op;amount=amt;isOK=isok;description=des;
		String.format("%20s", className);String.format("%8s", operation);String.format("%20s", description);
	}
	public Transaction(Date dt,String cn,int id0,String op,double amt,boolean isok,String des) {
		date=dt;time=date.getTime();
		className=cn;id=id0;operation=op;amount=amt;isOK=isok;description=des;
		String.format("%20s", className);String.format("%8s", operation);String.format("%20s", description);
	}
	public Transaction() {String.format("%20s", className);String.format("%8s", operation);String.format("%20s", description);}
	public long getTime() {return time;}
	public Date getDateCreadted() {return date;}
	public void setClassName(String cn) {className=String.format("%20s", cn);}
	public String getClaaName() {return className;}
	public void setID(int id0) {id=id0;}//此操作比较危险
	public int getID() {return id;}
	public void setOperation(String op) {operation=String.format("%8s", op);}
	public String getOperation() {return operation;}
	public void setAmount(double amt) {amount=amt;}
	public double getAmount(){return amount;}
	public void setBalance(boolean isok) {isOK=isok;}
	public boolean getBalance(){return isOK;}
	public void setDescription(String d) {description=String.format("%20s", d);}
	public String getDescription() {return description;}
}
