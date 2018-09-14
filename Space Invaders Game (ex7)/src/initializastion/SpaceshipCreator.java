package initializastion;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import basecomponents.SpaceShip;
import biuoop.KeyboardSensor;
import game.GameLevel;
import geometry.Point;
import logic.BulletCreator;
import logic.BulletRemover;
import logic.Counter;
import logic.SpaceshipListener;

/**
 *
 * @author H
 *
 */
public class SpaceshipCreator {

  /**
   * create the spaceship.
   * @param game level
   * @param keyboard sensor
   * @param lives counter
   */
  public static void create(GameLevel game, KeyboardSensor keyboard, Counter lives) {
    Image image = null;
    InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("ship.jpg");
    try {
      image = ImageIO.read(is);
    } catch (IOException e) {
      System.out.println("Error: " + e);
    }
    BulletCreator bcS = new BulletCreator(game);
    SpaceShip ship = new SpaceShip(new Point(400, 550), bcS, keyboard, image);
    ship.addHitListener(new SpaceshipListener(game, lives));
    ship.addHitListener(new BulletRemover(game));
    ship.addToGame(game);
  }
}
