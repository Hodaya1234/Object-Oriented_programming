package game;

import gameobjects.Ball;
import gameobjects.Block;
import gameobjects.Counter;

/**
 * Block Remover.
 * @author H
 *
 */
public class BlockRemover implements HitListener {
  private GameLevel game;
  private Counter remainingBlocks;

  /**
   * Constructor.
   * @param game the game object
   * @param remainingBlocks how many blocks are left
   */
  public BlockRemover(GameLevel game, Counter remainingBlocks) {
    this.game = game;
    this.remainingBlocks = remainingBlocks;
  }

  @Override
  public void hitEvent(Block beingHit, Ball hitter) {
    // Blocks that are hit and reach 0 hit-points should be removed from the game.
    if (beingHit.getHitPoints() < 1) {
      beingHit.removeHitListener(this);
      this.remainingBlocks.decrease(1);
      beingHit.removeFromGame(game);
    }
  }

}
