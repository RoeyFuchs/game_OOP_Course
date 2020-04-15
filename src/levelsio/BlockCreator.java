package levelsio;

import elements.Block;

/**
 * an interface of a factory-object that is used for creating blocks.
 */
public interface BlockCreator {
    /**
     * Create a block at the specified location.
     * @param xpos the x postion
     * @param ypos the y postion
     * @return a block
     */
    Block create(int xpos, int ypos);
}