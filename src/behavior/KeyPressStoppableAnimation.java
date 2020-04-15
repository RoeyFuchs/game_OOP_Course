package behavior;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * key press stopable animation - will mange the stop of animation that need to stop by press a key.
 */
public class KeyPressStoppableAnimation implements Animation  {
    private KeyboardSensor keyboardSensor;
    private String key;
    private Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed;

    /**
     * constructor.
     * @param sensor - the keyboard
     * @param key - the key to stop the animation
     * @param animation the animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.key = key;
        this.keyboardSensor = sensor;
        this.animation = animation;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        if (!this.keyboardSensor.isPressed(this.key)) {
            this.isAlreadyPressed = false;
        }
        animation.doOneFrame(d, dt);
        if (this.keyboardSensor.isPressed(this.key) && !this.isAlreadyPressed) {
            this.stop = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }

}
