package unittests.integration;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.*;
import geometries.*;
import primitives.*;
import renderer.Camera;

import java.util.ArrayList;
import java.util.List;

/**
 * Integration tests for Camera and Sphere intersections.
 */
class CameraSphereIntersectoin {

    /**
     * Finds the intersection points between a group of geometries and the rays constructed by a camera.
     *
     * @param groupOfGeometrics the group of geometries to test for intersections
     * @param camera            the camera used to construct rays
     * @return a list of intersection points, or null if no intersections are found
     */
    public static int intersectionNumber(Geometries groupOfGeometrics, Camera camera) {
		int counter = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				var result = groupOfGeometrics.findIntersections(camera.constructRay(3, 3, j, i));
				if (result != null)
					counter += result.size();
			}
		}
		return counter;
    }

    /**
     * Test cases for intersection of camera rays and spheres.
     */
    @Test
    void testIntersection() {

        // TC01: Test a ray that passes through the center (2 intersections)
        Point centerPoint01 = new Point(0, 0, -3);
        Sphere sphere01 = new Sphere(centerPoint01, 1.0);
        Camera camera01 = Camera.getBuilder().setLocation(new Point(0, 0, 0))
                .setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0))
                .setVpSize(3, 3).setVpDistance(1).build();

        assertEquals(2, intersectionNumber(new Geometries(sphere01), camera01),
                "Number of intersections is incorrect");

        // TC02: Test with the camera very close to a large sphere
        Point centerPoint02 = new Point(0, 0, -2.5);
        Sphere sphere02 = new Sphere(centerPoint02, 2.5);
        Camera camera02 = Camera.getBuilder().setLocation(new Point(0, 0, 0.5))
                .setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0))
                .setVpSize(3, 3).setVpDistance(1).build();

        assertEquals(18, intersectionNumber(new Geometries(sphere02), camera02),
                "Number of intersections is incorrect");

        // TC03: Test when the view plane is inside the sphere
        Point centerPoint03 = new Point(0, 0, -2);
        Sphere sphere03 = new Sphere(centerPoint03, 2.0);
        Camera camera03 = Camera.getBuilder().setLocation(new Point(0, 0, 0.5))
                .setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0))
                .setVpSize(3, 3).setVpDistance(1).build();

        assertEquals(10, intersectionNumber(new Geometries(sphere03), camera03),
                "Number of intersections is incorrect");

        // TC04: Test when the entire view plane is inside the sphere
        Point centerPoint04 = new Point(0, 0, -1);
        Sphere sphere04 = new Sphere(centerPoint04, 4.0);
        Camera camera04 = Camera.getBuilder().setLocation(new Point(0, 0, 0.5))
                .setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0))
                .setVpSize(3, 3).setVpDistance(1).build();

        assertEquals(9, intersectionNumber(new Geometries(sphere04), camera04),
                "Number of intersections is incorrect");

        // TC05: Test when the view plane is after the object
        Point centerPoint05 = new Point(0, 0, 1.0);
        Sphere sphere05 = new Sphere(centerPoint05, 0.5);
        Camera camera05 = Camera.getBuilder().setLocation(new Point(0, 0, 0))
                .setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0))
                .setVpSize(3, 3).setVpDistance(1).build();

        assertNull(intersectionNumber(new Geometries(sphere05), camera05),
                "Number of intersections should be 0");
    }
}
