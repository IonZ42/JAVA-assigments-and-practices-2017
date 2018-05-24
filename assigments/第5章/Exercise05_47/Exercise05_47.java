import java.util.Scanner;

public class Exercise05_47 {
	
	public static void main(String[] args) {
		System.out.println("Enter the first 12 digits of an ISBN-13 as a string: ");
		Scanner input=new Scanner(System.in);
		String ori=input.nextLine();
		if(ori.length()!=12) {System.out.println(ori+" is an invalid input");}
		else{
			int sum=0;
			for(int i=0;i<ori.length();i++) {
				if(i%2==1) {sum+=Integer.parseInt(""+ori.charAt(i))*3;}
				else {sum+=Integer.parseInt(""+ori.charAt(i));}
			}
			int thirteen=10-sum%10;if(thirteen==10) {thirteen=0;}
			System.out.println("The ISBN-13 number is "+ori+(thirteen+""));
			}
		input.close();
	}

}
