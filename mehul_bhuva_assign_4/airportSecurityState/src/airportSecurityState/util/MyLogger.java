package airportSecurityState.util;

/**
 * MyLogger gives debug level
 * @author Mehul
 *
 */
public class MyLogger {
	// FIXME: Add more enum values as needed for the assignment
	public static enum DebugLevel {
		CONSTRUCTOR , NONE , EXCEPTION
	};

	private static DebugLevel debugLevel;

	// FIXME: Add switch cases for all the levels
	/**
	 * setDebugValue sets debug level
	 * @param levelIn
	 */
	public static void setDebugValue(int levelIn) {
		switch (levelIn) {
		case 2:
            debugLevel = DebugLevel.EXCEPTION;
            break;
        case 1:
            debugLevel = DebugLevel.CONSTRUCTOR; 
			break;
		default:
			debugLevel = DebugLevel.NONE;
			break;
		}
	}

	/**
	 * set current debug level
	 * @param levelIn
	 */
	public static void setDebugValue(DebugLevel levelIn) {
		debugLevel = levelIn;
	}

	/**
	 * To display message
	 * @param message
	 * @param levelIn
	 */
	public static void writeMessage(String message, DebugLevel levelIn) {
		if (levelIn == debugLevel)
			System.out.println(message);
	}

	public String toString() {
		return "The debug level has been set to the following " + debugLevel;
	}
}
