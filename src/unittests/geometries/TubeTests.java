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
 * 
 */
class TubeTests {

	/**
	 * Test method for {@link geometries.Tube#getNormal(primitives.Point)}.
	 */
	@Test
	void testGetNormal() {
		
	 Point pointRay = new Point(0,0,0);
	 Vector vecRay = new Vector(0,0,1);
	 Ray myRay = new Ray(pointRay,vecRay);
	 Tube myTube = new Tube(myRay, 3);
	 Point pointTube = new Point(3,0,5);
	 Point pointBVA = new Point(3,0,0);

	 Vector result = new Vector(1, 0, 0);
	 
     // ============ Equivalence Partitions Tests ==============
assertTrue(myTube.getNormal(pointTube) == result , " getNormal() does not return correct values");

// =============== Boundary Values Tests ==================
assertTrue(myTube.getNormal(pointBVA) == result , "  getNormal() does not return correct values with a point that makes 90 degrees with the point on the ray");

		
	}

}
