package logic;

import java.awt.Color;

import basecomponents.Bullet;
import game.GameLevel;
import geometry.Point;

/**
 * will hold the game in order to add the bullet to its components will be held
 * by each shootable!
 * @author H
 *
 */
public class BulletCreator {

  private GameLevel game;

  /**
   * Constructor.
   * @param g game level to add to
   */
  public BulletCreator(GameLevel g) {
    this.game = g;
  }

  /**
   * create a bullet and add it to the game.
   * @param shooter that shot
   * @param center of the bullet
   * @param radius of the bullet
   * @param direction up or down
   * @param speed of moving
   * @param color of the bullet
   */
  public void createBullet(Shootable shooter, Point center, int radius, int direction, int speed, Color color) {
    game.addBullet(new Bullet(shooter, center, radius, direction, speed, color));
  }
}
