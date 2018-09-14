package logic;

/**
 *
 * @author H
 *
 */
public interface HitListener {

  /**
   * gets a notification that a hit happened.
   * @param info the information of the hit - the moving object, hittable
   */
  void hitHappened(HitInformation info);
}
