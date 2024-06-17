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
		assertEquals(p101, ray.getPoint(1.0));

		// TC02
		assertEquals(p_101, ray.getPoint(-1.0));
		// =============== Boundary Values Tests ==================
		// TC03
		assertEquals(ray.getHead(), ray.getPoint(0.0));

	}

}
