package geometries;

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
	protected final Ray axis ;
	
    /**
     * Constructs a new Tube with the given axis ray and radius.
     * 
     * @param ax The axis ray of the tube.
     * @param R The radius of the tube.
     */
	public Tube(Ray ax , double R) {
		super(R);
		this.axis = ax;
	}
	
	@Override
	public Vector getNormal(Point P) {
		// TODO Auto-generated method stub
		return null;
	}

}
