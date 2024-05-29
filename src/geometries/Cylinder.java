package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * The Cylinder class represents a cylinder in a 3D space. It extends the Tube
 * class and includes an additional height attribute.
 */
public class Cylinder extends Tube {

	/**
	 * The height of the cylinder.
	 */
	private final double height;

	/**
	 * Constructs a new Cylinder with the given axis ray, radius, and height.
	 * 
	 * @param ax The axis ray of the cylinder.
	 * @param R  The radius of the cylinder.
	 * @param H  The height of the cylinder.
	 */
	public Cylinder(Ray ax, double R, double H) {
		super(ax, R);
		this.height = H;
	}

	/**
	 * Computes the normal vector at a given point on the surface of the cylinder.
	 * 
	 * @param point The point on the surface of the cylinder.
	 * @return The normal vector at the given point.
	 */
	@Override
	public Vector getNormal(Point P) {
		// center points on the bases
		if (P.equals(axis.getHead()) || P.equals(axis.getHead().add(axis.getDirection().normalize().scale(height)))) {
			return axis.getDirection().normalize();

		}
		// points on the bases
		if (P.subtract(axis.getHead()).dotProduct(axis.getDirection()) == 0 || axis.getHead()
				.add(axis.getDirection().normalize().scale(height)).subtract(P).dotProduct(axis.getDirection()) == 0) {
			return axis.getDirection().normalize();
		} else { // points on the surface
			return super.getNormal(P);
		}

	}
}