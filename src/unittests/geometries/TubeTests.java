/**
 * 
 */
package unittests.geometries;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import primitives.Point;
import primitives.Vector;
import geometries.Tube;
import primitives.Ray;

/**
 * Unit tests for the Tube class
 */
class TubeTests {

	/**
	 * Test method for {@link geometries.Tube#getNormal(primitives.Point)}.
	 */
	@Test
	void testGetNormal() {
		Point pointRay = new Point(0, 0, 0);
		Vector vecRay = new Vector(0, 0, 8);
		Ray myRay = new Ray(pointRay, vecRay);
		Tube myTube = new Tube(myRay, 3);
		Point pointTube = new Point(3, 0, 5);
		Point pointBVA = new Point(3, 0, 0);
		Vector result = new Vector(1, 0, 0);

		// ============ Equivalence Partitions Tests ==============
		// TC01: Test if getNormal() returns the correct normal vector for a point on
		// the tube surface
		assertEquals(result, myTube.getNormal(pointTube), "getNormal() does not return correct values");

		// =============== Boundary Values Tests ==================
		// TC02: Test if getNormal() returns the correct normal vector for a point on
		// the tube surface that is orthogonal to the ray
		assertEquals(result, myTube.getNormal(pointBVA),
				"getNormal() does not return correct values with a point that makes 90 degrees with the point on the ray");
	}
}
