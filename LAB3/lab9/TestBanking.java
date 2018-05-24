/*
 * This class creates the program to test the banking classes.
 * It creates a set of customers, with a few accounts each,
 * and generates a report of current account balances.
 */
import java.util.*;
import java.util.Scanner;
import java.io.*;
import banking.domain.*;
import banking.reports.*;

public class TestBanking {

  public static void main(String[] args) throws FileNotFoundException {
    Bank bank = Bank.getBank();
    
    System.out.println("\n【check the out put in step9output.txt !!】\n");
    File inputf=new File("step9input.txt");
    File outputf=new File("step9output.txt");
    FileOutputStream fos=new FileOutputStream(outputf);
    PrintStream ps=new PrintStream(fos);
    System.setOut(ps);
    if(inputf.exists()) {
    	try(
    			Scanner in=new Scanner(inputf);
        		)
    	{
    	    // 任务1：Create bank customers and their accounts from account section of step9input.txt
        	ArrayList<String> ac=new ArrayList<String>();
        	int acnum=0;boolean acZone=false;
        	ArrayList<String> tr=new ArrayList<String>();
        	int trnum=0;boolean trZone=false;
        	in.useDelimiter("\n|;");
        	while(in.hasNextLine()) {
        		String line=in.nextLine();
        		if(line.contentEquals("")) {acZone=false;}
        		else if(line.contentEquals("account")) {acZone=true;acnum=-1;}
        		else if(line.contentEquals("transaction")) {trZone=true;trnum=-1;}
        		if(acZone) {ac.add(line);acnum++;}
        		else if(trZone) {tr.add(line);trnum++;}
        		}
        	for(int i=1;i<=acnum;i++) {//i=1略过account句
        		Scanner si=new Scanner(ac.get(i));
        		si.useDelimiter(" |,|;");
        		String f=si.next();String l=si.next();
        		if(!bank.hasCustomer(f,l)) {bank.addCustomer(f,l);}
        		double blc=0;double irate=0;double prt=0;String f0="",l0="";char sharetype=' ';
        		int type=0;//type 1 saveAccount 2 check(withdraw)Account 3 share account
        		while(si.hasNext()) {
        			switch(si.next()) {
        			case "s":type=1;if(si.hasNextDouble()) {blc=si.nextDouble();}
        			    else{type=3;si.next();f0=si.next();l0=si.next();sharetype='s';}break;//share account
        			case "c":type=2;if(si.hasNextDouble()) {blc=si.nextDouble();}
        			    else{type=3;si.next();f0=si.next();l0=si.next();sharetype='c';}break;//share account
        			case "i":irate=si.nextDouble();break;//interest rate,when show it,*100!
        			case "o":prt=si.nextDouble();break;//overdraftProtection
        			default:;}
        			if(!si.hasNext()) {
        				if(type==1) {
        					bank.getCustomer(f,l).setSavingsAccount(new SavingsAccount(blc,irate));}
        				else if(type==2) {
        					if(prt==0) {bank.getCustomer(f,l).setCheckingAccount(new CheckingAccount(blc));}
        					else{bank.getCustomer(f,l).setCheckingAccount(new CheckingAccount(blc,prt));}
        					}
        				else if(type==3) {
        					if(!bank.hasCustomer(f0,l0)) {bank.addCustomer(f0,l0);}
        				    bank.getCustomer(f, l).shareType=sharetype;
        				    bank.getCustomer(f, l).shareFromFN=f0;
				            bank.getCustomer(f, l).shareFromLN=l0;
        				    if(sharetype=='s') {if(bank.getCustomer(f0, l0).getSavingsAccount()==null){
        				    	bank.getCustomer(f0, l0).setSavingsAccount(new SavingsAccount(0,0));}
        				        System.out.println(f+"shares her/his Savings Account with "+f0+" "+l0);}
        				    else {if(bank.getCustomer(f0, l0).getCheckingAccount()==null) //必为checkingAccount
                				{bank.getCustomer(f0, l0).setCheckingAccount(new CheckingAccount());}
        				        System.out.println(f+" shares her/his Checking Account with "+f0+" "+l0);
        				        }}
        			break;}
        		}//不做查用户名重复和性别处理了
        		si.close();
        	}
        	//任务2：Demonstrate behavior of various account types according to transactions section of step9input.txt 
        	for(int i=1;i<=trnum;i++) {//i=1略过transaction句
        		Scanner si=new Scanner(tr.get(i));
        		si.useDelimiter(" |,|;");
        		String f=si.next();
    		    String l=si.next();
    		    if(!bank.hasCustomer(f,l)) {bank.addCustomer(f,l);}
    		    System.out.println("");
    		    Account act=bank.getCustomer(f, l).getFirstAccount();//实际上测试用例取的都是第一个账户存取款
		    	if(act instanceof SavingsAccount)
		    		{System.out.println("Customer ["+l+", "+f+"] has a savings balance of "+act.getBalance()
		    		+" with a "+((SavingsAccount) act).getInterestRate()*100+"% interest rate.");
		    		}
		    	else {System.out.println("Customer ["+l+", "+f+"] has a checking balance of "+act.getBalance()
		    	+" with a "+((CheckingAccount) act).getOverdraftProtection()+" overdraft protection.");
	    		}
    		    double amt=0;
    		    int type=0;//type 1 deposit 2 withdraw
    		    while(si.hasNext()) {
    		    	switch(si.next()) {
    		    	case "d":amt=si.nextDouble();type=1;break;//deposit
    		    	case "w":amt=si.nextDouble();type=2;break;//withdraw
    		    	default:;}
    		    	if(bank.getCustomer(f, l).shareType!=' ') {
    		    		String f0=bank.getCustomer(f, l).shareFromFN,l0=bank.getCustomer(f, l).shareFromLN;
    		    		if(type==1) {
    		    			if(bank.getCustomer(f, l).shareType=='s')
    		    			{
        		    			System.out.print("Savings Acct ["+f0+" "+l0+"] : ");bank.getCustomer(f0,l0).getSavingsAccount().deposit(amt);}
    		    			else 
        		    			System.out.print("Checking Acct ["+f0+" "+l0+"] : ");bank.getCustomer(f0, l0).getCheckingAccount().deposit(amt);}
    		    		else if(type==2) {
    		    			if(bank.getCustomer(f, l).shareType=='s')
    		    			{
        		    			System.out.print("Savings Acct ["+f0+" "+l0+"] : ");try {
									bank.getCustomer(f0,l0).getSavingsAccount().withdraw(amt);
								} catch (OverdraftException e) {
									System.out.println("\n"+e.getMessage()+e.getDeficit());
								}}
    		    			else 
        		    			System.out.print("Checking Acct ["+f0+" "+l0+"] : ");try {
									bank.getCustomer(f0,l0).getCheckingAccount().withdraw(amt);
								} catch (OverdraftException e) {
									System.out.println("\n"+e.getMessage()+e.getDeficit());
								}}
    		    	}
    		    	else {
    		    		if(type==1) {
    		    			if(act instanceof SavingsAccount)
    		    			{
        		    			System.out.print("AAASavings Acct ["+f+" "+l+"] : ");
        		    			bank.getCustomer(f,l).getSavingsAccount().deposit(amt);continue;
        		    			}
    		    			else 
        		    			System.out.print("Checking Acct ["+f+" "+l+"] : ");bank.getCustomer(f, l).getCheckingAccount().deposit(amt);}
    		    		else if(type==2) {
    		    			if(act instanceof SavingsAccount)
    		    			{
        		    			System.out.print("BBBSavings Acct ["+f+" "+l+"] : ");
        		    			try {
									bank.getCustomer(f,l).getSavingsAccount().withdraw(amt);
								} catch (OverdraftException e) {
									System.out.println("\n"+e.getMessage()+e.getDeficit());
								}continue;
        		    			}
    		    			else 
        		    			System.out.print("Checking Acct ["+f+" "+l+"] : ");try {
									bank.getCustomer(f,l).getCheckingAccount().withdraw(amt);
								} catch (OverdraftException e) {
									System.out.println("\n"+e.getMessage()+e.getDeficit());
								}}
    		    	}
        		}
    		    if(bank.getCustomer(f, l).shareType!=' ') {
    				String f0=bank.getCustomer(f, l).shareFromFN,l0=bank.getCustomer(f, l).shareFromLN;
    				if(bank.getCustomer(f, l).shareType=='s')
    				{System.out.println("Cusotmer ["+l+", "+f+"] has a savings balance of "
    				+bank.getCustomer(f0,l0).getSavingsAccount().getBalance());
    				}
    				else System.out.println("Cusotmer ["+l+", "+f+"] has a checking balance of "
    				+bank.getCustomer(f0,l0).getCheckingAccount().getBalance());
    				}
    			else {
    				if(act instanceof SavingsAccount)
    				{System.out.println("Cusotmer ["+l+", "+f+"] has a savings balance of "
    				+bank.getCustomer(f, l).getSavingsAccount().getBalance());
    				}
    				else System.out.println("Cusotmer ["+l+", "+f+"] has a checking balance of "
    				+bank.getCustomer(f, l).getCheckingAccount().getBalance());
    				}
    		    si.close();
    		}
    	}
    }
    else {throw new FileNotFoundException();}
    //CustomerReport cr=new CustomerReport();
    //cr.generateReport();;
  }
  
}