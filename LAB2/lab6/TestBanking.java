import java.util.*;
import java.util.Scanner;
import java.io.*;
import banking.*;

public class TestBanking {

  public static void main(String[] args) throws FileNotFoundException {
    Bank bank = new Bank();
    
    System.out.println("\n【Customers report is in step6output.txt】\n");
    File inputf=new File("step6input.txt");
    File outputf=new File("step6output.txt");
    if(inputf.exists()) {
    	try(
    			Scanner in=new Scanner(inputf);
    			PrintWriter out=new PrintWriter(outputf);
        		)
    	{
    		// 任务1：Create several customers and their accounts from step6input.txt
        	ArrayList<String> ac=new ArrayList<String>();
        	int acnum=0;
        	in.useDelimiter("\n|;");
        	while(in.hasNextLine()) {
        		String line=in.nextLine();
        		ac.add(line);acnum++;
        		//System.out.println(line+acnum);
        		}
        	for(int i=0;i<acnum;i++) {//1个人1AC1SC共两账户
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
    				    bank.getCustomer(f, l).shareFromOther=true;
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
            // 任务2：Generate a report and write into step6output.txt
        	// Determine the account type,Use the instanceof operator
        	// Print the current balance of the account
        	out.println("                 COSTOMERS REPORT                ");
        	out.println("                 ================                ");
        	for(int i=0;i<bank.getNumOfCustomers();i++) {
        		out.println("\nCustomer: "+bank.getCustomer(i).getLastName()+", "
        	            +bank.getCustomer(i).getFirstName());
        		if(bank.getCustomer(i).shareType=='c') {
        			String f0=bank.getCustomer(i).shareFromFN,l0=bank.getCustomer(i).shareFromLN;
        			out.println("     Checking account : current balance is "
	        				+bank.getCustomer(f0,l0).getCheckingAccount().getBalance());
        			out.println("     Savings account : current balance is "
	        				+bank.getCustomer(i).getSavingsAccount().getBalance());
        		}
        		else if(bank.getCustomer(i).shareType=='s') {
        			String f0=bank.getCustomer(i).shareFromFN,l0=bank.getCustomer(i).shareFromLN;
        			out.println("     Checking account : current balance is "
	        				+bank.getCustomer(i).getCheckingAccount().getBalance());
        			out.println("     CheckinSavingsg account : current balance is "
	        				+bank.getCustomer(f0,l0).getSavingsAccount().getBalance());
        		}
        		else {
	        		out.println("     Checking account : current balance is "
	        				+bank.getCustomer(i).getCheckingAccount().getBalance());
	        		out.println("     Savings account : current balance is "
	        				+bank.getCustomer(i).getSavingsAccount().getBalance());
        		}
        	}
        }
    }
    else {throw new FileNotFoundException();} 
   }
}