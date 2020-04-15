package specialscreen;
import behavior.Animation;
import behavior.GameFlow;
import biuoop.DrawSurface;
import elements.Counter;

import java.awt.Color;

/**
 * Win screen.
 */
public class WinScreen implements Animation {
    private Counter score;

    /**
     * constructor.
     * @param gameFlow - the game flow
     */
    public WinScreen(GameFlow gameFlow) {
        this.score = gameFlow.getScore();
    }

    /**
     * the Win frame.
     *
     * @param d the surface
     * @param dt avoid
     */
    public void doOneFrame(DrawSurface d, double dt) {
        Integer gameScore = this.score.getValue();
        d.setColor(Color.BLACK);
        d.drawText(0, d.getHeight() / 2, "You win! your score is:" + gameScore.toString(), 50);
        //same text, but move it a little bit, just make it prettier
        d.setColor(Color.BLUE);
        d.drawText(2, d.getHeight() / 2 + 5, "You win! your score is:" + gameScore.toString(), 50);
    }

    /**
     * check if need to stop.
     *
     * @return true or false
     */
    public boolean shouldStop() {
        return false;
    }

}