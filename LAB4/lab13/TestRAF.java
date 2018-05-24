import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Scanner;

public class TestRAF {
	public TestRAF() {}
	
	public static ArrayList<String> readRAF(RandomAccessFile datFile, File schemaFile) throws IOException {
		ArrayList<String> temp=new ArrayList<String>();
		switch(schemaFile.getName()) {
		case "checkingaccountSchema.txt":{
			String[] lines=new String[5];
			int[] sizes=new int[5];
			int recordsize=0;
			long records=datFile.length()/recordsize;
			try(
	    			Scanner in=new Scanner(schemaFile);
	        		)
	    	{
				for(int i=0;i<5;i++) {lines[i]=in.nextLine();sizes[i]=Integer.parseInt(""+(lines[i].split(" "))[2]);recordsize+=sizes[i];}
				for(int i=0;i<records;i++) {
					temp.add(FixedLengthStringIO.readFixedLengthString(4, datFile));}}
	    	}
		case "customerSchema.txt":{
			String[] lines=new String[3];
			int[] sizes=new int[3];
			int recordsize=0;
			try(
	    			Scanner in=new Scanner(schemaFile);
	        		)
	    	{
				for(int i=0;i<3;i++) {lines[i]=in.nextLine();sizes[i]=Integer.parseInt(""+(lines[i].split(" "))[2]);recordsize+=sizes[i];}
	    	}
		}
		case "savingsaccountSchema.txt":{
			String[] lines=new String[4];
			int[] sizes=new int[4];
			int recordsize=0;
			try(
	    			Scanner in=new Scanner(schemaFile);
	        		)
	    	{
				for(int i=0;i<4;i++) {lines[i]=in.nextLine();sizes[i]=Integer.parseInt(""+(lines[i].split(" "))[2]);recordsize+=sizes[i];}
	    	}
		}
		case "transactionSchema.txt":{
			String[] lines=new String[7];
			int[] sizes=new int[7];
			int recordsize=0;
			try(
	    			Scanner in=new Scanner(schemaFile);
	        		)
	    	{
				for(int i=0;i<7;i++) {lines[i]=in.nextLine();sizes[i]=Integer.parseInt(""+(lines[i].split(" "))[2]);recordsize+=sizes[i];}
	    	}
		}
		}
		return temp;
	}
	
	public static void writeRAF(RandomAccessFile datFile, File schemaFile) {
		switch(schemaFile.getName()) {
		case "checkingaccountSchema.txt":{}
		case "customerSchema.txt":{}
		case "savingsaccountSchema.txt":{}
		case "transactionSchema.txt":{}
		}
	}
}
