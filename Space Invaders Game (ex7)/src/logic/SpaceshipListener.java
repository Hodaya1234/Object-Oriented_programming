package logic;

import game.GameLevel;

/**
 *
 * @author H
 *
 */
public class SpaceshipListener implements HitListener {

  private GameLevel game;
  private Counter lives;

  /**
   * Constructor.
   * @param game level
   * @param lives left counter
   */
  public SpaceshipListener(GameLevel game, Counter lives) {
    this.game = game;
    this.lives = lives;
  }

  @Override
  public void hitHappened(HitInformation info) {
    game.spaceShipHit();
    lives.decrease(1);
  }
}