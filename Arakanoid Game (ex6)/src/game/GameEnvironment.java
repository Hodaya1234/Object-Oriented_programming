package game;

import java.util.ArrayList;

import gameobjects.Collidable;
import geometry.Line;
import geometry.Point;

/**
 * all the objects in the game.
 * @author H
 *
 */
public class GameEnvironment {
  private ArrayList<Collidable> collidables;

  /**
   * constructor.
   */
  public GameEnvironment() {
    this.collidables = new ArrayList<Collidable>();
  }

  /**
   * get collidable list.
   * @return collidables
   */
  public ArrayList<Collidable> getCollidables() {
    return this.collidables;
  }

  /**
   * add the given collidable to the environment.
   * @param c the collidable object
   */
  public void addCollidable(Collidable c) {
    this.collidables.add(c);
  }

  /**
   * Remove a collidable object from the list.
   * @param c the collidable object
   */
  public void removeCollidable(Collidable c) {
    if (this.collidables.contains(c)) {
      this.collidables.remove(c);
    }
  }

  /**
   * return closest collision of the ball with game objects.
   * @param trajectory of the ball
   * @return closest collision
   */
  public CollisionInfo getClosestCollision(Line trajectory) {
  ArrayList<Collidable> list = new ArrayList<Collidable>(this.collidables);
    Point closestPoint = trajectory.end();
    Collidable closestCollidable = null;
    Point p;
    for (Collidable c : list) {
      p = trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle());
      if ((p != null)
          && (p.distance(trajectory.start()) < closestPoint.distance(trajectory.start()))) {
        closestPoint = p;
        closestCollidable = c;
      }
    }
    if (closestCollidable != null) {
      return new CollisionInfo(closestPoint, closestCollidable);
    } else {
      return null;
    }
  }
}
