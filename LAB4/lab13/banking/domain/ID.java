package banking.domain;

public class ID{
	protected int mid=-1;
	protected String id;
	public ID() {mid=-1;id=""+mid;}
	public ID(int id0) {mid=id0;id=""+mid;System.out.println(id);}
	public void setMid(int id0) {mid=id0;}//id=Integer.parseInt(id0+"");
	public int getMid() {return mid;}

	public void setID(String m) {id=m;}
	public String getId() {return id;}
}