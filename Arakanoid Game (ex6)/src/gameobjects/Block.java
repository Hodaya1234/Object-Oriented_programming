package gameobjects;
import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import biuoop.DrawSurface;
import game.GameLevel;
import game.HitListener;
import game.HitNotifier;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;

/**
 * Blocks of the game.
 * @author H
 *
 */
public class Block implements Collidable, Sprite, HitNotifier {
  private Rectangle rectangle;
  private Color color;
  private int hits;
  private List<HitListener> hitListeners;
  private Map<Integer, Color> fillingColor;
  private Map<Integer, Image> fillingImage;
  private Color stroke;

  /**
   * constructor.
   * @param upperLeft point
   * @param width of the bloc
   * @param height of the bloc
   * @param color of the bloc
   * @param hits left till the block disappears
   */
  public Block(Point upperLeft, double width, double height, Color color, int hits) {
    this.rectangle = new Rectangle(upperLeft, width, height);
    this.color = color;
    this.hits = hits;
    this.hitListeners = new ArrayList<HitListener>();
    this.stroke = Color.black;
    this.fillingColor = new HashMap<Integer, Color>();
    this.fillingImage = new HashMap<Integer, Image>();
  }

  /**
   * set the number of hits.
   * @param hitsNum left
   */
  public void setHits(int hitsNum) {
    this.hits = hitsNum;
  }

  /**
   * how many hits are left before the block is removed.
   * @return number of hits
   */
  public int getHitPoints() {
    return this.hits;
  }

  /**
   * set color of the block.
   * @param c to set
   */
  public void setColor(Color c) {
    this.color = c;
  }

  /**
   * set color of the frame of the block.
   * @param c to set
   */
  public void setStroke(Color c) {
    this.stroke = c;
  }

  /**
   * rectangle of the block.
   * @return rectangle
   */
  public Rectangle getCollisionRectangle() {
    return this.rectangle;
  }

  /**
   * set the map of color filling block.
   * @param filling
   *          the map from hits left to a color
   */
  public void setFillingColor(Map<Integer, Color> filling) {
    this.fillingColor = filling;
  }

  /**
   * set the map of image filling block.
   * @param filling
   *          the map from hits left to an image
   */
  public void setFillingImage(Map<Integer, Image> filling) {
    this.fillingImage = filling;
  }

  /**
   * add the block to the game.
   * @param game to add the block to
   */
  public void addToGame(GameLevel game) {
    game.addCollidable(this);
    game.addSprite(this);
  }

  /**
   * Remove the block from the game.
   * @param game to remove the block from
   */
  public void removeFromGame(GameLevel game) {
    game.removeCollidable(this);
    game.removeSprite(this);
  }

  /**
   * drawing the block.
   * @param d the GUI draw surface
   */
  public void drawOn(DrawSurface d) {
    int x = (int) rectangle.getUpperLeft().getX();
    int y = (int) rectangle.getUpperLeft().getY();
    int width = (int) rectangle.getWidth();
    int height = (int) rectangle.getHeight();
    if (fillingColor.containsKey(hits)) {
      d.setColor(fillingColor.get(hits));
      d.fillRectangle(x, y, width, height);
    } else if (fillingImage.containsKey(hits)) {
      d.drawImage(x, y, fillingImage.get(hits));
    } else if (fillingColor.containsKey(0)) {
      d.setColor(fillingColor.get(0));
      d.fillRectangle(x, y, width, height);
    } else if (fillingImage.containsKey(0)) {
      d.drawImage(x, y, fillingImage.get(0));
    } else if (this.color != null) {
      d.setColor(this.color);
      d.fillRectangle(x, y, width, height);
    }
    if (this.stroke != null) {
      d.setColor(this.stroke);
      d.drawRectangle(x, y, width, height);
    }
  }

/**
 * calculate ball velocity after hitting a block.
 * @param hitter the ball that hit the block
 * @param collisionPoint the point where the ball hit
 * @param currentVelocity the current velocity of the ball
 * @return new Velocity
 */
  public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
    this.wasHit();
    int side = this.getCollisionRectangle().whichSide(collisionPoint);
    //if point is in up or down lines change y axes
    if (this.isCorner(collisionPoint)) {
      this.notifyHit(hitter);
      return new Velocity(-currentVelocity.getDx(), -currentVelocity.getDy());
    }
    //change the ball's velocity away from the surface
    if ((side == 0) || (side == 2)) {
      // if the ball hit the top or bottom side
      this.notifyHit(hitter);
      return new Velocity(currentVelocity.getDx(), -1 * currentVelocity.getDy());
    } else if ((side == 1) || (side == 3)) {
      // if the ball hit he left or right sides
      this.notifyHit(hitter);
      return new Velocity(-1 * currentVelocity.getDx(), currentVelocity.getDy());
    } else {
      return currentVelocity;
    }
  }

  /**
   * if there are hits left, take one down.
   */
  public void wasHit() {
    if (this.hits > 0) {
      hits--;
    }
  }

  /**
   * @param dt
   *          nothing.
   */
  public void timePassed(double dt) {
    return;
  }

  /**
   * check if the ball hit the corner of the block.
   * @param p collision point
   * @return true if corner, false otherwise
   */
  public boolean isCorner(Point p) {
    Point[] corners = this.rectangle.corners();
    for (Point c : corners) {
      if (c.equals(p)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public void addHitListener(HitListener hl) {
    this.hitListeners.add(hl);
}

  @Override
  public void removeHitListener(HitListener hl) {
    this.hitListeners.remove(hl);
  }

  /**
   * Make a copy of the hitListeners and notify them about a hit event.
   * @param hitter the ball that hit
   */
  private void notifyHit(Ball hitter) {
      List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
      for (HitListener hl : listeners) {
         hl.hitEvent(this, hitter);
      }
   }
}