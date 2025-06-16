package unittests.geometries;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import geometries.*;
import primitives.*;

/**
 * Unit tests for geometries.Triangle class
 * @author Your Name
 */
class TriangleTests {
    /**
     * Delta value for accuracy when comparing the numbers of type 'double' in assertEquals
     */
    private static final double DELTA = 0.000001;

    /**
     * Test method for {@link geometries.Triangle#getNormal(primitives.Point)}.
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Triangle tr = new Triangle(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0));
        double sqrt3 = Math.sqrt(1d / 3);

        // ensure there are no exceptions
        assertDoesNotThrow(() -> tr.getNormal(new Point(0, 0, 1)), "");

        // generate the test result
        Vector result = tr.getNormal(new Point(0, 0, 1));

        // ensure |result| = 1
        assertEquals(1, result.length(), DELTA, "Triangle's normal is not a unit vector");

        // ensure the result is orthogonal to the triangle (check both possible directions)
        assertTrue(new Vector(sqrt3, sqrt3, sqrt3).equals(result) ||
                        new Vector(-sqrt3, -sqrt3, -sqrt3).equals(result),
                "Triangle's normal is not correct");
    }
}