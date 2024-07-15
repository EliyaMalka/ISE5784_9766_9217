/**
 * ***********bonus**************
 */
package unittests.geometries;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import geometries.Cylinder;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * Unit tests for geometries.Cylinder class.
 */
class CylinderTests {

	/**
	 * Test method for {@link geometries.Cylinder#getNormal(primitives.Point)}. This
	 * test checks the functionality of the getNormal method for the Cylinder class.
	 */
	@Test
	void testGetNormal() {
		Point pointOfRy = new Point(0, 0, 0);
		Vector vecOfRay = new Vector(0, 0, 1);
		Ray ray = new Ray(pointOfRy, vecOfRay);
		Cylinder cylinder1 = new Cylinder(ray, 3.0, 10.0);

		Point pointE1 = new Point(3, 0, 5);
		Point pointE2 = new Point(1, 0, 0);
		Point pointE3 = new Point(0, 1, 10);

		Point pointB1 = new Point(0, 0, 0);
		Point pointB3 = new Point(3, 3, 0);

		Vector result = new Vector(1, 0, 0);

		// ============ Equivalence Partitions Tests ==============
		// TC1: Testing the normal vector on the side surface of the cylinder
		assertEquals(result, cylinder1.getNormal(pointE1), "getNormal() does not return the correct normal vector");

		// TC2: Testing the normal vector at the bottom base of the cylinder
		assertTrue(
				vecOfRay.normalize().equals(cylinder1.getNormal(pointE2))
						|| vecOfRay.scale(-1).normalize().equals(cylinder1.getNormal(pointE2)),
				"getNormal() does not return the correct normal vector at the bottom base");

		// TC3: Testing the normal vector at the top base of the cylinder
		assertTrue(
				vecOfRay.normalize().equals(cylinder1.getNormal(pointE3))
						|| vecOfRay.scale(-1).normalize().equals(cylinder1.getNormal(pointE3)),
				"getNormal() does not return the correct normal vector at the top base");

		// =============== Boundary Values Tests ==================

		// TC4: Testing the normal vector at the center point on the bottom base of the
		// cylinder

		assertTrue(
				vecOfRay.normalize().equals(cylinder1.getNormal(pointB1))
						|| vecOfRay.scale(-1).normalize().equals(cylinder1.getNormal(pointB1)),
				"getNormal() does not return the correct normal vector at the center point on the bottom base of the cylinder base");

		// TC5: Testing the normal vector at the center point on the top base of the
		// cylinder

		assertTrue(
				vecOfRay.normalize().equals(cylinder1.getNormal(pointB3))
						|| vecOfRay.scale(-1).normalize().equals(cylinder1.getNormal(pointB3)),
				"getNormal() does not return the correct normal vector at the center piont on the top base");

		// TC7: Examine the normal vector at a point that is a "seam" between the bases
		// and the cylinder shell
		// **** It was decided that in the case of a "seam", the normal vector relative
		// to the
		// base will be returned ****

		assertTrue(
				vecOfRay.normalize().equals(cylinder1.getNormal(pointB3))
						|| vecOfRay.scale(-1).normalize().equals(cylinder1.getNormal(pointB3)),
				"getNormal() does not return the correct normal vector at the ''seam'' piont on the Cylinder");

	}

}
