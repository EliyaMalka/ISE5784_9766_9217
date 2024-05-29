/**
 * 
 */
package unittests.geometries;

import static org.junit.jupiter.api.Assertions.*;
import primitives.Point;
import primitives.Vector;
import geometries.RadialGeometry;
import geometries.Sphere;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the Sphere class.
 */
class SphereTests {

	/**
	 * Test method for {@link geometries.Sphere#getNormal(primitives.Point)}. This
	 * test checks the correctness of the getNormal method in the Sphere class.
	 */
	@Test
	void testGetNormal() {
		// ============ Equivalence Partitions Tests ==============

		// Test if the normal calculation is correct
		Point center = new Point(0, 0, 0); // center of the sphere
		Sphere mySphere = new Sphere(center, 3.0); // create a sphere with radius 3
		Point pointOnSphere = new Point(1, 2, 2); // a point on the sphere surface
		Vector expectedNormal = new Vector(1, 2, 2).normalize(); // the expected normal vector, normalized

		// Check if the normal vector returned by getNormal is as expected
		assertEquals(expectedNormal, mySphere.getNormal(pointOnSphere),
				"getNormal() doesn't return the correct normal vector");
	}
}
