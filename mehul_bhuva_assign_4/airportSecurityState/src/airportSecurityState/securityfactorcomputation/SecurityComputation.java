package airportSecurityState.securityfactorcomputation;

import java.util.ArrayList;

import airportSecurityState.airportStates.AirportContext;
import airportSecurityState.airportStates.AirportStateI;

public class SecurityComputation {
	private ArrayList<Integer> Days = new ArrayList<>();
    private ArrayList<String> Items = new ArrayList<>();
    private String CurrentLine;
    private int Traveller = 0;
    //private FileDisplayInterface ResultsObject = new Results();
    private String Result;
	private double  averageTraffic = 0;
    private double  averageProhibitedItems = 0;
    private AirportStateI CurrentState;
    int count = 1;
    
    public AirportStateI getCurrentState() {
		return CurrentState;
	}

	public void setCurrentState(AirportStateI currentState) {
		CurrentState = currentState;
	}

	public String getCurrentLine() {
		return CurrentLine;
	}

	public void setCurrentLine(String currentLine) {
		CurrentLine = currentLine;
		this.setTraveller(this.getTraveller()+1);
	}
	public int getTraveller() {
		return Traveller;
	}

	public void setTraveller(int traveller) {
		Traveller = traveller;
	}

	public double getAverageTraffic() {
		return averageTraffic;
	}

	public void setAverageTraffic(double  averageTraffic) {
		this.averageTraffic = averageTraffic;
	}

	public double getAverageProhibitedItems() {
		return averageProhibitedItems;
	}

	public void setAverageProhibitedItems(double  averageProhibitedItems) {
		this.averageProhibitedItems = averageProhibitedItems;
	}
    	

	public String getResult() {
		return Result;
	}

	public void setResult(String result) {
		Result = result;
	}
	
	
	/**
	 * function to calculate Average Traffic
	 * @param dayNum
	 * @param totalPlanes
	 * @return
	 */
	public double  calulateAverageTraffic(int dayNum, int noofTraveler) {
		averageTraffic = (noofTraveler / (double) dayNum);
		return averageTraffic;
	}

	/**
	 * function to calculate Average ProhibitedItems
	 * @param itemCount
	 * @param dayNumber
	 * @return
	 */
	public double  calculateProhibitedItems(int itemCount, int dayNumber) {
		averageProhibitedItems = (itemCount /(double)  dayNumber);
		return averageProhibitedItems;
	}
	
	
	public void CalculateFactors()
	{
		String[] split = CurrentLine.split(";");
		Days.add(Integer.parseInt(split[0].split(":")[1]));
		Items.add(split[1].split(":")[1]);
		this.setAverageTraffic(calulateAverageTraffic(Days.get(Days.size()-1), this.getTraveller()));
		this.setAverageProhibitedItems(calculateProhibitedItems(Items.size(), Days.get(Days.size()-1)));
	}

	public SecurityComputation CalculateState(SecurityComputation SecurityComputationobj , AirportContext newContext)
	{
		if(SecurityComputationobj.getAverageTraffic() >= 8 || SecurityComputationobj.getAverageProhibitedItems() >= 4)
		{
			newContext.setCurrentState(newContext.getHighRisk());
			//System.out.println("Count :"+count+" "+SecurityComputationobj.getAverageTraffic() +"   "+ SecurityComputationobj.getAverageProhibitedItems());
			//System.out.println("2 4 6 8 10");
			//count++;
			SecurityComputationobj.setResult("2 4 6 8 10");
			//MyLogger.writeMessage("State Changed to Low Risk State", DebugLevel.STATECHANGE);
		}
		else if((SecurityComputationobj.getAverageTraffic() >= 4 && SecurityComputationobj.getAverageTraffic() < 8)
				|| (SecurityComputationobj.getAverageProhibitedItems() >= 2 && SecurityComputationobj.getAverageProhibitedItems() < 4))
		{
			newContext.setCurrentState(newContext.getModerateRisk());
			//System.out.println("Count :"+count+" "+SecurityComputationobj.getAverageTraffic() +"   "+ SecurityComputationobj.getAverageProhibitedItems());
			//System.out.println("2 3 5 8 9");
			//count++;
			SecurityComputationobj.setResult("2 3 5 8 9");
			//MyLogger.writeMessage("State Changed to Low Risk State", DebugLevel.STATECHANGE);
		}
		else if ((SecurityComputationobj.getAverageTraffic() >= 0) && (SecurityComputationobj.getAverageTraffic() < 4)
				|| (SecurityComputationobj.getAverageProhibitedItems() >= 0) && (SecurityComputationobj.getAverageProhibitedItems() < 2)) {

			newContext.setCurrentState(newContext.getLowRisk());
			//System.out.println("Count :"+count+" "+SecurityComputationobj.getAverageTraffic() +"   "+ SecurityComputationobj.getAverageProhibitedItems());
			//System.out.println("1 3 5 7 9");
			//count++;
			SecurityComputationobj.setResult("1 3 5 7 9");
			//MyLogger.writeMessage("State Changed to Low Risk State", DebugLevel.STATECHANGE);
		}
		return SecurityComputationobj;
	}
	
}
