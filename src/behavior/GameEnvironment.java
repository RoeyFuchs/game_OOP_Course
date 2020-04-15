package behavior;

import biuoop.DrawSurface;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;

import java.util.ArrayList;
import java.util.List;

/**
 * the game environment. including a list of te collidable object.
 */
public class GameEnvironment {
    private List<Collidable> collidable;
    /**
     * counstructor.
     */
    public GameEnvironment() {
        this.collidable = new ArrayList<>();
    }

    /**
     * add a collidable to the game environment.
     *
     * @param c - a collidable
     */
    public void addCollidable(Collidable c) {
        this.collidable.add(c);
    }

    /**
     * remove a collidable from the environement.
     * @param c the collidable to remove
     */
    public void removeCollidable(Collidable c) {
        this.collidable.remove(c);
    }

    /**
     * find the collision that the trajectory will occur.
     *
     * @param trajectory - the trajectory of the ball
     * @return collision info
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        return trajectory.closestIntersectionToStartOfLineWithArrayOfCollidables(this.collidable);
    }



    /**
     * chech if the ball is inside a collidable.
     * @param point - the point
     * @return true or false
     */
    public Collidable checkIfInside(Point point) {
        for (int i = 0; i < this.collidable.size(); i++) {
            Rectangle rec = this.collidable.get(i).getCollisionRectangle();
            if (point.getX() > rec.getUpperLeft().getX()
               && point.getX() < (rec.getUpperLeft().getX() + rec.getWidth())
               && point.getY() > rec.getUpperLeft().getY()
               && point.getY() < (rec.getUpperLeft().getY() + rec.getHeight())) {
                return this.collidable.get(i);
            }
        }
        return null;
    }


    /**
     * draw on a surface the all collision.
     *
     * @param surface - the game surface.
     */
    public void drawOn(DrawSurface surface) {
        for (int i = 0; i < collidable.size(); i++) {
            collidable.get(i).getCollisionRectangle().drawOn(surface);
        }
        //draw paddle
    }

}