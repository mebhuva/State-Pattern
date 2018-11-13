package airportSecurityState.airportStates;

import airportSecurityState.securityfactorcomputation.SecurityComputation;
import airportSecurityState.util.MyLogger;
import airportSecurityState.util.MyLogger.DebugLevel;

/**
 * ModerateRiskState impelements AirportStateI
 * 
 * @author Mehul
 *
 */
public class ModerateRiskState implements AirportStateI {

	private AirportContext newContext;

	/**
	 * Constructor sets the AirportContext
	 * 
	 * @param moderateContext
	 */
	public ModerateRiskState(AirportContext moderateContext) {
		MyLogger.writeMessage(this.getClass().getName() + " Constructor is called ", DebugLevel.CONSTRUCTOR);
		newContext = moderateContext;
	}

	@Override
	public SecurityComputation increaseOrDecreaseSecurity(SecurityComputation sc) {
		sc.CalculateState(sc, newContext);
		return sc;
	}

	@Override
	public String toString() {
		return "ModerateRiskState [newContext=" + newContext + "]";
	}
}
