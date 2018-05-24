public class Exercise09_07 {

	public static void main(String[] args) {
		Account test=new Account(1122,20000);
		System.out.println("Create test account with id=1122 and balance 20000 dollars");
		test.withdraw(2500);
		test.deposit(3000);
		System.out.println("now the balance is "+test.getBalance()+"dollars");
		System.out.println("mothly interest "+test.getMothlyInterest()+"dollars");
		System.out.println("the date of account creation is "+test.getDateCreadted());
	}

}
