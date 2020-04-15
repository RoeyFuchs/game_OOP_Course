package behavior;

import geometry.Line;
import geometry.Point;

/**
 *Collision Info. including the point of the occurs and the collision object.
 */
public class CollisionInfo {
    private Point collistionPoint;
    private Collidable collisionObject;

    /**
     * counstructor.
     * @param collistionPoint the occurs point
     * @param collisionObject the collision object
     */
    public CollisionInfo(Point collistionPoint, Collidable collisionObject) {
        this.collistionPoint = collistionPoint;
        this.collisionObject = collisionObject;

    }

    /**
     * get the top border of the Collision.
     * @return a line of the top border
     */
    public Line getTopBorder() {
        return this.collisionObject.getCollisionRectangle().getRectangleBorder().get(0);
    }
    /**
     * get the top right of the Collision.
     * @return a line of the right border
     */
    public Line getRightBorder() {
        return this.collisionObject.getCollisionRectangle().getRectangleBorder().get(1);
    }
    /**
     * get the bottom border of the Collision.
     * @return a line of the bottom border
     */
    public Line getBootomBorder() {
        return this.collisionObject.getCollisionRectangle().getRectangleBorder().get(2);
    }
    /**
     * get the left border of the Collision.
     * @return a line of the left border
     */
    public Line getLeftBorder() {
        return this.collisionObject.getCollisionRectangle().getRectangleBorder().get(3);
    }

    /**
     * get the the point at which the collision occurs.
     * @return the point
     */
    public Point collisionPoint() {
        return this.collistionPoint;
    }
    /**
     * get the collidable object involved in the collision.
     * @return the collidable
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}