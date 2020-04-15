package levels;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import behavior.Velocity;
import behavior.Sprite;
import geometry.Point;
import elements.Block;

/**
 * Green 3 Level.
 */
public class Green3Level implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new ArrayList<>();
        list.add(Velocity.fromAngleAndSpeed(-45, 350));
        list.add(Velocity.fromAngleAndSpeed(45, 350));
        return list;
    }

    @Override
    public int paddleSpeed() {
        return 250;
    }

    @Override
    public int paddleWidth() {
        return 185;
    }

    @Override
    public String levelName() {
        return "Green 3";
    }

    @Override
    public Sprite getBackground() {
        return new Green3Background();
    }

    @Override
    public List<Block> blocks() {
        int blockHight = 30;
        int blockWidth = 50;
        List<Block> list = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            Color clr = getRandomColor();
            for (int j = 1; j + i <= 10; j++) {
                Point startBlock = new Point(750 - (j * blockWidth), 150 + (i * blockHight));
                list.add(new Block(startBlock, blockWidth, blockHight, 1, clr));
            }

        }
        return list;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 0;
    }

    @Override
    public void setBackGround(Block block) {
        return;
    }

    /**
     * random a color.
     *
     * @return random color
     */
    public Color getRandomColor() {
        Random rand = new Random();
        //256 - RGB MAP
        int red = rand.nextInt(256);
        int green = rand.nextInt(256);
        int blue = rand.nextInt(256);
        return new Color(red, green, blue);
    }
}
