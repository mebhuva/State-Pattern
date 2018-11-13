package airportSecurityState.airportStates;

import airportSecurityState.securityfactorcomputation.SecurityComputation;
import airportSecurityState.util.MyLogger;
import airportSecurityState.util.MyLogger.DebugLevel;

/**
 * LowRiskState impelements AirportStateI
 * 
 * @author Mehul
 *
 */
public class LowRiskState implements AirportStateI {

	private AirportContext newContext;

	/**
	 * Constructor sets the AirportContext
	 * 
	 * @param lowContext
	 */
	public LowRiskState(AirportContext lowContext) {
		MyLogger.writeMessage(this.getClass().getName() + " Constructor is called ", DebugLevel.CONSTRUCTOR);
		newContext = lowContext;
	}

	@Override
	public SecurityComputation increaseOrDecreaseSecurity(SecurityComputation sc) {
		sc.CalculateState(sc, newContext);
		return sc;
	}

	@Override
	public String toString() {
		return "LowRiskState [newContext=" + newContext + "]";
	}
}
