package gameobjects;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;

/**
 * objects the ball can collide with.
 * @author H
 *
 */
public interface Collidable {
  /**
   * Return the "collision shape" of the object.
   * @return rectangle
   */
  Rectangle getCollisionRectangle();

  /**
   * Notify the object that we collided with it at collisionPoint with a given velocity.
   * The return is the new velocity expected after the hit.
   * @param hitter the ball that hit
   * @param collisionPoint the point of collision
   * @param currentVelocity of the ball
   * @return new velocity
   */
  Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}