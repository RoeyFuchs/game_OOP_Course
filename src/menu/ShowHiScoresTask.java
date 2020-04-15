package menu;

import behavior.Animation;
import behavior.AnimationRunner;
import behavior.KeyPressStoppableAnimation;
import biuoop.KeyboardSensor;

/**
 * high score runner task.
 */
public class ShowHiScoresTask implements Task<Void> {
    private AnimationRunner runner;
    private Animation highScoresAnimation;

    /**
     * constructor.
     * @param runner the animation runner
     * @param highScoresAnimation the high score animation
     */
    public ShowHiScoresTask(AnimationRunner runner, Animation highScoresAnimation) {
        this.runner = runner;
        this.highScoresAnimation = highScoresAnimation;
    }

    @Override
    public Void run() {
        this.runner.run(new KeyPressStoppableAnimation(this.runner.getGui().getKeyboardSensor(),
                KeyboardSensor.SPACE_KEY, this.highScoresAnimation));
        return null;
    }

}
