package geometries;

import java.util.List;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import static primitives.Util.*;

/**
 * The Plane class represents a plane in a 3D space.
 */
public class Plane implements Geometry {

	/**
	 * A point on the plane.
	 */
	private final Point q;

	/**
	 * The normal vector to the plane.
	 */
	private final Vector normal;

	/**
	 * Constructs a new Plane passing through three given points.
	 *
	 * @param point1 The first point on the plane.
	 * @param point2 The second point on the plane.
	 * @param point3 The third point on the plane.
	 * @throws IllegalArgumentException when the points are collinear or coincident
	 */
	public Plane(Point point1, Point point2, Point point3) {
		this.normal = point2.subtract(point1).crossProduct(point3.subtract(point1)).normalize();
		this.q = point1;
	}

	/**
	 * Constructs a new Plane with the given point and normal vector.
	 *
	 * @param point The reference point on the plane.
	 * @param vector The normal vector to the plane.
	 */
	public Plane(Point point, Vector vector) {
		this.q = point;
		this.normal = vector.normalize();
	}

	/**
	 * Returns the normal vector to the plane at a given point (which is ignored in
	 * this case since the plane is infinite and the normal vector is constant
	 * everywhere on the plane).
	 *
	 * @param P The point (ignored).
	 * @return The normal vector to the plane.
	 */
	@Override
	public Vector getNormal(Point P) {
		return this.normal;
	}

	/**
	 * Gets the normal vector to the plane.
	 *
	 * @return The normal vector.
	 */
	public Vector getNormal() {
		return this.normal;
	}

	/**
	 * Finds the intersection points of a given ray with the plane.
	 *
	 * @param ray The ray to find intersections with.
	 * @return A list containing the intersection point, or null if no intersection
	 *         exists.
	 */
	@Override
	public List<Point> findIntersections(Ray ray) {
		if (this.q.equals(ray.getHead())) {
			return null; // Ray starts on the plane, no intersections.
		}

		double numerator = this.normal.dotProduct(this.q.subtract(ray.getHead()));
		double denominator = this.normal.dotProduct(ray.getDirection());

		if (isZero(denominator)) {
			return null; // Ray is parallel to the plane.
		}

		double t = numerator / denominator;

		if (t > 0) {
			return List.of(ray.getPoint(alignZero(t)));
		} else {
			return null; // Intersection point is behind the ray origin.
		}
	}

}
