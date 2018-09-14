package usergraphics;

import java.io.Serializable;

/**
 *
 * @author H
 *
 */
public class ScoreInfo implements Serializable {

  private static final long serialVersionUID = 1L;
  private String name;
  private int score;

  /**
   * Constructor.
   * @param name of the user
   * @param score of the game
   */
  public ScoreInfo(String name, int score) {
    this.name = name;
    this.score = score;
  }

  /**
   * Constructor.
   * @param name
   *          of the user
   * @param score
   *          of the game
   */
  public ScoreInfo(String name, String score) {
    this(name, Integer.parseInt(score));
  }

  /**
   * get user name.
   * @return String of the name
   */
  public String getName() {
    return this.name;
  }

  /**
   * get score of the winning game.
   * @return integer of the score
   */
  public int getScore() {
    return this.score;
  }
}
