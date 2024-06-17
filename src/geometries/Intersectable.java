package geometries;

import java.util.List;
import primitives.Point;
import primitives.Ray;

/**
 * Intersectable interface represents a geometric object that can be intersected
 * by a ray. It defines a method to find the intersection points between a ray
 * and the geometric object.
 */
public interface Intersectable {

	/**
	 * Finds the intersection points between a given ray and the geometric object.
	 *
	 * @param ray the ray for which we want to find intersections with the geometric
	 *            object
	 * @return a list of points where the ray intersects the geometric object. If
	 *         there are no intersections, an empty list or null may be returned.
	 */
	public List<Point> findIntersections(Ray ray);
}