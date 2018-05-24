package banking;

public class Bank{
private Customer[] customers;
private int numberOfCustomers=0;//keeps track of the next customers array index

public Bank(){customers=new Customer[8];numberOfCustomers=0;}
public void addCustomer(String f,String l){customers[numberOfCustomers++]=new Customer(f,l);}
public int getNumOfCustomers(){return numberOfCustomers;}
public Customer getCustomer(int index){return customers[index];}
}