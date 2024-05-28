/**
 * 
 */
package unittests.primitives;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import primitives.Double3;
import primitives.Point;
import primitives.Vector;

/**
* Unit tests for primitives.Point class
*/

class PointTests {
	
	   private final double DELTA = 0.000001;


	/**
     * Test method for {@link primitives.Point#add(primitives.Vector)}.
	 * Tests the addition of a vector to a point.
	 */
	@Test
	void testAdd() {
	      Point p1 = new Point(3, 7, 8);
	      Point result = new Point(5, 11, 13);		
	      Vector v1 = new Vector(2, 4, 5);
	      Vector negativVec = new Vector(-3, -7, -8);

	      // ============ Equivalence Partitions Tests ==============
	      // Check if add() returns correct values
	      assertTrue(p1.add(v1).equals(result), "add() doesn't return correct values");

	      // =============== Boundary Values Tests ==================
	      // Check if add() returns correct values with zero point
	      assertTrue(Point.ZERO.add(v1).equals(v1), "add() doesn't return correct values with zero point");
	      // Check if add() returns correct values with negative vector to point
	      assertTrue(p1.add(negativVec).equals(Point.ZERO), "add() doesn't return correct values with negative vector to point");

	   }
	
	   /**
	    * Test method for {@link primitives.Point#subtract(primitives.Point)}.
	    * Tests the subtraction of one point from another.
	    */
	@Test
	void testSubtract() {
	      Point p1 = new Point(3, 7, 8);
	      Point p2 = new Point(1, 4, 5);
	      Point negativPoint = new Point(-1, -2, -3);
	      Vector result1 = new Vector(2, 3, 3);
	      Vector result2 = new Vector(4, 9, 11);
	      Vector vecSamePoint = new Vector(3, 7, 8);

	      // ============ Equivalence Partitions Tests ==============
	      // Check if subtract() returns correct values
	      assertTrue(p1.subtract(p2).equals(result1), "subtract() doesn't return correct values");

	      // =============== Boundary Values Tests ==================
	      // Check if subtract() returns correct values with zero point
	      assertTrue(p1.subtract(Point.ZERO).equals(vecSamePoint), "subtract() doesn't return correct values with zero point");
	      // Check if subtract() returns correct values with negative point
	      assertTrue(p1.subtract(negativPoint).equals(result2), "subtract() doesn't return correct values with negative point");
	      // Check if subtract() throws exception when the result is zero vector
	      assertThrows(IllegalArgumentException.class, () -> p1.subtract(vecSamePoint), "subtract() doesn't throw exception when the result is zero vector");
	   }

	/**
	    * Test method for {@link primitives.Point#distanceSquared(primitives.Point)}.
	    * Tests the squared distance calculation between two points.
	    */
	   @Test
	   void testDistanceSquared() {
	      Point p1 = new Point(1, 2, 3);
	      Point p2 = new Point(5, 1, 0);
	      Point nagativPoint3 = new Point(-2, -1, -3);

	      // ============ Equivalence Partitions Tests ==============
	      // Check if distanceSquared() returns correct values
	      assertEquals(p1.distanceSquared(p2), 26.0, DELTA, "distanceSquared() doesn't return correct values");

	      // =============== Boundary Values Tests ==================
	      // Check if distanceSquared() returns correct values with zero point
	      assertEquals(p1.distanceSquared(Point.ZERO), 14.0, DELTA, "distanceSquared() doesn't return correct values with zero point");
	      // Check if distanceSquared() returns correct values with same point
	      assertEquals(p1.distanceSquared(p1), 0.0, DELTA, "distanceSquared() doesn't return correct values with same point");
	      // Check if distanceSquared() returns correct values with negative point
	      assertEquals(p1.distanceSquared(nagativPoint3), 54.0, DELTA, "distanceSquared() doesn't return correct values with negative point");
	   }

	   /**
	    * Test method for {@link primitives.Point#distance(primitives.Point)}.
	    * Tests the distance calculation between two points.
	    */
	   @Test
	   void testDistance() {
	      Point p1 = new Point(1, 2, 3);
	      Point p2 = new Point(5, 1, 0);
	      Point nagativPoint3 = new Point(-2, -1, -3);

	      // ============ Equivalence Partitions Tests ==============
	      // Check if distance() returns correct values
	      assertEquals(p1.distance(p2), Math.sqrt(26), DELTA, "distance() doesn't return correct values");

	      // =============== Boundary Values Tests ==================
	      // Check if distance() returns correct values with zero point
	      assertEquals(p1.distance(Point.ZERO), Math.sqrt(14), DELTA, "distance() doesn't return correct values with zero point");
	      // Check if distance() returns correct values with same point
	      assertEquals(p1.distance(p1), 0.0, DELTA, "distance() doesn't return correct values with same point");
	      // Check if distance() returns correct values with negative point
	      assertEquals(p1.distance(nagativPoint3), Math.sqrt(54), DELTA, "distance() doesn't return correct values with negative point");
	   }
	}