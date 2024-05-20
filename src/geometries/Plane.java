package geometries;

import primitives.Point;
import primitives.Vector;

public class Plane implements Geometry {

	private final Point q;
	private final Vector normal;
	
	public Plane(Point P1 ,Point P2 ,Point P3 ) {
	
	this.normal = null;
	this.q = P1;
		
	}
	
	public Plane(Point P ,Vector V ) {
		
	this.q = P;	
	this.normal = V.normalize();
	
	}
	
	
	@Override
	public Vector getNormal(Point P) {
		
		
		
		return null;
	}
	
	// לא מימשנו !!!
	public Vector getNormal() {
		
		return this.normal;
	}
	

}
