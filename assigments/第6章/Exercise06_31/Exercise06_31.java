import java.util.Scanner;

public class Exercise06_31 {
	
	public static int getDigit(int d) {
		if(d<10) {return d;}
		return 1+d%10;
	}
	public static int sumOfOddPlace(String a) {
		int sum=0;
		for(int i=0;i<a.length();i+=2) {
			sum+=getDigit(2*Integer.parseInt(""+a.charAt(i)));
		}
		return sum;
	}
	public static int sumOfDoubleEvenPlace(String a) {
		int sum=0;
		for(int i=1;i<a.length();i+=2) {
			sum+=Integer.parseInt(""+a.charAt(i));
		}
		return sum;
	}
	public static String getPrefix(String a,int k) {
		return a.substring(0,k);
	}
	public static boolean prefixMatched(String a) {
		String b=getPrefix(a,2),c=getPrefix(a,1);
		if(b.equals("37")) {return true;}
		if(c.equals("4")||c.equals("5")||c.equals("6")) {return true;}
		else{return false;}
	}
	public static boolean isValid(String a) {
		if(a.length()<13||a.length()>16) {return false;}
		if(!prefixMatched(a)) {return false;}
		int b=sumOfDoubleEvenPlace(a);
		int c=sumOfOddPlace(a);
		if((b+c)%10==0) {return true;}
		return false;
	}
	public static void main(String[] args) {
		System.out.println("Enter a credit card number as a string: ");
		Scanner input=new Scanner(System.in);
		String a=input.next();
		if(isValid(a)) {System.out.println((a+"")+" is valid");}
		else{System.out.println((a+"")+" is invalid");}
		input.close();
	}
	
}
