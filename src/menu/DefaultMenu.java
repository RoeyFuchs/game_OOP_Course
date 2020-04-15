package menu;

import behavior.AnimationRunner;
import behavior.SubMenuTask;
import levelsio.LevelSetReader;
import specialscreen.HighScoresAnimation;


/**
 * Default menu to get the default menu.
 */
public class DefaultMenu {

    /**
     * get the default menu.
     *
     * @param as      the animation runner
     * @param scores  the highscore table
     * @param levelSetReader level reader
     * @return a menu
     */
    public static Menu<Task<Void>> getDeafultMenu(AnimationRunner as, HighScoresAnimation scores,
                                                  LevelSetReader levelSetReader) {
        Menu<Task<Void>> subMenu = levelSetReader.createMenu(as);
        Menu<Task<Void>> menu = new MenuAnimation<Task<Void>>(as);
        menu.addSelection("h", "High scores", new ShowHiScoresTask(as, scores));
        menu.addSelection("s", "Play Game", new SubMenuTask(as, subMenu));
        menu.addSelection("q", "Quit Game", new CloseGame());

        return menu;
    }
}
