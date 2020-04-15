package behavior;

import elements.Ball;
import geometry.Point;
import geometry.Rectangle;

/**
 * Collidable interface.
 */
public interface Collidable {
    /**
     * Return the "collision shape" of the object.
     * @return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    // Notify the object that we collided with it at collisionPoint with
    // a given velocity.
    // The return is the new velocity expected after the hit (based on
    // the force the object inflicted on us).

    /**
     * return the velocity of the ball after it this collidable.
     * @param collisionPoint the point of the occur
     * @param currentVelocity the current velocity of the ball
     * @param hitter the ball the hit the collidable
     * @return new velocity
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}