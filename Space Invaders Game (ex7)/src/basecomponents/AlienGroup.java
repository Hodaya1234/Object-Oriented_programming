package basecomponents;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import biuoop.DrawSurface;
import game.GameLevel;
import logic.Shootable;
import logic.Sprite;

/**
 *
 * @author H
 *
 */
public class AlienGroup implements Sprite, Shootable, AlienContainer {

  private List<AlienColumn> columns;
  private double timeCounter;
  private int startSpeed;
  private int currentSpeed;
  private int direction;

  /**
   * Constructor.
   * @param list of alien columns
   * @param speed of the aliens
   * @param direction of initial movement
   */
  public AlienGroup(List<AlienColumn> list, int speed, int direction) {
    this.columns = list;
    timeCounter = 0;
    this.startSpeed = speed;
    this.currentSpeed = speed;
    this.direction = direction;
  }

  /**
   * restert place of the aliens to top left.
   */
  public void moveToStart() {
    int currentLeft = left();
    int desiredLeft = 10;
    int currentTop = top();
    int desiredTop = 23;
    move(desiredLeft - currentLeft, desiredTop - currentTop);
    currentSpeed = startSpeed;
  }

  @Override
  public int bottom() {
    List<AlienColumn> newList = new ArrayList<AlienColumn>(columns);
    int bottom = newList.get(0).bottom();
    for (AlienColumn c : newList) {
      if (c.bottom() > bottom) {
        bottom = c.bottom();
      }
    }
    return bottom;
  }

  @Override
  public void shoot() {
    Random rand = new Random();
    int number = rand.nextInt(columns.size());
    columns.get(number).shoot();
  }

  @Override
  public void drawOn(DrawSurface d) {
  }

  @Override
  public void timePassed(double dt) {
    List<AlienColumn> newList = new ArrayList<AlienColumn>(columns);
    for (AlienColumn c : newList) {
      if (c.isEmpty()) {
        columns.remove(c);
      }
    }
    if (columns.isEmpty()) {
      return;
    }

    if (timeCounter > 0.5) {
      timeCounter = 0;
      shoot();
    }
    timeCounter += dt;
    int dx = (int) (direction * currentSpeed * dt);

    if (right() >= 795) {
      move(-1, Alien.HEIGHT);
      direction = Alien.LEFT;
      currentSpeed = (int) (1.1 * currentSpeed);
    } else if (left() <= 5) {
      move(1, Alien.HEIGHT);
      direction = Alien.RIGHT;
      currentSpeed = (int) (1.1 * currentSpeed);
    } else {
      move(dx, 0);
    }
  }

  @Override
  public void move(int dx, int dy) {
    for (AlienColumn c : columns) {
      c.move(dx, dy);
    }
  }

  /**
   * find the most right column.
   * @return right column
   */
  private AlienColumn rightMost() {
    if (columns.isEmpty()) {
      return null;
    }

    List<AlienColumn> newList = new ArrayList<AlienColumn>(columns);
    AlienColumn right = newList.get(0);
    for (AlienColumn c : newList) {
      if (c.right() > right.right()) {
        right = c;
      }
    }
    return right;
  }

  /**
   * find the most left column.
   * @return left column
   */
  private AlienColumn leftMost() {
    if (columns.isEmpty()) {
      return null;
    }

    List<AlienColumn> newList = new ArrayList<AlienColumn>(columns);
    AlienColumn left = newList.get(0);
    for (AlienColumn c : newList) {
      if (c.left() < left.left()) {
        left = c;
      }
    }
    return left;
  }

  @Override
  public int howMany() {
    int count = 0;
    for (AlienColumn c : columns) {
      count += c.howMany();
    }
    List<AlienColumn> newList = new ArrayList<AlienColumn>(columns);
    for (AlienColumn c : newList) {
      if (c.isEmpty()) {
        columns.remove(c);
      }
    }
    return count;
  }


  @Override
  public int left() {
    return leftMost().left();
  }

  @Override
  public int right() {
    return rightMost().right();
  }

  @Override
  public int top() {
    List<AlienColumn> newList = new ArrayList<AlienColumn>(columns);
    int mostTop = newList.get(0).top();
    for (AlienColumn c : newList) {
      if (c.top() < mostTop) {
        mostTop = c.top();
      }
    }
    return mostTop;
  }

  @Override
  public void removeFromGame(GameLevel g) {
    // TODO Auto-generated method stub

  }

  @Override
  public void addToGame(GameLevel g) {
    g.addSprite(this);
  }

}
