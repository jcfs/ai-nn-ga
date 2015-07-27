package pt.pxinxas.fcpp.entity;

/**
 * @author JSalavisa
 */
public abstract class WorldObject implements Comparable<WorldObject> {
    /* default z-index to zero */
    protected int zIndex = 0;

    protected Integer id;

    /**
     * Method that will be called to update the current Object
     *
     * @param delta
     */
    public abstract void update(float delta);

    /**
     * Method that will render the specific object to the screen
     */
    public abstract void render();

    /**
     * Method that states if the world object should be updated
     *
     * @param delta
     */
    public abstract boolean shouldUpdate(float delta);

    /**
     * Method that states if the world object should be rendered
     *
     * @param delta
     */
    public abstract boolean shouldRender();

    /**
     * Method that saves the object previous state
     *
     */
    public abstract void savePreviousState();

    @Override
    public int compareTo(WorldObject o) {
        return (this.zIndex < o.zIndex) ? 1 : (this.zIndex > o.zIndex) ? -1 : 0;
    }

    public int getzIndex() {
        return zIndex;
    }

    public void setzIndex(int zIndex) {
        this.zIndex = zIndex;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
