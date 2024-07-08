package primitives;

import java.util.List;

import static primitives.Util.isZero;
import geometries.Intersectable.GeoPoint;

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
	 * @param point  The head point of the ray.
	 * @param vector The direction vector of the ray.
	 */
	public Ray(Point point, Vector vector) {
		this.head = point;
		this.direction = vector.normalize();
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
		return "Ray[ " + "point head = " + head + ", vector direction = " + direction + " ]";
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

	/**
	 * Finds the closest point to the head of the ray from a list of points.
	 * 
	 * @param points The list of points to search.
	 * @return The closest point to the head of the ray, or null if the list is
	 *         empty.
	 */
	public Point findClosestPoint(List<Point> points) {
		return points == null || points.isEmpty() ? null
				: findClosestGeoPoint(points.stream().map(p -> new GeoPoint(null, p)).toList()).point;
	}

	/**
	 * Finds the closest point to the head of the ray from a list of points.
	 * 
	 * @param geoPoints The list of points to search.
	 * @return The closest point to the head of the ray, or null if the list is
	 *         empty.
	 */
	public GeoPoint findClosestGeoPoint(List<GeoPoint> geoPoints) {
		// Return null if the list of points is empty
		if (geoPoints.isEmpty()) {
			return null;
		}
		// Initialize with the first point in the list
		double minDistance = this.getHead().distance(geoPoints.get(0).point);
		GeoPoint closest = geoPoints.get(0);
		// Iterate through the list to find the closest point
		for (int i = 1; i < geoPoints.size(); i++) {
			double distance = this.getHead().distance(geoPoints.get(i).point);
			if (distance < minDistance) {
				minDistance = distance;
				closest = geoPoints.get(i);
			}
		}
		// Return the closest point
		return closest;
	}
}
