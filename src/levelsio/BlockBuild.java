package levelsio;


import elements.Block;
import geometry.Point;
import java.util.Map;
import java.util.TreeMap;

/**
 * using the blocks build to build a block step by step.
 */
public class BlockBuild implements BlockCreator {
    private String symbol;
    private Integer width;
    private Integer height;
    private Integer hitPoints;
    private Map<Integer, String> fill;
    private String stroke;
    private String defaultFill;

    /**
     * constructor.
     */
    public BlockBuild() {
        this.fill = new TreeMap<>();
    }

    /**
     * set a symbol to the block.
     *
     * @param sym th symbo
     */
    public void setSymbol(String sym) {
        this.symbol = sym;
    }

    /**
     * set a width to the block.
     *
     * @param wid the width
     */
    public void setWidth(int wid) {
        this.width = wid;
    }

    /**
     * set hit points to the block.
     *
     * @param points how many points the block will have
     */
    public void setHitPoints(int points) {
        this.hitPoints = points;
    }

    /**
     * set a height to the block.
     *
     * @param hig the height
     */
    public void setHeight(int hig) {
        this.height = hig;
    }

    /**
     * set the default fill.
     *
     * @param dFill the default fill.
     */
    public void setDefaultFill(String dFill) {
        this.defaultFill = dFill;
    }

    /**
     * add fill.
     *
     * @param num   when to show it (by score)
     * @param value what to show (which fill)
     */
    public void addFill(String num, String value) {
        this.fill.put(Integer.parseInt(num), value);

    }

    /**
     * set the sroke.
     *
     * @param str the stroke.
     */
    public void setStroke(String str) {
        this.stroke = str;
    }

    /**
     * compelete data from the default setting.
     *
     * @param bd the default block
     */
    public void completeBlock(BlockDefault bd) {
        if (this.width == null) {
            this.width = bd.getWidth();
        }
        if (this.height == null) {
            this.height = bd.getHeight();
        }
        if (this.hitPoints == null) {
            this.hitPoints = bd.getHitPoints();
        }
        if (this.fill == null) {
            this.fill = bd.getFill();
        }
        if (this.fill == null) {
            this.fill = bd.getFill();
        }
        if (this.defaultFill == null) {
            this.defaultFill = bd.getDefaultFill();
        }
        if (this.stroke == null && bd.getStroke() != null) {
            this.stroke = bd.getStroke();
        }
    }

    /**
     * check if all the necessary settings are set.
     *
     * @return true or false.
     */
    public boolean checkIfAllComplete() {
        if (this.width == null || this.height == null || this.hitPoints == null
                || this.symbol == null) {
            return false;
        }
        return true;
    }

    /**
     * get the symbol of the block.
     *
     * @return the symbol
     */
    public String getSymbol() {
        return this.symbol;
    }

    @Override
    public Block create(int xpos, int ypos) {
        Point point = new Point(xpos, ypos);
        return new Block(point, this.width, this.height, this.hitPoints, this.fill, this.stroke, this.defaultFill);
    }
}
