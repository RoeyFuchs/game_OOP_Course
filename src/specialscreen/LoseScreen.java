package specialscreen;
import behavior.Animation;
import behavior.GameFlow;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import elements.Counter;

import java.awt.Color;

/**
 * Lose screen.
 */
public class LoseScreen implements Animation {
    private KeyboardSensor keyboard;
    private Counter score;
    private boolean stop;

    /**
     * constructor.
     * @param gameFlow - the game flow
     */
    public LoseScreen(GameFlow gameFlow) {
        this.keyboard = gameFlow.getKeyboardSensor();
        this.score = gameFlow.getScore();
        this.stop = false;
    }

    /**
     * the Lose frame.
     *
     * @param d the surface
     * @param dt avoid
     */
    public void doOneFrame(DrawSurface d, double dt) {
            Integer gameScore = this.score.getValue();
            d.setColor(Color.BLACK);
            d.drawText(0, d.getHeight() / 2, "Game Over. your score is:" + gameScore.toString(), 50);
            //same text, but move it a little bit, just make it prettier
            d.setColor(Color.RED);
            d.drawText(2, d.getHeight() / 2, "Game Over. your score is:" + gameScore.toString(), 50);
            if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
                this.stop = true;
        }
    }

    /**
     * check if need to stop.
     *
     * @return true or false
     */
    public boolean shouldStop() {
        return this.stop;
    }

}