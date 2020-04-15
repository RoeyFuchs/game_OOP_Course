package behavior;

import elements.Ball;
import elements.Block;
import elements.Counter;

/**
 * a BlockRemover is in charge of removing blocks from the gameLevel, as well as keeping count
 * of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * constructor.
     * @param gameLevel the gameLevel
     * @param removedBlocks a Counter
     */
    public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * constructor.
     *
     * @param gameLevel the gameLevel
     */
    public BlockRemover(GameLevel gameLevel) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = new Counter();
    }

    /**
     * get the counter.
     *
     * @return the counter
     */
    public Counter getCounter() {
        return this.remainingBlocks;
    }

    // Blocks that are hit and reach 0 hit-points should be removed
    // from the gameLevel. Remember to remove this listener from the block
    // that is being removed from the gameLevel.

    /**
     * check the hit points of the block that hit, when hit event happen,
     * if its zero - remove the block.
     *
     * @param beingHit the block that hit
     * @param hitter   the ball that hit
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        this.gameLevel.getScoreListen().hitEvent(beingHit, hitter);
        if (beingHit.gethitPoints() <= 0) {
            beingHit.removeFromGame(this.gameLevel);
            beingHit.removeHitListener(this);
            this.gameLevel.getBlocksCounter().decrease(1);
        }
    }
}