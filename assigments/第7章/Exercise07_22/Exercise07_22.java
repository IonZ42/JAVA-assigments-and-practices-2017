public class Exercise07_22 {
	
	public static void main(String[] args) {
	
		char[][] board=new char[8][8];
		boolean[][]map=new boolean[8][8];
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				if(map[i][j]==false) {
					if(i==3&&j==1)continue;
					board[i][j]='Q';
					for(int k=0;k<8;k++) {map[i][k]=true;}
					for(int k=0;k<8;k++) {map[k][j]=true;}
					for(int k=0;k<8;k++) 
					{if(j-(Math.abs(i-k))>=0) {map[k][j-(Math.abs(i-k))]=true;}
					if(j+(Math.abs(i-k))<8) {map[k][j+(Math.abs(i-k))]=true;}
					}
				}
			}
		}
		for(int i=0;i<8;i++) {
			for(int j=0;j<17;j++) {
				if(j%2==0) {System.out.printf("|");}
				else System.out.printf(""+board[i][j/2]);
				if(j==16) {System.out.printf("\r\n");}
			}
		}
	}
}
