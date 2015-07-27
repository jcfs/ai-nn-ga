package pt.pxinxas.fcpp.game;


public class GameContextProvider {
	private static final GameContextProvider instance = new GameContextProvider();

	private GameStaticContext context;
	
	/**
	 * @return the context
	 */
	public GameStaticContext getContext() {
		return context;
	}

	/**
	 * @param context
	 *            the context to set
	 */
	public void setContext(GameStaticContext context) {
		this.context = context;
	}

	/**
	 * @return the instance
	 */
	public static GameContextProvider getInstance() {
		return instance;
	}

}
