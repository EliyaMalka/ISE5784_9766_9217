package unittests.geometries;

import geometries.*;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the Geometries class. link to model in geoGebra:
 * https://www.geogebra.org/calculator/qvqhw6bc
 */
public class GeometriesTests {

	/**
	 * Test method for findIntersections function. Tests various scenarios of
	 * finding intersections between rays and geometries.
	 */
	@Test
	public void findIntersection() {

		Geometries emptyListObjects = new Geometries();
		Geometries nonIntersectionList = new Geometries(new Sphere(new Point(2, 0, 0), 1.0),
				new Triangle(new Point(0, 0, 2), new Point(0, -2, 0), new Point(0, 2, 0)));
		Geometries oneIntersectionList = new Geometries(new Sphere(new Point(2, 0, 0), 1.0),
				new Triangle(new Point(0, 0, 2), new Point(0, -2, 0), new Point(0, 2, 0)));
		Geometries intersectionList = new Geometries(new Sphere(new Point(2, 0, 0), 1.0),
				new Triangle(new Point(0, 0, 2), new Point(0, -2, 0), new Point(0, 2, 0)),
				new Sphere(new Point(0, -6, 0), 2.0));
		Geometries allGeometriesIntersaction = new Geometries(new Sphere(new Point(2, 0, 0), 1.0),
				new Triangle(new Point(0, 0, 2), new Point(0, -2, 0), new Point(0, 2, 0)));

		Ray rayTC00 = new Ray(new Point(2, 3, 4), new Vector(1, 0, 0));
		Ray rayTC01 = new Ray(new Point(0, 0, 3), new Vector(6, 0, -3));
		Ray rayTC02 = new Ray(new Point(-4, 0, 0), new Vector(4, 0, 1));
		Ray rayTC03 = new Ray(new Point(-2, 0, 1), new Vector(5, 0, -1));
		Ray rayTC04 = new Ray(new Point(-2, 0, 1), new Vector(5, 0, -1));

		// =============== Boundary Values Tests ==================
		// TC00
		// check if empty list return null
		assertNull(emptyListObjects.findIntersections(rayTC00), "An empty list does not return null");

		// TC01
		// check BVA without intersections
		assertNull(nonIntersectionList.findIntersections(rayTC01), "A non-intersection ray does not return 0");

		// TC02
		// check BVA with 1 intersection
		assertEquals(1, oneIntersectionList.findIntersections(rayTC02).size(),
				"A ray with one intersection does not return one point");

		// TC03
		// check BVA with all geometries intersection
		assertEquals(3, allGeometriesIntersaction.findIntersections(rayTC03).size(),
				"A ray that intersections all geometries does not return correct points number");

		// ============ Equivalence Partitions Tests ==============

		// TC04
		// check EP with 3 intersection
		assertEquals(3, intersectionList.findIntersections(rayTC04).size(),
				"intersections ray does not return correct points number");
	}

}
