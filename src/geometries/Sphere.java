package geometries;

import java.util.List;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * The Sphere class represents a sphere in a 3D space.
 */
public class Sphere extends RadialGeometry {

	/**
	 * The center point of the sphere.
	 */
	private final Point center;

	/**
	 * Constructs a new Sphere with the given center point and radius.
	 * 
	 * @param centerPoint  The center point of the sphere.
	 * @param radiusSphere The radius of the sphere.
	 */
	public Sphere(Point centerPoint, double radiusSphere) {
		super(radiusSphere);
		this.center = centerPoint;
	}

	/**
	 * Computes the normal vector at a given point on the surface of the sphere.
	 * 
	 * @param pointOnSphere The point on the surface of the sphere.
	 * @return The normal vector at the given point.
	 */
	@Override
	public Vector getNormal(Point pointOnSphere) {
		return (pointOnSphere.subtract(this.center)).normalize();
	}

	/**
	 * Finds intersection points of a given ray with the sphere.
	 * 
	 * @param ray The ray to find intersections with.
	 * @return A list containing the intersection points, or null if no intersection
	 *         exists.
	 */
	@Override
	public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {

		if (ray.getHead().equals(center)) {
			return List.of(new GeoPoint(this, ray.getPoint(this.radius)));
		}

		Vector headToCenter = this.center.subtract(ray.getHead());
		double t_m = headToCenter.dotProduct(ray.getDirection());
		double d = Math.sqrt(headToCenter.lengthSquared() - t_m * t_m);
		double t_h = Math.sqrt(this.radius * this.radius - d * d);

		double t_1 = t_m + t_h;
		double t_2 = t_m - t_h;

		Point point_1 = null;
		Point point_2 = null;

		if (d == this.radius) {
			return null;
		}

		if (t_1 > 0) {
			point_1 = ray.getPoint(t_1);
		}
		if (t_2 > 0) {
			point_2 = ray.getPoint(t_2);
		}

		if (point_1 != null && point_2 != null) {

			if (t_1 < t_2) {
				return List.of(new GeoPoint(this, ray.getPoint(t_1)), new GeoPoint(this, ray.getPoint(t_2)));
			} else {
				return List.of(new GeoPoint(this, ray.getPoint(t_2)), new GeoPoint(this, ray.getPoint(t_1)));
			}

		}

		if (point_1 != null) {
			return List.of(new GeoPoint(this, ray.getPoint(t_1)));
		}
		if (point_2 != null) {
			return List.of(new GeoPoint(this, ray.getPoint(t_2)));
		}

		return null;
	}
}
