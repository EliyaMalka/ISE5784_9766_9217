package geometries;

import java.util.List;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.alignZero;

/**
 * The Triangle class represents a triangle in a 3D space. It extends the
 * Polygon class and is defined by three vertices.
 */
public class Triangle extends Polygon {

	/**
	 * Constructs a triangle with three given vertices.
	 * 
	 * @param vertex1 The first vertex of the triangle.
	 * @param vertex2 The second vertex of the triangle.
	 * @param vertex3 The third vertex of the triangle.
	 */
	public Triangle(Point vertex1, Point vertex2, Point vertex3) {
		super(vertex1, vertex2, vertex3);
	}

	@Override
	protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
		var intersection = plane.findIntersections(ray);
		if (intersection == null)
			return null;

		Point head = ray.getHead();
		Vector direction = ray.getDirection();
		// Compute vectors from ray's head to each triangle
		Vector v1 = super.vertices.get(0).subtract(head);
		Vector v2 = super.vertices.get(1).subtract(head);
		// Calculate normal vectors of the triangle's based pyramid side for each edge
		Vector n1 = v1.crossProduct(v2);
		double sign1 = alignZero(direction.dotProduct(n1));
		if (sign1 == 0)
			return null;

		Vector v3 = super.vertices.get(2).subtract(head);
		Vector n2 = v2.crossProduct(v3);
		double sign2 = alignZero(direction.dotProduct(n2));
		if (sign1 * sign2 <= 0)
			return null;

		Vector n3 = v3.crossProduct(v1);
		double sign3 = alignZero(direction.dotProduct(n3));
		if (sign1 * sign3 <= 0)
			return null;

		return List.of(new GeoPoint(this, intersection.getFirst()));
	}
}
