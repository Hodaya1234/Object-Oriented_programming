package basecomponents;

import java.util.ArrayList;
import java.util.List;

import logic.Shootable;

/**
 * contains at most 5 aliens.
 * @author H
 *
 */
public class AlienColumn implements Shootable, AlienContainer {

  private List<Alien> aliens;

  /**
   * Constructor.
   * @param aliens of the column
   */
  public AlienColumn(List<Alien> aliens) {
    this.aliens = aliens;
  }

  @Override
  public int bottom() {
    removeDead();
    if (isEmpty()) {
      return -1;
    }
    Alien a = bottomAlien();
    return a.getRectangle().upY() + a.getRectangle().getHeight();
  }

  /**
   * is the list empty.
   * @return whether the column of aliens is empty
   */
  public boolean isEmpty() {
    return this.aliens.isEmpty();
  }

  /**
   * counts the aliens.
   * @return number of aliens
   */
  public int howMany() {
    removeDead();
    return aliens.size();
  }

  /**
   * remove the dead aliens from the game.
   */
  public void removeDead() {
    List<Alien> newList = new ArrayList<Alien>(aliens);
    for (Alien a : newList) {
      if (a.dead()) {
        aliens.remove(a);
      }
    }
  }

  /**
   * get the left border of the aliens.
   * @return x presentation
   */
  public int left() {
    removeDead();
    if (!aliens.isEmpty()) {
      return aliens.get(0).getRectangle().leftX();
    } else {
      return -1;
    }
  }

  @Override
  public int right() {
    if (aliens.isEmpty()) {
      return -1;
    }
    return left() + Alien.WIDTH;
  }

  @Override
  public int top() {
    removeDead();
    return aliens.get(0).getRectangle().upY();
  }

  @Override
  public void shoot() {
    removeDead();
    bottomAlien().shoot();
  }

  @Override
  public void move(int dx, int dy) {
    removeDead();
    for (Alien a : aliens) {
      a.move(dx, dy);
    }
  }

  /**
   * find the bottom most alien.
   * @return bottom alien
   */
  private Alien bottomAlien() {
    removeDead();
    if (isEmpty()) {
      return null;
    }
    List<Alien> newList = new ArrayList<Alien>(aliens);
    Alien bottom = newList.get(0);
    for (Alien a : newList) {
      if (a.getRectangle().upY() > bottom.getRectangle().upY()) {
        bottom = a;
      }
    }
    return bottom;
  }
}