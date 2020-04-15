package menu;

/**
 * menu option.
 * @param <T> the return value type
 */
public class MenuOption<T> {
    private String key;
    private String message;
    private T returnVal;
    private boolean isSubMenu = false;

    /**
     * constructor.
     * @param key the key to choose
     * @param message the message to show in the menu
     * @param returnVal the return value of the option
     */
    public MenuOption(String key, String message, T returnVal) {
        this.key = key;
        this.message = message;
        this.returnVal = returnVal;
    }

    /**
     * get the key.
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * get the return value.
     * @return the return value
     */
    public T getReturnVal() {
        return returnVal;
    }

    /**
     * get the message.
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * set this option as a sub menu.
     */
    public void setIsSubMenu() {
        this.isSubMenu = true;
    }


}
