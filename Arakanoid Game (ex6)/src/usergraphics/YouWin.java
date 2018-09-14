package usergraphics;

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
public class YouWin implements Animation {

  private int score;
  private Image image;

  /**
   * Constructor.
   * @param score
   *          to display
   */
  public YouWin(int score) {
    this.score = score;
    InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("background_images/lamb.jpg");
    try {
      this.image = ImageIO.read(is);
    } catch (IOException e) {
      this.image = null;
    }
  }

  @Override
  public void doOneFrame(DrawSurface d, double dt) {
    if (image != null) {
      d.drawImage(0, 0, image);
      d.setColor(Color.WHITE);
      d.drawText(635, 425, "" + score, 50);
    } else {
      d.setColor(Color.pink);
      d.fillRectangle(0, 0, 800, 600);
      d.setColor(Color.WHITE);
      d.drawText(90, 300, "You Win! Your score is " + score, 50);
    }
  }

  @Override
  public boolean shouldStop() {
    // TODO Auto-generated method stub
    return false;
  }
}