package geometry;

/**
 * Line class.
 *
 */
public class Line {
  private Point start;
  private Point end;

  /**
   * constructor.
   * @param start point
   * @param end point
   */
  public Line(Point start, Point end) {
    this.start = start;
    this.end = end;
  }

  /**
  * constructor.
  * @param x1 of start point
  * @param y1 of start point
  * @param x2 of end point
  * @param y2 of end point
  */
  public Line(double x1, double y1, double x2, double y2) {
    this(new Point(x1, y1), new Point(x2, y2));
  }

  /**
   * calculate slope of the line.
   * @return slope
   */
  public double slope() {
    return (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
  }

  /**
   * Return the length of the line.
   * @return length
   */
  public double length() {
    return this.start.distance(end);
  }

  /**
    * Find the middle point of a line.
    *@return middle
    */
  public Point middle() {
    double x = (this.start.getX() + this.end.getX()) / 2;
    double y = (this.start.getY() + this.end.getY()) / 2;
    return new Point(x, y);
  }

  /**
   * Returns the start point of the line.
   * @return start
   */
  public Point start() {
    return this.start;
  }

  /**
   * Returns the end point of the line.
   * @return end
   */
  public Point end() {
    return this.end;
  }

  /**
    * Returns true if the lines intersect, false otherwise.
    *
    * @param other the second line
    * @return whether the lines intersect
    */
  public boolean isIntersecting(Line other) {
    return (intersectionWith(other) != null);
  }

  /**
   * Returns the intersection point if the lines intersect and null otherwise.
   * @param other line
   * @return intersection point
   */
  public Point intersectionWith(Line other) {
    return IntersectingLines.intersection(this, other);
  }

  /**
   * returns true if a point is in a line.
   * @param point the point to check
   * @return whether the point is in the line
   */
  public boolean isPointInLine(Point point) {
    return point.distance(this.start) + point.distance(this.end) < this.start.distance(this.end) + 1;
  }

  /**
   * return true is the lines are equal, false otherwise.
   * @param other line
   * @return whether the lines are equal
   */
  public boolean equals(Line other) {
    return (this.start.equals(other.start()) && (this.end.equals(other.end())));
  }

  /**
   * If this line does not intersect with the rectangle, return null.
   * Otherwise, return the closest intersection point to the start of the line.
   * @param rect the rectangle
   * @return null or the closest intersection
   */
  public Point closestIntersectionToStartOfLine(Rectangle rect) {
    java.util.List<Point> points = rect.intersectionPoints(this);
    if (points.isEmpty()) {
      return null;
    }
    if ((points.size() == 1)
        || (points.get(0).distance(this.start()) < points.get(1).distance(this.start()))) {
      return points.get(0);
    }
    return points.get(1);
  }
}