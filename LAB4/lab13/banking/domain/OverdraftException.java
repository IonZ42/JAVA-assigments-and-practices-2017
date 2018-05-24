package banking.domain;

public class OverdraftException extends Exception {
	private double deficit;
	
	public OverdraftException(String message,double Deficit) {
		super(message);deficit=Deficit;
	}
	public double getDeficit() {return deficit;}
}
