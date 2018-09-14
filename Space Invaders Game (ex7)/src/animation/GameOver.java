package animation;

import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import biuoop.DrawSurface;

/**
 *
 * @author H
 *
 */
public class GameOver implements Animation {

  private int score;
  private Image image;

  /**
   * Constructor.
   * @param score
   *          to show on the screen
   */
  public GameOver(int score) {
    this.score = score;
    InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("over.jpg");
    try {
      this.image = ImageIO.read(is);
    } catch (IOException e) {
      image = null;
    }
  }

  @Override
  public void doOneFrame(DrawSurface d, double dt) {
    if (image != null) {
      d.drawImage(0, 0, image);
      d.setColor(Color.WHITE);
      d.drawText(338, 545, "" + score, 40);
    } else {
      d.setColor(Color.black);
      d.fillRectangle(0, 0, 800, 600);
      d.setColor(Color.WHITE);
      d.drawText(90, 150, "You lose!", 50);
      d.drawText(200, 300, "You're score is: " + score, 50);
    }
  }

  @Override
  public boolean shouldStop() {
    return false;
  }

}
