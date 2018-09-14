package game;

import gameobjects.Ball;
import gameobjects.Block;

/**
 * Listens to hits of a ball and a block.
 * @author H
 *
 */
public interface HitListener {

  /**
   * This method is called whenever the beingHit object is hit.
   * @param beingHit the object that was hit
   * @param hitter the Ball that's doing the hitting.
   */
  void hitEvent(Block beingHit, Ball hitter);
}
