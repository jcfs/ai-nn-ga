package pt.pxinxas.fcpp.boundingbox;

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
public class PolygonBoundingBox implements BoundingBox {
	private final Entity entity;
	private final Polygon polygon;

	public PolygonBoundingBox(Entity entity) {
		this.polygon = new Polygon();
		this.entity = entity;
		this.polygon.setOrigin(this.entity.getWidth() / 2, this.entity.getHeight() / 2);
		this.polygon.setVertices(new float[] { 0f, 0f, 0f, entity.getHeight(), entity.getWidth(), entity.getHeight(), entity.getWidth(), 0f });
		this.polygon.setPosition((float) this.entity.getPosition().x - (this.entity.getWidth() / 2), (float) this.entity.getPosition().y
				- (this.entity.getHeight() / 2));
	}

	@Override
	public void update() {
		this.polygon.setPosition((float) this.entity.getPosition().x - (this.entity.getWidth() / 2), (float) this.entity.getPosition().y
				- (this.entity.getHeight() / 2));
		this.polygon.setVertices(new float[] { 0f, 0f, 0f, this.entity.getHeight(), this.entity.getWidth(), this.entity.getHeight(),
				this.entity.getWidth(), 0f });
		this.polygon.setRotation((float) this.entity.getRotation());
	}

	@Override
	public boolean intersects(BoundingBox boundingBox) {
		if (boundingBox != null) {
			if (boundingBox.getShape() instanceof Circle) {
				return overlaps(this.polygon, (Circle) boundingBox.getShape());
			} else if (boundingBox.getShape() instanceof Polygon) {
				return Intersector.overlapConvexPolygons(this.polygon, (Polygon) boundingBox.getShape());
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
				if (Intersector.intersectSegmentCircle(new Vector2(vertices[i - 2], vertices[i - 1]), new Vector2(vertices[i], vertices[i + 1]),
						center, squareRadius)) {
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
		float radius = (float) Math.hypot(this.entity.getWidth(), this.entity.getHeight()) / 2;
		return new Vector(this.entity.getPosition().x + radius, this.entity.getPosition().y + radius);
	}

	@Override
	public Vector getMinVertice() {
		float radius = (float) Math.hypot(this.entity.getWidth(), this.entity.getHeight()) / 2;
		return new Vector(this.entity.getPosition().x - radius, this.entity.getPosition().y - radius);
	}

	@Override
	public boolean contains(Point p) {
		return this.polygon.contains((float) p.x, (float) p.y);
	}

}
