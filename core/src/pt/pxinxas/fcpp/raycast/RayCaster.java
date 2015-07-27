package pt.pxinxas.fcpp.raycast;

import java.util.Set;

import pt.pxinxas.fcpp.entity.Entity;
import pt.pxinxas.fcpp.util.Point;
import pt.pxinxas.fcpp.util.SpatialHashMap;
import pt.pxinxas.fcpp.util.Vector;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;

public class RayCaster {
	private static final RayCaster instance = new RayCaster();

	// Fields to avoid instantiation during the casting
	private final Point previous = new Point();
	private final Vector2 intersection = new Vector2();
	private final Vector2 p1 = new Vector2();
	private final Vector2 p2 = new Vector2();
	private final Vector2 p3 = new Vector2();
	private final Vector2 p4 = new Vector2();

	public Point castRay(Point point, Vector direction, double maxDistance, SpatialHashMap map) {
		Point current = new Point();
		current.set(point.x, point.y);
		this.previous.set(point.x, point.y);

		while (Math.hypot(current.x - point.x, current.y - point.y) < maxDistance) {
			this.previous.x = current.x;
			this.previous.y = current.y;

			current.x += direction.x;
			current.y += direction.y;

			Set<Entity> entities = map.get(current);
			if (entities != null) {
				for (Entity entity : entities) {
					if (entity.blocksLineOfSight()) {
						Vector2 processLineOfSight = processLineOfSight(current, point, entity);
						if (processLineOfSight != null) {
							Point result = new Point();
							result.x = processLineOfSight.x;
							result.y = processLineOfSight.y;
							return result;
						}
					}
				}

			}
		}

		return current;
	}

	/**
	 * @param point
	 * @param current
	 * @param entity
	 * @return
	 */
	private Vector2 processLineOfSight(Point point, Point current, Entity entity) {
		this.p1.set((float) previous.x, (float) previous.y);
		this.p2.set((float) current.x, (float) current.y);

		Polygon polygon = (Polygon) entity.getBoundingBox().getShape();

		float[] vertices = polygon.getTransformedVertices();

		for (int i = 0; i < vertices.length - 1; i += 2) {
			float p1x = vertices[i % vertices.length];
			float p1y = vertices[(i + 1) % vertices.length];

			float p2x = vertices[(i + 2) % vertices.length];
			float p2y = vertices[(i + 3) % vertices.length];

			this.p3.set(p1x, p1y);
			this.p4.set(p2x, p2y);

			if (Intersector.intersectSegments(this.p1, this.p2, this.p3, this.p4, this.intersection)) {
				return this.intersection;
			}
		}
		return null;
	}

	/**
	 * @return the instance
	 */
	public static RayCaster getInstance() {
		return instance;
	}

}
