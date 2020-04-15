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
 * the level "Wide easy".
 */
public class WideEasyLevel implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 5;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            list.add(Velocity.fromAngleAndSpeed(-60 + (i * 20), 100));
        }
        return list;
    }

    @Override
    public int paddleSpeed() {
        return 25;
    }

    @Override
    public int paddleWidth() {
        return 570;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        return new WideEasyLevelBackground();
    }

    @Override
    public List<Block> blocks() {
        int blockWidth = 60;
        List<Block> list = new ArrayList<>();
        Point start = new Point(40, 300);
        for (int i = 0; i <= 11; i++) {
            Point p = new Point(start.getX() + (i * blockWidth), start.getY());
            list.add(new Block(p, blockWidth, 30, 1, this.getRandomColor()));
        }

        return list;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 11;
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
