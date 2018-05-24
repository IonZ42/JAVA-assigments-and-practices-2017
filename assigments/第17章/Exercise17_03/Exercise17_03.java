import java.io.*;

public class Exercise17_03 {
	public static void main(String[] args) throws IOException,FileNotFoundException {
		int sum = 0;
		File f = new File("Exercise17_03.dat");
		if (f.createNewFile()) {
			try (DataOutputStream output = new DataOutputStream(new FileOutputStream("Exercise17_03.dat"));) {//用data而不用buffered
				output.writeInt(12345);output.writeInt(-12345);output.writeInt(0);output.writeInt(6);
			}
        } else {}
		try {
			try (DataInputStream input = new DataInputStream(new FileInputStream("Exercise17_03.dat"));) {//用data而不用buffered
				while (true) {
					sum += input.readInt();
				}
				
			}
		} 
		catch (EOFException ex) {
			System.out.println("the sum of integers is "+sum);
		}
		catch (FileNotFoundException ex) {
			System.out.println("file doesn't exsist");
		}

	}
}
