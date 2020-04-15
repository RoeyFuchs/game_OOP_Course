package levelsio;

import elements.Block;
import java.util.Map;
import java.util.TreeMap;

/**
 * the block factory.
 */
public class BlocksFromSymbolsFactory {
    private Map<String, Integer> spacerWidths;
    private Map<String, BlockCreator> blockCreators;

    /**
     * constructor.
     */
    public BlocksFromSymbolsFactory() {
        this.spacerWidths = new TreeMap<>();
        this.blockCreators = new TreeMap<>();
    }


    /**
     * check if the input string is spacer symbol.
     * @param s the string
     * @return true or false
     */
    public boolean isSpaceSymbol(String s) {
        return this.spacerWidths.containsKey(s);

    }
    /**
     * check if the input string is block symbol.
     * @param s the string
     * @return true or false
     */
    public boolean isBlockSymbol(String s) {
        return this.blockCreators.containsKey(s);
    }

    /**
     * get the width of spacer by symbol input.
     * @param s the symbol
     * @return the width
     */
    public int getSpaceWidth(String s) {
        return this.spacerWidths.get(s);
    }

    /**
     * create a block.
     * @param s the symbol of the block
     * @param x the x position
     * @param y the y position
     * @return a new block
     */
    public Block getBlock(String s, int x, int y) {
        Block newBlock = this.blockCreators.get(s).create(x, y);
        return newBlock;
    }

    /**
     * get the spacer map.
     * @return the spacer map
     */
    public Map<String, Integer> getSpacerWidths() {
        return this.spacerWidths;
    }

    /**
     * get the blocks creator map.
     * @return the blocks creator map
     */
    public Map<String, BlockCreator> getBlockCreators() {
        return this.blockCreators;
    }
}
