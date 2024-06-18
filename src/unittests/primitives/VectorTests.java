/**
 * 
 */
package unittests.primitives;

import static org.junit.jupiter.api.Assertions.*;

import primitives.Double3;
import primitives.Point;
import primitives.Vector;

import org.junit.jupiter.api.Test;

/**
 * Testing Vectors. This class tests the {@link primitives.Vector} class. Tests
 * include various methods such as lengthSquared, constructor, length, add,
 * scale, dotProduct, crossProduct, and normalize.
 */
class VectorTests {

	/**
	 * Delta value for accuracy when comparing the numbers of type 'double' in
	 * assertEquals.
	 */
	private final double DELTA = 0.000001;

	/**
	 * Test method for {@link primitives.Vector#lengthSquared()}. Tests the
	 * lengthSquared function of the Vector class.
	 */
	@Test
	void testLengthSquared() {
		// ============ Equivalence Partitions Tests ==============

		// check if lengthSquared() return correct value
		Vector v = new Vector(2, 4, -4);
		assertEquals(36, v.lengthSquared(), DELTA, "lengthSquared() does not return the correct value");
	}

	/**
	 * Test method for {@link primitives.Vector#Vector(double,double,double)}. Tests
	 * the constructor of the Vector class.
	 */
	@Test
	void testVector() {
		// ============ Equivalence Partitions Tests ==============

		// Check if constructor sets the correct values
		Vector v = new Vector(1, 2, 3);
		Double3 xyzToCheck = new Double3(1, 2, 3);
		assertEquals(v , new Vector(xyzToCheck), "Constructor does not set correct values");

		// Check if constructor does not throw exception for valid values
		assertDoesNotThrow(() -> new Vector(0, 0, 1), "Constructor throws exception for valid values");
		assertDoesNotThrow(() -> new Vector(0, 1, 0), "Constructor throws exception for valid values");
		assertDoesNotThrow(() -> new Vector(1, 0, 0), "Constructor throws exception for valid values");

		// =============== Boundary Values Tests ==================

		// Check if constructor throws exception for zero vector
		Double3 xyz = new Double3(0, 0, 0);
		assertThrows(IllegalArgumentException.class, () -> new Vector(0, 0, 0),
				"Constructor does not throw IllegalArgumentException for zero vector");
		assertThrows(IllegalArgumentException.class, () -> new Vector(xyz),
				"Constructor does not throw IllegalArgumentException for zero vector");
	}

	/**
	 * Test method for {@link primitives.Vector#length()}. Tests the length function
	 * of the Vector class.
	 */
	@Test
	void testLength() {
		// ============ Equivalence Partitions Tests ==============

		// Check if length() returns correct value
		Vector v = new Vector(2, 4, -4);
		assertEquals(6, v.length(), DELTA, "length() does not return the correct value");
	}

	/**
	 * Test method for {@link primitives.Vector#add(primitives.Vector)}. Tests the
	 * add function of the Vector class.
	 */
	@Test
	void testAddVector() {
		Vector v1 = new Vector(1, 2, 3);
		Vector v2 = new Vector(2, 3, 4);
		Vector result = new Vector(3, 5, 7);
		Vector negativeV1 = new Vector(-1, -2, -3);

		// ============ Equivalence Partitions Tests ==============

		// Check if add() returns correct values
		assertEquals(result, v1.add(v2), "add() does not return the correct values");
		assertDoesNotThrow(() -> v1.add(v2), "add() throws exception for valid values");

		// =============== Boundary Values Tests ==================

		// Check if add() throws exception for adding negative vectors
		assertThrows(IllegalArgumentException.class, () -> v1.add(negativeV1),
				"add() does not throw exception for adding negative vectors");
	}

	/**
	 * Test method for {@link primitives.Vector#scale(double)}. Tests the scale
	 * function of the Vector class.
	 */
	@Test
	void testScale() {
		Vector v = new Vector(1, 2, 3);
		double x = 2.0;
		double y = -2.0;
		Vector resultX = new Vector(2, 4, 6);
		Vector resultY = new Vector(-2, -4, -6);

		// ============ Equivalence Partitions Tests ==============

		// Check if scale() returns correct values
		assertEquals(resultX ,v.scale(x), "scale() does not return the correct values");
		assertEquals(resultY ,v.scale(y), "scale() does not return the correct values");
		assertDoesNotThrow(() -> v.scale(x));

		// =============== Boundary Values Tests ==================

		// Check if scale() throws exception for scalar 0
		assertThrows(IllegalArgumentException.class, () -> v.scale(0), "scale() does not throw exception for scalar 0");
	}

	/**
	 * Test method for {@link primitives.Vector#dotProduct(primitives.Vector)}.
	 * Tests the dotProduct function of the Vector class.
	 */
	@Test
	void testDotProduct() {
		Vector v1 = new Vector(1, 2, 3);
		Vector v2 = new Vector(2, 3, 4);
		Vector unitVector = new Vector(0, 0, 1);
		double result = 20.0;
		Vector vertical = new Vector(-1, -1, 1);

		// ============ Equivalence Partitions Tests ==============

		// Check if dotProduct() returns correct values
		assertEquals(result, v1.dotProduct(v2), DELTA, "dotProduct() does not return the correct values");

		// =============== Boundary Values Tests ==================

		// Check if dotProduct() returns correct values for vertical vectors
		assertEquals(0, v1.dotProduct(vertical), DELTA,
				"dotProduct() does not return correct values for vertical vectors");
		// Check if dotProduct() returns correct values for unit vector
		assertEquals(3, v1.dotProduct(unitVector), DELTA,
				"dotProduct() does not return correct values for unit vector");
	}

	/**
	 * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
	 * Tests the crossProduct function of the Vector class.
	 */
	@Test
	void testCrossProduct() {
		Vector v1 = new Vector(3, 2, 1);
		Vector v2 = new Vector(6, 5, 4);
		Vector twiceV1 = new Vector(6, 4, 2);
		Vector negativeTwiceV1 = new Vector(-6, -4, -2);
		Vector negativeV1 = new Vector(-3, -2, -1);

		// ============ Equivalence Partitions Tests ==============

		// Check if crossProduct() returns correct orthogonal vectors
		assertEquals(v1.dotProduct(v1.crossProduct(v2)) , 0, DELTA, "crossProduct() does not return correct values");
		assertEquals(v2.dotProduct(v2.crossProduct(v1)) , 0, DELTA, "crossProduct() does not return correct values");

		// =============== Boundary Values Tests ==================

		// Check if crossProduct() throws exception for parallel vectors
		assertThrows(IllegalArgumentException.class, () -> v1.crossProduct(twiceV1),
				"crossProduct() does not throw exception for same direction vectors");
		assertThrows(IllegalArgumentException.class, () -> v1.crossProduct(negativeTwiceV1),
				"crossProduct() does not throw exception for opposite direction vectors");
		assertThrows(IllegalArgumentException.class, () -> v1.crossProduct(negativeV1),
				"crossProduct() does not throw exception for same negative vector");
		assertThrows(IllegalArgumentException.class, () -> v1.crossProduct(v1),
				"crossProduct() does not throw exception for same vector");
	}

	/**
	 * Test method for {@link primitives.Vector#normalize()}. Tests the normalize
	 * function of the Vector class.
	 */
	@Test
	void testNormalize() {
		Vector v = new Vector(1, 2, 3);
		Vector unitVector1 = new Vector(1, 0, 0);
		Vector unitVector2 = new Vector(0, 1, 0);
		Vector unitVector3 = new Vector(0, 0, 1);

		// ============ Equivalence Partitions Tests ==============

		// Check if normalize() returns a unit vector
		assertEquals(v.normalize().length() , 1, DELTA, "normalize() does not return a unit vector");

		// =============== Boundary Values Tests ==================

		// Check if normalize() returns correct values for unit vectors
		assertEquals(unitVector1.normalize().length() , 1, DELTA, "normalize() does not return correct values for unit vector");
		assertEquals(unitVector2.normalize().length() , 1, DELTA, "normalize() does not return correct values for unit vector");
		assertEquals(unitVector3.normalize().length() , 1, DELTA, "normalize() does not return correct values for unit vector");
	}
	
	/**
	 * Test method for {@link primitives.Point#subtract(primitives.Point)}. Tests
	 * the subtraction of one point from another.
	 */
	@Test
	void testSubtract() {
		Vector p1 = new Vector(3, 7, 8);
		Vector p2 = new Vector(1, 4, 5);
		Vector negativPoint = new Vector(-1, -2, -3);
		Vector result1 = new Vector(2, 3, 3);
		Vector result2 = new Vector(4, 9, 11);
		Vector vecSamePoint = new Vector(3, 7, 8);

		// ============ Equivalence Partitions Tests ==============
		// Check if subtract() returns correct values
		assertEquals(p1.subtract(p2) ,result1, "subtract() doesn't return correct values");

		// =============== Boundary Values Tests ==================
		// Check if subtract() returns correct values with zero point
		assertEquals(p1.subtract(Point.ZERO) ,vecSamePoint,
				"subtract() doesn't return correct values with zero point");
		// Check if subtract() returns correct values with negative point
		assertEquals(p1.subtract(negativPoint) ,result2,
				"subtract() doesn't return correct values with negative point");
		// Check if subtract() throws exception when the result is zero vector
		assertThrows(IllegalArgumentException.class, () -> p1.subtract(vecSamePoint),
				"subtract() doesn't throw exception when the result is zero vector");
	}

}
