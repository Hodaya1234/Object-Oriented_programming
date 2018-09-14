package basecomponents;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;
import game.GameLevel;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import logic.BulletCreator;
import logic.HitInformation;
import logic.HitListener;
import logic.Hittable;
import logic.Shootable;
import logic.Sprite;

/**
 * A single alien.
 * @author H
 *
 */
public class Alien implements Sprite, Hittable, Shootable {

  private BulletCreator bc;
  private Rectangle rect;
  private List<HitListener> listeners;
  private boolean dead;
  private Image image;
  public static final int WIDTH = 40;
  public static final int HEIGHT = 30;
  public static final int LEFT = -1;
  public static final int RIGHT = 1;

  /**
   * Constructor.
   * @param bc bullet creator
   * @param x first x position of the alien
   * @param y first x position of the alien
   * @param image of an alien
   */
  public Alien(BulletCreator bc, int x, int y, Image image) {
    this.bc = bc;
    this.rect = new Rectangle(new Point(x, y), WIDTH, HEIGHT);
    this.listeners = new ArrayList<HitListener>();
    this.dead = false;
    this.image = image;
  }

  /**
   * get the rectangle that represents the alien for collisions.
   * @return rectangle
   */
  public Rectangle getRectangle() {
    return rect;
  }

  /**
   * is the alien still alive.
   * @return whether the alien is dead
   */
  public boolean dead() {
    return dead;
  }

  /**
   * move the alien.
   * @param dx the change of x axes
   * @param dy the change of y axes
   */
  public void move(int dx, int dy) {
    this.rect = new Rectangle(new Point(rect.leftX() + dx, rect.upY() + dy), WIDTH, HEIGHT);
  }

  @Override
  public void shoot() {
    Point place = new Point(rect.leftX() + (WIDTH / 2), rect.upY() + HEIGHT + 7);
    bc.createBullet(this, place, 5, Bullet.DOWN, 400, Color.red);
  }

  @Override
  public boolean isInTrajectory(Line trajectory) {
    return rect.isIntersecting(trajectory);
  }

  @Override
  public Point closestCollision(Line trajectory) {
    return rect.closestIntersection(trajectory);
  }

  @Override
  public void tellHitListeners(HitInformation info) {
    List<HitListener> list = new ArrayList<HitListener>(this.listeners);
    for (HitListener h : list) {
      h.hitHappened(info);
    }
  }

  @Override
  public void addHitListener(HitListener h) {
    listeners.add(h);

  }

  @Override
  public void removeHitListener(HitListener h) {
    if (listeners.contains(h)) {
      listeners.remove(h);
    }
  }

  @Override
  public void drawOn(DrawSurface d) {
    if (image != null) {
      d.drawImage(rect.leftX(), rect.upY(), image);
    } else {
      d.setColor(Color.white);
      d.fillRectangle(rect.leftX(), rect.upY(), WIDTH, HEIGHT);
    }
  }

  @Override
  public void timePassed(double dt) {
    // add moving hands
  }

  @Override
  public void removeFromGame(GameLevel g) {
    dead = true;
    g.removeHittable(this);
    g.removeSprite(this);
  }

  @Override
  public void addToGame(GameLevel g) {
    g.addHittable(this);
    g.addSprite(this);
  }
}