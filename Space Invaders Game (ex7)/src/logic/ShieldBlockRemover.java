package logic;

import game.GameLevel;

/**
 *
 * @author H
 *
 */
public class ShieldBlockRemover implements HitListener {

  private GameLevel game;

  /**
   * Constructor.
   * @param g game level to remove the shield from when there is a hit
   */
  public ShieldBlockRemover(GameLevel g) {
    this.game = g;
  }

  @Override
  public void hitHappened(HitInformation info) {
      info.getWasHit().removeFromGame(game);
  }

}
