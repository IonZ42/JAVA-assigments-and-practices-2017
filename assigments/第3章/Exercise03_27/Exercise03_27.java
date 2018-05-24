import java.util.Scanner;

public class Exercise03_27 {
	//points on the edge are not considered inside the triangle
	public static void main(String[] args) {
		System.out.println("Enter a point's x- and y- coordinates: ");
		double x,y;
		Scanner input=new Scanner(System.in);
		x=input.nextDouble();
		y=input.nextDouble();
		boolean isIn=false;
		if(x>0) {if(y>0) {if(x<200) {if(y<100-0.5*x) {isIn=true;}}}}
		if(isIn) {System.out.println("The point is in the triangle");}
		else {System.out.println("The point is not in the triangle");}
		input.close();
	}

}
