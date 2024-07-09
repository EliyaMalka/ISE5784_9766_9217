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
		Vector vecOfRy = new Vector(0, 0, 1);
		Ray Ry = new Ray(pointOfRy, vecOfRy);
		Cylinder Cyl = new Cylinder(Ry, 3.0, 10.0);

		Point pointEP1 = new Point(3, 0, 5);
		Point pointEP2 = new Point(1, 0, 0);
		Point pointEP3 = new Point(0, 1, 10);

		Point pointBVA1 = new Point(0, 0, 0);
		Point pointBVA3 = new Point(3, 3, 0);

		Vector result = new Vector(1, 0, 0);

		// ============ Equivalence Partitions Tests ==============
		// TC1: Testing the normal vector on the side surface of the cylinder
		assertEquals(result, Cyl.getNormal(pointEP1), "getNormal() does not return the correct normal vector");

		// TC2: Testing the normal vector at the bottom base of the cylinder
		assertTrue(
				vecOfRy.normalize().equals(Cyl.getNormal(pointEP2))
						|| vecOfRy.scale(-1).normalize().equals(Cyl.getNormal(pointEP2)),
				"getNormal() does not return the correct normal vector at the bottom base");

		// TC3: Testing the normal vector at the top base of the cylinder
		assertTrue(
				vecOfRy.normalize().equals(Cyl.getNormal(pointEP3))
						|| vecOfRy.scale(-1).normalize().equals(Cyl.getNormal(pointEP3)),
				"getNormal() does not return the correct normal vector at the top base");

		// =============== Boundary Values Tests ==================

		// TC4: Testing the normal vector at the center point on the bottom base of the
		// cylinder

		assertTrue(
				vecOfRy.normalize().equals(Cyl.getNormal(pointBVA1))
						|| vecOfRy.scale(-1).normalize().equals(Cyl.getNormal(pointBVA1)),
				"getNormal() does not return the correct normal vector at the center point on the bottom base of the cylinder base");

		// TC5: Testing the normal vector at the center point on the top base of the
		// cylinder

		assertTrue(
				vecOfRy.normalize().equals(Cyl.getNormal(pointBVA3))
						|| vecOfRy.scale(-1).normalize().equals(Cyl.getNormal(pointBVA3)),
				"getNormal() does not return the correct normal vector at the center piont on the top base");

		// TC7: Examine the normal vector at a point that is a "seam" between the bases
		// and the cylinder shell
		// **** It was decided that in the case of a "seam", the normal vector relative
		// to the
		// base will be returned ****

		assertTrue(
				vecOfRy.normalize().equals(Cyl.getNormal(pointBVA3))
						|| vecOfRy.scale(-1).normalize().equals(Cyl.getNormal(pointBVA3)),
				"getNormal() does not return the correct normal vector at the ''seam'' piont on the Cylinder");

	}

}
