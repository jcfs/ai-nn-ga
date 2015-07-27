package pt.pxinxas.fcpp.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import pt.pxinxas.fcpp.boundingbox.BoundingBox;
import pt.pxinxas.fcpp.entity.Entity;

public class SpatialHashMap {
    private final int cellSize;
    private final Map<Vector, Set<Entity>> map = new HashMap<Vector, Set<Entity>>();

    public SpatialHashMap() {
        this(64);
    }

    public SpatialHashMap(int cellSize) {
        this.cellSize = cellSize;
    }

    public void add(Entity e) {
        BoundingBox boundingBox = e.getBoundingBox();
        if (e.getBoundingBox() != null) {
            Vector minVertice = getPoint(boundingBox.getMinVertice().x, boundingBox.getMinVertice().y);
            Vector maxVertice = getPoint(boundingBox.getMaxVertice().x, boundingBox.getMaxVertice().y);
            for (double i = minVertice.x; i <= maxVertice.x; i++) {
                for (double j = minVertice.y; j <= maxVertice.y; j++) {
                    Vector point = new Vector(i, j);

                    Set<Entity> list = map.get(point);
                    if (list == null) {
                        list = new HashSet<Entity>();
                    }
                    list.add(e);
                    map.put(point, list);
                }
            }
        }

    }

    public Set<Entity> get(Entity e) {
        Set<Entity> result = new HashSet<Entity>();

        BoundingBox boundingBox = e.getBoundingBox();
        if (boundingBox != null) {
            Vector minVertice = getPoint(boundingBox.getMinVertice().x, boundingBox.getMinVertice().y);
            Vector maxVertice = getPoint(boundingBox.getMaxVertice().x, boundingBox.getMaxVertice().y);

            for (double i = minVertice.x; i <= maxVertice.x; i++) {
                for (double j = minVertice.y; j <= maxVertice.y; j++) {
                    Set<Entity> set = map.get(new Vector(i, j));
                    if (set != null) {
                        result.addAll(set);
                    }
                }
            }
        }

        return result;
    }
    
    public Set<Entity> get(Point p) {
    	 Vector point = getPoint(p.x, p.y);
    	 return map.get(point);
    }

    public void clear() {
        map.clear();
    }

    private Vector getPoint(double x, double y) {
        return new Vector((int) x / cellSize, (int) y / cellSize);
    }

}
