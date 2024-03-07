import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.File;

public class MainTesting {
	
	public static CompoundInterest ci;
	
	public static BufferedWriter bw;
	
	public static BufferedReader br;
	
	public static MyFileIO fio;
	
	public static File f;
	
	public static int numTest = 1;
	
	public static double rate = .05;
	
	public static int period = 15;
	
	public static String fName = "data/FinGenParams" + numTest + ".txt";
	
	public static void main(String[] args) {
		ci = new CompoundInterest();
		f = new File(fName);
		fio = new MyFileIO();
		//bw = fio.openBufferedWriter(null);
		//br = fio.openBufferedReader(null);
		
		//calcuation testing
		//for (int i = 1; i < period + 1; i++)
			//System.out.println(ci.testingCalc(rate, i) + "\n");
		
		/*
		String temp = "output file: FinTable1.txt";
		temp = temp.trim();
		temp = temp.toLowerCase();
		System.out.println(temp);
		temp = "output file: FinTable1.txt";
		String test = temp.replaceAll("\\w+\\s*\\w+\\s*:\\s*", "");
		System.out.println(test);
		*/
		
		ci.createOutputFile(fName);
		/*
		System.out.println(ci.getRate());
		System.out.println(ci.getPeriod());
		String[] columns = ci.getColumns();
		String line = "       end of period";
		for (int i = 0; i < columns.length; i++) {
			line += "        " + columns[i];
		}
		System.out.println(line);
		*/
	}
}
