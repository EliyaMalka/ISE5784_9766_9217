package unittests.integration;

import geometries.*;
import primitives.*;
import renderer.Camera;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

/**
 * CameraTriangleIntersection class of tests for intersections between a Camera and Triangles.
 */
class CameraTriangleIntersection {

    /**
     * Tests the intersection of rays from the camera with triangles.
     */
    @Test
    void testIntersection() {

        // TC01: small triangle
        Triangle triangle01 = new Triangle(new Point(0, 1.0, -2.0), new Point(-1.0, -1.0, -2.0),
                new Point(1.0, -1.0, -2.0));
        Camera camera01 = Camera.getBuilder().setLocation(new Point(0, 0, 0))
                .setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0)).setVpSize(3, 3).setVpDistance(1).build();
        assertEquals(1, CameraSphereIntersectoin.intersectionNumber(new Geometries(triangle01), camera01),
                "Number of intersections is incorrect for small triangle");

        // TC02: large triangle
        Triangle triangle02 = new Triangle(new Point(0, 20.0, -2.0), new Point(-1.0, -1.0, -2.0),
                new Point(1.0, -1.0, -2.0));
        Camera camera02 = camera01;
        assertEquals(2, CameraSphereIntersectoin.intersectionNumber(new Geometries(triangle02), camera02),
                "Number of intersections is incorrect for large triangle");
    }
}
