package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 *
 * @author H
 *
 */
public class KeyPressStoppableAnimation implements Animation {

  private KeyboardSensor sensor;
  private String key;
  private Animation animation;
  private boolean isAlreadyPressed;

  /**
   * Constructor.
   * @param sensor
   *          keyboard
   * @param key
   *          to stop the animation
   * @param animation
   *          to run
   */
  public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
    this.sensor = sensor;
    this.key = key;
    this.animation = animation;
    this.isAlreadyPressed = true;
  }

  @Override
  public void doOneFrame(DrawSurface d, double dt) {
    this.animation.doOneFrame(d, dt);
    if (!sensor.isPressed(key)) {
      isAlreadyPressed = false;
    }
  }

  @Override
  public boolean shouldStop() {
    if (isAlreadyPressed) {
      return false;
    }
    return sensor.isPressed(key);
  }
}