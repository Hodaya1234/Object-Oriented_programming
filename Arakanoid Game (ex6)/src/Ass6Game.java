import maingame.MainGame;

/**
 *
 * @author H
 *
 */
public class Ass6Game {

  /**
   * assignment 6 game.
   * @param args
   *          receives a level definition file, or starts the default
   */
  public static void main(String[] args) {

    if (args.length == 1) {
      try {
        MainGame.mainGame(args[0]);
      } catch (Exception e) {
        MainGame.mainGame("level_sets.txt");
      }
    } else {
      MainGame.mainGame("level_sets.txt");
    }
  }
}