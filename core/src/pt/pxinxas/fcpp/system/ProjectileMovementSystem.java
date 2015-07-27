package pt.pxinxas.fcpp.system;

import pt.pxinxas.fcpp.entity.Entity;

/**
 * @author jcfs
 */
public class ProjectileMovementSystem extends EntitySystem {

    private static final ProjectileMovementSystem instance = new ProjectileMovementSystem();

    private ProjectileMovementSystem() {

    }

    @Override
    public void update(Entity entity, float delta) {
        updateProjectileSpeed(entity, delta);
    }

    /**
     * Formula applied to calculate the projectile speed: v = v0 + at
     * The speed is capped by the maxspeed vector
     *
     * @param entity
     * @param delta
     */
    protected void updateProjectileSpeed(Entity entity, float delta) {
        entity.getSpeed().x += entity.getAcceleration().x * delta;
        entity.getSpeed().y += entity.getAcceleration().y * delta;

        double len = entity.getSpeed().len();
        double maxLen = entity.getMaxSpeed().len();

        if (len > maxLen) {
            double factor = maxLen / len;
            entity.getSpeed().x *= factor;
            entity.getSpeed().y *= factor;
        }
    }

    /**
     * @return the instance
     */
    public static ProjectileMovementSystem getInstance() {
        return instance;
    }

}
