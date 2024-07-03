package unittests.geometries;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import primitives.*;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the Ray class.
 */
public class RayTests {
	
    /**
     * Default constructor for RayTests.
     */
    public RayTests() {
        // Default constructor just for javadoc ganerition
    }

    /**
     * Test for the getPoint method in the Ray class.
     */
    @Test
    void getPointTest() {

        final Point p001 = new Point(0, 0, 1);
        final Point p101 = new Point(1, 0, 1);
        final Point p_101 = new Point(-1, 0, 1);

        final Vector v100 = new Vector(1, 0, 0);

        Ray ray = new Ray(p001, v100);

        // ============ Equivalence Partitions Tests ==============
        // TC01: when t is positive
        assertEquals(p101, ray.getPoint(1.0), "ERROR TC01: when t is positive");

        // TC02: when t is negative
        assertEquals(p_101, ray.getPoint(-1.0), "ERROR TC02: when t is negative");

        // =============== Boundary Values Tests ==================
        // TC03: when t equals zero
        assertEquals(ray.getHead(), ray.getPoint(0.0), "ERROR TC03: when t equals zero");
    }
    
    /**
     * Test for the findClosestPoint method in the Ray class.
     */
    @Test
    void findClosestPoint() {
        List<Point> points = new ArrayList<>();
        points.add(new Point(0, 0, 1));
        points.add(new Point(0, 0, 3));
        points.add(new Point(0, 0, 2));
        List<Point> points02 = new ArrayList<>();
        Ray rayTest01 = new Ray(new Point(0, 0, 4), new Vector(1, 1, 1));
        Ray rayTest03 = new Ray(new Point(0, 0, 0), new Vector(1, 1, 1));
        Ray rayTest04 = new Ray(new Point(0, 0, 2.1), new Vector(1, 1, 1));
        
        // ============ Equivalence Partitions Tests ==============
        // TC01: when the closest point is the middle
        assertEquals(new Point(0, 0, 3), rayTest01.findClosestPoint(points), "Returned incorrect point");

        // =============== Boundary Values Tests ==================
        // TC02: when the list is empty
        assertNull(rayTest01.findClosestPoint(points02), "Returned non-null when the list is empty");

        // TC03: when the closest point is the first
        assertEquals(new Point(0, 0, 1), rayTest03.findClosestPoint(points), "Returned incorrect point");

        // TC04: when the closest point is the last
        assertEquals(new Point(0, 0, 2), rayTest04.findClosestPoint(points), "Returned incorrect point");
    }
}
