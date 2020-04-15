package geometry;
import behavior.Collidable;
import behavior.CollisionInfo;
import behavior.GameLevel;
import behavior.Sprite;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * class of Line, using other class of point.
 */
public class Line implements Sprite {

    private Point start;
    private Point end;
    private Double incline;
    private Color color = Color.BLACK;
    private boolean isPoint;

    /**
     * constructor.
     *
     * @param start - start point of the line
     * @param end   - end point of the line
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
        this.incline = incline();
        this.isPoint = isPoint();
    }

    /**
     * constructor.
     *
     * @param x1 - the x value of the start point
     * @param y1 - the y value of the start point
     * @param x2 - the x value of the end point
     * @param y2 - the y value of the end point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
        this.incline = incline();
        this.isPoint = isPoint();
    }

    /**
     * constructor.
     *
     * @param x1 - the x value of the start point
     * @param y1 - the y value of the start point
     * @param x2 - the x value of the end point
     * @param y2 - the y value of the end point
     * @param color - the color of the line
     */
    public Line(double x1, double y1, double x2, double y2, Color color) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
        this.incline = incline();
        this.isPoint = isPoint();
        this.color = color;
    }

    /**
     * constructor.
     * @param start start point
     * @param end end point
     * @param color the color of the line
     */
    public Line(Point start, Point end, Color color) {
        this.start = start;
        this.end = end;
        this.incline = incline();
        this.isPoint = isPoint();
        this.color = color;
    }


    /**
     * find the length of the line.
     *
     * @return - the length of the line
     */
    public double length() {
        double x1 = this.start.getX();
        double y1 = this.start.getY();
        double x2 = this.end.getX();
        double y2 = this.end.getY();
        //length formula
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }

    /**
     * find the middle point of the line.
     *
     * @return - the middle point (as point)
     */
    public Point middle() {
        double x1 = this.start.getX();
        double y1 = this.start.getY();
        double x2 = this.end.getX();
        double y2 = this.end.getY();
        //formula to find the middle
        return new Point((x1 + x2) / 2, (y1 + y2) / 2);

    }

    /**
     * find the start point of the line.
     *
     * @return - the start point of the line (as point)
     */
    public Point start() {
        return new Point(this.start.getX(), this.start.getY());
    }

    /**
     * find the end point of the line.
     *
     * @return - the end point of the line (as point)
     */
    public Point end() {
        return new Point(this.end.getX(), this.end.getY());
    }

    /**
     * the function check if the line is intersecting with other line.
     *
     * @param other - other line
     * @return true is yes, otherwise false
     */
    public boolean isIntersecting(Line other) {
        //check if there's a meeting point
        if (intersectionWith(other) == null) {
            return false;
        }
        return true;
    }


    /**
     * the function check what the meeting point.
     *
     * @param other - other line
     * @return the meeting point (as point), or null if they are not meeting
     */
    public Point intersectionWith(Line other) {
        //check if they equals, or contain each other.
        if (this.equals(other)) {
            return null;
        }
        //if one of lines (or both) are point, there is no meeting point
        if (this.isPoint || other.isPoint) {
            return null;
        }
        Double xMeeting;
        Double yMeeting;
        double x1 = this.start.getX();
        double y1 = this.start.getY();
        Double m1 = this.incline;
        double x2 = other.start.getX();
        double y2 = other.start.getY();
        Double m2 = other.incline;
        //check individual for a spical cases of x=k or y=i
        if (m2 == Double.POSITIVE_INFINITY || m2 == Double.NEGATIVE_INFINITY) {
            if (m1 == 0) {
                xMeeting = x2;
                yMeeting = y1;
            } else {
                xMeeting = x2;
                yMeeting = m1 * xMeeting - m1 * x1 + y1;
            }
        } else if (m1 == Double.POSITIVE_INFINITY || m1 == Double.NEGATIVE_INFINITY) {
            if (m2 == 0) {
                xMeeting = x1;
                yMeeting = y2;
            } else {
                xMeeting = x1;
                yMeeting = m2 * xMeeting - m2 * x2 + y2;
            }

            //if the lines are parallel
        } else if (m1.equals(m2)) {
            return null;
            //the regular case
        } else {
            //using a formula
            xMeeting = (-x2 * m2 + y2 + m1 * x1 - y1) / (m1 - m2);
            yMeeting = m1 * xMeeting - m1 * x1 + y1;
        }
        if (yMeeting.isNaN()) {
            return null;
        }
        Point meeting = new Point(xMeeting, yMeeting);
        //check if the point is on the lines by distance from the edge
        if ((this.start.distance(meeting) > this.length()) || (this.end.distance(meeting) > this.length())
                || (other.start.distance(meeting) > other.length()) || (other.end.distance(meeting) > other.length())) {
            return null;
        }
        return meeting;
    }

    /**
     * check if the lines are equals.
     *
     * @param other - other line
     * @return true if equals, otherwise false
     */
    public boolean equals(Line other) {
        if ((this.start.equals(other.start)) && (this.end.equals(other.end))) {
            return true;
        }
        //in a case of the points (start and end) are inverted
        if ((this.end.equals(other.start)) && (this.start.equals(other.end))) {
            return true;
        }
        return false;
    }

    /**
     * check what the incline of the line.
     *
     * @return the incline value. in a case of x=k or y=i, return zero.
     */
    public Double incline() {
        return (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
    }

    /**
     * check if the line is just a point.
     *
     * @return true if yes, otherwise false
     */
    public boolean isPoint() {
        if (this.start.equals(this.end)) {
            return true;
        }
        return false;
    }


    /**
     * find the start point.
     *
     * @return the start point
     */
    public Point getStart() {
        return this.start;
    }

    /**
     * find the end point.
     *
     * @return the end point
     */
    public Point getEnd() {
        return this.end;
    }

    /**
     * return the closet meeting point with the rectangle.
     *
     * @param rect - a rectangle
     * @return the closes meeting point
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List meetingPoints = rect.intersectionPoints(this);
        int index = 0;
        Point closestPoint = new Point(0, 0);
        if (meetingPoints.size() == 0) {
            return null;
        }
        closestPoint = (Point) meetingPoints.get(0);
        double distance = getStart().distance((Point) meetingPoints.get(index));
        while (index < meetingPoints.size()) {
            if (this.getStart().distance((Point) meetingPoints.get(index)) < distance) {
                closestPoint = (Point) meetingPoints.get(index);
                distance = this.getStart().distance((Point) meetingPoints.get(index));
            }
            index++;
        }
        return closestPoint;
    }

    /**
     * find the closet meeting point with a few rectangles.
     *
     * @param collidable - an array of collidables
     * @return the closet point
     */
    public CollisionInfo closestIntersectionToStartOfLineWithArrayOfCollidables(List<Collidable> collidable) {
        List<Point> points = new ArrayList<Point>();
        List<Collidable> intersectionRect = new ArrayList<Collidable>();
        for (int i = 0; i < collidable.size(); i++) {
            if (this.closestIntersectionToStartOfLine(collidable.get(i).getCollisionRectangle()) != null) {
                points.add(this.closestIntersectionToStartOfLine(collidable.get(i).getCollisionRectangle()));
                intersectionRect.add(collidable.get(i));
            }
        }
        if (points.isEmpty()) {
            return null;
        }
        int closestId = closestPointFromStartWithArrayOfPoints(points);
        return new CollisionInfo(points.get(closestId), intersectionRect.get(closestId));
    }


    /**
     * find the closet point with array of points.
     *
     * @param points - array of points
     * @return the closest point
     */
    public int closestPointFromStartWithArrayOfPoints(List<Point> points) {
        int pointId = 0;
        Point closest = points.get(0);
        for (int i = 0; i < points.size(); i++) {
            if (this.start.distance(closest) > this.start.distance(points.get(i))) {
                closest = points.get(i);
                pointId = i;
            }
        }
        return pointId;
    }

    /**
     * check if a point is on the line.
     *
     * @param point the point to check
     * @return true or false
     */
    public boolean pointOnLine(Point point) {
        if (point.getY() - this.start.getY() == this.incline * (point.getX() - this.start.getX())) {
            if (point.distance(this.start) + point.distance(this.end) == this.length()) {
                return true;
            }
        }
        if (this.incline == Double.POSITIVE_INFINITY || this.incline == Double.NEGATIVE_INFINITY) {
            if (this.start.getX() == point.getX()
                    && (point.distance(this.start) + point.distance(this.end) == this.length())) {
                return true;
            }
        }
        return false;
    }

    /**
     * find the distance between point and a line, using the middle point of the line. enough for my use.
     *
     * @param point the point to check the distance
     * @return the distance
     */
    public double distanceFromPoint(Point point) {
        //check if is a case of y=b or x=b
        if (this.incline == Double.NEGATIVE_INFINITY || this.incline == Double.POSITIVE_INFINITY) {
            return Math.abs(this.getStart().getX() - point.getX());
        }
        if (this.incline == 0) {
            return Math.abs(this.getStart().getY() - point.getY());
        }
        Double b = this.start.getY() - (this.incline * this.getStart().getX());
        //find the distance by formula.
        return Math.abs(((this.incline * point.getX()) - point.getY() + b) / Math.sqrt(Math.pow(this.incline, 2) + 1));

    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.drawLine((int) this.getStart().getX(), (int) this.getStart().getY(),
                (int) this.getEnd().getX(), (int) this.getEnd().getY());
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
     * get a point near the end, calculate by 95% of end and 5% of the start.
     * @return a point
     */
    public Point getPointNearEnd() {
        double x = (this.start.getX() * 0.4) + (this.end.getX() * 0.6);
        double y = (this.start.getY() * 0.4) + (this.end.getY() * 0.6);
        return new Point(x, y);
    }


}