package usergraphics;

import biuoop.DrawSurface;
/**
 * Animation Interface.
 * @author H
 *
 */
public interface Animation {

  /**
   * Draw one frame of the animation.
   * @param d
   *          the draw surface
   * @param dt
   *          time passed
   */
  void doOneFrame(DrawSurface d, double dt);

  /**
   * when the animation should stop.
   * @return true if the conditions are right.
   */
  boolean shouldStop();

}
