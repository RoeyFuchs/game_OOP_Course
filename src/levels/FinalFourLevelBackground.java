package levels;
import biuoop.DrawSurface;

import java.awt.Color;
import behavior.Sprite;
import behavior.SpriteCollection;
import geometry.Rectangle;
import behavior.GameLevel;
import geometry.Clouds;

/**
 * final four level background.
 */
public class FinalFourLevelBackground implements Sprite {
    private SpriteCollection collection;

    /**
     * constructor - as requested.
     */
    public FinalFourLevelBackground() {
        this.collection = new SpriteCollection();
        this.collection.addSprite(new Rectangle(0, 0, 800, 600, new Color(0, 192, 216)));
        this.collection.addSprite(new Clouds(150, 450));
        this.collection.addSprite(new Clouds(650, 550));

    }

    @Override
    public void drawOn(DrawSurface d) {
        this.collection.drawAllOn(d);
    }

    @Override
    public void timePassed(double dt) {
        return;
    }

    @Override
    public void addToGame(GameLevel gameLevel) {
        return;
    }
}
