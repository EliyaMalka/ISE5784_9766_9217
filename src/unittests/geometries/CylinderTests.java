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
	    * Delta value for accuracy when comparing the numbers of type 'double' in
	    * assertEquals
	    */
	   private final double DELTA = 0.000001;


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
        assertTrue(vecRay.normalize() == myC.getNormal(pointBVA1) || vecRay.scale(-1).normalize() == myC.getNormal(pointBVA1), "getNormal() does not return the correct normal vector at the base");

        // TC11: Testing the normal vector at the top base of the cylinder
        assertTrue(vecRay == myC.getNormal(pointBVA2) || vecRay.scale(-1) == myC.getNormal(pointBVA2), "getNormal() does not return the correct normal vector at the top");
    }
}
