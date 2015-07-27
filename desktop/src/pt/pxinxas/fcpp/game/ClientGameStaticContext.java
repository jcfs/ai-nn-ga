package pt.pxinxas.fcpp.game;

import pt.pxinxas.fcpp.util.Point;

/**
 * @author jcfs
 */
public class ClientGameStaticContext implements GameStaticContext {
	private static final ClientGameStaticContext instance = new ClientGameStaticContext();

	/**
	 * The map Id of the game
	 */
	private Integer mapId;

	private Point spawnPoint;

	private Point targetPoint;

	private ClientGameStaticContext() {
		// private constructor - ensure it's a singleton
	}

	public synchronized void reset() {
		this.mapId = null;
	}

	public synchronized Integer getMapId() {
		return this.mapId;
	}

	public synchronized void setMapId(Integer mapId) {
		this.mapId = mapId;
	}

	public static ClientGameStaticContext getInstance() {
		return instance;
	}

	public void setSpawnPoint(Point spawnPoint) {
		this.spawnPoint = spawnPoint;
	}

	public void setTargetPoint(Point targetPoint) {
		this.targetPoint = targetPoint;
	}

	public Point getSpawnPoint() {
		return spawnPoint;
	}

	public Point getTargetPoint() {
		return targetPoint;
	}

}
