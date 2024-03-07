import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;

public class CompoundInterest {
	
	/** The MyFileIO, all the file reading and file writing things. */
	private MyFileIO fio;
	
	/** The buffered reader, it reads the input file. */
	private BufferedReader br;
	
	/** The buffered writer, it writes the output file. */
	private BufferedWriter bw;
	
	/** The interest rate. */
	private double rate;
	
	/** The annuities. */
	private int period;
	
	/** The number of columns. */
	private String[] columns;
	
	/** The output file name. */
	private String ofName;
	
	/**
	 * Instantiates a new compound interest.
	 */
	public CompoundInterest() {
		fio = new MyFileIO();
	}
	
	/**
	 * The one method that runs all private ones, creates everything.
	 *
	 * @param input the input
	 */
	public void createTable(String input) {
		separateData(input);
		if (inputErrorChecking(input) || outputErrorChecking(ofName))
			return;
	}
	
	/**
	 * Separates the useful data from the comments and labels
	 *
	 * @param input the input file name
	 */
	private void separateData(String input) {
		String rate = "";
		String period = "";
		String[] columns = new String[6];
		String ofName = "";
		File in = new File(input);
		br = fio.openBufferedReader(in);
		String temp = "";
		
		try {
			while ((temp = br.readLine()) != null) {
				temp = temp.trim();
				String original = temp;
				temp = temp.toLowerCase();
				if (temp.length() != 0 && temp.charAt(0) == '#')
					continue;
				if (temp.replaceAll("(\\w+)\\s*:", "$1:").substring(0, 5).equals("rate:")) {
					rate = temp.replaceAll("\\w+\\s*:\\s*([\\d+\\.\\d+])", "$1");
				} else if (temp.replaceAll("(\\w+)\\s*:", "$1:")
						.substring(0, 8).equals("periods:")) {
					period = temp.replaceAll("\\w+\\s*:\\s*([\\d+.\\d+])", "$1");
				} else if (temp.replaceAll("(\\w+)\\s*:", "$1:")
						.substring(0, 8).equals("columns:")) {
					temp = temp.replaceAll("\\w+\\s*:\\s*([\\w{2}.])\\s*", "$1");
					columns = temp.split(",\\s*");
				} else if (temp.replaceAll("(\\w+)\\s*(\\w+)\\s*:", "$1$2:")
						.substring(0, 11).equals("outputfile:")) {
					ofName = original.replaceAll("\\w+\\s*\\w+\\s*:\\s*", "");
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setRate(Double.parseDouble(rate));
		setPeriod(Integer.parseInt(period));
		setColumns(columns);
		setOutputName(ofName);
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
	
	/**
	 * Sets the rate.
	 *
	 * @param rate the new rate
	 */
	public void setRate(double rate) {
		this.rate = rate;
	}
	
	/**
	 * Gets the rate.
	 *
	 * @return the rate
	 */
	public double getRate() {
		return rate;
	}
	
	/**
	 * Sets the columns.
	 *
	 * @param columns the new columns
	 */
	public void setColumns(String[] columns) {
		this.columns = columns;
	}
	
	/**
	 * Gets the columns.
	 *
	 * @return the columns
	 */
	public String[] getColumns() {
		return columns;
	}
	
	/**
	 * Sets the period.
	 *
	 * @param period the new period
	 */
	public void setPeriod(int period) {
		this.period = period;
	}
	
	/**
	 * Gets the period.
	 *
	 * @return the period
	 */
	public int getPeriod() {
		return period;
	}
	
	private void setOutputName(String of) {
		ofName = of;
	}
	
	//delete
	public String getOutputName() {
		return ofName;
	}
	
	//delete later
	public String testingCalc(double rate, int period) {
		return "PF:  " + formatNumbers(calcPF(rate, period)) + "\nFP:  " + formatNumbers(calcFP(rate, period))
			+ "\nPA:  " + formatNumbers(calcPA(rate, period)) + "\nAP:  " + formatNumbers(calcAP(rate, period))
			+ "\nFA:  " + formatNumbers(calcFA(rate, period)) + "\nAF:  " + formatNumbers(calcAF(rate, period));
	}
}
