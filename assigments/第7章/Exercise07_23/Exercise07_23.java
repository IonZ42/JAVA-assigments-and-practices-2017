public class Exercise07_23 {

	public static void main(String[] args) {
		boolean[]boxes=new boolean[100];
		for(int i=1;i<=100;i++) {
			for(int j=i;j<=100;j+=i) {
				boxes[j-1]=!boxes[j-1];
			}
		}
		for(int i=0;i<100;i++) {
			if(boxes[i]) {System.out.printf("L"+(i+1)+" ");}
		}
		System.out.printf("is open");
	}
}
