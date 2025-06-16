package unittests.geometries;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import geometries.*;
import primitives.*;

/**
 * Unit tests for geometries.Tube class
 * @author Your Name
 */
class TubeTests {
    /**
     * Delta value for accuracy when comparing the numbers of type 'double' in assertEquals
     */
    private static final double DELTA = 0.000001;

    /**
     * Test method for {@link geometries.Tube#getNormal(primitives.Point)}.
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        Ray axis = new Ray(new Point(0, 0, 1), new Vector(0, 0, 1));
        Tube tube = new Tube(axis, 1.0);

        // TC01: Point on the tube surface
        Point pointOnTube = new Point(1, 0, 1);
        Vector result = tube.getNormal(pointOnTube);

        // ensure there are no exceptions
        assertDoesNotThrow(() -> tube.getNormal(pointOnTube), "");

        // ensure |result| = 1
        assertEquals(1, result.length(), DELTA, "Tube's normal is not a unit vector");

        // ensure the result is orthogonal to the axis
        assertEquals(0, result.dotProduct(axis.dir), DELTA, "Tube's normal is not orthogonal to axis");

        // ensure the result points outward from axis
        assertEquals(new Vector(1, 0, 0), result, "Tube's normal is not correct");

        // =============== Boundary Values Tests ==================
        // TC11: Point where connection to axis ray head creates 90 degree angle with axis
        Point specialPoint = new Point(1, 0, 0); // Point at same height as ray start
        Vector result2 = tube.getNormal(specialPoint);
        assertEquals(1, result2.length(), DELTA, "Tube's normal is not a unit vector at boundary");
        assertEquals(new Vector(1, 0, 0), result2, "Tube's normal is not correct at boundary");
    }
}