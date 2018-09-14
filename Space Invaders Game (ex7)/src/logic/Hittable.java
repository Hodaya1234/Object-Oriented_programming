package logic;

import game.GameLevel;
import geometry.Line;
import geometry.Point;

/**
 * objects the bullet can hit - shield, alien, and spaceship.
 * @author H
 *
 */
public interface Hittable {

  /**
   * checks if a certain object is about to hit the hittable.
   * @param trajectory path of the moving object
   * @return true if the object will hit it.
   */
  boolean isInTrajectory(Line trajectory);

  /**
   * if an object will hit the hittable, find the point of collision.
   * @param trajectory path of the object
   * @return point of collision
   */
  Point closestCollision(Line trajectory);

  /**
   * notify the relevant classes about the hit.
   * @param info the information of the hit
   */
  void tellHitListeners(HitInformation info);

  /**
   * add a listener object to follow the hits.
   * @param h the listener
   */
  void addHitListener(HitListener h);

  /**
   * remove a listener object from following the hits.
   * @param h the listener
   */
  void removeHitListener(HitListener h);

  /**
   * remove the hittable from the game.
   * @param g the game level
   */
  void removeFromGame(GameLevel g);
}