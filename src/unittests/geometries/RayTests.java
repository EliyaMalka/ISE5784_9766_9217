package unittests.geometries;

import static org.junit.jupiter.api.Assertions.*;

import primitives.*;

import org.junit.jupiter.api.Test;

public class RayTests {

	@Test
	void getPointTest() {

		final Point p001 = new Point(0, 0, 1);
		final Point p101 = new Point(1, 0, 1);
		final Point p_101 = new Point(-1, 0, 1);

		final Vector v100 = new Vector(1, 0, 0);

		Ray ray = new Ray(p001, v100);

		// ============ Equivalence Partitions Tests ==============
		// TC01
		//when t is positive
		assertEquals(p101, ray.getPoint(1.0),"ERROR TC01: when t is positive");

		// TC02
		//when t is negative
		assertEquals(p_101, ray.getPoint(-1.0),"ERROR TC02: when t is negative");
		// =============== Boundary Values Tests ==================
		// TC03
		// when t equal to zero
		assertEquals(ray.getHead(), ray.getPoint(0.0),"ERROR TC03: when t equal to zero");

	}

}
