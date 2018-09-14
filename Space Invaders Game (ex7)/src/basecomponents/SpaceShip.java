package basecomponents;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
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
 *
 * @author H
 *
 */
public class SpaceShip implements Sprite, Shootable, Hittable {

  private BulletCreator bc;
  private Rectangle rect;
  private KeyboardSensor keyboard;
  private final int xSpeed = 500;
  private double timeCounter;
  private List<HitListener> listeners;
  private Image image;

  /**
   * Constructor.
   * @param upperLeft point
   * @param bc bullet creator
   * @param keyboard sensor
   * @param image of space ship
   */
  public SpaceShip(Point upperLeft, BulletCreator bc, KeyboardSensor keyboard, Image image) {
    this.bc = bc;
    this.keyboard = keyboard;
    this.rect = new Rectangle(upperLeft, 70, 40);
    this.timeCounter = 0;
    this.listeners = new ArrayList<HitListener>();
    this.image = image;
  }

  @Override
  public void shoot() {
    Point place = new Point(rect.leftX() + (rect.getWidth() / 2), rect.upY() - 6);
    bc.createBullet(this, place, 5, Bullet.UP, 500, Color.white);
  }


  @Override
  public void drawOn(DrawSurface d) {
    if (image == null) {
    d.setColor(Color.blue);
    d.fillRectangle(rect.leftX(), rect.upY(), rect.getWidth(), rect.getHeight());
    } else {
      d.drawImage(rect.leftX(), rect.upY(), image);
    }

  }

  /**
   * move the spaceship left.
   * @param dt
   *          time passed
   */
  public void moveLeft(double dt) {
    int xNext = (int) (rect.leftX() - (xSpeed * dt));
    if (xNext > 10) {
      rect.moveX(-1 * (int) (xSpeed * dt));
    }
  }

  /**
   * move the spaceship left.
   * @param dt
   *          time passed
   */
  public void moveRight(double dt) {
    int xNext = (int) (rect.leftX() + (xSpeed * dt));
    if (xNext < 790 - rect.getWidth()) {
      rect.moveX((int) (xSpeed * dt));
    }
  }

  @Override
  public void timePassed(double dt) {
    timeCounter += dt;
    if (keyboard.isPressed((KeyboardSensor.SPACE_KEY)) && (timeCounter >= 0.35)) {
      this.shoot();
      timeCounter = 0;
    }
    if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
      this.moveLeft(dt);
    } else if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
      this.moveRight(dt);
    }
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
  public void removeFromGame(GameLevel g) {
    // TODO Auto-generated method stub

  }

  @Override
  public void addToGame(GameLevel g) {
    g.addSprite(this);
    g.addHittable(this);
  }

}