package logic;

import game.GameLevel;

/**
 *
 * @author H
 *
 */
public class BulletRemover implements HitListener {

  private GameLevel game;

  /**
   * removes a bullet from the game once got hit.
   * @param g the game.
   */
  public BulletRemover(GameLevel g) {
    this.game = g;
  }

  @Override
  public void hitHappened(HitInformation info) {
    info.getBullet().removeFromGame(game);
  }
}