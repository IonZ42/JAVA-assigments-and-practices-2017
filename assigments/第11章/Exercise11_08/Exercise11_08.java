public class Exercise11_08 {
	public static void main(String[] args) {
		Account test=new Account("Geogre",1122,1000);
		System.out.println("Construct a test account : name Geogre, id 1122, balance 1000, annual interest rate 1.5%.");
		test.deposit(30);
		test.deposit(40);
		test.deposit(50);
		test.withdraw(5);
		test.withdraw(4);
		test.withdraw(2);
		test.printTransactions();
	}
}
