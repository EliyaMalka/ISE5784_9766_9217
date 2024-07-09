package unittests.integration;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import geometries.*;
import primitives.*;
import renderer.*;
import scene.*;

/**
 * Integration tests for Camera and geometries intersections.
 */
class CameraIntersections {

	public List<Point> intersectionsPoints(Geometries groupGeometries, Camera camera) {
		List<Point> result = new ArrayList<Point>();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				List<Point> intersections = groupGeometries.findIntersections(camera.constructRay(3, 3, j, i));
				if (intersections != null) {
					result.addAll(intersections);
				}
			}
		}
		return result.isEmpty() ? null : result;
	}

	/**
	 * Tests the number of intersections between the rays constructed by the camera
	 * and various geometries.
	 */
	@Test
	void testInterations() {

		Camera camera000 = Camera.getBuilder().setLocation(new Point(0, 0, 0))
				.setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0)).setVpSize(3, 3).setVpDistance(1)
				.setRayTracer(new SimpleRayTracer(new Scene("test"))).setImageWriter(new ImageWriter("test", 600, 800))
				.build();
		Camera camera0005 = Camera.getBuilder().setLocation(new Point(0, 0, 0.5))
				.setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0)).setVpSize(3, 3).setVpDistance(1)
				.setRayTracer(new SimpleRayTracer(new Scene("test"))).setImageWriter(new ImageWriter("test", 600, 800))
				.build();
		// TC01: Test a ray that passes through the center of a sphere (2 intersections)
		Point centerPoint01 = new Point(0, 0, -3);
		Sphere sphere01 = new Sphere(centerPoint01, 1.0);
		assertEquals(2, intersectionsPoints(new Geometries(sphere01), camera000).size(),
				"Number of intersections is incorrect");

		// TC02: Test with the camera very close to a large sphere (18 intersections)
		Point centerPoint02 = new Point(0, 0, -2.5);
		Sphere sphere02 = new Sphere(centerPoint02, 2.5);
		assertEquals(18, intersectionsPoints(new Geometries(sphere02), camera0005).size(),
				"Number of intersections is incorrect");

		// TC03: Test when the view plane is inside the sphere (10 intersections)
		Point centerPoint03 = new Point(0, 0, -2);
		Sphere sphere03 = new Sphere(centerPoint03, 2.0);
		assertEquals(10, intersectionsPoints(new Geometries(sphere03), camera0005).size(),
				"Number of intersections is incorrect");

		// TC04: Test when the entire view plane is inside the sphere (9 intersections)
		Point centerPoint04 = new Point(0, 0, -1);
		Sphere sphere04 = new Sphere(centerPoint04, 4.0);
		assertEquals(9, intersectionsPoints(new Geometries(sphere04), camera0005).size(),
				"Number of intersections is incorrect");

		// TC05: Test when the view plane is after the object (no intersections)
		Point centerPoint05 = new Point(0, 0, 1.0);
		Sphere sphere05 = new Sphere(centerPoint05, 0.5);
		assertNull(intersectionsPoints(new Geometries(sphere05), camera000),
				"Number of intersections should be 0 (and return null)");

		// plane tests
		// TC11: Checks when the plane is parallel to the camera
		Plane plane01 = new Plane(new Point(0, 0, -3.0), new Vector(0, 0, -1.0));
		assertEquals(9, intersectionsPoints(new Geometries(plane01), camera000).size(),
				"Number of intersections is incorrect");

		// TC12: Checks when the plane is in front of the camera (9 intersections)
		Plane plane02 = new Plane(new Point(0, 0, -3.0), new Vector(0, 0, 1.0));
		assertEquals(9, intersectionsPoints(new Geometries(plane02), camera000).size(),
				"Number of intersections is incorrect");

		// TC13: Checks when the plane is in front of the camera at an angle (6
		// intersections)
		Plane plane03 = new Plane(new Point(0, 0, -3.0), new Vector(1, 1, 1));
		assertEquals(6, intersectionsPoints(new Geometries(plane03), camera000).size(),
				"Number of intersections is incorrect");

		// triangle tests
		// TC31: small triangle
		Triangle triangle01 = new Triangle(new Point(0, 1.0, -2.0), new Point(-1.0, -1.0, -2.0),
				new Point(1.0, -1.0, -2.0));
		assertEquals(1, intersectionsPoints(new Geometries(triangle01), camera000).size(),
				"Number of intersections is incorrect for small triangle");

		// TC32: large triangle
		Triangle triangle02 = new Triangle(new Point(0, 20.0, -2.0), new Point(-1.0, -1.0, -2.0),
				new Point(1.0, -1.0, -2.0));
		assertEquals(2, intersectionsPoints(new Geometries(triangle02), camera000).size(),
				"Number of intersections is incorrect for large triangle");
	}
}
