package banking.reports;

import java.io.*;
import java.util.ArrayList;

import banking.domain.*;

public class CustomerReport {
  Bank bank=Bank.getBank();
  File outputf=new File("step10output.txt");
  File outputf2=new File("step10output2.txt");
  
  public CustomerReport() {}
  public void generateReport() throws FileNotFoundException {
    
    bank.sortCustomers();
    try(
			PrintWriter out=new PrintWriter(outputf);)
    {
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
			out.println("");
		}
    }
  }
  
  public void reportSearchResult(double blclimit) throws FileNotFoundException {
	  ArrayList<Customer> results=bank.searchCustomers(blclimit);
	  
	  try(
				PrintWriter out=new PrintWriter(outputf2);)
	    {
			out.println("                 SEARCH BALANCE REPORT                ");
			out.println("                 =====================                ");
			out.println("                 limit balance :"+blclimit+"                ");
			for(Customer c:results) {
				out.println("\nCustomer: "+c.getLastName()+", "+c.getFirstName());
				if(c.shareType=='c') {
					String f0=c.shareFromFN,l0=c.shareFromLN;
					out.println("     Checking account : current balance is "
		    				+bank.getCustomer(f0,l0).getCheckingAccount().getBalance());
					out.println("     Savings account : current balance is "
		    				+c.getSavingsAccount().getBalance());
				}
				else if(c.shareType=='s') {
					String f0=c.shareFromFN,l0=c.shareFromLN;
					out.println("     Checking account : current balance is "
		    				+c.getCheckingAccount().getBalance());
					out.println("     CheckinSavingsg account : current balance is "
		    				+bank.getCustomer(f0,l0).getSavingsAccount().getBalance());
				}
				else {
		    		out.println("     Checking account : current balance is "
		    				+c.getCheckingAccount().getBalance());
		    		out.println("     Savings account : current balance is "
		    				+c.getSavingsAccount().getBalance());
				}
				out.println("");
			}
	    }
  }

}
