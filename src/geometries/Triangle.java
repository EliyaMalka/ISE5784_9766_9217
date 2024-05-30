package geometries;

import java.util.Iterator;

import primitives.Point;
import primitives.Vector;

/**
 * The Triangle class represents a triangle in a 3D space. It extends the
 * Polygon class and is defined by three vertices.
 */
public class Triangle extends Polygon {

	public Triangle(Point vert1, Point vert2, Point vert3) {
		super(vert1, vert2, vert3);
	}

}
