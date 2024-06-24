package unittests.integration;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import geometries.Geometries;
import geometries.Plane;
import primitives.Point;
import primitives.Vector;
import renderer.Camera;

class CameraPlaneIntersectoin {

	@Test
	@Disabled
	void testIntersction() {

		// TC01
		// Checks when the plane is parallel to the camera
		Plane plane01 = new Plane(new Point(0, 0, -3.0), new Vector(0, 0, -1.0));
		Camera camera01 = Camera.getBuilder().setLocation(new Point(0, 0, 0))
				.setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0)).setVpSize(3, 3).setVpDistance(1).build();
		assertEquals(9, CameraSphereIntersectoin.intersectionNumber(new Geometries(plane01), camera01).size(),
				"number of intersection is false");

		// TC02
		// Checks when the plane is in front of the camera (9 intersection)
		Plane plane02 = new Plane(new Point(0, 0, -3.0), new Vector(0, 0, 1.0));
		Camera camera02 = Camera.getBuilder().setLocation(new Point(0, 0, 0))
				.setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0)).setVpSize(3, 3).setVpDistance(1).build();
		assertEquals(9, CameraSphereIntersectoin.intersectionNumber(new Geometries(plane02), camera02).size(),
				"number of intersection is false");

		// TC03
		// Checks when the plane is in front of the camera (6 intersection)
		Plane plane03 = new Plane(new Point(0, 0, -3.0), new Vector(1, 1, 1));
		Camera camera03 = Camera.getBuilder().setLocation(new Point(0, 0, 0))
				.setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0)).setVpSize(3, 3).setVpDistance(1).build();
		assertEquals(6, CameraSphereIntersectoin.intersectionNumber(new Geometries(plane03), camera03).size(),
				"number of intersection is false");
	}

}
