package airportSecurityState.airportStates;

import airportSecurityState.securityfactorcomputation.SecurityComputation;
import airportSecurityState.util.MyLogger;
import airportSecurityState.util.MyLogger.DebugLevel;

public class AirportContext implements AirportContextI {

	private AirportStateI LowRisk;
	private AirportStateI ModerateRisk;
	private AirportStateI HighRisk;
	private AirportStateI CurrentState;

	/**
	 * @return LowRisk
	 */
	public AirportStateI getLowRisk() {
		return LowRisk;
	}

	/**
	 * sets LowRisk
	 * 
	 * @param lowRisk
	 */
	public void setLowRisk(AirportStateI lowRisk) {
		LowRisk = lowRisk;
	}

	/**
	 * @return ModerateRisk
	 */
	public AirportStateI getModerateRisk() {
		return ModerateRisk;
	}

	/**
	 * sets ModerateRisk
	 * 
	 * @param moderateRisk
	 */
	public void setModerateRisk(AirportStateI moderateRisk) {
		ModerateRisk = moderateRisk;
	}

	/**
	 * @return HighRisk
	 */
	public AirportStateI getHighRisk() {
		return HighRisk;
	}

	/**
	 * sets HighRisk
	 * 
	 * @param highRisk
	 */
	public void setHighRisk(AirportStateI highRisk) {
		HighRisk = highRisk;
	}

	/**
	 * @return CurrentState
	 */
	public AirportStateI getCurrentState() {
		return CurrentState;
	}

	/**
	 * sets CurrentState
	 * 
	 * @param currentState
	 */
	public void setCurrentState(AirportStateI currentState) {
		CurrentState = currentState;
	}

	/**
	 * 
	 * AirportContext constructor sets all state
	 */
	public AirportContext() {
		LowRisk = new LowRiskState(this);
		ModerateRisk = new ModerateRiskState(this);
		HighRisk = new HighRiskState(this);
		setCurrentState(LowRisk);
		MyLogger.writeMessage("AirportContext Contructor", DebugLevel.CONSTRUCTOR);
	}

	@Override
	public SecurityComputation lineProcessing(SecurityComputation SecurityComputationObject) {
		SecurityComputationObject = CurrentState.increaseOrDecreaseSecurity(SecurityComputationObject);
		return SecurityComputationObject;
	}

	@Override
	public String toString() {
		return "AirportContext [LowRisk=" + LowRisk + ", ModerateRisk=" + ModerateRisk + ", HighRisk=" + HighRisk
				+ ", CurrentState=" + CurrentState + "]";
	}
}
