package gameobjects;

/**
 * simple counter.
 * @author H
 *
 */
public class Counter {

  private int number;

  /**
   * initialize counter to zero.
   */
  public Counter() {
    this(0);
  }

  /**
   * initialize counter.
   * @param first the number to initialize
   */
  public Counter(int first) {
    this.number = first;
  }

  /**
   * add number to current count.
   * @param num to add
   */
  public void increase(int num) {
    this.number += num;
  }

  /**
   * subtract number from current count.
   * @param num to subtract
   */
  public void decrease(int num) {
    this.number -= num;
  }

  /**
   * get current count.
   * @return number
   */
  public int getValue() {
    return this.number;
  }
}
