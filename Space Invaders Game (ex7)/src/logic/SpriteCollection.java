package logic;

import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;

/**
 *
 * @author H
 *
 */
public class SpriteCollection {

  private List<Sprite> list;

  /**
   * Constructor. starts a new sprite list.
   */
  public SpriteCollection() {
    this.list = new ArrayList<Sprite>();
  }

  /**
   * Constructor.
   * @param list of sprite objects
   */
  public SpriteCollection(List<Sprite> list) {
    this.list = list;
  }

  /**
   * add a sprite to the list.
   * @param s sprite
   */
  public void addSprite(Sprite s) {
    this.list.add(s);
  }

  /**
   * remove a sprite from the list.
   * @param s sprite
   */
  public void removeSprite(Sprite s) {
    if (list.contains(s)) {
      list.remove(s);
    }
  }

  /**
   * draw all the sprite objects in the list.
   * @param d drawsurface of the GUI
   */
  public void drawAllOn(DrawSurface d) {
    for (Sprite s : list) {
      s.drawOn(d);
    }
  }

  /**
   * notify the sprite objects that they need to move.
   * @param dt time passed since last time
   */
  public void timePassed(double dt) {
    ArrayList<Sprite> newList = new ArrayList<Sprite>(this.list);
    for (Sprite s : newList) {
      s.timePassed(dt);
    }
  }
}