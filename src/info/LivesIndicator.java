package info;

import behavior.GameLevel;
import behavior.Sprite;
import biuoop.DrawSurface;
import elements.Counter;
import geometry.Point;


/**
 * lives indicator.
 */
public class LivesIndicator implements Sprite {
    private GameLevel gameLevel;
    private Counter counter;

    /**
     * construct.
     */
    public LivesIndicator() {
        this.gameLevel = null;
        this.counter = null;
    }


    @Override
    public void drawOn(DrawSurface d) {
        InfoBar infoBar = this.gameLevel.getInfoBar();
        Point place = infoBar.getRegion(1);
        d.setColor(infoBar.getFontColor());
        d.drawText((int) place.getX(), (int) place.getY(),
                "Lives: " + this.counter.getValueString(), infoBar.getFontSize());

    }

    @Override
    public void timePassed(double dt) {
        return;
    }

    @Override
    public void addToGame(GameLevel g) {
        this.gameLevel = g;
        g.addSprite(this);
        this.counter = g.getLives();
    }
}
