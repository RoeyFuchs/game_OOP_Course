package behavior;

import elements.Ball;
import elements.Block;

/**
 * Hit Listener interface. for the class that need to know that something hit.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * @param beingHit the block that hit
     * @param hitter the ball that hit
     */
    void hitEvent(Block beingHit, Ball hitter);
}