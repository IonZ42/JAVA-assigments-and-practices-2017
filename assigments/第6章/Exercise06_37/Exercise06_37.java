import java.util.Scanner;

public class Exercise06_37 {

	public static String format(int num,int width) {
		String n=num+"";
		int d=width-n.length();
		for(int i=0;i<d;i++) {n="0"+n;}
		return n;
	}
	public static void main(String[] args) {
		System.out.println("Enter the number: ");
		Scanner input=new Scanner(System.in);
		int num=input.nextInt();
		System.out.println("Enter the format width: ");
		int width=input.nextInt();
		System.out.println("The fixed number is: "+format(num,width));
		input.close();
	}
}
