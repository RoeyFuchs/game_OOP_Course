package specialscreen;

import behavior.Animation;
import biuoop.DrawSurface;
import info.HighScoresTable;

import java.awt.Color;
import java.io.File;

/**
 * the high score animation show the high score table.
 */
public class HighScoresAnimation implements Animation {
    private HighScoresTable highScoresTable;
    /**
     * constructor.
     *
     * @param scores the high score table
     */
    public HighScoresAnimation(HighScoresTable scores) {
        this.highScoresTable = scores;
    }


    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        this.update();
        d.setColor(Color.BLACK);
        d.drawText(250, 50, "High Score:", 50);
        d.setColor(Color.RED);
        d.drawText(150, 90, "Player", 40);
        d.drawText(500, 90, "Score", 40);
        for (int i = 0; i < this.highScoresTable.getHighScores().size(); i++) {
            Integer score = this.highScoresTable.getHighScores().get(i).getScore();
            d.setColor(Color.blue);
            d.drawText(150, 125 + (i * 50), this.highScoresTable.getHighScores().get(i).getName(), 35);
            d.drawText(500, 125 + (i * 50), score.toString(), 35);
        }
    }

    @Override
    public boolean shouldStop() {
        return false;
    }

    /**
     * update the high score table.
     */
    public void update() {
        File file = new File(HighScoresTable.getFileName());
        try {
            this.highScoresTable = HighScoresTable.loadFromFile(file);
        } catch (Exception error) {
            System.out.println("High Score update Failed");
        }
    }


}