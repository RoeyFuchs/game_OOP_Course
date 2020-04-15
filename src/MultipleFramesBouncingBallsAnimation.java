import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import elements.Ball;
import geometry.Point;
import geometry.Rectangle;

import java.awt.Color;

/**
 * animation of balls in 2 frames.
 */
public class MultipleFramesBouncingBallsAnimation {
    /**
     * main function - check the argument and make all happen.
     *
     * @param args - balls radius
     */
    public static void main(String[] args) {
        CheckInput checkArgs = new CheckInput(args);
        try {
            checkArgs.checkMultipleFrames();
        } catch (Exception e) {
            return;
        }

        final int screenSizeWidth = 800;
        final int screenSizeHeight = 600;
        int halfOfBalls = args.length / 2;
        Ball[][] ballsGroup = new Ball[2][halfOfBalls];
        //location of the rectangles.
        Point firstRectangleStart = new Point(50, 50);
        Point firstRectangleEnd = new Point(500, 500);
        Point secondRectangleStart = new Point(450, 450);
        Point secondRectangleEnd = new Point(600, 600);
        //color of the rectangles
        Rectangle firstRec = new Rectangle(firstRectangleStart, firstRectangleEnd, Color.GRAY);
        Rectangle secendRec = new Rectangle(secondRectangleStart, secondRectangleEnd, Color.YELLOW);
        //put the rectangles location in arrays
        Point[] rectangleStart = new Point[]{firstRectangleStart, secondRectangleStart};
        Point[] rectangleEnd = new Point[]{firstRectangleEnd, secondRectangleEnd};

        int ballsIndex = 0;
        //Create Balls
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < halfOfBalls; j++) {
                ballsGroup[i][j] = new Ball((int) rectangleStart[i].getX(), (int) rectangleEnd[i].getX(),
                     (int) rectangleStart[i].getY(), (int) rectangleEnd[i].getY(), Integer.parseInt(args[ballsIndex]));
                ballsIndex++;
            }
        }
        GUI gui = new GUI("2 Frames few balls", screenSizeWidth, screenSizeHeight);
        Sleeper sleeper = new Sleeper();
        while (true) {
            DrawSurface d = gui.getDrawSurface();
            firstRec.drawOn(d);
            secendRec.drawOn(d);
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < halfOfBalls; j++) {
                    ballsGroup[i][j].moveOneStep(15);
                    ballsGroup[i][j].drawOn(d);
                    sleeper.sleepFor(5);
                }
            }
            gui.show(d);
        }
    }
}
