package menu;

import animation.Animation;

/**
 *
 * @author H
 *
 * @param <T>
 *          abstract
 */
public interface Menu<T> extends Animation {
  /**
   * add a selection to the mane.
   * @param key
   *          to press to choose
   * @param message
   *          to print to the screen
   * @param returnVal
   *          the outcome of the choice
   */
  void addSelection(String key, String message, T returnVal);

  /**
   *
   * @return the status of the menu, the choice
   */
  T getStatus();
}
