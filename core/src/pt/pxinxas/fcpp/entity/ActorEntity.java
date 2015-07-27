package pt.pxinxas.fcpp.entity;


public abstract class ActorEntity extends AnimatedEntity {

	protected boolean abilitiesActive = true;

	public ActorEntity() {
	}

	/**
	 * (non-Javadoc)
	 *
	 * @see pt.pxinxas.fcpp.entity.Entity#update(float)
	 */
	@Override
	public void update(float delta) {
		super.update(delta);

	}

	@Override
	public void render() {
		super.render();
	}

	public void hit(Entity parent) {

	}

	public void getHit() {

	}

}
