package primitives;

import static primitives.Util.isZero;

/**
The Point class represents a point in 3D space using a Double3 object.
It provides methods for point manipulation such as addition, subtraction,
and distance calculations.
*/

public class Point {
	
    // The coordinates of the point.
	protected final Double3 xyz;
	
    // A static constant representing the origin point (0,0,0).
	public static Point ZERO = new Point(0,0,0); 
	
    /**
     * Constructor that initializes a point with the given x, y, and z coordinates. 
     * @param x the x coordinate
     * @param y the y coordinate
     * @param z the z coordinate
     */
	public Point(double x ,double y , double z ) {
		
		this.xyz = new Double3(x,y,z);
	}
	
    /**
     * Constructor that initializes a point with a given Double3 object.
     * 
     * @param XYZ the Double3 object containing the coordinates
     */
	public Point(Double3 XYZ ) {
		
		this.xyz = XYZ;
	}
	
	

	@Override
	public boolean equals(Object obj) {
	      if (this == obj) return true;
	      return (obj instanceof Point other)
	            && isZero(this.xyz.d1 - other.xyz.d1)
	            && isZero(this.xyz.d2 - other.xyz.d2)
	            && isZero(this.xyz.d3 - other.xyz.d3);
	}
	

	@Override
	public String toString() {
		return "(" + this.xyz.d1 + " , " + this.xyz.d2 + " , " + this.xyz.d3 + ")";
	}
	
	   /**
     * Adds a vector to this point and returns a new point. 
     * @param vec the vector to add
     * @return a new point resulting from the addition
     */
	public Point add(Vector vec) {			
		return new Point(this.xyz.add(vec.xyz));
	}
	
    /**
     * Subtracts a point from this point and returns a new vector.
     * 
     * @param secondPoint the point to subtract
     * @return a new vector resulting from the subtraction
     */
	public Vector subtract(Point secondPoint) {
		return (new Vector(this.xyz.subtract(secondPoint.xyz)));
	}
	
	   /**
     * Calculates the square of the distance between this point and another point.
     * 
     * @param P the other point
     * @return the square of the distance between the points
     */
	public double distanceSquared(Point P) {
		
		Double3 distansVec = (xyz.subtract(P.xyz)).product(xyz.subtract(P.xyz));
		return (distansVec.d1 + distansVec.d2 + distansVec.d3);
	}
	
	   /**
     * Calculates the distance between this point and another point.
     * 
     * @param P the other point
     * @return the distance between the points
     */
	public double distance(Point P) {
		return Math.sqrt(distanceSquared(P));
	}
	

	
}
