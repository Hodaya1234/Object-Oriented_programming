package menu;

import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 *
 * @author H
 *
 * @param <T>
 */
public class MenuAnimation<T> implements Menu<T> {

  private T status;
  private KeyboardSensor keyboard;
  private List<Selection<T>> options;
  private boolean shouldStop;
  private Image image;

  /**
   * Constructor.
   * @param keyboard sensor
   */
  public MenuAnimation(KeyboardSensor keyboard) {
    this.keyboard = keyboard;
    this.options = new ArrayList<Selection<T>>();
    this.shouldStop = false;
    InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("space.jpg");
    try {
      this.image = ImageIO.read(is);
    } catch (IOException e) {
      System.out.println("Error: " + e);
    }
  }

  @Override
  public void doOneFrame(DrawSurface d, double dt) {
    d.drawImage(0, 0, image);
    d.setColor(Color.WHITE);
    d.drawText(150, 150, "Space Invaders", 70);
    int i = 250;
    for (Selection<T> s : options) {
      d.drawText(180, i, s.message(), 40);
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
    return this.shouldStop;
  }

  @Override
  public void addSelection(String key, String message, T returnVal) {
    this.options.add(new Selection<T>(key, message, returnVal));
  }

  @Override
  public T getStatus() {
    return status;
  }
}