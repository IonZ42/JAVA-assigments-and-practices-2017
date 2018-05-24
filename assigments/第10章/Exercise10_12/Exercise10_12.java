public class Exercise10_12 {
	public static void main(String[] args) {
		Triangle2D t1=new Triangle2D(new MyPoint(2.5,2),new MyPoint(4.2,3),new MyPoint(5,3.5));
		System.out.println("test triangle t1, its area is "+t1.getArea());
		System.out.println("its perimeter is "+t1.getPerimeter());
		System.out.println("is point (3,3) in triangle t1? : "+t1.contains(new MyPoint(3,3)));
		System.out.println("is triangle (2.9,2),(4,1),(1,3.4) in triagnle t1? : "
		+t1.contains(new Triangle2D(new MyPoint(2.9,2),new MyPoint(4,1),new MyPoint(1,3.4))));
		System.out.println("is triangle (2,5.5),(4,-3),(2,6.5) overlaps triagnle t1? : "
		+t1.overlaps(new Triangle2D(new MyPoint(2,5.5),new MyPoint(4,-3),new MyPoint(2,6.5))));
	}
}
