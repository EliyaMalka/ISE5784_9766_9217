package geometries;

import java.util.List;

import primitives.*;
import static primitives.Util.*;

/**
 * The Tube class represents a tube (cylindrical surface) in a 3D space.
 */
public class Tube extends RadialGeometry {

	/**
	 * The axis ray of the tube.
	 */
	protected final Ray axis;

	/**
	 * Constructs a new Tube with the given axis ray and radius.
	 * 
	 * @param ax The axis ray of the tube.
	 * @param R  The radius of the tube.
	 */
	public Tube(Ray ax, double R) {
		super(R);
		this.axis = ax;
	}

	/**
	 * Returns the normal vector to the tube at a given point.
	 * 
	 * @param P The point on the tube where the normal is to be calculated.
	 * @return The normal vector to the tube at point P.
	 */
	@Override
	public Vector getNormal(Point P) {
		Vector u = P.subtract(axis.getHead());
		double projection = axis.getDirection().dotProduct(u);
		Point closestPoint = isZero(projection) //
				? axis.getHead() //
				: axis.getHead().add(axis.getDirection().scale(projection));
		return P.subtract(closestPoint).normalize();
	}

	@Override
	public List<Point> findIntersections(Ray ray) {
		// TODO Auto-generated method stub
		return null;
	}

}
