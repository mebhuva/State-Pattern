package airportSecurityState.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import airportSecurityState.util.MyLogger.DebugLevel;

/**
 * @author Mehul
 *
 */
public class Results implements FileDisplayInterface {

	ArrayList<String> ResultList = new ArrayList<>();

	/**
	 * Default Constructor
	 */
	public Results() {
		MyLogger.writeMessage(this.getClass().getName() + "Default Constructor is called ", DebugLevel.CONSTRUCTOR);
	}

	@Override
	public void writeFile(String FileName) {
		// TODO Auto-generated method stub
		BufferedWriter writer = null;

		try {
			File file = new File(FileName);
			writer = new BufferedWriter(new FileWriter(file, true));// using buffered writer to write into the file
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}
		try {
			for (String objString : ResultList) {
				try {
					writer.append(objString + "\n");
				} catch (IOException e) {
					e.printStackTrace();
				} finally {

				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {

		}
		try {
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

		}

	}

	@Override
	public void storeresult(String resultValue) {
		this.ResultList.add(resultValue);
	}

	@Override
	public String toString() {
		return "Results [ResultList=" + ResultList + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ResultList == null) ? 0 : ResultList.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Results other = (Results) obj;
		if (ResultList == null) {
			if (other.ResultList != null)
				return false;
		} else if (!ResultList.equals(other.ResultList))
			return false;
		return true;
	}
}
