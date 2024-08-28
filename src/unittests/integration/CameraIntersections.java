package unittests.integration;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import geometries.*;
import primitives.*;
import renderer.*;
import scene.*;

/**
 * Integration tests for Camera and geometries intersections. This class tests
 * the intersections between rays constructed by a camera and various geometric
 * shapes such as spheres, planes, and triangles.
 */
class CameraIntersections {

	/**
	 * Helper method to assert the number of intersections between the camera's rays
	 * and a given geometry.
	 * 
	 * @param expected the expected number of intersections
	 * @param geometry the geometry to test against
	 * @param camera   the camera generating the rays
	 */
	private void assertIntersections(int expected, Intersectable geometry, Camera camera) {
		int count = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				var intersections = geometry.findIntersections(camera.constructRay(3, 3, j, i));
				if (intersections != null) {
					count += intersections.size();
				}
			}
		}
		assertEquals(expected, count, "Number of intersections is incorrect");
	}

	/**
	 * Camera builder setup. This builder is used to configure the camera with the
	 * desired parameters, including direction vectors, view plane size, view plane
	 * distance, ray tracer, and image writer.
	 */
	private final Camera.Builder builder = Camera.getBuilder() //
			.setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0)) //
			.setVpSize(3, 3).setVpDistance(1) //
			.setRayTracer(new SimpleRayTracer(new Scene("test"))) //
			.setImageWriter(new ImageWriter("test", 600, 800));

	/**
	 * Camera instance positioned at the origin (0, 0, 0). This camera is
	 * constructed using the builder and is used to generate rays from the origin.
	 */
	private final Camera camera1 = builder.setLocation(new Point(0, 0, 0)).build();

	/**
	 * Camera instance positioned slightly in front of the origin at (0, 0, 0.5).
	 * This camera is constructed using the builder and is used to generate rays
	 * from a location slightly forward of the origin.
	 */
	private final Camera camera2 = builder.setLocation(new Point(0, 0, 0.5)).build();

	/**
	 * Tests the number of intersections between the rays constructed by the camera
	 * and sphere.
	 */
	@Test
	void testWithSphere() {
		// TC01: Test a ray that passes through the center of a sphere (2 intersections)
		assertIntersections(2, new Sphere(new Point(0, 0, -3), 1.0), camera1);

		// TC02: Test with the camera very close to a large sphere (18 intersections)
		assertIntersections(18, new Sphere(new Point(0, 0, -2.5), 2.5), camera2);

		// TC03: Test when the view plane is inside the sphere (10 intersections)
		assertIntersections(10, new Sphere(new Point(0, 0, -2), 2.0), camera2);

		// TC04: Test when the entire view plane is inside the sphere (9 intersections)
		assertIntersections(9, new Sphere(new Point(0, 0, -1), 4.0), camera2);

		// TC05: Test when the view plane is after the object (no intersections)
		assertIntersections(0, new Sphere(new Point(0, 0, 1.0), 0.5), camera1);
	}

	/**
	 * Tests the number of intersections between the rays constructed by the camera
	 * and plane.
	 */
	@Test
	void testWithPlane() {
		// TC11: Checks when the plane is parallel to the camera
		assertIntersections(9, new Plane(new Point(0, 0, -3.0), new Vector(0, 0, -1.0)), camera1);

		// TC12: Checks when the plane is in front of the camera (9 intersections)
		assertIntersections(9, new Plane(new Point(0, 0, -3.0), new Vector(0, 0, 1.0)), camera1);

		// TC13: Checks when the plane is in front of the camera at an angle (6
		// intersections)
		assertIntersections(6, new Plane(new Point(0, 0, -3.0), new Vector(1, 1, 1)), camera1);
	}

	/**
	 * Tests the number of intersections between the rays constructed by the camera
	 * and triangle.
	 */
	@Test
	void testWithTriangle() {
		// TC31: small triangle
		assertIntersections(1,
				new Triangle(new Point(0, 1.0, -2.0), new Point(-1.0, -1.0, -2.0), new Point(1.0, -1.0, -2.0)),
				camera1);

		// TC32: large triangle
		assertIntersections(2,
				new Triangle(new Point(0, 20.0, -2.0), new Point(-1.0, -1.0, -2.0), new Point(1.0, -1.0, -2.0)),
				camera1);
	}
}
