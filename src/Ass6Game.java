

import behavior.AnimationRunner;
import info.HighScoresTable;
import levelsio.LevelSetReader;
import menu.DefaultMenu;
import menu.Menu;
import menu.Task;
import specialscreen.HighScoresAnimation;


import java.io.File;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * run the game.
 */
public class Ass6Game {
    private static final String DEFAULT_FILE = "level_sets.txt";
    /**
     * main function to fun the game.
     * @param args levels
     */
    public static void main(String[] args) {
        AnimationRunner as = new AnimationRunner(60, 800, 600);

        File file = new File(HighScoresTable.getFileName());
        HighScoresAnimation scores = new HighScoresAnimation(HighScoresTable.loadFromFile(file));
        String fileName;

        if (args.length == 0) {
           fileName = Ass6Game.DEFAULT_FILE;
        } else {
            fileName = args[0];
        }

        InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream(fileName);
        Reader read = new InputStreamReader(inputStream);
        LevelSetReader levelSetReader = new LevelSetReader(read);

        while (true) {
            Menu<Task<Void>> menu = DefaultMenu.getDeafultMenu(as, scores, levelSetReader);
            as.run(menu);
            Task<Void> task = menu.getStatus();
            task.run();
        }
    }



}