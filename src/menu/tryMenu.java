package menu;

import behavior.AnimationRunner;
import info.HighScoresTable;
import specialscreen.HighScoresAnimation;

import java.io.File;

public class tryMenu {

    public static void main(String[] args) {

        AnimationRunner runner = new AnimationRunner(60, 800, 600);
        File file = new File(HighScoresTable.getFileName());

        HighScoresAnimation scores = new HighScoresAnimation(HighScoresTable.loadFromFile(file));

        Menu<Task<Void>> menu = new MenuAnimation<Task<Void>>(runner);
        menu.addSelection("h", "High scores", new ShowHiScoresTask(runner, scores));

        while (true) {
            runner.run(menu);
            // wait for user selection
            Task<Void> task = menu.getStatus();
            task.run();

        }
    }
}

