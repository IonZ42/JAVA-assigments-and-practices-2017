import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Exercise12_13 {

	public static void main(String[] args) throws FileNotFoundException {
		for(int i=0;i<args.length;i++) {
			File f=new File(args[i]);
        	int linenum=0,charnum=0,wordnum=0;
			try(
					Scanner in=new Scanner(f);
					)
			{
				ArrayList<String> lines=new ArrayList<String>();
	        	while(in.hasNextLine()) {lines.add(in.nextLine());linenum++;}
	        	for(String line:lines) {
	        		charnum+=line.length();
	        		Scanner t=new Scanner(line);
	        		t.useDelimiter(" ");
	        		while(t.hasNext()) {t.next();wordnum++;}
	        		t.close();
	        	}
			}
			System.out.println("\nFile "+args[i]+" has\n"
					+charnum+" characters\n"
					+wordnum+" words\n"
					+linenum+" lines");
		}
	}
}
