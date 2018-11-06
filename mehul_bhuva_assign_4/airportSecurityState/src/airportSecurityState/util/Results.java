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
public class Results implements FileDisplayInterface{

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
			writer = new BufferedWriter(new FileWriter(file, true));//using buffered writer to write into the file
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			
		}
		try {
			for (String objString : ResultList) {
				try {
					writer.append(objString+"\n");
				} catch (IOException e) {
					e.printStackTrace();
				}
				finally
				{
					
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		finally
		{
			
		}
		    try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		    finally
			{
				
			}

	}
	
	
	@Override
	public void storeresult(String resultValue) {
		this.ResultList.add(resultValue);
	}
}
