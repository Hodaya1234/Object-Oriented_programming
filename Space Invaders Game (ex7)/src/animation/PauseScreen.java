package animation;

import biuoop.DrawSurface;

/**
 * When the player pauses the game.
 * @author H
 *
 */
public class PauseScreen implements Animation {

  @Override
  public void doOneFrame(DrawSurface d, double dt) {
    d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
  }

  @Override
  public boolean shouldStop() {
    return false;
  }
}