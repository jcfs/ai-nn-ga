package pt.pxinxas.fcpp.entity;

import pt.pxinxas.fcpp.boundingbox.PolygonBoundingBox;
import pt.pxinxas.fcpp.system.CollisionAxis;

public class Block extends StaticEntity {

    public Block() {

    }

    public Block(float x, float y) {
        this(x, y, 32, 32);
    }

    public Block(float x, float y, float width, float height) {
        this.zIndex = -1;
        this.getCurrentState().position.y = y;
        this.getCurrentState().position.x = x;
        this.setWidth(width);
        this.setHeight(height);

        this.boundingBox = new PolygonBoundingBox(this);
    }

    @Override
    public boolean shouldRender() {
        return false;
    }

    @Override
    public void collideWithEntity(Entity ent, float delta, CollisionAxis axis) {
    }

    @Override
    public String getTextureFile() {
        return null;
    }

    @Override
    public void collidedByEntity(Entity ent, float delta) {

    }

    @Override
    public boolean pushEntity(WorldObject entity) {
        return false;
    }

}
