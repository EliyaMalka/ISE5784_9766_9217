package primitives;

import static primitives.Util.isZero;

/**
 * The Ray class represents a ray in a 3D space, defined by a point and a
 * direction vector.
 */
public class Ray {

	/**
	 * The head point of the ray.
	 */
	private final Point head;

	/**
	 * The direction vector of the ray.
	 */
	private final Vector direction;

	/**
	 * Constructs a new Ray with the given head point and direction vector.
	 * 
	 * @param P The head point of the ray.
	 * @param V The direction vector of the ray.
	 */
	public Ray(Point P, Vector V) {
		this.head = P;
		this.direction = V.normalize();
	}

	/**
	 * Checks whether this ray equals another object.
	 * 
	 * @param obj The object to compare with this ray.
	 * @return true if the rays are equal, false otherwise.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		return (obj instanceof Ray other) && (this.head.equals(other.head)) && (this.direction.equals(other.direction));
	}

	/**
	 * Returns a string representation of the ray.
	 * 
	 * @return A string representing the ray.
	 */
	@Override
	public String toString() {
		return "Ray[" + "point head = " + head + ", vector direction = " + direction + " ]";
	}

	/**
	 * Returns the head point of the ray.
	 * 
	 * @return The head point of the ray.
	 */
	public Point getHead() {
		return head;
	}

	/**
	 * Returns the direction vector of the ray.
	 * 
	 * @return The direction vector of the ray.
	 */
	public Vector getDirection() {
		return direction;
	}

	/**
	 * Computes a point on the ray at a distance t from its head.
	 * 
	 * @param t The distance from the head of the ray.
	 * @return The point on the ray at distance t from its head.
	 */
	public Point getPoint(double t) {
		if (isZero(t)) {
			return this.getHead();
		} else {
			return head.add(direction.scale(t));
		}
	}
}
