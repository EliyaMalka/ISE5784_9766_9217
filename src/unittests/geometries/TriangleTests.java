package unittests.geometries;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import geometries.Triangle;
import primitives.Point;
import primitives.Vector;

/**
 * Unit tests for the Triangle class.
 */
class TriangleTests {

	/**
	 * Test method for {@link geometries.Triangle#getNormal(primitives.Point)}. This
	 * test checks the correctness of the getNormal method in the Triangle class.
	 */
	@Test
	void getNormalTest() {
		// ============ Equivalence Partitions Tests ==============

		// Test if the normal calculation is correct
		Point p1 = new Point(2, 0, 0); // first vertex of the triangle
		Point p2 = new Point(0, 6, 0); // second vertex of the triangle
		Point p3 = new Point(0, 0, 0); // third vertex of the triangle
		Vector expectedNormal = new Vector(0, 0, 1); // the expected normal vector

		Triangle triangle = new Triangle(p1, p2, p3); // create a triangle with the above vertices

		// Check if the normal vector returned by getNormal is as expected
		Vector normal = triangle.getNormal(p3); // normal vector at point p3
		assertTrue(normal.equals(expectedNormal) || normal.equals(expectedNormal.scale(-1.0)),
				"getNormal() doesn't return the correct normal vector");
	}
}
