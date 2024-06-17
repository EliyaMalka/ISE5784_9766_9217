package geometries;

import java.util.List;

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
	 * @param axisRay The axis ray of the tube.
	 * @param radius  The radius of the tube.
	 */
	public Tube(Ray axisRay, double radius) {
		super(radius);
		this.axis = axisRay;
	}

	/**
	 * Returns the normal vector to the tube at a given point.
	 * 
	 * @param point The point on the tube where the normal is to be calculated.
	 * @return The normal vector to the tube at point P.
	 */
	@Override
	public Vector getNormal(Point point) {
		// if the point orthogonal to the ray
		if (point.subtract(axis.getHead()).dotProduct(axis.getDirection()) == 0) {
			return point.subtract(axis.getHead()).normalize();
		} else {
			// Calculate the projection of P onto the axis ray
			Point frontPoint = axis.getHead().add(axis.getDirection().normalize()
					.scale(axis.getDirection().normalize().dotProduct(point.subtract(axis.getHead()))));
			return point.subtract(frontPoint).normalize();
		}
	}

	/**
	 * Finds intersection points of a given ray with the tube.
	 * 
	 * @param ray The ray to find intersections with.
	 * @return A list containing the intersection points, or null if no intersection
	 *         exists.
	 */
	@Override
	public List<Point> findIntersections(Ray ray) {
		return null; // TODO BONUS: be implemented
	}

}
