import java.io.*;
import java.util.Scanner;

public class Exercise17_14 {
	public static void main(String[] args) throws IOException,FileNotFoundException {
		Scanner in=new Scanner(System.in);
		String inpath,outpath;
		System.out.println("Enter the input file path: ");inpath=in.next();
		System.out.println("Enter the output file path: ");outpath=in.next();
		in.close();
		try {
			try(    BufferedInputStream input = new BufferedInputStream(new FileInputStream(inpath));
					BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(outpath));) 
			{
				int bytesSize=input.available();
				while (bytesSize-->0) {output.write(input.read()+5);}
			}
			System.out.println("the file "+inpath+" has been encrypted into file "+outpath);
		} 
		catch (EOFException ex) {
			System.out.println("the file "+inpath+" has been encrypted");
		}
		catch (FileNotFoundException ex) {
			System.out.println("file doesn't exsist");
		}
	}
}
