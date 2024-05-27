package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * The Cylinder class represents a cylinder in a 3D space.
 * It extends the Tube class and includes an additional height attribute.
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
     * @param R The radius of the cylinder.
     * @param H The height of the cylinder.
     */
	public Cylinder(Ray ax , double R , double H ) {
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
		
		//assumption: the ray head point is the center point of the lower base of the cylinder.
		
		// factor is project vector onto the direction of the axis
		double factor = axis.getDirection().dotProduct(P.subtract(axis.getHead()) ) / (axis.getDirection().lengthSquared()) ;
		
        // Define a small epsilon value to represent the acceptable error margin
        double epsilon = 0.000001;

        // Check if the point is at the top or bottom of the cylinder
        if (Math.abs(factor - this.height) < epsilon || Math.abs(factor) < epsilon) {
            // Return the normalized axis direction as the normal vector
            return axis.getDirection().normalize();
        } else {
            // Call the superclass method to calculate the normal vector
            return super.getNormal(P);
        }
		
	}
	
	
}
