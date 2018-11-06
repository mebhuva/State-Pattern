package airportSecurityState.airportStates;

import airportSecurityState.securityfactorcomputation.SecurityComputation;

public interface AirportStateI {
	public SecurityComputation increaseOrDecreaseSecurity(SecurityComputation sc);
}
