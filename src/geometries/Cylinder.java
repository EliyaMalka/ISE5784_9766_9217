package geometries;

import primitives.Ray;

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

	
	
	
}
