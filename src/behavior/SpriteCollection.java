package behavior;

import biuoop.DrawSurface;
import elements.Paddle;

import java.util.ArrayList;
import java.util.List;

/**
 * spirite collection - the all spirite object of the game.
 */
public class SpriteCollection {
    private List<Sprite> sprite;
    private Paddle pad;

    /**
     * constructor.
     */
    public SpriteCollection() {
        this.sprite = new ArrayList<>();
    }

    /**
     * add a spirit to the collection.
     * @param s - a spirit
     */
    public void addSprite(Sprite s) {
        this.sprite.add(s);
    }

    /**
     * remove a sprite.
     * @param s the sprite to remove
     */
    public void removeSprite(Sprite s) {
        this.sprite.remove(s);
    }

    /**
     * call timePassed() on all sprites.
     * @param dt - frames
     */
    public void notifyAllTimePassed(double dt) {
        for (int i = 0; i < sprite.size(); i++) {
            sprite.get(i).timePassed(dt);
        }
    }

    /**
     * call drawOn(d) on all sprites.
     * @param d - the surface
     */
    public void drawAllOn(DrawSurface d) {
        for (int i = 0; i < sprite.size(); i++) {
            sprite.get(i).drawOn(d);
        }
    }
}