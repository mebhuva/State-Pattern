package airportSecurityState.securityfactorcomputation;

import java.util.ArrayList;

import airportSecurityState.airportStates.AirportContext;
import airportSecurityState.airportStates.AirportStateI;
import airportSecurityState.util.MyLogger;
import airportSecurityState.util.MyLogger.DebugLevel;

/**
 * @author Mehul SecurityComputation computes the states based on the condition
 *
 */
public class SecurityComputation {
	private ArrayList<Integer> Days = new ArrayList<>();
	private ArrayList<String> Items = new ArrayList<>();
	ArrayList<String> probhibitedItems = new ArrayList<String>();
	private String CurrentLine;
	private int Traveller = 0;
	// private FileDisplayInterface ResultsObject = new Results();
	private String Result;
	private int averageTraffic = 0;
	private int averageProhibitedItems = 0;
	private AirportStateI CurrentState;
	int count = 1;

	/**
	 * Default Constuctor
	 */
	public SecurityComputation() {
		MyLogger.writeMessage(this.getClass().getName() + " Default Constructor is called ", DebugLevel.CONSTRUCTOR);
	}

	/**
	 * @return CurrentState
	 */
	public AirportStateI getCurrentState() {
		return CurrentState;
	}

	/**
	 * sets CurrentState
	 * 
	 * @param currentState
	 */
	public void setCurrentState(AirportStateI currentState) {
		CurrentState = currentState;
	}

	/**
	 * @return CurrentLine
	 */
	public String getCurrentLine() {
		return CurrentLine;
	}

	/**
	 * @return probhibitedItems
	 */
	public ArrayList<String> getProbhibitedItems() {
		return probhibitedItems;
	}

	/**
	 * sets probhibitedItems
	 * 
	 * @param probhibitedItems
	 */
	public void setProbhibitedItems(ArrayList<String> probhibitedItems) {
		this.probhibitedItems = probhibitedItems;
	}

	/**
	 * sets CurrentLine
	 * 
	 * @param currentLine
	 */
	public void setCurrentLine(String currentLine) {
		CurrentLine = currentLine;
		this.setTraveller(this.getTraveller() + 1);
		this.setProhibitedItems();
	}

	/**
	 * @return Travellers
	 */
	public int getTraveller() {
		return Traveller;
	}

	/**
	 * sets Traveller
	 * 
	 * @param traveller
	 */
	public void setTraveller(int traveller) {
		Traveller = traveller;
	}

	/**
	 * @return averageTraffic
	 */
	public int getAverageTraffic() {
		return averageTraffic;
	}

	/**
	 * sets AverageTraffic
	 * 
	 * @param averageTraffic
	 */
	public void setAverageTraffic(int averageTraffic) {
		this.averageTraffic = averageTraffic;
	}

	/**
	 * @return averageProhibitedItems
	 */
	public int getAverageProhibitedItems() {
		return averageProhibitedItems;
	}

	/**
	 * sets AverageProhibitedItems
	 * 
	 * @param averageProhibitedItems
	 */
	public void setAverageProhibitedItems(int averageProhibitedItems) {
		this.averageProhibitedItems = averageProhibitedItems;
	}

	/**
	 * @return Result
	 */
	public String getResult() {
		return Result;
	}

	/**
	 * sets Result
	 * 
	 * @param result
	 */
	public void setResult(String result) {
		Result = result;
	}

	/**
	 * function to calculate Average Traffic
	 * 
	 * @param dayNum
	 * @param totalPlanes
	 * @return
	 */
	public int calulateAverageTraffic(int dayNum, int noofTraveler) {
		averageTraffic = (noofTraveler / dayNum);
		return averageTraffic;
	}

	/**
	 * function to calculate Average ProhibitedItems
	 * 
	 * @param itemCount
	 * @param dayNumber
	 * @return
	 */
	public int calculateProhibitedItems(int itemCount, int dayNumber) {
		averageProhibitedItems = (itemCount / dayNumber);
		return averageProhibitedItems;
	}

	/**
	 * setProhibitedItems function sets the ProhibitedItems into the list
	 * 
	 */
	public void setProhibitedItems() {
		probhibitedItems.add("NailCutters");
		probhibitedItems.add("Grains");
		probhibitedItems.add("EndangeredAnimals");
		probhibitedItems.add("Plants");

	}

	/**
	 * CalculateFactors computes AverageTraffic and AverageProhibitedItems
	 */
	public void CalculateFactors() {
		String[] split = CurrentLine.split(";");
		Days.add(Integer.parseInt(split[0].split(":")[1]));
		if (this.getProbhibitedItems().contains(split[1].split(":")[1])) {
			Items.add(split[1].split(":")[1]);
		}
		this.setAverageTraffic(calulateAverageTraffic(Days.get(Days.size() - 1), this.getTraveller()));
		this.setAverageProhibitedItems(calculateProhibitedItems(Items.size(), Days.get(Days.size() - 1)));
	}

	/**
	 * CalculateState sets the state
	 * 
	 * @param SecurityComputationobj
	 * @param newContext
	 * @return
	 */
	public SecurityComputation CalculateState(SecurityComputation SecurityComputationobj, AirportContext newContext) {
		if (SecurityComputationobj.getAverageTraffic() >= 8
				|| SecurityComputationobj.getAverageProhibitedItems() >= 4) {
			newContext.setCurrentState(newContext.getHighRisk());
			SecurityComputationobj.setResult("2 4 6 8 10");
			MyLogger.writeMessage("State Changed to Low Risk State", DebugLevel.STATECHANGE);
		} else if ((SecurityComputationobj.getAverageTraffic() >= 4 && SecurityComputationobj.getAverageTraffic() < 8)
				|| (SecurityComputationobj.getAverageProhibitedItems() >= 2
						&& SecurityComputationobj.getAverageProhibitedItems() < 4)) {
			newContext.setCurrentState(newContext.getModerateRisk());
			SecurityComputationobj.setResult("2 3 5 8 9");
			MyLogger.writeMessage("State Changed to Low Risk State", DebugLevel.STATECHANGE);
		} else if ((SecurityComputationobj.getAverageTraffic() >= 0) && (SecurityComputationobj.getAverageTraffic() < 4)
				|| (SecurityComputationobj.getAverageProhibitedItems() >= 0)
						&& (SecurityComputationobj.getAverageProhibitedItems() < 2)) {

			newContext.setCurrentState(newContext.getLowRisk());
			SecurityComputationobj.setResult("1 3 5 7 9");
			MyLogger.writeMessage("State Changed to Low Risk State", DebugLevel.STATECHANGE);
		}
		return SecurityComputationobj;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((CurrentLine == null) ? 0 : CurrentLine.hashCode());
		result = prime * result + ((CurrentState == null) ? 0 : CurrentState.hashCode());
		result = prime * result + ((Days == null) ? 0 : Days.hashCode());
		result = prime * result + ((Items == null) ? 0 : Items.hashCode());
		result = prime * result + ((Result == null) ? 0 : Result.hashCode());
		result = prime * result + Traveller;
		result = prime * result + averageProhibitedItems;
		result = prime * result + averageTraffic;
		result = prime * result + count;
		result = prime * result + ((probhibitedItems == null) ? 0 : probhibitedItems.hashCode());
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
		SecurityComputation other = (SecurityComputation) obj;
		if (CurrentLine == null) {
			if (other.CurrentLine != null)
				return false;
		} else if (!CurrentLine.equals(other.CurrentLine))
			return false;
		if (CurrentState == null) {
			if (other.CurrentState != null)
				return false;
		} else if (!CurrentState.equals(other.CurrentState))
			return false;
		if (Days == null) {
			if (other.Days != null)
				return false;
		} else if (!Days.equals(other.Days))
			return false;
		if (Items == null) {
			if (other.Items != null)
				return false;
		} else if (!Items.equals(other.Items))
			return false;
		if (Result == null) {
			if (other.Result != null)
				return false;
		} else if (!Result.equals(other.Result))
			return false;
		if (Traveller != other.Traveller)
			return false;
		if (averageProhibitedItems != other.averageProhibitedItems)
			return false;
		if (averageTraffic != other.averageTraffic)
			return false;
		if (count != other.count)
			return false;
		if (probhibitedItems == null) {
			if (other.probhibitedItems != null)
				return false;
		} else if (!probhibitedItems.equals(other.probhibitedItems))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SecurityComputation [Days=" + Days + ", Items=" + Items + ", probhibitedItems=" + probhibitedItems
				+ ", CurrentLine=" + CurrentLine + ", Traveller=" + Traveller + ", Result=" + Result
				+ ", averageTraffic=" + averageTraffic + ", averageProhibitedItems=" + averageProhibitedItems
				+ ", CurrentState=" + CurrentState + ", count=" + count + "]";
	}
}
