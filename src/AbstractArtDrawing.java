import biuoop.GUI;
import biuoop.DrawSurface;
import geometry.Line;
import geometry.Point;

import java.awt.Color;

/**
 * drawing lines, thire middle point, and meeting point with others lines.
 */
public class AbstractArtDrawing {
    /**
     * the drawing function. using Line and Point classes, and gived GUI and DrawSurface.
     */
    public void drawLines() {
        //draw info as const
        final int radius = 3;
        final int numOfLines = 10;
        final int widthStart = 0;
        final int widthEnd = 700;
        final int heightStart = 0;
        final int heightEnd = 700;

        // Create a window with the title "My Abstract Creation"
        GUI gui = new GUI("My Abstract Creation", widthEnd, heightEnd);
        DrawSurface d = gui.getDrawSurface();
        //an array of the lines
        Line[] lines = new Line[numOfLines];
        for (int i = 0; i < numOfLines; ++i) {
            //create random lines and draw them
            lines[i] = new Line(new Point(widthStart, widthEnd, heightStart,  heightEnd),
                                new Point(widthStart, widthEnd, heightStart,  heightEnd));
            d.setColor(Color.BLACK);
            d.drawLine((int) lines[i].getStart().getX(), (int) lines[i].getStart().getY(),
                      (int) lines[i].getEnd().getX(), (int) lines[i].getEnd().getY());
        }
        Point mid = new Point(0, 0);
        //find the middle point of every line, and draw it
        for (int i = 0; i < numOfLines; ++i) {
           mid = lines[i].middle();
           d.setColor(Color.BLUE);
           d.fillCircle((int) mid.getX(), (int) mid.getY(), radius);
        }

        Point meeting = new Point(0, 0);
        //find the all meeting point of the lines, and draw them
        for (int i = 0; i < numOfLines; ++i) {
            for (int j = 0; j < numOfLines; ++j) {
                if (lines[i].isIntersecting(lines[j])) {
                    meeting = lines[i].intersectionWith(lines[j]);
                    d.setColor(Color.RED);
                    d.fillCircle((int) meeting.getX(), (int) meeting.getY(), radius);
                }
            }
        }
        gui.show(d);
    }

    /**
     * the main function call the drawing function, and nothing else.
     * @param args will not use.
     */
    public static void main(String[] args) {
        AbstractArtDrawing abstrctWindows = new AbstractArtDrawing();
        abstrctWindows.drawLines();
    }
}
