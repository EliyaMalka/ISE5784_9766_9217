package geometries;


import primitives.*;

/**
 * The Geometry interface represents a geometric shape in a 3D space. It defines
 * a method for computing the normal vector at a given point on the shape.
 */
public abstract class Geometry extends Intersectable {

	protected Color emission = Color.BLACK;
	/**
	 * Computes the normal vector at a given point on the shape.
	 * 
	 * @param point The point on the shape.
	 * @return The normal vector at the given point.
	 */
	public abstract Vector getNormal(Point point);

	public Color getEmission() {
		return emission;
	}
	
	public Geometry setEmission(Color emission) {
		this.emission = emission;
		return this;
	}
}
