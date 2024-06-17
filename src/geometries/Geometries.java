package geometries;

import java.util.LinkedList;
import java.util.List;

import primitives.Point;
import primitives.Ray;

/**
 * Represents a collection of geometries that implement the Intersectable
 * interface. It allows adding geometries and finding intersections with a given
 * ray.
 */
public class Geometries implements Intersectable {

	private final List<Intersectable> geometries = new LinkedList<>();

	/**
	 * Default constructor initializes an empty collection of geometries.
	 */
	public Geometries() {
	}

	/**
	 * Constructor that initializes the Geometries object with an array of
	 * Intersectable geometries.
	 *
	 * @param geometries Array of geometries to initialize with.
	 */
	public Geometries(Intersectable... geometries) {
		this.add(geometries);
	}

	/**
	 * Adds one or more Intersectable geometries to the collection.
	 *
	 * @param geometries Array of geometries to add.
	 */
	public void add(Intersectable... geometries) {
		this.geometries.addAll(List.of(geometries));
	}

	/**
	 * Finds all intersection points of a given ray with the geometries in the
	 * collection.
	 *
	 * @param ray The ray to find intersections with.
	 * @return A list of intersection points, or an empty list if no intersections
	 *         found.
	 */
	@Override
	public List<Point> findIntersections(Ray ray) {
		List<Point> allIntersectionPoints = new LinkedList<>();

		for (Intersectable obj : this.geometries) {
			List<Point> objIntersectionPoints = obj.findIntersections(ray);
			if (objIntersectionPoints != null) {
				allIntersectionPoints.addAll(objIntersectionPoints);
			}
		}

		return allIntersectionPoints;
	}

}
