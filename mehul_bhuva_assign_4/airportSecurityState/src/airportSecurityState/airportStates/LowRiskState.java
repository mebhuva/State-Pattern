package airportSecurityState.airportStates;

import airportSecurityState.securityfactorcomputation.SecurityComputation;

public class LowRiskState implements AirportStateI {

	private AirportContext newContext;

	public LowRiskState(AirportContext lowContext) {
		newContext = lowContext;
	}
	@Override
	public SecurityComputation increaseOrDecreaseSecurity(SecurityComputation sc) {
		sc.CalculateState(sc, newContext);
		return sc;
	}

}
