import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import elements.Ball;

/**
 * animation of few balls moving.
 */
public class MultipleBouncingBallsAnimation {
    /**
     * main function. doing all! create the balls and make them move
     * @param args - the radius of the balls
     */
    public static void main(String[] args) {
        CheckInput checkArgs = new CheckInput(args);
        try {
            checkArgs.checkMultipleBalls();
        } catch (Exception e) {
            return;
        }
        //screen size and location
        final int defaultWidthStart = 0;
        final int defaultWidthEnd = 800;
        final int defaultHeightStart = 0;
        final int defaultHeightEnd = 600;
        //create the balls
        Ball[] balls = new Ball[args.length];
        for (int i = 0; i < args.length; i++) {
            balls[i] = new Ball(defaultWidthStart, defaultWidthEnd, defaultHeightStart,
                                defaultHeightEnd, Integer.parseInt(args[i]));
        }
        GUI gui = new GUI("few balls", defaultWidthEnd - defaultWidthStart, defaultHeightEnd - defaultHeightStart);
        Sleeper sleeper = new Sleeper();
        //make them move
        while (true) {
            DrawSurface d = gui.getDrawSurface();
            for (int i = 0; i < args.length; i++) {
                balls[i].moveOneStep(15);
                balls[i].drawOn(d);
                sleeper.sleepFor(5);
            }
            gui.show(d);
        }
    }
}
