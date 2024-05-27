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
class CylinderTests {

	@Test
	void test() {
		@Test
		void testGetNormal() {
			
		 Point pointRay = new Point(0,0,0);
		 Vector vecRay = new Vector(0,0,1);
		 Ray myRay = new Ray(pointRay,vecRay);
		 Cylinder myC = new Cylinder(myRay, 3.0 , 10.0);
		 Point pointC = new Point(3,0,5);
		 
		 Point pointBVA1 = new Point(0,0,0);
		 Point pointBVA2 = new Point(0,0,10);

		 Vector result = new Vector(1, 0, 0);
		 
     // ============ Equivalence Partitions Tests ==============

	// =============== Boundary Values Tests ==================
		 
		 
		 
	}

}
