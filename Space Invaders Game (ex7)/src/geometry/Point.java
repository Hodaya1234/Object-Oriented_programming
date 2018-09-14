package geometry;
/**
 * Class for a point.
 */
public class Point {

  private int x;
  private int y;

  /**
   * constructor.
   * @param x coordinate
   * @param y coordinate
   */
  public Point(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /**
   * constructor.
   * @param x
   *          coordinate
   * @param y
   *          coordinate
   */
  public Point(double x, double y) {
    this((int) x, (int) y);
  }

  /**
   * return the distance of this point to the other point.
   *
   * @param other
   *          the second point
   * @return distance
   */
  public double distance(Point other) {
    double x1 = this.x;
    double x2 = other.getX();
    double y1 = this.y;
    double y2 = other.getY();
    return Math.sqrt((x1 - x2) * (x1 - x2) + ((y1 - y2) * (y1 - y2)));
  }

  /**
   * return true is the points are equal, false otherwise.
   * @param other point
   * @return true if they are the same
   */
  public boolean equals(Point other) {
    return ((this.x == other.getX()) && (this.y == other.getY()));
  }

  /**
   * Return the x value of this point.
   * @return x
   */
  public int getX() {
    return this.x;
  }

  /**
   * Return y value.
   * @return y
   */
  public int getY() {
    return this.y;
  }

  /**
   * change point.
   * @param newX new value
   * @param newY new value
   */
  public void changePoint(int newX, int newY) {
    this.x = newX;
    this.y = newY;
  }
}