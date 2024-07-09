package geometries;

import java.util.List;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import static primitives.Util.*;

/**
 * The Plane class represents a plane in a 3D space.
 */
public class Plane extends Geometry {

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
	 * @param point  The reference point on the plane.
	 * @param vector The normal vector to the plane.
	 */
	public Plane(Point point, Vector vector) {
		this.q = point;
		this.normal = vector.normalize();
	}

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

	@Override
	protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
		var head = ray.getHead();
		if (this.q.equals(head))
			return null; // Ray starts on the plane, no intersections.

		double numerator = this.normal.dotProduct(this.q.subtract(head));
		double denominator = this.normal.dotProduct(ray.getDirection());
		if (isZero(denominator))
			return null; // Ray is parallel to the plane.

		double t = alignZero(numerator / denominator);
		// Intersection point is behind the ray origin => return null
		return t <= 0 ? null : List.of(new GeoPoint(this, ray.getPoint(t)));
	}
}
