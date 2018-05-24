package banking.domain;

public class Bank{
	private Customer[] customers;
	private int numberOfCustomers=0;//keeps track of the next customers array index
	private static Bank bank=null;//在此private场合privately无参构造

	public static Bank getBank() {
		if(bank==null) {bank=new Bank();}
		return bank;}
	private Bank(){customers=new Customer[50];numberOfCustomers=0;}
	public void addCustomer(String f,String l){
		System.out.println("Creating the customer "+f+" "+l+" .");
		customers[numberOfCustomers++]=new Customer(f,l);}
	public int getNumOfCustomers(){return numberOfCustomers;}
	public Customer getCustomer(int index){return customers[index];}
	public Customer getCustomer(String f,String l){
		int target=-1;
		for(int i=0;i<numberOfCustomers;i++){
			if(customers[i].getFirstName().contentEquals(f)){
				if(customers[i].getLastName().contentEquals(l)){target=i;}}}
		if(target==-1) {return null;}
		else return customers[target];
		}
	public boolean hasCustomer(String f,String l) {if(getCustomer(f,l)!=null) {return true;}else return false;}
}