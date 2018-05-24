package banking.domain;

import java.util.ArrayList;

public class Bank{
	private ArrayList<Customer> customers;
	private static Bank bank=null;//在此private场合privately无参构造

	public static Bank getBank() {
		if(bank==null) {bank=new Bank();}
		return bank;}
	private Bank(){customers=new ArrayList<Customer>();}
	public void addCustomer(String f,String l){
		System.out.println("Creating the customer "+f+" "+l+" .");
		customers.add(new Customer(f,l));}
	public int getNumOfCustomers(){return customers.size();}
	public Customer getCustomer(int index){return customers.get(index);}
	public Customer getCustomer(String f,String l){
		int target=-1;
		for(int i=0;i<customers.size();i++){
			if(customers.get(i).getFirstName().contentEquals(f)){
				if(customers.get(i).getLastName().contentEquals(l)){target=i;}}}
		if(target==-1) {return null;}
		else return customers.get(target);
		}
	public boolean hasCustomer(String f,String l) {if(getCustomer(f,l)!=null) {return true;}else return false;}
}