import java.util.Scanner;

public class Exercise03_19 {
	
	public static void main(String[] args) {
		System.out.println("Enter three edges of a triangle: ");
		double[] e=new double[3];
		Scanner input=new Scanner(System.in);
		for(int i=0;i<3;i++) {
			e[i]=input.nextDouble();
			if(e[i]<=0) {System.out.println("invalid input,enter this edge again ");i-=1;}
			}
		double ans=0;
		if(e[0]+e[1]>e[2]) {if(e[1]+e[2]>e[0]) {if(e[2]+e[0]>e[1]) {ans=e[1]+e[2]+e[0];}}}
		if(ans>0) {System.out.printf("The girth of the triangle is %-10.1f",ans);}
		else {System.out.println("invalid inputs");}
		input.close();
	}

}
