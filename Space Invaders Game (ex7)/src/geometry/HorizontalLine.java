package geometry;

/**
 *
 * @author H
 *
 */
public class HorizontalLine implements Line {

  private int y;
  private int startX;
  private int endX;

  /**
   * constructor.
   * @param start point
   * @param end point
   */
  public HorizontalLine(Point start, Point end) {
    this.startX = start.getX();
    this.endX = end.getX();
    this.y = start.getY();
  }

  /**
   * Constructor.
   * @param xStart integer
   * @param xEnd integer
   * @param y the same for start and end
   */
  public HorizontalLine(int xStart, int xEnd, int y) {
    this.y = y;
    this.startX = xStart;
    this.endX = xEnd;
  }

  /**
   * Return the length of the line.
   * @return length
   */
  public int length() {
    return Math.abs(endX - startX);
  }

  @Override
  public Point start() {
    return new Point(startX, y);
  }

  @Override
  public Point end() {
    return new Point(endX, y);
  }

  @Override
  public boolean vertical() {
    return false;
  }

  @Override
  public boolean horizontal() {
    return true;
  }

  @Override
  public boolean isIntersecting(Line other) {
    return (intersection(other) != null);
  }

  @Override
  public Point intersection(Line other) {
    if (other.horizontal()) {
      return null;
    }
    if (other.vertical()) {
      Point inter = new Point(other.start().getX(), y);
      if ((isPointInLine(inter)) && (other.isPointInLine(inter))) {
        return inter;
      }
    }
    return null;
  }

  @Override
  public boolean isPointInLine(Point point) {
    boolean between = (((point.getX() <= startX) && (point.getX() >= endX))
        || ((point.getX() >= startX) && (point.getX() <= endX)));
    return ((y == point.getY()) && (between));
  }
}
