package pt.pxinxas.fcpp.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import pt.pxinxas.fcpp.boundingbox.BoundingBox;
import pt.pxinxas.fcpp.manager.MatchAssetManager;
import pt.pxinxas.fcpp.manager.SpriteBatchManager;
import pt.pxinxas.fcpp.system.CollisionAxis;
import pt.pxinxas.fcpp.system.CollisionSystem;
import pt.pxinxas.fcpp.system.EntitySystem;
import pt.pxinxas.fcpp.system.SystemManager;
import pt.pxinxas.fcpp.system.TopDownMovementSystem;
import pt.pxinxas.fcpp.util.Vector;

import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Base Entity
 *
 * @author JSalavisa
 */
public abstract class Entity extends WorldObject {
    protected EntityState previousState = new EntityState();
    protected EntityState currentState = new EntityState();
    protected Vector renderPosition = new Vector(0, 0);

    /** Motion and position fields */
    protected Vector maxSpeed = new Vector(0.25f, 0.25f);
    protected Vector maxAcceleration = new Vector(0.08f, 0.08f);

    protected Vector speed = new Vector(0, 0);
    protected Vector acceleration = new Vector(0, 0);

    protected float alpha = 1f;

    protected boolean hasFriction = true;

    protected List<Direction> directions = new ArrayList<Direction>();

    protected EntityType type;
    protected Entity parent;
    protected Sprite sprite;

    protected SystemManager systemManager = new SystemManager();
    protected BoundingBox boundingBox;

    public Entity() {
        loadTexture();
        loadSystems();
    }

    /**
     * Method that determines if this entity blocks line of sight
     *
     * @return
     */
    public boolean blocksLineOfSight() {
        return false;
    }

    /**
     * @return
     */
    public abstract String getTextureFile();

    /**
     * Method that determines if an entity should collide with another
     *
     * @param entity
     *            the entity
     * @return
     */
    public abstract boolean collidesWith(Entity entity);

    /**
     * Method that determines if this entity should push the other
     *
     * @param entity
     * @return
     */
    public abstract boolean pushEntity(WorldObject entity);

    /**
     * Method that is called when this entity collides another entity
     *
     * @param ent
     *            the entity being collided
     * @param delta
     *            delta time
     * @param axis
     */
    public abstract void collideWithEntity(Entity ent, float delta, CollisionAxis axis);

    /**
     * Method that is called when this entity is collided by another entity
     *
     * @param ent
     *            the entity colliding this entity
     * @param delta
     *            delta time
     */
    public abstract void collidedByEntity(Entity ent, float delta);

    /**
     * Verifies if the entity should be rendered rendered
     *
     * @return
     */
    @Override
    public abstract boolean shouldRender();

    /**
     * (non-Javadoc)
     *
     * @see pt.pxinxas.fcpp.entity.WorldObject#update(float)
     */
    @Override
    public void update(float delta) {
        this.systemManager.update(this, delta);
        if (this.boundingBox != null) {
            this.getBoundingBox().update();
        }
    }

    /**
     * (non-Javadoc)
     *
     * @see pt.pxinxas.fcpp.entity.WorldObject#render()
     */
    @Override
    public void render() {
        if (sprite != null && shouldRender()) {
            double xPosition = (this.currentState.position.x - (this.currentState.width / 2));
            double yPosition = (this.currentState.position.y - (this.currentState.height / 2));

            sprite.setPosition((float) xPosition, (float) yPosition);
            sprite.setSize(this.currentState.width, this.currentState.height);
            sprite.setRotation((float) this.currentState.rotation);

            sprite.draw(SpriteBatchManager.getInstance().getBatch());
        }

    }

    @Override
    public void savePreviousState() {
        this.previousState = this.currentState.clone();
    }

    @Override
    public boolean shouldUpdate(float delta) {
        return this.currentState.status == Status.ACTIVE;
    }

    public void remove() {
        this.setStatus(Status.INACTIVE);
    }

    public double distanceToEntity(Entity entity) {
        return Math.hypot(entity.getPosition().x - this.currentState.position.x, entity.getPosition().y - this.currentState.position.y);
    }

    private void loadSystems() {
        List<? extends EntitySystem> entitySystems = getEntitySystems();
        if (entitySystems != null) {
            for (EntitySystem system : entitySystems) {
                systemManager.add(system);
            }
        }
    }

    protected List<? extends EntitySystem> getEntitySystems() {
        EntitySystem[] systems = {TopDownMovementSystem.getInstance(), CollisionSystem.getInstance()};
        return Arrays.asList(systems);
    }

    protected void loadTexture() {
        String textureFile = getTextureFile();
        if (textureFile != null) {
            this.sprite = new Sprite(MatchAssetManager.getInstance().getRegion(textureFile));
            if (this.currentState.width == 0 || this.currentState.height == 0) {
                this.currentState.width = sprite.getRegionWidth();
                this.currentState.height = sprite.getRegionHeight();
            }
        }
    }

    /**
     * @param entity
     */
    public void addChild(Entity entity) {
        entity.parent = this;
    }

    /**
     * @param entity
     */
    public void removeChild(Entity entity) {
        if (entity != null && entity.parent != null) {
            entity.parent = null;
        }
    }

    public Vector getSpeed() {
        return speed;
    }

    public void setSpeed(Vector speed) {
        this.speed = speed;
    }

    public Vector getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(Vector acceleration) {
        this.acceleration = acceleration;
    }

    public Vector getPosition() {
        return currentState.position;
    }

    public void setPosition(Vector position) {
        this.currentState.position = position;
    }

    public Vector getMaxAcceleration() {
        return maxAcceleration;
    }

    public void setMaxAcceleration(Vector maxAcceleration) {
        this.maxAcceleration = maxAcceleration;
    }

    public Vector getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(Vector maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public float getWidth() {
        return currentState.width;
    }

    public void setWidth(float width) {
        this.currentState.width = width;
    }

    public float getHeight() {
        return currentState.height;
    }

    public void setHeight(float height) {
        this.currentState.height = height;
    }

    public List<Direction> getDirections() {
        return directions;
    }

    public void setDirections(List<Direction> motionDirection) {
        this.directions = motionDirection;
    }

    public EntityType getType() {
        return type;
    }

    public void setType(EntityType type) {
        this.type = type;
    }

    public BoundingBox getBoundingBox() {
        return boundingBox;
    }

    public void setBoundingBox(BoundingBox boundingBox) {
        this.boundingBox = boundingBox;
    }

    public double getRotation() {
        return currentState.rotation;
    }

    public void setRotation(double rotation) {
        this.currentState.rotation = rotation;
    }

    public boolean isHasFriction() {
        return hasFriction;
    }

    public void setHasFriction(boolean hasFriction) {
        this.hasFriction = hasFriction;
    }

    public float getAlpha() {
        return alpha;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    public Entity getParent() {
        return parent;
    }

    public void setParent(Entity parent) {
        this.parent = parent;
    }

    public Vector getRenderPosition() {
        return renderPosition;
    }

    public void setRenderPosition(Vector renderPosition) {
        this.renderPosition = renderPosition;
    }

    public Status getStatus() {
        return this.currentState.status;
    }

    public void setStatus(Status status) {
        this.currentState.status = status;
    }

    public EntityState getPreviousState() {
        return previousState;
    }

    public void setPreviousState(EntityState previousState) {
        this.previousState = previousState;
    }

    public EntityState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(EntityState currentState) {
        this.currentState = currentState;
    }

}
