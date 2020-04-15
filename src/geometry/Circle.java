package geometry;
import behavior.GameLevel;
import behavior.Sprite;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * the circle class.
 */
public class Circle implements Sprite {
    private Point center;
    private int radius;
    private Color color;

    /**
     * contractor.
     * @param x the x value of the center.
     * @param y the y value of the center.
     * @param rad the radius of the circle
     * @param clr the color of the circle
     */
    public Circle(double x, double y, int rad, Color clr) {
        this.center = new Point(x, y);
        this.radius = rad;
        this.color = clr;
    }
    /**
     * contractor.
     * @param center the center
     * @param rad the radius of the circle
     * @param clr the color of the circle
     */
    public Circle(Point center, int rad, Color clr) {
        this.center = center;
        this.radius = rad;
        this.color = clr;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.getColor());
        d.drawCircle(this.getX(), this.getY(), this.radius);

    }

    @Override
    public void timePassed(double dt) {

    }

    @Override
    public void addToGame(GameLevel gameLevel) {

    }

    /**
     * get the color of the circle.
     * @return the color of the circle
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * get the radius of the circle.
     * @return the radius of the circle
     */
    public int getRadius() {
        return radius;
    }

    /**
     * get the x value of the center (as int).
     * @return the x value
     */
    public int getX() {
        return (int) this.center.getX();
    }
    /**
     * get the y value of the center (as int).
     * @return the y value
     */
    public int getY() {
        return (int) this.center.getY();
    }

}
