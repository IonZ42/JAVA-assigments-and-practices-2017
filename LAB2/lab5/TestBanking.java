/*
 * This class creates the program to test the banking classes.
 * It creates a new Bank, sets the Customer (with an initial balance),
 * and performs a series of transactions with the Account object.
 */
import java.util.*;
import java.util.Scanner;
import java.io.*;
import banking.*;

public class TestBanking {

  public static void main(String[] args) throws FileNotFoundException {
    Bank bank = new Bank();
    
    System.out.println("\n【check the out put in step5output.txt !!】\n");
    File inputf=new File("step5input.txt");
    File outputf=new File("step5output.txt");
    FileOutputStream fos=new FileOutputStream(outputf);
    PrintStream ps=new PrintStream(fos);
    System.setOut(ps);
    if(inputf.exists()) {
    	try(
    			Scanner in=new Scanner(inputf);
    			//PrintWriter out=new PrintWriter(outputf);
        		)
    	{
    	    // 任务1：Create bank customers and their accounts from account section of step5input.txt
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
        		//System.out.println(line+acnum+" "+trnum);
        		}
        	for(int i=1;i<=acnum;i++) {//同一用户可有1c1s两账户，两账户balance不共通，i=1略过account句
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
        					bank.getCustomer(f,l).setSavingsAccount(new SavingsAccount(blc,irate));
        					bank.getCustomer(f,l).singleAccountAndIsSA=true;}
        				else if(type==2) {
        					if(prt==0) {bank.getCustomer(f,l).setCheckingAccount(new CheckingAccount(blc));}
        					else{bank.getCustomer(f,l).setCheckingAccount(new CheckingAccount(blc,prt));}
        					bank.getCustomer(f,l).singleAccountAndIsSA=false;
        					}
        				else if(type==3) {
        					if(!bank.hasCustomer(f0,l0)) {bank.addCustomer(f0,l0);}
        				    bank.getCustomer(f, l).shareFromOther=true;
        				    bank.getCustomer(f, l).shareType=sharetype;
        				    bank.getCustomer(f, l).shareFromFN=f0;
				            bank.getCustomer(f, l).shareFromLN=l0;
        				    if(sharetype=='s') {if(bank.getCustomer(f0, l0).getSavingsAccount()==null){
        				    	bank.getCustomer(f0, l0).setSavingsAccount(new SavingsAccount(0,0));}
        				        System.out.println(f+"shares her/his Savings Account with "+f0+" "+l0);
        				        bank.getCustomer(f,l).singleAccountAndIsSA=true;}
        				    else {if(bank.getCustomer(f0, l0).getCheckingAccount()==null) //必为checkingAccount
                				{bank.getCustomer(f0, l0).setCheckingAccount(new CheckingAccount());}
        				        System.out.println(f+" shares her/his Checking Account with "+f0+" "+l0);
        				        }
        				        bank.getCustomer(f,l).singleAccountAndIsSA=false;}
        			break;}
        		}//不做查用户名重复和性别处理了
        		si.close();
        	}
        	//任务2：Demonstrate behavior of various account types according to transactions section of step5input.txt 
        	for(int i=1;i<=trnum;i++) {//i=1略过transaction句
        		Scanner si=new Scanner(tr.get(i));
        		si.useDelimiter(" |,|;");
        		String f=si.next();
    		    String l=si.next();
    		    if(!bank.hasCustomer(f,l)) {bank.addCustomer(f,l);}
    		    System.out.println("");
		    	if(bank.getCustomer(f, l).singleAccountAndIsSA)
		    		{System.out.println("Retrieving the customer "
		    			+f+" "+l+" with his/her savings account.");
		    		}
		    	else {
		    		System.out.println("Retrieving the customer "
		    			+f+" "+l+" with his/her chaecking account.");
		    		}
    		    double amt=0;
    		    int type=0;//type 1 deposit 2 withdraw
    		    while(si.hasNext()) {
    		    	switch(si.next()) {
    		    	case "d":amt=si.nextDouble();type=1;break;//deposit
    		    	case "w":amt=si.nextDouble();type=2;break;//withdraw
    		    	default:;}
    		    	if(bank.getCustomer(f, l).shareFromOther) {
    		    		String f0=bank.getCustomer(f, l).shareFromFN,l0=bank.getCustomer(f, l).shareFromLN;
    		    		if(type==1) {
    		    			if(bank.getCustomer(f, l).shareType=='s')
    		    			{bank.getCustomer(f0,l0).getSavingsAccount().deposit(amt);}
    		    			else bank.getCustomer(f0, l0).getCheckingAccount().deposit(amt);}
    		    		else if(type==2) {
    		    			if(bank.getCustomer(f, l).shareType=='s')
    		    			{bank.getCustomer(f0,l0).getSavingsAccount().withdraw(amt);}
    		    			else bank.getCustomer(f0,l0).getCheckingAccount().withdraw(amt);}
    		    	}
    		    	else {
    		    		if(type==1) {
    		    			if(bank.getCustomer(f, l).singleAccountAndIsSA)
    		    			{bank.getCustomer(f,l).getSavingsAccount().deposit(amt);}
    		    			else bank.getCustomer(f, l).getCheckingAccount().deposit(amt);}
    		    		else if(type==2) {
    		    			if(bank.getCustomer(f, l).singleAccountAndIsSA)
    		    			{bank.getCustomer(f,l).getSavingsAccount().withdraw(amt);}
    		    			else bank.getCustomer(f,l).getCheckingAccount().withdraw(amt);}
    		    	}
        		}
    		    if(f.contentEquals("Tim")) {
    		    	System.out.println("Now the overdraft protection of checking account is "
    		        +bank.getCustomer(f, l).getCheckingAccount().getOverdraftProtection());
    		    	}
    		    if(bank.getCustomer(f, l).shareFromOther) {
    				String f0=bank.getCustomer(f, l).shareFromFN,l0=bank.getCustomer(f, l).shareFromLN;
    				if(bank.getCustomer(f, l).shareType=='s')
    				{System.out.println("Cusotmer ["+l+", "+f+"] has a balance of "
    				+bank.getCustomer(f0,l0).getSavingsAccount().getBalance());
    				}
    				else System.out.println("Cusotmer ["+l+", "+f+"] has a balance of "
    				+bank.getCustomer(f0,l0).getCheckingAccount().getBalance());
    				}
    			else {
    				if(bank.getCustomer(f, l).singleAccountAndIsSA)
    				{System.out.println("Cusotmer ["+l+", "+f+"] has a balance of "
    				+bank.getCustomer(f, l).getSavingsAccount().getBalance());
    				}
    				else System.out.println("Cusotmer ["+l+", "+f+"] has a balance of "
    				+bank.getCustomer(f, l).getCheckingAccount().getBalance());
    				}
    		    si.close();
    		}
        	//任务3：将上述控制台输出写入step5output.txt
                //实现方法：更改System.setOut为输出到文件而非控制台，见开始几行
    	}
    }
    else {//System.setOut(System.out);
        System.out.println("\nstep5input.txt is not exsist !!\n");
        throw new FileNotFoundException();}
  }
  
}