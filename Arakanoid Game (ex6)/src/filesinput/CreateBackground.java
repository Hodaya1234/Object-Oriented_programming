package filesinput;

import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import biuoop.DrawSurface;
import gameobjects.Sprite;

/**
 *
 * @author H
 *
 */
public class CreateBackground {

  private Image image;
  private Color color;

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
  };

  /**
   * anonymous background class with a color.
   * @author H
   *
   */
  private class ColorBackground implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
      d.setColor(color);
      d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
      ;
    }

    @Override
    public void timePassed(double dt) {
      return;
    }
  };

  /**
   * create a sprite background from an image.
   * @param filename
   *          to read the image from
   * @return the background from image class.
   */
  public Sprite createBackgroundFromFile(String filename) {
    InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(filename);
    try {
      this.image = ImageIO.read(is);
    } catch (IOException e) {
      System.out.println("Error: " + e);
    }
    return new ImageBackground();
  }

  /**
   * create a sprite background from a color.
   * @param backColor
   *          for background
   * @return the sprite
   */
  public Sprite createBackgroundFromColor(String backColor) {
    this.color = new ColorsParser().colorFromString(backColor);
    return new ColorBackground();
  }

}
