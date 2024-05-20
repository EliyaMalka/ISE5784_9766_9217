package primitives;

import static primitives.Util.isZero;

/**
 * The Vector class represents a vector in a 3D space. 
 * It extends the Point class and includes methods for vector operations.
 */
public class Vector extends Point {

    /**
     * Constructs a new Vector with the given x, y, and z coordinates.
     * 
     * @param x The x-coordinate of the vector.
     * @param y The y-coordinate of the vector.
     * @param z The z-coordinate of the vector.
     * @throws IllegalArgumentException if the vector is a zero vector.
     */
	public Vector(double x ,double y , double z) {
		super(x,y,z);
		if(x == 0 && y == 0 && z == 0)	{		
			throw new IllegalArgumentException("can't build the zero vector");
		}
	}
	
    /**
     * Constructs a new Vector with the given Double3 object.
     * 
     * @param XYZ The Double3 object containing the coordinates of the vector.
     * @throws IllegalArgumentException if the vector is a zero vector.
     */
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
	
    /**
     * Calculates the squared length of this vector.
     * 
     * @return The squared length of the vector.
     */
	public double lengthSquared() {
		
		return (double)(this.dotProduct(this));
	}
	
    /**
     * Calculates the length of this vector.
     * 
     * @return The length of the vector.
     */
	public double length() {
		return (double)(Math.sqrt(this.lengthSquared()));
	}
	
    /**
     * Adds the given vector to this vector.
     * 
     * @param V The vector to add.
     * @return A new vector that is the result of the addition.
     */
	public Vector add(Vector V) {
		return new Vector(xyz.add(V.xyz));
	}
	
    /**
     * Scales this vector by a given factor.
     * 
     * @param scal The scaling factor.
     * @return A new vector that is the result of the scaling.
     */
	public Vector scale(double scal) {
		return new Vector(xyz.scale(scal));
	}
	
    /**
     * Calculates the dot product of this vector and another vector.
     * 
     * @param V The vector to dot with.
     * @return The dot product of the two vectors.
     */
	public double dotProduct(Vector V) {
		
		return (double)(this.xyz.d1*V.xyz.d1+this.xyz.d2*V.xyz.d2+this.xyz.d3*V.xyz.d3);
	}
	
    /**
     * Calculates the cross product of this vector and another vector.
     * 
     * @param V The vector to cross with.
     * @return A new vector that is the result of the cross product.
     */
	public Vector crossProduct(Vector V) {
		
		return new Vector(this.xyz.d2*V.xyz.d3 -this.xyz.d3*V.xyz.d2,this.xyz.d3*V.xyz.d1 -this.xyz.d1*V.xyz.d3,this.xyz.d1*V.xyz.d2 -this.xyz.d2*V.xyz.d1);
	}
	
    /**
     * Normalizes this vector.
     * 
     * @return A new normalized vector.
     */
	public Vector normalize() {
	return new Vector((double)(this.xyz.d1)/(this.length()),(double)(this.xyz.d2)/(this.length()),(double)(this.xyz.d3)/(this.length()));
	}
	}
