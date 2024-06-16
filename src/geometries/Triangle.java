package geometries;

import java.util.Iterator;
import java.util.List;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import static primitives.Util.*;
import java.util.List;

/**
 * The Triangle class represents a triangle in a 3D space. It extends the
 * Polygon class and is defined by three vertices.
 */
public class Triangle extends Polygon {

	public Triangle(Point vert1, Point vert2, Point vert3) {
		super(vert1, vert2, vert3);
	}

	@Override
	public List<Point> findIntersections(Ray ray) {
		
		Vector v1 = super.vertices.get(0).subtract(ray.getHead());
		Vector v2 = super.vertices.get(1).subtract(ray.getHead());
		Vector v3 = super.vertices.get(2).subtract(ray.getHead());

		Vector n1 = v1.crossProduct(v2).normalize();
		Vector n2 = v2.crossProduct(v3).normalize();
		Vector n3 = v3.crossProduct(v1).normalize();

		if ((n1.dotProduct(ray.getDirection()) > 0 && n2.dotProduct(ray.getDirection()) > 0
				&& n3.dotProduct(ray.getDirection()) > 0)
				|| (n1.dotProduct(ray.getDirection()) < 0 && n2.dotProduct(ray.getDirection()) < 0
						&& n3.dotProduct(ray.getDirection()) < 0)) {
			return super.plane.findIntersections(ray);
		}
		
		return null;
	}
}
