package airportSecurityState.airportStates;

import airportSecurityState.securityfactorcomputation.SecurityComputation;
import airportSecurityState.util.MyLogger;
import airportSecurityState.util.MyLogger.DebugLevel;

/**
 * HighRiskState impelements AirportStateI
 * 
 * @author Mehul
 *
 */
public class HighRiskState implements AirportStateI {
	private AirportContext newContext;

	/**
	 * Constructor sets the AirportContext
	 * 
	 * @param highContext
	 */
	public HighRiskState(AirportContext highContext) {
		MyLogger.writeMessage(this.getClass().getName() + " Constructor is called ", DebugLevel.CONSTRUCTOR);
		newContext = highContext;
	}

	@Override
	public SecurityComputation increaseOrDecreaseSecurity(SecurityComputation sc) {
		sc.CalculateState(sc, newContext);
		return sc;
	}

	@Override
	public String toString() {
		return "HighRiskState [newContext=" + newContext + "]";
	}

}
