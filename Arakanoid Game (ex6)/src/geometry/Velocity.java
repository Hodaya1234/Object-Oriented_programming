package geometry;
/**
 * Velocity specifies the change in position on the `x` and the `y` axes.
 * @author H
 *
 */
public class Velocity {
  private double dx;
  private double dy;

  /**
   * constructor.
   * @param dx x difference
   * @param dy y difference
   */
  public Velocity(double dx, double dy) {
    this.dx = dx;
    this.dy = dy;
  }

  /**
   * Return x difference.
   * @return x
   */
  public double getDx() {
    return this.dx;
  }

  /**
   * Return y difference.
   * @return y
   */
  public double getDy() {
    return this.dy;
  }

  /**
   * calculate speed size.
   * @return speed
   */
  public double speed() {
    return Math.sqrt((this.dy * this.dy) + (this.dx * this.dx));
  }

  /**
   * Create velocity from angle and speed.
   *
   * @param angle of the direction
   * @param speed the velocity size
   * @return velocity
   */
  public static Velocity fromAngleAndSpeed(double angle, double speed) {
    double radAngle = Math.toRadians(angle % 360);
    double dx = speed * Math.sin(radAngle);
    double dy = (-1) * (speed * Math.cos(radAngle));
    return new Velocity(dx, dy);
  }

  /**
   * Take a point with position (x,y) and return a new point with position.
   * @param p old point
   * @return new point
   */
  public Point applyToPoint(Point p) {
    return new Point(p.getX() + dx, p.getY() + dy);
  }
}
