package geometries;

import java.util.List;

import primitives.Point;
import primitives.Vector;
import primitives.Ray;

/**
 * The Tube class represents a tube (cylindrical surface) in a 3D space.
 */
public class Tube extends RadialGeometry {

	/**
	 * The axis ray of the tube.
	 */
	protected final Ray axis;

	/**
	 * Constructs a new Tube with the given axis ray and radius.
	 * 
	 * @param axisRay The axis ray of the tube.
	 * @param radius  The radius of the tube.
	 */
	public Tube(Ray axisRay, double radius) {
		super(radius);
		this.axis = axisRay;
	}

	@Override
	public Vector getNormal(Point point) {
		double projection = point.subtract(axis.getHead()).dotProduct(axis.getDirection());
		return point.subtract(axis.getPoint(projection)).normalize();
	}

	@Override
	protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
		return null; // TODO BONUS: be implemented
	}

}
