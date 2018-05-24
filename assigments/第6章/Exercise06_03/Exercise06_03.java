import java.util.Scanner;

public class Exercise06_03 {
	
	public static String reverse(String t) {
		String r="";
		for(int i=t.length()-1;i>=0;i--) {
			r+=t.charAt(i);
		}
		return r;
	}
	public static int reverse(int a) {
		String t=a+"";
		return Integer.parseInt(reverse(t));
	}
	public static boolean isPalindrome(int a) {
		int b=reverse(a);
		if(a==b) {return true;}
		else {return false;}
	}
	public static void main(String[] args) {
		System.out.println("Enter an integer to see whether it is a palindrome: ");
		Scanner input=new Scanner(System.in);
		int a=input.nextInt();
		if(isPalindrome(a)) {System.out.println((a+"")+" is a palindrome");}
		else{System.out.println((a+"")+" is not a palindrome");}
		input.close();
	}

}
