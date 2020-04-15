/**
 * input check.
 */
public class CheckInput {
    private String[] args;

    /**
     * constructor.
     *
     * @param args - argument input.
     */
    CheckInput(String[] args) {
        this.args = args;
    }

    /**
     * check function for Multiple Frames class.
     *
     * @throws Exception - if something wrong
     */
    public void checkMultipleFrames() throws Exception {
        checkIfEmpty();
        checkIfIntegers();
        checkIfEven();
        checkIfPositive();
    }

    /**
     * check function for Multiple Ball class.
     * @throws Exception - if something wrong
     */
    public void checkMultipleBalls() throws Exception {
        checkIfEmpty();
        checkIfIntegers();
        checkIfPositive();
    }

    /**
     * check if there is any arguments.
     * @throws Exception if its empty
     */
    public void checkIfEmpty() throws Exception {
        if (this.args.length == 0) {
            System.out.println("empty argument");
            throw new Exception();
        }
    }

    /**
     * check if the all numbers are positive.
     *
     * @throws Exception - if something are not positive
     */
    public void checkIfPositive() throws Exception {
        for (int i = 0; i < this.args.length; i++) {
            if (Integer.parseInt(this.args[i]) <= 0) {
                System.out.println("The radius have to be positive");
                throw new Exception();
            }
        }
    }

    /**
     * check if the there are even number of numbers.
     *
     * @throws Exception - if there are odd number of numbers
     */
    public void checkIfEven() throws Exception {
        //check if the there are even number of numbers
        if (this.args.length % 2 != 0) {
            System.out.println("Have to be even number of balls");
            throw new Exception();
        }
    }

    /**
     * check if the input is integers.
     *
     * @throws Exception - if not all the input are integers
     */
    public void checkIfIntegers() throws Exception {
        //check if the arguments are integers
        for (int i = 0; i < this.args.length; i++) {
            try {
                Integer.parseInt(this.args[i]);
            } catch (Exception e) {
                System.out.println("invalid input");
                throw new Exception();
            }
        }
    }
}
