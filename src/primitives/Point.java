package primitives;

/**
 * The Point class represents a point in 3D space using a Double3 object. It
 * provides methods for point manipulation such as addition, subtraction and
 * distance calculations.
 */
public class Point {

	/** The coordinates of the point. */
	protected final Double3 xyz;

	/** A static constant representing the origin point (0,0,0). */
	public static Point ZERO = new Point(Double3.ZERO);

	/**
	 * Constructor that initializes a point with the given x, y, and z coordinates.
	 * 
	 * @param x the x coordinate value
	 * @param y the y coordinate value
	 * @param z the z coordinate value
	 */
	public Point(double x, double y, double z) {
		this.xyz = new Double3(x, y, z);
	}

	/**
	 * Constructor that initializes a point with a given triad of coordinates'
	 * values.
	 * 
	 * @param xyz the triad of numbers containing the coordinates' values
	 */
	public Point(Double3 xyz) {
		this.xyz = xyz;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		return (obj instanceof Point other) && this.xyz.equals(other.xyz);
	}

	@Override
	public String toString() {
		return "" + this.xyz;
	}

	/**
	 * Adds a vector to this point and returns a new point.
	 * 
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
		double dx = xyz.d1 - P.xyz.d1;
		double dy = xyz.d2 - P.xyz.d2;
		double dz = xyz.d3 - P.xyz.d3;
		return dx * dx + dy * dy + dz * dz;
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
