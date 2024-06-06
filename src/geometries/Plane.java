package geometries;

import java.util.List;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

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
	 * @param P1 The first point on the plane.
	 * @param P2 The second point on the plane.
	 * @param P3 The third point on the plane.
	 * 
	 * @throws IllegalArgumentException when there are convergent points or the points are co-linear
	 */
	public Plane(Point P1, Point P2, Point P3) {
		this.normal = ((P2.subtract(P1)).crossProduct(P3.subtract(P1))).normalize();
		this.q = P1;
	}

	/**
	 * Constructs a new Plane with the given point and normal vector.
	 * 
	 * @param P The reference point on the plane.
	 * @param V The normal vector to the plane.
	 */
	public Plane(Point P, Vector V) {
		this.q = P;
		this.normal = V.normalize();
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
	public List<Point> findIntersections(Ray ray) {
		return null;
	}
}
