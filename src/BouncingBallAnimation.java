import behavior.Velocity;
import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;
import elements.Ball;

import java.util.Random;


/**
 * animation of one ball moving.
 */
public class BouncingBallAnimation {
    /**
     * main function. doing all! create a ball and make him move
     * @param args - ignored
     */
    public static void main(String[] args) {
        //screen size and location
        final int defaultWidthStart = 0;
        final int defaultWidthEnd = 200;
        final int defaultHeightStart = 0;
        final int defaultHeightEnd = 200;

        final int maxRadius = 30;
        final int maxSpeed = 10;

        GUI gui = new GUI("just a ball", defaultWidthEnd - defaultWidthStart, defaultHeightEnd - defaultHeightStart);
        Sleeper sleeper = new Sleeper();
        //create a ball and give him velocity
        Random rand = new Random();
        Ball ball = new Ball(defaultWidthStart, defaultWidthEnd, defaultHeightStart,
                             defaultHeightEnd, rand.nextInt(maxRadius) + 1);

        //360 - any possible angel
        Velocity v = Velocity.fromAngleAndSpeed(rand.nextInt(360), rand.nextInt(maxSpeed) + 1);
        ball.setVelocity(v);
        while (true) {
            ball.moveOneStep(15);
            DrawSurface d = gui.getDrawSurface();
            ball.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }
}