package elements;
import java.awt.Color;
import java.util.Random;

import behavior.Sprite;
import behavior.CollisionInfo;
import behavior.Velocity;
import behavior.GameEnvironment;
import behavior.GameLevel;
import biuoop.DrawSurface;
import geometry.Line;
import geometry.Point;

/**
 * Ball class - have properties like his color, radius, and his location. including speed.
 */
public class Ball implements Sprite {
    private static final int DEFAULT_WIDTH_START = 0;
    private static final int DEFAULT_WIDTH_END = 800;
    private static final int DEFAULT_HEIGHT_START = 0;
    private static final int DEFAULT_HEIGHT_END = 600;

    private Point point;
    private Point safePoint = null;
    private int radius;
    private Color color;
    private Velocity vel;
    private int widthStart = DEFAULT_WIDTH_START;
    private int widthEnd = DEFAULT_WIDTH_END;
    private int heightStart = DEFAULT_HEIGHT_START;
    private int heightEnd = DEFAULT_HEIGHT_END;
    private GameEnvironment environment;
    private GameLevel gameLevelCurt;

    /**
     * constructor by (class of) point.
     *
     * @param center - the location of the ball
     * @param r      - radius
     * @param color  - ball color
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.point = center;
        this.radius = r;
        this.color = color;
        this.vel = new Velocity(0, 0);
    }

    /**
     * constructor by location.
     *
     * @param x     - location of the ball (x axis)
     * @param y     - location of the ball (y axis)
     * @param r     - radius
     * @param color - ball color
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        this.point = new Point(x, y);
        this.radius = r;
        this.color = color;
        this.vel = new Velocity(0, 0);
    }

    /**
     * special constructor for random ball.
     *
     * @param widthStart  - width location start
     * @param widthEnd    - width location end
     * @param heightStart - height location start
     * @param heightEnd   - height location end
     * @param radius      - radius
     */
    public Ball(int widthStart, int widthEnd, int heightStart, int heightEnd, int radius) {
        //lowest size 50 - as requested
        final int lowestSize = 50;

        this.point = new Point(widthStart, widthEnd, heightStart, heightEnd);
        this.heightStart = heightStart;
        this.heightEnd = heightEnd;
        this.widthStart = widthStart;
        this.widthEnd = widthEnd;
        this.radius = radius;
        setRandomColor();
        //give the ball speed by his size. 50 or more - the lowest.
        int speed;
        if (radius >= lowestSize) {
            speed = 1;
        } else {
            speed = lowestSize - radius;
        }
        //give the ball velocity and direction
        Random rand = new Random();
        //360 - any possible angel
        this.vel = Velocity.fromAngleAndSpeed(rand.nextInt(360), speed);
    }

    /**
     * constructor.
     *
     * @param p           - location of the ball
     * @param r           - radius
     * @param color       - ball color
     * @param widthStart  - width start location
     * @param widthEnd    - width end location
     * @param heightStart - height start location
     * @param heightEnd   - height end location
     */
    public Ball(Point p, int r, java.awt.Color color, int widthStart, int widthEnd, int heightStart, int heightEnd) {
        this.point = p;
        this.radius = r;
        this.color = color;
        //set velocity to zero
        this.vel = new Velocity(0, 0);
        this.widthStart = widthStart;
        this.widthEnd = widthEnd;
        this.heightStart = heightStart;
        this.heightEnd = heightEnd;
    }

    /**
     * find the location on x axis.
     *
     * @return the x location
     */
    public int getX() {
        return (int) this.point.getX();
    }

    /**
     * find the location on y axis.
     *
     * @return the y location
     */
    public int getY() {
        return (int) this.point.getY();
    }

    /**
     * find the radius of the ball.
     *
     * @return the radius
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * find the color of the ball.
     *
     * @return the color
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    //

    /**
     * draw the ball on the given DrawSurface.
     *
     * @param surface - which surface to draw on
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.getColor());
        surface.fillCircle(this.getX(), this.getY(), this.getSize());
    }

    /**
     * set the environment envirnment of the ball.
     *
     * @param gameEnv - the environment environment
     */
    public void setGameEnvirnment(GameEnvironment gameEnv) {
        this.environment = gameEnv;
    }

    /**
     * se velocity to the ball.
     *
     * @param v - the velocity
     */
    public void setVelocity(Velocity v) {
        this.vel = v;
    }

    /**
     * set velocity to the ball.
     *
     * @param dx - how many "steps" on x axis
     * @param dy - how many "steps" on y axis
     */
    public void setVelocity(double dx, double dy) {
        this.vel = new Velocity(dx, dy);
    }

    /**
     * find the velocity of the ball.
     *
     * @return - the velocity
     */
    public Velocity getVelocity() {
        return this.vel;
    }

    /**
     * move a step, by the velocity of the ball.
     * @param dt - the mount of seconds passed
     */
    public void moveOneStep(double dt) {
        dt = 1 / dt;
        CollisionInfo closetsCollision = this.environment.getClosestCollision(this.getVectorFramed(dt));
        //check if the ball is in a block or something else. made that to avoid weird bugs
        if (this.environment.checkIfInside(this.point) != null) {
            this.vel = new Velocity(-this.vel.getDx(), -this.vel.getDy());
            this.point = this.safePoint;
            this.point = this.getVelocity().applyToPoint(this.point, dt);
            return;
        }
        //if the ball free to go
        if (closetsCollision == null) {
            this.point = this.getVelocity().applyToPoint(this.point, dt);
            this.safePoint = this.point;
        } else {
            //get closer to the hit point
            Line collisionVector = new Line(this.point.getX(), this.point.getY(),
                    closetsCollision.collisionPoint().getX(), closetsCollision.collisionPoint().getY());
            Point tempPoint = collisionVector.getPointNearEnd();
            //check that the ball isn't in the paddle.
            this.point = checkIfPaddle(tempPoint);
            this.vel = closetsCollision.collisionObject().hit(this, closetsCollision.collisionPoint(), this.vel);

        }
    }

    /**
     * check if a point is with Y condition of the paddle or more.
     * @param p - the point
     * @return - the point above the paddle (or the original point)
     */
    public Point checkIfPaddle(Point p) {
        Double epsilon = 1.0;
        if (p.getY() >= gameLevelCurt.getPedStart().getY()) {
            return new Point(p.getX(), gameLevelCurt.getPedStart().getY() - epsilon);
        }
        return p;
    }

    /**
     * check if the ball will arrive to the limit of the screen
     * if yes - change to the opposite direction.
     */
    public void limitCheck() {
        double futureX = this.getVelocity().getDx() + point.getX();
        double futureY = this.getVelocity().getDy() + point.getY();
        //check the 4 borders of the screen
        if (futureX + this.radius > this.widthEnd) {
            this.point = new Point(this.widthEnd - this.radius, this.getY());
            this.vel = new Velocity(-this.getVelocity().getDx(), this.getVelocity().getDy());
        }
        if (futureX - this.radius < this.widthStart) {
            this.point = new Point(this.widthStart + this.radius, this.getY());
            this.vel = new Velocity(-this.getVelocity().getDx(), this.getVelocity().getDy());
        }
        if (futureY + this.radius > this.heightEnd) {
            this.point = new Point(this.getX(), this.heightEnd - this.radius);
            this.vel = new Velocity(this.getVelocity().getDx(), -this.getVelocity().getDy());
        }
        if (futureY - this.radius < this.heightStart) {
            this.point = new Point(this.getX(), this.heightStart + this.radius);
            this.vel = new Velocity(this.getVelocity().getDx(), -this.getVelocity().getDy());
        }
    }
    /**
     * get vector - to know where the ball will be in the next time.
     * using *2 to ensure
     *
     * @return a Line of the direction
     */
    public Line getVector() {
        if (this.getY() + this.vel.getDy() > this.getY()) {
            if (this.getX() + this.vel.getDx() > this.getX()) {
                return new Line(this.getX(), this.getY(), this.getX() + (2 * this.vel.getDx()),
                        this.getY() + (2 * this.vel.getDy()));
            } else {
                return new Line(this.getX(), this.getY(), this.getX() + (2 * this.vel.getDx()),
                        this.getY() + (2 * this.vel.getDy()));
            }
        } else {
            if (this.getX() + this.vel.getDx() > this.getX()) {
                return new Line(this.getX(), this.getY(), this.getX() + (2 * this.vel.getDx()),
                        this.getY() + (2 * this.vel.getDy()));
            } else {
                return new Line(this.getX(), this.getY(), this.getX() + (2 * this.vel.getDx()),
                        this.getY() + (2 * this.vel.getDy()));
            }
        }
    }

    /**
     * get the vector, including the dt.
     * @param dt - framed rate
     * @return the vector
     */
    public Line getVectorFramed(double dt) {
        double multiSafeNum = 2;
        if (this.getY() + (this.vel.getDy() * dt) > this.getY()) {
            if (this.getX() + (this.vel.getDx() * dt) > this.getX()) {
                return new Line(this.getX(), this.getY(), this.getX() + (multiSafeNum * this.vel.getDx() * dt),
                        this.getY() + (multiSafeNum * this.vel.getDy() * dt));
            } else {
                return new Line(this.getX(), this.getY(), this.getX() + (multiSafeNum * this.vel.getDx() * dt),
                        this.getY() + (multiSafeNum * this.vel.getDy() * dt));
            }
        } else {
            if (this.getX() + this.vel.getDx() > this.getX()) {
                return new Line(this.getX(), this.getY(), this.getX() + (multiSafeNum * this.vel.getDx() * dt),
                        this.getY() + (multiSafeNum * this.vel.getDy() * dt));
            } else {
                return new Line(this.getX(), this.getY(), this.getX() + (multiSafeNum * this.vel.getDx() * dt),
                        this.getY() + (multiSafeNum * this.vel.getDy() * dt));
            }
        }
    }


    /**
     * set random color to the ball.
     */
    public void setRandomColor() {
        Random rand = new Random();
        //256 - RGB MAP
        int red = rand.nextInt(256);
        int green = rand.nextInt(256);
        int blue = rand.nextInt(256);
        this.color = new Color(red, green, blue);
    }

    @Override
    public void timePassed(double dt) {
        this.moveOneStep(dt);
    }

    /**
     * add the ball to a gameLevel.
     * @param gameLevel the gameLevel to add the sprite to
     */
    public void addToGame(GameLevel gameLevel) {
        this.gameLevelCurt = gameLevel;
        gameLevel.addSprite(this);
        this.setGameEnvirnment(gameLevel.getEnvironment());
    }

    /**
     * remove the ball from the game.
     * @param g the game to remove from
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
        this.gameLevelCurt = null;
        this.setGameEnvirnment(null);
    }

}