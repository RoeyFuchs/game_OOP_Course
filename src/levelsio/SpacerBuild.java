package levelsio;

/**
 * build a spacer.
 */
public class SpacerBuild {
        private String symbol;
    private int width;

    /**
     * constructor.
     */
    public SpacerBuild() {
        this.symbol = null;
    }

    /**
     * get the symbol of the spacer.
     *
     * @return the symbol
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * get the width of the spacer.
     *
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * set the symbol.
     * @param sym the symbol
     */
    public void setSymbol(String sym) {
        this.symbol = sym;
    }

    /**
     * set the width.
     * @param wid the width
     */
    public void setWidth(int wid) {
        this.width = wid;
    }


}
