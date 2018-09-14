package initializastion;

import basecomponents.ShieldBlock;
import game.GameLevel;
import geometry.Point;
import logic.BulletRemover;
import logic.ShieldBlockRemover;

/**
 *
 * @author H
 *
 */
public class ShieldCreator {

  /**
   * add the shield building blocks.
   * @param game level
   * @param y position
   */
  public static void addShields(GameLevel game, int y) {
    int spaceLength = 50;
    int numOfBlocks = 40;
    ShieldBlockRemover sr = new ShieldBlockRemover(game);
    BulletRemover br = new BulletRemover(game);

    for (int i = 0; i < 3; i++) {
      int x = spaceLength * (i + 1) + 200 * i;
      for (int k = 0; k < 4; k++) {
        for (int j = 0; j < numOfBlocks; j++) {
          ShieldBlock s = new ShieldBlock(new Point(x + 5 * j, y + 5 * k), 5, 5);
          s.addHitListener(br);
          s.addHitListener(sr);
          s.addToGame(game);
        }
      }
    }
  }
}
