package unittests.geometries;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import geometries.*;
import primitives.*;

/**
 * Unit tests for geometries.Plane class
 * @author Your Name
 */
class PlaneTests {
    /**
     * Delta value for accuracy when comparing the numbers of type 'double' in assertEquals
     */
    private static final double DELTA = 0.000001;

    /**
     * Test method for {@link geometries.Plane#Plane(primitives.Point, primitives.Point, primitives.Point)}.
     */
    @Test
    void testConstructor() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Correct plane with three points
        assertDoesNotThrow(() -> new Plane(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0)),
                "Failed constructing a correct plane");

        // =============== Boundary Values Tests ==================
        // TC11: First and second points are identical
        assertThrows(IllegalArgumentException.class,
                () -> new Plane(new Point(0, 0, 1), new Point(0, 0, 1), new Point(0, 1, 0)),
                "Constructed a plane with first and second points identical");

        // TC12: First and third points are identical
        assertThrows(IllegalArgumentException.class,
                () -> new Plane(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 0, 1)),
                "Constructed a plane with first and third points identical");

        // TC13: Second and third points are identical
        assertThrows(IllegalArgumentException.class,
                () -> new Plane(new Point(0, 0, 1), new Point(1, 0, 0), new Point(1, 0, 0)),
                "Constructed a plane with second and third points identical");

        // TC14: All three points are identical
        assertThrows(IllegalArgumentException.class,
                () -> new Plane(new Point(0, 0, 1), new Point(0, 0, 1), new Point(0, 0, 1)),
                "Constructed a plane with all three points identical");

        // TC15: All three points are collinear
        assertThrows(IllegalArgumentException.class,
                () -> new Plane(new Point(0, 0, 1), new Point(0, 0, 2), new Point(0, 0, 3)),
                "Constructed a plane with all three points collinear");
    }

    /**
     * Test method for {@link geometries.Plane#getNormal(primitives.Point)}.
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Plane pl = new Plane(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0));
        double sqrt3 = Math.sqrt(1d / 3);
        Vector normal = pl.getNormal(new Point(0, 0, 1));

        // ensure there are no exceptions
        assertDoesNotThrow(() -> pl.getNormal(new Point(0, 0, 1)), "");

        // ensure |result| = 1
        assertEquals(1, normal.length(), DELTA, "Plane's normal is not a unit vector");

        // ensure the result is orthogonal to the plane (check both possible directions)
        assertTrue(new Vector(sqrt3, sqrt3, sqrt3).equals(normal) ||
                        new Vector(-sqrt3, -sqrt3, -sqrt3).equals(normal),
                "Plane's normal is not correct");
    }
}