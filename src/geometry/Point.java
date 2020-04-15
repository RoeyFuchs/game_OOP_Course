package geometry;
import java.util.Random;

/**
 * class of Point, including x and y (coordinates).
 */
public class Point {
    private double x;
    private double y;

    /**
     * constructor.
     *
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * constructor - build a point to zero.
     */
    public Point() {
        this.x = 0;
        this.y = 0;
    }

    /**
     * random constructor.
     *
     * @param maxWidthStart  - max width start point
     * @param maxHeightStart - max height start point
     * @param maxWidthEnd    - max width end point
     * @param maxHeightEnd   - max height end point
     */
    public Point(int maxWidthStart, int maxWidthEnd, int maxHeightStart, int maxHeightEnd) {

        Random rand = new Random();
        this.x = rand.nextInt(maxWidthEnd - maxWidthStart) + maxHeightStart;
        this.y = rand.nextInt(maxHeightEnd - maxHeightStart) + maxHeightStart;

    }

    /**
     * find the distance between the point and other point.
     *
     * @param other - other point
     * @return the distance between them.
     */
    public double distance(Point other) {
        return Math.sqrt((this.x - other.x) * (this.x - other.x) + (this.y - other.y) * (this.y - other.y));
    }

    /**
     * Check if the point is equals to the other point.
     *
     * @param other - other point
     * @return return true if them equals, otherwise false
     */
    public boolean equals(Point other) {
        return (other.x == this.x) && (other.y == this.y);
    }

    /**
     * find the x value.
     *
     * @return the x value
     */
    public double getX() {
        return this.x;
    }

    /**
     * find the y value.
     *
     * @return the y value
     */
    public double getY() {
        return this.y;
    }
}

