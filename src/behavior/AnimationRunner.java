package behavior;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * animation runner - create the GUI and run the animation.
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;

    /**
     * constrictor.
     * @param fps - how many freames per second
     * @param gameLevel - the gameLevel.
     */
    public AnimationRunner(int fps, GameLevel gameLevel) {
        this.gui = new GUI("My Game", gameLevel.getDefaultWidthEnd(),
                gameLevel.getDefaultHeightEnd());
        this.framesPerSecond = fps;
    }

    /**
     * constrictor.
     * @param fps - how many freames per second
     * @param width - screen width
     * @param hight - screen hight
     */
    public AnimationRunner(int fps, int width, int hight) {
        this.gui = new GUI("My Game", width, hight);
        this.framesPerSecond = fps;
    }

    /**
     * the basic animaton loop.
     * @param animation - the animation to run
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / this.framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = this.gui.getDrawSurface();
            animation.doOneFrame(d, this.framesPerSecond);
            Sleeper sleeper = new Sleeper();
            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

    /**
     * get the GUI.
     * @return the GUI
     */
    public GUI getGui() {
        return gui;
    }

    /**
     * close the gui.
     */
    public void closeGui() {
        this.gui.close();
    }
}
