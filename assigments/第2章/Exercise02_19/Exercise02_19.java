import java.util.Scanner;

public class Exercise02_19 {
	
	public static double s(double p1x,double p1y,double p2x,double p2y) {
		return Math.pow((Math.pow((p2x-p1x), 2)+Math.pow((p2y-p1y),2)),0.5);
	}
	public static void main(String[] args) {
		System.out.println("Enter three points for a triangle: ");
		double[] p=new double[6];
		Scanner input=new Scanner(System.in);
		for(int i=0;i<6;i++) {p[i]=input.nextDouble();}
		double s1=s(p[0],p[1],p[2],p[3]),s2=s(p[2],p[3],p[4],p[5]),s3=s(p[4],p[5],p[0],p[1]),s=0.5*(s1+s2+s3);
		double ans=Math.pow(s*(s-s1)*(s-s2)*(s-s3), 0.5);
		System.out.printf("The area of the triangle is %-10.1f", ans);
		input.close();
	}

}
