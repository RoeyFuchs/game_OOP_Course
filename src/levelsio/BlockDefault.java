package levelsio;


import java.util.Map;
import java.util.TreeMap;

/**
 * save default setting for the block.
 */
public class BlockDefault {


    private int width;
    private int height;
    private int hitPoints;
    private Map<Integer, String> fill;
    private String stroke;
    private String defaultFill;

    /**
     * constructor.
     */
    public BlockDefault() {
        this.fill = new TreeMap<>();
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
     * get the width.
     *
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * get the height.
     *
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * get the hit points.
     *
     * @return the hit points
     */
    public int getHitPoints() {
        return hitPoints;
    }

    /**
     * get the fill lists.
     *
     * @return the fill lists
     */
    public Map<Integer, String> getFill() {
        return fill;
    }

    /**
     * get the stroke.
     *
     * @return the stroke
     */
    public String getStroke() {
        return stroke;
    }

    /**
     * get the default fill.
     *
     * @return the default fill
     */
    public String getDefaultFill() {
        return defaultFill;
    }
}
