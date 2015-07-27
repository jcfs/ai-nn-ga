package pt.pxinxas.fcpp.entity;

import pt.pxinxas.fcpp.boundingbox.CircleBoundingBox;
import pt.pxinxas.fcpp.manager.WorldManager;

public abstract class Player extends PlayerEntity {

    protected PlayerTeam team;

    public Player() {
        this.boundingBox = new CircleBoundingBox(this);
        this.zIndex = -4;
    }

    public Player(PlayerTeam team) {
        this.team = team;
        this.boundingBox = new CircleBoundingBox(this);
        this.zIndex = -4;
    }

    public void spawn(float x, float y) {
        this.currentState.status = Status.ACTIVE;

        this.getCurrentState().position.x = x;
        this.getCurrentState().position.y = y;

        WorldObject worldObject = WorldManager.getInstance().getWorldObjectsMap().get(this.id);
        if (worldObject == null) {
            WorldManager.getInstance().addWorldObject(this);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see pt.pxinxas.fcpp.entity.ActorEntity#render()
     */
    @Override
    public void render() {
        super.render();
    }

    @Override
    public void update(float delta) {

        super.update(delta);
    }

    @Override
    public String getTextureFile() {
        if (team != null) {
            switch (team) {
            case BLUE:
                return "nova-blue-animation";
            case GREEN:
                return "nova-green-animation";
            case RED:
                return "nova-red-animation";
            case YELLOW:
                return "nova-yellow-animation";
            default:
                return null;
            }
        }
        return null;
    }


    @Override
    public boolean shouldAnimate() {
        return this.speed.x != 0 || this.speed.y != 0;
    }

    @Override
    public int getAnimationColumnCount() {
        return 4;
    }

    @Override
    public int getAnimationRowCount() {
        return 1;
    }

    @Override
    public float getAnimationSpeed() {
        return 0.075f;
    }

    /**
     * @return the team
     */
    public PlayerTeam getTeam() {
        return team;
    }

    /**
     * @param team
     *            the team to set
     */
    public void setTeam(PlayerTeam team) {
        this.team = team;
    }

}
