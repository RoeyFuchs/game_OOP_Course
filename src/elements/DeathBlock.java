package elements;

import behavior.Velocity;
import geometry.Point;
import geometry.Rectangle;

/**
 * Death block - will use as a death sigh.
 */
public class DeathBlock extends Block {
    /**
     * constructor.
     * @param rectangle the block rectangle
     */
    public DeathBlock(Rectangle rectangle) {
        super(rectangle);
    }

    /**
     * constructor.
     * @param leftTop - the top left point
     * @param width the width of the block
     * @param hight - the hight of the block
     */
    public DeathBlock(Point leftTop, double width, double hight) {
        super(leftTop, width, hight);
    }



    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        super.notifyHit(hitter);
        return currentVelocity;
    }
}
