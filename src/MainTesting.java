import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.File;

public class MainTesting {
	
	public static BufferedWriter bw;
	
	public static BufferedReader br;
	
	public static MyFileIO fio;
	
	public static File f;
	
	public static int numTest = 1;
	
	public static void main(String[] args) {
		f = new File("data/FinGenParams" + numTest + ".txt");
		fio = new MyFileIO();
		bw = fio.openBufferedWriter(null);
		br = fio.openBufferedReader(null);
		
	}
}
