package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * The Geometry interface represents a geometric shape in a 3D space. It defines
 * a method for computing the normal vector at a given point on the shape.
 */
public interface Geometry {

	/**
	 * Computes the normal vector at a given point on the shape.
	 * 
	 * @param P The point on the shape.
	 * @return The normal vector at the given point.
	 */
	public Vector getNormal(Point P);

}
