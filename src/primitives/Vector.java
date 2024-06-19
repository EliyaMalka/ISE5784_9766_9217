package primitives;

/**
 * The Vector class represents a vector in a 3D space. It extends the Point
 * class and includes methods for vector operations.
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
	public Vector(double x, double y, double z) {
		super(x, y, z);
		if (xyz.equals(Double3.ZERO))
			throw new IllegalArgumentException("can't build the zero vector");
	}

	/**
	 * Constructs a new Vector with the given Double3 object.
	 * 
	 * @param xyzDouble3 The Double3 object containing the coordinates of the
	 *                   vector.
	 * @throws IllegalArgumentException if the vector is a zero vector.
	 */
	public Vector(Double3 xyzDouble3) {
		super(xyzDouble3);
		if (xyz.equals(Double3.ZERO))
			throw new IllegalArgumentException("can't build the zero vector");
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		return (obj instanceof Vector other) && super.equals(other);
	}

	@Override
	public String toString() {
		return "<" + super.xyz + ">";
	}

	/**
	 * Calculates the squared length of this vector.
	 * 
	 * @return The squared length of the vector.
	 */
	public double lengthSquared() {
		return this.dotProduct(this);
	}

	/**
	 * Calculates the length of this vector.
	 * 
	 * @return The length of the vector.
	 */
	public double length() {
		return Math.sqrt(this.lengthSquared());
	}

	/**
	 * Adds the given vector to this vector.
	 * 
	 * @param vector The vector to add.
	 * @return A new vector that is the result of the addition.
	 */
	public Vector add(Vector vector) {
		return new Vector(xyz.add(vector.xyz));
	}

	/**
	 * Scales this vector by a given factor.
	 * 
	 * @param scal The scaling factor.
	 * @return A new vector that is the result of the scaling.
	 */
	public Vector scale(double scale) {
		return new Vector(xyz.scale(scale));
	}

	/**
	 * Calculates the dot product of this vector and another vector.
	 * 
	 * @param vector The vector to dot with.
	 * @return The dot product of the two vectors.
	 */
	public double dotProduct(Vector vector) {
		return this.xyz.d1 * vector.xyz.d1 + this.xyz.d2 * vector.xyz.d2 + this.xyz.d3 * vector.xyz.d3;
	}

	/**
	 * Calculates the cross product of this vector and another vector.
	 * 
	 * @param vector The vector to cross with.
	 * @return A new vector that is the result of the cross product.
	 */
	public Vector crossProduct(Vector vector) {
		return new Vector( //
				this.xyz.d2 * vector.xyz.d3 - this.xyz.d3 * vector.xyz.d2, //
				this.xyz.d3 * vector.xyz.d1 - this.xyz.d1 * vector.xyz.d3, //
				this.xyz.d1 * vector.xyz.d2 - this.xyz.d2 * vector.xyz.d1);
	}

	/**
	 * Normalizes this vector.
	 * 
	 * @return A new normalized vector.
	 */
	public Vector normalize() {
		return this.scale(1 / length());
	}
}
