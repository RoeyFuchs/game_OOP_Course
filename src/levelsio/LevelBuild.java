package levelsio;

import behavior.Velocity;
import elements.Block;
import geometry.Point;
import geometry.Rectangle;
import levels.LevelByFile;
import levels.LevelInformation;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * using the level build to build a level step by step.
 */
public class LevelBuild {
    private String levelName;
    private List<Velocity> ballsVelocity;
    private int paddleSpeed;
    private int paddleWidth;
    private int numOfBlocks;
    private File blockDefFile;
    private int blockStartX;
    private int blockStartY;
    private int rowHeight;
    private List<Block> blocks;
    private BlocksFromSymbolsFactory factory;



    private String backGroundString;
    private Color backgroundColor;
    private File backgroundFile;


    /**
     * constructor.
     */
    public LevelBuild() {
        this.levelName = null;
        this.ballsVelocity = null;
        this.blocks = new ArrayList<>();
    }

    /**
     * set a name to the level.
     *
     * @param str the name
     */
    public void setLevelName(String str) {
        this.levelName = str;
    }

    /**
     * set the balls velocity.
     *
     * @param list the list with the velocity
     */
    public void setBallsVelocity(List<Velocity> list) {
        this.ballsVelocity = list;
    }

    /**
     * set the paddle speed.
     *
     * @param speed the speed
     */
    public void setPaddleSpeed(int speed) {
        this.paddleSpeed = speed;
    }

    /**
     * set the paddle width.
     *
     * @param width the speed
     */
    public void setPaddleWidth(int width) {
        this.paddleWidth = width;
    }

    /**
     * set number of blocks.
     *
     * @param num - number of blocks
     */
    public void setNumOfBlocks(int num) {
        this.numOfBlocks = num;
    }

    /**
     * get the block definitions file.
     *
     * @param file the file
     */
    public void setBlockDefFile(File file) {
        this.blockDefFile = file;
    }

    /**
     * set the block start x.
     *
     * @param blockStart the x value
     */
    public void setBlockStartX(int blockStart) {
        this.blockStartX = blockStart;
    }

    /**
     * set the block start y.
     *
     * @param blockStart the y value
     */
    public void setBlockStartY(int blockStart) {
        this.blockStartY = blockStart;
    }

    /**
     * set the background as string.
     * @param str the string
     */
    public void setBackGroundString(String str) {
        this.backGroundString = str;
    }

    /**
     * set the height of the row.
     *
     * @param height the height
     */
    public void setRowHeight(int height) {
        this.rowHeight = height;
    }

    /**
     * set the background color.
     *
     * @param color the color
     */
    public void setBackgroundColor(Color color) {
        this.backgroundColor = color;
    }

    /**
     * set the background image.
     *
     * @param file the image
     */
    public void setBackgroundFile(File file) {
        this.backgroundFile = file;
    }

    /**
     * initialize the block factory.
     */
    public void blocksStart() {
        try {
            InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream(
                    this.blockDefFile.toString());
            InputStreamReader streamReader = new InputStreamReader(inputStream);
            this.factory = BlocksDefinitionReader.fromReader(streamReader);
        } catch (Exception error) {
            System.out.println("Error level build blocks start");
            error.printStackTrace();
        }
    }

    /**
     * Create the blocks by a file.
     *
     * @param reader the reader of the file
     */
    public void blocksCreate(BufferedReader reader) {
        try {
            String line;
            int x = this.blockStartX;
            int y = this.blockStartY;
            String sym;
            while ((line = reader.readLine()) != null) {
                if (line.equals("END_BLOCKS")) {
                    break;
                }
                for (int i = 0; i < line.length(); i++) {
                    //find the symbol in the file
                    sym = String.valueOf(line.charAt(i));
                    if (this.factory.isSpaceSymbol(sym)) {
                        x += this.factory.getSpaceWidth(sym);
                    }
                    //if the symbol is a block symbol, create the block.
                    if (this.factory.isBlockSymbol(sym)) {
                        this.blocks.add(this.factory.getBlock(sym, x, y));
                        x += this.blocks.get(this.blocks.size() - 1).getWidth();
                    }
                }
                y += this.rowHeight;
                x = this.blockStartX;
            }
        } catch (Exception error) {
            System.out.println("Error level build blocksCreate");
            error.printStackTrace();
        }
    }

    /**
     * create the level information.
     * @return level information
     */
    public LevelInformation closeLevel() {
        LevelInformation newLevel = new LevelByFile(this.levelName, this.ballsVelocity, this.paddleSpeed,
                this.paddleWidth, this.ballsVelocity.size(), this.blocks, this.numOfBlocks);
        Point upperLeft = new Point(0, 0);
        Map<Integer, String> map = new TreeMap<>();
        Block backRound = new Block(upperLeft, 800, 600, 1, map,
                null, this.backGroundString);
        //make it uncollidable
        Rectangle rec = new Rectangle(upperLeft, 0, 0);
        backRound.setCollisionRectangle(rec);
        newLevel.setBackGround(backRound);
        return newLevel;
    }


}
