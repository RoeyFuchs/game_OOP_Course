package levels;
import biuoop.DrawSurface;
import java.awt.Color;

import behavior.Sprite;
import behavior.SpriteCollection;
import geometry.Rectangle;
import geometry.Circle;
import geometry.Line;
import behavior.GameLevel;


/**
 * the background of the level "Direct hit".
 */
public class DirectHitLevelBackground implements Sprite {
    private SpriteCollection collection;

    /**
     * constructor - as requested.
     */
    public DirectHitLevelBackground() {
        this.collection = new SpriteCollection();
        this.collection.addSprite(new Rectangle(0, 0, 800, 600, Color.BLACK));
        this.collection.addSprite(new Circle(400, 150, 100, Color.BLUE));
        this.collection.addSprite(new Circle(400, 150, 80, Color.BLUE));
        this.collection.addSprite(new Circle(400, 150, 60, Color.BLUE));
        this.collection.addSprite(new Line(260, 150, 560, 150, Color.BLUE));
        this.collection.addSprite(new Line(400, 40, 400, 260, Color.BLUE));

    }

    @Override
    public void drawOn(DrawSurface d) {
        collection.drawAllOn(d);
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
