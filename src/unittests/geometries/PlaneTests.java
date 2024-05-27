/**
 * 
 */
package unittests.geometries;

import primitives.Point;
import primitives.Vector;
import geometries.Plane;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * 
 */
class PlaneTests {	   
	    @Test
	    public void testConstructorPlane() {
	    
	    Point p1 = new Point(1,0,0);
	    Point p2 = new Point(1,0,0);
	    Point p3 = new Point(0,0,1);
	   
	    Point sameRayP3_1 = new Point(0,0,3);
	    Point sameRayP3_2 = new Point(0,0,9);
	    	
     // =============== Boundary Values Tests ==================
        assertThrows(IllegalAccessException.class, () -> new Plane(p1, p2, p3) , "the constractor dos't throw illegal exception when sent to him same points");
        assertThrows(IllegalAccessException.class, () -> new Plane(p3, sameRayP3_1, sameRayP3_2) , "the constractor dos't throw illegal exception when sent to him points on same ray");

	    	
	    }
	   

	/**
	 * Test method for {@link geometries.Plane#getNormal(primitives.Point)}.
	 */
	@Test
	void testGetNormalPoint() {
		
		Point p1 = new Point(1,0,0);
		Point p2 = new Point(0,1,0);
		Point p3 = new Point(0,0,1);
		
		Vector normallVector = new Vector(1/Math.sqrt(3) , 1/Math.sqrt(3) , 1/Math.sqrt(3)); // after nirmool

	    Plane myPlane = new Plane(p1, p2,p3) ;

		// ============ Equivalence Partitions Tests ==============
		assertTrue( (myPlane.getNormal(p1) == normallVector) || (myPlane.getNormal(p1) == normallVector.scale(-1.0) ) ,"getNormal( primitives.Point ) dos't return true value") ;
	
	}

}
