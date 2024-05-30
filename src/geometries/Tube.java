package geometries;

import primitives.Point;
import primitives.Vector;
import primitives.Ray;

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
		// if the point orthogonal to the ray
		if (P.subtract(axis.getHead()).dotProduct(axis.getDirection()) == 0) {
			return P.subtract(axis.getHead()).normalize();
		} else {
			// Calculate the projection of P onto the axis ray
			Point frontPoint = axis.getHead()
					.add(axis.getDirection().normalize().scale(axis.getDirection().normalize().dotProduct(P.subtract(axis.getHead()))));
			return P.subtract(frontPoint).normalize();
		}
	}

}
