package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import static primitives.Util.isZero;

/**
 * Class Cylinder is the basic class representing a cylinder in Cartesian
 * 3-Dimensional coordinate system.
 *
 * @author Maor Atari
 */
public class Cylinder extends Tube {
    /**
     * The height of the cylinder
     */
    private final double height;

    /**
     * Constructor to initialize Cylinder with axis ray, radius and height
     *
     * @param axisRay the axis ray
     * @param radius  the radius value
     * @param height  the height value
     */
    public Cylinder(Ray axisRay, double radius, double height) {
        super(axisRay, radius);
        this.height = height;
    }

    @Override
    public Vector getNormal(Point point) {
        Point p0 = axisRay.p0;
        Vector dir = axisRay.dir;

        // Calculate the projection parameter t
        double t;
        if (point.equals(p0)) {
            // Special case: point is exactly at the ray start (bottom center)
            t = 0;
        } else {
            Vector pointToAxisStart = point.subtract(p0);
            t = pointToAxisStart.dotProduct(dir);
        }

        // Check if point is on bottom base
        if (isZero(t)) {
            return dir.scale(-1); // Normal pointing down from bottom
        }

        // Check if point is on top base
        if (isZero(t - height)) {
            return dir; // Normal pointing up from top
        }

        // Point is on the side surface - use tube's logic
        Point closestPointOnAxis = p0.add(dir.scale(t));
        return point.subtract(closestPointOnAxis).normalize();
    }
}