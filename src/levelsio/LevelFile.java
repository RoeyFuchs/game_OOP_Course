package levelsio;


import behavior.Velocity;
import levels.LevelInformation;


import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * getting a level information from a file.
 */
public class LevelFile {
    private LevelBuild levelBuild;
    private BufferedReader reader;
    private List<LevelInformation> levelsInfo;

    /**
     * constructor.
     *
     * @param readIO the reader
     */
    public LevelFile(Reader readIO) {
        this.levelsInfo = new ArrayList<>();
        this.reader = new BufferedReader(readIO);
    }

    /**
     * read the data from a file.
     * @throws IOException in a case of something wrong
     */
    public void getLevelsInfo() throws IOException {
        String line;
        //ignore the first line with the max size
        while ((line = this.reader.readLine()) != null) {
            this.mapString(line);
        }
        this.reader.close();
    }

    /**
     * using this method to send the string to the correct method.
     *
     * @param str the string
     */
    public void mapString(String str) {
        if (str.startsWith("START_LEVEL")) {
            this.createNewLevel();
            return;
        }
        if (str.startsWith("level_name")) {
            this.levelName(str);
            return;
        }
        if (str.startsWith("ball_velocities")) {
            this.ballVelocity(str);
            return;
        }
        if (str.startsWith("paddle_speed")) {
            this.paddleSpeed(str);
            return;
        }
        if (str.startsWith("paddle_width")) {
            this.paddleWidth(str);
            return;
        }
        if (str.startsWith("num_blocks")) {
            this.numOfBlocks(str);
            return;
        }
        if (str.startsWith("block_definitions")) {
            this.blockDef(str);
            return;
        }
        if (str.startsWith("blocks_start_x")) {
            this.blockStartX(str);
            return;
        }
        if (str.startsWith("blocks_start_y")) {
            this.blockStartY(str);
            return;
        }
        if (str.startsWith("row_height")) {
            this.heightRow(str);
            return;
        }
        if (str.startsWith("background")) {
            this.background(str);
            return;
        }
        if (str.startsWith("START_BLOCKS")) {
            this.blocksStart();
        }
        if (str.startsWith("END_LEVEL")) {
            this.closeLevel();
        }

    }

    /**
     * add the level name.
     * @param str the string
     */
    public void levelName(String str) {
        str = this.getPureDataString(str);
        this.levelBuild.setLevelName(str);
    }



    /**
     * add the velocity.
     * @param str the string
     */
    public void ballVelocity(String str) {
        String temp;
        str = this.getPureDataString(str);
        //find how many velocity we have
        int counter = str.split(" ", -1).length - 1;
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i <= counter; i++) {
            //using temp to the seprate each velocety
            if (i != counter) {
                //char 32 - space
                temp = str.substring(0, str.indexOf(32));
                str = str.substring(temp.length() + 1, str.length());
            } else {
                temp = str;
            }
            stringList.add(temp);
        }

        List<Velocity> velocityList = new ArrayList<>();
        String a;
        String b;
        int index;
        //convert from string to velocity
        for (int i = 0; i < stringList.size(); i++) {
            index = stringList.get(i).indexOf(',');
            a = stringList.get(i).substring(0, index);
            b = stringList.get(i).substring(index + 1);
            velocityList.add(Velocity.fromAngleAndSpeed(Double.parseDouble(a), Double.parseDouble(b)));
        }
        this.levelBuild.setBallsVelocity(velocityList);
    }

    /**
     * add the paddle speed.
     * @param str the string
     */
    public void paddleSpeed(String str) {
        str = this.getPureDataString(str);
        this.levelBuild.setPaddleSpeed(Integer.parseInt(str));
    }

    /**
     * add the paddle width.
     * @param str the string
     */
    public void paddleWidth(String str) {
        str = this.getPureDataString(str);
        this.levelBuild.setPaddleWidth(Integer.parseInt(str));
    }

    /**
     * add number of blocks.
     * @param str the string
     */
    public void numOfBlocks(String str) {
        str = this.getPureDataString(str);
        this.levelBuild.setNumOfBlocks(Integer.parseInt(str));
    }

    /**
     * add the definitions blocks file.
     * @param str the string
     */
    public void blockDef(String str) {
        str = this.getPureDataString(str);
        File file = new File(str);
        this.levelBuild.setBlockDefFile(file);
    }

    /**
     * set the block start x.
     * @param str the string
     */
    public void blockStartX(String str) {
        str = this.getPureDataString(str);
        this.levelBuild.setBlockStartX(Integer.parseInt(str));
    }

    /**
     * set the block start y.
     * @param str the string
     */
    public void blockStartY(String str) {
        str = this.getPureDataString(str);
        this.levelBuild.setBlockStartY(Integer.parseInt(str));
    }

    /**
     * set the height row.
     * @param str the string
     */
    public void heightRow(String str) {
        str = this.getPureDataString(str);
        this.levelBuild.setRowHeight(Integer.parseInt(str));
    }

    /**
     * getting the background (image or color).
     * @param str the string
     */
    public void background(String str) {
        str = this.getPureDataString(str);
        this.levelBuild.setBackGroundString(str);
        //if the background is a image
        if (str.startsWith("image")) {
            File file = new File(str.substring(6, str.length() - 1));
            this.levelBuild.setBackgroundFile(file);
            return;
        }
        //else - the background is a color
        //remove the leading "color"
        str = str.substring(6);
        //if the color is in RGB values
        if (str.startsWith("RGB")) {
            //remove the "RGB" and the "))"
            str = str.substring(4, str.length() - 2);
            //find the red, green, and blue values, that seprated by ","
            int index = str.indexOf(',');
            int red = Integer.parseInt(str.substring(0, index));
            str = str.substring(index + 1);
            index = str.indexOf(',');
            int green = Integer.parseInt(str.substring(0, index));
            str = str.substring(index + 1);
            int blue = Integer.parseInt(str);
            this.levelBuild.setBackgroundColor(new Color(red, green, blue));
            return;
        }
        //else - the color is defind by his name
        str = str.substring(0, str.length() - 1);
        Color color = new Color(0, 0,  0);
        try {
            //using field to using color static method
            Field field = Class.forName("java.awt.Color").getField(str);
            color = (Color) field.get(null);
            this.levelBuild.setBackgroundColor(color);
        } catch (Exception error) {
            System.out.println("Error levelfile background color");
        }
    }

    /**
     * set the factory and make the blocks.
     */
    public void blocksStart() {
        this.levelBuild.blocksStart();
        this.levelBuild.blocksCreate(this.reader);
        }



    /**
     * create new level.
     */
    public void createNewLevel() {
        this.levelBuild = new LevelBuild();
    }

    /**
     * finish to build the level, and add it to the list.
     */
    public void closeLevel() {
        this.levelsInfo.add(this.levelBuild.closeLevel());
    }

    /**
     * get the levels list.
     * @return the level list
     */
    public List<LevelInformation> getLevelsList() {
        return this.levelsInfo;
    }

    /**
     * clear the text before the ":".
     * @param str the string.
     * @return the string without the start
     */
    public String getPureDataString(String str) {
        int i;
        i = str.indexOf(":") + 1;
        return str.substring(i);
    }




}
