package geometries;

/**
 * The RadialGeometry class represents a radial geometry shape in a 3D space. It
 * is an abstract class that provides a common base for various radial
 * geometries like spheres, cylinders, and cones.
 */
public abstract class RadialGeometry implements Geometry {

	/**
	 * The radius of the radial geometry shape.
	 */
	protected final double radius;
	/**
	 * The squared radius of the radial geometry shape.
	 */
	protected final double radiusSquared;

	/**
	 * Constructs a new RadialGeometry with the given radius.
	 * 
	 * @param radius The radius of the radial geometry shape.
	 */
	public RadialGeometry(double radius) {
		this.radius = radius;
		this.radiusSquared = radius * radius;
	}
}
