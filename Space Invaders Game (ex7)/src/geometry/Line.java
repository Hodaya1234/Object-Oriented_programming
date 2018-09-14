package geometry;

/**
 *
 * @author H
 *
 */
public interface Line {

  /**
   * start of the line.
   * @return start point
   */
  Point start();

  /**
   * end of the line.
   * @return end point
   */
  Point end();

  /**
   * is the line vertical.
   * @return true if x is the same for start and end
   */
  boolean vertical();

  /**
   * is the line horizontal.
   * @return true if y is the same for start and end
   */
  boolean horizontal();

  /**
   * is a certain point inside the line.
   * @param point to check
   * @return true if the point is on the line
   */
  boolean isPointInLine(Point point);

  /**
   * find the intersection point of two lines.
   * @param other line
   * @return the point or null if it does not exist
   */
  Point intersection(Line other);

  /**
   * is there an intersection point for two lines.
   * @param other line
   * @return true if the inttersection point is not null
   */
  boolean isIntersecting(Line other);
}