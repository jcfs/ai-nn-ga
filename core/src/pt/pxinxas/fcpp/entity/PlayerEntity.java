package pt.pxinxas.fcpp.entity;

import pt.pxinxas.fcpp.system.CollisionAxis;

public abstract class PlayerEntity extends ControlableEntity {
	public PlayerEntity() {
	}

	/**
	 * Player Entities collide with everything except their own children, ground
	 * entities and projectiles
	 *
	 * (non-Javadoc)
	 *
	 * @see pt.pxinxas.fcpp.entity.Entity#collidesWith(pt.pxinxas.fcpp.entity.Entity)
	 */
	@Override
	public boolean collidesWith(Entity entity) {
		if (entity.getParent() != null && entity.getParent() == this || entity instanceof PlayerEntity) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public void collideWithEntity(Entity ent, float delta, CollisionAxis axis) {

	}

	@Override
	public void collidedByEntity(Entity ent, float delta) {

	}

	@Override
	public boolean pushEntity(WorldObject entity) {
		return false;
	}

}
