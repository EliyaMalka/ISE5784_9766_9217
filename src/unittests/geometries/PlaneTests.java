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
	* link to GeoGebra model : https://www.geogebra.org/calculator/kjc6u59j
	*/
	@Test
	public void testFindIntersections() { 
		
		
		
		Plane plane = new Plane(p200, p002,p100); 

		final Point p010Negativ = new Point(0, -1, 0);
		final Point p042 = new Point(0, 4, 2);

		final Vector v012 = new Vector(0, 1, 2); 
		final Vector v011Negativ = new Vector(0, -1, -1); 
		final Vector v001 = new Vector(0, 0, 1); 
		final Vector v010 = new Vector(0, 1, 0); 
		final Vector v010Negativ = new Vector(0, -1, 0); 
		
		final Ray rayTC01 = new Ray(p010Negativ,v012);
		final Ray rayTC11 = new Ray(p010Negativ,v001);
		final Ray rayTC12 = new Ray(p100,v001);
		final Ray rayTC21 = new Ray(p042,v010Negativ);
		final Ray rayTC22 = new Ray(p042,v010);
		final Ray rayTC23 = new Ray(p002,v010);
		final Ray rayTC31 = new Ray(p002,v011Negativ);
        final Ray rayTC41 = new Ray(p200,v011Negativ);
        



	// ============ Equivalence Partitions Tests ============== 
	// TC01 A ray that starts outside the plane, not parallel to the plane,
	//makes a non-right angle with the plane, and cuts the plane.
		assertEquals(p002 ,plane.findIntersections(rayTC01),"ERROR TC01 in ray that starts outside the plane, not parallel to the plane,"
				+ "	makes a non-right angle with the plane, and cuts the plane." );

		
		
		

	// =============== Boundary Values Tests ==================
		// **** Group: ray parallel to the plane, inside the plane and outside. ****
		
		// TC11 A ray out of the plane and parallel to it. (0 points)
		assertNull(plane.findIntersections(rayTC11),"ERROR TC11 in ray ***out of the plane and parallel to it.");
		//TC12 ray inside the plane (0 points)
		assertNull(plane.findIntersections(rayTC12),"ERROR TC12 in ray ***inside of the plane and parallel to it.");
		
		// **** Group: A ray is perpendicular to the plane ****
		
		// TC21 A perpendicular ray **before the plane (1 points)
		assertEquals(p002 ,plane.findIntersections(rayTC21),"ERROR TC21 in perpendicular ray **before the plane");
		// TC22 A perpendicular ray **after the plane (0 points)
		assertNull(plane.findIntersections(rayTC22),"ERROR TC22 in perpendicular ray **after the plane");
		// TC23 perpendicular ray **in the plane (0 points)
		assertNull(plane.findIntersections(rayTC23),"ERROR TC23 in perpendicular ray **in the plane");
		
		//  **** Group: One case of a ray neither parallel nor perpendicular to the plane 
		//        but starting from inside the plane ****
		//        TC31
		assertNull(plane.findIntersections(rayTC31),"ERROR TC31 ray neither parallel nor perpendicular to the plane"
				+ "	but starting from inside the plane");
		
        // **** Group: One case similar to the previous case 
		// but the beginning of the ray starts at the reference point of the plane
		//        TC41

		assertNull(plane.findIntersections(rayTC41),"ERROR TC41 in ERROR TC31 ray neither parallel nor perpendicular to the plane"
				+ "	but the beginning of the ray starts at the reference point of the plane");



	}
	
}
