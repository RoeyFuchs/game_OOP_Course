package elements;

import behavior.Collidable;
import behavior.Sprite;
import behavior.HitListener;
import behavior.HitNotifier;
import behavior.GameLevel;
import behavior.Velocity;
import biuoop.DrawSurface;
import geometry.Point;
import geometry.Rectangle;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * block class.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rectangle;
    private Rectangle collisionRectangle;
    private int hitPoints = 0;
    private List<HitListener> hitListeners;
    private Color color = Color.BLACK;
    private String defaultFill;
    private Color stroke = null;
    private BufferedImage img;
    private Color correctColor;
    private Map<Integer, String> fillMap;


    /**
     * constructor.
     *
     * @param upperLeft   the point up left
     * @param width       the width
     * @param height      the height
     * @param hitPoints   how many hit points
     * @param fill        fill list by hit points
     * @param stroke      stroke color
     * @param defaultFill default fill color
     */
    public Block(Point upperLeft, int width, int height, int hitPoints, Map<Integer, String> fill,
                 String stroke, String defaultFill) {
        this.rectangle = new Rectangle(upperLeft, (double) width, (double) height);
        this.collisionRectangle = this.rectangle;
        this.hitPoints = hitPoints;
        if (fill.size() != 0) {
            this.fillMap = fill;
        } else {
            this.fillMap = new TreeMap<Integer, String>();
        }
        this.defaultFill = defaultFill;
        if (stroke != null) {
            this.stroke = this.getColorByString(stroke);
        }
        this.hitListeners = new ArrayList<>();
        this.editColorAndImg();
    }

    /**
     * counstructor.
     *
     * @param rectangle the rectangle of the block
     */
    public Block(Rectangle rectangle) {
        this.rectangle = rectangle;
        this.rectangle.setColor(Color.GRAY);
        this.collisionRectangle = rectangle;
        this.hitListeners = new ArrayList<>();
        this.editColorAndImg();
    }

    /**
     * counstructor.
     *
     * @param leftTop - left point of the block
     * @param width   the width of the block
     * @param hight   the hight of the block
     */
    public Block(Point leftTop, double width, double hight) {
        this.rectangle = new Rectangle(leftTop, width, hight);
        this.rectangle.setColor(Color.GRAY);
        this.defaultFill = "Color(GRAY)";
        this.collisionRectangle = this.rectangle;
        this.hitListeners = new ArrayList<>();
        this.fillMap = new TreeMap<Integer, String>();
        this.editColorAndImg();

    }

    /**
     * counstructor.
     *
     * @param leftTop   - left point of the block
     * @param width     the width of the block
     * @param hight     the hight of the block
     * @param hitPoints number of hit
     * @param color     the color of the block
     */
    public Block(Point leftTop, double width, double hight, int hitPoints, Color color) {
        this.rectangle = new Rectangle(leftTop, width, hight);
        this.hitPoints = hitPoints;
        this.color = color;
        this.rectangle.setColor(color);
        this.collisionRectangle = this.rectangle;
        this.hitListeners = new ArrayList<>();
        this.editColorAndImg();
    }

    /**
     * set Hit points to the block.
     *
     * @param points - number of "lives"
     */
    public void setHitPoint(int points) {
        this.hitPoints = points;
    }

    /**
     * set a collision rectangle for the block.
     *
     * @param rec the collision rectangle
     */
    public void setCollisionRectangle(Rectangle rec) {
        this.collisionRectangle = rec;
    }

    /**
     * set a color to the block.
     *
     * @param clr the color
     */
    public void setColor(Color clr) {
        this.color = clr;
        this.rectangle.setColor(clr);
    }

    /**
     * get the rectangle of the block.
     *
     * @return the rectangle
     */
    public Rectangle getCollisionRectangle() {
        return this.collisionRectangle;
    }


    @Override
    public void timePassed(double dt) {
        return;
    }

    /**
     * draw the block.
     *
     * @param d surface - the surface to draw on
     */
    public void drawOn(DrawSurface d) {
        if (this.img != null) {
            d.drawImage((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                    this.img);
        } else {
            d.setColor(this.correctColor);
            d.fillRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                    (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
        }
        if (this.stroke != null) {
            d.setColor(this.stroke);
            d.drawRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                    (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
        }
    }

    /**
     * draw the number of hit points.
     *
     * @param d - the surface to draw on
     */
    public void drawHitPoints(DrawSurface d) {
        final int fontSize = 15;
        String hitPointsToDraw;
        if (this.hitPoints <= 0) {
            hitPointsToDraw = "X";
        } else {
            hitPointsToDraw = Integer.toString(this.hitPoints);
        }
        d.setColor(Color.WHITE);
        d.drawText((int) this.rectangle.getMiddle().getX(), (int) this.rectangle.getMiddle().getY(),
                hitPointsToDraw, fontSize);
    }

    /**
     * add the block to a gameLevel.
     *
     * @param gameLevel the gameLevel to add the sprite to
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addCollidable(this);
        gameLevel.addSprite(this);
    }

    /**
     * return the velocity of an object after hit this block.
     *
     * @param collisionPoint  - where the occur happen.
     * @param currentVelocity ball velocity before the hit
     * @param hitter          - the ball that hit the block
     * @return the new velocity of the ball
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        this.hitPoints -= 1;
        this.notifyHit(hitter);
        if (this.hitPoints != 0) {
            this.editColorAndImg();
        }
        //check if the hit is on the sides of the rectangle (left or right), or on or below the rectangle (up or down)
        if (this.getCollisionRectangle().getBootomBorder().pointOnLine(collisionPoint)
                || this.getCollisionRectangle().getTopBorder().pointOnLine(collisionPoint)) {
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        }
        if (this.getCollisionRectangle().getLeftBorder().pointOnLine(collisionPoint)
                || this.getCollisionRectangle().getRightBorder().pointOnLine(collisionPoint)) {
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        }

        //if the point isn't exactly on the point
        int borderNum = this.getCollisionRectangle().findClosestBorder(collisionPoint);
        if (borderNum == 0 || borderNum == 2) {
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        } else {
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        }
    }

    /**
     * remove this block from the gameLevel.
     *
     * @param gameLevel the gameLevel to remove from
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * notify the all listeners that the block been hit.
     *
     * @param hitter the ball that hit the block
     */
    protected void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * get hit points of the block.
     *
     * @return the hit points
     */
    public int gethitPoints() {
        return this.hitPoints;
    }

    /**
     * get the rectangle of the block.
     *
     * @return the rectangle of the block
     */
    public Rectangle getRectangle() {
        return rectangle;
    }


    /**
     * get color by string.
     *
     * @param str the string
     * @return color
     */
    public Color getColorByString(String str) {
        if (str.startsWith("color(RGB")) {
            //remove the "RGB" and the "))"
            str = str.substring(10, str.length() - 2);
            //find the red, green, and blue values, that seprated by ","
            int index = str.indexOf(',');
            int red = Integer.parseInt(str.substring(0, index));
            str = str.substring(index + 1);
            index = str.indexOf(',');
            int green = Integer.parseInt(str.substring(0, index));
            str = str.substring(index + 1);
            int blue = Integer.parseInt(str);
            return new Color(red, green, blue);
        }
        //else - the color is defind by his name
        str = str.substring(6, str.length() - 1);
        Color clr = new Color(0, 0, 0);
        try {
            //using field to using color static method
            Field field = Class.forName("java.awt.Color").getField(str);
            color = (Color) field.get(null);
            return color;
        } catch (Exception error) {
            System.out.println("Error Block get color by string");
        }
        return null;
    }

    /**
     * get the height of the block.
     *
     * @return the height of the block
     */
    public int getHeight() {
        return (int) this.rectangle.getHeight();
    }

    /**
     * get the width of the block.
     *
     * @return the width of the block
     */
    public int getWidth() {
        return (int) this.rectangle.getWidth();
    }

    /**
     * check if the string is a img address.
     *
     * @param str the string
     * @return true or false
     */
    public boolean isImg(String str) {
        return str.startsWith("image");
    }

    /**
     * set img to the block.
     *
     * @param str the string with the img address
     */
    public void setImg(String str) {
        str = str.substring(6, str.length() - 1);
        BufferedImage image = null;
        try {
            image = ImageIO.read(ClassLoader.getSystemClassLoader().getResourceAsStream(str));
            this.img = image;
        } catch (IOException e) {
            System.out.println("Image load Error");
        }
    }

    /**
     * edit the current color/img by hit points.
     */
    public void editColorAndImg() {
        //if there are not a fill-K
        if (this.fillMap.size() == 0) {
            if (isImg(this.defaultFill)) {
                this.setImg(this.defaultFill);
                this.correctColor = null;
            } else {
                this.correctColor = this.getColorByString(this.defaultFill);
                this.img = null;
            }
            return;
        }
        Integer hitPoint = this.hitPoints;
        //check if we have a special fill to this k

        if (this.fillMap.containsKey(this.hitPoints)) {
            if (this.isImg(this.fillMap.get(this.hitPoints))) {
                this.correctColor = null;
                this.setImg(this.fillMap.get(this.hitPoints));
            } else {
                this.correctColor = this.getColorByString(this.fillMap.get(this.hitPoints));
                this.img = null;
            }
            //if we don't have a special color or img to this K - using the default
        } else {
            if (this.isImg(this.defaultFill)) {
                this.correctColor = null;
                this.setImg(this.defaultFill);
            } else {
                this.correctColor = this.getColorByString(this.defaultFill);
                this.img = null;
            }
        }
    }
}
