package unittests.integration;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import geometries.Geometries;
import geometries.Plane;
import primitives.Point;
import primitives.Vector;
import renderer.Camera;

/**
 * Integration tests for Camera and Plane intersections.
 */
class CameraPlaneIntersectoin {

    /**
     * Test cases for intersection of camera rays and planes.
     */
    @Test
    void testIntersection() {

        // TC01: Checks when the plane is parallel to the camera
        Plane plane01 = new Plane(new Point(0, 0, -3.0), new Vector(0, 0, -1.0));
        Camera camera01 = Camera.getBuilder().setLocation(new Point(0, 0, 0))
                .setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0))
                .setVpSize(3, 3).setVpDistance(1).build();
        assertEquals(9, CameraSphereIntersectoin.intersectionNumber(new Geometries(plane01), camera01),
                "Number of intersections is incorrect");

        // TC02: Checks when the plane is in front of the camera (9 intersections)
        Plane plane02 = new Plane(new Point(0, 0, -3.0), new Vector(0, 0, 1.0));
        Camera camera02 = Camera.getBuilder().setLocation(new Point(0, 0, 0))
                .setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0))
                .setVpSize(3, 3).setVpDistance(1).build();
        assertEquals(9, CameraSphereIntersectoin.intersectionNumber(new Geometries(plane02), camera02),
                "Number of intersections is incorrect");

        // TC03: Checks when the plane is in front of the camera at an angle (6 intersections)
        Plane plane03 = new Plane(new Point(0, 0, -3.0), new Vector(1, 1, 1));
        Camera camera03 = Camera.getBuilder().setLocation(new Point(0, 0, 0))
                .setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0))
                .setVpSize(3, 3).setVpDistance(1).build();
        assertEquals(6, CameraSphereIntersectoin.intersectionNumber(new Geometries(plane03), camera03),
                "Number of intersections is incorrect");
    }
}
