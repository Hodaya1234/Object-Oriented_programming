package initializastion;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import basecomponents.Alien;
import basecomponents.AlienColumn;
import basecomponents.AlienGroup;
import game.GameLevel;
import logic.AlienKiller;
import logic.BulletCreator;
import logic.BulletRemover;
import logic.ScoreTracker;

/**
 *
 * @author H
 *
 */
public class AlienCreator {

  /**
   * creating aliens and adding them to the game.
   * @param game level
   * @param score tracker to follow the hits
   * @param levelNumber to knoe the initial speed
   */
  public static void aliensCreator(GameLevel game, ScoreTracker score, int levelNumber) {
    BulletCreator bc = new BulletCreator(game);
    BulletRemover br = new BulletRemover(game);
    AlienKiller ak = new AlienKiller(game);
    Image image = null;
    InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("alien.png");
    try {
      image = ImageIO.read(is);
    } catch (IOException e) {
      System.out.println("Error: " + e);
    }
    int x = 10;
    int y;
    ArrayList<AlienColumn> columnList = new ArrayList<AlienColumn>();
    for (int i = 0; i < 10; i++) {
      ArrayList<Alien> alienList = new ArrayList<Alien>();
      y = 23;
      for (int j = 0; j < 5; j++) {
        Alien a = new Alien(bc, x, y, image);
        a.addHitListener(br);
        a.addHitListener(ak);
        a.addHitListener(score);
        a.addToGame(game);
        alienList.add(a);
        y += Alien.HEIGHT + 10;
        // listeners: ball remover, alien killer, and score tracker
      }
      AlienColumn alienColumn = new AlienColumn(alienList);
      columnList.add(alienColumn);
      // alienColumn.addToGame(game);

      x += Alien.WIDTH + 10;
    }
    int speed = 60 + 8 * levelNumber;
    AlienGroup group = new AlienGroup(columnList, speed, Alien.RIGHT);
    game.addAliens(group);
    game.addSprite(group);
  }
}