package behavior;

import elements.Ball;
import elements.Block;
import elements.Counter;

/**
 * score listener.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    private final int pointsForDestroying = 10;
    private final int pointsForHit = 5;

    /**
     * constructor.
     *
     * @param g the gameLevel
     */
    public ScoreTrackingListener(GameLevel g) {
        this.currentScore = g.getScore();
    }

    /**
     * hit event - what will happen when hit something.
     *
     * @param beingHit the block that hit
     * @param hitter   the ball that hit
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(pointsForHit);
        if (beingHit.gethitPoints() == 0) {
            this.currentScore.increase(pointsForDestroying);
        }
    }

    /**
     * get the score counter.
     *
     * @return the score counter
     */
    public Counter getCurrentScore() {
        return currentScore;
    }
}
