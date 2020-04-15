package levels;

import behavior.Sprite;
import behavior.Velocity;
import elements.Block;

import java.util.List;

/**
 * create a level information by a data of a file.
 */
public class LevelByFile implements LevelInformation {
    private int numberOfBalls;
    private List<Velocity> ballsVelocities;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private List<Block> blocks;
    private Sprite backGround;
    private int blocksToRemove;

    /**
     * constructor.
     * @param name the level name
     * @param balls the balls velocities
     * @param padSpeed the paddle speed
     * @param padWid the paddle width
     * @param ballsNum how many balls
     * @param blocksList the blocks list
     * @param numOfBlocks how many blocks
     */
    public LevelByFile(String name, List<Velocity> balls, int padSpeed, int padWid, int ballsNum,
                       List<Block> blocksList, int numOfBlocks) {
        this.levelName = name;
        this.ballsVelocities = balls;
        this.paddleSpeed = padSpeed;
        this.paddleWidth = padWid;
        this.numberOfBalls = ballsNum;
        this.blocks = blocksList;
        this.blocksToRemove = numOfBlocks;
    }


    @Override
    public int numberOfBalls() {
        return this.numberOfBalls;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return this.ballsVelocities;
    }

    @Override
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    @Override
    public int paddleWidth() {
        return this.paddleWidth;
    }

    @Override
    public String levelName() {
        return this.levelName;
    }

    @Override
    public Sprite getBackground() {
        return this.backGround;
    }

    @Override
    public List<Block> blocks() {
        return this.blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.blocksToRemove;
    }

    @Override
    public void setBackGround(Block block) {
        this.backGround = block;
    }
}
