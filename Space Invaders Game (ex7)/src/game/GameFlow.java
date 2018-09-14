package game;

import java.io.File;
import java.io.IOException;

import animation.AnimationRunner;
import animation.GameOver;
import animation.HighScoresAnimation;
import animation.HighScoresTable;
import animation.KeyPressStoppableAnimation;
import animation.ScoreInfo;
import biuoop.DialogManager;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import logic.Counter;

/**
 *
 * @author H
 *
 */
public class GameFlow {

  private GUI gui;
  private AnimationRunner runner;
  private KeyboardSensor keyboard;
  private HighScoresTable highScoresTable;
  private File highscores;
  private Counter score;
  private Counter lives;
  private int levelNumber;

  /**
   * Constructor.
   * @param gui Graphic user interface
   * @param runner animation
   * @param keyboard sensor
   */
  public GameFlow(GUI gui, AnimationRunner runner, KeyboardSensor keyboard) {
    this.gui = gui;
    this.runner = runner;
    this.keyboard = keyboard;
    this.score = new Counter();
    this.lives = new Counter(3);
    this.levelNumber = 1;
    this.highscores = new File("highscores");
    this.highScoresTable = HighScoresTable.loadFromFile(highscores);
  }

  /**
   * run the levels until no lives remain.
   */
  public void runLevel() {
    while (lives.getValue() > 0) {
      GameLevel level = new GameLevel(runner, keyboard, score, lives, levelNumber);
      level.initialize();
      level.run();
      levelNumber++;
    }
    runner.run(new KeyPressStoppableAnimation(keyboard, KeyboardSensor.SPACE_KEY, new GameOver(score.getValue())));
    if (this.highScoresTable.getRank(this.score.getValue()) <= HighScoresTable.SIZE) {
      DialogManager dialog = gui.getDialogManager();
      String name = dialog.showQuestionDialog("Name", "What is your name?", "");
      highScoresTable.add(new ScoreInfo(name, this.score.getValue()));
      try {
        highScoresTable.save(this.highscores);
      } catch (IOException e) {
        System.err.println("Error saving scores at end of game");
      }
    }
    runner.run(
        new KeyPressStoppableAnimation(keyboard, KeyboardSensor.SPACE_KEY, new HighScoresAnimation(highScoresTable)));
  }
}