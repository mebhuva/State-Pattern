package airportSecurityState.airportStates;

import airportSecurityState.securityfactorcomputation.SecurityComputation;

public interface AirportContextI {
	public SecurityComputation lineProcessing(SecurityComputation SecurityComputationObject);
}
