package info;
import behavior.GameLevel;
import behavior.Sprite;
import biuoop.DrawSurface;
import geometry.Point;
import geometry.Rectangle;

import java.awt.Color;

/**
 * info bar - lives, score, level name.
 */
public class InfoBar implements Sprite {
    private Rectangle rec;
    private GameLevel gameLevel;
    //font size default - 15
    private int fontSize = 15;
    //font color default - white
    private Color fontColor = Color.WHITE;

    /**
     * constructor.
     * @param g the gameLevel
     */
    public InfoBar(GameLevel g) {
        final int height = 25;
        this.gameLevel = g;
        Point top = new Point(0, 0);
        this.rec = new Rectangle(top, g.getDefaultWidthEnd(), height);
        this.rec.setColor(Color.RED);
    }

    @Override
    public void drawOn(DrawSurface d) {
        this.rec.drawOn(d);
        //draw the level name
        d.setColor(this.getFontColor());
        d.drawText((int) this.getRegion(3).getX(), (int) this.getRegion(3).getY(),
                this.gameLevel.getLevelName(), this.getFontSize());
    }

    @Override
    public void timePassed(double dt) {

    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * get region for -
     * 1 - Lives
     * 2 - Score
     * 3 - level name.
     * @param num - number of the region
     * @return the point of the region.
     */
    public Point getRegion(int num) {
        //using 5 to move the text to the center.
        return new Point(this.rec.getUpperLeft().getX() + (this.rec.getWidth() / 4) * num,
                        this.rec.getMiddle().getY() + 5);
    }

    /**
     * get the font size.
     * @return the font size
     */
    public int getFontSize() {
        return this.fontSize;
    }

    /**
     * get the font color.
     * @return the font color
     */
    public Color getFontColor() {
        return this.fontColor;
    }
}
