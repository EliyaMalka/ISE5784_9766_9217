/**
 * 
 */
package unittests.geometries;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import geometries.Triangle;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * Unit tests for the Triangle class.
 */
class TriangleTests {

	/**
	 * Test method for {@link geometries.Triangle#getNormal(primitives.Point)}. This
	 * test checks the correctness of the getNormal method in the Triangle class.
	 * link to GeoGebra model : https://www.geogebra.org/calculator/jkwvzcyk
	 * 
	 */
	@Test
	void getNormalTest() {
		// ============ Equivalence Partitions Tests ==============

		// Test if the normal calculation is correct
		Point p200 = new Point(2, 0, 0); // first vertex of the triangle
		Point p060 = new Point(0, 6, 0); // second vertex of the triangle
		Point p000 = new Point(0, 0, 0); // third vertex of the triangle
		Vector expectedNormal = new Vector(0, 0, 1); // the expected normal vector

		Triangle triangle = new Triangle(p200, p060, p000); // create a triangle with the above vertices

		// Check if the normal vector returned by getNormal is as expected
		Vector normal = triangle.getNormal(p000); // normal vector at point p3
		assertTrue(normal.equals(expectedNormal) || normal.equals(expectedNormal.scale(-1.0)),
				"getNormal() doesn't return the correct normal vector");
	}

	private final Point p201 = new Point(2, 0, 1);
	private final Point p003 = new Point(0, 0, 3);
	private final Point p_201 = new Point(-2, 0, 1);

	void testFindIntersections() {

		Triangle triangle = new Triangle(p201, p003, p_201);

		final Point p002 = new Point(0, 0, 2); // TC01
		final Point p302 = new Point(3, 0, 2); // TC02
		final Point p400 = new Point(4, 0, 0); // TC03

		final Point p003 = new Point(0, 0, 3); // TC10
		final Point p102 = new Point(1, 0, 2); // TC11
		final Point p301 = new Point(3, 0, 1); // TC12

		final Vector v032 = new Vector(0, 3, 2); // TC01
		final Vector v332 = new Vector(3, 3, 2); // TC02
		final Vector v430 = new Vector(4, 3, 0); // TC03

		final Vector v033 = new Vector(0, 3, 3); // TC10
		final Vector v132 = new Vector(1, 3, 2); // TC11
		final Vector v331 = new Vector(3, 3, 1); // TC12

		final Point p0_30RayPoint = new Point(0, -3, 0); // point of rays

		final Ray rayTC01 = new Ray(p0_30RayPoint, v032);
		final Ray rayTC02 = new Ray(p0_30RayPoint, v332);
		final Ray rayTC03 = new Ray(p0_30RayPoint, v430);

		final Ray rayTC10 = new Ray(p0_30RayPoint, v033);
		final Ray rayTC11 = new Ray(p0_30RayPoint, v132);
		final Ray rayTC12 = new Ray(p0_30RayPoint, v331);

		// ============ Equivalence Partitions Tests ==============
		//TC01
		assertEquals(p002, triangle.findIntersections(rayTC01),"A intersection ray inside triangle does not return a correct point");
		//TC02
		assertNull(triangle.findIntersections(rayTC02), "A non - intersection ray outside triangle against edge does not return a correct point");
		//TC03
		assertNull(triangle.findIntersections(rayTC03), "A non - intersection ray outside triangle against vertex does not return a correct point");

		// =============== Boundary Values Tests ==================
		//TC10
		assertEquals(p003, triangle.findIntersections(rayTC10), "A intersection ray on vertex does not return a correct point");
		//TC11
		assertEquals(p102, triangle.findIntersections(rayTC11), "A intersection ray on edge does not return a correct point");
		//TC12
		assertNull(triangle.findIntersections(rayTC12), "A intersection ray on edge's continuation does not return a correct point");

	}
}
