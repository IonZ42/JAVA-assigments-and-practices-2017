public class MyPoint {
	private double x;
	private double y;
	
	public MyPoint() {x=0;y=0;}
	public MyPoint(double x0,double y0) {x=x0;y=y0;}
	public double getx() {return x;}
	public double gety() {return y;}
	public double distance(MyPoint ref) 
	{double dx=x-ref.getx();double dy=y-ref.gety();return Math.pow(dx*dx+dy*dy, 0.5);}
	public double distance(double x0,double y0) 
	{double dx=x-x0;double dy=y-y0;return Math.pow(dx*dx+dy*dy, 0.5);}
	public boolean areSegementsCrossed(MyPoint p2,MyPoint p3,MyPoint p4) 
	{
		double x2=p2.getx(),y2=p2.gety(),y3=p3.gety(),y4=p4.gety(),x3=p3.getx(),x4=p4.getx();
		if(Math.min(x,x2)<=Math.max(x3,x4) && Math.min(x3,x4)<=Math.max(x,x2) 
		&& Math.min(y,y2)<=Math.max(y3,y4) && Math.min(y3,y4)<=Math.max(y,y2) ){return true;}
		else return false;}
}