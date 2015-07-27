package pt.pxinxas.fcpp.manager;

public class RunningMode {
	private static final RunningMode instance = new RunningMode();

	private RunningModeType type;

	private RunningMode() {

	}

	/**
	 * @return the instance
	 */
	public static RunningMode getInstance() {
		return instance;
	}

	/**
	 * @return the type
	 */
	public RunningModeType getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(RunningModeType type) {
		this.type = type;
	}

}
