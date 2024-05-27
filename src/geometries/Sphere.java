package geometries;

import primitives.Point;
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
     * @param P The center point of the sphere.
     * @param rad The radius of the sphere.
     */
	public Sphere(Point P , double rad) {
		super(rad);
		this.center = P;	
	}
	
	
	@Override
	public Vector getNormal(Point P) {
		
		return ( P.subtract(this.center) ).normalize() ;
		
	}

}
