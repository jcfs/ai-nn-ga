package pt.pxinxas.fcpp.camera;

import pt.pxinxas.fcpp.entity.Entity;
import pt.pxinxas.fcpp.util.Constants;

import com.badlogic.gdx.graphics.OrthographicCamera;

public class TopDownCamera implements GameCamera {

	private final OrthographicCamera camera = new OrthographicCamera(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
	private Entity entity;

	public TopDownCamera(Entity entity) {
		this.entity = entity;
	}

	@Override
	public void update(float delta) {

		this.camera.position.set((float) entity.getPosition().x, (float) entity.getPosition().y, 0);
		this.camera.update(true);
	}

	@Override
	public OrthographicCamera getCamera() {
		return camera;
	}

	/**
	 * @return the entity
	 */
	public Entity getEntity() {
		return entity;
	}

	/**
	 * @param entity
	 *            the entity to set
	 */
	public void setEntity(Entity entity) {
		this.entity = entity;
	}

}
