package geometries;

import java.util.List;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.alignZero;

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

	@Override
	public Vector getNormal(Point pointOnSphere) {
		return (pointOnSphere.subtract(this.center)).normalize();
	}

	@Override
	public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
		Point head = ray.getHead();
		if (head.equals(center))
			return List.of(new GeoPoint(this, ray.getPoint(this.radius)));

		Vector headToCenter = this.center.subtract(head);
		double tm = headToCenter.dotProduct(ray.getDirection());
		double dSquared = headToCenter.lengthSquared() - tm * tm;
		double thSquared = this.radiusSquared - dSquared;
		if (alignZero(thSquared) <= 0)
			return null;

		double th = Math.sqrt(thSquared);
		double t2 = alignZero(tm + th); // it is always greater than t1
		if (t2 <= 0)
			return null;

		Point geoPoint2 = ray.getPoint(t2);
		double t1 = alignZero(tm - th);
		return t1 <= 0 //
				? List.of(new GeoPoint(this, geoPoint2)) //
				: List.of(new GeoPoint(this, ray.getPoint(t1)), new GeoPoint(this, geoPoint2));
	}
}
