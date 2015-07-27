package pt.pxinxas.fcpp.boundingbox;

import java.io.Serializable;

import pt.pxinxas.fcpp.entity.Entity;
import pt.pxinxas.fcpp.util.Point;
import pt.pxinxas.fcpp.util.Vector;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.math.Vector2;

/**
 * Boudingbox to an entity, shrinking it to the maximum possible so it is
 * ajusted to the sprite size.
 *
 * @author JSalavisa
 *
 */
public class CircleBoundingBox implements BoundingBox, Serializable {
    private static final long serialVersionUID = 1L;
    private final Entity entity;
    private final Circle polygon;

    public CircleBoundingBox(Entity entity) {
        this.polygon = new Circle();
        this.polygon.setRadius((float) Math.hypot(entity.getWidth() / 2, entity.getHeight() / 2) / 2);
        this.entity = entity;
    }

    @Override
    public void update() {
        this.polygon.setRadius((float) Math.hypot(entity.getWidth() / 2, entity.getHeight() / 2) / 2);
        this.polygon.setPosition((float) this.entity.getPosition().x, (float) this.entity.getPosition().y);
    }

    @Override
    public boolean intersects(BoundingBox boundingBox) {
        if (boundingBox != null) {
            if (boundingBox.getShape() instanceof Circle) {
                return this.polygon.overlaps((Circle) boundingBox.getShape()) || this.polygon.contains((Circle) boundingBox.getShape());
            } else if (boundingBox.getShape() instanceof Polygon) {
                return overlaps((Polygon) boundingBox.getShape(), this.polygon)
                    || ((Polygon) boundingBox.getShape()).contains(this.polygon.x, this.polygon.y);
            }
        }
        return false;

    }

    private boolean overlaps(Polygon polygon, Circle circle) {
        float[] vertices = polygon.getTransformedVertices();
        Vector2 center = new Vector2(circle.x, circle.y);
        float squareRadius = circle.radius * circle.radius;
        for (int i = 0; i < vertices.length; i += 2) {
            if (i == 0) {
                if (Intersector.intersectSegmentCircle(new Vector2(vertices[vertices.length - 2], vertices[vertices.length - 1]), new Vector2(
                    vertices[i], vertices[i + 1]), center, squareRadius)) {
                    return true;
                }
            } else {
                if (Intersector.intersectSegmentCircle(
                    new Vector2(vertices[i - 2], vertices[i - 1]),
                    new Vector2(vertices[i], vertices[i + 1]),
                    center,
                    squareRadius)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Shape2D getShape() {
        return this.polygon;
    }

    @Override
    public Vector getMaxVertice() {
        return new Vector(this.polygon.x + this.polygon.radius, this.polygon.y + this.polygon.radius);
    }

    @Override
    public Vector getMinVertice() {
        return new Vector(this.polygon.x - this.polygon.radius, this.polygon.y - this.polygon.radius);
    }

	@Override
	public boolean contains(Point p) {
		return this.polygon.contains((float)p.x, (float)p.y);
	}

}
