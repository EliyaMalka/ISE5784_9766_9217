/**
 * 
 */
package unittests.geometries;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import geometries.Cylinder;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * ***********bonus**************
 */


/**
 * Unit tests for geometries.Cylinder class.
 */
class CylinderTests {

    /**
     * Test method for {@link geometries.Cylinder#getNormal(primitives.Point)}.
     * This test checks the functionality of the getNormal method for the Cylinder class.
     */
    @Test
    void testGetNormal() {
        Point pointRay = new Point(0, 0, 0);
        Vector vecRay = new Vector(0, 0, 1);
        Ray myRay = new Ray(pointRay, vecRay);
        Cylinder myC = new Cylinder(myRay, 3.0, 10.0);
        Point pointC = new Point(3, 0, 5);

        Point pointBVA1 = new Point(0, 0, 0);
        Point pointBVA2 = new Point(0, 0, 10);

        Vector result = new Vector(1, 0, 0);

        // ============ Equivalence Partitions Tests ==============
        // TC01: Testing the normal vector on the side surface of the cylinder
        assertEquals(result, myC.getNormal(pointC), "getNormal() does not return the correct normal vector");

        // =============== Boundary Values Tests ==================
        // TC10: Testing the normal vector at the bottom base of the cylinder
        assertEquals(new Vector(0, 0, 1), myC.getNormal(pointBVA1), "getNormal() does not return the correct normal vector at the base");

        // TC11: Testing the normal vector at the top base of the cylinder
        assertEquals(new Vector(0, 0, 1), myC.getNormal(pointBVA2), "getNormal() does not return the correct normal vector at the top");
    }
}
