public class Exercise04_03 {

	public static double s(double p1x,double p1y,double p2x,double p2y) {
		p1x=Math.toRadians(p1x);
		p1y=Math.toRadians(p1y);
		p2x=Math.toRadians(p2x);
		p2y=Math.toRadians(p2y);
		return 6371*Math.acos(Math.sin(p1x)*Math.sin(p2x)+Math.cos(p1x)*Math.cos(p2x)*Math.cos(p1y-p2y));
	}
	public static double triArea(double s1,double s2,double s3) {
		double s=0.5*(s1+s2+s3);
		return Math.pow(s*(s-s1)*(s-s2)*(s-s3), 0.5);
	}
	public static void main(String[] args) {
		final double[] p= {35.2270869,-80.8431267,33.7489954,-84.3879824,32.0835407,-81.0998342,28.5383355,-81.3792365};
        //Charlotte,Atlanta,Savannah,Orlando
        double s1=s(p[0],p[1],p[2],p[3]),s2=s(p[2],p[3],p[4],p[5]),s3=s(p[4],p[5],p[0],p[1]);
        double s4=s(p[2],p[3],p[6],p[7]),s5=s(p[6],p[7],p[4],p[5]);
        double ans=triArea(s1,s2,s3)+triArea(s2,s4,s5);
		System.out.printf("The area of the rectangle is %-10.1f squre kilometres", ans);
	}
}
