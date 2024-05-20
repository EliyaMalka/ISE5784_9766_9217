package geometries;

import primitives.Point;
import primitives.Vector;

public class Sphere extends RadialGeometry {

	private final Point center;
	
	public Sphere(Point P , double rad) {
		super(rad);
		this.center = P;	
	}
	
	
	@Override
	public Vector getNormal(Point P) {
		// TODO Auto-generated method stub
		return null;
	}

}
