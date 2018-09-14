package game;

import java.util.ArrayList;

import biuoop.DrawSurface;
import gameobjects.Sprite;

/**
 * moving objects of the game.
 * @author H
 *
 */
public class SpriteCollection {

  private ArrayList<Sprite> spriteList;

  /**
   * constructor.
   */
  public SpriteCollection() {
    this.spriteList = new ArrayList<Sprite>();
  }

  /**
  * add an object to the list.
  * @param s sprite object
  */
  public void addSprite(Sprite s) {
    this.spriteList.add(s);
  }

  /**
   * Remove a sprite from the game.
   * @param s Sprite object
   */
  public void removeSprite(Sprite s) {
    if (this.spriteList.contains(s)) {
     this.spriteList.remove(s);
    }
  }

  /**
   * call timePassed() on all sprite.
   * @param dt
   *          time passed
   */
  public void notifyAllTimePassed(double dt) {
    ArrayList<Sprite> list = new ArrayList<Sprite>(this.spriteList);
    for (Sprite s : list) {
      s.timePassed(dt);
    }
  }

  /**
   * call drawOn(d) on all sprite.
   * @param d
   *          GUI draw surface
   */
  public void drawAllOn(DrawSurface d) {
    ArrayList<Sprite> list = new ArrayList<Sprite>(this.spriteList);
    for (Sprite s : list) {
      s.drawOn(d);
    }
  }
}