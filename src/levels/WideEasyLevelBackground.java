package levels;
import biuoop.DrawSurface;
import behavior.Sprite;
import behavior.SpriteCollection;
import geometry.Rectangle;
import geometry.FullCircle;
import geometry.Point;
import behavior.GameLevel;
import geometry.Line;
import java.awt.Color;

/**
 * the background of the level "Wide Easy".
 */
public class WideEasyLevelBackground implements Sprite {
    private SpriteCollection collection;

    /**
     * constructor - as requested.
     */
    public WideEasyLevelBackground() {
        this.collection = new SpriteCollection();
        this.collection.addSprite(new Rectangle(0, 0, 800, 600, Color.WHITE));
        Point sun = new Point(170, 130);
        this.collection.addSprite(new FullCircle(sun, 75, new Color(229, 255, 139)));
        int numOfLines = 40;
        for (int i = 0; i <= numOfLines; i++) {
            Point end = new Point((800 / numOfLines) * i, 300);
            Line l = new Line(sun, end, Color.YELLOW);
            this.collection.addSprite(l);
        }
        this.collection.addSprite(new FullCircle(sun, 50, new Color(227, 246, 165)));
        this.collection.addSprite(new FullCircle(sun, 25, new Color(230, 242, 189)));

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
