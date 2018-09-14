package initializastion;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import biuoop.DrawSurface;
import game.GameLevel;
import logic.Sprite;

/**
 *
 * @author H
 *
 */
public class CreateBackground {

  private Image image;

  /**
   * anonymous background class with an image.
   * @author H
   *
   */
  private class ImageBackground implements Sprite {

    @Override
    public void drawOn(DrawSurface d) {
      d.drawImage(0, 0, image);
    }

    @Override
    public void timePassed(double dt) {
      return;
    }

    @Override
    public void removeFromGame(GameLevel g) {
      // TODO Auto-generated method stub

    }

    @Override
    public void addToGame(GameLevel g) {
      g.addSprite(this);
    }
  };

  /**
   * create a sprite background from an image.
   * @param g game level to add the background sprite to
   * @param filename to read the image from
   */
  public void create(GameLevel g, String filename) {
    InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(filename);
    try {
      this.image = ImageIO.read(is);
    } catch (IOException e) {
      System.out.println("Error: " + e);
    }
    new ImageBackground().addToGame(g);
  }
}
