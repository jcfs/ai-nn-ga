package pt.pxinxas.fcpp.screen;

import java.util.ArrayList;
import java.util.List;

import pt.pxinxas.fcpp.ai.GeneticAlgorithm;
import pt.pxinxas.fcpp.ai.Genome;
import pt.pxinxas.fcpp.entity.Agent;
import pt.pxinxas.fcpp.entity.PlayerTeam;
import pt.pxinxas.fcpp.entity.Status;
import pt.pxinxas.fcpp.game.ClientGameStaticContext;
import pt.pxinxas.fcpp.manager.CameraManager;
import pt.pxinxas.fcpp.manager.ClientMapManager;
import pt.pxinxas.fcpp.manager.SpriteBatchManager;
import pt.pxinxas.fcpp.manager.WorldManager;
import pt.pxinxas.fcpp.util.Point;
import pt.pxinxas.fcpp.util.Vector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;

public class AiClientMatchScreen implements Screen {

	private static final int POPULATION_SIZE = 30;
	private static final float TTL = 10000;

	float deltaTime = 1f / 60f * 1000;
	double currentTime = System.currentTimeMillis();
	float accumulator = 0.0f;
	float timer = 0;
	boolean populationAlive = false;
	int done;

	private final GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(POPULATION_SIZE, 704);
	private final List<Agent> activePopulation = new ArrayList<Agent>();
	private Point targetPoint;
	private Point spawnPoint;

	public AiClientMatchScreen() {
	}

	/**
	 * (non-Javadoc)
	 *
	 * @see com.badlogic.gdx.Screen#show()
	 */
	@Override
	public void show() {
		ClientMapManager.getInstance().loadMap(-1);
		targetPoint = ClientGameStaticContext.getInstance().getTargetPoint();
		spawnPoint = ClientGameStaticContext.getInstance().getSpawnPoint();
	}

	/**
	 * (non-Javadoc)
	 *
	 * @see com.badlogic.gdx.Screen#render(float)
	 */
	@Override
	public void render(float delta) {
		update(delta);
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT | GL30.GL_DEPTH_BUFFER_BIT);
		Gdx.gl.glClearColor(0, 0, 0, 1);

		SpriteBatchManager.getInstance().getBatch().setProjectionMatrix(CameraManager.getInstance().getActiveCamera().combined);
		SpriteBatchManager.getInstance().getBatch().begin();

		ClientMapManager.getInstance().renderBottomLayer();
		WorldManager.getInstance().render(delta);
		SpriteBatchManager.getInstance().getBatch().end();

		SpriteBatchManager.getInstance().getBatch().setProjectionMatrix(CameraManager.getInstance().getActiveCamera().combined);
		SpriteBatchManager.getInstance().getBatch().begin();
		ClientMapManager.getInstance().renderTopLayer();
		SpriteBatchManager.getInstance().getBatch().end();

	}

	/**
	 * @param delta
	 */
	public void update(float delta) {

		float millisecondsDelta = delta * 1000.0f;

		if (!populationAlive) {
			generateNewPopulation();
		}

		/**
		 * Start of update methods
		 */

		double newTime = System.currentTimeMillis();
		double frameTime = newTime - currentTime;
		currentTime = newTime;
		accumulator += frameTime;

		while (accumulator >= deltaTime) {
			WorldManager.getInstance().update(deltaTime);
			accumulator -= deltaTime;
		}

		CameraManager.getInstance().update(millisecondsDelta);
		ClientMapManager.getInstance().update(millisecondsDelta);

		if (populationAlive) {
			timer += millisecondsDelta;

			if (done == POPULATION_SIZE || timer > TTL) {
				done = 0;
				timer = 0;
				float maxFit = -100000;
				Agent fittest = null;
				for (Agent agent : activePopulation) {
					Vector position = agent.getPosition();

					double targetDistance = -(position.getDistance((float) targetPoint.x - (float) position.x, (float) targetPoint.y
							- (float) position.y));
					float fitness = (float) targetDistance * 1f;
					agent.getGenome().setFitness(fitness * 0.99f - agent.getTimer() * 0.01f);

					if (fitness > maxFit) {
						maxFit = fitness;
						fittest = agent;
					}

					agent.remove();
				}

				System.out.println(fittest);
				geneticAlgorithm.breed();
				activePopulation.clear();
				populationAlive = false;
			} else {
				for (Agent agent : activePopulation) {
					if (agent.isStuck() && agent.getStatus() != Status.DONE) {
						agent.setStatus(Status.DONE);
						agent.setTimeToStuck(timer);
						done++;
					}
				}
			}
		}
	}

	private void generateNewPopulation() {
		List<Genome> population = geneticAlgorithm.getPopulation();

		activePopulation.clear();
		done = 0;
		for (Genome genome : population) {
			genome.setFitness(0);
			activePopulation.add(new Agent(PlayerTeam.RED, null, genome));
		}
		System.out.println("New generation " + geneticAlgorithm.getGeneration() + " " + population.size() + "  " + done);
		populationAlive = true;
	}

	@Override
	public void dispose() {
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {
	}

}
