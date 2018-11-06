package airportSecurityState.airportStates;

import airportSecurityState.securityfactorcomputation.SecurityComputation;

public class ModerateRiskState implements AirportStateI {

	private AirportContext newContext;

	public ModerateRiskState(AirportContext lowContext) {
		newContext = lowContext;
	}
	
	@Override
	public SecurityComputation increaseOrDecreaseSecurity(SecurityComputation sc) {
		sc.CalculateState(sc, newContext);
		return sc;
	}

}
