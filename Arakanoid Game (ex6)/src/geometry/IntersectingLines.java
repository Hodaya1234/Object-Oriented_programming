package geometry;
/**
 * Checks lines intersection.
 * @author H
 *
 */
public class IntersectingLines {

  /**
    * Finds the intersection of two points.
    * @param l1 the first line
    * @param l2 the second line
    * @return intersection point
    */
  public static Point intersection(Line l1, Line l2) {
    double x1start = l1.start().getX();
    double x1end = l1.end().getX();
    double x2start = l2.start().getX();
    double x2end = l2.end().getX();
    if (l1.equals(l2)) {
      return null;
    }
    // checking whether one of the lines is vertical
    if (x1start == x1end) {
      Point intersect = vertical(x1start, l2);
      if ((l1.isPointInLine(intersect)) && (l2.isPointInLine(intersect))) {
        return intersect;
      } else {
        return null;
      }
    }
    if (x2start == x2end) {
      Point intersect = vertical(x2start, l1);
      if ((l1.isPointInLine(intersect)) && (l2.isPointInLine(intersect))) {
        return intersect;
      } else {
        return null;
      }
    }
    // finds intersection using algebra equations
    double y1start = l1.start().getY();
    double y1end = l1.end().getY();
    double y2start = l2.start().getY();
    double y2end = l2.end().getY();
    double m1 = (y1start - y1end) / (x1start - x1end);
    double m2 = (y2start - y2end) / (x2start - x2end);
    double n1 = y1start - m1 * x1start;
    double n2 = y2start - m2 * x2start;
    if (m1 == m2) {
      return null;
    }
    if (n1 == n2) {
      return new Point(0, n1);
    }
    if ((l1.start().equals(l2.start())) || (l1.start().equals(l2.end()))) {
      return l1.start();
    }
    if ((l1.end().equals(l2.start())) || (l1.end().equals(l2.end()))) {
      return l1.end();
    }
    double a = (n2 - n1) / (m1 - m2);
    double b = m1 * a + n1;
    Point intersection = new Point(a, b);
    // checking if the point is on both lines
    if (l1.isPointInLine(intersection) && (l2.isPointInLine(intersection))) {
      return intersection;
    }
    return null;
  }

  /**
    * Finds intersection when one line is vertical.
    *
    * @param x the x coordinate of the vertical line
    * @param l the second line
    * @return intersection of vertical
    */
  public static Point vertical(double x, Line l) {
    double m = (l.start().getY() - l.end().getY()) / (l.start().getX() - l.end().getX());
    double n = l.start().getY() - m * l.start().getX();
    double y = (m * x) + n;
    return new Point(x, y);
  }
}