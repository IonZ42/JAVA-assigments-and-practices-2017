package banking.domain;

import java.util.ArrayList;

public class Customer implements Comparable<Customer>{
	private String firstName;
	private String lastName;
	private ArrayList<Account> accounts;
	protected int customerID=nextCustomerID;
	static int nextCustomerID=0;
	
	public String shareFromFN="",shareFromLN="";
	public char shareType=' ';//if it's not blank,then shared from others

	public Customer(String f,String l){firstName=f;lastName=l;accounts=new ArrayList<Account>();nextCustomerID++;}
	public Customer(){firstName="";lastName="";accounts=new ArrayList<Account>();nextCustomerID++;}
	public void setFirstName(String f){firstName=f;}
	public String getFirstName(){return firstName;}
	public void setLastName(String l){lastName=l;}
	public String getLastName(){return lastName;}
	public void setCustomerID(int id) {customerID=id;}
	public int getCustomerID() {return customerID;}
	public ArrayList<Account> getAccounts(){return accounts;}
	
	public void setSavingsAccount(SavingsAccount sat){//��Ӧs type��add��ť
		accounts.add(sat);
		Bank.getBank().transactions.add(new Transaction("SavingsAccount",sat.accountID, "add", sat.balance, true, ""));}//add����������βID��Ϊl-1
	public SavingsAccount getSavingsAccount(){
		for(Account a:accounts) {
			if (a instanceof SavingsAccount) {return (SavingsAccount) a;}}//return first ���˻�
		setSavingsAccount(new SavingsAccount());
		return (SavingsAccount) accounts.get(accounts.size()-1);}//else return new and last ���˻�
	
	public void setCheckingAccount(CheckingAccount cat){//��Ӧc type��add��ť
		accounts.add(cat);
		Bank.getBank().transactions.add(new Transaction("CheckingAccount",cat.accountID, "add", cat.balance, true, ""));}
	public CheckingAccount getCheckingAccount(){
		for(Account a:accounts) {
			if (a instanceof CheckingAccount) {return (CheckingAccount) a;}}//return first ȡ�˻�
		setCheckingAccount(new CheckingAccount());
		return (CheckingAccount) accounts.get(accounts.size()-1);}//else return new and last ȡ�˻�
	
	public Account getFirstAccount() {return accounts.get(0);}
	public Account getMaxAccount(){
		double max=0;
		for(Account a:accounts) {
			if (a.getBalance()>max) {max=a.getBalance();}}
		for(Account a:accounts) {
			if (a.getBalance()==max) {return a;}}//return �������˻�
		setCheckingAccount(new CheckingAccount());//���˻����½�һ���յ�CA
		return (CheckingAccount) accounts.get(accounts.size()-1);}//else return new and last �˻�balance0
	public Account getAccount(int ID){
		for(Account a:accounts) {if (a.accountID==ID) {return a;}}
		return null;}//�Ҳ����򷵻�null����ʱ�ǿռ��
	
	public int getAccountIndex(int ID){
		for(int i=0;i<accounts.size();i++) {if(accounts.get(i).accountID==ID) {return i;}}
		return -1;}//�Ҳ����򷵻�-1
	public void deleteAccount(int ID) {//��ӦID��delete��ť
		int i=getAccountIndex(ID);
		if(i>=0) {Bank.getBank().transactions.add(new Transaction(accounts.get(i).getClass().getName(),ID,"delete",0,true,""));
			accounts.remove(i);}
		else{System.out.println("can't find account");
			Bank.getBank().transactions.add(new Transaction(accounts.get(i).getClass().getName(),ID,"delete",0,false,"wrong ID"));}}
	
	public ArrayList<Account> searchAboveAccounts(double blclimit) {
		ArrayList<Account> temp=new ArrayList<Account>();
		for(Account c:accounts) {
			 if(c.balance>blclimit) {temp.add(c);}}
		return temp;
	}
	public int[] searchAboveResult(double blclimit) {//��Ӧ����balance limit��search��ť
		int[] temp=new int[accounts.size()];//��¼��Ч�Ĳ�ѯ�����ID����
		for(int i=0;i<temp.length;i++) {temp[i]=-1;}//��Ч��ID���>=0
		int index=0;
		for(Account a:searchAboveAccounts(blclimit)) {temp[index++]=a.accountID;}
		int[] temp2=new int[index];
		for(int i=0;i<temp2.length;i++) {temp2[i]=temp[i];}
		return temp2;
	}
	public ArrayList<Account> searchBelowAccounts(double blclimit) {
		ArrayList<Account> temp=new ArrayList<Account>();
		for(Account c:accounts) {
			 if(c.balance<blclimit) {temp.add(c);}}
		return temp;
	}
	public int[] searchBelowResult(double blclimit) {//��ӦС��balance limit��search��ť
		int[] temp=new int[accounts.size()];//��¼��Ч�Ĳ�ѯ�����ID����
		for(int i=0;i<temp.length;i++) {temp[i]=-1;}//��Ч��ID���>=0
		int index=0;
		for(Account a:searchBelowAccounts(blclimit)) {temp[index++]=a.accountID;}
		int[] temp2=new int[index];
		for(int i=0;i<temp2.length;i++) {temp2[i]=temp[i];}
		return temp2;
	}
	
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