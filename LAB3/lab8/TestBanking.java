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

    System.out.println("\n【Customers report is in step8output.txt】\n");
    File inputf=new File("step8input.txt");
    if(inputf.exists()) {
    	try(
    			Scanner in=new Scanner(inputf);
        		)
    	{
        	ArrayList<String> ac=new ArrayList<String>();
        	int acnum=0;
        	in.useDelimiter("\n|;");
        	while(in.hasNextLine()) {
        		String line=in.nextLine();
        		ac.add(line);acnum++;
        		}
        	for(int i=1;i<acnum;i++) {//第一行跳过
        		Scanner si=new Scanner(ac.get(i));
        		si.useDelimiter(" |,|;");
        		String l=si.next();String f=si.next();
        		if(!bank.hasCustomer(f,l)) {bank.addCustomer(f,l);}
        		si.useDelimiter(",|;");
        		double blc=0;double irate=0;double prt=0;String f0="",l0="";char sharetype=' ';
        		int type=0;//type 1 saveAccount 2 check(withdraw)Account 3 share account
        		while(si.hasNext()) {
        			String t=si.next();
        			String[] tokens=t.split(" ");
        			switch(tokens[0]) {
        			case "s":type=1;if(tokens[2].contentEquals("i")) {
        				blc=Double.parseDouble(tokens[1]);irate=Double.parseDouble(tokens[3]);}
        			    else{type=3;l0=tokens[2];f0=tokens[3];sharetype='s';}break;//share account
        			case "c":type=2;
        				if(tokens.length>2) {
        					if(tokens[2].contentEquals("o")) {
	        				blc=Double.parseDouble(tokens[1]);prt=Double.parseDouble(tokens[3]);}
	        			    else{type=3;l0=tokens[2];f0=tokens[3];sharetype='c';}}
        			    else blc=Double.parseDouble(tokens[1]);break;//share account
        			default:;}
    			    if(type==1) {bank.getCustomer(f,l).setSavingsAccount(new SavingsAccount(blc,irate));}
    				else if(type==2) {
    					if(prt==0) {bank.getCustomer(f,l).setCheckingAccount(new CheckingAccount(blc));}
    					else{bank.getCustomer(f,l).setCheckingAccount(new CheckingAccount(blc,prt));}}
    				else if(type==3) {
    					if(!bank.hasCustomer(f0,l0)) {bank.addCustomer(f0,l0);}
    				    bank.getCustomer(f, l).shareType=sharetype;
    				    bank.getCustomer(f, l).shareFromFN=f0;
			            bank.getCustomer(f, l).shareFromLN=l0;
    				    if(sharetype=='s') {if(bank.getCustomer(f0, l0).getSavingsAccount()==null){
    				    	bank.getCustomer(f0, l0).setSavingsAccount(new SavingsAccount(0,0));}
    				        System.out.println(f+"shares her/his Savings Account with "+f0+" "+l0);}
    				    else {if(bank.getCustomer(f0, l0).getCheckingAccount()==null) 
            				{bank.getCustomer(f0, l0).setCheckingAccount(new CheckingAccount());}
    				        System.out.println(f+" shares her/his Checking Account with "+f0+" "+l0);}
    				    }
    			    }
        		    si.close();
        	}
            
        }
    }
    else {throw new FileNotFoundException();} 
    CustomerReport cr=new CustomerReport();
    cr.generateReport();
   }
}