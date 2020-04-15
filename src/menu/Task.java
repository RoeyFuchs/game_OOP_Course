package menu;

/**
 * running a task.
 * @param <T> an option that call task
 */
public interface Task<T> {
    /**
     * run task.
     * @return something that we will want to return from a task
     */
    T run();

}
