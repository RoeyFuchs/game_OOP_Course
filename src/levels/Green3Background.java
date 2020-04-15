package levels;
import biuoop.DrawSurface;

import java.awt.Color;

import behavior.Sprite;
import behavior.SpriteCollection;
import geometry.Rectangle;
import geometry.FullCircle;
import geometry.Point;
import behavior.GameLevel;

/**
 * green 3 level background.
 */
public class Green3Background implements Sprite {

    private SpriteCollection collection;

    /**
     * constructor - as requested.
     */
    public Green3Background() {
        this.collection = new SpriteCollection();
        Point startBuild = new Point(100, 400);
        int spaceBetweenWinAndBuild = 10;
        int windowsWidth = 15;
        int windowsHeight = 15;
        int spaceBetweenWin = 30;
        int buildingWidth = 120;
        int buildingHeight = 250;

        this.collection.addSprite(new Rectangle(0, 0, 800, 600, new Color(165, 152, 245)));
        this.collection.addSprite(new Rectangle(startBuild, buildingWidth, buildingHeight, Color.BLACK));
        //Windows create
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                Rectangle rec = new Rectangle(spaceBetweenWinAndBuild + startBuild.getX() + (spaceBetweenWin * j),
                        spaceBetweenWinAndBuild + startBuild.getY() + (spaceBetweenWin * i),
                        windowsWidth, windowsHeight, Color.WHITE);
                this.collection.addSprite(rec);
            }
        }
        Rectangle smallerBuild = new Rectangle(startBuild.getX() + (buildingWidth / 4),
                                               startBuild.getY() - buildingHeight * 0.5,
                                                buildingWidth * 0.5, buildingHeight * 0.5, Color.DARK_GRAY);
        this.collection.addSprite(smallerBuild);
        int anteneLenght = 150;
        Rectangle smallestBuild = new Rectangle(smallerBuild.getUpperLeft().getX() + smallerBuild.getWidth() * 0.25,
                smallerBuild.getUpperLeft().getY() - anteneLenght, buildingWidth * 0.25, anteneLenght, Color.WHITE);
        this.collection.addSprite(smallestBuild);
        Point center = new Point(smallestBuild.getUpperLeft().getX() + (smallestBuild.getWidth() / 2),
                                    smallestBuild.getUpperLeft().getY());
        this.collection.addSprite(new FullCircle(center, 20, Color.YELLOW));
        this.collection.addSprite(new FullCircle(center, 10, Color.RED));
        this.collection.addSprite(new FullCircle(center, 5, Color.WHITE));
            }

    @Override
    public void drawOn(DrawSurface d) {
        this.collection.drawAllOn(d);

    }

    @Override
    public void timePassed(double dt) {
        return;
    }

    @Override
    public void addToGame(GameLevel gameLevel) {
        return;
    }
}
