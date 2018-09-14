package basecomponents;

/**
 * column and group.
 * @author H
 *
 */
public interface AlienContainer {

  /**
   * how many aliens are in the collection.
   * @return the number of aliens
   */
  int howMany();

  /**
   * move the aliens.
   * @param dx difference in x axes
   * @param dy difference in y axes
   */
  void move(int dx, int dy);

  /**
   * get the left border of the aliens.
   * @return x presentation
   */
  int left();

  /**
   * get the right border of the aliens.
   * @return x presentation
   */
  int right();

  /**
   * get the bottom border of the aliens.
   * @return y presentation
   */
  int bottom();

  /**
   * get the top border of the aliens.
   * @return y presentation
   */
  int top();

}
