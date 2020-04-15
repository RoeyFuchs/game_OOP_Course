package geometry;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * full circle, is like the circle but draw it as a full circle.
 */
public class FullCircle extends Circle {

    /**
     * contractor.
     * @param x the x value of the center.
     * @param y the y value of the center.
     * @param rad the radius of the circle
     * @param clr the color of the circle
     */
    public FullCircle(double x, double y, int rad, Color clr) {
        super(x, y, rad, clr);
    }
    /**
     * contractor.
     * @param center the center
     * @param rad the radius of the circle
     * @param clr the color of the circle
     */
    public FullCircle(Point center, int rad, Color clr) {
        super(center, rad, clr);
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(super.getColor());
        d.fillCircle(super.getX(), super.getY(), super.getRadius());
    }

}
