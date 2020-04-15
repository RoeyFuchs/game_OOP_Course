package behavior;

import elements.Ball;
import elements.Block;
import elements.Counter;


/**
 * a BallRemover is in charge of removing balls from the gameLevel, as well as keeping count
 * of the number of balls that remain.
 */

public class BallRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBalls;

    /**
     * constructor.
     * @param gameLevel    the gameLevel
     * @param removedBalls a Counter
     */
    public BallRemover(GameLevel gameLevel, Counter removedBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = removedBalls;
    }

    /**
     * constructor.
     *
     * @param gameLevel the gameLevel
     */
    public BallRemover(GameLevel gameLevel) {
        this.gameLevel = gameLevel;
        this.remainingBalls = new Counter();
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.gameLevel);
        this.gameLevel.getBallsCounter().decrease(1);
        //if no balls left, decrease one live
        if (this.gameLevel.getBallsCounter().getValue() == 0) {
            this.gameLevel.getLives().decrease(1);
        }
    }

    /**
     * get the counter.
     *
     * @return the counter
     */
    public Counter getCounter() {
        return this.remainingBalls;
    }
}
