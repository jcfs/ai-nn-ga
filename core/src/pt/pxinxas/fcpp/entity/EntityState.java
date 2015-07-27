package pt.pxinxas.fcpp.entity;

import pt.pxinxas.fcpp.util.Vector;

/**
 * Class that hold the state of an entity
 * 
 * @author jcfs
 */
public class EntityState implements Cloneable {

	public Status status = Status.ACTIVE;

	public float width;
	public float height;
	public double rotation;
	public Vector position = new Vector(0, 0);

	public EntityState() {

	}

	public EntityState(Status status, float width, float height, double rotation, Vector position) {
		this.status = status;
		this.width = width;
		this.height = height;
		this.rotation = rotation;
		this.position = new Vector(position.x, position.y);
	}

	@Override
	public EntityState clone() {
		return new EntityState(this.status, this.width, this.height, this.rotation, this.position);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(height);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((position == null) ? 0 : position.hashCode());
		temp = Double.doubleToLongBits(rotation);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		temp = Double.doubleToLongBits(width);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EntityState other = (EntityState) obj;
		if (Double.doubleToLongBits(height) != Double.doubleToLongBits(other.height))
			return false;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		if (Double.doubleToLongBits(rotation) != Double.doubleToLongBits(other.rotation))
			return false;
		if (status != other.status)
			return false;
		if (Double.doubleToLongBits(width) != Double.doubleToLongBits(other.width))
			return false;
		return true;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EntityState [status=" + status + ", width=" + width + ", height=" + height + ", rotation=" + rotation + ", position=" + position
				+ "]";
	}

}
