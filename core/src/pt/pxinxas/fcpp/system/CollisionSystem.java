package pt.pxinxas.fcpp.system;

import java.util.Set;

import pt.pxinxas.fcpp.entity.Entity;
import pt.pxinxas.fcpp.entity.Status;
import pt.pxinxas.fcpp.manager.WorldManager;

/**
 * @author jcfs
 */
public class CollisionSystem extends EntitySystem {

	private static final CollisionSystem instance = new CollisionSystem();

	private CollisionSystem() {

	}

	@Override
	public void update(Entity entity, float delta) {
		handleLinearMovementCollision(entity, delta);
		handleStaticCollision(entity, delta);
		if (entity.getBoundingBox() != null) {
			entity.getBoundingBox().update();
		}
	}

	/**
	 * @param entity
	 * @param delta
	 */
	private void handleLinearMovementCollision(Entity entity, float delta) {
		float increment = 1f;

		for (float i = 1; i < (Math.abs(entity.getSpeed().x) * delta); i += increment) {
			if (i < (Math.abs(entity.getSpeed().x) * delta)) {
				entity.getPosition().x += Math.signum(entity.getSpeed().x) * increment;
				entity.getBoundingBox().update();

				CollisionAxis axis = null;
				if (Math.signum(entity.getSpeed().x) < 0) {
					axis = CollisionAxis.X_R;
				}
				if (Math.signum(entity.getSpeed().x) > 0) {
					axis = CollisionAxis.X_L;
				}

				Entity collidedEntity = checkWorldCollision(entity);

				if (collidedEntity != null) {
					if (entity.pushEntity(collidedEntity)) {
						collidedEntity.getPosition().x += Math.signum(entity.getSpeed().x) * increment;
						collidedEntity.getBoundingBox().update();
					} else {
						while (collides(entity, collidedEntity)) {
							entity.getPosition().x -= Math.signum(entity.getSpeed().x) * increment;
							entity.getBoundingBox().update();
						}

						entity.collideWithEntity(collidedEntity, delta, axis);
						collidedEntity.collidedByEntity(entity, delta);
						entity.getSpeed().x = 0;
					}
					break;
				}
			}
		}

		for (float j = 1; j < (Math.abs(entity.getSpeed().y) * delta); j += increment) {
			if (j < (Math.abs(entity.getSpeed().y) * delta)) {
				entity.getPosition().y += Math.signum(entity.getSpeed().y) * increment;
				entity.getBoundingBox().update();

				CollisionAxis axis = null;
				if (Math.signum(entity.getSpeed().y) < 0) {
					axis = CollisionAxis.Y_U;
				}
				if (Math.signum(entity.getSpeed().y) > 0) {
					axis = CollisionAxis.Y_D;
				}

				Entity collidedEntity = checkWorldCollision(entity);

				if (collidedEntity != null) {
					if (entity.pushEntity(collidedEntity)) {
						collidedEntity.getPosition().y += Math.signum(entity.getSpeed().y) * increment;
						collidedEntity.getBoundingBox().update();
					} else {
						while (collides(entity, collidedEntity)) {
							entity.getPosition().y -= Math.signum(entity.getSpeed().y) * increment;
							entity.getBoundingBox().update();
						}

						entity.collideWithEntity(collidedEntity, delta, axis);
						collidedEntity.collidedByEntity(entity, delta);
						entity.getSpeed().y = 0;
					}
					break;
				}
			}
		}
	}

	private void handleStaticCollision(Entity entity, float delta) {
		Entity collided = checkWorldCollision(entity);
		if (collided != null) {
			entity.collideWithEntity(collided, delta, null);
			collided.collidedByEntity(entity, delta);
		}
	}

	public Entity checkWorldCollision(Entity entity) {
		Entity e = null;

		Set<Entity> entities = WorldManager.getInstance().getSpatialMap().get(entity);
		if (entities != null) {
			for (Entity currentEntity : entities) {
				if (currentEntity instanceof Entity && currentEntity != entity && currentEntity.getStatus() == Status.ACTIVE
						&& entity.getStatus() == Status.ACTIVE) {
					e = currentEntity;
					if (collides(entity, e)) {
						return e;
					} else {
						e = null;
					}
				}
			}
		}
		return e;
	}

	/**
	 * @param entity1
	 * @param entity2
	 * @return
	 */
	public boolean collides(Entity entity1, Entity entity2) {
		if (entity2.getStatus() == Status.ACTIVE && entity2.getBoundingBox() != null) {
			return entity1.collidesWith(entity2) && entity1.getBoundingBox().intersects(entity2.getBoundingBox());
		} else {
			return false;
		}
	}

	/**
	 * @return the instance
	 */
	public static CollisionSystem getInstance() {
		return instance;
	}

}
