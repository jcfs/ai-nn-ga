package pt.pxinxas.fcpp.util;

import java.io.Serializable;

import com.badlogic.gdx.math.MathUtils;

public class Vector implements Serializable {
    private static final long serialVersionUID = 1L;

    public double x;
    public double y;

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getDistance() {
        return Math.hypot(this.x, this.y);
    }

    public double getDistance(float x, float y) {
        return Math.sqrt(x * x + y * y);
    }

    public void add(Vector v) {
        this.x += v.x;
        this.y += v.y;
    }

    public void mul(Vector v) {
        this.x *= v.x;
        this.y *= v.y;
    }

    public Vector nor() {
        double len = len();
        if (len != 0) {
            x /= len;
            y /= len;
        }
        return this;
    }

    public double len() {
        return Math.hypot(x, y);
    }

    
    /** Rotates the Vector2 by the given angle, counter-clockwise assuming the y-axis points up.
	 * @param degrees the angle in degrees */
	public Vector rotate (double degrees) {
		return rotateRad(degrees * MathUtils.degreesToRadians);
	}

	/** Rotates the Vector2 by the given angle, counter-clockwise assuming the y-axis points up.
	 * @param radians the angle in radians */
	public Vector rotateRad (double radians) {
		double cos = Math.cos(radians);
		double sin = Math.sin(radians);

		double newX =  (this.x * cos - this.y * sin);
		double newY =  (this.x * sin + this.y * cos);

		this.x = newX;
		this.y = newY;

		return this;
	}
    /**
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Vector [x=" + x + ", y=" + y + "]";
    }

    /**
     * {@inheritDoc}
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    /**
     * {@inheritDoc}
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Vector other = (Vector) obj;
        if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x)) {
            return false;
        }
        if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y)) {
            return false;
        }
        return true;
    }

}
