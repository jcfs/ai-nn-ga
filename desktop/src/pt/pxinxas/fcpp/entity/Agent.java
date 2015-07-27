package pt.pxinxas.fcpp.entity;

import java.util.List;

import pt.pxinxas.fcpp.ai.Genome;
import pt.pxinxas.fcpp.ai.NeuralNetwork;
import pt.pxinxas.fcpp.game.ClientGameStaticContext;
import pt.pxinxas.fcpp.input.ClientPlayerInputController;
import pt.pxinxas.fcpp.input.Input;
import pt.pxinxas.fcpp.input.InputController;
import pt.pxinxas.fcpp.manager.WorldManager;
import pt.pxinxas.fcpp.raycast.RayCaster;
import pt.pxinxas.fcpp.util.Point;
import pt.pxinxas.fcpp.util.SpatialHashMap;
import pt.pxinxas.fcpp.util.Vector;

public class Agent extends Player {

	private Float targetAngle;

	private final NeuralNetwork network = new NeuralNetwork(9, 4, 1, 50);

	private Input input = new Input();
	private Genome genome;
	private boolean stuck;

	private final Integer DISTANCE_UPDATE = 100;
	private float distanceTimer;
	private double oldDistance;
	private float rightSensorDistance;
	private float leftSensorDistance;
	private float topSensorDistance;
	private float downSensorDistance;
	private float seSensorDistance;
	private float swSensorDistance;
	private float neSensorDistance;
	private float nwSensorDistance;
	private float timer;

	public Agent() {
		WorldManager.getInstance().addWorldObject(this);
	}

	public Agent(PlayerTeam playerTeam, Integer id, Genome genome) {
		this.genome = genome;
		this.team = playerTeam;
		this.id = id;
		WorldManager.getInstance().addWorldObject(this);
		this.setPosition(new Vector(ClientGameStaticContext.getInstance().getSpawnPoint().x, ClientGameStaticContext.getInstance().getSpawnPoint().y));
		this.network.importWeights(genome.getGenes());
	}

	@Override
	public void update(float delta) {
		super.update(delta);

		SpatialHashMap spatialMap = WorldManager.getInstance().getSpatialMap();

		// Update sensors information
		Point point = new Point(this.getPosition().x, this.getPosition().y);
		Vector direction = new Vector(1, 0);
		Point rightSensor = RayCaster.getInstance().castRay(point, direction, 800, spatialMap);
		direction = direction.rotate(-45);
		Point seSensor = RayCaster.getInstance().castRay(point, direction, 800, spatialMap);
		direction = direction.rotate(-45);
		Point downSensor = RayCaster.getInstance().castRay(point, direction, 800, spatialMap);
		direction = direction.rotate(-45);
		Point swSensor = RayCaster.getInstance().castRay(point, direction, 800, spatialMap);
		direction = direction.rotate(-45);
		Point leftSensor = RayCaster.getInstance().castRay(point, direction, 800, spatialMap);
		direction = direction.rotate(-45);
		Point nwSensor = RayCaster.getInstance().castRay(point, direction, 800, spatialMap);
		direction = direction.rotate(-45);
		Point topSensor = RayCaster.getInstance().castRay(point, direction, 800, spatialMap);
		direction = direction.rotate(-45);
		Point neSensor = RayCaster.getInstance().castRay(point, direction, 800, spatialMap);

		targetAngle = (float) Math.toDegrees(Math.atan2(this.getPosition().x - ClientGameStaticContext.getInstance().getTargetPoint().x,
				this.getPosition().y - ClientGameStaticContext.getInstance().getTargetPoint().y));

		rightSensorDistance = (float) rightSensor.distance(point);
		leftSensorDistance = (float) leftSensor.distance(point);
		topSensorDistance = (float) topSensor.distance(point);
		downSensorDistance = (float) downSensor.distance(point);

		seSensorDistance = (float) seSensor.distance(point);
		swSensorDistance = (float) swSensor.distance(point);
		neSensorDistance = (float) neSensor.distance(point);
		nwSensorDistance = (float) nwSensor.distance(point);

		// input the sensor information to the network
		List<Float> outputs = network.update(rightSensorDistance, leftSensorDistance, topSensorDistance, downSensorDistance, seSensorDistance,
				swSensorDistance, neSensorDistance, nwSensorDistance, targetAngle);

		input = new Input(outputs);

		distanceTimer += delta;
		if (distanceTimer > DISTANCE_UPDATE) {
			distanceTimer = 0;
			Point targetPoint = ClientGameStaticContext.getInstance().getTargetPoint();
			double newDistance = this.getPosition().getDistance((float) targetPoint.x - (float) this.getPosition().x,
					(float) targetPoint.y - (float) this.getPosition().y);

			if (newDistance == oldDistance) {
				stuck = true;
			} else {
				oldDistance = newDistance;
			}
		}
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	protected Input getCurrentInput() {
		return input;
	}

	@Override
	protected InputController getInputController() {
		return new ClientPlayerInputController();
	}

	/**
	 * @return the genome
	 */
	public Genome getGenome() {
		return genome;
	}

	/**
	 * @return
	 */
	public boolean isStuck() {
		return stuck;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Agent [" + " rightSensorDistance=" + rightSensorDistance + ", leftSensorDistance=" + leftSensorDistance + ", topSensorDistance="
				+ topSensorDistance + ", downSensorDistance=" + downSensorDistance + ", seSensorDistance=" + seSensorDistance + ", swSensorDistance="
				+ swSensorDistance + ", neSensorDistance=" + neSensorDistance + ", nwSensorDistance=" + nwSensorDistance + "]";
	}

	/**
	 * @param timer
	 */
	public void setTimeToStuck(float timer) {
		this.timer = timer;

	}

	/**
	 * @return the timer
	 */
	public float getTimer() {
		return timer;
	}

}
