package airportSecurityState.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import airportSecurityState.util.MyLogger.DebugLevel;

/**
 * FileProcessor class process input file
 * 
 * @author Mehul
 *
 */
public class FileProcessor {

	private File input;
	private BufferedReader Bfreader;

	/**
	 * Default Constructor
	 * 
	 */
	public FileProcessor() {
		MyLogger.writeMessage(this.getClass().getName() + " Default Constructor is called ", DebugLevel.CONSTRUCTOR);
	}

	/**
	 * Constructor used to initialize input file object
	 * 
	 * @param args
	 */
	public FileProcessor(String FileName) {

		MyLogger.writeMessage(this.getClass().getName() + " Constructor is called ", DebugLevel.CONSTRUCTOR);
		try {
			this.input = new File(FileName);
			Bfreader = new BufferedReader(new FileReader(this.input));
		} catch (IOException e) {
			MyLogger.writeMessage(this.getClass().getName() + " : " + e.toString(), DebugLevel.EXCEPTION);
			System.exit(0);
		} finally {

		}
	}

	/**
	 * fileInputExists checks if file input exists or not
	 * 
	 * @return 0 or 1
	 */
	public int fileInputExists() {
		if (!this.input.exists()) {
			return 0;
		} else {
			return 1;
		}
	}

	/**
	 * method to read a line from input file
	 * 
	 * @return new line from input file.
	 */
	public String readLine() {
		try {
			String readLine = Bfreader.readLine();
			return readLine;
		} catch (IOException e) {
			MyLogger.writeMessage(this.getClass().getName() + " : " + e.toString(), DebugLevel.EXCEPTION);
			System.exit(0);
		} finally {

		}
		return null;
	}

	/**
	 * FileClose called after file reading is completed
	 */

	public void fileClose() {
		try {
			Bfreader.close();
		} catch (IOException e) {
			MyLogger.writeMessage(this.getClass().getName() + " : " + e.toString(), DebugLevel.EXCEPTION);
		} finally {

		}
	}

	@Override
	public String toString() {
		return "FileProcessor [input=" + input + ", Bfreader=" + Bfreader + "]";
	}
}