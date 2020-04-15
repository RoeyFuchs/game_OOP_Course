package behavior;

import menu.Menu;
import menu.MenuAnimation;
import menu.Task;

/**
 * sub menu task (to run the sub menu).
 */
public class SubMenuTask extends MenuAnimation implements Task {
    private Menu<Task<Void>> menu;

    /**
     * contractor.
     *
     * @param ar animation runner
     * @param menu a menu
     */
    public SubMenuTask(AnimationRunner ar, Menu<Task<Void>> menu) {
        super(ar);
        this.menu = menu;
    }

    @Override
    public Void run() {
        super.getAr().run(this.menu);
        Task<Void> task = menu.getStatus();
        task.run();
        return null;
    }
}
