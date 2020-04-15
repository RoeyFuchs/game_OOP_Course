package levelsio;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * this class will read the blocks file and create Block Build class.
 */
public class BlocksFile {
    private Reader reader;
    private BlockDefault defaultBlock;
    private List<BlockBuild> blocks;
    private List<SpacerBuild> spacer;
    private BlocksFromSymbolsFactory factory;

    /**
     * constructor.
     * @param reader of the file
     */
    public BlocksFile(Reader reader) {
        this.reader = reader;
        this.blocks = new ArrayList<>();
        this.spacer = new ArrayList<>();
        this.factory = new BlocksFromSymbolsFactory();
    }

    /**
     * read the data from a file.
     * @return a blocks factory
     * @throws IOException in a case of something wrong
     */
    public BlocksFromSymbolsFactory getBlocksInfo() throws IOException {
        BufferedReader inputStream = new BufferedReader(this.reader);
        String line;
        while ((line = inputStream.readLine()) != null) {
            this.mapString(line);
        }
        inputStream.close();
        return this.factory;
    }

    /**
     * using this method to send the string to the correct method.
     *
     * @param str the string
     */
    public void mapString(String str) {
        if (str.startsWith("#")) {
            return;
        }
        if (str.startsWith("bdef")) {
            this.bdef(str);
            return;
        }
        if (str.startsWith("sdef")) {
            this.sdef(str);
            return;
        }
        if (str.startsWith("default")) {
            this.defaultSetting(str);
            return;
        }

    }

    /**
     * create a BlockBuild with the input (by file) setting.
     * @param str the setting
     */
    public void bdef(String str) {
        BlockBuild blockBuild = new BlockBuild();
        str = this.getPureDataString(str);
        String keyValue;
        String key;
        String value;
        String strTemp;
        while (str.length() > 0) {
            keyValue = this.getKeyValue(str);
            value = this.getValue(keyValue);
            key = this.getKey(keyValue);
            this.bdefMap(key, value, blockBuild);
            strTemp = str;
            str = this.getPureDataString(str);
            if (strTemp.equals(str)) {
                break;
            }
        }
        if (this.defaultBlock != null) {
            blockBuild.completeBlock(this.defaultBlock);
        }
        if (!blockBuild.checkIfAllComplete()) {
            System.out.println("Error - Not all the block setting are set.");
        }
        this.factory.getBlockCreators().put(blockBuild.getSymbol(), blockBuild);
        this.blocks.add(blockBuild);
    }

    /**
     * create a spacer with the input (by file) setting.
     * @param str the setting
     */
    public void sdef(String str) {
        SpacerBuild spacerBuild = new SpacerBuild();
        str = this.getPureDataString(str);
        String keyValue;
        String key;
        String value;
        String strTemp;
        while (str.length() > 0) {
            keyValue = this.getKeyValue(str);
            value = this.getValue(keyValue);
            key = this.getKey(keyValue);
            this.spacerMap(key, value, spacerBuild);
            strTemp = str;
            str = this.getPureDataString(str);
            if (strTemp.equals(str)) {
                break;
            }
        }
        this.factory.getSpacerWidths().put(spacerBuild.getSymbol(), spacerBuild.getWidth());
        this.spacer.add(spacerBuild);

    }

    /**
     * map to chose the right method for the spacer.
     * @param key the key
     * @param value the value
     * @param spacerBuild the spacer
     */
    public void spacerMap(String key, String value, SpacerBuild spacerBuild) {
        if (key.startsWith("symbol")) {
            spacerBuild.setSymbol(value);
            return;
        }
        if (key.startsWith("width")) {
            spacerBuild.setWidth(Integer.parseInt(value));
            return;
        }

    }

    /**
     * map to chose the right method for the blocks.
     * @param key the key
     * @param value the value
     * @param block the block build
     */
    public void bdefMap(String key, String value, BlockBuild block) {
        if (key.startsWith("symbol")) {
            block.setSymbol(value);
            return;
        }
        if (key.startsWith("width")) {
            block.setWidth(Integer.parseInt(value));
            return;
        }
        if (key.startsWith("hit_points")) {
            block.setHitPoints(Integer.parseInt(value));
            return;
        }
        if (key.startsWith("height")) {
            block.setHeight(Integer.parseInt(value));
            return;
        }
        if (key.startsWith("fill-")) {
            block.addFill(key.substring(5), value);
            return;
        }
        if (key.startsWith("fill")) {
            block.setDefaultFill(value);
            return;
        }
        if (key.startsWith("stroke")) {
            block.setStroke(value);
            return;
        }
    }

    /**
     * create a default setting to the blocks.
     * @param str string with data
     */
    public void defaultSetting(String str) {
        BlockDefault blockDefault = new BlockDefault();
        str = this.getPureDataString(str);
        String keyValue;
        String key;
        String value;
        String strTemp;
        while (str.length() > 0) {
            keyValue = this.getKeyValue(str);
            value = this.getValue(keyValue);
            key = this.getKey(keyValue);
            this.defaultMap(key, value, blockDefault);
            strTemp = str;
            str = this.getPureDataString(str);
            if (strTemp.equals(str)) {
                break;
            }
        }
        this.defaultBlock = blockDefault;
    }

    /**
     * map to chose the right method.
     * @param key the key
     * @param value the value
     * @param block the block build
     */
    public void defaultMap(String key, String value, BlockDefault block) {
        if (key.startsWith("width")) {
            block.setWidth(Integer.parseInt(value));
            return;
        }
        if (key.startsWith("hit_points")) {
            block.setHitPoints(Integer.parseInt(value));
            return;
        }
        if (key.startsWith("height")) {
            block.setHeight(Integer.parseInt(value));
            return;
        }
        if (key.startsWith("fill-")) {
            block.addFill(key.substring(5), value);
            return;
        }
        if (key.startsWith("fill")) {
            block.setDefaultFill(value);
            return;
        }
        if (key.startsWith("stroke")) {
            block.setStroke(value);
            return;
        }
    }

    /**
     * clear the first word (bdef, defaulet, sdef).
     * @param str the string.
     * @return the string without the first word
     */
    public String getPureDataString(String str) {
        int i;
        i = str.indexOf(" ") + 1;
        return str.substring(i);
    }

    /**
     * seprate the key:value from the string.
     * @param str string
     * @return the key:value
     */
    public String getKeyValue(String str) {
        int i;
        i = str.indexOf(" ");
        if (i == -1) {
            return str;
        }
        return str.substring(0, i);
    }

    /**
     * get the key.
     * @param str the string
     * @return the key
     */
    public String getKey(String str) {
        int i;
        i = str.indexOf(":");
        return str.substring(0, i);
    }
    /**
     * get the value.
     * @param str the string
     * @return the value
     */
    public String getValue(String str) {
        int i;
        i = str.indexOf(":") + 1;
        return str.substring(i);
    }

}
