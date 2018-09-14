package animation;

import java.awt.Color;

import biuoop.DrawSurface;
import game.GameLevel;
import logic.Counter;
import logic.Sprite;

/**
 * writes relevant information on the screen.
 * @author H
 *
 */
public class ScreenWriter implements Sprite {

  private Counter score;
  private Counter lives;
  private int level;

  /**
   * Constructor.
   * @param score of the game
   * @param lives remaining
   * @param level of the game
   */
  public ScreenWriter(Counter score, Counter lives, int level) {
    this.score = score;
    this.lives = lives;
    this.level = level;
  }

  @Override
  public void drawOn(DrawSurface d) {
    d.setColor(Color.white);
    d.drawText(5, 20, "level: " + level + "  score: " + score.getValue() + "  lives: " + lives.getValue(), 20);
  }

  @Override
  public void timePassed(double dt) {
  }

  @Override
  public void removeFromGame(GameLevel g) {
  }

  @Override
  public void addToGame(GameLevel g) {
  }
}