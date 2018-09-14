package menu;

/**
 *
 * @author H
 *
 * @param <T>
 */
public class Selection<T> {
  private String key;
  private String message;
  private T returnVal;

  /**
   * Constructor.
   * @param key
   *          to press
   * @param message
   *          to print to the screen
   * @param returnVal
   *          result of the selection
   */
  public Selection(String key, String message, T returnVal) {
    this.key = key;
    this.message = message;
    this.returnVal = returnVal;
  }

  /**
   *
   * @return key
   */
  public String key() {
    return this.key;
  }

  /**
   *
   * @return string message
   */
  public String message() {
    return this.message;
  }

  /**
   *
   * @return result of selection
   */
  public T returnVal() {
    return this.returnVal;
  }
}