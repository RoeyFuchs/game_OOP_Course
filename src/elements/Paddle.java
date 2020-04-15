package elements;

import behavior.Collidable;
import behavior.GameLevel;
import behavior.Sprite;
import behavior.Velocity;
import biuoop.DrawSurface;
import geometry.Point;
import geometry.Rectangle;

import java.awt.Color;

/**
 * the paddle class - "the player".
 */
public class Paddle implements Sprite, Collidable {
    //7 is the default.
    private int speed = 7;

    private Rectangle rectangle;
    private biuoop.KeyboardSensor keyboard;

    private Double region1;
    private Double region2;
    private Double region3;
    private Double region4;
    private Double region5;
    private Double regionLength;
    private GameLevel gameLevelCurt;

    /**
     * constructor.
     *
     * @param rec a rectangle as paddle
     */
    public Paddle(Rectangle rec) {
        this.rectangle = rec;
        this.regionLength = this.getCollisionRectangle().getWidth() / 5;
        updateRegions();
    }

    /**
     * constructor.
     *
     * @param rec   a rectangle as paddle
     * @param speed - the speed of the paddle
     */
    public Paddle(Rectangle rec, int speed) {
        this.rectangle = rec;
        this.regionLength = this.getCollisionRectangle().getWidth() / 5;
        updateRegions();
        this.speed = speed;
    }

    /**
     * move the paddle to the left.
     *
     * @param dt of seconds passed
     */
    public void moveLeft(double dt) {
        Color color = this.rectangle.getColor();
        Point topLeft = new Point(this.getCollisionRectangle().getUpperLeft().getX() - (speed * dt),
                this.getCollisionRectangle().getUpperLeft().getY());
        this.rectangle = new Rectangle(topLeft, this.getCollisionRectangle().getWidth(),
                this.getCollisionRectangle().getHeight());
        this.rectangle.setColor(color);
        updateRegions();
    }

    /**
     * mode the paddle to the right.
     * @param dt mount of seconds passed
     */
    public void moveRight(double dt) {
        Color color = this.rectangle.getColor();
        Point topLeft = new Point(this.getCollisionRectangle().getUpperLeft().getX() + (speed * dt),
                this.getCollisionRectangle().getUpperLeft().getY());
        this.rectangle = new Rectangle(topLeft, this.getCollisionRectangle().getWidth(),
                this.getCollisionRectangle().getHeight());
        this.rectangle.setColor(color);
        updateRegions();
    }

    /**
     * draw the paddle.
     *
     * @param d surface
     */
    public void drawOn(DrawSurface d) {
        this.rectangle.drawOn(d);

    }

    @Override
    public void timePassed(double dt) {
        dt = 1 / dt;
        if (this.keyboard.isPressed(keyboard.LEFT_KEY) && !isInEndOfScreenLeft()) {
            this.moveLeft(dt);
        }
        if (this.keyboard.isPressed(keyboard.RIGHT_KEY) && !isInEndOfScreenRight()) {
            this.moveRight(dt);
        }
    }

    /**
     * update the regions of the paddle, depend on his location.
     */
    public void updateRegions() {
        Double widthForRegion = this.regionLength;
        Double startXPoint = this.getCollisionRectangle().getUpperLeft().getX();
        this.region1 = startXPoint;
        this.region2 = startXPoint + widthForRegion;
        this.region3 = startXPoint + (widthForRegion * 2);
        this.region4 = startXPoint + (widthForRegion * 3);
        this.region5 = startXPoint + (widthForRegion * 4);

    }

    /**
     * check if the paddle is in the end of the screen from left.
     *
     * @return true or false
     */
    public boolean isInEndOfScreenLeft() {
        return (this.getCollisionRectangle().getUpperLeft().getX() <= (gameLevelCurt.getWidthOfBorder()));
    }

    /**
     * check if the paddle is in the end of the screen from right.
     *
     * @return true or false
     */
    public boolean isInEndOfScreenRight() {
        return (this.getCollisionRectangle().getUpperLeft().getX() + this.getCollisionRectangle().getWidth()
                >= (gameLevelCurt.getDefaultWidthEnd() - gameLevelCurt.getWidthOfBorder()));
    }

    /**
     * set the received color as paddle color.
     *
     * @param color - the color
     */
    public void setColor(Color color) {
        this.rectangle.setColor(color);
    }


    /**
     * get the rectangle of the paddle.
     *
     * @return the rectangle of the paddle
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }


    /**
     * the hit function - return a new velocity, depend on where the ball hit the paddle.
     *
     * @param collisionPoint  the point of the occur
     * @param currentVelocity the current velocity of the ball
     * @param hitter          - the ball that hit the block
     * @return the new velocity
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        //left side of the paddle
        if (collisionPoint.getX() == this.region1) {
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        }
        //area 1
        if (collisionPoint.getX() > this.region1 && collisionPoint.getX() < this.region2) {
            return Velocity.fromAngleAndSpeed(300, currentVelocity.getTotalSpeed());
        }
        //area 2
        if (collisionPoint.getX() >= this.region2 && collisionPoint.getX() < this.region3) {
            return Velocity.fromAngleAndSpeed(330, currentVelocity.getTotalSpeed());
        }
        //area 3
        if (collisionPoint.getX() >= this.region3 && collisionPoint.getX() < this.region4) {
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        }
        //area 4
        if (collisionPoint.getX() >= this.region4 && collisionPoint.getX() < this.region5) {
            return Velocity.fromAngleAndSpeed(30, currentVelocity.getTotalSpeed());
        }
        //area 5
        if (collisionPoint.getX() >= this.region5 && collisionPoint.getX() < (this.region1
                + this.getCollisionRectangle().getWidth())) {
            return Velocity.fromAngleAndSpeed(60, currentVelocity.getTotalSpeed());
        }
        //the right side of the paddle
        if (collisionPoint.getX() == this.region1 + this.getCollisionRectangle().getWidth()) {
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        }
        return currentVelocity;
    }

    /**
     * Add this paddle to the gameLevel.
     *
     * @param gameLevel the gameLevel to add the paddle to
     */
    public void addToGame(GameLevel gameLevel) {
        this.gameLevelCurt = gameLevel;
        gameLevel.addCollidable(this);
        gameLevel.addSprite(this);
        this.keyboard = gameLevel.getRunner().getGui().getKeyboardSensor();
    }

    /**
     * remove the paddle from the gameLevel.
     *
     * @param gameLevel the gameLevel to remove from
     */
    public void removeFromGame(GameLevel gameLevel) {
        this.gameLevelCurt = null;
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
        this.keyboard = null;
    }

}