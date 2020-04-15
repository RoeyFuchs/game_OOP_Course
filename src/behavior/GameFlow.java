package behavior;

import biuoop.DialogManager;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import elements.Counter;
import info.HighScoresTable;
import info.ScoreInfo;
import levels.LevelInformation;
import menu.Task;
import specialscreen.HighScoresAnimation;
import specialscreen.LoseScreen;
import specialscreen.WinScreen;

import java.io.File;
import java.io.IOException;
import java.util.List;


/**
 * game flow receive a few game and manage the flow of the game.
 */
public class GameFlow implements Task<Void> {
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private Counter score;
    private Counter numOfLives;
    private HighScoresTable highScoresTable;
    private List<LevelInformation> levels;

    /**
     * constructor.
     * @param ar animation runner
     * @param ks keyboard sensor
     * @param levels levels to run
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, List levels) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.score = new Counter();
        this.numOfLives = new Counter();
        //7 lives is the default
        this.numOfLives.increase(7);
        this.loadHighScore();
        this.levels = levels;
    }

    /**
     * run the levels.
     */
    public void runLevels() {
        for (LevelInformation levelInfo : this.levels) {
            GameLevel level = new GameLevel(levelInfo, this);
            level.initialize();
            while (level.getBlocksCounter().getValue() > 0 && this.numOfLives.getValue() > 0) {
                level.playOneTurn();
            }
            //lose screen
            if (this.numOfLives.getValue() == 0) {
                KeyPressStoppableAnimation lose = new KeyPressStoppableAnimation(this.keyboardSensor,
                        KeyboardSensor.SPACE_KEY, new LoseScreen(this));
                this.animationRunner.run(lose);
                scoreAfterGame();
                break;
            }
        }
        //wining screen
        KeyPressStoppableAnimation win = new KeyPressStoppableAnimation(this.keyboardSensor, KeyboardSensor.SPACE_KEY,
                                                                            new WinScreen(this));
        this.animationRunner.run(win);
        scoreAfterGame();
    }

    /**
     * get the animation ruuner.
     * @return the animation runner
     */
    public AnimationRunner getAnimationRunner() {
        return this.animationRunner;
    }

    /**
     * get the keyboard sensor.
     * @return the keyboard sensor
     */
    public KeyboardSensor getKeyboardSensor() {
        return this.keyboardSensor;
    }

    /**
     * get the score counter.
     * @return the score counter
     */
    public Counter getScore() {
        return this.score;
    }

    /**
     * get number of lives.
     * @return number of lives counter
     */
    public Counter getNumOfLives() {
        return this.numOfLives;
    }

    /**
     * load (or create) a high score table.
     */
    private void loadHighScore() {
        final int sizeOfTable = 10;
        File file = new File(HighScoresTable.getFileName());
        if (file.isFile()) {
            this.highScoresTable = HighScoresTable.loadFromFile(file);
        } else {
            this.highScoresTable = new HighScoresTable(sizeOfTable);
            try {
                this.highScoresTable.save(file);
            } catch (IOException error) {
                System.out.println("IO ERROR");
                error.printStackTrace();
            }
        }
    }

    /**
     * check if the score is relevant to the high score table, and ask player name to save the score.
     */
    public void highScoreDialog() {
        //check if relevant
        if (!(this.highScoresTable.getRank(this.score.getValue()) < this.highScoresTable.size())) {
            return;
        }
        //get the player name
        GUI gui =  this.animationRunner.getGui();
        DialogManager dialog = gui.getDialogManager();
        String name = dialog.showQuestionDialog("Tell my who you are", "What is your name?", "");
        //because of using space as a separate in data base, replace spaces with -
        name = name.replaceAll(" ", "-");
        //add to the data base
        ScoreInfo scoreInfo = new ScoreInfo(name, this.score.getValue());
        this.highScoresTable.add(scoreInfo);
    }

    /**
     * thing to do after the game end with the score - save it and show the high score screen.
     */
    public void scoreAfterGame() {
        this.highScoreDialog();
        KeyPressStoppableAnimation highScoresAnimation = new KeyPressStoppableAnimation(this.keyboardSensor,
                KeyboardSensor.SPACE_KEY, new HighScoresAnimation(this.highScoresTable));
        this.animationRunner.run(highScoresAnimation);
    }

    @Override
    public Void run() {
        this.runLevels();
        return null;
    }

}