package geometry;

/**
 * Line class.
 *
 */
public class VerticalLine implements Line {

  private int x;
  private int startY;
  private int endY;

  /**
   * constructor.
   * @param start point
   * @param end point
   */
  public VerticalLine(Point start, Point end) {
    this.startY = start.getY();
    this.endY = end.getY();
    this.x = start.getX();
  }

  /**
   * Constructor.
   * @param x the same for start and end
   * @param yStart integer
   * @param yEnd integer
   */
  public VerticalLine(int x, int yStart, int yEnd) {
    this.x = x;
    this.startY = yStart;
    this.endY = yEnd;
  }

  /**
   * Return the length of the line.
   * @return length
   */
  public int length() {
    return Math.abs(endY - startY);
  }

  /**
   * Returns the start point of the line.
   * @return start
   */
  public Point start() {
    return new Point(x, this.startY);
  }

  @Override
  public Point end() {
    return new Point(x, this.endY);
  }

  @Override
  public boolean vertical() {
    return true;
  }

  @Override
  public boolean horizontal() {
    return false;
  }

  @Override
  public boolean isIntersecting(Line other) {
    return (intersection(other) != null);
  }

  @Override
  public Point intersection(Line other) {
    if (other.vertical()) {
      return null;
    }
    if (other.horizontal()) {
      Point inter = new Point(x, other.start().getY());
      if ((isPointInLine(inter)) && (other.isPointInLine(inter))) {
        return inter;
      }
    }
    return null;
  }

  @Override
  public boolean isPointInLine(Point point) {
    boolean between = (((point.getY() <= startY) && (point.getY() >= endY))
        || ((point.getY() >= startY) && (point.getY() <= endY)));
    return ((x == point.getX()) && (between));
  }
}