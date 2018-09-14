package game;

import java.util.List;

import gameobjects.Block;
import gameobjects.Sprite;
import geometry.Velocity;

/**
 * Information for each level.
 * @author H
 *
 */
public interface LevelInformation {
  /**
   * Number of balls for the initialized level.
   * @return number of balls
   */
  int numberOfBalls();

  /**
   * The initial velocity of each ball.
   * @return a list of the velocity's
   */
  List<Velocity> initialBallVelocities();

  /**
   * The speed in which the paddle moves.
   * @return the d-x of movement
   */
  int paddleSpeed();

  /**
   * The length of the paddle.
   * @return the width
   */
  int paddleWidth();

  /**
   * the level name will be displayed at the top of the screen.
   * @return a String of the name
   */
  String levelName();

  /**
   * Returns a sprite with the background of the level.
   * @return the background
   */
  Sprite getBackground();

  /**
   * The Blocks that make up this level, each block contains its size, color and location.
   * @return the list of blocks
   */
  List<Block> blocks();

  /**
   * Number of blocks that should be removed
   * before the level is considered to be "cleared".
   * This number should be <= blocks.size();
   * @return number of blocks
   */
  int numberOfBlocksToRemove();
}