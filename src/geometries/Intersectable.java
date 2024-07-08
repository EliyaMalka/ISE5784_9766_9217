package geometries;

import java.util.List;
import primitives.Point;
import primitives.Ray;

/**
 * Intersectable interface represents a geometric object that can be intersected
 * by a ray. It defines a method to find the intersection points between a ray
 * and the geometric object.
 */
public abstract class Intersectable {

	public static class GeoPoint {
		public Geometry geometry;
		public Point point;

		public GeoPoint(Geometry geometry, Point point) {
			this.geometry = geometry;
			this.point = point;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (getClass() != obj.getClass()) {
				return false;
			}
			GeoPoint other = (GeoPoint) obj;
			return geometry.equals(other.geometry) && point.equals(other.point);
		}

		@Override
		public String toString() {
			return "GeoPoint [geometry=" + geometry + ", point=" + point + "]";
		}
	}

	/**
	 * Finds the intersection points between a given ray and the geometric object.
	 *
	 * @param ray the ray for which we want to find intersections with the geometric
	 *            object
	 * @return a list of points where the ray intersects the geometric object. If
	 *         there are no intersections, an empty list or null may be returned.
	 */
	public List<Point> findIntersections(Ray ray) {
		var geoList = findGeoIntersections(ray);
		return geoList == null ? null : geoList.stream().map(gp -> gp.point).toList();
	}

	public List<GeoPoint> findGeoIntersections(Ray ray) {
		return findGeoIntersectionsHelper(ray);
	}

	protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray);
}