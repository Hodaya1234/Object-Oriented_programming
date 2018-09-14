package animation;

import java.awt.Color;

import biuoop.DrawSurface;
import logic.SpriteCollection;

/**
 * A count-down before the beginning of the game.
 * @author H
 *
 */
public class CountdownAnimation implements Animation {

  private SpriteCollection sprites;
  private boolean shouldStop;
  private double numOfMilliSeconds;
  private int countFrom;
  private int currentCount;
  private long startTime;
  private double interval;

  /**
   * Constructor.
   * @param numOfSeconds for the screen
   * @param countFrom which number
   * @param sprites all the objects of the game
   */
  public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection sprites) {
    this.sprites = sprites;
    this.numOfMilliSeconds = 1000 * numOfSeconds;
    this.countFrom = countFrom;
    this.currentCount = countFrom;
    // this.gameScreen = gameScreen;
    this.shouldStop = false;
    this.startTime = System.currentTimeMillis();
    this.interval = 1000 * numOfSeconds / (double) (countFrom + 1);
  }

  @Override
  public void doOneFrame(DrawSurface d, double dt) {
    d.setColor(Color.black);
    d.fillRectangle(0, 0, 800, 600);
    this.sprites.drawAllOn(d);
    long passedTime = System.currentTimeMillis() - this.startTime;
    if (passedTime > numOfMilliSeconds) {
      this.shouldStop = true;
    }
    if (passedTime > interval * (this.countFrom - this.currentCount + 1)) {
      this.currentCount--;
    }
    if (this.currentCount < 1) {
    d.setColor(Color.BLACK);
    d.drawText(350, 300, "Go!", 70);
    d.setColor(Color.WHITE);
    d.drawText(352, 302, "Go!", 66);
  } else {
    d.setColor(Color.BLACK);
    d.drawText(380, 300, Integer.toString(this.currentCount), 70);
    d.setColor(Color.white);
    d.drawText(382, 302, Integer.toString(this.currentCount), 66);
  }
  }

  @Override
  public boolean shouldStop() {
    return this.shouldStop;
  }
}
