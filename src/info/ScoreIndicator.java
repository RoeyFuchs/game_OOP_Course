package info;
import behavior.GameLevel;
import behavior.Sprite;
import biuoop.DrawSurface;
import elements.Counter;
import geometry.Point;

/**
 * score indicator that will show the score of the player.
 */
public class ScoreIndicator implements Sprite {
    private GameLevel gameLevel;
    private Counter counter;

    /**
     * constructor.
     * @param g - the gameLevel
     */
    public ScoreIndicator(GameLevel g) {
        this.gameLevel = g;
    }
    @Override
    public void drawOn(DrawSurface d) {
        InfoBar infoBar = this.gameLevel.getInfoBar();
        Point place = infoBar.getRegion(2);
        d.setColor(infoBar.getFontColor());
        d.drawText((int) place.getX(), (int) place.getY(),
                "Score:" +  this.counter.getValueString(), infoBar.getFontSize());
    }

    @Override
    public void timePassed(double dt) {

    }

    @Override
    public void addToGame(GameLevel g) {
        this.gameLevel = g;
        this.counter = gameLevel.getScore();
        g.addSprite(this);
    }
}
