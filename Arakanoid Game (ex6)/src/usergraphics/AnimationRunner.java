package usergraphics;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * Animation Runner.
 * @author H
 *
 */
public class AnimationRunner {
  private GUI gui;
  private double framesPerSecond;
  private Sleeper sleeper;

  /**
   * Constructor.
   * @param gui
   *          graphic interface for the animation
   * @param framesPerSecond
   *          frames for each second
   */
  public AnimationRunner(GUI gui, double framesPerSecond) {
  this.gui = gui;
  this.framesPerSecond = framesPerSecond;
  this.sleeper = new Sleeper();
  }

  /**
   * main run function of the animation.
   * @param animation interface
   */
  public void run(Animation animation) {
    double millisecondsPerFrame = 1000 / this.framesPerSecond;
    while (!animation.shouldStop()) {
      long startTime = System.currentTimeMillis(); // timing
      DrawSurface d = gui.getDrawSurface();

      animation.doOneFrame(d, 1 / this.framesPerSecond); //dt!!

      gui.show(d);
      long usedTime = System.currentTimeMillis() - startTime;
      long milliSecondLeftToSleep = (long) (millisecondsPerFrame - usedTime);
      if (milliSecondLeftToSleep > 0) {
        this.sleeper.sleepFor(milliSecondLeftToSleep);
      }
    }
  }
}
