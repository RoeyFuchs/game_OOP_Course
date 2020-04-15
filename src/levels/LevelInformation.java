package levels;

import behavior.Sprite;
import behavior.Velocity;
import elements.Block;

import java.util.List;

/**
 * level information interface.
 */
public interface LevelInformation {
    /**
     * the number of the balls in the level.
     *
     * @return the quantity of balls
     */
    int numberOfBalls();

    /**
     * The initial velocity of each ball.
     *
     * @return a list with the velocity of each ball
     */
    List<Velocity> initialBallVelocities();

    /**
     * the paddle speed.
     *
     * @return the speed
     */
    int paddleSpeed();

    /**
     * the paddle width.
     *
     * @return the width
     */
    int paddleWidth();

    /**
     * the name of the level.
     *
     * @return the level name
     */
    String levelName();

    /**
     * Returns a sprite with the background of the level.
     *
     * @return a sprite with the background
     */
    Sprite getBackground();

    /**
     * The Blocks that make up this level.
     *
     * @return a list with the all blocks
     */
    List<Block> blocks();

    /**
     * Number of levels that should be removed.
     *
     * @return Number of levels that should be removed
     */
    int numberOfBlocksToRemove();

    /**
     * set a rectangle as a background.
     * @param block the rectangle
     */
    void setBackGround(Block block);
}