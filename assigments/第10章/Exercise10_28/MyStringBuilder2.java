public class MyStringBuilder2 {
	private char[] chars;
	
	public MyStringBuilder2(){chars=new char[0];}
	public MyStringBuilder2(char[] cs){chars=cs;}
	public MyStringBuilder2(String s0){
		int length=s0.length();chars =new char[length];for(int i=0;i<length;i++){chars[i]=s0.charAt(i);}}
	public int length() {return chars.length;}
	public char charAt(int index) {return chars[index];}
	public MyStringBuilder2 insert(int offset,MyStringBuilder2 s) {
		char[] temp=chars;chars=new char[temp.length+s.length()];
		for(int i=0;i<offset;i++) {chars[i]=temp[i];}
		for(int i=offset;i<offset+s.length();i++) {chars[i]=s.charAt(i-offset);}
		for(int i=offset+s.length();i<chars.length;i++) {chars[i]=temp[i-s.length()];}
		return this;}
	public MyStringBuilder2 reverse(int offset,MyStringBuilder2 s) {
		char[] temp=chars;
		for(int i=0;i<temp.length;i++) {chars[i]=temp[temp.length-i-1];}
		return this;}
	public MyStringBuilder2 subString(int begin)
	{return new MyStringBuilder2(String.valueOf(chars).substring(begin));}
	public MyStringBuilder2 toUpperCase(){for(int i=0;i<chars.length;i++) 
	{if(97<=chars[i]&&122>=chars[i])chars[i]-=32;}return this;}
}
