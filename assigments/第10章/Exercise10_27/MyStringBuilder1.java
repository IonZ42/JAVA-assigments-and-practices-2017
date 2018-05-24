public class MyStringBuilder1 {
	private char[] chars;
	
	public MyStringBuilder1(){chars=new char[0];}
	public MyStringBuilder1(String s0){
		int length=s0.length();chars =new char[length];for(int i=0;i<length;i++){chars[i]=s0.charAt(i);}}
	public MyStringBuilder1 append(MyStringBuilder1 s0) {
		char[] temp=chars;chars=new char[s0.length()];
		for(int i=0;i<temp.length;i++){chars[i]=temp[i];}
		for(int i=temp.length;i<chars.length;i++) {chars[i]=s0.charAt(i-temp.length);}
		return this;}
	public MyStringBuilder1 append(int i) {return this.append(new MyStringBuilder1(""+i));}
	public int length() {return chars.length;}
	public char charAt(int index) {return chars[index];}
	public MyStringBuilder1 toLowerCase(){for(int i=0;i<chars.length;i++) 
	{if(65<=chars[i]&&90>=chars[i])chars[i]+=32;}return this;}
	public MyStringBuilder1 subString(int begin, int end)
	{return new MyStringBuilder1(String.valueOf(chars).substring(begin,end));}
	public String toString() {return String.valueOf(chars);}
}
