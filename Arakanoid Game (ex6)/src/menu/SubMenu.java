package menu;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 *
 * @author H
 *
 * @param <T>
 */
public class SubMenu<T> implements Menu<T> {

  private T status;
  private KeyboardSensor keyboard;
  private List<Selection<T>> options;
  private boolean shouldStop;
  private Image image;

  /**
   * Constructor.
   * @param keyboard
   *          sensor
   * @param image
   *          to draw in the back
   */
  public SubMenu(KeyboardSensor keyboard, Image image) {
    this.keyboard = keyboard;
    this.options = new ArrayList<Selection<T>>();
    this.shouldStop = false;
    this.image = image;
  }

  @Override
  public void doOneFrame(DrawSurface d, double dt) {
    if (image != null) {
      d.drawImage(0, 0, image);
    } else {
      d.setColor(Color.DARK_GRAY);
      d.fillRectangle(0, 0, 800, 600);
    }
    d.setColor(Color.WHITE);
    d.drawText(150, 150, "Difficulty:", 60);
    int i = 250;
    for (Selection<T> s : options) {
      d.drawText(180, i, "[" + s.key() + "] " + s.message(), 40);
      d.drawCircle(150, i - 10, 20);
      i += 70;
      if (keyboard.isPressed(s.key())) {
        this.shouldStop = true;
        this.status = s.returnVal();
        return;
      }
    }
  }

  @Override
  public boolean shouldStop() {
    return shouldStop;
  }

  @Override
  public void addSelection(String key, String message, T returnVal) {
    this.options.add(new Selection<T>(key, message, returnVal));
  }

  @Override
  public T getStatus() {
    return status;
  }

  @Override
  public void addSubMenu(String key, String message, Menu<T> subMenu) {
    return;
  }
}