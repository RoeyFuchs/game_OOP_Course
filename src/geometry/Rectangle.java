package geometry;
import behavior.GameLevel;
import behavior.Sprite;
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Rectangle have start point (the left top corner), width, height and color.
 */
public class Rectangle implements Sprite {
    private Point leftTopCorner;
    private double width;
    private double height;
    private Color color = Color.BLACK;
    private List border;
    private Line topBorder;
    private Line rightBorder;
    private Line bootomBorder;
    private Line leftBorder;


    /**
     * constructor.
     * @param leftTopCorner - point to the left top corner
     * @param width - the width of the rectangle
     * @param height - the height of the rectangle
     * @param color - the color of the rectangle
     */
    public Rectangle(Point leftTopCorner, int width, int height, Color color) {
        this.leftTopCorner = leftTopCorner;
        this.width = width;
        this.height = height;
        this.color = color;

        this.border = this.getRectangleBorder();
        this.topBorder = this.getRectangleBorder().get(0);
        this.rightBorder = this.getRectangleBorder().get(1);
        this.bootomBorder = this.getRectangleBorder().get(2);
        this.leftBorder = this.getRectangleBorder().get(3);
    }

    /**
     * cunstroctur.
     * @param leftTopCorner - point of the left top corner
     * @param rightBottomCorner - point of the right bottom corner
     * @param color - the color of the rectangle
     */
    public Rectangle(Point leftTopCorner, Point rightBottomCorner, Color color) {
        this.leftTopCorner = leftTopCorner;
        this.width = rightBottomCorner.getX() - leftTopCorner.getX();
        this.height = (int) rightBottomCorner.getY() - (int) leftTopCorner.getY();
        this.color = color;

        this.border = this.getRectangleBorder();
        this.topBorder = this.getRectangleBorder().get(0);
        this.rightBorder = this.getRectangleBorder().get(1);
        this.bootomBorder = this.getRectangleBorder().get(2);
        this.leftBorder = this.getRectangleBorder().get(3);
    }

    /**
     * constractur.
     * @param upperLeft - point of the left top corner
     * @param width - width of the rectangle
     * @param height - height of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.leftTopCorner = upperLeft;
        this.width = width;
        this.height = height;

        this.border = this.getRectangleBorder();
        this.topBorder = this.getRectangleBorder().get(0);
        this.rightBorder = this.getRectangleBorder().get(1);
        this.bootomBorder = this.getRectangleBorder().get(2);
        this.leftBorder = this.getRectangleBorder().get(3);
    }

    /**
     * constructor.
     * @param x - x of the left top corner
     * @param y - point to the left top corner
     * @param width - the width of the rectangle
     * @param height - the height of the rectangle
     * @param color - y of the left top corner
     */
    public Rectangle(double x, double y, double width, double height, Color color) {
        this.leftTopCorner = new Point(x, y);
        this.width = width;
        this.height = height;
        this.color = color;

        this.border = this.getRectangleBorder();
        this.topBorder = this.getRectangleBorder().get(0);
        this.rightBorder = this.getRectangleBorder().get(1);
        this.bootomBorder = this.getRectangleBorder().get(2);
        this.leftBorder = this.getRectangleBorder().get(3);
    }

    // Return the width and height of the rectangle

    /**
     * get the width of the rectangle.
     * @return the width of the rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * get the height of the rectangle.
     * @return the height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * get the upper-left point of the rectangle.
     * @return the upper-left point of the rectangle
     */
    public Point getUpperLeft() {
        return this.leftTopCorner;
    }

    /**
     * get the middle of the rectangle.
     * @return a point of the middle
     */
    public Point getMiddle() {
        return new Point(this.getUpperLeft().getX() + (this.getWidth() / 2),
                         this.getUpperLeft().getY() + (this.getHeight() / 2));
    }

    /**
     * get a list of the border.
     * @return a borders list of the rectangle. start with the top border, and continue clockwise
     */
    public java.util.List<Line> getRectangleBorder() {
        List<Point> corners = getCorners();
        Line l1 = new Line(corners.get(0), corners.get(1));
        Line l2 = new Line(corners.get(1), corners.get(2));
        Line l3 = new Line(corners.get(2), corners.get(3));
        Line l4 = new Line(corners.get(0), corners.get(3));
        List<Line> linesList = new ArrayList<Line>();
        linesList.add(l1);
        linesList.add(l2);
        linesList.add(l3);
        linesList.add(l4);
        return linesList;

    }

    /**
     * find the corners of the rectangle.
     * @return a list of points of the rectangle, start with the left corner and continue clockwise
     */
    public java.util.List<Point> getCorners() {
        List<Point> cornersList = new ArrayList<Point>();
        Point rightTopCorner = new Point(this.leftTopCorner.getX() + this.width, this.leftTopCorner.getY());
        Point leftBottomCorner = new Point(this.leftTopCorner.getX(), this.leftTopCorner.getY() + this.height);
        Point rightBottomCorner = new Point(rightTopCorner.getX(), leftBottomCorner.getY());
        cornersList.add(this.leftTopCorner);
        cornersList.add(rightTopCorner);
        cornersList.add(rightBottomCorner);
        cornersList.add(leftBottomCorner);
        return cornersList;

    }
    /**
     * the function find the meeting point of the rectangle with a line.
     * @param line - line to check a meeting with him
     * @return - a list of meeting point. possibly empty
     */
    public java.util.List intersectionPoints(Line line) {
        List<Line> rectangleBorder = getRectangleBorder();
        List<Point> meetingPoints = new ArrayList<>();
        for (int i = 0; i < rectangleBorder.size(); i++) {
            if (line.isIntersecting(rectangleBorder.get(i))) {
                meetingPoints.add(line.intersectionWith(rectangleBorder.get(i)));
            }
        }
        return meetingPoints;
    }

    /**
     * draw the rectangle on a surface.
     * @param surface - the surface to draw on
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.getColor());
        surface.fillRectangle((int) leftTopCorner.getX(), (int) leftTopCorner.getY(), (int) width, (int) height);
        surface.setColor(Color.BLACK);
        surface.drawRectangle((int) leftTopCorner.getX(), (int) leftTopCorner.getY(), (int) width, (int) height);
    }

    @Override
    public void timePassed(double dt) {
        return;
    }

    @Override
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);

    }

    /**
     * find the color of the rectangle.
     * @return the color
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * find the line on the top border.
     * @return the line of the top border
     */
    public Line getTopBorder() {
        return this.topBorder;
    }
    /**
     * find the line on the bottom border.
     * @return the line of the bottom border
     */
    public Line getBootomBorder() {
        return this.bootomBorder;
    }
    /**
     * find the line on the right border.
     * @return the line of the right border
     */
    public Line getRightBorder() {
        return this.rightBorder;
    }
    /**
     * find the line on the left border.
     * @return the line of the left border
     */
    public Line getLeftBorder() {
        return this.leftBorder;
    }


    /**
     * return this rectangle (have to be - Collidable interface).
     * @return the line of the top border
     */
    public Rectangle getCollisionRectangle() {
        return this;
    }

    /**
     * set a color to the rectangle.
     * @param inputColor the color
     */
    public void setColor(Color inputColor) {
        this.color = inputColor;
    }



    /**
     * the function return the num of the border that the received point is on it. 0 - Top border and with clockwise.
     * @param point - the point to check
     * @return - the border number
     */
    public int getBorderbyPoint(Point point) {
        if (this.getTopBorder().pointOnLine(point)) {
            return 0;
        }
        if (this.getRightBorder().pointOnLine(point)) {
            return 1;
        }
        if (this.getBootomBorder().pointOnLine(point)) {
            return 2;
        }
        if (this.getLeftBorder().pointOnLine(point)) {
            return 3;
        }
        return -1;
    }

    /**
     * check the closet border to a point.
     * @param point - the point to check with.
     * @return the number of the border (the top border is 0, and with clockwise)
     */
    public int findClosestBorder(Point point) {
        double distance = this.getRectangleBorder().get(0).distanceFromPoint(point);
        int closestNum = 0;
        for (int i = 1; i < this.getRectangleBorder().size(); i++) {
            if (distance > this.getRectangleBorder().get(i).distanceFromPoint(point)) {
                closestNum = i;
                distance = this.getRectangleBorder().get(i).distanceFromPoint(point);
            }
        }
        return closestNum;
    }

}
