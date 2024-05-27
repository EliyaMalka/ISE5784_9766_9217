/**
 * 
 */
package unittests.geometries;

import static org.junit.jupiter.api.Assertions.*;
import primitives.Point;
import primitives.Vector;
import geometries.RadialGeometry;
import geometries.Sphere;

import org.junit.jupiter.api.Test;

/**
 * 
 */
class SphereTests {

	/**
	 * Test method for {@link geometries.Sphere#getNormal(primitives.Point)}.
	 */
	@Test
	void testGetNormal() {
	     // ============ Equivalence Partitions Tests ==============
		Point p = new Point(0,0,0);
        Sphere mySphere = new Sphere(p, 3.0);
		Point pointOnSphere = new Point(1,2,2);
		Vector normalVector = new Vector(1,2,2);
        assertTrue(mySphere.getNormal(pointOnSphere) == normalVector," getNormal() dos't return corecct normal vector");
	}

}
