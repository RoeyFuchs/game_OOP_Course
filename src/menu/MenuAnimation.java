package menu;

import behavior.AnimationRunner;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * the menu animation.
 *
 * @param <T> the type to the return value
 */
public class MenuAnimation<T> implements Menu {
    private List<MenuOption<T>> optionList;
    private T status;
    private KeyboardSensor keyboardSensor;
    private boolean stop;
    private AnimationRunner ar;

    /**
     * contractor.
     *
     * @param ar animation runner
     */
    public MenuAnimation(AnimationRunner ar) {
        this.optionList = new ArrayList<MenuOption<T>>();
        this.stop = false;
        this.keyboardSensor = ar.getGui().getKeyboardSensor();
        this.ar = ar;
    }


    @Override
    public void addSelection(String key, String message, Object returnVal) {
        MenuOption menuOption = new MenuOption<>(key, message, returnVal);
        this.optionList.add(menuOption);
    }

    @Override
    public Object getStatus() {
        return this.status;
    }

    @Override
    public void addSubMenu(String key, String message, Menu subMenu) {
        MenuOption menuOption = new MenuOption<>(key, message, subMenu);
        this.optionList.add(menuOption);
    }

    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        d.setColor(Color.YELLOW);
        d.drawText(150, 150, "Menu", 180);
        for (int i = 0; i < this.optionList.size(); i++) {
            d.setColor(Color.RED);
            d.drawText(150, 250 + (i * 30), "Press " + this.optionList.get(i).getKey() + " to", 30);
            d.setColor(Color.BLUE);
            d.drawText(300, 250 + (i * 30), this.optionList.get(i).getMessage(), 30);
        }

        for (int i = 0; i < this.optionList.size(); i++) {
            if (this.keyboardSensor.isPressed(this.optionList.get(i).getKey())) {
                this.stop = true;
                this.status = this.optionList.get(i).getReturnVal();
            }
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }

    /**
     * get the animation runner.
     * @return the animation runner
     */
    public AnimationRunner getAr() {
        return this.ar;
    }


}
