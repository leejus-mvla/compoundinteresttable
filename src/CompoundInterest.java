import java.io.BufferedWriter;
import java.io.File;
import java.io.BufferedReader;

public class CompoundInterest {
	
	private MyFileIO fio;
	
	private BufferedReader br;
	
	private BufferedWriter bw;
	
	public CompoundInterest() {
		fio = new MyFileIO();
	}
	
	public void createTable(String input, String output) {
		File in = new File(input);
		File of = new File(output);
		if (inputErrorChecking(input) || outputErrorChecking(output))
			return;
		
	}
	
	/**
	 * Error checking for the input file.
	 *
	 * @param input the input file name
	 * @return true, if there are errors
	 */
	private boolean inputErrorChecking(String input) {
		File in = new File(input);
		int fileStatus = fio.checkFileStatus(in, true);
		if (fileStatus == MyFileIO.EMPTY_NAME) {
			System.out.println("Input file no name");
			return true;
		} else if (fileStatus == MyFileIO.NOT_A_FILE) {
			System.out.println("Input file isn't a file");
			return true;
		} else if (fileStatus == MyFileIO.FILE_DOES_NOT_EXIST) {
			System.out.println("Input file does not exist");
			return true;
		} else if (fileStatus == MyFileIO.READ_ZERO_LENGTH) {
			System.out.println("Input file is empty");
			return true;
		}
		return false;
	}
	
	/**
	 * Error checking for the output file.
	 *
	 * @param output the output file name
	 * @return true, if there are errors
	 */
	private boolean outputErrorChecking(String output) {
		File of = new File(output);
		int outputStatus = fio.checkFileStatus(of, false);
		if (outputStatus == MyFileIO.EMPTY_NAME) {
			System.out.println("Output file name is empty");
			return true;
		} else if (outputStatus == MyFileIO.NOT_A_FILE) {
			System.out.println("Output file is not a file");
			return true;
		}
		return false;
	}
	
	private String formatNumbers(double num) {
		return String.format("%.4f", num);
	}
	
	/**
	 * Calculates present given final.
	 *
	 * @param rate the rate
	 * @param period the period
	 * @return the P given F
	 */
	private double calcPF(double rate, int period) {
		return 1 / (Math.pow(1 + rate, period));
	}
	
	/**
	 * Calculates final given present.
	 *
	 * @param rate the rate
	 * @param period the period
	 * @return the F given P
	 */
	private double calcFP(double rate, int period) {
		return 1 / calcPF(rate, period);
	}
	
	/**
	 * Calculates present given annuities.
	 *
	 * @param rate the rate
	 * @param period the period
	 * @return the present value of n payments
	 */
	private double calcPA(double rate, int period) {
		double finalValue = 1 - calcPF(rate, period);
		return finalValue / rate;
	}
	
	/**
	 * Calculates annuities given present.
	 *
	 * @param rate the rate
	 * @param period the period
	 * @return the number of payments given present
	 */
	private double calcAP(double rate, int period) {
		return 1 / calcPA(rate, period);
	}
	
	/**
	 * Calculates annuities given final.
	 *
	 * @param rate the rate
	 * @param period the period
	 * @return the future value of n payments
	 */
	private double calcFA(double rate, int period) {
		double finalValue = calcFP(rate, period) - 1;
		return finalValue / rate;
	}
	
	/**
	 * Calculates final given annuities.
	 *
	 * @param rate the rate
	 * @param period the period
	 * @return the number of payments given future value
	 */
	private double calcAF(double rate, int period) {
		return 1 / calcFA(rate, period);
	}
	
	//delete later
	public String testingCalc(double rate, int period) {
		return "PF:  " + formatNumbers(calcPF(rate, period)) + "\nFP:  " + formatNumbers(calcFP(rate, period))
			+ "\nPA:  " + formatNumbers(calcPA(rate, period)) + "\nAP:  " + formatNumbers(calcAP(rate, period))
			+ "\nFA:  " + formatNumbers(calcFA(rate, period)) + "\nAF:  " + formatNumbers(calcAF(rate, period));
	}
}
