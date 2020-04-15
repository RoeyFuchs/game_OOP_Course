package levels;
import java.awt.Color;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.TreeMap;
import behavior.Velocity;
import behavior.Sprite;
import geometry.Point;
import elements.Block;

/**
 * Final Four Level.
 */
public class FinalFourLevel implements LevelInformation {

    @Override
    public int numberOfBalls() {
        return 3;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new ArrayList<>();
        list.add(Velocity.fromAngleAndSpeed(-45, 100));
        list.add(Velocity.fromAngleAndSpeed(0, 100));
        list.add(Velocity.fromAngleAndSpeed(45, 100));
        return list;
    }

    @Override
    public int paddleSpeed() {
        return 50;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }

    @Override
    public String levelName() {
        return "Final Four!";
    }

    @Override
    public Sprite getBackground() {
        return new FinalFourLevelBackground();
    }

    @Override
    public List<Block> blocks() {
        List<Block> list = new ArrayList<>();
        int blockHight = 30;
        int blockWidth = 45;
        Map<Integer, Color> colorMap = new TreeMap<>();
        colorMap.put(0, Color.RED);
        colorMap.put(1, Color.ORANGE);
        colorMap.put(2, Color.PINK);
        colorMap.put(3, Color.YELLOW);
        colorMap.put(4, Color.GREEN);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 15; j++) {
                Point startBlock = new Point(700 - (j * blockWidth), 150 + (i * blockHight));
                list.add(new Block(startBlock, blockWidth, blockHight, 1, colorMap.get(i)));
            }
        }
        return list;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 75;
    }

    @Override
    public void setBackGround(Block block) {
        return;
    }
}
