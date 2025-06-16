package unittests.geometries;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import geometries.*;
import primitives.*;

/**
 * Unit tests for geometries.Cylinder class
 * @author Your Name
 */
class CylinderTests {
    /**
     * Delta value for accuracy when comparing the numbers of type 'double' in assertEquals
     */
    private static final double DELTA = 0.000001;

    /**
     * Test method for {@link geometries.Cylinder#getNormal(primitives.Point)}.
     */
    @Test
    void testGetNormal() {
        Ray axis = new Ray(new Point(0, 0, 0), new Vector(0, 0, 1));
        Cylinder cylinder = new Cylinder(axis, 1.0, 2.0);

        // ============ Equivalence Partitions Tests ==============
        // TC01: Point on the side surface
        Point pointOnSide = new Point(1, 0, 1);
        Vector result1 = cylinder.getNormal(pointOnSide);
        assertEquals(1, result1.length(), DELTA, "Cylinder's normal is not a unit vector");
        assertEquals(new Vector(1, 0, 0), result1, "Cylinder's side normal is not correct");

        // TC02: Point on bottom base (excluding center)
        Point pointOnBottom = new Point(0.5, 0, 0);
        Vector result2 = cylinder.getNormal(pointOnBottom);
        assertEquals(1, result2.length(), DELTA, "Cylinder's normal is not a unit vector");
        assertEquals(new Vector(0, 0, -1), result2, "Cylinder's bottom normal is not correct");

        // TC03: Point on top base (excluding center)
        Point pointOnTop = new Point(0.5, 0, 2);
        Vector result3 = cylinder.getNormal(pointOnTop);
        assertEquals(1, result3.length(), DELTA, "Cylinder's normal is not a unit vector");
        assertEquals(new Vector(0, 0, 1), result3, "Cylinder's top normal is not correct");

        // =============== Boundary Values Tests ==================
        // TC11: Point at center of bottom base
        Point bottomCenter = new Point(0, 0, 0);
        Vector result4 = cylinder.getNormal(bottomCenter);
        assertEquals(1, result4.length(), DELTA, "Cylinder's normal is not a unit vector");
        assertEquals(new Vector(0, 0, -1), result4, "Cylinder's bottom center normal is not correct");

        // TC12: Point at center of top base
        Point topCenter = new Point(0, 0, 2);
        Vector result5 = cylinder.getNormal(topCenter);
        assertEquals(1, result5.length(), DELTA, "Cylinder's normal is not a unit vector");
        assertEquals(new Vector(0, 0, 1), result5, "Cylinder's top center normal is not correct");

        // TC13: Point on edge between bottom base and side
        Point bottomEdge = new Point(1, 0, 0);
        Vector result6 = cylinder.getNormal(bottomEdge);
        assertEquals(1, result6.length(), DELTA, "Cylinder's normal is not a unit vector");
        // At the edge, we expect the base normal (since we check base first)
        assertEquals(new Vector(0, 0, -1), result6, "Cylinder's bottom edge normal is not correct");

        // TC14: Point on edge between top base and side
        Point topEdge = new Point(1, 0, 2);
        Vector result7 = cylinder.getNormal(topEdge);
        assertEquals(1, result7.length(), DELTA, "Cylinder's normal is not a unit vector");
        // At the edge, we expect the base normal (since we check base first)
        assertEquals(new Vector(0, 0, 1), result7, "Cylinder's top edge normal is not correct");
    }
}