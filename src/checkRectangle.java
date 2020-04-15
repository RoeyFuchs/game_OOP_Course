import geometry.Line;
import geometry.Point;
import geometry.Rectangle;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class checkRectangle {
    public static void main(String[] args) {
        Point p1 = new Point(100, 0);
        Rectangle r1 = new Rectangle (p1, 200, 100, Color.BLACK);
        List<Line> rectangleBorder = r1.getRectangleBorder();
        Line l1 = new Line (100, -50, 99, -50);
        ArrayList a = (ArrayList) r1.intersectionPoints(l1);
        System.out.println(r1.intersectionPoints(l1));
    }
}
