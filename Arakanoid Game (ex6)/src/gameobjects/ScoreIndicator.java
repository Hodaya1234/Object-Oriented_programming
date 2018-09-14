package gameobjects;

import java.awt.Color;

import biuoop.DrawSurface;

/**
 * Indicate the score earned by the player.
 * @author H
 *
 */
public class ScoreIndicator implements Sprite {

  private Counter score;

  /**
   * Constructor.
   * @param score counter updated in the game
   */
  public ScoreIndicator(Counter score) {
    this.score = score;
  }

  @Override
  public void drawOn(DrawSurface d) {
    d.setColor(Color.BLACK);
    d.drawText(300, 20, "Score: " + Integer.toString(score.getValue()), 20);
  }

  @Override
  public void timePassed(double dt) {
    return;
  }
}
