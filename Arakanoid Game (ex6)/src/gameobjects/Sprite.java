package gameobjects;
import biuoop.DrawSurface;

/**
 * objects moving in the game.
 * @author H
 *
 */
public interface Sprite {
  /**
  * draw the sprite to the screen.
  * @param d GUI draw surface
  */
  void drawOn(DrawSurface d);

  /**
   * notify the sprite that time has passed.
   * @param dt
   *          time
   */
  void timePassed(double dt);
}