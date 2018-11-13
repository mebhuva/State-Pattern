package airportSecurityState.airportStates;

import airportSecurityState.securityfactorcomputation.SecurityComputation;

/**
 * AirportStateI interface which low, moderate and high state implements
 * 
 * @author Mehul
 *
 */
public interface AirportStateI {
	/**
	 * 
	 * increaseOrDecreaseSecurity is written to change state based on condition
	 * 
	 * @param sc
	 * @return the SecurityComputation objects with state and result
	 */
	public SecurityComputation increaseOrDecreaseSecurity(SecurityComputation sc);
}
