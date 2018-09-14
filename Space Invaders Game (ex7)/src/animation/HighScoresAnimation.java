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
public class HighScoresAnimation implements Animation {

  private HighScoresTable scores;
  private Image image;

  /**
   * Constructor.
   * @param scores
   *          table to display
   */
  public HighScoresAnimation(HighScoresTable scores) {
    this.scores = scores;
    InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("table.jpg");
    try {
      this.image = ImageIO.read(is);
    } catch (IOException e) {
      image = null;
    }
  }

  @Override
  public void doOneFrame(DrawSurface d, double dt) {
    d.drawImage(0, 0, image);
    int max = scores.getsize();
    if (max > HighScoresTable.SIZE) {
      max = HighScoresTable.SIZE;
    }
    for (int i = 0; i < max; i++) {
      ScoreInfo info = scores.getHighScores().get(i);
      String text = i + 1 + ") " + info.getName() + ": " + info.getScore();
      if (text.length() >= 25) {
        text = text.substring(0, 24);
      }
      d.setColor(Color.white);
      d.drawText(90, 220 + i * (340 / HighScoresTable.SIZE), text, 40);
    }
  }

  @Override
  public boolean shouldStop() {
    return false;
  }
}