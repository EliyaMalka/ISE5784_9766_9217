/**
 * 
 */
package unittests.geometries;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import geometries.Triangle;
import primitives.Point;
import primitives.Vector;

/**
 * 
 */
class TriangleTests {

	@Test
	void getNormalTest() {
		Point p1 = new Point(2,0,0);
		Point p2 = new Point(0,6,0);
		Point p3 = new Point(0,0,0);
		Vector result = new Vector(0,0,1);
		Triangle A = new Triangle(p1,p2,p3);
		
	      // ============ Equivalence Partitions Tests ==============
		assertTrue( ( (A.getNormal(p3) == result) || (A.getNormal(p3) == result.scale(-1.0)) ) ,"getNormal() dos't return true value");
	}

}
