package banking.domain;

import java.util.ArrayList;
import java.util.Collections;

public class Bank{
	private ArrayList<Customer> customers;
	public ArrayList<Transaction> transactions;
	private static Bank bank=null;//�ڴ�private����privately�޲ι���

	public static Bank getBank() {
		if(bank==null) {bank=new Bank();}
		return bank;}
	private Bank(){customers=new ArrayList<Customer>();transactions=new ArrayList<Transaction>();}
	
	public void addCustomer(String f,String l){//��Ӧf+l��add��ť
		System.out.println("Creating the customer "+f+" "+l+" .");
		customers.add(new Customer(f,l));
		transactions.add(new Transaction("Customer",getCustomer(f,l).customerID,"add",0,true,"FN:"+f+" LN:"+l));}
	public int getNumOfCustomers(){return customers.size();}
	
	public Customer getCustomer(int ID){
		for(Customer c:customers) {if(c.customerID==ID) {return c;}}
		return null;}//�Ҳ����򷵻�null����ǰ�Ⱦ���hasCustomer���
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
		return -1;}//�Ҳ����򷵻�-1
	public int getCustomerIndex(String f,String l){
		for(int i=0;i<customers.size();i++) {
			if(customers.get(i).getFirstName().equalsIgnoreCase(f)&&customers.get(i).getLastName().equalsIgnoreCase(l)) {return i;}}
		return -1;}//�Ҳ����򷵻�-1
	
	public void sortCustomers() {Collections.sort(customers);}
	
	public Customer searchCustomers(String f,String l) {return getCustomer(f,l);}//����ָ���������û�
	public ArrayList<Customer> searchCustomers(double blclimit) {//���������ڡ��������û�
		ArrayList<Customer> temp=new ArrayList<Customer>();
		for(Customer c:customers) {
			 if(c.getMaxAccount().getBalance()>blclimit) {temp.add(c);}}
		return temp;
	    }
	public ArrayList<Customer> searchCustomers(String keyword) {//����֧�ֵ��ؼ���������
		int keywordsize=keyword.length();//����ģ�����������FN/LN��ÿ�����ϳ��ȵ��������ַ���
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
	public int[] searchResult(String keyword) {//��Ӧkeyword��search��ť
		int[] temp=new int[customers.size()];//��¼��Ч�Ĳ�ѯ�����ID����
		for(int i=0;i<temp.length;i++) {temp[i]=-1;}//��Ч��ID���>=0
		int index=0;//����Indexֵ��Ԫ����
		for(Customer c:searchCustomers(keyword)) {temp[index++]=c.customerID;}
		int[] temp2=new int[index];
		for(int i=0;i<temp2.length;i++) {temp2[i]=temp[i];}
		return temp2;
	}
	
	//ɾ�����û�ԭID��Ȼ������Ĺ�������������û�ȡ��
	public void deleteCustomer(String f,String l) {//��Ӧf+l��delete��ť
		int i=getCustomerIndex(f,l);
		if(i>=0) {transactions.add(new Transaction("Customer",getCustomer(f,l).customerID,"delete",0,true,"FN:"+f+" LN:"+l));
			customers.remove(i);}
		else{System.out.println("can't find customer");
			transactions.add(new Transaction("Customer",getCustomer(f,l).customerID,"delete",0,false,"wrong name"));}}
	public void deleteCustomer(int ID) {//��ӦID��delete��ť
		int i=getCustomerIndex(ID);
		if(i>=0) {transactions.add(new Transaction("Customer",ID,"delete",0,true,""));
			customers.remove(i);}
		else{System.out.println("can't find customer");
			transactions.add(new Transaction("Customer",ID,"delete",0,false,"wrong ID"));}}
}