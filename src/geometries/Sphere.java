package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * The Sphere class represents a sphere in a 3D space.
 */
public class Sphere extends RadialGeometry {

	/**
	 * The center point of the sphere.
	 */
	private final Point center;

	/**
	 * Constructs a new Sphere with the given center point and radius.
	 * 
	 * @param P   The center point of the sphere.
	 * @param rad The radius of the sphere.
	 */
	public Sphere(Point P, double rad) {
		super(rad);
		this.center = P;
	}

	/**
	 * Computes the normal vector at a given point on the surface of the sphere.
	 * 
	 * @param point The point on the surface of the sphere.
	 * @return The normal vector at the given point.
	 */
	@Override
	public Vector getNormal(Point P) {

		return (P.subtract(this.center)).normalize();

	}

}
