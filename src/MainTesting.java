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
	
	public static void main(String[] args) {
		ci = new CompoundInterest();
		f = new File("data/FinGenParams" + numTest + ".txt");
		fio = new MyFileIO();
		//bw = fio.openBufferedWriter(null);
		//br = fio.openBufferedReader(null);
		for (int i = 1; i < period + 1; i++)
			System.out.println(ci.testingCalc(rate, i) + "\n");
	}
}
