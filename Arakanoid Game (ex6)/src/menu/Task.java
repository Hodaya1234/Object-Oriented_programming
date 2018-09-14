package menu;

/**
 *
 * @author H
 *
 * @param <T>
 *          abstract
 */
public interface Task<T> {
  /**
   * run the certain function of the task.
   * @return a value
   */
  T run();
}
