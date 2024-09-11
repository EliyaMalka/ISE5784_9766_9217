package unittests.primitives;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import primitives.Point;
import primitives.Vector;

/**
 * Unit tests for primitives.Point class
 */

class PointTests {

	/** The delta for comparing double numbers */
	private final double DELTA = 0.000001;

	/**
	 * Test method for {@link primitives.Point#add(primitives.Vector)}. Tests the
	 * addition of a vector to a point.
	 */
	@Test
	void testAdd() {
		Point p378 = new Point(3, 7, 8);
		Point result = new Point(5, 11, 13);
		Vector v245 = new Vector(2, 4, 5);
		Vector vm3m7m8 = new Vector(-3, -7, -8);

		// ============ Equivalence Partitions Tests ==============
		// Check if add() returns correct values
		assertEquals(p378.add(v245), result, "add() doesn't return correct values");

		// =============== Boundary Values Tests ==================
		// Check if add() returns correct values with zero point
		assertEquals(Point.ZERO.add(v245), v245, "add() doesn't return correct values with zero point");
		// Check if add() returns correct values with negative vector to point
		assertEquals(p378.add(vm3m7m8), Point.ZERO,
				"add() doesn't return correct values with negative vector to point");

	}

	/**
	 * Test method for {@link primitives.Point#subtract(primitives.Point)}. Tests
	 * the subtraction of one point from another.
	 * 
	 * @throws IllegalArgumentException if The zero vector is created
	 */
	@Test
	void testSubtract() {
		Point p378 = new Point(3, 7, 8);
		Point p145 = new Point(1, 4, 5);
		Point pm1m2m3 = new Point(-1, -2, -3);
		Vector result1 = new Vector(2, 3, 3);
		Vector result2 = new Vector(4, 9, 11);
		Vector vecSamePoint = new Vector(3, 7, 8);

		// ============ Equivalence Partitions Tests ==============
		// Check if subtract() returns correct values
		assertEquals(p378.subtract(p145), result1, "subtract() doesn't return correct values");

		// =============== Boundary Values Tests ==================
		// Check if subtract() returns correct values with zero point
		assertEquals(p378.subtract(Point.ZERO), vecSamePoint,
				"subtract() doesn't return correct values with zero point");
		// Check if subtract() returns correct values with negative point
		assertEquals(p378.subtract(pm1m2m3), result2, "subtract() doesn't return correct values with negative point");
		// Check if subtract() throws exception when the result is zero vector
		assertThrows(IllegalArgumentException.class, () -> p378.subtract(vecSamePoint),
				"subtract() doesn't throw exception when the result is zero vector");
	}

	/**
	 * Test method for {@link primitives.Point#distanceSquared(primitives.Point)}.
	 * Tests the squared distance calculation between two points.
	 */
	@Test
	void testDistanceSquared() {
		Point p123 = new Point(1, 2, 3);
		Point p510 = new Point(5, 1, 0);
		Point pm2m1m3 = new Point(-2, -1, -3);

		// ============ Equivalence Partitions Tests ==============
		// Check if distanceSquared() returns correct values
		assertEquals(p123.distanceSquared(p510), 26.0, DELTA, "distanceSquared() doesn't return correct values");

		// =============== Boundary Values Tests ==================
		// Check if distanceSquared() returns correct values with zero point
		assertEquals(p123.distanceSquared(Point.ZERO), 14.0, DELTA,
				"distanceSquared() doesn't return correct values with zero point");
		// Check if distanceSquared() returns correct values with same point
		assertEquals(p123.distanceSquared(p123), 0.0, DELTA,
				"distanceSquared() doesn't return correct values with same point");
		// Check if distanceSquared() returns correct values with negative point
		assertEquals(p123.distanceSquared(pm2m1m3), 54.0, DELTA,
				"distanceSquared() doesn't return correct values with negative point");
	}

	/**
	 * Test method for {@link primitives.Point#distance(primitives.Point)}. Tests
	 * the distance calculation between two points.
	 */
	@Test
	void testDistance() {
		Point p123 = new Point(1, 2, 3);
		Point p519 = new Point(5, 1, 0);
		Point pm2m1m3 = new Point(-2, -1, -3);

		// ============ Equivalence Partitions Tests ==============
		// Check if distance() returns correct values
		assertEquals(p123.distance(p519), Math.sqrt(26), DELTA, "distance() doesn't return correct values");

		// =============== Boundary Values Tests ==================
		// Check if distance() returns correct values with zero point
		assertEquals(p123.distance(Point.ZERO), Math.sqrt(14), DELTA,
				"distance() doesn't return correct values with zero point");
		// Check if distance() returns correct values with same point
		assertEquals(p123.distance(p123), 0.0, DELTA, "distance() doesn't return correct values with same point");
		// Check if distance() returns correct values with negative point
		assertEquals(p123.distance(pm2m1m3), Math.sqrt(54), DELTA,
				"distance() doesn't return correct values with negative point");
	}
}