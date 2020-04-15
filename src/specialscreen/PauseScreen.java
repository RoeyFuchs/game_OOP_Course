package specialscreen;

import behavior.Animation;
import biuoop.DrawSurface;

/**
 * pause screen let the player to get a rest.
 */
public class PauseScreen implements Animation {

    /**
     * constructor.
     */
    public PauseScreen() {
    }

    /**
     * the pause frame.
     *
     * @param d  the surface
     * @param dt - avoid that
     */
    public void doOneFrame(DrawSurface d, double dt) {
        d.drawText(0, d.getHeight() / 2, "-paused- press space to continue", 32);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }



}