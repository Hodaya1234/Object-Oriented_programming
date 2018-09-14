package gameobjects;

import java.awt.Color;

import biuoop.DrawSurface;

/**
 * The objects that indicates hoe many lives are left to play.
 * @author H
 *
 */
public class LivesIndicator implements Sprite {

  private Counter lives;

  /**
   * Coonstructor.
   * @param lives to play
   */
  public LivesIndicator(Counter lives) {
    this.lives = lives;
  }

  @Override
  public void drawOn(DrawSurface d) {
    d.setColor(Color.black);
    if (lives.getValue() == 0) {
      d.drawText(50, 20, "No lives! Be careful!", 20);
    } else {
      d.drawText(50, 20, "Lives: " + Integer.toString(this.lives.getValue()), 20);
    }
  }

  @Override
  public void timePassed(double dt) {
    return;
  }
}