package geometries;

import primitives.*;
import static primitives.Util.*;

/**
 * The Cylinder class represents a cylinder in a 3D space. It extends the Tube
 * class and includes an additional height attribute.
 */
public class Cylinder extends Tube {

	/**
	 * The height of the cylinder.
	 */
	private final double height;

	/**
	 * Constructs a new Cylinder with the given axis ray, radius, and height.
	 * 
	 * @param ax The axis ray of the cylinder.
	 * @param R  The radius of the cylinder.
	 * @param H  The height of the cylinder.
	 */
	public Cylinder(Ray ax, double R, double H) {
		super(ax, R);
		this.height = H;
	}

	/**
	 * Computes the normal vector at a given point on the surface of the cylinder.
	 * 
	 * @param P The point on the surface of the cylinder.
	 * @return The normal vector at the given point.
	 */
	@Override
	public Vector getNormal(Point P) {
		Point head = axis.getHead();
		Vector direction = axis.getDirection();
		// center points on the 1st base
		if (P.equals(axis.getHead()))
			return direction;

		// check whether the point is on one of the bases
		double projection = direction.dotProduct(P.subtract(head));
		if (isZero(projection) || isZero(projection - this.height))
			return direction;

		// points on the side surface
		return super.getNormal(P);
	}
}