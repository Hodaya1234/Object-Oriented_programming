package geometry;

/**
 * rectangle shape.
 * @author H
 *
 */
public class Rectangle {

  private Point upperLeft;
  private int width;
  private int height;
  private Line[] sides;   //a sides array of: up, right, down, left

  public static final int UP = 0;
  public static final int RIGHT = 1;
  public static final int DOWN = 2;
  public static final int LEFT = 3;

  /**
   * constructor.
   * @param upperLeft point
   * @param width of the rectangle
   * @param height of the rectangle
   */
  public Rectangle(Point upperLeft, int width, int height) {
    this.upperLeft = upperLeft;
    this.width = width;
    this.height = height;
    this.setSides();
  }

  /**
   * return rectangle width.
   * @return width
   */
  public int getWidth() {
    return this.width;
  }

  /**
   * return rectangle height.
   * @return height
   */
  public int getHeight() {
    return this.height;
  }

  /**
   * return left side.
   * @return left
   */
  public int leftX() {
    return upperLeft.getX();
  }

  /**
   * return top side.
   * @return top
   */
  public int upY() {
    return upperLeft.getY();
  }

  /**
   * Returns the upper-left point of the rectangle.
   * @return upper left
   */
  public Point getUpperLeft() {
    return this.upperLeft;
  }

  /**
   * return rectangle four side lines.
   * @return sides
   */
  public Line[] sides() {
    return this.sides;
  }

  /**
   * move y by a few pixels.
   * @param dy difference
   */
  public void moveY(int dy) {
    upperLeft = new Point(upperLeft.getX(), upperLeft.getY() + dy);
    this.setSides();
  }

  /**
   * move a by a few pixels.
   * @param dx difference
   */
  public void moveX(int dx) {
    upperLeft = new Point(upperLeft.getX() + dx, upperLeft.getY());
    this.setSides();
  }

  /**
   * create a sides list of: up, right, down, left.
   */
  public void setSides() {
    this.sides = new Line[4];
    int x = upperLeft.getX();
    int y = upperLeft.getY();
    sides[UP] = new HorizontalLine(x, x + width, y);
    sides[RIGHT] = new VerticalLine(x + width, y, y + height);
    sides[DOWN] = new HorizontalLine(x, x + width, y + height);
    sides[LEFT] = new VerticalLine(x, y, y + height);
  }

  /**
   * check intersections with a line.
   * @param line to check intersection
   * @return a list of the intersection points
   */
  public Point closestIntersection(Line line) {
    Point closest = null;
    for (int i = 0; i < 4; i++) {
      if (this.sides[i].isIntersecting(line)) {
        Point p = sides[i].intersection(line);
        if ((closest == null) || (closest.distance(line.start()) > p.distance(line.start()))) {
          closest = p;
        }
      }
    }
    return closest;
  }

  /**
   * is there an intersection of the rectangle with a line.
   * @param l the line
   * @return true if there is
   */
  public boolean isIntersecting(Line l) {
    return (closestIntersection(l) != null);
  }
}