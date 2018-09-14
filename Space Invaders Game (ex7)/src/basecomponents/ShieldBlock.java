package basecomponents;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;
import game.GameLevel;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import logic.HitInformation;
import logic.HitListener;
import logic.Hittable;
import logic.Sprite;

/**
 *
 * @author H
 *
 */
public class ShieldBlock implements Sprite, Hittable {

  private Rectangle rect;
  private List<HitListener> listeners;
  private Color color;

  /**
   * Constructor.
   * @param p point of block
   * @param w width
   * @param h height
   */
  public ShieldBlock(Point p, int w, int h) {
    this.rect = new Rectangle(p, w, h);
    this.listeners = new ArrayList<HitListener>();
    this.color = Color.white;
  }

  @Override
  public void addToGame(GameLevel g) {
    g.addSprite(this);
    g.addHittable(this);
  }

  @Override
  public void removeFromGame(GameLevel g) {
    g.removeSprite(this);
    g.removeHittable(this);
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
  public void drawOn(DrawSurface d) {
    d.setColor(color);
    d.fillRectangle(rect.getUpperLeft().getX(), rect.getUpperLeft().getY(), rect.getWidth(), rect.getHeight());
  }

  @Override
  public void timePassed(double dt) {
    // TODO Auto-generated method stub

  }

  @Override
  public void tellHitListeners(HitInformation info) {
    List<HitListener> newList = new ArrayList<HitListener>(listeners);
    for (HitListener h : newList) {
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
}