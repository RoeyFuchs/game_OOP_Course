package elements;
/**
 * basic counter.
 */
public class Counter {
    private int val;

    /**
     * constractur.
     */
    public Counter() {
        this.val = 0;
    }

    /**
     * add number to current count.
     * @param number how many to add
     */
    public void increase(int number) {
        this.val += number;
    }

    /**
     * subtract number from current count.
     *
     * @param number how many to decrease
     */
    public void decrease(int number) {
        this.val -= number;
    }

    /**
     * get current count.
     *
     * @return the current value
     */
    public int getValue() {
        return this.val;
    }

    /**
     * get the value as a string.
     * @return a string with the value
     */
    public String getValueString() {
        Integer value =  this.val;
        return value.toString();
    }

}