package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import static primitives.Util.isZero;

/**
 * Class Tube is the basic class representing a tube in Cartesian
 * 3-Dimensional coordinate system.
 *
 * @author Maor Atari
 */
public class Tube extends RadialGeometry {
    /**
     * The axis ray of the tube
     */
    protected final Ray axisRay;

    /**
     * Constructor to initialize Tube with axis ray and radius
     *
     * @param axisRay the axis ray
     * @param radius  the radius value
     */
    public Tube(Ray axisRay, double radius) {
        super(radius);
        this.axisRay = axisRay;
    }

    @Override
    public Vector getNormal(Point point) {
        // Calculate the projection of the point onto the axis
        Vector pointToAxisStart = point.subtract(axisRay.p0);
        double t = pointToAxisStart.dotProduct(axisRay.dir);

        // Handle special case where t = 0 (point is at the same height as ray start)
        if (isZero(t)) {
            return point.subtract(axisRay.p0).normalize();
        }

        // Find the closest point on the axis to the given point
        Point closestPointOnAxis = axisRay.p0.add(axisRay.dir.scale(t));

        // Return the normalized vector from the axis to the point
        return point.subtract(closestPointOnAxis).normalize();
    }
}