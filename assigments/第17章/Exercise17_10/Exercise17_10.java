import java.io.*;

public class Exercise17_10 {
	public static void main(String[] args) {
		try {
			try (BufferedInputStream input = new BufferedInputStream(new FileInputStream(args[0]));) {
				int n = Integer.parseInt(args[1]), bytesSize = input.available(), size = bytesSize / n;
				String name=args[0];
				for (int i = 1; i <= n; i++) {
					int counter = 0;
					//File f = new File(name+"."+i);if (f.createNewFile()) {} else {}//不行，即使同名文件已存在，也要更新文件
					try (BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(name+"."+i))) {
						while(counter++<size) {output.write(input.read());}
					}
				}
				try (BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(name+"."+n,true))) {//追加在尾部
					for(int i=0;i<bytesSize-size*n;i++) {output.write(input.read());}//处理剩余byte
				}
				System.out.println("file "+args[0]+" has been split into "+n+" parts");
			}
		} 
		catch (EOFException ex) {
			System.out.println("file split done!");
		} 
		catch (FileNotFoundException ex) {
			System.out.println("file not found");
		} 
		catch (IOException ex) {
			System.out.println("io exception");
		}
		catch (ArrayIndexOutOfBoundsException ex) {
			System.out.println("please input the target file path!");
		}
	}
}