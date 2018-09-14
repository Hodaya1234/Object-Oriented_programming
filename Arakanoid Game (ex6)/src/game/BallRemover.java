package game;

import gameobjects.Ball;
import gameobjects.Block;
import gameobjects.Counter;

/**
 * Ball Remover.
 * @author H
 *
 */
public class BallRemover implements HitListener {

  private GameLevel game;
  private Counter remainingBalls;

  /**
   * Constructor.
   * @param game the game object
   * @param balls how many balls are in the game
   */
  public BallRemover(GameLevel game, Counter balls) {
    this.game = game;
    this.remainingBalls = balls;
  }

  @Override
  public void hitEvent(Block beingHit, Ball hitter) {
    hitter.removeFromGame(this.game);
    this.remainingBalls.decrease(1);
  }

}
