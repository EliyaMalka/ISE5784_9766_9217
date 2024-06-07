/**
 * 
 */
package unittests.geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import geometries.Plane;
import geometries.Sphere;

import static primitives.Util.*;

import java.awt.TrayIcon;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the Plane class.
 */
class PlaneTests {

	/**
	 * Test method for
	 * {@link geometries.Plane#Plane(primitives.Point, primitives.Point, primitives.Point)}.
	 */
	@Test
	public void testConstructorPlane() {
		Point p1 = new Point(1, 0, 0);
		Point p2 = new Point(1, 0, 0);
		Point p3 = new Point(0, 0, 1);

		Point sameRayP3_1 = new Point(0, 0, 3);
		Point sameRayP3_2 = new Point(0, 0, 9);

		// =============== Boundary Values Tests ==================
		// Test for constructing a plane with two identical points
		assertThrows(IllegalArgumentException.class, () -> new Plane(p1, p2, p3),
				"The constructor does not throw an IllegalArgumentException when given two identical points");

		// Test for constructing a plane with three collinear points
		assertThrows(IllegalArgumentException.class, () -> new Plane(p3, sameRayP3_1, sameRayP3_2),
				"The constructor does not throw an IllegalArgumentException when given three collinear points");
	}

	/**
	 * Test method for {@link geometries.Plane#getNormal(primitives.Point)}.
	 */
	@Test
	void testGetNormalPoint() {
		Point p1 = new Point(1, 0, 0);
		Point p2 = new Point(0, 1, 0);
		Point p3 = new Point(0, 0, 1);

		Vector normalVector = new Vector(1 / Math.sqrt(3), 1 / Math.sqrt(3), 1 / Math.sqrt(3)); // normalized vector

		Plane myPlane = new Plane(p1, p2, p3);

		// ============ Equivalence Partitions Tests ==============
		// Test for correct normal vector calculation
		assertTrue(
				(myPlane.getNormal(p1).equals(normalVector))
						|| (myPlane.getNormal(p1).equals(normalVector.scale(-1.0))),
				"getNormal(Point) does not return the correct normal vector");
	}
	
	private final Point p200 = new Point(2, 0, 0);
	private final Point p002 = new Point(0, 0, 2);
	private final Point p100 = new Point(1, 0, 0);
	
	/**
	* Test method for {@link geometries.plane#findIntersections(primitives.Ray)}.
	*/
	@Test
	public void testFindIntersections() { 
		
		Plane plane = new Plane(p200, p002,p100); 

		final Point p010Negativ = new Point(0, -1, 0);
		final Vector v011 = new Vector(0, 1, 1); 
        final Ray ray = new Ray(p010Negativ,v011);
        
		final Point p001 = new Point(0, 0, 1);



	// ============ Equivalence Partitions Tests ============== 
		assertEquals(p001 ,plane.findIntersections(ray));

		
		
		

	// =============== Boundary Values Tests ==================


	}
	
}
