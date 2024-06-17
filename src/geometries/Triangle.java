package geometries;

import java.util.List;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * The Triangle class represents a triangle in a 3D space. It extends the
 * Polygon class and is defined by three vertices.
 */
public class Triangle extends Polygon {

	/**
	 * Constructs a triangle with three given vertices.
	 * 
	 * @param vert1 The first vertex of the triangle.
	 * @param vert2 The second vertex of the triangle.
	 * @param vert3 The third vertex of the triangle.
	 */
	public Triangle(Point vert1, Point vert2, Point vert3) {
		super(vert1, vert2, vert3);
	}

	/**
	 * Finds intersection points of a given ray with the triangle.
	 * 
	 * @param ray The ray to find intersections with.
	 * @return A list containing the intersection point, or null if no intersection
	 *         exists.
	 */
	@Override
	public List<Point> findIntersections(Ray ray) {
		// Compute vectors from ray's head to each triangle vertex
		Vector v1 = super.vertices.get(0).subtract(ray.getHead());
		Vector v2 = super.vertices.get(1).subtract(ray.getHead());
		Vector v3 = super.vertices.get(2).subtract(ray.getHead());

		// Calculate normal vectors of the triangle's edges
		Vector n1 = v1.crossProduct(v2).normalize();
		Vector n2 = v2.crossProduct(v3).normalize();
		Vector n3 = v3.crossProduct(v1).normalize();

		// Check if the ray intersects the plane of the triangle
		if ((n1.dotProduct(ray.getDirection()) > 0 && n2.dotProduct(ray.getDirection()) > 0
				&& n3.dotProduct(ray.getDirection()) > 0)
				|| (n1.dotProduct(ray.getDirection()) < 0 && n2.dotProduct(ray.getDirection()) < 0
						&& n3.dotProduct(ray.getDirection()) < 0)) {
			return super.plane.findIntersections(ray);
		}

		// No intersection if the ray does not intersect the plane within the triangle's
		// boundaries
		return null;
	}
}
