package game;
import gameobjects.Collidable;
import geometry.Point;

/**
 * information of a hit.
 * @author H
 *
 */
public class CollisionInfo {
  private Point collisionPoint;
  private Collidable collidable;

  /**
   * constructor.
   * @param collisionPoint Point
   * @param collidable object
   */
  public CollisionInfo(Point collisionPoint, Collidable collidable) {
    this.collisionPoint = collisionPoint;
    this.collidable = collidable;
  }

  /**
   * return the point at which the collision occurs.
   * @return point
   */
  public Point collisionPoint() {
    return this.collisionPoint;
  }

  /**
   * return the collidable object involved in the collision.
   * @return object
   */
  public Collidable collisionObject() {
    return this.collidable;
  }
}
