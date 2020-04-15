package menu;

import behavior.Animation;

/**
 * interface for the game menu.
 * @param <T> for generic
 */
public interface Menu<T> extends Animation  {
    /**
     * add a selection to the menu.
     * @param key what to push to choose
     * @param message what to show on the screen
     * @param returnVal what to return
     */
    void addSelection(String key, String message, T returnVal);

    /**
     * get the chosen option.
     * @return the return value of the chosen
     */
    T getStatus();

    /**
     * add a sub menu option.
     * @param key the key to enter the sub menu
     * @param message the message to show
     * @param subMenu the submenu
     */
    void addSubMenu(String key, String message, Menu<T> subMenu);



}