
public class Exercise12_05 {

	public static void main(String[] args) {
		try {
			Triangle t1=new Triangle(3,4,5),t2=new Triangle (-3,-4,-5);
		} catch (IllegalTriangleException e) {
			System.out.println("IllegalTriangleException: "+e.getMessage());
			System.exit(0);
		}
	}

}

class IllegalTriangleException extends Exception{
	public IllegalTriangleException(String message) {super(message);}
}


class Triangle {
	private double side1,side2,side3;
	
	Triangle(double s1,double s2,double s3) throws IllegalTriangleException {
		System.out.println("constructing a new triangle with "+s1+" , "+s2+" , "+s3+" sides.");
		if(s1+s2>s3&&s1+s3>s2&&s2+s3>s1) {side1=s1;side2=s2;side3=s3;System.out.println("successfully constructed.");}
		else throw new IllegalTriangleException("Illegal triangle, please check your input!");
	}
}