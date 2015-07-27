package pt.pxinxas.fcpp.system;

import pt.pxinxas.fcpp.entity.Direction;
import pt.pxinxas.fcpp.entity.Entity;

/**
 * @author jcfs
 */
public class TopDownMovementSystem extends EntitySystem {

    private static final int FRICTION_COEFITIEN = 1;
    private static final TopDownMovementSystem instance = new TopDownMovementSystem();

    private TopDownMovementSystem() {

    }

    @Override
    public void update(Entity entity, float delta) {
        applyAcceleration(entity, delta);
        friction(entity, delta);
        updateLinearSpeed(entity, delta);
    }

    private void applyAcceleration(Entity entity, float delta) {

        entity.getAcceleration().x = 0;
        entity.getAcceleration().y = 0;

        if (entity.getDirections().contains(Direction.LEFT)) {
            entity.getAcceleration().x += -entity.getMaxAcceleration().x;
        }

        if (entity.getDirections().contains(Direction.RIGHT)) {
            entity.getAcceleration().x += entity.getMaxAcceleration().x;
        }

        if (entity.getDirections().contains(Direction.UP)) {
            entity.getAcceleration().y += entity.getMaxAcceleration().y;
        }

        if (entity.getDirections().contains(Direction.DOWN)) {
            entity.getAcceleration().y += -entity.getMaxAcceleration().y;
        }
    }

    /**
     * Formula applied to calculate the entity speed: v = v0 + at
     * The speed is capped by the maxspeed vector
     *
     *
     * @param entity
     * @param delta
     */
    protected void updateLinearSpeed(Entity entity, float delta) {
        entity.getSpeed().x += entity.getSpeed().x + entity.getAcceleration().x * delta;
        entity.getSpeed().y += entity.getSpeed().y + entity.getAcceleration().y * delta;

        double len = entity.getSpeed().len();
        double maxLen = entity.getMaxSpeed().len();

        if (len > maxLen) {
            double factor = maxLen / len;
            entity.getSpeed().x *= factor;
            entity.getSpeed().y *= factor;
        }
    }

    /**
     * Function that adds some friction to the entity speed
     *
     * @param delta
     */
    private void friction(Entity entity, float delta) {
        if (entity.isHasFriction()) {
            double oldx = entity.getSpeed().x;
            double oldy = entity.getSpeed().y;

            // if we are not acceleration on this axis and we are not stopped - friction is applied
            if (entity.getAcceleration().x == 0 && Math.abs(entity.getSpeed().x) != 0) {
                entity.getSpeed().x += -Math.signum(entity.getSpeed().x) * entity.getMaxAcceleration().x * FRICTION_COEFITIEN * delta;

                // if the friction is so much that we changed direction we stop
                if (Math.signum(oldx) != Math.signum(entity.getSpeed().x)) {
                    entity.getSpeed().x = 0;
                }
            }

            // if we are not acceleration on this axis and we are not stopped - friction is applied
            if (entity.getAcceleration().y == 0 && Math.abs(entity.getSpeed().y) != 0) {
                entity.getSpeed().y += -Math.signum(entity.getSpeed().y) * entity.getMaxAcceleration().y * FRICTION_COEFITIEN * delta;

                // if the friction is so much that we changed direction we stop
                if (Math.signum(oldy) != Math.signum(entity.getSpeed().y)) {
                    entity.getSpeed().y = 0;
                }
            }
        }
    }

    /**
     * @return the instance
     */
    public static TopDownMovementSystem getInstance() {
        return instance;
    }

}
