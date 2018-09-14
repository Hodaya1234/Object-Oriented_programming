package gameobjects;
import java.awt.Color;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.GameLevel;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;

/**
 * game paddle.
 * @author H
 *
 */
public class Paddle implements Sprite, Collidable {
  private biuoop.KeyboardSensor keyboard;
  private Rectangle rectangle;
  private Color color;
  private double legalLeft;
  private double legalRight;
  private Point start;
  private int speed;

  /**
   * Constructor.
   * @param keyboard sensor
   * @param paddleSpeed d-x Velocity of the paddle
   * @param paddleWidth length of the paddle
   */
  public Paddle(biuoop.KeyboardSensor keyboard, int paddleSpeed, int paddleWidth) {
    this.keyboard = keyboard;
    Point upperLeft = new Point(400 - (paddleWidth / 2), 565);
    this.rectangle = new Rectangle(upperLeft, paddleWidth, 20);
    this.speed = paddleSpeed;
    this.color = new Color(255, 200, 0);
    this.legalLeft = 20;
    this.legalRight = 775;
    this.start = upperLeft;
  }

  /**
   * move the paddle left.
   * @param dt
   *          time passed
   */
  public void moveLeft(double dt) {
    Point upperLeft = new Point(this.rectangle.getUpperLeft().getX() - this.speed / 60,
        this.rectangle.getUpperLeft().getY());
    this.rectangle = new Rectangle(upperLeft, this.rectangle.getWidth(),
      this.rectangle.getHeight());
  }

  /**
   * move the paddle right.
   * @param dt
   *          time passed
   */
  public void moveRight(double dt) {
    Point upperLeft = new Point(this.rectangle.getUpperLeft().getX() + this.speed / 60,
        this.rectangle.getUpperLeft().getY());
    this.rectangle = new Rectangle(upperLeft, this.rectangle.getWidth(),
      this.rectangle.getHeight());
  }

  /**
   * if a key is pressed, move left / right.
   * @param dt
   *          time passed
   */
  public void timePassed(double dt) {
    if ((this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) && (this.legalLeft())) {
      this.moveLeft(dt);
    } else if ((this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) && (this.legalRight())) {
      this.moveRight(dt);
    } else {
      return;
    }
  }

  /**
   * draw the paddle.
   * @param d GUI draw surface
   */
  public void drawOn(DrawSurface d) {
    int x = (int) this.rectangle.getUpperLeft().getX();
    int y = (int) this.rectangle.getUpperLeft().getY();
    int w = (int) this.rectangle.getWidth();
    int h = (int) this.rectangle.getHeight();
    d.setColor(color);
    d.fillRectangle(x, y, w, h);
    d.setColor(Color.black);
    d.drawRectangle(x, y, w, h);
  }

  /**
   * return the paddle rectangle.
   * @return rectangle
   */
  public Rectangle getCollisionRectangle() {
    return this.rectangle;
  }

  /**
   * return the new velocity of the ball after hit.
   * @param hitter the ball that hit
   * @param collisionPoint between the ball and the paddle
   * @param currentVelocity of the ball
   * @return new velocity
   */
  public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
    double paddleY = this.getCollisionRectangle().getUpperLeft().getY();
    if (hitter.getY() > paddleY) {
      hitter.moveBallToPoint(new Point(hitter.getX(), paddleY - 1));
    }
    double x = collisionPoint.getX();
    double left = this.rectangle.getUpperLeft().getX();
    double length = (this.rectangle.getWidth() / 5);
    if (isIn(x, left, left + length)) {
      return Velocity.fromAngleAndSpeed(300, currentVelocity.speed());
    } else if (isIn(x, left + length, left + 2 * length)) {
      return Velocity.fromAngleAndSpeed(330, currentVelocity.speed());
    } else if (isIn(x, left + 2 * length, left + 3 * length)) {
      return Velocity.fromAngleAndSpeed(0, currentVelocity.speed());
    } else if (isIn(x, left + 3 * length, left + 4 * length)) {
      return Velocity.fromAngleAndSpeed(30, currentVelocity.speed());
    } else if (isIn(x, left + 4 * length, left + 5 * length)) {
      return Velocity.fromAngleAndSpeed(60, currentVelocity.speed());
    } else {
      return new Velocity(-currentVelocity.getDx(), -currentVelocity.getDy());
    }
  }

  /**
  * add the paddle to the game.
  * @param g game
  */
  public void addToGame(GameLevel g) {
    g.addCollidable(this);
    g.addSprite(this);
  }

  /**
   * reset the place of the paddle to the middle.
   */
  public void reset() {
    this.rectangle = new Rectangle(start, this.rectangle.getWidth(), this.rectangle.getHeight());
  }

  /**
  * check if an x coordinate is in a certain range.
  * @param x coordinate
  * @param first coordinate
  * @param second coordinate
  * @return true if it is in the range
  */
  public static boolean isIn(double x, double first, double second) {
    if (((first <= x) && (x < second)) ||  ((first >= x) && (x > second))) {
      return true;
    }
    return false;
  }

  /**
  * can the paddle go right.
  * @return true if a right move is legal
  */
  public boolean legalRight() {
    return (this.rectangle.getUpperLeft().getX() + this.rectangle.getWidth() < legalRight);
  }

  /**
  * can a paddle go left.
  * @return true if a left move is legal
  */
  public boolean legalLeft() {
    return (this.rectangle.getUpperLeft().getX() > legalLeft);
  }
}