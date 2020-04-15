package levels;
import behavior.Velocity;
import behavior.Sprite;
import geometry.Point;
import elements.Block;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * the level "Direct Hit".
 */
public class DirectHitLevel implements LevelInformation {

    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new ArrayList<>();
        list.add(new Velocity(0, -250));
        return list;
    }

    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public int paddleWidth() {
        return 80;
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        return new DirectHitLevelBackground();
    }

    @Override
    public List<Block> blocks() {
        List<Block> list = new ArrayList<>();
        Point p = new Point(370, 135);
        list.add(new Block(p, 60, 30, 1, Color.RED));
        return list;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }

    @Override
    public void setBackGround(Block block) {
        return;
    }
}
