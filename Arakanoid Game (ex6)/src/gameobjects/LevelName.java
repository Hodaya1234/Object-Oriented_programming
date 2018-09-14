package gameobjects;

import java.awt.Color;

import biuoop.DrawSurface;

/**
 * Level name.
 * @author H
 *
 */
public class LevelName implements Sprite {
  private String name;

  /**
   * Constructor.
   * @param name level
   */
  public LevelName(String name) {
    this.name = name;
  }

  @Override
  public void drawOn(DrawSurface d) {
    //draw the name on top of the surface
    d.setColor(Color.black);
    d.drawText(510, 20, "Level Name: " + this.name, 20);
  }

  @Override
  public void timePassed(double dt) {
    return;
  }
}
