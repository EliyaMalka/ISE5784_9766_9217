package geometries;

import java.util.LinkedList;
import java.util.List;

import primitives.Ray;

/**
 * Represents a collection of geometries that implement the Intersectable
 * interface. It allows adding geometries and finding intersections with a given
 * ray.
 */
public class Geometries extends Intersectable {

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

	@Override
	protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
		List<GeoPoint> allIntersectionPoints = null;

		for (Intersectable obj : this.geometries) {
			var intersectionPoints = obj.findGeoIntersections(ray);
			if (intersectionPoints != null) {
				if (allIntersectionPoints == null)
					allIntersectionPoints = new LinkedList<>(intersectionPoints);
				else
					allIntersectionPoints.addAll(intersectionPoints);
			}
		}

		return allIntersectionPoints;
	}

}
