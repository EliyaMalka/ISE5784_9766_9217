package unittests.integration;
import geometries.*;
import primitives.*;
import renderer.Camera;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CameraTriangleIntersectoin {

	@Test
	void testIntersction() {

		// TC01
		// small triangle
		Triangle triangle01 = new Triangle(new Point(0, 1.0, -2.0), new Point(-1.0, -1.0, -2.0), new Point(1.0, -1.0, -2.0));
		Camera camera01 = Camera.getBuilder().setLocation(new Point(0, 0, 0))
				.setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0)).setVpSize(3, 3).setVpDistance(1).Build();
		assertEquals(1, CameraSphereIntersectoin.intersectionNumber(new Geometries(triangle01), camera01).size(),
				"number of intersection is false");

		// TC02
		// large triangle
		Triangle triangle02 = new Triangle(new Point(0, 20.0, -2.0), new Point(-1.0, -1.0, -2.0), new Point(1.0, -1.0, -2.0));
		Camera camera02 = camera01;
		assertEquals(2, CameraSphereIntersectoin.intersectionNumber(new Geometries(triangle02), camera02).size(),
				"number of intersection is false");
	}

}
