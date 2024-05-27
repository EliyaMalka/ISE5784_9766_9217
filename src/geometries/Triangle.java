package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * The Triangle class represents a triangle in a 3D space.
 * It extends the Polygon class and is defined by three vertices.
 */
public class Triangle extends Polygon {

	public Triangle(Point... vertices) {
	super(vertices);
	}
	
	@Override
	public Vector getNormal(Point point) {
		return null;
	}
}
