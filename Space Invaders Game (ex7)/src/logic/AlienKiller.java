package logic;

import game.GameLevel;

/**
 * killing the aliens once hit.
 * @author H
 *
 */
public class AlienKiller implements HitListener {

  private GameLevel game;

  /**
   * Constructor.
   * @param game level to add the killer to.
   */
  public AlienKiller(GameLevel game) {
    this.game = game;
  }

  @Override
  public void hitHappened(HitInformation info) {
    info.getWasHit().removeFromGame(game);
  }
}
