package logic;

/**
 *
 * @author H
 *
 */
public class ScoreTracker implements HitListener {

  private Counter score;

  /**
   * Constructor.
   * @param score counter to update when a hit happens
   */
  public ScoreTracker(Counter score) {
    this.score = score;
  }

  @Override
  public void hitHappened(HitInformation info) {
    score.increase(100);
  }

}
