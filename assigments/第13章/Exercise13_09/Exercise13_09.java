public class Exercise13_09 {
	public static void main(String[] args) {
		
	}
}

class Circle extends GeometricObject implements Comparable<Circle>{
	private final double pi=3.14159;
	private double radius;
	
	public Circle() {radius=1;}
	public Circle(double r) {radius=r;}
	public Circle(double r,String color,boolean filled) {super(color,filled);radius=r;}
	public double getRadius() {return radius;}
	public void setRadius(double r) {radius=r;}
	public double getDiameter() {return 2*radius;}
	/*******************************************************/
	/**********************作业要求↓************************/
	@Override
	public int compareTo(Circle c) {double r=c.getRadius();return radius>r?1:(radius==r?0:-1);}
	@Override
	public boolean equals(Object c) {if(c instanceof Circle) {return ((Circle) c).getRadius()==radius?true:false;}return false;}
	//一个问题，当两个半径相等的圆比较时，equals返回true，compareTo返回0，而要求二者最好结果一致。就算把true当作1，结果也不会一致的？？
	/**********************作业要求↑************************/
	/*******************************************************/
	@Override
	public double getArea() {return pi*radius*radius;}
	@Override
	public double getPerimeter() {return 2*pi*radius;}
}

abstract class GeometricObject{
	String color;
	boolean filled;
	java.util.Date dateCreated;

	protected GeometricObject() {color="";filled=false;dateCreated=new java.util.Date();}
	protected GeometricObject(String c,boolean f) {color=c;filled=f;dateCreated=new java.util.Date();}
	public String getColor() {return color;}
	public void setColor(String c) {color=c;}
	public boolean getFilled() {return filled;}
	public void setFilled(boolean f) {filled=f;}
	public java.util.Date getDateCreated(){return dateCreated;}
	public String toString() {
		return color;}
	public abstract double getArea();
	public abstract double getPerimeter();
}