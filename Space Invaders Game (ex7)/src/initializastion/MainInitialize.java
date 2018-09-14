package initializastion;

import animation.ScreenWriter;
import biuoop.KeyboardSensor;
import game.GameLevel;
import logic.Counter;
import logic.ScoreTracker;

/**
 *
 * @author H
 *
 */
public class MainInitialize {

  /**
   * Constructor.
   * @param game level to add all the objects to
   * @param keyboard sensor
   * @param score counter
   * @param lives counter
   * @param level number
   */
  public static void initialize(GameLevel game, KeyboardSensor keyboard, Counter score,
      Counter lives, int level) {

    ScoreTracker tracker = new ScoreTracker(score);
    AlienCreator.aliensCreator(game, tracker, level);

    SpaceshipCreator.create(game, keyboard, lives);

    ShieldCreator.addShields(game, 485);

    game.addSprite(new ScreenWriter(score, lives, level));
  }
}