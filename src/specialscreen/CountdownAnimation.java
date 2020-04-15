package specialscreen;
import behavior.Animation;
import behavior.SpriteCollection;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * countdown animation before the game start.
 */

public class CountdownAnimation implements Animation {
    private Integer counter;
    private long startTime;
    private long different;
    private int diffCounter;
    private SpriteCollection gameScreen;
    private boolean stop;

    /**
     * constructor.
     * @param numOfSeconds - how many second before the game will start.
     * @param countFrom - from which number start to count.
     * @param gameScreen - the sprite of the game.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.startTime = System.currentTimeMillis();
        this.different = (long) ((numOfSeconds * 1000) / (countFrom));
        this.diffCounter = 1;
        this.counter = countFrom;
        this.gameScreen = gameScreen;
        this.stop = false;
    }


    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        this.gameScreen.drawAllOn(d);
            if (System.currentTimeMillis() > this.startTime + (different * this.diffCounter)) {
                this.counter -= 1;
                this.diffCounter += 1;
            }
            d.setColor(Color.RED);
            d.drawText(d.getWidth() / 2, d.getHeight() / 2, this.counter.toString(), 100);
            if (this.counter == 0) {
                this.stop = true;
            }
    }

    /**
     * check if need to stop.
     * @return true or false.
     */
    public boolean shouldStop() {
        return this.stop;
    }

}