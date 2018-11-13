package airportSecurityState.driver;

import airportSecurityState.util.Results;

import java.io.IOException;

import airportSecurityState.airportStates.AirportContext;
import airportSecurityState.airportStates.AirportContextI;
import airportSecurityState.securityfactorcomputation.SecurityComputation;
import airportSecurityState.util.FileDisplayInterface;
import airportSecurityState.util.FileProcessor;
import airportSecurityState.util.MyLogger;
import airportSecurityState.util.MyLogger.DebugLevel;

/**
 * @author Mehul
 *
 */
public class Driver {
	/**
	 * Main method is the entry point of this project
	 * 
	 * @param args
	 */
	public static void main(String[] args) throws IOException {

		/*
		 * As the build.xml specifies the arguments as argX, in case the argument value
		 * is not given java takes the default value specified in build.xml. To avoid
		 * that, below condition is used
		 */
		if (args.length != 3 || args[0].equals("${arg0}") || args[1].equals("${arg1}") || args[2].equals("${arg2}")) {

			System.err.println("Invalid arguments specified");
			System.err.println(
					"Command : ant -buildfile build.xml run -Darg0=input.txt -Darg1=output.txt -Darg2=DEBUG_LEVEL");
			System.err.println("DEBUG_LEVEL : 0 - None, 1 - CONSTRUCTOR , 2 - EXCEPTION , 3 - STATECHANGE, 4 -  RESULT");
			System.exit(0);
		} else {
			FileProcessor fp = new FileProcessor(args[0]);// creating fileprocessor object
			int checkinsert = fp.fileInputExists();// checking if input file exists or not in the directory
			if (checkinsert == 0) {
				System.err.println("Input File not found");
				System.exit(0);
			}
			if (!isInteger(args[2])) {
				System.err.println("Debug Value shuld be a number");
				System.err.println("DEBUG_LEVEL : 0 - None, 1 - CONSTRUCTOR , 2 - EXCEPTION , 3 - STATECHANGE, 4 -  RESULT");
				System.exit(0);
			} else {
				int debug_level = 0;
				try {
					debug_level = Integer.parseInt(args[2]);
				} catch (NumberFormatException e1) {
					MyLogger.writeMessage(Driver.class.getSimpleName() + " : " + e1.toString(), DebugLevel.EXCEPTION);
				} finally {

				}
				if (debug_level > 0 && debug_level < 4) {
					System.err.println("Debug level should be between 0 and 4");
					System.err.println("DEBUG_LEVEL : 0 - None, 1 - CONSTRUCTOR , 2 - EXCEPTION , 3 - STATECHANGE, 4 -  RESULT");
					System.exit(0);
				}
				MyLogger.setDebugValue(debug_level);// it sets current debug level

				FileDisplayInterface ResultsObject = new Results();
				SecurityComputation SecurityComputationObject = new SecurityComputation();
				AirportContextI airportobj = new AirportContext();
				String line;

				while ((line = fp.readLine()) != null) {
					SecurityComputationObject.setCurrentLine(line);
					SecurityComputationObject.CalculateFactors();
					SecurityComputationObject = airportobj.lineProcessing(SecurityComputationObject);
					ResultsObject.storeresult(SecurityComputationObject.getResult());
				}
				FileProcessor fp1 = new FileProcessor(args[1]);// creating fileprocessor object
				int checkoutput = fp.fileInputExists();// checking if output file exists or not in the directory
				if (checkoutput == 0) {
					MyLogger.writeMessage("Creating output file and Writing into the file", DebugLevel.RESULT);
					MyLogger.WriteResult(ResultsObject, args[1]);
				} else {
					if (fp1.readLine() == null) {
						MyLogger.writeMessage("File is empty", DebugLevel.RESULT);
						MyLogger.writeMessage("Creating output file and Writing into the file", DebugLevel.RESULT);
						MyLogger.WriteResult(ResultsObject, args[1]);
					} else {
						System.err.println("output file is not empty");
						MyLogger.writeMessage("File is not empty", DebugLevel.EXCEPTION);
						System.exit(0);

					}
				}

			}

		}

	}

	/**
	 * isInteger check if string is integer or not
	 * 
	 * @param str
	 * @return
	 */
	private static boolean isInteger(String str) {
		try {
			int i = Integer.parseInt(str);
			return true;
		} catch (NumberFormatException er) {
			return false;
		} finally {

		}

	}

	@Override
	public String toString() {
		return "Driver [getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
}
