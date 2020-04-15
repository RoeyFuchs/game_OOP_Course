package behavior;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import elements.Ball;
import elements.Block;
import elements.DeathBlock;
import elements.Counter;
import elements.Paddle;
import geometry.Point;
import geometry.Rectangle;
import info.InfoBar;
import info.LivesIndicator;
import info.ScoreIndicator;
import levels.LevelInformation;
import specialscreen.CountdownAnimation;
import specialscreen.PauseScreen;

import java.awt.Color;
import java.util.List;
import java.util.Random;

/**
 * The game - including the game environment and the sprite collection.
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GameFlow gameFlow;
    private Paddle pad;
    private HitListener listen;
    private HitListener listenDeath;
    private ScoreTrackingListener scoreTrackingListener;
    private Counter numOfBlocksCounter;
    private Counter numOfBallsCounter;
    private Counter score;
    private Counter numOfLives;
    private InfoBar infoBar;
    private AnimationRunner runner;
    private boolean running;
    private LevelInformation lvl;

    private biuoop.KeyboardSensor keyboard;

    private static final int DEFAULT_WIDTH_END = 800;
    private static final int DEFAULT_HEIGHT_END = 600;

    private static final double WIDTH_OF_BLOCK = 70;
    private static final double HIGHT_OF_BLOCK = 30;

    private static final double WIDTH_OF_BORDER = 25;


    private final Point ballStart = new Point(DEFAULT_WIDTH_END / 2, 560);
    private static final int BALL_SPEED = 10;

    /**
     * constructor.
     *
     * @param lvl - the level information.
     * @param gameFlow - the game flow
     */
    public GameLevel(LevelInformation lvl, GameFlow gameFlow) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.lvl = lvl;
        this.gameFlow = gameFlow;
    }

    /**
     * add a collidable to the game.
     *
     * @param c - the collidable
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * add a sprite to the game.
     *
     * @param s - the sprite
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }


    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle) and add them to the game.
     */
    public void initialize() {
        this.runner = this.gameFlow.getAnimationRunner();
        this.keyboard = this.gameFlow.getKeyboardSensor();
        this.score = this.gameFlow.getScore();
        this.numOfLives = this.gameFlow.getNumOfLives();

        this.listen = new BlockRemover(this);
        this.listenDeath = new BallRemover(this);
        this.scoreTrackingListener = new ScoreTrackingListener(this);
        this.numOfBlocksCounter = new Counter();
        this.numOfBallsCounter = new Counter();
        this.addSprite(this.lvl.getBackground());
        createBorders();
        createBlocks();

        this.infoBar = new InfoBar(this);
        this.infoBar.addToGame(this);
        LivesIndicator livesInd = new LivesIndicator();
        livesInd.addToGame(this);
        ScoreIndicator scoreInd = new ScoreIndicator(this);
        scoreInd.addToGame(this);


    }


    /**
     * play the game -- start the animation loop.
     */
    public void playOneTurn() {
        cretePaddle();
        createBall(this.lvl.numberOfBalls(), this.lvl.initialBallVelocities());
        this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        this.running = true;
        this.runner.run(this);
    }

    /**
     * create blocks.
     */
    public void createBlocks() {
        Color randomColor;
        for (int i = 0; i < this.lvl.blocks().size(); i++) {
            randomColor = this.getRandomColor();
            Block b = this.lvl.blocks().get(i);
            b.addToGame(this);
            b.addHitListener(this.listen);
            this.numOfBlocksCounter.increase(1);
        }
    }


    /**
     * create border to the game.
     */
    public void createBorders() {
        //create top border
        Point topPoint = new Point(0, 0);
        //multiply because the info bar.
        Block topBorder = new Block(topPoint, DEFAULT_WIDTH_END, WIDTH_OF_BORDER * 2);
        topBorder.addToGame(this);
        //create left border
        Point leftPoint = new Point(0, 0);
        Block leftBorder = new Block(leftPoint, WIDTH_OF_BORDER, DEFAULT_WIDTH_END);
        leftBorder.addToGame(this);
        //create right border
        Point rightPoint = new Point(DEFAULT_WIDTH_END - WIDTH_OF_BORDER, 0);
        Block rightBorder = new Block(rightPoint, WIDTH_OF_BORDER, DEFAULT_HEIGHT_END);
        rightBorder.addToGame(this);
        //create bottom border
        Point bottomPoint = new Point(0, DEFAULT_HEIGHT_END);
        DeathBlock bottomBorder = new DeathBlock(bottomPoint, DEFAULT_WIDTH_END, 0);
        bottomBorder.addToGame(this);
        bottomBorder.addHitListener(this.listenDeath);
    }


    /**
     * create a ball number 1.
     *
     * @param numOfBalls - number of balls
     * @param speedList list of the ball speed
     */
    public void createBall(int numOfBalls, List<Velocity> speedList) {
        for (int i = 0; i < numOfBalls; i++) {
            Ball ball = new Ball(ballStart, 5, getRandomColor());
            ball.setVelocity(speedList.get(i));
            ball.addToGame(this);
            this.numOfBallsCounter.increase(1);
        }
    }

    /**
     * create a pedal to the game.
     */
    public void cretePaddle() {
        Point p = new Point((DEFAULT_WIDTH_END / 2) - (this.lvl.paddleWidth() / 2),
                DEFAULT_HEIGHT_END - HIGHT_OF_BLOCK);
        Paddle paddle = new Paddle(new Rectangle(p, this.lvl.paddleWidth(), 25), this.lvl.paddleSpeed());
        paddle.addToGame(this);
        paddle.setColor(getRandomColor());
        this.pad = paddle;
    }

    /**
     * return the environment of the game.
     *
     * @return the environment
     */
    public GameEnvironment getEnvironment() {
        return this.environment;
    }

    /**
     * remove a collidable from the game.
     *
     * @param c the collidable
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * remove a sprite from the game.
     *
     * @param s the sprite
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * get the counter of the blocks.
     *
     * @return the blocks counter
     */
    public Counter getBlocksCounter() {
        return this.numOfBlocksCounter;
    }

    /**
     * get the counter of the balls.
     *
     * @return the balls counter
     */
    public Counter getBallsCounter() {
        return this.numOfBallsCounter;
    }

    /**
     * get the score listener.
     *
     * @return the score listener
     */
    public ScoreTrackingListener getScoreListen() {
        return this.scoreTrackingListener;
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


    /**
     * get peddle start point.
     *
     * @return point of pedlle start
     */
    public Point getPedStart() {
        return this.pad.getCollisionRectangle().getUpperLeft();
    }


    /**
     * get the default width end.
     *
     * @return the width end
     */
    public int getDefaultWidthEnd() {
        return DEFAULT_WIDTH_END;
    }

    /**
     * get the default height end.
     *
     * @return the height end
     */
    public int getDefaultHeightEnd() {
        return DEFAULT_HEIGHT_END;
    }

    /**
     * get the score counter.
     *
     * @return the scorecounter
     */
    public Counter getScore() {
        return this.score;
    }

    /**
     * get the lives counter.
     *
     * @return the lives counter
     */
    public Counter getLives() {
        return this.numOfLives;
    }

    /**
     * get the info bar of the game.
     *
     * @return the info bar
     */
    public InfoBar getInfoBar() {
        return this.infoBar;
    }

    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        final int pointForWin = 100;
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY,
                                                            new PauseScreen()));
        }
        if (this.numOfBlocksCounter.getValue() == 0) {
            this.score.increase(pointForWin);
            this.pad.removeFromGame(this);
            this.running = false;
        }
        if (this.numOfBallsCounter.getValue() == 0) {
            this.pad.removeFromGame(this);
            this.running = false;
        }
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed(dt);
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }


    /**
     * get the game runner.
     *
     * @return the game runner
     */
    public AnimationRunner getRunner() {
        return runner;
    }

    /**
     * get the level name.
     * @return the level name
     */
    public String getLevelName() {
        return this.lvl.levelName();
    }

    /**
     * get the width of border.
     * @return the width of the border
     */
    public double getWidthOfBorder() {
        return WIDTH_OF_BORDER;
    }

}