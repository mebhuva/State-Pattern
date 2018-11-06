package airportSecurityState.airportStates;

import airportSecurityState.securityfactorcomputation.SecurityComputation;

public class HighRiskState implements AirportStateI{
	private AirportContext newContext;

	public HighRiskState(AirportContext lowContext) {
		newContext = lowContext;
	}
	@Override
	public SecurityComputation increaseOrDecreaseSecurity(SecurityComputation sc) {
		sc.CalculateState(sc, newContext);
		return sc;
	}

}
