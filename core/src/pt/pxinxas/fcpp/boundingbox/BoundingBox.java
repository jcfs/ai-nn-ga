package pt.pxinxas.fcpp.boundingbox;

import pt.pxinxas.fcpp.util.Point;
import pt.pxinxas.fcpp.util.Vector;

import com.badlogic.gdx.math.Shape2D;

/**
 *
 * @author JSalavisa
 *
 */
public interface BoundingBox {

    public Shape2D getShape();

    public void update();

    public boolean intersects(BoundingBox boundingBox);
    
    public boolean contains(Point p);

    public Vector getMaxVertice();

    public Vector getMinVertice();

}
