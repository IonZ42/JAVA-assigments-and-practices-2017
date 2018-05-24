package banking.domain;

import java.util.ArrayList;
import java.util.Collections;

public class Bank{
	private ArrayList<Customer> customers;
	public ArrayList<Transaction> transactions;
	private static Bank bank=null;//在此private场合privately无参构造

	public static Bank getBank() {
		if(bank==null) {bank=new Bank();}
		return bank;}
	private Bank(){customers=new ArrayList<Customer>();transactions=new ArrayList<Transaction>();}
	
	public void addCustomer(String f,String l){//对应f+l的add按钮
		System.out.println("Creating the customer "+f+" "+l+" .");
		customers.add(new Customer(f,l));
		transactions.add(new Transaction("Customer",getCustomer(f,l).customerID,"add",0,true,"FN:"+f+" LN:"+l));}
	public int getNumOfCustomers(){return customers.size();}
	
	public Customer getCustomer(int ID){
		for(Customer c:customers) {if(c.customerID==ID) {return c;}}
		return null;}//找不到则返回null，用前先经过hasCustomer检查
	public Customer getCustomer(String f,String l){
		int target=-1;
		for(int i=0;i<customers.size();i++){
			if(customers.get(i).getFirstName().contentEquals(f)){
				if(customers.get(i).getLastName().contentEquals(l)){target=i;}}}
		if(target==-1) {return null;}
		else return customers.get(target);
		}
	
	public boolean hasCustomer(String f,String l) {
		if(getCustomer(f,l)!=null) {return true;}else return false;}
	
	public int getCustomerIndex(int ID){
		for(int i=0;i<customers.size();i++) {if(customers.get(i).customerID==ID) {return i;}}
		return -1;}//找不到则返回-1
	public int getCustomerIndex(String f,String l){
		for(int i=0;i<customers.size();i++) {
			if(customers.get(i).getFirstName().equalsIgnoreCase(f)&&customers.get(i).getLastName().equalsIgnoreCase(l)) {return i;}}
		return -1;}//找不到则返回-1
	
	public void sortCustomers() {Collections.sort(customers);}
	
	public Customer searchCustomers(String f,String l) {return getCustomer(f,l);}//搜索指定姓名的用户
	public ArrayList<Customer> searchCustomers(double blclimit) {//搜索余额【多于】参数的用户
		ArrayList<Customer> temp=new ArrayList<Customer>();
		for(Customer c:customers) {
			 if(c.getMaxAccount().getBalance()>blclimit) {temp.add(c);}}
		return temp;
	    }
	public ArrayList<Customer> searchCustomers(String keyword) {//【仅支持单关键字搜索】
		int keywordsize=keyword.length();//采用模糊搜索，检查FN/LN中每个符合长度的连续子字符串
		ArrayList<Customer> temp=new ArrayList<Customer>();
		for(Customer c:customers) {
			String fn=c.getFirstName();
			String ln=c.getLastName();
			int fnsize=fn.length();
			int lnsize=fn.length();
			boolean finded=false;
			for(int i=0;i<=fnsize-keywordsize;i++) {if(fn.substring(i, i+keywordsize).equalsIgnoreCase(keyword)) 
				{temp.add(c);finded=true;break;}}
			if(!finded) {
				for(int i=0;i<=lnsize-keywordsize;i++) {if(ln.substring(i, i+keywordsize).equalsIgnoreCase(keyword)) 
					{temp.add(c);finded=true;break;}}
			}
		}
		return temp;
	    }
	public int[] searchResult(String keyword) {//对应keyword的search按钮
		int[] temp=new int[customers.size()];//记录有效的查询结果（ID）们
		for(int i=0;i<temp.length;i++) {temp[i]=-1;}//有效的ID编号>=0
		int index=0;//最终Index值即元素数
		for(Customer c:searchCustomers(keyword)) {temp[index++]=c.customerID;}
		int[] temp2=new int[index];
		for(int i=0;i<temp2.length;i++) {temp2[i]=temp[i];}
		return temp2;
	}
	
	//删除后用户原ID仍然保留（墓碑），不被新用户取代
	public void deleteCustomer(String f,String l) {//对应f+l的delete按钮
		int i=getCustomerIndex(f,l);
		if(i>=0) {transactions.add(new Transaction("Customer",getCustomer(f,l).customerID,"delete",0,true,"FN:"+f+" LN:"+l));
			customers.remove(i);}
		else{System.out.println("can't find customer");
			transactions.add(new Transaction("Customer",getCustomer(f,l).customerID,"delete",0,false,"wrong name"));}}
	public void deleteCustomer(int ID) {//对应ID的delete按钮
		int i=getCustomerIndex(ID);
		if(i>=0) {transactions.add(new Transaction("Customer",ID,"delete",0,true,""));
			customers.remove(i);}
		else{System.out.println("can't find customer");
			transactions.add(new Transaction("Customer",ID,"delete",0,false,"wrong ID"));}}
}