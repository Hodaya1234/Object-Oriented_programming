package gameobjects;
import java.awt.Color;

import biuoop.DrawSurface;
import game.CollisionInfo;
import game.GameEnvironment;
import game.GameLevel;
import geometry.Line;
import geometry.Point;
import geometry.Velocity;

/**
 * Ball class.
 * @author H
 *
 */
public class Ball implements Sprite {
  private Point center;
  private int radius;
  private java.awt.Color color;
  private Velocity velocity;
  private GameEnvironment environment;

  /**
   * constructor.
   * @param center point
   * @param r radius
   * @param color of the ball
   */
  public Ball(Point center, int r, java.awt.Color color) {
    this((int) center.getX(), (int) center.getY(), r, color);
  }

  /**
   * constructor.
   * @param x the x location
   * @param y the y location
   * @param r radius the circle's radius
   * @param color the color of the ball
   */
  public Ball(int x, int y, int r, java.awt.Color color) {
    this.center = new Point(x, y);
    this.radius = r;
    this.color = color;
  }

  /**
   * setting game environment.
   * @param e the environment
   */
  public void setEnvironment(GameEnvironment e) {
    this.environment = e;
  }

  /**
   * return center.
   * @return ball center Point
   */
  public Point getCenter() {
    return this.center;
  }

  /**
   * Return x center.
   * @return x
   */
  public int getX() {
    return (int) this.center.getX();
  }

  /**
   * Return y center.
   * @return y
   */
  public int getY() {
    return (int) this.center.getY();
  }

  /**
   * Return radius.
   * @return radius
   */
  public int getSize() {
    return this.radius;
  }

  /**
   * Return color.
   * @return color
   */
  public java.awt.Color getColor() {
    return this.color;
  }

  /**
   * draw the ball on the given DrawSurface.
   * @param d surface to draw on
   */
  public void drawOn(DrawSurface d) {
    d.setColor(this.color);
    d.fillCircle(this.getX(), this.getY(), this.radius);
    d.setColor(Color.black);
    d.drawCircle(this.getX(), this.getY(), this.radius);
  }

  /**
   * Set velocity of the ball.
   * @param v velocity class
   */
  public void setVelocity(Velocity v) {
    this.velocity = v;
  }

  /**
   * Set Velocity from x and y differences.
   * @param dx x difference
   * @param dy y difference
   */
  public void setVelocity(double dx, double dy) {
    this.velocity = new Velocity(dx, dy);
  }

  /**
   * Return Velocity.
   * @return Velocity
   */
  public Velocity getVelocity() {
    return this.velocity;
  }

  /**
   * add a ball to the game sprites.
   * @param g the game
   */
  public void addToGame(GameLevel g) {
    g.addSprite(this);
  }

  /**
   * Remove the ball from the game.
   * @param g the game
   */
  public void removeFromGame(GameLevel g) {
    g.removeSprite(this);
  }

  /**
   * moves the ball for the next frame.
   * @param dt
   *          time passed
   */
  public void moveOneStep(double dt) {
    Point next = new Velocity(this.velocity.getDx() * dt, this.velocity.getDy() * dt).applyToPoint(this.getCenter());
    Line trajectory = new Line(this.center, next);
    CollisionInfo info = this.environment.getClosestCollision(trajectory);
    if (info == null) {
      // if there are no collisions, move on trajectory
      this.center = trajectory.end();
    } else {
      //move the ball near collision point, and change velocity
      this.moveNearCollision(info);
      this.setVelocity(info.collisionObject().hit(this, info.collisionPoint(), this.getVelocity()));
    }
  }

  /**
   * moves the ball near the collision.
   * @param info - the object and point
   */
  public void moveNearCollision(CollisionInfo info) {
    int side = info.collisionObject().getCollisionRectangle().whichSide(info.collisionPoint());
    double dx = this.getVelocity().getDx();
    double dy = this.getVelocity().getDy();
    Point c = info.collisionPoint();
    if ((dx > 0) && (dy > 0)) {
      this.center = new Point(c.getX() - 1, c.getY() - 1);
    } else if ((dx < 0) && (dy > 0)) {
      this.center = new Point(c.getX() + 1, c.getY() - 1);
    } else if ((dx > 0) && (dy < 0)) {
      this.center = new Point(c.getX() - 1, c.getY() + 1);
    } else if ((dx < 0) && (dy < 0)) {
      this.center = new Point(c.getX() + 1, c.getY() + 1);
    } else if (side == 0) {
      this.center = new Point(c.getX(), c.getY() - 1);
    } else if (side == 1) {
      this.center = new Point(c.getX() + 1, c.getY());
    } else if (side == 2) {
      this.center = new Point(c.getX(), c.getY() + 1);
    } else if (side == 3) {
      this.center = new Point(c.getX() - 1, c.getY());
    } else {
      this.center = new Line(this.center, c).middle();
    }
  }

  /**
   * moving a ball to a point p.
   * @param p the point
   */
  public void moveBallToPoint(Point p) {
    this.center = p;
  }

  /**
   * let the ball now time passed and it needs to move.
   * @param dt
   *          time passed
   */
  public void timePassed(double dt) {
    this.moveOneStep(dt);
  }
}