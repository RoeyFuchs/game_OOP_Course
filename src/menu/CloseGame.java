package menu;

/**
 * task to close the game.
 */
public class CloseGame implements Task {

    /**
     * empty constructor.
     */

    public CloseGame() {
    }

    @Override
    public Void run() {
        System.exit(0);
        return null;
    }

}
