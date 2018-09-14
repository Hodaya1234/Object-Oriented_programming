

import java.io.File;

import animation.AnimationRunner;
import animation.HighScoresAnimation;
import animation.HighScoresTable;
import animation.KeyPressStoppableAnimation;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import game.GameFlow;
import menu.MenuAnimation;
import menu.Task;

/**
 *
 * @author H
 *
 */
public class MainGame {

  /**
   * main running game.
   * @param args empty
   */
  public static void main(String[] args) {
    GUI gui = new GUI("Arkanoid", 800, 600);
    AnimationRunner runner = new AnimationRunner(gui, 60);
    KeyboardSensor keyboard = gui.getKeyboardSensor();
    File highscores = new File("highscores");

    Task<Void> quit = new Task<Void>() {
      @Override
      public Void run() {
        gui.close();
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

    Task<Void> run = new Task<Void>() {
      @Override
      public Void run() {
        GameFlow flow = new GameFlow(gui, new AnimationRunner(gui, 60), gui.getKeyboardSensor());
        flow.runLevel();
        return null;
      }
    };

    while (true) {
      MenuAnimation<Task<Void>> menu = new MenuAnimation<Task<Void>>(keyboard);
      menu.addSelection("s", "[s] play", run);
      menu.addSelection("h", "[h] high scores", highScoreTask);
      menu.addSelection("q", "[q] quit", quit);
      runner.run(menu);
      Task<Void> status = menu.getStatus();
      status.run();
    }
  }
}