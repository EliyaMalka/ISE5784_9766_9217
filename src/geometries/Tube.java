package geometries;

import primitives.Point;
import primitives.Vector;
import primitives.Ray;

public class Tube extends RadialGeometry {

	protected final Ray axis ;
	
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
