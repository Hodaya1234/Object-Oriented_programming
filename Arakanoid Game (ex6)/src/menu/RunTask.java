package menu;

import java.util.List;

import game.GameFlow;
import game.LevelInformation;

/**
 *
 * @author H
 *
 * @param <T>
 */
public class RunTask<T> implements Task<T> {
  private GameFlow game;
  private List<LevelInformation> levelsList;

  /**
   * the task the runs the game.
   * @param game
   *          flow that runs levels
   * @param levelsList
   *          level information to run
   */
  public RunTask(GameFlow game, List<LevelInformation> levelsList) {
    this.game = game;
    this.levelsList = levelsList;
  }

  @Override
  public T run() {
    game.runLevels(levelsList);
    return null;
  }
}