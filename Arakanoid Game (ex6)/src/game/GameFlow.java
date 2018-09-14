package game;

import java.io.File;
import java.io.IOException;
import java.util.List;

import biuoop.DialogManager;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import gameobjects.Counter;
import usergraphics.AnimationRunner;
import usergraphics.GameOver;
import usergraphics.HighScoresAnimation;
import usergraphics.HighScoresTable;
import usergraphics.KeyPressStoppableAnimation;
import usergraphics.ScoreInfo;
import usergraphics.YouWin;

/**
 * A game with levels.
 * @author H
 *
 */
public class GameFlow {

  private GUI gui;
  private AnimationRunner runner;
  private KeyboardSensor keyboard;
  private Counter score;
  private Counter lives;
  private HighScoresTable highScoresTable;
  private File highscores;

  /**
   * Constructor.
   * @param gui
   *          graphic user interface
   * @param runner
   *          for the animation loops
   * @param keyboard
   *          sensor
   */
  public GameFlow(GUI gui, AnimationRunner runner, KeyboardSensor keyboard) {
      this.gui = gui;
      this.runner = runner;
      this.keyboard = keyboard;
      this.score = new Counter();
      this.lives = new Counter(7);
      this.highscores = new File("highscores");
      this.highScoresTable = HighScoresTable.loadFromFile(highscores);
  }

  /**
   * Run a few levels in a row.
   * @param levels the list of levels to play
   */
  public void runLevels(List<LevelInformation> levels) {
    for (LevelInformation levelInfo : levels) {
      GameLevel level = new GameLevel(levelInfo, this.keyboard, this.runner, this.score, this.lives);
      level.initialize();
      while ((level.howManyBlocks() > 0) && (this.lives.getValue() >= 0)) {
        level.playOneTurn();
      }
      if (this.lives.getValue() < 1) {
        this.runner
            .run(new KeyPressStoppableAnimation(keyboard, KeyboardSensor.SPACE_KEY, new GameOver(score.getValue())));
         break;
      }
    }
    if (this.lives.getValue() >= 0) {
      this.runner.run(new KeyPressStoppableAnimation(keyboard, KeyboardSensor.SPACE_KEY, new YouWin(score.getValue())));
    }
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
    this.runner.run(
        new KeyPressStoppableAnimation(keyboard, KeyboardSensor.SPACE_KEY, new HighScoresAnimation(highScoresTable)));
  }
}