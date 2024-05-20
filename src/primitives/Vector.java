package primitives;

import static primitives.Util.isZero;

public class Vector extends Point {

	public Vector(double x ,double y , double z) {
		super(x,y,z);
		if(x == 0 && y == 0 && z == 0)	{		
			throw new IllegalArgumentException("can't build the zero vector");
		}
	}
	
	public Vector(Double3 XYZ) {
		super(XYZ);
		if (XYZ.d1 == 0 && XYZ.d2 == 0 && XYZ.d3 == 0) 
			throw new IllegalArgumentException("can't build the zero vector");
	}
	
	@Override
	public boolean equals(Object obj) {
	      if (this == obj) return true;
	      return (obj instanceof Vector other)
	            && ZERO.equals(new Point(this.xyz.subtract(other.xyz) ) ) ;
		}
	
	@Override
	public String toString() {
		return "<" + this.xyz.d1 + " , " + this.xyz.d2 + " , " + this.xyz.d3 + ">";
	}
	
	public double lengthSquared() {
		
		return (double)(this.dotProduct(this));
	}
	
	public double length() {
		return (double)(Math.sqrt(this.lengthSquared()));
	}
	
	public Vector add(Vector V) {
		return new Vector(xyz.add(V.xyz));
	}
	
	
	public Vector scale(double scal) {
		return new Vector(xyz.scale(scal));
	}
	
	public double dotProduct(Vector V) {
		
		return (double)(this.xyz.d1*V.xyz.d1+this.xyz.d2*V.xyz.d2+this.xyz.d3*V.xyz.d3);
	}
	
	public Vector crossProduct(Vector V) {
		
		return new Vector(this.xyz.d2*V.xyz.d3 -this.xyz.d3*V.xyz.d2,this.xyz.d3*V.xyz.d1 -this.xyz.d1*V.xyz.d3,this.xyz.d1*V.xyz.d2 -this.xyz.d2*V.xyz.d1);
	}
	
	
	public Vector normalize() {
	return new Vector((double)(this.xyz.d1)/(this.length()),(double)(this.xyz.d2)/(this.length()),(double)(this.xyz.d3)/(this.length()));
	}
	}
