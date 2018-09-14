package game;

import gameobjects.Ball;
import gameobjects.Block;
import gameobjects.Counter;

/**
 * Tracks the score.
 * @author H
 *
 */
public class ScoreTrackingListener implements HitListener {

  private Counter currentScore;

  /**
   * Constructor.
   * @param scoreCounter to update the score of the game.
   */
  public ScoreTrackingListener(Counter scoreCounter) {
    this.currentScore = scoreCounter;
  }

  @Override
  public void hitEvent(Block beingHit, Ball hitter) {
    //when a hit is made, update the score of the game.
    this.currentScore.increase(5);
    if (beingHit.getHitPoints() < 1) {
      this.currentScore.increase(10);
    }
  }
}
