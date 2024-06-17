/**
 * 
 */
package unittests.geometries;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Comparator;

import primitives.*;
import geometries.*;

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

	private final Point p100 = new Point(1, 0, 0);

	/**
	* Test method for {@link geometries.Sphere#findIntersections(primitives.Ray)}.
	* link to GeoGebra model : https://www.geogebra.org/3d/s52zjgbw
	*/
	@Test
	public void testFindIntersections() { 
		
		Sphere sphere = new Sphere(p100, 1.0); 
		
		final Point p01 = new Point(-1, 0, 0);
		final Vector v310 = new Vector(3, 1, 0); 
		final Vector v110 = new Vector(1, 1, 0);
		final Point gp1 = new Point(0.0651530771650466, 0.355051025721682, 0); 
		final Point gp2 = new Point(1.53484692283495, 0.844948974278318, 0); 
		final var exp = List.of(gp1, gp2); 
		//----------------------------------------------------------------------
		final Point p000 = new Point(0, 0, 0);
		final Point p200 = new Point(2, 0, 0);
		final Point p101 = new Point(1, 0, 1);
		final Point p300 = new Point(3, 0, 0);
		final Point pTC15 = new Point(0.5, 0, 0);
		final Point p110 = new Point(1, 1, 0);
		final Point p001 = new Point(0, 0, 1);


		final Point pointInSphere = new Point(1.5, 0, 0);
		final Point expTC03 = new Point(1, 1, 0);
		final Point pointOutSphere = new Point(2.5, 0, 0);
		
		final var expTC13 = List.of(p000, p200); 
		
		final Vector v_0o510 = new Vector(-0.5, 1, 0); 
		final Vector v100 = new Vector(1, 0, 0); 
		final Vector v0_11 = new Vector(0, -1, 1); 
		final Vector v100Negativ = new Vector(-1, 0, 0); 
		final Vector v1_0o50 = new Vector(1, -0.5, 0); 
		final Vector v001 = new Vector(0, 0, 1);
		
		Ray rayTC03 = new Ray(pointInSphere, v_0o510);
		Ray rayTC04 = new Ray(pointOutSphere, v1_0o50);
		Ray rayTC11 = new Ray(p110, v0_11);
		Ray rayTC12 = new Ray(p110, v_0o510);
		
		// ============ Equivalence Partitions Tests ============== 
		// TC01: Ray's line is outside the sphere (0 points)
		assertNull(sphere.findIntersections(new Ray(p01, v110)), "Ray's line out of sphere"); 
		assertNull(sphere.findIntersections(new Ray(p01, v110)), "Ray's line out of sphere"); 
		
		// TC02: Ray starts before and crosses the sphere (2 points) 
		final var result1 = sphere.findIntersections(new Ray(p01, v310))
				.stream()
				.sorted(Comparator.comparingDouble(p -> p.distance(p01)))
				.toList(); 
		assertEquals(2, result1.size(), "Wrong number of points"); 
		assertEquals(exp, result1, "Ray crosses sphere");
	
	// TC03: Ray starts inside the sphere (1 point)
		assertEquals(expTC03, sphere.findIntersections(rayTC03).get(0), "ERROR TC03: Ray starts **inside the sphere (1 point)");

	// TC04: Ray starts after the sphere (0 points)
		assertNull(sphere.findIntersections(rayTC04), "ERROR TC04: Ray starts **after the sphere (0 point)");

	// =============== Boundary Values Tests ==================
	// **** Group: Ray's line crosses the sphere (but not the center)
	// TC11: Ray starts at sphere and goes inside (1 points)
		assertEquals(new Point(1,0,1),sphere.findIntersections(rayTC11).get(0), "ERROR TC11: Ray starts at sphere and goes **inside (not the throw the center) (1 points)");
	// TC12: Ray starts at sphere and goes outside (0 points)
		assertNull(sphere.findIntersections(rayTC12), "ERROR TC11: Ray starts at sphere and goes **outside (not the throw the center) (0 points)");
		
	// **** Group: Ray's line goes through the center
	// TC13: Ray starts before the sphere (2 points)
		assertEquals(expTC13,sphere.findIntersections(new Ray(p01, v100)).stream()
				.sorted(Comparator.comparingDouble(p -> p.distance(p01)))
				.toList());
	// TC14: Ray starts at sphere and goes inside (1 points)
		assertEquals(p000,sphere.findIntersections(new Ray(p200, v100Negativ)).get(0));
	// TC15: Ray starts inside (1 points)
		assertEquals(p000,sphere.findIntersections(new Ray(pTC15, v100Negativ)).get(0));
	// TC16: Ray starts at the center (1 points)
		assertEquals(p200,sphere.findIntersections(new Ray(p100, v100)).get(0));
	// TC17: Ray starts at sphere and goes outside (0 points)
		assertNull(sphere.findIntersections(new Ray(p000,v100Negativ)));
	// TC18: Ray starts after sphere (0 points)
		assertNull(sphere.findIntersections(new Ray(p01,v100Negativ)));
		
	// **** Group: Ray's line is tangent to the sphere (all tests 0 points)
	// TC19: Ray starts before the tangent point
		assertNull(sphere.findIntersections(new Ray(p001, v100)));
	// TC20: Ray starts at the tangent point
		assertNull(sphere.findIntersections(new Ray(p101,v100)));
	// TC21: Ray starts after the tangent point
		assertNull(sphere.findIntersections(new Ray(p001,v100Negativ)));
		
	// **** Group: Special cases
	// TC22: Ray's line is outside, ray is orthogonal to ray start to sphere's center line
		assertNull(sphere.findIntersections(new Ray(p300,v001)));

	}
}
