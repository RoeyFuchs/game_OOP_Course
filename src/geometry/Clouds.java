package geometry;
import behavior.Sprite;
import behavior.SpriteCollection;
import behavior.GameLevel;
import biuoop.DrawSurface;


import java.awt.Color;

/**
 * clouds - a grope of a few circle.
 */
public class Clouds implements Sprite {
    private SpriteCollection collection;

    /**
     * constructor of clouds.
     * @param x the x of the center
     * @param y the y of the center
     */
    public Clouds(double x, double y) {
        Point center = new Point(x, y);
        this.collection = new SpriteCollection();
        //the rain
        for (int i = 0; i < 10; i++) {
            this.collection.addSprite(new Line(center.getX() + (i * 5) - 10, center.getY(),
                                        center.getX() + (i * 5) - 50, 600, Color.WHITE));
        }
        //the clouds
        this.collection.addSprite(new FullCircle(center.getX() + 20, center.getY() + 10, 25, Color.LIGHT_GRAY));
        this.collection.addSprite(new FullCircle(center.getX() - 10, center.getY() - 20, 30, new Color(187, 202, 209)));
        this.collection.addSprite(new FullCircle(center, 15, Color.WHITE));

        this.collection.addSprite(new FullCircle(center.getX() + 30, center.getY() - 20, 25, Color.LIGHT_GRAY));
        this.collection.addSprite(new FullCircle(center.getX() + 30, center.getY() - 40, 25, new Color(168, 199, 199)));
        this.collection.addSprite(new FullCircle(center.getX() + 10, center.getY() - 40, 25, new Color(183, 182, 191)));
        this.collection.addSprite(new FullCircle(center.getX() - 5, center.getY() - 40, 25, new Color(163, 182, 191)));


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
