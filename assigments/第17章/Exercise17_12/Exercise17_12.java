import java.io.*;

public class Exercise17_12 {
	public static void main(String[] args) {
		try {
			try (BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(args[args.length-1]));) {
				for (int i = 0; i <args.length-1; i++) {
					try (BufferedInputStream input = new BufferedInputStream(new FileInputStream(args[i]))) {
						int bytesSize = input.available();
						while(bytesSize-->0) {output.write(input.read());}
					}
				}
				System.out.println(args.length+" parts of file has been merge into file: "+args[args.length-1]);
			}
		} 
		catch (EOFException ex) {
			System.out.println("file merge done!");
		} 
		catch (FileNotFoundException ex) {
			System.out.println("file not found");
		} 
		catch (IOException ex) {
			System.out.println("io exception");
		}
		catch (ArrayIndexOutOfBoundsException ex) {
			System.out.println("please input file names in right format!");
		}
	}
}