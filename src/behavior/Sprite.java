package behavior;

import biuoop.DrawSurface;

/**
 * spirit interfcace.
 */
public interface Sprite {
    /**
     * draw the sprite to the screen.
     * @param d surface
     */
    void drawOn(DrawSurface d);
    /**
     * notify the sprite that time has passed.
     * @param dt - mount of seconds passed.
     */
    void timePassed(double dt);

    /**
     * add the sprite to a gameLevel.
     * @param gameLevel the gameLevel to add the sprite to
     */
    void addToGame(GameLevel gameLevel);
}