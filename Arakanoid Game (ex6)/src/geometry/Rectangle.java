package geometry;
import java.util.ArrayList;
import java.util.List;

/**
 * rectangle shape.
 * @author H
 *
 */
public class Rectangle {

  private Point upperLeft;
  private double width;
  private double height;
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
  public Rectangle(Point upperLeft, double width, double height) {
    this.upperLeft = upperLeft;
    this.width = width;
    this.height = height;
    this.setSides();
  }

  /**
   * return rectangle width.
   * @return width
   */
  public double getWidth() {
    return this.width;
  }

  /**
   * return rectangle height.
   * @return height
   */
  public double getHeight() {
    return this.height;
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
   * create a sides list of: up, right, down, left.
   */
  public void setSides() {
    this.sides = new Line[4];
    Point[] corners = this.corners();
    Point upperRight = corners[1];
    Point downRight = corners[2];
    Point downLeft = corners[3];
    sides[0] = new Line(this.getUpperLeft(), upperRight);
    sides[1] = new Line(upperRight, downRight);
    sides[2] = new Line(downLeft, downRight);
    sides[3] = new Line(this.upperLeft, downLeft);
  }

  /**
   * find four corner points.
   * @return corners
   */
  public Point[] corners() {
    Point[] corners = new Point[4];
    Point upLeft = this.getUpperLeft();
    corners[0] = this.upperLeft;
    //up right:
    corners[1] = new Point(upLeft.getX() + this.width, upLeft.getY());
    //down right:
    corners[2] = new Point(upLeft.getX() + this.width, upLeft.getY() + this.getHeight());
    //down left:
    corners[3] = new Point(upLeft.getX(), upLeft.getY() + this.getHeight());
    return corners;
  }

  /**
   * check if a point is in the rectangle.
   * @param p point
   * @return true if it is, false otherwise
   */
  public boolean isPointInRectangle(Point p) {
    double yUp = this.getUpperLeft().getY();
    double yDown = yUp + this.getHeight();
    double xLeft = this.getUpperLeft().getX();
    double xRight = xLeft + this.getWidth();
    if ((p.getX() >= xRight) && (p.getX() <= xLeft) && (p.getY() >= yUp) && (p.getY() <= yDown)) {
      return true;
    }
    return false;
  }

  /**
   * check intersections with a line.
   * @param line to check intersection
   * @return a list of the intersection points
   */
  public java.util.List<Point> intersectionPoints(Line line) {
    List<Point> interPoints = new ArrayList<Point>();
    for (int i = 0; i < 4; i++) {
      if (this.sides[i].isIntersecting(line)) {
        Point p = this.sides[i].intersectionWith(line);
        interPoints.add(p);;
      }
    }
    return interPoints;
  }

  /**
   * check on which side of the rectangle a point is.
   * @param p point
   * @return the side for which it belongs, or -1 if there is no side.
   */
  public int whichSide(Point p) {
    if (this.sides()[0].isPointInLine(p)) {
      return UP;
    } else if (this.sides()[1].isPointInLine(p)) {
      return RIGHT;
    } else if (this.sides()[2].isPointInLine(p)) {
      return DOWN;
    } else if (this.sides()[3].isPointInLine(p)) {
      return LEFT;
    } else {
      return -1;
    }
  }
}