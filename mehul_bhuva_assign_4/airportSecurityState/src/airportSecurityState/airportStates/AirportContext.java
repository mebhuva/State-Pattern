package airportSecurityState.airportStates;

import airportSecurityState.securityfactorcomputation.SecurityComputation;
import airportSecurityState.util.MyLogger;
import airportSecurityState.util.MyLogger.DebugLevel;

public class AirportContext {
	private AirportStateI LowRisk;
	private AirportStateI ModerateRisk;
	private AirportStateI HighRisk;
	private AirportStateI CurrentState;
	public AirportStateI getLowRisk() {
		return LowRisk;
	}

	public void setLowRisk(AirportStateI lowRisk) {
		LowRisk = lowRisk;
	}

	public AirportStateI getModerateRisk() {
		return ModerateRisk;
	}

	public void setModerateRisk(AirportStateI moderateRisk) {
		ModerateRisk = moderateRisk;
	}

	public AirportStateI getHighRisk() {
		return HighRisk;
	}

	public void setHighRisk(AirportStateI highRisk) {
		HighRisk = highRisk;
	}

	public AirportStateI getCurrentState() {
		return CurrentState;
	}

	public void setCurrentState(AirportStateI currentState) {
		CurrentState = currentState;
	}
	
	public AirportContext() {
	LowRisk = new LowRiskState(this);
	ModerateRisk = new ModerateRiskState(this);
	HighRisk = new HighRiskState(this);
	setCurrentState(LowRisk);
	MyLogger.writeMessage("AirportContext Contructor", DebugLevel.CONSTRUCTOR);
	}
	
	
	public SecurityComputation lineProcessing(SecurityComputation SecurityComputationObject) {
		SecurityComputationObject = CurrentState.increaseOrDecreaseSecurity(SecurityComputationObject);
		return SecurityComputationObject;
	}
}
