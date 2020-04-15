package behavior;

import geometry.Point;

/**
 * Velocity specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {
    private double dx;
    private double dy;
    /**
     * constructor.
     * @param dx - how many "steps" in the x axis
     * @param dy - how many "steps" in the y axis
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }
    /**
     * Take a point with position (x,y) and return a new point with position (x+dx, y+dy).
     * @param p - old point (which point to move)
     * @param dt - amount frame per seconde
     * @return - the new point (the old point but after the movement)
     */
    public Point applyToPoint(Point p, double dt) {
        return new Point(p.getX() + (this.dx * dt), p.getY() + (this.dy * dt));
    }

    /**
     * find how many steps the in x axis.
     * @return the number of steps
     */
    public double getDx() {
        return this.dx;
    }
    /**
     * find how many steps the in y axis.
     * @return the number of steps
     */
    public double getDy() {
        return dy;
    }

    /**
     * get the total speed, by dy and dx.
     * @return the total speed
     */
    public double getTotalSpeed() {
        return Math.sqrt(Math.pow(this.dx, 2) + Math.pow(this.dy, 2));
    }
    /**
     * give a option to set the velocity by angel and speed. angel 0 means up, and them with clockwise
     * @param angle - the angel to move. 0 is up, and them with clockwise
     * @param speed - speed to move
     * @return - new velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.sin(Math.toRadians(angle));
        double dy = speed * -Math.cos(Math.toRadians(angle));
        return new Velocity(dx, dy);
    }
}