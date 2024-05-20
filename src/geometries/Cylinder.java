package geometries;

import primitives.Ray;

public class Cylinder extends Tube {
	
	private final double height;
	
	public Cylinder(Ray ax , double R , double H ) {
	super(ax, R);
	this.height = H;
	}

	
	
	
}
