package logic;
import biuoop.DrawSurface;
import game.GameLevel;

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

  /**
   * remove from the game level.
   * @param g the game
   */
  void removeFromGame(GameLevel g);

  /**
   * add to game level sprites.
   * @param g the game
   */
  void addToGame(GameLevel g);
}