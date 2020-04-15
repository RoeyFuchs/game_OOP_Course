package behavior;

import biuoop.DrawSurface;

/**
 * Animation interface.
 */
public interface Animation {
    /**
     * what to draw in a frame.
     * @param d - the surface to draw on.
     * @param dt - mount of seconds passed.
     */
    void doOneFrame(DrawSurface d, double dt);

    /**
     * check in need to stop.
     * @return true or false
     */
    boolean shouldStop();

}