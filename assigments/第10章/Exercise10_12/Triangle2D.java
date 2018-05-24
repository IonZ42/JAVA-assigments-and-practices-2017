public class Triangle2D {
	private MyPoint p1,p2,p3;
	
	public Triangle2D() {p1=new MyPoint(0,0);p2=new MyPoint(1,1);p3=new MyPoint(2,5);}
	public Triangle2D(MyPoint p01,MyPoint p02,MyPoint p03) {p1=p01;p2=p02;p3=p03;}
	public void setp1(MyPoint p01) {p1=p01;}
	public void setp2(MyPoint p02) {p1=p02;}
	public void setp3(MyPoint p03) {p1=p03;}
	public MyPoint getp1() {return p1;}
	public MyPoint getp2() {return p2;}
	public MyPoint getp3() {return p3;}
	public double getArea() 
	{double s1=p1.distance(p2),s2=p2.distance(p3),s3=p3.distance(p1),s=0.5*(s1+s2+s3);
	return Math.pow(s*(s-s1)*(s-s2)*(s-s3), 0.5);}
	public double getPerimeter() {return p1.distance(p2)+p2.distance(p3)+p3.distance(p1);}
	public boolean contains(MyPoint p) 
	{if((!p.areSegementsCrossed(p1,p2,p3))&&(!p.areSegementsCrossed(p2,p1,p3))&&(!p.areSegementsCrossed(p3,p1,p2)))
	{return true;}else return false;}
	public boolean contains(Triangle2D t) 
	{if(contains(t.getp1())&&contains(t.getp2())&&contains(t.getp3())){return true;}else return false;}
	public boolean overlaps(Triangle2D t) 
	{
		MyPoint p4=t.getp1(),p5=t.getp2(),p6=t.getp3();
		int ct=0;
		if(p1.areSegementsCrossed(p2, p5, p4)) {ct++;}
		if(p1.areSegementsCrossed(p2, p5, p6)) {ct++;}
		if(p1.areSegementsCrossed(p2, p6, p4)) {ct++;}
		if(p1.areSegementsCrossed(p3, p5, p4)) {ct++;}
		if(p1.areSegementsCrossed(p3, p6, p4)) {ct++;}
		if(p1.areSegementsCrossed(p3, p5, p6)) {ct++;}
		if(p3.areSegementsCrossed(p2, p5, p4)) {ct++;}
		if(p3.areSegementsCrossed(p2, p6, p4)) {ct++;}
		if(p3.areSegementsCrossed(p2, p5, p6)) {ct++;}
		if(ct==0){return false;}else return true;}
}
