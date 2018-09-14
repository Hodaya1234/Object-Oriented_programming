package maingame;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.Reader;
import java.util.List;

import javax.imageio.ImageIO;

import biuoop.GUI;
import biuoop.KeyboardSensor;
import filesinput.LevelSpecificationReader;
import game.GameFlow;
import game.LevelInformation;
import menu.MenuAnimation;
import menu.RunTask;
import menu.SubMenu;
import menu.Task;
import usergraphics.AnimationRunner;
import usergraphics.HighScoresAnimation;
import usergraphics.HighScoresTable;
import usergraphics.KeyPressStoppableAnimation;

/**
 *
 * @author H
 *
 */
public class MainGame {

  /**
   * Main class of the game.
   * @param levelSets
   *          file definitions file
   */
  public static void mainGame(String levelSets) {
    GUI gui = new GUI("Arkanoid", 800, 600);
    AnimationRunner runner = new AnimationRunner(gui, 60);
    KeyboardSensor keyboard = gui.getKeyboardSensor();
    File highscores = new File("highscores");

    Task<Void> quit = new Task<Void>() {
      @Override
      public Void run() {
        gui.close();
        System.exit(0);
        return null;
      }
    };
    Task<Void> highScoreTask = new Task<Void>() {
      public Void run() {
        HighScoresTable table = HighScoresTable.loadFromFile(highscores);
        KeyPressStoppableAnimation scores = new KeyPressStoppableAnimation(keyboard, "space",
            new HighScoresAnimation(table));
        runner.run(scores);
        return null;
      }
    };

    Task<Void> runSub = new Task<Void>() {
      @Override
      public Void run() {
        while (true) {
          Image menuImage = null;
          menuImage = getBackground("background_images/forrest.jpg");
          SubMenu<Task<Void>> submenu = new SubMenu<Task<Void>>(keyboard, menuImage);
          InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(levelSets);
          LineNumberReader l = new LineNumberReader(new InputStreamReader(is));
          String line;
          try {
            line = l.readLine();
            while (line != null) {
              if ((l.getLineNumber() % 2) == 1) {
                String path = l.readLine();
                Task<Void> runLevels = new RunTask<Void>(new GameFlow(gui, runner, keyboard), getLevelInfo(path));
                submenu.addSelection(line.split(":")[0], line.split(":")[1], runLevels);
              } else {
                line = l.readLine();
              }
            }
          } catch (IOException e) {
            e.printStackTrace();
          }
          runner.run(submenu);
          Task<Void> status = submenu.getStatus();
          status.run();
          break;
        }
        return null;
      }
    };

    Image menuImage = null;
    menuImage = getBackground("background_images/forrest.jpg");

    while (true) {
      MenuAnimation<Task<Void>> menu = new MenuAnimation<Task<Void>>(keyboard, menuImage);
      menu.addSelection("s", "[s] play", runSub);
      menu.addSelection("h", "[h] high scores", highScoreTask);
      menu.addSelection("q", "[q] quit", quit);
      runner.run(menu);
      Task<Void> status = menu.getStatus();
      status.run();
    }
  }

  /**
   * get a list of level information from a certain file.
   * @param levelInformation
   *          the file
   * @return level information list
   */
  public static List<LevelInformation> getLevelInfo(String levelInformation) {
    InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(levelInformation);
    Reader reader = new InputStreamReader(is);
    return new LevelSpecificationReader().fromReader(reader);
  }

  /**
   * get an image as input.
   * @param filename
   *          to open input stream
   * @return the image
   */
  public static Image getBackground(String filename) {
    Image image = null;
    InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(filename);
    try {
      image = ImageIO.read(is);
    } catch (IOException e1) {
      return null;
    }
    return image;
  }
}