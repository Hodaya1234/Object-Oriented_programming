package basecomponents;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;
import game.GameLevel;
import geometry.Line;
import geometry.Point;
import geometry.VerticalLine;
import logic.HitInformation;
import logic.Hittable;
import logic.Shootable;
import logic.Sprite;

/**
 *
 * @author H
 *
 */
public class Bullet implements Sprite {

  private Point center;
  private int radius;
  private Shootable shooter;
  private int speed;
  private int direction;
  private Color color;
  private List<Hittable> hittables;

  public static final int UP = -1;
  public static final int DOWN = 1;

  /**
   * constructor.
   * @param shooter that shot the bullet
   * @param center of the bullet
   * @param radius of the bullet
   * @param direction of movement
   * @param speed of movement
   * @param color of the bullet
   */
  public Bullet(Shootable shooter, Point center, int radius, int direction, int speed, Color color) {
    this.shooter = shooter;
    this.direction = direction;
    this.center = center;
    this.radius = radius;
    this.speed = speed;
    this.direction = direction;
    this.color = color;
    this.hittables = new ArrayList<Hittable>();
  }

  @Override
  public void addToGame(GameLevel g) {
    g.addBullet(this);
  }

  @Override
  public void removeFromGame(GameLevel g) {
    g.removeBullet(this);
  }

  /**
   * gets the list of hittable objects from the game for each step.
   * @param l the list
   */
  public void setHittables(List<Hittable> l) {
    this.hittables = l;
  }

  @Override
  public void drawOn(DrawSurface d) {
    d.setColor(color);
    d.fillCircle(center.getX(), center.getY(), radius);
  }

  @Override
  public void timePassed(double dt) {
    Point next = new Point(center.getX(), center.getY() + direction * speed * dt);
    Line trajectory = new VerticalLine(center.getX(), center.getY(), next.getY());
    List<Hittable> list = new ArrayList<Hittable>(hittables);
    Point closestP = null;
    Point current = null;
    Hittable closestH = null;
    for (Hittable h : list) {
      if (h.isInTrajectory(trajectory)) {
        current = h.closestCollision(trajectory);
        if ((closestP == null) || (center.distance(closestP) > center.distance(current))) {
          closestP = h.closestCollision(trajectory);
          closestH = h;
        }
      }
    }
    if (closestP != null) {
      closestH.tellHitListeners(new HitInformation(shooter, closestH, this));
    } else {
      this.center = next;
    }
  }
}