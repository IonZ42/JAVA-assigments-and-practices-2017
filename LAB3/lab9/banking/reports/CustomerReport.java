package banking.reports;

import java.io.*;

import banking.domain.*;

public class CustomerReport {

  public CustomerReport() {}
  public void generateReport() throws FileNotFoundException {
    
    Bank bank=Bank.getBank();
    File outputf=new File("step9output.txt");
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
		}
    }
  }

}
