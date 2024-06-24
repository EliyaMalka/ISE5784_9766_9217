package unittests.integration;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.*;

import geometries.*;
import primitives.*;
import renderer.Camera;

import java.util.ArrayList;
import java.util.List;

class CameraSphereIntersectoin {

	public static List<Point> intersectionNumber(Geometries groupOfGeometrics, Camera camera) {

		List<Point> result = new ArrayList<Point>();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				List<Point> intersections = groupOfGeometrics.findIntersections(camera.constructRay(3, 3, j, i));
				if (intersections != null) {
					result.addAll(intersections);
				}
			}
		}
		return result.isEmpty() ? null : result;
	}

	@Test
	@Disabled
	void testIntersction() {

		// TC01
		// test A ray that passes through the center (2 intersection)
		Point centerPoint01 = new Point(0, 0, -3);
		Sphere sphere01 = new Sphere(centerPoint01, 1.0);
		Camera camera01 = Camera.getBuilder().setLocation(new Point(0, 0, 0))
				.setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0)).setVpSize(3, 3).setVpDistance(1).build();

		assertEquals(2, intersectionNumber(new Geometries(sphere01), camera01).size(),
				"number of intersection is false");

		// TC02
		// test that the camera is very close to the large Sphere in relation to it
		Point centerPoint02 = new Point(0, 0, -2.5);
		Sphere sphere02 = new Sphere(centerPoint02, 2.5);
		Camera camera02 = Camera.getBuilder().setLocation(new Point(0, 0, 0.5))
				.setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0)).setVpSize(3, 3).setVpDistance(1).build();

		assertEquals(18, intersectionNumber(new Geometries(sphere02), camera02).size(),
				"number of intersection is false");

		// TC03
		// test When the vp is inside the sphere
		Point centerPoint03 = new Point(0, 0, -2);
		Sphere sphere03 = new Sphere(centerPoint03, 2.0);
		Camera camera03 = Camera.getBuilder().setLocation(new Point(0, 0, 0.5))
				.setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0)).setVpSize(3, 3).setVpDistance(1).build();

		assertEquals(10, intersectionNumber(new Geometries(sphere03), camera03).size(),
				"number of intersection is false");

		// TC04
		// test When all vp is inside the sphere
		Point centerPoint04 = new Point(0, 0, -1);
		Sphere sphere04 = new Sphere(centerPoint04, 4.0);
		Camera camera04 = Camera.getBuilder().setLocation(new Point(0, 0, 0.5))
				.setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0)).setVpSize(3, 3).setVpDistance(1).build();

		assertEquals(9, intersectionNumber(new Geometries(sphere04), camera04).size(),
				"number of intersection is false");

		// TC05
		// test that vp after object
		Point centerPoint05 = new Point(0, 0, 1.0);
		Sphere sphere05 = new Sphere(centerPoint05, 0.5);
		Camera camera05 = Camera.getBuilder().setLocation(new Point(0, 0, 0))
				.setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0)).setVpSize(3, 3).setVpDistance(1).build();

		assertNull(intersectionNumber(new Geometries(sphere05), camera05).size(), "number of intersection need be 0");

	}

}
