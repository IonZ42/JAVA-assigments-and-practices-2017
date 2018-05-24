import java.util.Scanner;

public class Exercise02_06 {
	
	public static void main(String[] args) {
		System.out.println("Enter a number between 0 and 1000: ");
		Scanner input=new Scanner(System.in);
		int num=input.nextInt();
		if(num<=0||num>=1000) {System.out.println("invalid input!try again");num=input.nextInt();}
		int a1=num%10;
		num-=a1;
		int a2=(num%100)/10;
		num-=10*a2;
		int a3=(num%1000)/100;
		int sum=a1+a2+a3;
		System.out.println("The sum of the digits is "+sum);
		input.close();
	}

}
