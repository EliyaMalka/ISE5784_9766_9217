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

	/**
	 * Test method for {@link primitives.Point#add(primitives.Vector)}.
	 */
	@Test
	void testAdd() {
		Point p1 = new Point(3,7,8);
		Point result = new Point(5,11,13);		

		Vector v1 = new Vector(2,4,5);
		Vector negativVec = new Vector(-3,-7,-8);

// ============ Equivalence Partitions Tests ==============
assertTrue(p1.add(v1) == result , "add() dosn't return correct values");

// =============== Boundary Values Tests ==================
assertTrue(p1.ZERO.add(v1) == v1 , "add() dosn't return correct values with zero point");
assertTrue(p1.add(negativVec) == p1.ZERO , "add() dosn't return correct values with vector nagativ to point");

	}
	

	/**
	 * Test method for {@link primitives.Point#subtract(primitives.Point)}.
	 */
	@Test
	void testSubtract() {
		Point p1 = new Point(3,7,8);
		Point p2 = new Point(1,4,5);
		Point negativPoint = new Point(-1,-2,-3);

		Vector result1 = new Vector(2,3,3);
		Vector result2 = new Vector(4,9,11);
		Vector vecSamePoint = new Vector(3,7,8);

// ============ Equivalence Partitions Tests ==============
assertTrue(p1.subtract(p2) == result1 , "subtract() dosn't return correct values");

// =============== Boundary Values Tests ==================
assertTrue(p1.subtract(p1.ZERO) == vecSamePoint , "subtract() dosn't return correct values with zero point");
assertTrue(p1.subtract(negativPoint) == result2 , "subtract() dosn't return correct values with negativ point");
assertThrows(IllegalAccessException.class, ()->p1.subtract(vecSamePoint) , "subtract() dosn't throw exception when the result is zero vector");
	
	
	}

	/**
	 * Test method for {@link primitives.Point#distanceSquared(primitives.Point)}.
	 */
	@Test
	void testDistanceSquared() {
		Point p1 = new Point(1,2,3);
		Point p2 = new Point(5,1,0);
		Point nagativPoint3 = new Point(-2,-1,-3);
		
// ============ Equivalence Partitions Tests ==============
assertEquals(p1.distanceSquared(p2), 26.0,"distanceSquared() dosn't return correct values");

//=============== Boundary Values Tests ==================
assertEquals(p1.distanceSquared(p2.ZERO), 14.0,"distanceSquared() dosn't return correct values with zero point");
assertEquals(p1.distanceSquared(p1), 0.0 ,"distanceSquared() dosn't return correct values with same point");
assertEquals(p1.distanceSquared(nagativPoint3), 63.0,"distanceSquared() dosn't return correct values with nagativ point");

	}

	/**
	 * Test method for {@link primitives.Point#distance(primitives.Point)}.
	 */
	@Test
	void testDistance() {
		Point p1 = new Point(1,2,3);
		Point p2 = new Point(5,1,0);
		Point nagativPoint3 = new Point(-2,-1,-3);
		
// ============ Equivalence Partitions Tests ==============
assertEquals(p1.distance(p2), Math.sqrt(26),"distance() dosn't return correct values");

//=============== Boundary Values Tests ==================
assertEquals(p1.distance(p2.ZERO), Math.sqrt(14),"distance() dosn't return correct values with zero point");
assertEquals(p1.distance(p1), 0.0 ,"distance() dosn't return correct values with same point");
assertEquals(p1.distance(nagativPoint3), Math.sqrt(63),"distance() dosn't return correct values with nagativ point");
	}

}
